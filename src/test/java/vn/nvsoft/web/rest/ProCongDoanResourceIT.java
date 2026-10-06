package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProCongDoan;
import vn.nvsoft.repository.ProCongDoanRepository;
import vn.nvsoft.service.ProCongDoanService;
import vn.nvsoft.service.dto.ProCongDoanDTO;
import vn.nvsoft.service.mapper.ProCongDoanMapper;
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
 * Integration tests for the {@link ProCongDoanResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProCongDoanResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_NHOM_CONG_DOAN = "AAAAAAAAAA";
    private static final String UPDATED_NHOM_CONG_DOAN = "BBBBBBBBBB";

    @Autowired
    private ProCongDoanRepository proCongDoanRepository;

    @Autowired
    private ProCongDoanMapper proCongDoanMapper;

    @Autowired
    private ProCongDoanService proCongDoanService;

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

    private MockMvc restProCongDoanMockMvc;

    private ProCongDoan proCongDoan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProCongDoanResource proCongDoanResource = new ProCongDoanResource(proCongDoanService);
        this.restProCongDoanMockMvc = MockMvcBuilders.standaloneSetup(proCongDoanResource)
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
    public static ProCongDoan createEntity(EntityManager em) {
        ProCongDoan proCongDoan = new ProCongDoan()
            .ten(DEFAULT_TEN)
            .nhomCongDoan(DEFAULT_NHOM_CONG_DOAN);
        return proCongDoan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProCongDoan createUpdatedEntity(EntityManager em) {
        ProCongDoan proCongDoan = new ProCongDoan()
            .ten(UPDATED_TEN)
            .nhomCongDoan(UPDATED_NHOM_CONG_DOAN);
        return proCongDoan;
    }

    @BeforeEach
    public void initTest() {
        proCongDoan = createEntity(em);
    }

    @Test
    @Transactional
    public void createProCongDoan() throws Exception {
        int databaseSizeBeforeCreate = proCongDoanRepository.findAll().size();

        // Create the ProCongDoan
        ProCongDoanDTO proCongDoanDTO = proCongDoanMapper.toDto(proCongDoan);
        restProCongDoanMockMvc.perform(post("/api/pro-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongDoanDTO)))
            .andExpect(status().isCreated());

        // Validate the ProCongDoan in the database
        List<ProCongDoan> proCongDoanList = proCongDoanRepository.findAll();
        assertThat(proCongDoanList).hasSize(databaseSizeBeforeCreate + 1);
        ProCongDoan testProCongDoan = proCongDoanList.get(proCongDoanList.size() - 1);
        assertThat(testProCongDoan.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProCongDoan.getNhomCongDoan()).isEqualTo(DEFAULT_NHOM_CONG_DOAN);
    }

    @Test
    @Transactional
    public void createProCongDoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proCongDoanRepository.findAll().size();

        // Create the ProCongDoan with an existing ID
        proCongDoan.setId(1L);
        ProCongDoanDTO proCongDoanDTO = proCongDoanMapper.toDto(proCongDoan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProCongDoanMockMvc.perform(post("/api/pro-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongDoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCongDoan in the database
        List<ProCongDoan> proCongDoanList = proCongDoanRepository.findAll();
        assertThat(proCongDoanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProCongDoans() throws Exception {
        // Initialize the database
        proCongDoanRepository.saveAndFlush(proCongDoan);

        // Get all the proCongDoanList
        restProCongDoanMockMvc.perform(get("/api/pro-cong-doans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proCongDoan.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].nhomCongDoan").value(hasItem(DEFAULT_NHOM_CONG_DOAN)));
    }
    
    @Test
    @Transactional
    public void getProCongDoan() throws Exception {
        // Initialize the database
        proCongDoanRepository.saveAndFlush(proCongDoan);

        // Get the proCongDoan
        restProCongDoanMockMvc.perform(get("/api/pro-cong-doans/{id}", proCongDoan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proCongDoan.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.nhomCongDoan").value(DEFAULT_NHOM_CONG_DOAN));
    }

    @Test
    @Transactional
    public void getNonExistingProCongDoan() throws Exception {
        // Get the proCongDoan
        restProCongDoanMockMvc.perform(get("/api/pro-cong-doans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProCongDoan() throws Exception {
        // Initialize the database
        proCongDoanRepository.saveAndFlush(proCongDoan);

        int databaseSizeBeforeUpdate = proCongDoanRepository.findAll().size();

        // Update the proCongDoan
        ProCongDoan updatedProCongDoan = proCongDoanRepository.findById(proCongDoan.getId()).get();
        // Disconnect from session so that the updates on updatedProCongDoan are not directly saved in db
        em.detach(updatedProCongDoan);
        updatedProCongDoan
            .ten(UPDATED_TEN)
            .nhomCongDoan(UPDATED_NHOM_CONG_DOAN);
        ProCongDoanDTO proCongDoanDTO = proCongDoanMapper.toDto(updatedProCongDoan);

        restProCongDoanMockMvc.perform(put("/api/pro-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongDoanDTO)))
            .andExpect(status().isOk());

        // Validate the ProCongDoan in the database
        List<ProCongDoan> proCongDoanList = proCongDoanRepository.findAll();
        assertThat(proCongDoanList).hasSize(databaseSizeBeforeUpdate);
        ProCongDoan testProCongDoan = proCongDoanList.get(proCongDoanList.size() - 1);
        assertThat(testProCongDoan.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProCongDoan.getNhomCongDoan()).isEqualTo(UPDATED_NHOM_CONG_DOAN);
    }

    @Test
    @Transactional
    public void updateNonExistingProCongDoan() throws Exception {
        int databaseSizeBeforeUpdate = proCongDoanRepository.findAll().size();

        // Create the ProCongDoan
        ProCongDoanDTO proCongDoanDTO = proCongDoanMapper.toDto(proCongDoan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProCongDoanMockMvc.perform(put("/api/pro-cong-doans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongDoanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCongDoan in the database
        List<ProCongDoan> proCongDoanList = proCongDoanRepository.findAll();
        assertThat(proCongDoanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProCongDoan() throws Exception {
        // Initialize the database
        proCongDoanRepository.saveAndFlush(proCongDoan);

        int databaseSizeBeforeDelete = proCongDoanRepository.findAll().size();

        // Delete the proCongDoan
        restProCongDoanMockMvc.perform(delete("/api/pro-cong-doans/{id}", proCongDoan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProCongDoan> proCongDoanList = proCongDoanRepository.findAll();
        assertThat(proCongDoanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
