package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProGioLamViec;
import vn.nvsoft.repository.ProGioLamViecRepository;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.service.dto.ProGioLamViecDTO;
import vn.nvsoft.service.mapper.ProGioLamViecMapper;
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

import vn.nvsoft.domain.enumeration.GioLam;
/**
 * Integration tests for the {@link ProGioLamViecResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProGioLamViecResourceIT {

    private static final Instant DEFAULT_GIO_BAT_DAU = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GIO_BAT_DAU = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_GIO_KET_THUC = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GIO_KET_THUC = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final GioLam DEFAULT_GIO_LAM = GioLam.C_1H;
    private static final GioLam UPDATED_GIO_LAM = GioLam.C_2H;

    private static final Integer DEFAULT_THOI_GIAN = 1;
    private static final Integer UPDATED_THOI_GIAN = 2;

    private static final String DEFAULT_CAPTION_TV = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION_TV = "BBBBBBBBBB";

    @Autowired
    private ProGioLamViecRepository proGioLamViecRepository;

    @Autowired
    private ProGioLamViecMapper proGioLamViecMapper;

    @Autowired
    private ProGioLamViecService proGioLamViecService;

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

    private MockMvc restProGioLamViecMockMvc;

    private ProGioLamViec proGioLamViec;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProGioLamViecResource proGioLamViecResource = new ProGioLamViecResource(proGioLamViecService);
        this.restProGioLamViecMockMvc = MockMvcBuilders.standaloneSetup(proGioLamViecResource)
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
    public static ProGioLamViec createEntity(EntityManager em) {
        ProGioLamViec proGioLamViec = new ProGioLamViec()
            .gioBatDau(DEFAULT_GIO_BAT_DAU)
            .gioKetThuc(DEFAULT_GIO_KET_THUC)
            .gioLam(DEFAULT_GIO_LAM)
            .thoiGian(DEFAULT_THOI_GIAN)
            .captionTV(DEFAULT_CAPTION_TV);
        return proGioLamViec;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProGioLamViec createUpdatedEntity(EntityManager em) {
        ProGioLamViec proGioLamViec = new ProGioLamViec()
            .gioBatDau(UPDATED_GIO_BAT_DAU)
            .gioKetThuc(UPDATED_GIO_KET_THUC)
            .gioLam(UPDATED_GIO_LAM)
            .thoiGian(UPDATED_THOI_GIAN)
            .captionTV(UPDATED_CAPTION_TV);
        return proGioLamViec;
    }

    @BeforeEach
    public void initTest() {
        proGioLamViec = createEntity(em);
    }

    @Test
    @Transactional
    public void createProGioLamViec() throws Exception {
        int databaseSizeBeforeCreate = proGioLamViecRepository.findAll().size();

        // Create the ProGioLamViec
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(proGioLamViec);
        restProGioLamViecMockMvc.perform(post("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isCreated());

        // Validate the ProGioLamViec in the database
        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeCreate + 1);
        ProGioLamViec testProGioLamViec = proGioLamViecList.get(proGioLamViecList.size() - 1);
        assertThat(testProGioLamViec.getGioBatDau()).isEqualTo(DEFAULT_GIO_BAT_DAU);
        assertThat(testProGioLamViec.getGioKetThuc()).isEqualTo(DEFAULT_GIO_KET_THUC);
        assertThat(testProGioLamViec.getGioLam()).isEqualTo(DEFAULT_GIO_LAM);
        assertThat(testProGioLamViec.getThoiGian()).isEqualTo(DEFAULT_THOI_GIAN);
        assertThat(testProGioLamViec.getCaptionTV()).isEqualTo(DEFAULT_CAPTION_TV);
    }

    @Test
    @Transactional
    public void createProGioLamViecWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proGioLamViecRepository.findAll().size();

        // Create the ProGioLamViec with an existing ID
        proGioLamViec.setId(1L);
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(proGioLamViec);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProGioLamViecMockMvc.perform(post("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProGioLamViec in the database
        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGioBatDauIsRequired() throws Exception {
        int databaseSizeBeforeTest = proGioLamViecRepository.findAll().size();
        // set the field null
        proGioLamViec.setGioBatDau(null);

        // Create the ProGioLamViec, which fails.
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(proGioLamViec);

        restProGioLamViecMockMvc.perform(post("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isBadRequest());

        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGioKetThucIsRequired() throws Exception {
        int databaseSizeBeforeTest = proGioLamViecRepository.findAll().size();
        // set the field null
        proGioLamViec.setGioKetThuc(null);

        // Create the ProGioLamViec, which fails.
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(proGioLamViec);

        restProGioLamViecMockMvc.perform(post("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isBadRequest());

        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProGioLamViecs() throws Exception {
        // Initialize the database
        proGioLamViecRepository.saveAndFlush(proGioLamViec);

        // Get all the proGioLamViecList
        restProGioLamViecMockMvc.perform(get("/api/pro-gio-lam-viecs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proGioLamViec.getId().intValue())))
            .andExpect(jsonPath("$.[*].gioBatDau").value(hasItem(DEFAULT_GIO_BAT_DAU.toString())))
            .andExpect(jsonPath("$.[*].gioKetThuc").value(hasItem(DEFAULT_GIO_KET_THUC.toString())))
            .andExpect(jsonPath("$.[*].gioLam").value(hasItem(DEFAULT_GIO_LAM.toString())))
            .andExpect(jsonPath("$.[*].thoiGian").value(hasItem(DEFAULT_THOI_GIAN)))
            .andExpect(jsonPath("$.[*].captionTV").value(hasItem(DEFAULT_CAPTION_TV)));
    }
    
    @Test
    @Transactional
    public void getProGioLamViec() throws Exception {
        // Initialize the database
        proGioLamViecRepository.saveAndFlush(proGioLamViec);

        // Get the proGioLamViec
        restProGioLamViecMockMvc.perform(get("/api/pro-gio-lam-viecs/{id}", proGioLamViec.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proGioLamViec.getId().intValue()))
            .andExpect(jsonPath("$.gioBatDau").value(DEFAULT_GIO_BAT_DAU.toString()))
            .andExpect(jsonPath("$.gioKetThuc").value(DEFAULT_GIO_KET_THUC.toString()))
            .andExpect(jsonPath("$.gioLam").value(DEFAULT_GIO_LAM.toString()))
            .andExpect(jsonPath("$.thoiGian").value(DEFAULT_THOI_GIAN))
            .andExpect(jsonPath("$.captionTV").value(DEFAULT_CAPTION_TV));
    }

    @Test
    @Transactional
    public void getNonExistingProGioLamViec() throws Exception {
        // Get the proGioLamViec
        restProGioLamViecMockMvc.perform(get("/api/pro-gio-lam-viecs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProGioLamViec() throws Exception {
        // Initialize the database
        proGioLamViecRepository.saveAndFlush(proGioLamViec);

        int databaseSizeBeforeUpdate = proGioLamViecRepository.findAll().size();

        // Update the proGioLamViec
        ProGioLamViec updatedProGioLamViec = proGioLamViecRepository.findById(proGioLamViec.getId()).get();
        // Disconnect from session so that the updates on updatedProGioLamViec are not directly saved in db
        em.detach(updatedProGioLamViec);
        updatedProGioLamViec
            .gioBatDau(UPDATED_GIO_BAT_DAU)
            .gioKetThuc(UPDATED_GIO_KET_THUC)
            .gioLam(UPDATED_GIO_LAM)
            .thoiGian(UPDATED_THOI_GIAN)
            .captionTV(UPDATED_CAPTION_TV);
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(updatedProGioLamViec);

        restProGioLamViecMockMvc.perform(put("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isOk());

        // Validate the ProGioLamViec in the database
        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeUpdate);
        ProGioLamViec testProGioLamViec = proGioLamViecList.get(proGioLamViecList.size() - 1);
        assertThat(testProGioLamViec.getGioBatDau()).isEqualTo(UPDATED_GIO_BAT_DAU);
        assertThat(testProGioLamViec.getGioKetThuc()).isEqualTo(UPDATED_GIO_KET_THUC);
        assertThat(testProGioLamViec.getGioLam()).isEqualTo(UPDATED_GIO_LAM);
        assertThat(testProGioLamViec.getThoiGian()).isEqualTo(UPDATED_THOI_GIAN);
        assertThat(testProGioLamViec.getCaptionTV()).isEqualTo(UPDATED_CAPTION_TV);
    }

    @Test
    @Transactional
    public void updateNonExistingProGioLamViec() throws Exception {
        int databaseSizeBeforeUpdate = proGioLamViecRepository.findAll().size();

        // Create the ProGioLamViec
        ProGioLamViecDTO proGioLamViecDTO = proGioLamViecMapper.toDto(proGioLamViec);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProGioLamViecMockMvc.perform(put("/api/pro-gio-lam-viecs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proGioLamViecDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProGioLamViec in the database
        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProGioLamViec() throws Exception {
        // Initialize the database
        proGioLamViecRepository.saveAndFlush(proGioLamViec);

        int databaseSizeBeforeDelete = proGioLamViecRepository.findAll().size();

        // Delete the proGioLamViec
        restProGioLamViecMockMvc.perform(delete("/api/pro-gio-lam-viecs/{id}", proGioLamViec.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProGioLamViec> proGioLamViecList = proGioLamViecRepository.findAll();
        assertThat(proGioLamViecList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
