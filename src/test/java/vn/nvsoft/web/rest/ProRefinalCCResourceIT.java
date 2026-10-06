package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProRefinalCC;
import vn.nvsoft.repository.ProRefinalCCRepository;
import vn.nvsoft.service.ProRefinalCCService;
import vn.nvsoft.service.dto.ProRefinalCCDTO;
import vn.nvsoft.service.mapper.ProRefinalCCMapper;
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
 * Integration tests for the {@link ProRefinalCCResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProRefinalCCResourceIT {

    private static final Long DEFAULT_KHXH_ID = 1L;
    private static final Long UPDATED_KHXH_ID = 2L;

    private static final String DEFAULT_MA_HANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_PO_NO = "AAAAAAAAAA";
    private static final String UPDATED_PO_NO = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_KIEM_FINAL = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_KIEM_FINAL = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TEN_CHUYEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CHUYEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_GIO_KIEM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_GIO_KIEM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SO_LUONG_KIEM_AQL = 1;
    private static final Integer UPDATED_SO_LUONG_KIEM_AQL = 2;

    private static final Integer DEFAULT_SO_LUONG = 1;
    private static final Integer UPDATED_SO_LUONG = 2;

    private static final Float DEFAULT_DO_AM = 1F;
    private static final Float UPDATED_DO_AM = 2F;

    private static final Integer DEFAULT_TONG_SO_LOI = 1;
    private static final Integer UPDATED_TONG_SO_LOI = 2;

    private static final String DEFAULT_SO_THU_TU_THUNG = "AAAAAAAAAA";
    private static final String UPDATED_SO_THU_TU_THUNG = "BBBBBBBBBB";

    private static final String DEFAULT_DO_KIM_MD = "AAAAAAAAAA";
    private static final String UPDATED_DO_KIM_MD = "BBBBBBBBBB";

    private static final String DEFAULT_AQL = "AAAAAAAAAA";
    private static final String UPDATED_AQL = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_LOI_CHO_PHEP = 1;
    private static final Integer UPDATED_MAX_LOI_CHO_PHEP = 2;

    private static final Boolean DEFAULT_COMPLETED = false;
    private static final Boolean UPDATED_COMPLETED = true;

    private static final String DEFAULT_NGUOI_KIEM = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_KIEM = "BBBBBBBBBB";

    @Autowired
    private ProRefinalCCRepository proRefinalCCRepository;

    @Autowired
    private ProRefinalCCMapper proRefinalCCMapper;

    @Autowired
    private ProRefinalCCService proRefinalCCService;

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

    private MockMvc restProRefinalCCMockMvc;

    private ProRefinalCC proRefinalCC;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProRefinalCCResource proRefinalCCResource = new ProRefinalCCResource(proRefinalCCService);
        this.restProRefinalCCMockMvc = MockMvcBuilders.standaloneSetup(proRefinalCCResource)
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
    public static ProRefinalCC createEntity(EntityManager em) {
        ProRefinalCC proRefinalCC = new ProRefinalCC()
            .khxhId(DEFAULT_KHXH_ID)
            .maHang(DEFAULT_MA_HANG)
            .poNo(DEFAULT_PO_NO)
            .ngayKiemFinal(DEFAULT_NGAY_KIEM_FINAL)
            .tenChuyen(DEFAULT_TEN_CHUYEN)
            .ngayGioKiem(DEFAULT_NGAY_GIO_KIEM)
            .soLuongKiemAql(DEFAULT_SO_LUONG_KIEM_AQL)
            .soLuong(DEFAULT_SO_LUONG)
            .doAm(DEFAULT_DO_AM)
            .tongSoLoi(DEFAULT_TONG_SO_LOI)
            .soThuTuThung(DEFAULT_SO_THU_TU_THUNG)
            .doKimMd(DEFAULT_DO_KIM_MD)
            .aql(DEFAULT_AQL)
            .maxLoiChoPhep(DEFAULT_MAX_LOI_CHO_PHEP)
            .completed(DEFAULT_COMPLETED)
            .nguoiKiem(DEFAULT_NGUOI_KIEM);
        return proRefinalCC;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProRefinalCC createUpdatedEntity(EntityManager em) {
        ProRefinalCC proRefinalCC = new ProRefinalCC()
            .khxhId(UPDATED_KHXH_ID)
            .maHang(UPDATED_MA_HANG)
            .poNo(UPDATED_PO_NO)
            .ngayKiemFinal(UPDATED_NGAY_KIEM_FINAL)
            .tenChuyen(UPDATED_TEN_CHUYEN)
            .ngayGioKiem(UPDATED_NGAY_GIO_KIEM)
            .soLuongKiemAql(UPDATED_SO_LUONG_KIEM_AQL)
            .soLuong(UPDATED_SO_LUONG)
            .doAm(UPDATED_DO_AM)
            .tongSoLoi(UPDATED_TONG_SO_LOI)
            .soThuTuThung(UPDATED_SO_THU_TU_THUNG)
            .doKimMd(UPDATED_DO_KIM_MD)
            .aql(UPDATED_AQL)
            .maxLoiChoPhep(UPDATED_MAX_LOI_CHO_PHEP)
            .completed(UPDATED_COMPLETED)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        return proRefinalCC;
    }

    @BeforeEach
    public void initTest() {
        proRefinalCC = createEntity(em);
    }

    @Test
    @Transactional
    public void createProRefinalCC() throws Exception {
        int databaseSizeBeforeCreate = proRefinalCCRepository.findAll().size();

        // Create the ProRefinalCC
        ProRefinalCCDTO proRefinalCCDTO = proRefinalCCMapper.toDto(proRefinalCC);
        restProRefinalCCMockMvc.perform(post("/api/pro-refinal-ccs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCDTO)))
            .andExpect(status().isCreated());

        // Validate the ProRefinalCC in the database
        List<ProRefinalCC> proRefinalCCList = proRefinalCCRepository.findAll();
        assertThat(proRefinalCCList).hasSize(databaseSizeBeforeCreate + 1);
        ProRefinalCC testProRefinalCC = proRefinalCCList.get(proRefinalCCList.size() - 1);
        assertThat(testProRefinalCC.getKhxhId()).isEqualTo(DEFAULT_KHXH_ID);
        assertThat(testProRefinalCC.getMaHang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testProRefinalCC.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testProRefinalCC.getNgayKiemFinal()).isEqualTo(DEFAULT_NGAY_KIEM_FINAL);
        assertThat(testProRefinalCC.getTenChuyen()).isEqualTo(DEFAULT_TEN_CHUYEN);
        assertThat(testProRefinalCC.getNgayGioKiem()).isEqualTo(DEFAULT_NGAY_GIO_KIEM);
        assertThat(testProRefinalCC.getSoLuongKiemAql()).isEqualTo(DEFAULT_SO_LUONG_KIEM_AQL);
        assertThat(testProRefinalCC.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testProRefinalCC.getDoAm()).isEqualTo(DEFAULT_DO_AM);
        assertThat(testProRefinalCC.getTongSoLoi()).isEqualTo(DEFAULT_TONG_SO_LOI);
        assertThat(testProRefinalCC.getSoThuTuThung()).isEqualTo(DEFAULT_SO_THU_TU_THUNG);
        assertThat(testProRefinalCC.getDoKimMd()).isEqualTo(DEFAULT_DO_KIM_MD);
        assertThat(testProRefinalCC.getAql()).isEqualTo(DEFAULT_AQL);
        assertThat(testProRefinalCC.getMaxLoiChoPhep()).isEqualTo(DEFAULT_MAX_LOI_CHO_PHEP);
        assertThat(testProRefinalCC.isCompleted()).isEqualTo(DEFAULT_COMPLETED);
        assertThat(testProRefinalCC.getNguoiKiem()).isEqualTo(DEFAULT_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void createProRefinalCCWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proRefinalCCRepository.findAll().size();

        // Create the ProRefinalCC with an existing ID
        proRefinalCC.setId(1L);
        ProRefinalCCDTO proRefinalCCDTO = proRefinalCCMapper.toDto(proRefinalCC);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProRefinalCCMockMvc.perform(post("/api/pro-refinal-ccs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalCC in the database
        List<ProRefinalCC> proRefinalCCList = proRefinalCCRepository.findAll();
        assertThat(proRefinalCCList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProRefinalCCS() throws Exception {
        // Initialize the database
        proRefinalCCRepository.saveAndFlush(proRefinalCC);

        // Get all the proRefinalCCList
        restProRefinalCCMockMvc.perform(get("/api/pro-refinal-ccs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proRefinalCC.getId().intValue())))
            .andExpect(jsonPath("$.[*].khxhId").value(hasItem(DEFAULT_KHXH_ID.intValue())))
            .andExpect(jsonPath("$.[*].maHang").value(hasItem(DEFAULT_MA_HANG)))
            .andExpect(jsonPath("$.[*].poNo").value(hasItem(DEFAULT_PO_NO)))
            .andExpect(jsonPath("$.[*].ngayKiemFinal").value(hasItem(DEFAULT_NGAY_KIEM_FINAL.toString())))
            .andExpect(jsonPath("$.[*].tenChuyen").value(hasItem(DEFAULT_TEN_CHUYEN)))
            .andExpect(jsonPath("$.[*].ngayGioKiem").value(hasItem(DEFAULT_NGAY_GIO_KIEM.toString())))
            .andExpect(jsonPath("$.[*].soLuongKiemAql").value(hasItem(DEFAULT_SO_LUONG_KIEM_AQL)))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG)))
            .andExpect(jsonPath("$.[*].doAm").value(hasItem(DEFAULT_DO_AM.doubleValue())))
            .andExpect(jsonPath("$.[*].tongSoLoi").value(hasItem(DEFAULT_TONG_SO_LOI)))
            .andExpect(jsonPath("$.[*].soThuTuThung").value(hasItem(DEFAULT_SO_THU_TU_THUNG)))
            .andExpect(jsonPath("$.[*].doKimMd").value(hasItem(DEFAULT_DO_KIM_MD)))
            .andExpect(jsonPath("$.[*].aql").value(hasItem(DEFAULT_AQL)))
            .andExpect(jsonPath("$.[*].maxLoiChoPhep").value(hasItem(DEFAULT_MAX_LOI_CHO_PHEP)))
            .andExpect(jsonPath("$.[*].completed").value(hasItem(DEFAULT_COMPLETED.booleanValue())))
            .andExpect(jsonPath("$.[*].nguoiKiem").value(hasItem(DEFAULT_NGUOI_KIEM)));
    }
    
    @Test
    @Transactional
    public void getProRefinalCC() throws Exception {
        // Initialize the database
        proRefinalCCRepository.saveAndFlush(proRefinalCC);

        // Get the proRefinalCC
        restProRefinalCCMockMvc.perform(get("/api/pro-refinal-ccs/{id}", proRefinalCC.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proRefinalCC.getId().intValue()))
            .andExpect(jsonPath("$.khxhId").value(DEFAULT_KHXH_ID.intValue()))
            .andExpect(jsonPath("$.maHang").value(DEFAULT_MA_HANG))
            .andExpect(jsonPath("$.poNo").value(DEFAULT_PO_NO))
            .andExpect(jsonPath("$.ngayKiemFinal").value(DEFAULT_NGAY_KIEM_FINAL.toString()))
            .andExpect(jsonPath("$.tenChuyen").value(DEFAULT_TEN_CHUYEN))
            .andExpect(jsonPath("$.ngayGioKiem").value(DEFAULT_NGAY_GIO_KIEM.toString()))
            .andExpect(jsonPath("$.soLuongKiemAql").value(DEFAULT_SO_LUONG_KIEM_AQL))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG))
            .andExpect(jsonPath("$.doAm").value(DEFAULT_DO_AM.doubleValue()))
            .andExpect(jsonPath("$.tongSoLoi").value(DEFAULT_TONG_SO_LOI))
            .andExpect(jsonPath("$.soThuTuThung").value(DEFAULT_SO_THU_TU_THUNG))
            .andExpect(jsonPath("$.doKimMd").value(DEFAULT_DO_KIM_MD))
            .andExpect(jsonPath("$.aql").value(DEFAULT_AQL))
            .andExpect(jsonPath("$.maxLoiChoPhep").value(DEFAULT_MAX_LOI_CHO_PHEP))
            .andExpect(jsonPath("$.completed").value(DEFAULT_COMPLETED.booleanValue()))
            .andExpect(jsonPath("$.nguoiKiem").value(DEFAULT_NGUOI_KIEM));
    }

    @Test
    @Transactional
    public void getNonExistingProRefinalCC() throws Exception {
        // Get the proRefinalCC
        restProRefinalCCMockMvc.perform(get("/api/pro-refinal-ccs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProRefinalCC() throws Exception {
        // Initialize the database
        proRefinalCCRepository.saveAndFlush(proRefinalCC);

        int databaseSizeBeforeUpdate = proRefinalCCRepository.findAll().size();

        // Update the proRefinalCC
        ProRefinalCC updatedProRefinalCC = proRefinalCCRepository.findById(proRefinalCC.getId()).get();
        // Disconnect from session so that the updates on updatedProRefinalCC are not directly saved in db
        em.detach(updatedProRefinalCC);
        updatedProRefinalCC
            .khxhId(UPDATED_KHXH_ID)
            .maHang(UPDATED_MA_HANG)
            .poNo(UPDATED_PO_NO)
            .ngayKiemFinal(UPDATED_NGAY_KIEM_FINAL)
            .tenChuyen(UPDATED_TEN_CHUYEN)
            .ngayGioKiem(UPDATED_NGAY_GIO_KIEM)
            .soLuongKiemAql(UPDATED_SO_LUONG_KIEM_AQL)
            .soLuong(UPDATED_SO_LUONG)
            .doAm(UPDATED_DO_AM)
            .tongSoLoi(UPDATED_TONG_SO_LOI)
            .soThuTuThung(UPDATED_SO_THU_TU_THUNG)
            .doKimMd(UPDATED_DO_KIM_MD)
            .aql(UPDATED_AQL)
            .maxLoiChoPhep(UPDATED_MAX_LOI_CHO_PHEP)
            .completed(UPDATED_COMPLETED)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        ProRefinalCCDTO proRefinalCCDTO = proRefinalCCMapper.toDto(updatedProRefinalCC);

        restProRefinalCCMockMvc.perform(put("/api/pro-refinal-ccs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCDTO)))
            .andExpect(status().isOk());

        // Validate the ProRefinalCC in the database
        List<ProRefinalCC> proRefinalCCList = proRefinalCCRepository.findAll();
        assertThat(proRefinalCCList).hasSize(databaseSizeBeforeUpdate);
        ProRefinalCC testProRefinalCC = proRefinalCCList.get(proRefinalCCList.size() - 1);
        assertThat(testProRefinalCC.getKhxhId()).isEqualTo(UPDATED_KHXH_ID);
        assertThat(testProRefinalCC.getMaHang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testProRefinalCC.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testProRefinalCC.getNgayKiemFinal()).isEqualTo(UPDATED_NGAY_KIEM_FINAL);
        assertThat(testProRefinalCC.getTenChuyen()).isEqualTo(UPDATED_TEN_CHUYEN);
        assertThat(testProRefinalCC.getNgayGioKiem()).isEqualTo(UPDATED_NGAY_GIO_KIEM);
        assertThat(testProRefinalCC.getSoLuongKiemAql()).isEqualTo(UPDATED_SO_LUONG_KIEM_AQL);
        assertThat(testProRefinalCC.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testProRefinalCC.getDoAm()).isEqualTo(UPDATED_DO_AM);
        assertThat(testProRefinalCC.getTongSoLoi()).isEqualTo(UPDATED_TONG_SO_LOI);
        assertThat(testProRefinalCC.getSoThuTuThung()).isEqualTo(UPDATED_SO_THU_TU_THUNG);
        assertThat(testProRefinalCC.getDoKimMd()).isEqualTo(UPDATED_DO_KIM_MD);
        assertThat(testProRefinalCC.getAql()).isEqualTo(UPDATED_AQL);
        assertThat(testProRefinalCC.getMaxLoiChoPhep()).isEqualTo(UPDATED_MAX_LOI_CHO_PHEP);
        assertThat(testProRefinalCC.isCompleted()).isEqualTo(UPDATED_COMPLETED);
        assertThat(testProRefinalCC.getNguoiKiem()).isEqualTo(UPDATED_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingProRefinalCC() throws Exception {
        int databaseSizeBeforeUpdate = proRefinalCCRepository.findAll().size();

        // Create the ProRefinalCC
        ProRefinalCCDTO proRefinalCCDTO = proRefinalCCMapper.toDto(proRefinalCC);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProRefinalCCMockMvc.perform(put("/api/pro-refinal-ccs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalCC in the database
        List<ProRefinalCC> proRefinalCCList = proRefinalCCRepository.findAll();
        assertThat(proRefinalCCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProRefinalCC() throws Exception {
        // Initialize the database
        proRefinalCCRepository.saveAndFlush(proRefinalCC);

        int databaseSizeBeforeDelete = proRefinalCCRepository.findAll().size();

        // Delete the proRefinalCC
        restProRefinalCCMockMvc.perform(delete("/api/pro-refinal-ccs/{id}", proRefinalCC.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProRefinalCC> proRefinalCCList = proRefinalCCRepository.findAll();
        assertThat(proRefinalCCList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
