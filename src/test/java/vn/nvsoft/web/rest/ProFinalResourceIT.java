package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProFinal;
import vn.nvsoft.repository.ProFinalRepository;
import vn.nvsoft.service.ProFinalService;
import vn.nvsoft.service.dto.ProFinalDTO;
import vn.nvsoft.service.mapper.ProFinalMapper;
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
 * Integration tests for the {@link ProFinalResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProFinalResourceIT {

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

    private static final Integer DEFAULT_SO_THU_TU_THUNG = 1;
    private static final Integer UPDATED_SO_THU_TU_THUNG = 2;

    private static final String DEFAULT_DO_KIM_MD = "AAAAAAAAAA";
    private static final String UPDATED_DO_KIM_MD = "BBBBBBBBBB";

    private static final String DEFAULT_AQL = "AAAAAAAAAA";
    private static final String UPDATED_AQL = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_LOI_CHO_PHEP = 1;
    private static final Integer UPDATED_MAX_LOI_CHO_PHEP = 2;

    private static final Boolean DEFAULT_COMPLETED = false;
    private static final Boolean UPDATED_COMPLETED = true;

    private static final Integer DEFAULT_SO_LUONG_PO_KIEM = 1;
    private static final Integer UPDATED_SO_LUONG_PO_KIEM = 2;

    private static final Boolean DEFAULT_PASS = false;
    private static final Boolean UPDATED_PASS = true;

    private static final String DEFAULT_NGUOI_KIEM = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_KIEM = "BBBBBBBBBB";

    @Autowired
    private ProFinalRepository proFinalRepository;

    @Autowired
    private ProFinalMapper proFinalMapper;

    @Autowired
    private ProFinalService proFinalService;

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

    private MockMvc restProFinalMockMvc;

    private ProFinal proFinal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProFinalResource proFinalResource = new ProFinalResource(proFinalService);
        this.restProFinalMockMvc = MockMvcBuilders.standaloneSetup(proFinalResource)
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
    public static ProFinal createEntity(EntityManager em) {
        ProFinal proFinal = new ProFinal()
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
            .soLuongPoKiem(DEFAULT_SO_LUONG_PO_KIEM)
            .pass(DEFAULT_PASS)
            .nguoiKiem(DEFAULT_NGUOI_KIEM);
        return proFinal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProFinal createUpdatedEntity(EntityManager em) {
        ProFinal proFinal = new ProFinal()
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
            .soLuongPoKiem(UPDATED_SO_LUONG_PO_KIEM)
            .pass(UPDATED_PASS)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        return proFinal;
    }

    @BeforeEach
    public void initTest() {
        proFinal = createEntity(em);
    }

    @Test
    @Transactional
    public void createProFinal() throws Exception {
        int databaseSizeBeforeCreate = proFinalRepository.findAll().size();

        // Create the ProFinal
        ProFinalDTO proFinalDTO = proFinalMapper.toDto(proFinal);
        restProFinalMockMvc.perform(post("/api/pro-finals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalDTO)))
            .andExpect(status().isCreated());

        // Validate the ProFinal in the database
        List<ProFinal> proFinalList = proFinalRepository.findAll();
        assertThat(proFinalList).hasSize(databaseSizeBeforeCreate + 1);
        ProFinal testProFinal = proFinalList.get(proFinalList.size() - 1);
        assertThat(testProFinal.getKhxhId()).isEqualTo(DEFAULT_KHXH_ID);
        assertThat(testProFinal.getMaHang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testProFinal.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testProFinal.getNgayKiemFinal()).isEqualTo(DEFAULT_NGAY_KIEM_FINAL);
        assertThat(testProFinal.getTenChuyen()).isEqualTo(DEFAULT_TEN_CHUYEN);
        assertThat(testProFinal.getNgayGioKiem()).isEqualTo(DEFAULT_NGAY_GIO_KIEM);
        assertThat(testProFinal.getSoLuongKiemAql()).isEqualTo(DEFAULT_SO_LUONG_KIEM_AQL);
        assertThat(testProFinal.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
        assertThat(testProFinal.getDoAm()).isEqualTo(DEFAULT_DO_AM);
        assertThat(testProFinal.getTongSoLoi()).isEqualTo(DEFAULT_TONG_SO_LOI);
        assertThat(testProFinal.getSoThuTuThung()).isEqualTo(DEFAULT_SO_THU_TU_THUNG);
        assertThat(testProFinal.getDoKimMd()).isEqualTo(DEFAULT_DO_KIM_MD);
        assertThat(testProFinal.getAql()).isEqualTo(DEFAULT_AQL);
        assertThat(testProFinal.getMaxLoiChoPhep()).isEqualTo(DEFAULT_MAX_LOI_CHO_PHEP);
        assertThat(testProFinal.isCompleted()).isEqualTo(DEFAULT_COMPLETED);
        assertThat(testProFinal.getSoLuongPoKiem()).isEqualTo(DEFAULT_SO_LUONG_PO_KIEM);
        assertThat(testProFinal.isPass()).isEqualTo(DEFAULT_PASS);
        assertThat(testProFinal.getNguoiKiem()).isEqualTo(DEFAULT_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void createProFinalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proFinalRepository.findAll().size();

        // Create the ProFinal with an existing ID
        proFinal.setId(1L);
        ProFinalDTO proFinalDTO = proFinalMapper.toDto(proFinal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProFinalMockMvc.perform(post("/api/pro-finals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProFinal in the database
        List<ProFinal> proFinalList = proFinalRepository.findAll();
        assertThat(proFinalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProFinals() throws Exception {
        // Initialize the database
        proFinalRepository.saveAndFlush(proFinal);

        // Get all the proFinalList
        restProFinalMockMvc.perform(get("/api/pro-finals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proFinal.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].soLuongPoKiem").value(hasItem(DEFAULT_SO_LUONG_PO_KIEM)))
            .andExpect(jsonPath("$.[*].pass").value(hasItem(DEFAULT_PASS.booleanValue())))
            .andExpect(jsonPath("$.[*].nguoiKiem").value(hasItem(DEFAULT_NGUOI_KIEM)));
    }
    
    @Test
    @Transactional
    public void getProFinal() throws Exception {
        // Initialize the database
        proFinalRepository.saveAndFlush(proFinal);

        // Get the proFinal
        restProFinalMockMvc.perform(get("/api/pro-finals/{id}", proFinal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proFinal.getId().intValue()))
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
            .andExpect(jsonPath("$.soLuongPoKiem").value(DEFAULT_SO_LUONG_PO_KIEM))
            .andExpect(jsonPath("$.pass").value(DEFAULT_PASS.booleanValue()))
            .andExpect(jsonPath("$.nguoiKiem").value(DEFAULT_NGUOI_KIEM));
    }

    @Test
    @Transactional
    public void getNonExistingProFinal() throws Exception {
        // Get the proFinal
        restProFinalMockMvc.perform(get("/api/pro-finals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProFinal() throws Exception {
        // Initialize the database
        proFinalRepository.saveAndFlush(proFinal);

        int databaseSizeBeforeUpdate = proFinalRepository.findAll().size();

        // Update the proFinal
        ProFinal updatedProFinal = proFinalRepository.findById(proFinal.getId()).get();
        // Disconnect from session so that the updates on updatedProFinal are not directly saved in db
        em.detach(updatedProFinal);
        updatedProFinal
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
            .soLuongPoKiem(UPDATED_SO_LUONG_PO_KIEM)
            .pass(UPDATED_PASS)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        ProFinalDTO proFinalDTO = proFinalMapper.toDto(updatedProFinal);

        restProFinalMockMvc.perform(put("/api/pro-finals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalDTO)))
            .andExpect(status().isOk());

        // Validate the ProFinal in the database
        List<ProFinal> proFinalList = proFinalRepository.findAll();
        assertThat(proFinalList).hasSize(databaseSizeBeforeUpdate);
        ProFinal testProFinal = proFinalList.get(proFinalList.size() - 1);
        assertThat(testProFinal.getKhxhId()).isEqualTo(UPDATED_KHXH_ID);
        assertThat(testProFinal.getMaHang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testProFinal.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testProFinal.getNgayKiemFinal()).isEqualTo(UPDATED_NGAY_KIEM_FINAL);
        assertThat(testProFinal.getTenChuyen()).isEqualTo(UPDATED_TEN_CHUYEN);
        assertThat(testProFinal.getNgayGioKiem()).isEqualTo(UPDATED_NGAY_GIO_KIEM);
        assertThat(testProFinal.getSoLuongKiemAql()).isEqualTo(UPDATED_SO_LUONG_KIEM_AQL);
        assertThat(testProFinal.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
        assertThat(testProFinal.getDoAm()).isEqualTo(UPDATED_DO_AM);
        assertThat(testProFinal.getTongSoLoi()).isEqualTo(UPDATED_TONG_SO_LOI);
        assertThat(testProFinal.getSoThuTuThung()).isEqualTo(UPDATED_SO_THU_TU_THUNG);
        assertThat(testProFinal.getDoKimMd()).isEqualTo(UPDATED_DO_KIM_MD);
        assertThat(testProFinal.getAql()).isEqualTo(UPDATED_AQL);
        assertThat(testProFinal.getMaxLoiChoPhep()).isEqualTo(UPDATED_MAX_LOI_CHO_PHEP);
        assertThat(testProFinal.isCompleted()).isEqualTo(UPDATED_COMPLETED);
        assertThat(testProFinal.getSoLuongPoKiem()).isEqualTo(UPDATED_SO_LUONG_PO_KIEM);
        assertThat(testProFinal.isPass()).isEqualTo(UPDATED_PASS);
        assertThat(testProFinal.getNguoiKiem()).isEqualTo(UPDATED_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingProFinal() throws Exception {
        int databaseSizeBeforeUpdate = proFinalRepository.findAll().size();

        // Create the ProFinal
        ProFinalDTO proFinalDTO = proFinalMapper.toDto(proFinal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProFinalMockMvc.perform(put("/api/pro-finals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProFinal in the database
        List<ProFinal> proFinalList = proFinalRepository.findAll();
        assertThat(proFinalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProFinal() throws Exception {
        // Initialize the database
        proFinalRepository.saveAndFlush(proFinal);

        int databaseSizeBeforeDelete = proFinalRepository.findAll().size();

        // Delete the proFinal
        restProFinalMockMvc.perform(delete("/api/pro-finals/{id}", proFinal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProFinal> proFinalList = proFinalRepository.findAll();
        assertThat(proFinalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
