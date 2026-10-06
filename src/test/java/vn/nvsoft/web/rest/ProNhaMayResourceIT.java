package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProNhaMay;
import vn.nvsoft.repository.ProNhaMayRepository;
import vn.nvsoft.service.ProNhaMayService;
import vn.nvsoft.service.dto.ProNhaMayDTO;
import vn.nvsoft.service.mapper.ProNhaMayMapper;
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
 * Integration tests for the {@link ProNhaMayResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProNhaMayResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;

    @Autowired
    private ProNhaMayRepository proNhaMayRepository;

    @Autowired
    private ProNhaMayMapper proNhaMayMapper;

    @Autowired
    private ProNhaMayService proNhaMayService;

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

    private MockMvc restProNhaMayMockMvc;

    private ProNhaMay proNhaMay;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProNhaMayResource proNhaMayResource = new ProNhaMayResource(proNhaMayService);
        this.restProNhaMayMockMvc = MockMvcBuilders.standaloneSetup(proNhaMayResource)
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
    public static ProNhaMay createEntity(EntityManager em) {
        ProNhaMay proNhaMay = new ProNhaMay()
            .ten(DEFAULT_TEN)
            .stt(DEFAULT_STT);
        return proNhaMay;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNhaMay createUpdatedEntity(EntityManager em) {
        ProNhaMay proNhaMay = new ProNhaMay()
            .ten(UPDATED_TEN)
            .stt(UPDATED_STT);
        return proNhaMay;
    }

    @BeforeEach
    public void initTest() {
        proNhaMay = createEntity(em);
    }

    @Test
    @Transactional
    public void createProNhaMay() throws Exception {
        int databaseSizeBeforeCreate = proNhaMayRepository.findAll().size();

        // Create the ProNhaMay
        ProNhaMayDTO proNhaMayDTO = proNhaMayMapper.toDto(proNhaMay);
        restProNhaMayMockMvc.perform(post("/api/pro-nha-mays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhaMayDTO)))
            .andExpect(status().isCreated());

        // Validate the ProNhaMay in the database
        List<ProNhaMay> proNhaMayList = proNhaMayRepository.findAll();
        assertThat(proNhaMayList).hasSize(databaseSizeBeforeCreate + 1);
        ProNhaMay testProNhaMay = proNhaMayList.get(proNhaMayList.size() - 1);
        assertThat(testProNhaMay.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProNhaMay.getStt()).isEqualTo(DEFAULT_STT);
    }

    @Test
    @Transactional
    public void createProNhaMayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proNhaMayRepository.findAll().size();

        // Create the ProNhaMay with an existing ID
        proNhaMay.setId(1L);
        ProNhaMayDTO proNhaMayDTO = proNhaMayMapper.toDto(proNhaMay);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProNhaMayMockMvc.perform(post("/api/pro-nha-mays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhaMayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNhaMay in the database
        List<ProNhaMay> proNhaMayList = proNhaMayRepository.findAll();
        assertThat(proNhaMayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProNhaMays() throws Exception {
        // Initialize the database
        proNhaMayRepository.saveAndFlush(proNhaMay);

        // Get all the proNhaMayList
        restProNhaMayMockMvc.perform(get("/api/pro-nha-mays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proNhaMay.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)));
    }
    
    @Test
    @Transactional
    public void getProNhaMay() throws Exception {
        // Initialize the database
        proNhaMayRepository.saveAndFlush(proNhaMay);

        // Get the proNhaMay
        restProNhaMayMockMvc.perform(get("/api/pro-nha-mays/{id}", proNhaMay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proNhaMay.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT));
    }

    @Test
    @Transactional
    public void getNonExistingProNhaMay() throws Exception {
        // Get the proNhaMay
        restProNhaMayMockMvc.perform(get("/api/pro-nha-mays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProNhaMay() throws Exception {
        // Initialize the database
        proNhaMayRepository.saveAndFlush(proNhaMay);

        int databaseSizeBeforeUpdate = proNhaMayRepository.findAll().size();

        // Update the proNhaMay
        ProNhaMay updatedProNhaMay = proNhaMayRepository.findById(proNhaMay.getId()).get();
        // Disconnect from session so that the updates on updatedProNhaMay are not directly saved in db
        em.detach(updatedProNhaMay);
        updatedProNhaMay
            .ten(UPDATED_TEN)
            .stt(UPDATED_STT);
        ProNhaMayDTO proNhaMayDTO = proNhaMayMapper.toDto(updatedProNhaMay);

        restProNhaMayMockMvc.perform(put("/api/pro-nha-mays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhaMayDTO)))
            .andExpect(status().isOk());

        // Validate the ProNhaMay in the database
        List<ProNhaMay> proNhaMayList = proNhaMayRepository.findAll();
        assertThat(proNhaMayList).hasSize(databaseSizeBeforeUpdate);
        ProNhaMay testProNhaMay = proNhaMayList.get(proNhaMayList.size() - 1);
        assertThat(testProNhaMay.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProNhaMay.getStt()).isEqualTo(UPDATED_STT);
    }

    @Test
    @Transactional
    public void updateNonExistingProNhaMay() throws Exception {
        int databaseSizeBeforeUpdate = proNhaMayRepository.findAll().size();

        // Create the ProNhaMay
        ProNhaMayDTO proNhaMayDTO = proNhaMayMapper.toDto(proNhaMay);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNhaMayMockMvc.perform(put("/api/pro-nha-mays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhaMayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNhaMay in the database
        List<ProNhaMay> proNhaMayList = proNhaMayRepository.findAll();
        assertThat(proNhaMayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProNhaMay() throws Exception {
        // Initialize the database
        proNhaMayRepository.saveAndFlush(proNhaMay);

        int databaseSizeBeforeDelete = proNhaMayRepository.findAll().size();

        // Delete the proNhaMay
        restProNhaMayMockMvc.perform(delete("/api/pro-nha-mays/{id}", proNhaMay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProNhaMay> proNhaMayList = proNhaMayRepository.findAll();
        assertThat(proNhaMayList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
