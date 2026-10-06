package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProLoaiChuyen;
import vn.nvsoft.repository.ProLoaiChuyenRepository;
import vn.nvsoft.service.ProLoaiChuyenService;
import vn.nvsoft.service.dto.ProLoaiChuyenDTO;
import vn.nvsoft.service.mapper.ProLoaiChuyenMapper;
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
 * Integration tests for the {@link ProLoaiChuyenResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProLoaiChuyenResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private ProLoaiChuyenRepository proLoaiChuyenRepository;

    @Autowired
    private ProLoaiChuyenMapper proLoaiChuyenMapper;

    @Autowired
    private ProLoaiChuyenService proLoaiChuyenService;

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

    private MockMvc restProLoaiChuyenMockMvc;

    private ProLoaiChuyen proLoaiChuyen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProLoaiChuyenResource proLoaiChuyenResource = new ProLoaiChuyenResource(proLoaiChuyenService);
        this.restProLoaiChuyenMockMvc = MockMvcBuilders.standaloneSetup(proLoaiChuyenResource)
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
    public static ProLoaiChuyen createEntity(EntityManager em) {
        ProLoaiChuyen proLoaiChuyen = new ProLoaiChuyen()
            .ten(DEFAULT_TEN);
        return proLoaiChuyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProLoaiChuyen createUpdatedEntity(EntityManager em) {
        ProLoaiChuyen proLoaiChuyen = new ProLoaiChuyen()
            .ten(UPDATED_TEN);
        return proLoaiChuyen;
    }

    @BeforeEach
    public void initTest() {
        proLoaiChuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createProLoaiChuyen() throws Exception {
        int databaseSizeBeforeCreate = proLoaiChuyenRepository.findAll().size();

        // Create the ProLoaiChuyen
        ProLoaiChuyenDTO proLoaiChuyenDTO = proLoaiChuyenMapper.toDto(proLoaiChuyen);
        restProLoaiChuyenMockMvc.perform(post("/api/pro-loai-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoaiChuyenDTO)))
            .andExpect(status().isCreated());

        // Validate the ProLoaiChuyen in the database
        List<ProLoaiChuyen> proLoaiChuyenList = proLoaiChuyenRepository.findAll();
        assertThat(proLoaiChuyenList).hasSize(databaseSizeBeforeCreate + 1);
        ProLoaiChuyen testProLoaiChuyen = proLoaiChuyenList.get(proLoaiChuyenList.size() - 1);
        assertThat(testProLoaiChuyen.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createProLoaiChuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proLoaiChuyenRepository.findAll().size();

        // Create the ProLoaiChuyen with an existing ID
        proLoaiChuyen.setId(1L);
        ProLoaiChuyenDTO proLoaiChuyenDTO = proLoaiChuyenMapper.toDto(proLoaiChuyen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProLoaiChuyenMockMvc.perform(post("/api/pro-loai-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoaiChuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProLoaiChuyen in the database
        List<ProLoaiChuyen> proLoaiChuyenList = proLoaiChuyenRepository.findAll();
        assertThat(proLoaiChuyenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProLoaiChuyens() throws Exception {
        // Initialize the database
        proLoaiChuyenRepository.saveAndFlush(proLoaiChuyen);

        // Get all the proLoaiChuyenList
        restProLoaiChuyenMockMvc.perform(get("/api/pro-loai-chuyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proLoaiChuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getProLoaiChuyen() throws Exception {
        // Initialize the database
        proLoaiChuyenRepository.saveAndFlush(proLoaiChuyen);

        // Get the proLoaiChuyen
        restProLoaiChuyenMockMvc.perform(get("/api/pro-loai-chuyens/{id}", proLoaiChuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proLoaiChuyen.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }

    @Test
    @Transactional
    public void getNonExistingProLoaiChuyen() throws Exception {
        // Get the proLoaiChuyen
        restProLoaiChuyenMockMvc.perform(get("/api/pro-loai-chuyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProLoaiChuyen() throws Exception {
        // Initialize the database
        proLoaiChuyenRepository.saveAndFlush(proLoaiChuyen);

        int databaseSizeBeforeUpdate = proLoaiChuyenRepository.findAll().size();

        // Update the proLoaiChuyen
        ProLoaiChuyen updatedProLoaiChuyen = proLoaiChuyenRepository.findById(proLoaiChuyen.getId()).get();
        // Disconnect from session so that the updates on updatedProLoaiChuyen are not directly saved in db
        em.detach(updatedProLoaiChuyen);
        updatedProLoaiChuyen
            .ten(UPDATED_TEN);
        ProLoaiChuyenDTO proLoaiChuyenDTO = proLoaiChuyenMapper.toDto(updatedProLoaiChuyen);

        restProLoaiChuyenMockMvc.perform(put("/api/pro-loai-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoaiChuyenDTO)))
            .andExpect(status().isOk());

        // Validate the ProLoaiChuyen in the database
        List<ProLoaiChuyen> proLoaiChuyenList = proLoaiChuyenRepository.findAll();
        assertThat(proLoaiChuyenList).hasSize(databaseSizeBeforeUpdate);
        ProLoaiChuyen testProLoaiChuyen = proLoaiChuyenList.get(proLoaiChuyenList.size() - 1);
        assertThat(testProLoaiChuyen.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingProLoaiChuyen() throws Exception {
        int databaseSizeBeforeUpdate = proLoaiChuyenRepository.findAll().size();

        // Create the ProLoaiChuyen
        ProLoaiChuyenDTO proLoaiChuyenDTO = proLoaiChuyenMapper.toDto(proLoaiChuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProLoaiChuyenMockMvc.perform(put("/api/pro-loai-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoaiChuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProLoaiChuyen in the database
        List<ProLoaiChuyen> proLoaiChuyenList = proLoaiChuyenRepository.findAll();
        assertThat(proLoaiChuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProLoaiChuyen() throws Exception {
        // Initialize the database
        proLoaiChuyenRepository.saveAndFlush(proLoaiChuyen);

        int databaseSizeBeforeDelete = proLoaiChuyenRepository.findAll().size();

        // Delete the proLoaiChuyen
        restProLoaiChuyenMockMvc.perform(delete("/api/pro-loai-chuyens/{id}", proLoaiChuyen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProLoaiChuyen> proLoaiChuyenList = proLoaiChuyenRepository.findAll();
        assertThat(proLoaiChuyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
