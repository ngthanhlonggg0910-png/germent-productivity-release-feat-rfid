package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.repository.NVButtonRepository;
import vn.nvsoft.service.NVButtonService;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.mapper.NVButtonMapper;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import vn.nvsoft.domain.enumeration.NVButtonState;
import vn.nvsoft.domain.enumeration.LWT;
import vn.nvsoft.domain.enumeration.GroupButtonType;
/**
 * Integration tests for the {@link NVButtonResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class NVButtonResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOVERED_ON_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_DISCOVERED_ON_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_CMND_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_CMND_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_PL_OFF = "AAAAAAAAAA";
    private static final String UPDATED_PL_OFF = "BBBBBBBBBB";

    private static final String DEFAULT_PL_ON = "AAAAAAAAAA";
    private static final String UPDATED_PL_ON = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEV_IDS = "AAAAAAAAAA";
    private static final String UPDATED_DEV_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_TELE_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_TELE_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_STAT_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_STAT_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_AVTY_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_AVTY_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_PL_AVAIL = "AAAAAAAAAA";
    private static final String UPDATED_PL_AVAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PL_NOT_AVAIL = "AAAAAAAAAA";
    private static final String UPDATED_PL_NOT_AVAIL = "BBBBBBBBBB";

    private static final NVButtonState DEFAULT_STATE = NVButtonState.ON;
    private static final NVButtonState UPDATED_STATE = NVButtonState.OFF;

    private static final LWT DEFAULT_LWT = LWT.ON_LINE;
    private static final LWT UPDATED_LWT = LWT.OFF_LINE;

    private static final Instant DEFAULT_LAST_UPDATE_STATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_UPDATE_STATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final GroupButtonType DEFAULT_GROUP_TYPE = GroupButtonType.LIGHT;
    private static final GroupButtonType UPDATED_GROUP_TYPE = GroupButtonType.SEWING;

    @Autowired
    private NVButtonRepository nVButtonRepository;

    @Autowired
    private NVButtonMapper nVButtonMapper;

    @Autowired
    private NVButtonService nVButtonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNVButtonMockMvc;

    private NVButton nVButton;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NVButtonResource nVButtonResource = new NVButtonResource(nVButtonService);
        this.restNVButtonMockMvc = MockMvcBuilders.standaloneSetup(nVButtonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NVButton createEntity(EntityManager em) {
        NVButton nVButton = new NVButton()
            .name(DEFAULT_NAME)
            .discoveredOnTopic(DEFAULT_DISCOVERED_ON_TOPIC)
            .cmndTopic(DEFAULT_CMND_TOPIC)
            .plOff(DEFAULT_PL_OFF)
            .plOn(DEFAULT_PL_ON)
            .uniqId(DEFAULT_UNIQ_ID)
            .devIds(DEFAULT_DEV_IDS)
            .teleTopic(DEFAULT_TELE_TOPIC)
            .statTopic(DEFAULT_STAT_TOPIC)
            .avtyTopic(DEFAULT_AVTY_TOPIC)
            .plAvail(DEFAULT_PL_AVAIL)
            .plNotAvail(DEFAULT_PL_NOT_AVAIL)
            .state(DEFAULT_STATE)
            .lwt(DEFAULT_LWT)
            .lastUpdateState(DEFAULT_LAST_UPDATE_STATE)
            .groupType(DEFAULT_GROUP_TYPE);
        return nVButton;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NVButton createUpdatedEntity(EntityManager em) {
        NVButton nVButton = new NVButton()
            .name(UPDATED_NAME)
            .discoveredOnTopic(UPDATED_DISCOVERED_ON_TOPIC)
            .cmndTopic(UPDATED_CMND_TOPIC)
            .plOff(UPDATED_PL_OFF)
            .plOn(UPDATED_PL_ON)
            .uniqId(UPDATED_UNIQ_ID)
            .devIds(UPDATED_DEV_IDS)
            .teleTopic(UPDATED_TELE_TOPIC)
            .statTopic(UPDATED_STAT_TOPIC)
            .avtyTopic(UPDATED_AVTY_TOPIC)
            .plAvail(UPDATED_PL_AVAIL)
            .plNotAvail(UPDATED_PL_NOT_AVAIL)
            .state(UPDATED_STATE)
            .lwt(UPDATED_LWT)
            .lastUpdateState(UPDATED_LAST_UPDATE_STATE)
            .groupType(UPDATED_GROUP_TYPE);
        return nVButton;
    }

    @BeforeEach
    public void initTest() {
        nVButton = createEntity(em);
    }

    @Test
    @Transactional
    public void createNVButton() throws Exception {
        int databaseSizeBeforeCreate = nVButtonRepository.findAll().size();

        // Create the NVButton
        NVButtonDTO nVButtonDTO = nVButtonMapper.toDto(nVButton);
        restNVButtonMockMvc.perform(post("/api/nv-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonDTO)))
            .andExpect(status().isCreated());

        // Validate the NVButton in the database
        List<NVButton> nVButtonList = nVButtonRepository.findAll();
        assertThat(nVButtonList).hasSize(databaseSizeBeforeCreate + 1);
        NVButton testNVButton = nVButtonList.get(nVButtonList.size() - 1);
        assertThat(testNVButton.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNVButton.getDiscoveredOnTopic()).isEqualTo(DEFAULT_DISCOVERED_ON_TOPIC);
        assertThat(testNVButton.getCmndTopic()).isEqualTo(DEFAULT_CMND_TOPIC);
        assertThat(testNVButton.getPlOff()).isEqualTo(DEFAULT_PL_OFF);
        assertThat(testNVButton.getPlOn()).isEqualTo(DEFAULT_PL_ON);
        assertThat(testNVButton.getUniqId()).isEqualTo(DEFAULT_UNIQ_ID);
        assertThat(testNVButton.getDevIds()).isEqualTo(DEFAULT_DEV_IDS);
        assertThat(testNVButton.getTeleTopic()).isEqualTo(DEFAULT_TELE_TOPIC);
        assertThat(testNVButton.getStatTopic()).isEqualTo(DEFAULT_STAT_TOPIC);
        assertThat(testNVButton.getAvtyTopic()).isEqualTo(DEFAULT_AVTY_TOPIC);
        assertThat(testNVButton.getPlAvail()).isEqualTo(DEFAULT_PL_AVAIL);
        assertThat(testNVButton.getPlNotAvail()).isEqualTo(DEFAULT_PL_NOT_AVAIL);
        assertThat(testNVButton.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testNVButton.getLwt()).isEqualTo(DEFAULT_LWT);
        assertThat(testNVButton.getLastUpdateState()).isEqualTo(DEFAULT_LAST_UPDATE_STATE);
        assertThat(testNVButton.getGroupType()).isEqualTo(DEFAULT_GROUP_TYPE);
    }

    @Test
    @Transactional
    public void createNVButtonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nVButtonRepository.findAll().size();

        // Create the NVButton with an existing ID
        nVButton.setId(1L);
        NVButtonDTO nVButtonDTO = nVButtonMapper.toDto(nVButton);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNVButtonMockMvc.perform(post("/api/nv-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NVButton in the database
        List<NVButton> nVButtonList = nVButtonRepository.findAll();
        assertThat(nVButtonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNVButtons() throws Exception {
        // Initialize the database
        nVButtonRepository.saveAndFlush(nVButton);

        // Get all the nVButtonList
        restNVButtonMockMvc.perform(get("/api/nv-buttons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nVButton.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].discoveredOnTopic").value(hasItem(DEFAULT_DISCOVERED_ON_TOPIC)))
            .andExpect(jsonPath("$.[*].cmndTopic").value(hasItem(DEFAULT_CMND_TOPIC)))
            .andExpect(jsonPath("$.[*].plOff").value(hasItem(DEFAULT_PL_OFF)))
            .andExpect(jsonPath("$.[*].plOn").value(hasItem(DEFAULT_PL_ON)))
            .andExpect(jsonPath("$.[*].uniqId").value(hasItem(DEFAULT_UNIQ_ID)))
            .andExpect(jsonPath("$.[*].devIds").value(hasItem(DEFAULT_DEV_IDS)))
            .andExpect(jsonPath("$.[*].teleTopic").value(hasItem(DEFAULT_TELE_TOPIC)))
            .andExpect(jsonPath("$.[*].statTopic").value(hasItem(DEFAULT_STAT_TOPIC)))
            .andExpect(jsonPath("$.[*].avtyTopic").value(hasItem(DEFAULT_AVTY_TOPIC)))
            .andExpect(jsonPath("$.[*].plAvail").value(hasItem(DEFAULT_PL_AVAIL)))
            .andExpect(jsonPath("$.[*].plNotAvail").value(hasItem(DEFAULT_PL_NOT_AVAIL)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].lwt").value(hasItem(DEFAULT_LWT.toString())))
            .andExpect(jsonPath("$.[*].lastUpdateState").value(hasItem(DEFAULT_LAST_UPDATE_STATE.toString())))
            .andExpect(jsonPath("$.[*].groupType").value(hasItem(DEFAULT_GROUP_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getNVButton() throws Exception {
        // Initialize the database
        nVButtonRepository.saveAndFlush(nVButton);

        // Get the nVButton
        restNVButtonMockMvc.perform(get("/api/nv-buttons/{id}", nVButton.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nVButton.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.discoveredOnTopic").value(DEFAULT_DISCOVERED_ON_TOPIC))
            .andExpect(jsonPath("$.cmndTopic").value(DEFAULT_CMND_TOPIC))
            .andExpect(jsonPath("$.plOff").value(DEFAULT_PL_OFF))
            .andExpect(jsonPath("$.plOn").value(DEFAULT_PL_ON))
            .andExpect(jsonPath("$.uniqId").value(DEFAULT_UNIQ_ID))
            .andExpect(jsonPath("$.devIds").value(DEFAULT_DEV_IDS))
            .andExpect(jsonPath("$.teleTopic").value(DEFAULT_TELE_TOPIC))
            .andExpect(jsonPath("$.statTopic").value(DEFAULT_STAT_TOPIC))
            .andExpect(jsonPath("$.avtyTopic").value(DEFAULT_AVTY_TOPIC))
            .andExpect(jsonPath("$.plAvail").value(DEFAULT_PL_AVAIL))
            .andExpect(jsonPath("$.plNotAvail").value(DEFAULT_PL_NOT_AVAIL))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.lwt").value(DEFAULT_LWT.toString()))
            .andExpect(jsonPath("$.lastUpdateState").value(DEFAULT_LAST_UPDATE_STATE.toString()))
            .andExpect(jsonPath("$.groupType").value(DEFAULT_GROUP_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNVButton() throws Exception {
        // Get the nVButton
        restNVButtonMockMvc.perform(get("/api/nv-buttons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNVButton() throws Exception {
        // Initialize the database
        nVButtonRepository.saveAndFlush(nVButton);

        int databaseSizeBeforeUpdate = nVButtonRepository.findAll().size();

        // Update the nVButton
        NVButton updatedNVButton = nVButtonRepository.findById(nVButton.getId()).get();
        // Disconnect from session so that the updates on updatedNVButton are not directly saved in db
        em.detach(updatedNVButton);
        updatedNVButton
            .name(UPDATED_NAME)
            .discoveredOnTopic(UPDATED_DISCOVERED_ON_TOPIC)
            .cmndTopic(UPDATED_CMND_TOPIC)
            .plOff(UPDATED_PL_OFF)
            .plOn(UPDATED_PL_ON)
            .uniqId(UPDATED_UNIQ_ID)
            .devIds(UPDATED_DEV_IDS)
            .teleTopic(UPDATED_TELE_TOPIC)
            .statTopic(UPDATED_STAT_TOPIC)
            .avtyTopic(UPDATED_AVTY_TOPIC)
            .plAvail(UPDATED_PL_AVAIL)
            .plNotAvail(UPDATED_PL_NOT_AVAIL)
            .state(UPDATED_STATE)
            .lwt(UPDATED_LWT)
            .lastUpdateState(UPDATED_LAST_UPDATE_STATE)
            .groupType(UPDATED_GROUP_TYPE);
        NVButtonDTO nVButtonDTO = nVButtonMapper.toDto(updatedNVButton);

        restNVButtonMockMvc.perform(put("/api/nv-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonDTO)))
            .andExpect(status().isOk());

        // Validate the NVButton in the database
        List<NVButton> nVButtonList = nVButtonRepository.findAll();
        assertThat(nVButtonList).hasSize(databaseSizeBeforeUpdate);
        NVButton testNVButton = nVButtonList.get(nVButtonList.size() - 1);
        assertThat(testNVButton.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNVButton.getDiscoveredOnTopic()).isEqualTo(UPDATED_DISCOVERED_ON_TOPIC);
        assertThat(testNVButton.getCmndTopic()).isEqualTo(UPDATED_CMND_TOPIC);
        assertThat(testNVButton.getPlOff()).isEqualTo(UPDATED_PL_OFF);
        assertThat(testNVButton.getPlOn()).isEqualTo(UPDATED_PL_ON);
        assertThat(testNVButton.getUniqId()).isEqualTo(UPDATED_UNIQ_ID);
        assertThat(testNVButton.getDevIds()).isEqualTo(UPDATED_DEV_IDS);
        assertThat(testNVButton.getTeleTopic()).isEqualTo(UPDATED_TELE_TOPIC);
        assertThat(testNVButton.getStatTopic()).isEqualTo(UPDATED_STAT_TOPIC);
        assertThat(testNVButton.getAvtyTopic()).isEqualTo(UPDATED_AVTY_TOPIC);
        assertThat(testNVButton.getPlAvail()).isEqualTo(UPDATED_PL_AVAIL);
        assertThat(testNVButton.getPlNotAvail()).isEqualTo(UPDATED_PL_NOT_AVAIL);
        assertThat(testNVButton.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testNVButton.getLwt()).isEqualTo(UPDATED_LWT);
        assertThat(testNVButton.getLastUpdateState()).isEqualTo(UPDATED_LAST_UPDATE_STATE);
        assertThat(testNVButton.getGroupType()).isEqualTo(UPDATED_GROUP_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingNVButton() throws Exception {
        int databaseSizeBeforeUpdate = nVButtonRepository.findAll().size();

        // Create the NVButton
        NVButtonDTO nVButtonDTO = nVButtonMapper.toDto(nVButton);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNVButtonMockMvc.perform(put("/api/nv-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NVButton in the database
        List<NVButton> nVButtonList = nVButtonRepository.findAll();
        assertThat(nVButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNVButton() throws Exception {
        // Initialize the database
        nVButtonRepository.saveAndFlush(nVButton);

        int databaseSizeBeforeDelete = nVButtonRepository.findAll().size();

        // Delete the nVButton
        restNVButtonMockMvc.perform(delete("/api/nv-buttons/{id}", nVButton.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NVButton> nVButtonList = nVButtonRepository.findAll();
        assertThat(nVButtonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
