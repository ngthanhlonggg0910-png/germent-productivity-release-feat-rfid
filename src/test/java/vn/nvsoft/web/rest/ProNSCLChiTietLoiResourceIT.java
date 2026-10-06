package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProNSCLChiTietLoi;
import vn.nvsoft.repository.ProNSCLChiTietLoiRepository;
import vn.nvsoft.service.ProNSCLChiTietLoiService;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProNSCLChiTietLoiMapper;
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
 * Integration tests for the {@link ProNSCLChiTietLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProNSCLChiTietLoiResourceIT {

    private static final String DEFAULT_MA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOI = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final GioLam DEFAULT_GIO_LAM = GioLam.C_1H;
    private static final GioLam UPDATED_GIO_LAM = GioLam.C_2H;

    private static final Double DEFAULT_DX = 1D;
    private static final Double UPDATED_DX = 2D;

    private static final Double DEFAULT_DY = 1D;
    private static final Double UPDATED_DY = 2D;

    private static final Boolean DEFAULT_IS_FRONT = false;
    private static final Boolean UPDATED_IS_FRONT = true;

    private static final Boolean DEFAULT_IS_INSIDE = false;
    private static final Boolean UPDATED_IS_INSIDE = true;

    @Autowired
    private ProNSCLChiTietLoiRepository proNSCLChiTietLoiRepository;

    @Autowired
    private ProNSCLChiTietLoiMapper proNSCLChiTietLoiMapper;

    @Autowired
    private ProNSCLChiTietLoiService proNSCLChiTietLoiService;

    @Autowired
    private ProNSCLService proNSCLService;

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

    private MockMvc restProNSCLChiTietLoiMockMvc;

    private ProNSCLChiTietLoi proNSCLChiTietLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProNSCLChiTietLoiResource proNSCLChiTietLoiResource = new ProNSCLChiTietLoiResource(proNSCLChiTietLoiService, proNSCLService);
        this.restProNSCLChiTietLoiMockMvc = MockMvcBuilders.standaloneSetup(proNSCLChiTietLoiResource)
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
    public static ProNSCLChiTietLoi createEntity(EntityManager em) {
        ProNSCLChiTietLoi proNSCLChiTietLoi = new ProNSCLChiTietLoi()
            .maLoi(DEFAULT_MA_LOI)
            .thoiGian(DEFAULT_THOI_GIAN)
            .gioLam(DEFAULT_GIO_LAM)
            .dx(DEFAULT_DX)
            .dy(DEFAULT_DY)
            .isFront(DEFAULT_IS_FRONT)
            .isInside(DEFAULT_IS_INSIDE);
        return proNSCLChiTietLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNSCLChiTietLoi createUpdatedEntity(EntityManager em) {
        ProNSCLChiTietLoi proNSCLChiTietLoi = new ProNSCLChiTietLoi()
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .gioLam(UPDATED_GIO_LAM)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE);
        return proNSCLChiTietLoi;
    }

    @BeforeEach
    public void initTest() {
        proNSCLChiTietLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProNSCLChiTietLoi() throws Exception {
        int databaseSizeBeforeCreate = proNSCLChiTietLoiRepository.findAll().size();

        // Create the ProNSCLChiTietLoi
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);
        restProNSCLChiTietLoiMockMvc.perform(post("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProNSCLChiTietLoi in the database
        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProNSCLChiTietLoi testProNSCLChiTietLoi = proNSCLChiTietLoiList.get(proNSCLChiTietLoiList.size() - 1);
        assertThat(testProNSCLChiTietLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProNSCLChiTietLoi.getThoiGian()).isEqualTo(DEFAULT_THOI_GIAN);
        assertThat(testProNSCLChiTietLoi.getGioLam()).isEqualTo(DEFAULT_GIO_LAM);
        assertThat(testProNSCLChiTietLoi.getDx()).isEqualTo(DEFAULT_DX);
        assertThat(testProNSCLChiTietLoi.getDy()).isEqualTo(DEFAULT_DY);
        assertThat(testProNSCLChiTietLoi.isIsFront()).isEqualTo(DEFAULT_IS_FRONT);
        assertThat(testProNSCLChiTietLoi.isIsInside()).isEqualTo(DEFAULT_IS_INSIDE);
    }

    @Test
    @Transactional
    public void createProNSCLChiTietLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proNSCLChiTietLoiRepository.findAll().size();

        // Create the ProNSCLChiTietLoi with an existing ID
        proNSCLChiTietLoi.setId(1L);
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProNSCLChiTietLoiMockMvc.perform(post("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNSCLChiTietLoi in the database
        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaLoiIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLChiTietLoiRepository.findAll().size();
        // set the field null
        proNSCLChiTietLoi.setMaLoi(null);

        // Create the ProNSCLChiTietLoi, which fails.
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);

        restProNSCLChiTietLoiMockMvc.perform(post("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkThoiGianIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLChiTietLoiRepository.findAll().size();
        // set the field null
        proNSCLChiTietLoi.setThoiGian(null);

        // Create the ProNSCLChiTietLoi, which fails.
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);

        restProNSCLChiTietLoiMockMvc.perform(post("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProNSCLChiTietLois() throws Exception {
        // Initialize the database
        proNSCLChiTietLoiRepository.saveAndFlush(proNSCLChiTietLoi);

        // Get all the proNSCLChiTietLoiList
        restProNSCLChiTietLoiMockMvc.perform(get("/api/pro-nscl-chi-tiet-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proNSCLChiTietLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].thoiGian").value(hasItem(DEFAULT_THOI_GIAN.toString())))
            .andExpect(jsonPath("$.[*].gioLam").value(hasItem(DEFAULT_GIO_LAM.toString())))
            .andExpect(jsonPath("$.[*].dx").value(hasItem(DEFAULT_DX.doubleValue())))
            .andExpect(jsonPath("$.[*].dy").value(hasItem(DEFAULT_DY.doubleValue())))
            .andExpect(jsonPath("$.[*].isFront").value(hasItem(DEFAULT_IS_FRONT.booleanValue())))
            .andExpect(jsonPath("$.[*].isInside").value(hasItem(DEFAULT_IS_INSIDE.booleanValue())));
    }

    @Test
    @Transactional
    public void getProNSCLChiTietLoi() throws Exception {
        // Initialize the database
        proNSCLChiTietLoiRepository.saveAndFlush(proNSCLChiTietLoi);

        // Get the proNSCLChiTietLoi
        restProNSCLChiTietLoiMockMvc.perform(get("/api/pro-nscl-chi-tiet-lois/{id}", proNSCLChiTietLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proNSCLChiTietLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.thoiGian").value(DEFAULT_THOI_GIAN.toString()))
            .andExpect(jsonPath("$.gioLam").value(DEFAULT_GIO_LAM.toString()))
            .andExpect(jsonPath("$.dx").value(DEFAULT_DX.doubleValue()))
            .andExpect(jsonPath("$.dy").value(DEFAULT_DY.doubleValue()))
            .andExpect(jsonPath("$.isFront").value(DEFAULT_IS_FRONT.booleanValue()))
            .andExpect(jsonPath("$.isInside").value(DEFAULT_IS_INSIDE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProNSCLChiTietLoi() throws Exception {
        // Get the proNSCLChiTietLoi
        restProNSCLChiTietLoiMockMvc.perform(get("/api/pro-nscl-chi-tiet-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProNSCLChiTietLoi() throws Exception {
        // Initialize the database
        proNSCLChiTietLoiRepository.saveAndFlush(proNSCLChiTietLoi);

        int databaseSizeBeforeUpdate = proNSCLChiTietLoiRepository.findAll().size();

        // Update the proNSCLChiTietLoi
        ProNSCLChiTietLoi updatedProNSCLChiTietLoi = proNSCLChiTietLoiRepository.findById(proNSCLChiTietLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProNSCLChiTietLoi are not directly saved in db
        em.detach(updatedProNSCLChiTietLoi);
        updatedProNSCLChiTietLoi
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .gioLam(UPDATED_GIO_LAM)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE);
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(updatedProNSCLChiTietLoi);

        restProNSCLChiTietLoiMockMvc.perform(put("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProNSCLChiTietLoi in the database
        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
        ProNSCLChiTietLoi testProNSCLChiTietLoi = proNSCLChiTietLoiList.get(proNSCLChiTietLoiList.size() - 1);
        assertThat(testProNSCLChiTietLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProNSCLChiTietLoi.getThoiGian()).isEqualTo(UPDATED_THOI_GIAN);
        assertThat(testProNSCLChiTietLoi.getGioLam()).isEqualTo(UPDATED_GIO_LAM);
        assertThat(testProNSCLChiTietLoi.getDx()).isEqualTo(UPDATED_DX);
        assertThat(testProNSCLChiTietLoi.getDy()).isEqualTo(UPDATED_DY);
        assertThat(testProNSCLChiTietLoi.isIsFront()).isEqualTo(UPDATED_IS_FRONT);
        assertThat(testProNSCLChiTietLoi.isIsInside()).isEqualTo(UPDATED_IS_INSIDE);
    }

    @Test
    @Transactional
    public void updateNonExistingProNSCLChiTietLoi() throws Exception {
        int databaseSizeBeforeUpdate = proNSCLChiTietLoiRepository.findAll().size();

        // Create the ProNSCLChiTietLoi
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNSCLChiTietLoiMockMvc.perform(put("/api/pro-nscl-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNSCLChiTietLoi in the database
        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProNSCLChiTietLoi() throws Exception {
        // Initialize the database
        proNSCLChiTietLoiRepository.saveAndFlush(proNSCLChiTietLoi);

        int databaseSizeBeforeDelete = proNSCLChiTietLoiRepository.findAll().size();

        // Delete the proNSCLChiTietLoi
        restProNSCLChiTietLoiMockMvc.perform(delete("/api/pro-nscl-chi-tiet-lois/{id}", proNSCLChiTietLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProNSCLChiTietLoi> proNSCLChiTietLoiList = proNSCLChiTietLoiRepository.findAll();
        assertThat(proNSCLChiTietLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
