package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.repository.ProCheckPointRepository;
import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.service.dto.ProCheckPointDTO;
import vn.nvsoft.service.mapper.ProCheckPointMapper;
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
 * Integration tests for the {@link ProCheckPointResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProCheckPointResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private ProCheckPointRepository proCheckPointRepository;

    @Autowired
    private ProCheckPointMapper proCheckPointMapper;

    @Autowired
    private ProCheckPointService proCheckPointService;

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

    private MockMvc restProCheckPointMockMvc;

    private ProCheckPoint proCheckPoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProCheckPointResource proCheckPointResource = new ProCheckPointResource(proCheckPointService);
        this.restProCheckPointMockMvc = MockMvcBuilders.standaloneSetup(proCheckPointResource)
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
    public static ProCheckPoint createEntity(EntityManager em) {
        ProCheckPoint proCheckPoint = new ProCheckPoint()
            .ten(DEFAULT_TEN);
        return proCheckPoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProCheckPoint createUpdatedEntity(EntityManager em) {
        ProCheckPoint proCheckPoint = new ProCheckPoint()
            .ten(UPDATED_TEN);
        return proCheckPoint;
    }

    @BeforeEach
    public void initTest() {
        proCheckPoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createProCheckPoint() throws Exception {
        int databaseSizeBeforeCreate = proCheckPointRepository.findAll().size();

        // Create the ProCheckPoint
        ProCheckPointDTO proCheckPointDTO = proCheckPointMapper.toDto(proCheckPoint);
        restProCheckPointMockMvc.perform(post("/api/pro-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCheckPointDTO)))
            .andExpect(status().isCreated());

        // Validate the ProCheckPoint in the database
        List<ProCheckPoint> proCheckPointList = proCheckPointRepository.findAll();
        assertThat(proCheckPointList).hasSize(databaseSizeBeforeCreate + 1);
        ProCheckPoint testProCheckPoint = proCheckPointList.get(proCheckPointList.size() - 1);
        assertThat(testProCheckPoint.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createProCheckPointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proCheckPointRepository.findAll().size();

        // Create the ProCheckPoint with an existing ID
        proCheckPoint.setId(1L);
        ProCheckPointDTO proCheckPointDTO = proCheckPointMapper.toDto(proCheckPoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProCheckPointMockMvc.perform(post("/api/pro-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCheckPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCheckPoint in the database
        List<ProCheckPoint> proCheckPointList = proCheckPointRepository.findAll();
        assertThat(proCheckPointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProCheckPoints() throws Exception {
        // Initialize the database
        proCheckPointRepository.saveAndFlush(proCheckPoint);

        // Get all the proCheckPointList
        restProCheckPointMockMvc.perform(get("/api/pro-check-points?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proCheckPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getProCheckPoint() throws Exception {
        // Initialize the database
        proCheckPointRepository.saveAndFlush(proCheckPoint);

        // Get the proCheckPoint
        restProCheckPointMockMvc.perform(get("/api/pro-check-points/{id}", proCheckPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proCheckPoint.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }

    @Test
    @Transactional
    public void getNonExistingProCheckPoint() throws Exception {
        // Get the proCheckPoint
        restProCheckPointMockMvc.perform(get("/api/pro-check-points/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProCheckPoint() throws Exception {
        // Initialize the database
        proCheckPointRepository.saveAndFlush(proCheckPoint);

        int databaseSizeBeforeUpdate = proCheckPointRepository.findAll().size();

        // Update the proCheckPoint
        ProCheckPoint updatedProCheckPoint = proCheckPointRepository.findById(proCheckPoint.getId()).get();
        // Disconnect from session so that the updates on updatedProCheckPoint are not directly saved in db
        em.detach(updatedProCheckPoint);
        updatedProCheckPoint
            .ten(UPDATED_TEN);
        ProCheckPointDTO proCheckPointDTO = proCheckPointMapper.toDto(updatedProCheckPoint);

        restProCheckPointMockMvc.perform(put("/api/pro-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCheckPointDTO)))
            .andExpect(status().isOk());

        // Validate the ProCheckPoint in the database
        List<ProCheckPoint> proCheckPointList = proCheckPointRepository.findAll();
        assertThat(proCheckPointList).hasSize(databaseSizeBeforeUpdate);
        ProCheckPoint testProCheckPoint = proCheckPointList.get(proCheckPointList.size() - 1);
        assertThat(testProCheckPoint.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingProCheckPoint() throws Exception {
        int databaseSizeBeforeUpdate = proCheckPointRepository.findAll().size();

        // Create the ProCheckPoint
        ProCheckPointDTO proCheckPointDTO = proCheckPointMapper.toDto(proCheckPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProCheckPointMockMvc.perform(put("/api/pro-check-points")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCheckPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCheckPoint in the database
        List<ProCheckPoint> proCheckPointList = proCheckPointRepository.findAll();
        assertThat(proCheckPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProCheckPoint() throws Exception {
        // Initialize the database
        proCheckPointRepository.saveAndFlush(proCheckPoint);

        int databaseSizeBeforeDelete = proCheckPointRepository.findAll().size();

        // Delete the proCheckPoint
        restProCheckPointMockMvc.perform(delete("/api/pro-check-points/{id}", proCheckPoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProCheckPoint> proCheckPointList = proCheckPointRepository.findAll();
        assertThat(proCheckPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
