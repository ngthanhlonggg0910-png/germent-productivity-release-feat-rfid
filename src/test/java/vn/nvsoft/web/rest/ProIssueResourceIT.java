package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProIssue;
import vn.nvsoft.repository.ProIssueRepository;
import vn.nvsoft.service.ProIssueService;
import vn.nvsoft.service.dto.ProIssueDTO;
import vn.nvsoft.service.mapper.ProIssueMapper;
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

/**
 * Integration tests for the {@link ProIssueResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProIssueResourceIT {

    private static final String DEFAULT_MA_HANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_PONO = "AAAAAAAAAA";
    private static final String UPDATED_PONO = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CHUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CHUYEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_GIO_BAT_DAU = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_GIO_BAT_DAU = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_GIO_KET_THUC = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_GIO_KET_THUC = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_MO_TA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MO_TA_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_MA_MAY = "AAAAAAAAAA";
    private static final String UPDATED_MA_MAY = "BBBBBBBBBB";

    private static final String DEFAULT_SO_TRAM = "AAAAAAAAAA";
    private static final String UPDATED_SO_TRAM = "BBBBBBBBBB";

    private static final Long DEFAULT_PRO_CHUYEN_ID = 1L;
    private static final Long UPDATED_PRO_CHUYEN_ID = 2L;

    private static final Long DEFAULT_PRO_SAN_PHAM_ID = 1L;
    private static final Long UPDATED_PRO_SAN_PHAM_ID = 2L;

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_POINT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_POINT_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_CHECK_POINT_ID = 1L;
    private static final Long UPDATED_CHECK_POINT_ID = 2L;

    private static final String DEFAULT_CHECK_POINT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_POINT_NAME = "BBBBBBBBBB";

    @Autowired
    private ProIssueRepository proIssueRepository;

    @Autowired
    private ProIssueMapper proIssueMapper;

    @Autowired
    private ProIssueService proIssueService;

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

    private MockMvc restProIssueMockMvc;

    private ProIssue proIssue;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProIssueResource proIssueResource = new ProIssueResource(proIssueService);
        this.restProIssueMockMvc = MockMvcBuilders.standaloneSetup(proIssueResource)
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
    public static ProIssue createEntity(EntityManager em) {
        ProIssue proIssue = new ProIssue()
            .maHang(DEFAULT_MA_HANG)
            .pono(DEFAULT_PONO)
            .tenChuyen(DEFAULT_TEN_CHUYEN)
            .ngayGioBatDau(DEFAULT_NGAY_GIO_BAT_DAU)
            .ngayGioKetThuc(DEFAULT_NGAY_GIO_KET_THUC)
            .maLoi(DEFAULT_MA_LOI)
            .moTaLoi(DEFAULT_MO_TA_LOI)
            .maMay(DEFAULT_MA_MAY)
            .soTram(DEFAULT_SO_TRAM)
            .proChuyenId(DEFAULT_PRO_CHUYEN_ID)
            .proSanPhamId(DEFAULT_PRO_SAN_PHAM_ID)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .checkPointType(DEFAULT_CHECK_POINT_TYPE)
            .checkPointId(DEFAULT_CHECK_POINT_ID)
            .checkPointName(DEFAULT_CHECK_POINT_NAME);
        return proIssue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProIssue createUpdatedEntity(EntityManager em) {
        ProIssue proIssue = new ProIssue()
            .maHang(UPDATED_MA_HANG)
            .pono(UPDATED_PONO)
            .tenChuyen(UPDATED_TEN_CHUYEN)
            .ngayGioBatDau(UPDATED_NGAY_GIO_BAT_DAU)
            .ngayGioKetThuc(UPDATED_NGAY_GIO_KET_THUC)
            .maLoi(UPDATED_MA_LOI)
            .moTaLoi(UPDATED_MO_TA_LOI)
            .maMay(UPDATED_MA_MAY)
            .soTram(UPDATED_SO_TRAM)
            .proChuyenId(UPDATED_PRO_CHUYEN_ID)
            .proSanPhamId(UPDATED_PRO_SAN_PHAM_ID)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .checkPointType(UPDATED_CHECK_POINT_TYPE)
            .checkPointId(UPDATED_CHECK_POINT_ID)
            .checkPointName(UPDATED_CHECK_POINT_NAME);
        return proIssue;
    }

    @BeforeEach
    public void initTest() {
        proIssue = createEntity(em);
    }

    @Test
    @Transactional
    public void createProIssue() throws Exception {
        int databaseSizeBeforeCreate = proIssueRepository.findAll().size();

        // Create the ProIssue
        ProIssueDTO proIssueDTO = proIssueMapper.toDto(proIssue);
        restProIssueMockMvc.perform(post("/api/pro-issues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueDTO)))
            .andExpect(status().isCreated());

        // Validate the ProIssue in the database
        List<ProIssue> proIssueList = proIssueRepository.findAll();
        assertThat(proIssueList).hasSize(databaseSizeBeforeCreate + 1);
        ProIssue testProIssue = proIssueList.get(proIssueList.size() - 1);
        assertThat(testProIssue.getMaHang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testProIssue.getPono()).isEqualTo(DEFAULT_PONO);
        assertThat(testProIssue.getTenChuyen()).isEqualTo(DEFAULT_TEN_CHUYEN);
        assertThat(testProIssue.getNgayGioBatDau()).isEqualTo(DEFAULT_NGAY_GIO_BAT_DAU);
        assertThat(testProIssue.getNgayGioKetThuc()).isEqualTo(DEFAULT_NGAY_GIO_KET_THUC);
        assertThat(testProIssue.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProIssue.getMoTaLoi()).isEqualTo(DEFAULT_MO_TA_LOI);
        assertThat(testProIssue.getMaMay()).isEqualTo(DEFAULT_MA_MAY);
        assertThat(testProIssue.getSoTram()).isEqualTo(DEFAULT_SO_TRAM);
        assertThat(testProIssue.getProChuyenId()).isEqualTo(DEFAULT_PRO_CHUYEN_ID);
        assertThat(testProIssue.getProSanPhamId()).isEqualTo(DEFAULT_PRO_SAN_PHAM_ID);
        assertThat(testProIssue.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testProIssue.getCheckPointType()).isEqualTo(DEFAULT_CHECK_POINT_TYPE);
        assertThat(testProIssue.getCheckPointId()).isEqualTo(DEFAULT_CHECK_POINT_ID);
        assertThat(testProIssue.getCheckPointName()).isEqualTo(DEFAULT_CHECK_POINT_NAME);
    }

    @Test
    @Transactional
    public void createProIssueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proIssueRepository.findAll().size();

        // Create the ProIssue with an existing ID
        proIssue.setId(1L);
        ProIssueDTO proIssueDTO = proIssueMapper.toDto(proIssue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProIssueMockMvc.perform(post("/api/pro-issues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssue in the database
        List<ProIssue> proIssueList = proIssueRepository.findAll();
        assertThat(proIssueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProIssues() throws Exception {
        // Initialize the database
        proIssueRepository.saveAndFlush(proIssue);

        // Get all the proIssueList
        restProIssueMockMvc.perform(get("/api/pro-issues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proIssue.getId().intValue())))
            .andExpect(jsonPath("$.[*].maHang").value(hasItem(DEFAULT_MA_HANG)))
            .andExpect(jsonPath("$.[*].pono").value(hasItem(DEFAULT_PONO)))
            .andExpect(jsonPath("$.[*].tenChuyen").value(hasItem(DEFAULT_TEN_CHUYEN)))
            .andExpect(jsonPath("$.[*].ngayGioBatDau").value(hasItem(DEFAULT_NGAY_GIO_BAT_DAU.toString())))
            .andExpect(jsonPath("$.[*].ngayGioKetThuc").value(hasItem(DEFAULT_NGAY_GIO_KET_THUC.toString())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].moTaLoi").value(hasItem(DEFAULT_MO_TA_LOI)))
            .andExpect(jsonPath("$.[*].maMay").value(hasItem(DEFAULT_MA_MAY)))
            .andExpect(jsonPath("$.[*].soTram").value(hasItem(DEFAULT_SO_TRAM)))
            .andExpect(jsonPath("$.[*].proChuyenId").value(hasItem(DEFAULT_PRO_CHUYEN_ID.intValue())))
            .andExpect(jsonPath("$.[*].proSanPhamId").value(hasItem(DEFAULT_PRO_SAN_PHAM_ID.intValue())))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].checkPointType").value(hasItem(DEFAULT_CHECK_POINT_TYPE)))
            .andExpect(jsonPath("$.[*].checkPointId").value(hasItem(DEFAULT_CHECK_POINT_ID.intValue())))
            .andExpect(jsonPath("$.[*].checkPointName").value(hasItem(DEFAULT_CHECK_POINT_NAME)));
    }
    
    @Test
    @Transactional
    public void getProIssue() throws Exception {
        // Initialize the database
        proIssueRepository.saveAndFlush(proIssue);

        // Get the proIssue
        restProIssueMockMvc.perform(get("/api/pro-issues/{id}", proIssue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proIssue.getId().intValue()))
            .andExpect(jsonPath("$.maHang").value(DEFAULT_MA_HANG))
            .andExpect(jsonPath("$.pono").value(DEFAULT_PONO))
            .andExpect(jsonPath("$.tenChuyen").value(DEFAULT_TEN_CHUYEN))
            .andExpect(jsonPath("$.ngayGioBatDau").value(DEFAULT_NGAY_GIO_BAT_DAU.toString()))
            .andExpect(jsonPath("$.ngayGioKetThuc").value(DEFAULT_NGAY_GIO_KET_THUC.toString()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.moTaLoi").value(DEFAULT_MO_TA_LOI))
            .andExpect(jsonPath("$.maMay").value(DEFAULT_MA_MAY))
            .andExpect(jsonPath("$.soTram").value(DEFAULT_SO_TRAM))
            .andExpect(jsonPath("$.proChuyenId").value(DEFAULT_PRO_CHUYEN_ID.intValue()))
            .andExpect(jsonPath("$.proSanPhamId").value(DEFAULT_PRO_SAN_PHAM_ID.intValue()))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.checkPointType").value(DEFAULT_CHECK_POINT_TYPE))
            .andExpect(jsonPath("$.checkPointId").value(DEFAULT_CHECK_POINT_ID.intValue()))
            .andExpect(jsonPath("$.checkPointName").value(DEFAULT_CHECK_POINT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingProIssue() throws Exception {
        // Get the proIssue
        restProIssueMockMvc.perform(get("/api/pro-issues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProIssue() throws Exception {
        // Initialize the database
        proIssueRepository.saveAndFlush(proIssue);

        int databaseSizeBeforeUpdate = proIssueRepository.findAll().size();

        // Update the proIssue
        ProIssue updatedProIssue = proIssueRepository.findById(proIssue.getId()).get();
        // Disconnect from session so that the updates on updatedProIssue are not directly saved in db
        em.detach(updatedProIssue);
        updatedProIssue
            .maHang(UPDATED_MA_HANG)
            .pono(UPDATED_PONO)
            .tenChuyen(UPDATED_TEN_CHUYEN)
            .ngayGioBatDau(UPDATED_NGAY_GIO_BAT_DAU)
            .ngayGioKetThuc(UPDATED_NGAY_GIO_KET_THUC)
            .maLoi(UPDATED_MA_LOI)
            .moTaLoi(UPDATED_MO_TA_LOI)
            .maMay(UPDATED_MA_MAY)
            .soTram(UPDATED_SO_TRAM)
            .proChuyenId(UPDATED_PRO_CHUYEN_ID)
            .proSanPhamId(UPDATED_PRO_SAN_PHAM_ID)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .checkPointType(UPDATED_CHECK_POINT_TYPE)
            .checkPointId(UPDATED_CHECK_POINT_ID)
            .checkPointName(UPDATED_CHECK_POINT_NAME);
        ProIssueDTO proIssueDTO = proIssueMapper.toDto(updatedProIssue);

        restProIssueMockMvc.perform(put("/api/pro-issues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueDTO)))
            .andExpect(status().isOk());

        // Validate the ProIssue in the database
        List<ProIssue> proIssueList = proIssueRepository.findAll();
        assertThat(proIssueList).hasSize(databaseSizeBeforeUpdate);
        ProIssue testProIssue = proIssueList.get(proIssueList.size() - 1);
        assertThat(testProIssue.getMaHang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testProIssue.getPono()).isEqualTo(UPDATED_PONO);
        assertThat(testProIssue.getTenChuyen()).isEqualTo(UPDATED_TEN_CHUYEN);
        assertThat(testProIssue.getNgayGioBatDau()).isEqualTo(UPDATED_NGAY_GIO_BAT_DAU);
        assertThat(testProIssue.getNgayGioKetThuc()).isEqualTo(UPDATED_NGAY_GIO_KET_THUC);
        assertThat(testProIssue.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProIssue.getMoTaLoi()).isEqualTo(UPDATED_MO_TA_LOI);
        assertThat(testProIssue.getMaMay()).isEqualTo(UPDATED_MA_MAY);
        assertThat(testProIssue.getSoTram()).isEqualTo(UPDATED_SO_TRAM);
        assertThat(testProIssue.getProChuyenId()).isEqualTo(UPDATED_PRO_CHUYEN_ID);
        assertThat(testProIssue.getProSanPhamId()).isEqualTo(UPDATED_PRO_SAN_PHAM_ID);
        assertThat(testProIssue.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testProIssue.getCheckPointType()).isEqualTo(UPDATED_CHECK_POINT_TYPE);
        assertThat(testProIssue.getCheckPointId()).isEqualTo(UPDATED_CHECK_POINT_ID);
        assertThat(testProIssue.getCheckPointName()).isEqualTo(UPDATED_CHECK_POINT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingProIssue() throws Exception {
        int databaseSizeBeforeUpdate = proIssueRepository.findAll().size();

        // Create the ProIssue
        ProIssueDTO proIssueDTO = proIssueMapper.toDto(proIssue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProIssueMockMvc.perform(put("/api/pro-issues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssue in the database
        List<ProIssue> proIssueList = proIssueRepository.findAll();
        assertThat(proIssueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProIssue() throws Exception {
        // Initialize the database
        proIssueRepository.saveAndFlush(proIssue);

        int databaseSizeBeforeDelete = proIssueRepository.findAll().size();

        // Delete the proIssue
        restProIssueMockMvc.perform(delete("/api/pro-issues/{id}", proIssue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProIssue> proIssueList = proIssueRepository.findAll();
        assertThat(proIssueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
