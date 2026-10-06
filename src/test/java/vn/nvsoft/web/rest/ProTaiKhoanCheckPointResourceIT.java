package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.repository.ProTaiKhoanCheckPointRepository;
import vn.nvsoft.service.ProTaiKhoanCheckPointService;
import vn.nvsoft.service.dto.ProTaiKhoanCheckPointDTO;
import vn.nvsoft.service.mapper.ProTaiKhoanCheckPointMapper;
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
 * Integration tests for the {@link ProTaiKhoanCheckPointResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProTaiKhoanCheckPointResourceIT {

    private static final String DEFAULT_TAI_KHOAN = "AAAAAAAAAA";
    private static final String UPDATED_TAI_KHOAN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CHECKED = false;
    private static final Boolean UPDATED_CHECKED = true;

    @Autowired
    private ProTaiKhoanCheckPointRepository proTaiKhoanCheckPointRepository;

    @Autowired
    private ProTaiKhoanCheckPointMapper proTaiKhoanCheckPointMapper;

    @Autowired
    private ProTaiKhoanCheckPointService proTaiKhoanCheckPointService;

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

    private MockMvc restProTaiKhoanCheckPointMockMvc;

    private ProTaiKhoanCheckPoint proTaiKhoanCheckPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProTaiKhoanCheckPointResource proTaiKhoanCheckPointResource = new ProTaiKhoanCheckPointResource(proTaiKhoanCheckPointService);
        this.restProTaiKhoanCheckPointMockMvc = MockMvcBuilders.standaloneSetup(proTaiKhoanCheckPointResource)
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
    public static ProTaiKhoanCheckPoint createEntity(EntityManager em) {
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint = new ProTaiKhoanCheckPoint()
            .taiKhoan(DEFAULT_TAI_KHOAN)
            .checked(DEFAULT_CHECKED);
        return proTaiKhoanCheckPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProTaiKhoanCheckPoint createUpdatedEntity(EntityManager em) {
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint = new ProTaiKhoanCheckPoint()
            .taiKhoan(UPDATED_TAI_KHOAN)
            .checked(UPDATED_CHECKED);
        return proTaiKhoanCheckPoint;
    }

    @BeforeEach
    public void initTest() {
        proTaiKhoanCheckPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createProTaiKhoanCheckPoint() throws Exception {
        int databaseSizeBeforeCreate = proTaiKhoanCheckPointRepository.findAll().size();

        // Create the ProTaiKhoanCheckPoint
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO = proTaiKhoanCheckPointMapper.toDto(proTaiKhoanCheckPoint);
        restProTaiKhoanCheckPointMockMvc.perform(post("/api/pro-tai-khoan-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTaiKhoanCheckPointDTO)))
            .andExpect(status().isCreated());

        // Validate the ProTaiKhoanCheckPoint in the database
        List<ProTaiKhoanCheckPoint> proTaiKhoanCheckPointList = proTaiKhoanCheckPointRepository.findAll();
        assertThat(proTaiKhoanCheckPointList).hasSize(databaseSizeBeforeCreate + 1);
        ProTaiKhoanCheckPoint testProTaiKhoanCheckPoint = proTaiKhoanCheckPointList.get(proTaiKhoanCheckPointList.size() - 1);
        assertThat(testProTaiKhoanCheckPoint.getTaiKhoan()).isEqualTo(DEFAULT_TAI_KHOAN);
        assertThat(testProTaiKhoanCheckPoint.isChecked()).isEqualTo(DEFAULT_CHECKED);
    }

    @Test
    @Transactional
    public void createProTaiKhoanCheckPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proTaiKhoanCheckPointRepository.findAll().size();

        // Create the ProTaiKhoanCheckPoint with an existing ID
        proTaiKhoanCheckPoint.setId(1L);
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO = proTaiKhoanCheckPointMapper.toDto(proTaiKhoanCheckPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProTaiKhoanCheckPointMockMvc.perform(post("/api/pro-tai-khoan-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTaiKhoanCheckPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProTaiKhoanCheckPoint in the database
        List<ProTaiKhoanCheckPoint> proTaiKhoanCheckPointList = proTaiKhoanCheckPointRepository.findAll();
        assertThat(proTaiKhoanCheckPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProTaiKhoanCheckPoints() throws Exception {
        // Initialize the database
        proTaiKhoanCheckPointRepository.saveAndFlush(proTaiKhoanCheckPoint);

        // Get all the proTaiKhoanCheckPointList
        restProTaiKhoanCheckPointMockMvc.perform(get("/api/pro-tai-khoan-check-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proTaiKhoanCheckPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].taiKhoan").value(hasItem(DEFAULT_TAI_KHOAN)))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProTaiKhoanCheckPoint() throws Exception {
        // Initialize the database
        proTaiKhoanCheckPointRepository.saveAndFlush(proTaiKhoanCheckPoint);

        // Get the proTaiKhoanCheckPoint
        restProTaiKhoanCheckPointMockMvc.perform(get("/api/pro-tai-khoan-check-points/{id}", proTaiKhoanCheckPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proTaiKhoanCheckPoint.getId().intValue()))
            .andExpect(jsonPath("$.taiKhoan").value(DEFAULT_TAI_KHOAN))
            .andExpect(jsonPath("$.checked").value(DEFAULT_CHECKED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProTaiKhoanCheckPoint() throws Exception {
        // Get the proTaiKhoanCheckPoint
        restProTaiKhoanCheckPointMockMvc.perform(get("/api/pro-tai-khoan-check-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProTaiKhoanCheckPoint() throws Exception {
        // Initialize the database
        proTaiKhoanCheckPointRepository.saveAndFlush(proTaiKhoanCheckPoint);

        int databaseSizeBeforeUpdate = proTaiKhoanCheckPointRepository.findAll().size();

        // Update the proTaiKhoanCheckPoint
        ProTaiKhoanCheckPoint updatedProTaiKhoanCheckPoint = proTaiKhoanCheckPointRepository.findById(proTaiKhoanCheckPoint.getId()).get();
        // Disconnect from session so that the updates on updatedProTaiKhoanCheckPoint are not directly saved in db
        em.detach(updatedProTaiKhoanCheckPoint);
        updatedProTaiKhoanCheckPoint
            .taiKhoan(UPDATED_TAI_KHOAN)
            .checked(UPDATED_CHECKED);
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO = proTaiKhoanCheckPointMapper.toDto(updatedProTaiKhoanCheckPoint);

        restProTaiKhoanCheckPointMockMvc.perform(put("/api/pro-tai-khoan-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTaiKhoanCheckPointDTO)))
            .andExpect(status().isOk());

        // Validate the ProTaiKhoanCheckPoint in the database
        List<ProTaiKhoanCheckPoint> proTaiKhoanCheckPointList = proTaiKhoanCheckPointRepository.findAll();
        assertThat(proTaiKhoanCheckPointList).hasSize(databaseSizeBeforeUpdate);
        ProTaiKhoanCheckPoint testProTaiKhoanCheckPoint = proTaiKhoanCheckPointList.get(proTaiKhoanCheckPointList.size() - 1);
        assertThat(testProTaiKhoanCheckPoint.getTaiKhoan()).isEqualTo(UPDATED_TAI_KHOAN);
        assertThat(testProTaiKhoanCheckPoint.isChecked()).isEqualTo(UPDATED_CHECKED);
    }

    @Test
    @Transactional
    public void updateNonExistingProTaiKhoanCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = proTaiKhoanCheckPointRepository.findAll().size();

        // Create the ProTaiKhoanCheckPoint
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO = proTaiKhoanCheckPointMapper.toDto(proTaiKhoanCheckPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProTaiKhoanCheckPointMockMvc.perform(put("/api/pro-tai-khoan-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTaiKhoanCheckPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProTaiKhoanCheckPoint in the database
        List<ProTaiKhoanCheckPoint> proTaiKhoanCheckPointList = proTaiKhoanCheckPointRepository.findAll();
        assertThat(proTaiKhoanCheckPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProTaiKhoanCheckPoint() throws Exception {
        // Initialize the database
        proTaiKhoanCheckPointRepository.saveAndFlush(proTaiKhoanCheckPoint);

        int databaseSizeBeforeDelete = proTaiKhoanCheckPointRepository.findAll().size();

        // Delete the proTaiKhoanCheckPoint
        restProTaiKhoanCheckPointMockMvc.perform(delete("/api/pro-tai-khoan-check-points/{id}", proTaiKhoanCheckPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProTaiKhoanCheckPoint> proTaiKhoanCheckPointList = proTaiKhoanCheckPointRepository.findAll();
        assertThat(proTaiKhoanCheckPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
