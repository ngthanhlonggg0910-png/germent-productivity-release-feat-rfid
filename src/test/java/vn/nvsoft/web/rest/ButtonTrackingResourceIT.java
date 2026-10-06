package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ButtonTracking;
import vn.nvsoft.repository.ButtonTrackingRepository;
import vn.nvsoft.service.ButtonTrackingService;
import vn.nvsoft.service.dto.ButtonTrackingDTO;
import vn.nvsoft.service.mapper.ButtonTrackingMapper;
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

/**
 * Integration tests for the {@link ButtonTrackingResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ButtonTrackingResourceIT {

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CURRENT_ALIAS_STATE = 1;
    private static final Integer UPDATED_CURRENT_ALIAS_STATE = 2;

    private static final Long DEFAULT_TRACKING_ID = 1L;
    private static final Long UPDATED_TRACKING_ID = 2L;

    @Autowired
    private ButtonTrackingRepository buttonTrackingRepository;

    @Autowired
    private ButtonTrackingMapper buttonTrackingMapper;

    @Autowired
    private ButtonTrackingService buttonTrackingService;

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

    private MockMvc restButtonTrackingMockMvc;

    private ButtonTracking buttonTracking;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ButtonTrackingResource buttonTrackingResource = new ButtonTrackingResource(buttonTrackingService);
        this.restButtonTrackingMockMvc = MockMvcBuilders.standaloneSetup(buttonTrackingResource)
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
    public static ButtonTracking createEntity(EntityManager em) {
        ButtonTracking buttonTracking = new ButtonTracking()
            .updateDate(DEFAULT_UPDATE_DATE)
            .currentAliasState(DEFAULT_CURRENT_ALIAS_STATE)
            .trackingId(DEFAULT_TRACKING_ID);
        return buttonTracking;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ButtonTracking createUpdatedEntity(EntityManager em) {
        ButtonTracking buttonTracking = new ButtonTracking()
            .updateDate(UPDATED_UPDATE_DATE)
            .currentAliasState(UPDATED_CURRENT_ALIAS_STATE)
            .trackingId(UPDATED_TRACKING_ID);
        return buttonTracking;
    }

    @BeforeEach
    public void initTest() {
        buttonTracking = createEntity(em);
    }

    @Test
    @Transactional
    public void createButtonTracking() throws Exception {
        int databaseSizeBeforeCreate = buttonTrackingRepository.findAll().size();

        // Create the ButtonTracking
        ButtonTrackingDTO buttonTrackingDTO = buttonTrackingMapper.toDto(buttonTracking);
        restButtonTrackingMockMvc.perform(post("/api/button-trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buttonTrackingDTO)))
            .andExpect(status().isCreated());

        // Validate the ButtonTracking in the database
        List<ButtonTracking> buttonTrackingList = buttonTrackingRepository.findAll();
        assertThat(buttonTrackingList).hasSize(databaseSizeBeforeCreate + 1);
        ButtonTracking testButtonTracking = buttonTrackingList.get(buttonTrackingList.size() - 1);
        assertThat(testButtonTracking.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testButtonTracking.getCurrentAliasState()).isEqualTo(DEFAULT_CURRENT_ALIAS_STATE);
        assertThat(testButtonTracking.getTrackingId()).isEqualTo(DEFAULT_TRACKING_ID);
    }

    @Test
    @Transactional
    public void createButtonTrackingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buttonTrackingRepository.findAll().size();

        // Create the ButtonTracking with an existing ID
        buttonTracking.setId(1L);
        ButtonTrackingDTO buttonTrackingDTO = buttonTrackingMapper.toDto(buttonTracking);

        // An entity with an existing ID cannot be created, so this API call must fail
        restButtonTrackingMockMvc.perform(post("/api/button-trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buttonTrackingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ButtonTracking in the database
        List<ButtonTracking> buttonTrackingList = buttonTrackingRepository.findAll();
        assertThat(buttonTrackingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllButtonTrackings() throws Exception {
        // Initialize the database
        buttonTrackingRepository.saveAndFlush(buttonTracking);

        // Get all the buttonTrackingList
        restButtonTrackingMockMvc.perform(get("/api/button-trackings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buttonTracking.getId().intValue())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].currentAliasState").value(hasItem(DEFAULT_CURRENT_ALIAS_STATE)))
            .andExpect(jsonPath("$.[*].trackingId").value(hasItem(DEFAULT_TRACKING_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getButtonTracking() throws Exception {
        // Initialize the database
        buttonTrackingRepository.saveAndFlush(buttonTracking);

        // Get the buttonTracking
        restButtonTrackingMockMvc.perform(get("/api/button-trackings/{id}", buttonTracking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buttonTracking.getId().intValue()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.currentAliasState").value(DEFAULT_CURRENT_ALIAS_STATE))
            .andExpect(jsonPath("$.trackingId").value(DEFAULT_TRACKING_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingButtonTracking() throws Exception {
        // Get the buttonTracking
        restButtonTrackingMockMvc.perform(get("/api/button-trackings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateButtonTracking() throws Exception {
        // Initialize the database
        buttonTrackingRepository.saveAndFlush(buttonTracking);

        int databaseSizeBeforeUpdate = buttonTrackingRepository.findAll().size();

        // Update the buttonTracking
        ButtonTracking updatedButtonTracking = buttonTrackingRepository.findById(buttonTracking.getId()).get();
        // Disconnect from session so that the updates on updatedButtonTracking are not directly saved in db
        em.detach(updatedButtonTracking);
        updatedButtonTracking
            .updateDate(UPDATED_UPDATE_DATE)
            .currentAliasState(UPDATED_CURRENT_ALIAS_STATE)
            .trackingId(UPDATED_TRACKING_ID);
        ButtonTrackingDTO buttonTrackingDTO = buttonTrackingMapper.toDto(updatedButtonTracking);

        restButtonTrackingMockMvc.perform(put("/api/button-trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buttonTrackingDTO)))
            .andExpect(status().isOk());

        // Validate the ButtonTracking in the database
        List<ButtonTracking> buttonTrackingList = buttonTrackingRepository.findAll();
        assertThat(buttonTrackingList).hasSize(databaseSizeBeforeUpdate);
        ButtonTracking testButtonTracking = buttonTrackingList.get(buttonTrackingList.size() - 1);
        assertThat(testButtonTracking.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testButtonTracking.getCurrentAliasState()).isEqualTo(UPDATED_CURRENT_ALIAS_STATE);
        assertThat(testButtonTracking.getTrackingId()).isEqualTo(UPDATED_TRACKING_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingButtonTracking() throws Exception {
        int databaseSizeBeforeUpdate = buttonTrackingRepository.findAll().size();

        // Create the ButtonTracking
        ButtonTrackingDTO buttonTrackingDTO = buttonTrackingMapper.toDto(buttonTracking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restButtonTrackingMockMvc.perform(put("/api/button-trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buttonTrackingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ButtonTracking in the database
        List<ButtonTracking> buttonTrackingList = buttonTrackingRepository.findAll();
        assertThat(buttonTrackingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteButtonTracking() throws Exception {
        // Initialize the database
        buttonTrackingRepository.saveAndFlush(buttonTracking);

        int databaseSizeBeforeDelete = buttonTrackingRepository.findAll().size();

        // Delete the buttonTracking
        restButtonTrackingMockMvc.perform(delete("/api/button-trackings/{id}", buttonTracking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ButtonTracking> buttonTrackingList = buttonTrackingRepository.findAll();
        assertThat(buttonTrackingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
