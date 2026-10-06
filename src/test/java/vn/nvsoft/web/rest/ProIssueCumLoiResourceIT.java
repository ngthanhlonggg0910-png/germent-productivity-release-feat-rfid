package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProIssueCumLoi;
import vn.nvsoft.repository.ProIssueCumLoiRepository;
import vn.nvsoft.service.ProIssueCumLoiService;
import vn.nvsoft.service.dto.ProIssueCumLoiDTO;
import vn.nvsoft.service.mapper.ProIssueCumLoiMapper;
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
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProIssueCumLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProIssueCumLoiResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MAU = "AAAAAAAAAA";
    private static final String UPDATED_MAU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TO_TRUONG = false;
    private static final Boolean UPDATED_TO_TRUONG = true;

    private static final Boolean DEFAULT_KY_THUAT = false;
    private static final Boolean UPDATED_KY_THUAT = true;

    private static final Boolean DEFAULT_QA = false;
    private static final Boolean UPDATED_QA = true;

    @Autowired
    private ProIssueCumLoiRepository proIssueCumLoiRepository;

    @Autowired
    private ProIssueCumLoiMapper proIssueCumLoiMapper;

    @Autowired
    private ProIssueCumLoiService proIssueCumLoiService;

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

    private MockMvc restProIssueCumLoiMockMvc;

    private ProIssueCumLoi proIssueCumLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProIssueCumLoiResource proIssueCumLoiResource = new ProIssueCumLoiResource(proIssueCumLoiService);
        this.restProIssueCumLoiMockMvc = MockMvcBuilders.standaloneSetup(proIssueCumLoiResource)
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
    public static ProIssueCumLoi createEntity(EntityManager em) {
        ProIssueCumLoi proIssueCumLoi = new ProIssueCumLoi()
            .ten(DEFAULT_TEN)
            .mau(DEFAULT_MAU)
            .toTruong(DEFAULT_TO_TRUONG)
            .kyThuat(DEFAULT_KY_THUAT)
            .qa(DEFAULT_QA);
        return proIssueCumLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProIssueCumLoi createUpdatedEntity(EntityManager em) {
        ProIssueCumLoi proIssueCumLoi = new ProIssueCumLoi()
            .ten(UPDATED_TEN)
            .mau(UPDATED_MAU)
            .toTruong(UPDATED_TO_TRUONG)
            .kyThuat(UPDATED_KY_THUAT)
            .qa(UPDATED_QA);
        return proIssueCumLoi;
    }

    @BeforeEach
    public void initTest() {
        proIssueCumLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProIssueCumLoi() throws Exception {
        int databaseSizeBeforeCreate = proIssueCumLoiRepository.findAll().size();

        // Create the ProIssueCumLoi
        ProIssueCumLoiDTO proIssueCumLoiDTO = proIssueCumLoiMapper.toDto(proIssueCumLoi);
        restProIssueCumLoiMockMvc.perform(post("/api/pro-issue-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueCumLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProIssueCumLoi in the database
        List<ProIssueCumLoi> proIssueCumLoiList = proIssueCumLoiRepository.findAll();
        assertThat(proIssueCumLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProIssueCumLoi testProIssueCumLoi = proIssueCumLoiList.get(proIssueCumLoiList.size() - 1);
        assertThat(testProIssueCumLoi.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProIssueCumLoi.getMau()).isEqualTo(DEFAULT_MAU);
        assertThat(testProIssueCumLoi.isToTruong()).isEqualTo(DEFAULT_TO_TRUONG);
        assertThat(testProIssueCumLoi.isKyThuat()).isEqualTo(DEFAULT_KY_THUAT);
        assertThat(testProIssueCumLoi.isQa()).isEqualTo(DEFAULT_QA);
    }

    @Test
    @Transactional
    public void createProIssueCumLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proIssueCumLoiRepository.findAll().size();

        // Create the ProIssueCumLoi with an existing ID
        proIssueCumLoi.setId(1L);
        ProIssueCumLoiDTO proIssueCumLoiDTO = proIssueCumLoiMapper.toDto(proIssueCumLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProIssueCumLoiMockMvc.perform(post("/api/pro-issue-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueCumLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssueCumLoi in the database
        List<ProIssueCumLoi> proIssueCumLoiList = proIssueCumLoiRepository.findAll();
        assertThat(proIssueCumLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProIssueCumLois() throws Exception {
        // Initialize the database
        proIssueCumLoiRepository.saveAndFlush(proIssueCumLoi);

        // Get all the proIssueCumLoiList
        restProIssueCumLoiMockMvc.perform(get("/api/pro-issue-cum-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proIssueCumLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mau").value(hasItem(DEFAULT_MAU)))
            .andExpect(jsonPath("$.[*].toTruong").value(hasItem(DEFAULT_TO_TRUONG.booleanValue())))
            .andExpect(jsonPath("$.[*].kyThuat").value(hasItem(DEFAULT_KY_THUAT.booleanValue())))
            .andExpect(jsonPath("$.[*].qa").value(hasItem(DEFAULT_QA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProIssueCumLoi() throws Exception {
        // Initialize the database
        proIssueCumLoiRepository.saveAndFlush(proIssueCumLoi);

        // Get the proIssueCumLoi
        restProIssueCumLoiMockMvc.perform(get("/api/pro-issue-cum-lois/{id}", proIssueCumLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proIssueCumLoi.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mau").value(DEFAULT_MAU))
            .andExpect(jsonPath("$.toTruong").value(DEFAULT_TO_TRUONG.booleanValue()))
            .andExpect(jsonPath("$.kyThuat").value(DEFAULT_KY_THUAT.booleanValue()))
            .andExpect(jsonPath("$.qa").value(DEFAULT_QA.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProIssueCumLoi() throws Exception {
        // Get the proIssueCumLoi
        restProIssueCumLoiMockMvc.perform(get("/api/pro-issue-cum-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProIssueCumLoi() throws Exception {
        // Initialize the database
        proIssueCumLoiRepository.saveAndFlush(proIssueCumLoi);

        int databaseSizeBeforeUpdate = proIssueCumLoiRepository.findAll().size();

        // Update the proIssueCumLoi
        ProIssueCumLoi updatedProIssueCumLoi = proIssueCumLoiRepository.findById(proIssueCumLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProIssueCumLoi are not directly saved in db
        em.detach(updatedProIssueCumLoi);
        updatedProIssueCumLoi
            .ten(UPDATED_TEN)
            .mau(UPDATED_MAU)
            .toTruong(UPDATED_TO_TRUONG)
            .kyThuat(UPDATED_KY_THUAT)
            .qa(UPDATED_QA);
        ProIssueCumLoiDTO proIssueCumLoiDTO = proIssueCumLoiMapper.toDto(updatedProIssueCumLoi);

        restProIssueCumLoiMockMvc.perform(put("/api/pro-issue-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueCumLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProIssueCumLoi in the database
        List<ProIssueCumLoi> proIssueCumLoiList = proIssueCumLoiRepository.findAll();
        assertThat(proIssueCumLoiList).hasSize(databaseSizeBeforeUpdate);
        ProIssueCumLoi testProIssueCumLoi = proIssueCumLoiList.get(proIssueCumLoiList.size() - 1);
        assertThat(testProIssueCumLoi.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProIssueCumLoi.getMau()).isEqualTo(UPDATED_MAU);
        assertThat(testProIssueCumLoi.isToTruong()).isEqualTo(UPDATED_TO_TRUONG);
        assertThat(testProIssueCumLoi.isKyThuat()).isEqualTo(UPDATED_KY_THUAT);
        assertThat(testProIssueCumLoi.isQa()).isEqualTo(UPDATED_QA);
    }

    @Test
    @Transactional
    public void updateNonExistingProIssueCumLoi() throws Exception {
        int databaseSizeBeforeUpdate = proIssueCumLoiRepository.findAll().size();

        // Create the ProIssueCumLoi
        ProIssueCumLoiDTO proIssueCumLoiDTO = proIssueCumLoiMapper.toDto(proIssueCumLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProIssueCumLoiMockMvc.perform(put("/api/pro-issue-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueCumLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssueCumLoi in the database
        List<ProIssueCumLoi> proIssueCumLoiList = proIssueCumLoiRepository.findAll();
        assertThat(proIssueCumLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProIssueCumLoi() throws Exception {
        // Initialize the database
        proIssueCumLoiRepository.saveAndFlush(proIssueCumLoi);

        int databaseSizeBeforeDelete = proIssueCumLoiRepository.findAll().size();

        // Delete the proIssueCumLoi
        restProIssueCumLoiMockMvc.perform(delete("/api/pro-issue-cum-lois/{id}", proIssueCumLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProIssueCumLoi> proIssueCumLoiList = proIssueCumLoiRepository.findAll();
        assertThat(proIssueCumLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
