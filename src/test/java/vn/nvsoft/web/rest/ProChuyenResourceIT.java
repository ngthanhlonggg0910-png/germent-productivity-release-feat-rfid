package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProChuyen;
import vn.nvsoft.repository.ProChuyenRepository;
import vn.nvsoft.service.ProChuyenService;
import vn.nvsoft.service.dto.ProChuyenDTO;
import vn.nvsoft.service.mapper.ProChuyenMapper;
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
 * Integration tests for the {@link ProChuyenResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProChuyenResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;

    private static final String DEFAULT_TEN_LOAI_CHUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI_CHUYEN = "BBBBBBBBBB";

    @Autowired
    private ProChuyenRepository proChuyenRepository;

    @Autowired
    private ProChuyenMapper proChuyenMapper;

    @Autowired
    private ProChuyenService proChuyenService;

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

    private MockMvc restProChuyenMockMvc;

    private ProChuyen proChuyen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProChuyenResource proChuyenResource = new ProChuyenResource(proChuyenService);
        this.restProChuyenMockMvc = MockMvcBuilders.standaloneSetup(proChuyenResource)
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
    public static ProChuyen createEntity(EntityManager em) {
        ProChuyen proChuyen = new ProChuyen()
            .ten(DEFAULT_TEN)
            .stt(DEFAULT_STT)
            .tenLoaiChuyen(DEFAULT_TEN_LOAI_CHUYEN);
        return proChuyen;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProChuyen createUpdatedEntity(EntityManager em) {
        ProChuyen proChuyen = new ProChuyen()
            .ten(UPDATED_TEN)
            .stt(UPDATED_STT)
            .tenLoaiChuyen(UPDATED_TEN_LOAI_CHUYEN);
        return proChuyen;
    }

    @BeforeEach
    public void initTest() {
        proChuyen = createEntity(em);
    }

    @Test
    @Transactional
    public void createProChuyen() throws Exception {
        int databaseSizeBeforeCreate = proChuyenRepository.findAll().size();

        // Create the ProChuyen
        ProChuyenDTO proChuyenDTO = proChuyenMapper.toDto(proChuyen);
        restProChuyenMockMvc.perform(post("/api/pro-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChuyenDTO)))
            .andExpect(status().isCreated());

        // Validate the ProChuyen in the database
        List<ProChuyen> proChuyenList = proChuyenRepository.findAll();
        assertThat(proChuyenList).hasSize(databaseSizeBeforeCreate + 1);
        ProChuyen testProChuyen = proChuyenList.get(proChuyenList.size() - 1);
        assertThat(testProChuyen.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProChuyen.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testProChuyen.getTenLoaiChuyen()).isEqualTo(DEFAULT_TEN_LOAI_CHUYEN);
    }

    @Test
    @Transactional
    public void createProChuyenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proChuyenRepository.findAll().size();

        // Create the ProChuyen with an existing ID
        proChuyen.setId(1L);
        ProChuyenDTO proChuyenDTO = proChuyenMapper.toDto(proChuyen);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProChuyenMockMvc.perform(post("/api/pro-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProChuyen in the database
        List<ProChuyen> proChuyenList = proChuyenRepository.findAll();
        assertThat(proChuyenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProChuyens() throws Exception {
        // Initialize the database
        proChuyenRepository.saveAndFlush(proChuyen);

        // Get all the proChuyenList
        restProChuyenMockMvc.perform(get("/api/pro-chuyens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proChuyen.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].tenLoaiChuyen").value(hasItem(DEFAULT_TEN_LOAI_CHUYEN)));
    }
    
    @Test
    @Transactional
    public void getProChuyen() throws Exception {
        // Initialize the database
        proChuyenRepository.saveAndFlush(proChuyen);

        // Get the proChuyen
        restProChuyenMockMvc.perform(get("/api/pro-chuyens/{id}", proChuyen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proChuyen.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.tenLoaiChuyen").value(DEFAULT_TEN_LOAI_CHUYEN));
    }

    @Test
    @Transactional
    public void getNonExistingProChuyen() throws Exception {
        // Get the proChuyen
        restProChuyenMockMvc.perform(get("/api/pro-chuyens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProChuyen() throws Exception {
        // Initialize the database
        proChuyenRepository.saveAndFlush(proChuyen);

        int databaseSizeBeforeUpdate = proChuyenRepository.findAll().size();

        // Update the proChuyen
        ProChuyen updatedProChuyen = proChuyenRepository.findById(proChuyen.getId()).get();
        // Disconnect from session so that the updates on updatedProChuyen are not directly saved in db
        em.detach(updatedProChuyen);
        updatedProChuyen
            .ten(UPDATED_TEN)
            .stt(UPDATED_STT)
            .tenLoaiChuyen(UPDATED_TEN_LOAI_CHUYEN);
        ProChuyenDTO proChuyenDTO = proChuyenMapper.toDto(updatedProChuyen);

        restProChuyenMockMvc.perform(put("/api/pro-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChuyenDTO)))
            .andExpect(status().isOk());

        // Validate the ProChuyen in the database
        List<ProChuyen> proChuyenList = proChuyenRepository.findAll();
        assertThat(proChuyenList).hasSize(databaseSizeBeforeUpdate);
        ProChuyen testProChuyen = proChuyenList.get(proChuyenList.size() - 1);
        assertThat(testProChuyen.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProChuyen.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testProChuyen.getTenLoaiChuyen()).isEqualTo(UPDATED_TEN_LOAI_CHUYEN);
    }

    @Test
    @Transactional
    public void updateNonExistingProChuyen() throws Exception {
        int databaseSizeBeforeUpdate = proChuyenRepository.findAll().size();

        // Create the ProChuyen
        ProChuyenDTO proChuyenDTO = proChuyenMapper.toDto(proChuyen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProChuyenMockMvc.perform(put("/api/pro-chuyens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChuyenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProChuyen in the database
        List<ProChuyen> proChuyenList = proChuyenRepository.findAll();
        assertThat(proChuyenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProChuyen() throws Exception {
        // Initialize the database
        proChuyenRepository.saveAndFlush(proChuyen);

        int databaseSizeBeforeDelete = proChuyenRepository.findAll().size();

        // Delete the proChuyen
        restProChuyenMockMvc.perform(delete("/api/pro-chuyens/{id}", proChuyen.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProChuyen> proChuyenList = proChuyenRepository.findAll();
        assertThat(proChuyenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
