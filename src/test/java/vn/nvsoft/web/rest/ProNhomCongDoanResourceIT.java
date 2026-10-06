package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProNhomCongDoan;
import vn.nvsoft.repository.ProNhomCongDoanRepository;
import vn.nvsoft.service.ProNhomCongDoanService;
import vn.nvsoft.service.dto.ProNhomCongDoanDTO;
import vn.nvsoft.service.mapper.ProNhomCongDoanMapper;
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
 * Integration tests for the {@link ProNhomCongDoanResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProNhomCongDoanResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CHECK_NANG_SUAT = false;
    private static final Boolean UPDATED_CHECK_NANG_SUAT = true;

    @Autowired
    private ProNhomCongDoanRepository proNhomCongDoanRepository;

    @Autowired
    private ProNhomCongDoanMapper proNhomCongDoanMapper;

    @Autowired
    private ProNhomCongDoanService proNhomCongDoanService;

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

    private MockMvc restProNhomCongDoanMockMvc;

    private ProNhomCongDoan proNhomCongDoan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProNhomCongDoanResource proNhomCongDoanResource = new ProNhomCongDoanResource(proNhomCongDoanService);
        this.restProNhomCongDoanMockMvc = MockMvcBuilders.standaloneSetup(proNhomCongDoanResource)
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
    public static ProNhomCongDoan createEntity(EntityManager em) {
        ProNhomCongDoan proNhomCongDoan = new ProNhomCongDoan()
            .ten(DEFAULT_TEN)
            .checkNangSuat(DEFAULT_CHECK_NANG_SUAT);
        return proNhomCongDoan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNhomCongDoan createUpdatedEntity(EntityManager em) {
        ProNhomCongDoan proNhomCongDoan = new ProNhomCongDoan()
            .ten(UPDATED_TEN)
            .checkNangSuat(UPDATED_CHECK_NANG_SUAT);
        return proNhomCongDoan;
    }

    @BeforeEach
    public void initTest() {
        proNhomCongDoan = createEntity(em);
    }

    @Test
    @Transactional
    public void createProNhomCongDoan() throws Exception {
        int databaseSizeBeforeCreate = proNhomCongDoanRepository.findAll().size();

        // Create the ProNhomCongDoan
        ProNhomCongDoanDTO proNhomCongDoanDTO = proNhomCongDoanMapper.toDto(proNhomCongDoan);
        restProNhomCongDoanMockMvc.perform(post("/api/pro-nhom-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhomCongDoanDTO)))
            .andExpect(status().isCreated());

        // Validate the ProNhomCongDoan in the database
        List<ProNhomCongDoan> proNhomCongDoanList = proNhomCongDoanRepository.findAll();
        assertThat(proNhomCongDoanList).hasSize(databaseSizeBeforeCreate + 1);
        ProNhomCongDoan testProNhomCongDoan = proNhomCongDoanList.get(proNhomCongDoanList.size() - 1);
        assertThat(testProNhomCongDoan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProNhomCongDoan.isCheckNangSuat()).isEqualTo(DEFAULT_CHECK_NANG_SUAT);
    }

    @Test
    @Transactional
    public void createProNhomCongDoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proNhomCongDoanRepository.findAll().size();

        // Create the ProNhomCongDoan with an existing ID
        proNhomCongDoan.setId(1L);
        ProNhomCongDoanDTO proNhomCongDoanDTO = proNhomCongDoanMapper.toDto(proNhomCongDoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProNhomCongDoanMockMvc.perform(post("/api/pro-nhom-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhomCongDoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNhomCongDoan in the database
        List<ProNhomCongDoan> proNhomCongDoanList = proNhomCongDoanRepository.findAll();
        assertThat(proNhomCongDoanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProNhomCongDoans() throws Exception {
        // Initialize the database
        proNhomCongDoanRepository.saveAndFlush(proNhomCongDoan);

        // Get all the proNhomCongDoanList
        restProNhomCongDoanMockMvc.perform(get("/api/pro-nhom-cong-doans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proNhomCongDoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].checkNangSuat").value(hasItem(DEFAULT_CHECK_NANG_SUAT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProNhomCongDoan() throws Exception {
        // Initialize the database
        proNhomCongDoanRepository.saveAndFlush(proNhomCongDoan);

        // Get the proNhomCongDoan
        restProNhomCongDoanMockMvc.perform(get("/api/pro-nhom-cong-doans/{id}", proNhomCongDoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proNhomCongDoan.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.checkNangSuat").value(DEFAULT_CHECK_NANG_SUAT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProNhomCongDoan() throws Exception {
        // Get the proNhomCongDoan
        restProNhomCongDoanMockMvc.perform(get("/api/pro-nhom-cong-doans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProNhomCongDoan() throws Exception {
        // Initialize the database
        proNhomCongDoanRepository.saveAndFlush(proNhomCongDoan);

        int databaseSizeBeforeUpdate = proNhomCongDoanRepository.findAll().size();

        // Update the proNhomCongDoan
        ProNhomCongDoan updatedProNhomCongDoan = proNhomCongDoanRepository.findById(proNhomCongDoan.getId()).get();
        // Disconnect from session so that the updates on updatedProNhomCongDoan are not directly saved in db
        em.detach(updatedProNhomCongDoan);
        updatedProNhomCongDoan
            .ten(UPDATED_TEN)
            .checkNangSuat(UPDATED_CHECK_NANG_SUAT);
        ProNhomCongDoanDTO proNhomCongDoanDTO = proNhomCongDoanMapper.toDto(updatedProNhomCongDoan);

        restProNhomCongDoanMockMvc.perform(put("/api/pro-nhom-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhomCongDoanDTO)))
            .andExpect(status().isOk());

        // Validate the ProNhomCongDoan in the database
        List<ProNhomCongDoan> proNhomCongDoanList = proNhomCongDoanRepository.findAll();
        assertThat(proNhomCongDoanList).hasSize(databaseSizeBeforeUpdate);
        ProNhomCongDoan testProNhomCongDoan = proNhomCongDoanList.get(proNhomCongDoanList.size() - 1);
        assertThat(testProNhomCongDoan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProNhomCongDoan.isCheckNangSuat()).isEqualTo(UPDATED_CHECK_NANG_SUAT);
    }

    @Test
    @Transactional
    public void updateNonExistingProNhomCongDoan() throws Exception {
        int databaseSizeBeforeUpdate = proNhomCongDoanRepository.findAll().size();

        // Create the ProNhomCongDoan
        ProNhomCongDoanDTO proNhomCongDoanDTO = proNhomCongDoanMapper.toDto(proNhomCongDoan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNhomCongDoanMockMvc.perform(put("/api/pro-nhom-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNhomCongDoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNhomCongDoan in the database
        List<ProNhomCongDoan> proNhomCongDoanList = proNhomCongDoanRepository.findAll();
        assertThat(proNhomCongDoanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProNhomCongDoan() throws Exception {
        // Initialize the database
        proNhomCongDoanRepository.saveAndFlush(proNhomCongDoan);

        int databaseSizeBeforeDelete = proNhomCongDoanRepository.findAll().size();

        // Delete the proNhomCongDoan
        restProNhomCongDoanMockMvc.perform(delete("/api/pro-nhom-cong-doans/{id}", proNhomCongDoan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProNhomCongDoan> proNhomCongDoanList = proNhomCongDoanRepository.findAll();
        assertThat(proNhomCongDoanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
