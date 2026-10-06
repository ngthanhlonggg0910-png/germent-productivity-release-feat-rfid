package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.service.dto.ProNSCLDTO;
import vn.nvsoft.service.mapper.ProNSCLMapper;
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
 * Integration tests for the {@link ProNSCLResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProNSCLResourceIT {

    private static final String DEFAULT_TAI_KHOAN = "AAAAAAAAAA";
    private static final String UPDATED_TAI_KHOAN = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_SAN_XUAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_SAN_XUAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;

    private static final Integer DEFAULT_MUC_TIEU_NGAY = 1;
    private static final Integer UPDATED_MUC_TIEU_NGAY = 2;

    private static final Integer DEFAULT_THUC_HIEN_NGAY = 1;
    private static final Integer UPDATED_THUC_HIEN_NGAY = 2;

    private static final Integer DEFAULT_LOI_NGAY = 1;
    private static final Integer UPDATED_LOI_NGAY = 2;

    private static final Integer DEFAULT_SUA_NGAY = 1;
    private static final Integer UPDATED_SUA_NGAY = 2;

    private static final Integer DEFAULT_BTP_CAP = 1;
    private static final Integer UPDATED_BTP_CAP = 2;

    private static final Integer DEFAULT_QTY_1_H = 1;
    private static final Integer UPDATED_QTY_1_H = 2;

    private static final Integer DEFAULT_QTY_2_H = 1;
    private static final Integer UPDATED_QTY_2_H = 2;

    private static final Integer DEFAULT_QTY_3_H = 1;
    private static final Integer UPDATED_QTY_3_H = 2;

    private static final Integer DEFAULT_QTY_4_H = 1;
    private static final Integer UPDATED_QTY_4_H = 2;

    private static final Integer DEFAULT_QTY_5_H = 1;
    private static final Integer UPDATED_QTY_5_H = 2;

    private static final Integer DEFAULT_QTY_6_H = 1;
    private static final Integer UPDATED_QTY_6_H = 2;

    private static final Integer DEFAULT_QTY_7_H = 1;
    private static final Integer UPDATED_QTY_7_H = 2;

    private static final Integer DEFAULT_QTY_8_H = 1;
    private static final Integer UPDATED_QTY_8_H = 2;

    private static final Integer DEFAULT_QTY_9_H = 1;
    private static final Integer UPDATED_QTY_9_H = 2;

    private static final Integer DEFAULT_QTY_10_H = 1;
    private static final Integer UPDATED_QTY_10_H = 2;

    private static final Integer DEFAULT_QTY_11_H = 1;
    private static final Integer UPDATED_QTY_11_H = 2;

    private static final Integer DEFAULT_QTY_12_H = 1;
    private static final Integer UPDATED_QTY_12_H = 2;

    private static final Integer DEFAULT_QTY_13_H = 1;
    private static final Integer UPDATED_QTY_13_H = 2;

    private static final Integer DEFAULT_QTY_14_H = 1;
    private static final Integer UPDATED_QTY_14_H = 2;

    private static final Integer DEFAULT_REJECT_1_H = 1;
    private static final Integer UPDATED_REJECT_1_H = 2;

    private static final Integer DEFAULT_REJECT_2_H = 1;
    private static final Integer UPDATED_REJECT_2_H = 2;

    private static final Integer DEFAULT_REJECT_3_H = 1;
    private static final Integer UPDATED_REJECT_3_H = 2;

    private static final Integer DEFAULT_REJECT_4_H = 1;
    private static final Integer UPDATED_REJECT_4_H = 2;

    private static final Integer DEFAULT_REJECT_5_H = 1;
    private static final Integer UPDATED_REJECT_5_H = 2;

    private static final Integer DEFAULT_REJECT_6_H = 1;
    private static final Integer UPDATED_REJECT_6_H = 2;

    private static final Integer DEFAULT_REJECT_7_H = 1;
    private static final Integer UPDATED_REJECT_7_H = 2;

    private static final Integer DEFAULT_REJECT_8_H = 1;
    private static final Integer UPDATED_REJECT_8_H = 2;

    private static final Integer DEFAULT_REJECT_9_H = 1;
    private static final Integer UPDATED_REJECT_9_H = 2;

    private static final Integer DEFAULT_REJECT_10_H = 1;
    private static final Integer UPDATED_REJECT_10_H = 2;

    private static final Integer DEFAULT_REJECT_11_H = 1;
    private static final Integer UPDATED_REJECT_11_H = 2;

    private static final Integer DEFAULT_REJECT_12_H = 1;
    private static final Integer UPDATED_REJECT_12_H = 2;

    private static final Integer DEFAULT_REJECT_13_H = 1;
    private static final Integer UPDATED_REJECT_13_H = 2;

    private static final Integer DEFAULT_REJECT_14_H = 1;
    private static final Integer UPDATED_REJECT_14_H = 2;

    private static final Integer DEFAULT_MAN_1_H = 1;
    private static final Integer UPDATED_MAN_1_H = 2;

    private static final Integer DEFAULT_MAN_2_H = 1;
    private static final Integer UPDATED_MAN_2_H = 2;

    private static final Integer DEFAULT_MAN_3_H = 1;
    private static final Integer UPDATED_MAN_3_H = 2;

    private static final Integer DEFAULT_MAN_4_H = 1;
    private static final Integer UPDATED_MAN_4_H = 2;

    private static final Integer DEFAULT_MAN_5_H = 1;
    private static final Integer UPDATED_MAN_5_H = 2;

    private static final Integer DEFAULT_MAN_6_H = 1;
    private static final Integer UPDATED_MAN_6_H = 2;

    private static final Integer DEFAULT_MAN_7_H = 1;
    private static final Integer UPDATED_MAN_7_H = 2;

    private static final Integer DEFAULT_MAN_8_H = 1;
    private static final Integer UPDATED_MAN_8_H = 2;

    private static final Integer DEFAULT_MAN_9_H = 1;
    private static final Integer UPDATED_MAN_9_H = 2;

    private static final Integer DEFAULT_MAN_10_H = 1;
    private static final Integer UPDATED_MAN_10_H = 2;

    private static final Integer DEFAULT_MAN_11_H = 1;
    private static final Integer UPDATED_MAN_11_H = 2;

    private static final Integer DEFAULT_MAN_12_H = 1;
    private static final Integer UPDATED_MAN_12_H = 2;

    private static final Integer DEFAULT_MAN_13_H = 1;
    private static final Integer UPDATED_MAN_13_H = 2;

    private static final Integer DEFAULT_MAN_14_H = 1;
    private static final Integer UPDATED_MAN_14_H = 2;

    private static final Integer DEFAULT_MAN_ONLY_7_HOURS = 1;
    private static final Integer UPDATED_MAN_ONLY_7_HOURS = 2;

    private static final Integer DEFAULT_FIX_1_H = 1;
    private static final Integer UPDATED_FIX_1_H = 2;

    private static final Integer DEFAULT_FIX_2_H = 1;
    private static final Integer UPDATED_FIX_2_H = 2;

    private static final Integer DEFAULT_FIX_3_H = 1;
    private static final Integer UPDATED_FIX_3_H = 2;

    private static final Integer DEFAULT_FIX_4_H = 1;
    private static final Integer UPDATED_FIX_4_H = 2;

    private static final Integer DEFAULT_FIX_5_H = 1;
    private static final Integer UPDATED_FIX_5_H = 2;

    private static final Integer DEFAULT_FIX_6_H = 1;
    private static final Integer UPDATED_FIX_6_H = 2;

    private static final Integer DEFAULT_FIX_7_H = 1;
    private static final Integer UPDATED_FIX_7_H = 2;

    private static final Integer DEFAULT_FIX_8_H = 1;
    private static final Integer UPDATED_FIX_8_H = 2;

    private static final Integer DEFAULT_FIX_9_H = 1;
    private static final Integer UPDATED_FIX_9_H = 2;

    private static final Integer DEFAULT_FIX_10_H = 1;
    private static final Integer UPDATED_FIX_10_H = 2;

    private static final Integer DEFAULT_FIX_11_H = 1;
    private static final Integer UPDATED_FIX_11_H = 2;

    private static final Integer DEFAULT_FIX_12_H = 1;
    private static final Integer UPDATED_FIX_12_H = 2;

    private static final Integer DEFAULT_FIX_13_H = 1;
    private static final Integer UPDATED_FIX_13_H = 2;

    private static final Integer DEFAULT_FIX_14_H = 1;
    private static final Integer UPDATED_FIX_14_H = 2;

    @Autowired
    private ProNSCLRepository proNSCLRepository;

    @Autowired
    private ProNSCLMapper proNSCLMapper;

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

    private MockMvc restProNSCLMockMvc;

    private ProNSCL proNSCL;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProNSCLResource proNSCLResource = new ProNSCLResource(proNSCLService);
        this.restProNSCLMockMvc = MockMvcBuilders.standaloneSetup(proNSCLResource)
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
    public static ProNSCL createEntity(EntityManager em) {
        ProNSCL proNSCL = new ProNSCL()
            .taiKhoan(DEFAULT_TAI_KHOAN)
            .ngaySanXuat(DEFAULT_NGAY_SAN_XUAT)
            .stt(DEFAULT_STT)
            .mucTieuNgay(DEFAULT_MUC_TIEU_NGAY)
            .thucHienNgay(DEFAULT_THUC_HIEN_NGAY)
            .loiNgay(DEFAULT_LOI_NGAY)
            .suaNgay(DEFAULT_SUA_NGAY)
            .btpCap(DEFAULT_BTP_CAP)
            .qty1h(DEFAULT_QTY_1_H)
            .qty2h(DEFAULT_QTY_2_H)
            .qty3h(DEFAULT_QTY_3_H)
            .qty4h(DEFAULT_QTY_4_H)
            .qty5h(DEFAULT_QTY_5_H)
            .qty6h(DEFAULT_QTY_6_H)
            .qty7h(DEFAULT_QTY_7_H)
            .qty8h(DEFAULT_QTY_8_H)
            .qty9h(DEFAULT_QTY_9_H)
            .qty10h(DEFAULT_QTY_10_H)
            .qty11h(DEFAULT_QTY_11_H)
            .qty12h(DEFAULT_QTY_12_H)
            .qty13h(DEFAULT_QTY_13_H)
            .qty14h(DEFAULT_QTY_14_H)
            .reject1h(DEFAULT_REJECT_1_H)
            .reject2h(DEFAULT_REJECT_2_H)
            .reject3h(DEFAULT_REJECT_3_H)
            .reject4h(DEFAULT_REJECT_4_H)
            .reject5h(DEFAULT_REJECT_5_H)
            .reject6h(DEFAULT_REJECT_6_H)
            .reject7h(DEFAULT_REJECT_7_H)
            .reject8h(DEFAULT_REJECT_8_H)
            .reject9h(DEFAULT_REJECT_9_H)
            .reject10h(DEFAULT_REJECT_10_H)
            .reject11h(DEFAULT_REJECT_11_H)
            .reject12h(DEFAULT_REJECT_12_H)
            .reject13h(DEFAULT_REJECT_13_H)
            .reject14h(DEFAULT_REJECT_14_H)
            .man1h(DEFAULT_MAN_1_H)
            .man2h(DEFAULT_MAN_2_H)
            .man3h(DEFAULT_MAN_3_H)
            .man4h(DEFAULT_MAN_4_H)
            .man5h(DEFAULT_MAN_5_H)
            .man6h(DEFAULT_MAN_6_H)
            .man7h(DEFAULT_MAN_7_H)
            .man8h(DEFAULT_MAN_8_H)
            .man9h(DEFAULT_MAN_9_H)
            .man10h(DEFAULT_MAN_10_H)
            .man11h(DEFAULT_MAN_11_H)
            .man12h(DEFAULT_MAN_12_H)
            .man13h(DEFAULT_MAN_13_H)
            .man14h(DEFAULT_MAN_14_H)
            .manOnly7Hours(DEFAULT_MAN_ONLY_7_HOURS)
            .fix1h(DEFAULT_FIX_1_H)
            .fix2h(DEFAULT_FIX_2_H)
            .fix3h(DEFAULT_FIX_3_H)
            .fix4h(DEFAULT_FIX_4_H)
            .fix5h(DEFAULT_FIX_5_H)
            .fix6h(DEFAULT_FIX_6_H)
            .fix7h(DEFAULT_FIX_7_H)
            .fix8h(DEFAULT_FIX_8_H)
            .fix9h(DEFAULT_FIX_9_H)
            .fix10h(DEFAULT_FIX_10_H)
            .fix11h(DEFAULT_FIX_11_H)
            .fix12h(DEFAULT_FIX_12_H)
            .fix13h(DEFAULT_FIX_13_H)
            .fix14h(DEFAULT_FIX_14_H);
        return proNSCL;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProNSCL createUpdatedEntity(EntityManager em) {
        ProNSCL proNSCL = new ProNSCL()
            .taiKhoan(UPDATED_TAI_KHOAN)
            .ngaySanXuat(UPDATED_NGAY_SAN_XUAT)
            .stt(UPDATED_STT)
            .mucTieuNgay(UPDATED_MUC_TIEU_NGAY)
            .thucHienNgay(UPDATED_THUC_HIEN_NGAY)
            .loiNgay(UPDATED_LOI_NGAY)
            .suaNgay(UPDATED_SUA_NGAY)
            .btpCap(UPDATED_BTP_CAP)
            .qty1h(UPDATED_QTY_1_H)
            .qty2h(UPDATED_QTY_2_H)
            .qty3h(UPDATED_QTY_3_H)
            .qty4h(UPDATED_QTY_4_H)
            .qty5h(UPDATED_QTY_5_H)
            .qty6h(UPDATED_QTY_6_H)
            .qty7h(UPDATED_QTY_7_H)
            .qty8h(UPDATED_QTY_8_H)
            .qty9h(UPDATED_QTY_9_H)
            .qty10h(UPDATED_QTY_10_H)
            .qty11h(UPDATED_QTY_11_H)
            .qty12h(UPDATED_QTY_12_H)
            .qty13h(UPDATED_QTY_13_H)
            .qty14h(UPDATED_QTY_14_H)
            .reject1h(UPDATED_REJECT_1_H)
            .reject2h(UPDATED_REJECT_2_H)
            .reject3h(UPDATED_REJECT_3_H)
            .reject4h(UPDATED_REJECT_4_H)
            .reject5h(UPDATED_REJECT_5_H)
            .reject6h(UPDATED_REJECT_6_H)
            .reject7h(UPDATED_REJECT_7_H)
            .reject8h(UPDATED_REJECT_8_H)
            .reject9h(UPDATED_REJECT_9_H)
            .reject10h(UPDATED_REJECT_10_H)
            .reject11h(UPDATED_REJECT_11_H)
            .reject12h(UPDATED_REJECT_12_H)
            .reject13h(UPDATED_REJECT_13_H)
            .reject14h(UPDATED_REJECT_14_H)
            .man1h(UPDATED_MAN_1_H)
            .man2h(UPDATED_MAN_2_H)
            .man3h(UPDATED_MAN_3_H)
            .man4h(UPDATED_MAN_4_H)
            .man5h(UPDATED_MAN_5_H)
            .man6h(UPDATED_MAN_6_H)
            .man7h(UPDATED_MAN_7_H)
            .man8h(UPDATED_MAN_8_H)
            .man9h(UPDATED_MAN_9_H)
            .man10h(UPDATED_MAN_10_H)
            .man11h(UPDATED_MAN_11_H)
            .man12h(UPDATED_MAN_12_H)
            .man13h(UPDATED_MAN_13_H)
            .man14h(UPDATED_MAN_14_H)
            .manOnly7Hours(UPDATED_MAN_ONLY_7_HOURS)
            .fix1h(UPDATED_FIX_1_H)
            .fix2h(UPDATED_FIX_2_H)
            .fix3h(UPDATED_FIX_3_H)
            .fix4h(UPDATED_FIX_4_H)
            .fix5h(UPDATED_FIX_5_H)
            .fix6h(UPDATED_FIX_6_H)
            .fix7h(UPDATED_FIX_7_H)
            .fix8h(UPDATED_FIX_8_H)
            .fix9h(UPDATED_FIX_9_H)
            .fix10h(UPDATED_FIX_10_H)
            .fix11h(UPDATED_FIX_11_H)
            .fix12h(UPDATED_FIX_12_H)
            .fix13h(UPDATED_FIX_13_H)
            .fix14h(UPDATED_FIX_14_H);
        return proNSCL;
    }

    @BeforeEach
    public void initTest() {
        proNSCL = createEntity(em);
    }

    @Test
    @Transactional
    public void createProNSCL() throws Exception {
        int databaseSizeBeforeCreate = proNSCLRepository.findAll().size();

        // Create the ProNSCL
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);
        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isCreated());

        // Validate the ProNSCL in the database
        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeCreate + 1);
        ProNSCL testProNSCL = proNSCLList.get(proNSCLList.size() - 1);
        assertThat(testProNSCL.getTaiKhoan()).isEqualTo(DEFAULT_TAI_KHOAN);
        assertThat(testProNSCL.getNgaySanXuat()).isEqualTo(DEFAULT_NGAY_SAN_XUAT);
        assertThat(testProNSCL.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testProNSCL.getMucTieuNgay()).isEqualTo(DEFAULT_MUC_TIEU_NGAY);
        assertThat(testProNSCL.getThucHienNgay()).isEqualTo(DEFAULT_THUC_HIEN_NGAY);
        assertThat(testProNSCL.getLoiNgay()).isEqualTo(DEFAULT_LOI_NGAY);
        assertThat(testProNSCL.getSuaNgay()).isEqualTo(DEFAULT_SUA_NGAY);
        assertThat(testProNSCL.getBtpCap()).isEqualTo(DEFAULT_BTP_CAP);
        assertThat(testProNSCL.getQty1h()).isEqualTo(DEFAULT_QTY_1_H);
        assertThat(testProNSCL.getQty2h()).isEqualTo(DEFAULT_QTY_2_H);
        assertThat(testProNSCL.getQty3h()).isEqualTo(DEFAULT_QTY_3_H);
        assertThat(testProNSCL.getQty4h()).isEqualTo(DEFAULT_QTY_4_H);
        assertThat(testProNSCL.getQty5h()).isEqualTo(DEFAULT_QTY_5_H);
        assertThat(testProNSCL.getQty6h()).isEqualTo(DEFAULT_QTY_6_H);
        assertThat(testProNSCL.getQty7h()).isEqualTo(DEFAULT_QTY_7_H);
        assertThat(testProNSCL.getQty8h()).isEqualTo(DEFAULT_QTY_8_H);
        assertThat(testProNSCL.getQty9h()).isEqualTo(DEFAULT_QTY_9_H);
        assertThat(testProNSCL.getQty10h()).isEqualTo(DEFAULT_QTY_10_H);
        assertThat(testProNSCL.getQty11h()).isEqualTo(DEFAULT_QTY_11_H);
        assertThat(testProNSCL.getQty12h()).isEqualTo(DEFAULT_QTY_12_H);
        assertThat(testProNSCL.getQty13h()).isEqualTo(DEFAULT_QTY_13_H);
        assertThat(testProNSCL.getQty14h()).isEqualTo(DEFAULT_QTY_14_H);
        assertThat(testProNSCL.getReject1h()).isEqualTo(DEFAULT_REJECT_1_H);
        assertThat(testProNSCL.getReject2h()).isEqualTo(DEFAULT_REJECT_2_H);
        assertThat(testProNSCL.getReject3h()).isEqualTo(DEFAULT_REJECT_3_H);
        assertThat(testProNSCL.getReject4h()).isEqualTo(DEFAULT_REJECT_4_H);
        assertThat(testProNSCL.getReject5h()).isEqualTo(DEFAULT_REJECT_5_H);
        assertThat(testProNSCL.getReject6h()).isEqualTo(DEFAULT_REJECT_6_H);
        assertThat(testProNSCL.getReject7h()).isEqualTo(DEFAULT_REJECT_7_H);
        assertThat(testProNSCL.getReject8h()).isEqualTo(DEFAULT_REJECT_8_H);
        assertThat(testProNSCL.getReject9h()).isEqualTo(DEFAULT_REJECT_9_H);
        assertThat(testProNSCL.getReject10h()).isEqualTo(DEFAULT_REJECT_10_H);
        assertThat(testProNSCL.getReject11h()).isEqualTo(DEFAULT_REJECT_11_H);
        assertThat(testProNSCL.getReject12h()).isEqualTo(DEFAULT_REJECT_12_H);
        assertThat(testProNSCL.getReject13h()).isEqualTo(DEFAULT_REJECT_13_H);
        assertThat(testProNSCL.getReject14h()).isEqualTo(DEFAULT_REJECT_14_H);
        assertThat(testProNSCL.getMan1h()).isEqualTo(DEFAULT_MAN_1_H);
        assertThat(testProNSCL.getMan2h()).isEqualTo(DEFAULT_MAN_2_H);
        assertThat(testProNSCL.getMan3h()).isEqualTo(DEFAULT_MAN_3_H);
        assertThat(testProNSCL.getMan4h()).isEqualTo(DEFAULT_MAN_4_H);
        assertThat(testProNSCL.getMan5h()).isEqualTo(DEFAULT_MAN_5_H);
        assertThat(testProNSCL.getMan6h()).isEqualTo(DEFAULT_MAN_6_H);
        assertThat(testProNSCL.getMan7h()).isEqualTo(DEFAULT_MAN_7_H);
        assertThat(testProNSCL.getMan8h()).isEqualTo(DEFAULT_MAN_8_H);
        assertThat(testProNSCL.getMan9h()).isEqualTo(DEFAULT_MAN_9_H);
        assertThat(testProNSCL.getMan10h()).isEqualTo(DEFAULT_MAN_10_H);
        assertThat(testProNSCL.getMan11h()).isEqualTo(DEFAULT_MAN_11_H);
        assertThat(testProNSCL.getMan12h()).isEqualTo(DEFAULT_MAN_12_H);
        assertThat(testProNSCL.getMan13h()).isEqualTo(DEFAULT_MAN_13_H);
        assertThat(testProNSCL.getMan14h()).isEqualTo(DEFAULT_MAN_14_H);
        assertThat(testProNSCL.getManOnly7Hours()).isEqualTo(DEFAULT_MAN_ONLY_7_HOURS);
        assertThat(testProNSCL.getFix1h()).isEqualTo(DEFAULT_FIX_1_H);
        assertThat(testProNSCL.getFix2h()).isEqualTo(DEFAULT_FIX_2_H);
        assertThat(testProNSCL.getFix3h()).isEqualTo(DEFAULT_FIX_3_H);
        assertThat(testProNSCL.getFix4h()).isEqualTo(DEFAULT_FIX_4_H);
        assertThat(testProNSCL.getFix5h()).isEqualTo(DEFAULT_FIX_5_H);
        assertThat(testProNSCL.getFix6h()).isEqualTo(DEFAULT_FIX_6_H);
        assertThat(testProNSCL.getFix7h()).isEqualTo(DEFAULT_FIX_7_H);
        assertThat(testProNSCL.getFix8h()).isEqualTo(DEFAULT_FIX_8_H);
        assertThat(testProNSCL.getFix9h()).isEqualTo(DEFAULT_FIX_9_H);
        assertThat(testProNSCL.getFix10h()).isEqualTo(DEFAULT_FIX_10_H);
        assertThat(testProNSCL.getFix11h()).isEqualTo(DEFAULT_FIX_11_H);
        assertThat(testProNSCL.getFix12h()).isEqualTo(DEFAULT_FIX_12_H);
        assertThat(testProNSCL.getFix13h()).isEqualTo(DEFAULT_FIX_13_H);
        assertThat(testProNSCL.getFix14h()).isEqualTo(DEFAULT_FIX_14_H);
    }

    @Test
    @Transactional
    public void createProNSCLWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proNSCLRepository.findAll().size();

        // Create the ProNSCL with an existing ID
        proNSCL.setId(1L);
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNSCL in the database
        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQty1hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty1h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty2hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty2h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty3hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty3h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty4hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty4h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty5hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty5h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty6hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty6h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty7hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty7h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty8hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty8h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty9hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty9h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty10hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty10h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty11hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty11h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty12hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty12h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty13hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty13h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQty14hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setQty14h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject1hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject1h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject2hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject2h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject3hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject3h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject4hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject4h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject5hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject5h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject6hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject6h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject7hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject7h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject8hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject8h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject9hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject9h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject10hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject10h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject11hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject11h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject12hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject12h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject13hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject13h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReject14hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setReject14h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan1hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan1h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan2hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan2h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan3hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan3h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan4hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan4h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan5hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan5h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan6hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan6h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan7hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan7h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan8hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan8h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan9hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan9h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan10hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan10h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan11hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan11h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan12hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan12h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan13hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan13h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMan14hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setMan14h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkManOnly7HoursIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setManOnly7Hours(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix1hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix1h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix2hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix2h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix3hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix3h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix4hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix4h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix5hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix5h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix6hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix6h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix7hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix7h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix8hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix8h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix9hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix9h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix10hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix10h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix11hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix11h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix12hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix12h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix13hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix13h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFix14hIsRequired() throws Exception {
        int databaseSizeBeforeTest = proNSCLRepository.findAll().size();
        // set the field null
        proNSCL.setFix14h(null);

        // Create the ProNSCL, which fails.
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        restProNSCLMockMvc.perform(post("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProNSCLS() throws Exception {
        // Initialize the database
        proNSCLRepository.saveAndFlush(proNSCL);

        // Get all the proNSCLList
        restProNSCLMockMvc.perform(get("/api/pro-nscls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proNSCL.getId().intValue())))
            .andExpect(jsonPath("$.[*].taiKhoan").value(hasItem(DEFAULT_TAI_KHOAN)))
            .andExpect(jsonPath("$.[*].ngaySanXuat").value(hasItem(DEFAULT_NGAY_SAN_XUAT.toString())))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].mucTieuNgay").value(hasItem(DEFAULT_MUC_TIEU_NGAY)))
            .andExpect(jsonPath("$.[*].thucHienNgay").value(hasItem(DEFAULT_THUC_HIEN_NGAY)))
            .andExpect(jsonPath("$.[*].loiNgay").value(hasItem(DEFAULT_LOI_NGAY)))
            .andExpect(jsonPath("$.[*].suaNgay").value(hasItem(DEFAULT_SUA_NGAY)))
            .andExpect(jsonPath("$.[*].btpCap").value(hasItem(DEFAULT_BTP_CAP)))
            .andExpect(jsonPath("$.[*].qty1h").value(hasItem(DEFAULT_QTY_1_H)))
            .andExpect(jsonPath("$.[*].qty2h").value(hasItem(DEFAULT_QTY_2_H)))
            .andExpect(jsonPath("$.[*].qty3h").value(hasItem(DEFAULT_QTY_3_H)))
            .andExpect(jsonPath("$.[*].qty4h").value(hasItem(DEFAULT_QTY_4_H)))
            .andExpect(jsonPath("$.[*].qty5h").value(hasItem(DEFAULT_QTY_5_H)))
            .andExpect(jsonPath("$.[*].qty6h").value(hasItem(DEFAULT_QTY_6_H)))
            .andExpect(jsonPath("$.[*].qty7h").value(hasItem(DEFAULT_QTY_7_H)))
            .andExpect(jsonPath("$.[*].qty8h").value(hasItem(DEFAULT_QTY_8_H)))
            .andExpect(jsonPath("$.[*].qty9h").value(hasItem(DEFAULT_QTY_9_H)))
            .andExpect(jsonPath("$.[*].qty10h").value(hasItem(DEFAULT_QTY_10_H)))
            .andExpect(jsonPath("$.[*].qty11h").value(hasItem(DEFAULT_QTY_11_H)))
            .andExpect(jsonPath("$.[*].qty12h").value(hasItem(DEFAULT_QTY_12_H)))
            .andExpect(jsonPath("$.[*].qty13h").value(hasItem(DEFAULT_QTY_13_H)))
            .andExpect(jsonPath("$.[*].qty14h").value(hasItem(DEFAULT_QTY_14_H)))
            .andExpect(jsonPath("$.[*].reject1h").value(hasItem(DEFAULT_REJECT_1_H)))
            .andExpect(jsonPath("$.[*].reject2h").value(hasItem(DEFAULT_REJECT_2_H)))
            .andExpect(jsonPath("$.[*].reject3h").value(hasItem(DEFAULT_REJECT_3_H)))
            .andExpect(jsonPath("$.[*].reject4h").value(hasItem(DEFAULT_REJECT_4_H)))
            .andExpect(jsonPath("$.[*].reject5h").value(hasItem(DEFAULT_REJECT_5_H)))
            .andExpect(jsonPath("$.[*].reject6h").value(hasItem(DEFAULT_REJECT_6_H)))
            .andExpect(jsonPath("$.[*].reject7h").value(hasItem(DEFAULT_REJECT_7_H)))
            .andExpect(jsonPath("$.[*].reject8h").value(hasItem(DEFAULT_REJECT_8_H)))
            .andExpect(jsonPath("$.[*].reject9h").value(hasItem(DEFAULT_REJECT_9_H)))
            .andExpect(jsonPath("$.[*].reject10h").value(hasItem(DEFAULT_REJECT_10_H)))
            .andExpect(jsonPath("$.[*].reject11h").value(hasItem(DEFAULT_REJECT_11_H)))
            .andExpect(jsonPath("$.[*].reject12h").value(hasItem(DEFAULT_REJECT_12_H)))
            .andExpect(jsonPath("$.[*].reject13h").value(hasItem(DEFAULT_REJECT_13_H)))
            .andExpect(jsonPath("$.[*].reject14h").value(hasItem(DEFAULT_REJECT_14_H)))
            .andExpect(jsonPath("$.[*].man1h").value(hasItem(DEFAULT_MAN_1_H)))
            .andExpect(jsonPath("$.[*].man2h").value(hasItem(DEFAULT_MAN_2_H)))
            .andExpect(jsonPath("$.[*].man3h").value(hasItem(DEFAULT_MAN_3_H)))
            .andExpect(jsonPath("$.[*].man4h").value(hasItem(DEFAULT_MAN_4_H)))
            .andExpect(jsonPath("$.[*].man5h").value(hasItem(DEFAULT_MAN_5_H)))
            .andExpect(jsonPath("$.[*].man6h").value(hasItem(DEFAULT_MAN_6_H)))
            .andExpect(jsonPath("$.[*].man7h").value(hasItem(DEFAULT_MAN_7_H)))
            .andExpect(jsonPath("$.[*].man8h").value(hasItem(DEFAULT_MAN_8_H)))
            .andExpect(jsonPath("$.[*].man9h").value(hasItem(DEFAULT_MAN_9_H)))
            .andExpect(jsonPath("$.[*].man10h").value(hasItem(DEFAULT_MAN_10_H)))
            .andExpect(jsonPath("$.[*].man11h").value(hasItem(DEFAULT_MAN_11_H)))
            .andExpect(jsonPath("$.[*].man12h").value(hasItem(DEFAULT_MAN_12_H)))
            .andExpect(jsonPath("$.[*].man13h").value(hasItem(DEFAULT_MAN_13_H)))
            .andExpect(jsonPath("$.[*].man14h").value(hasItem(DEFAULT_MAN_14_H)))
            .andExpect(jsonPath("$.[*].manOnly7Hours").value(hasItem(DEFAULT_MAN_ONLY_7_HOURS)))
            .andExpect(jsonPath("$.[*].fix1h").value(hasItem(DEFAULT_FIX_1_H)))
            .andExpect(jsonPath("$.[*].fix2h").value(hasItem(DEFAULT_FIX_2_H)))
            .andExpect(jsonPath("$.[*].fix3h").value(hasItem(DEFAULT_FIX_3_H)))
            .andExpect(jsonPath("$.[*].fix4h").value(hasItem(DEFAULT_FIX_4_H)))
            .andExpect(jsonPath("$.[*].fix5h").value(hasItem(DEFAULT_FIX_5_H)))
            .andExpect(jsonPath("$.[*].fix6h").value(hasItem(DEFAULT_FIX_6_H)))
            .andExpect(jsonPath("$.[*].fix7h").value(hasItem(DEFAULT_FIX_7_H)))
            .andExpect(jsonPath("$.[*].fix8h").value(hasItem(DEFAULT_FIX_8_H)))
            .andExpect(jsonPath("$.[*].fix9h").value(hasItem(DEFAULT_FIX_9_H)))
            .andExpect(jsonPath("$.[*].fix10h").value(hasItem(DEFAULT_FIX_10_H)))
            .andExpect(jsonPath("$.[*].fix11h").value(hasItem(DEFAULT_FIX_11_H)))
            .andExpect(jsonPath("$.[*].fix12h").value(hasItem(DEFAULT_FIX_12_H)))
            .andExpect(jsonPath("$.[*].fix13h").value(hasItem(DEFAULT_FIX_13_H)))
            .andExpect(jsonPath("$.[*].fix14h").value(hasItem(DEFAULT_FIX_14_H)));
    }
    
    @Test
    @Transactional
    public void getProNSCL() throws Exception {
        // Initialize the database
        proNSCLRepository.saveAndFlush(proNSCL);

        // Get the proNSCL
        restProNSCLMockMvc.perform(get("/api/pro-nscls/{id}", proNSCL.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proNSCL.getId().intValue()))
            .andExpect(jsonPath("$.taiKhoan").value(DEFAULT_TAI_KHOAN))
            .andExpect(jsonPath("$.ngaySanXuat").value(DEFAULT_NGAY_SAN_XUAT.toString()))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.mucTieuNgay").value(DEFAULT_MUC_TIEU_NGAY))
            .andExpect(jsonPath("$.thucHienNgay").value(DEFAULT_THUC_HIEN_NGAY))
            .andExpect(jsonPath("$.loiNgay").value(DEFAULT_LOI_NGAY))
            .andExpect(jsonPath("$.suaNgay").value(DEFAULT_SUA_NGAY))
            .andExpect(jsonPath("$.btpCap").value(DEFAULT_BTP_CAP))
            .andExpect(jsonPath("$.qty1h").value(DEFAULT_QTY_1_H))
            .andExpect(jsonPath("$.qty2h").value(DEFAULT_QTY_2_H))
            .andExpect(jsonPath("$.qty3h").value(DEFAULT_QTY_3_H))
            .andExpect(jsonPath("$.qty4h").value(DEFAULT_QTY_4_H))
            .andExpect(jsonPath("$.qty5h").value(DEFAULT_QTY_5_H))
            .andExpect(jsonPath("$.qty6h").value(DEFAULT_QTY_6_H))
            .andExpect(jsonPath("$.qty7h").value(DEFAULT_QTY_7_H))
            .andExpect(jsonPath("$.qty8h").value(DEFAULT_QTY_8_H))
            .andExpect(jsonPath("$.qty9h").value(DEFAULT_QTY_9_H))
            .andExpect(jsonPath("$.qty10h").value(DEFAULT_QTY_10_H))
            .andExpect(jsonPath("$.qty11h").value(DEFAULT_QTY_11_H))
            .andExpect(jsonPath("$.qty12h").value(DEFAULT_QTY_12_H))
            .andExpect(jsonPath("$.qty13h").value(DEFAULT_QTY_13_H))
            .andExpect(jsonPath("$.qty14h").value(DEFAULT_QTY_14_H))
            .andExpect(jsonPath("$.reject1h").value(DEFAULT_REJECT_1_H))
            .andExpect(jsonPath("$.reject2h").value(DEFAULT_REJECT_2_H))
            .andExpect(jsonPath("$.reject3h").value(DEFAULT_REJECT_3_H))
            .andExpect(jsonPath("$.reject4h").value(DEFAULT_REJECT_4_H))
            .andExpect(jsonPath("$.reject5h").value(DEFAULT_REJECT_5_H))
            .andExpect(jsonPath("$.reject6h").value(DEFAULT_REJECT_6_H))
            .andExpect(jsonPath("$.reject7h").value(DEFAULT_REJECT_7_H))
            .andExpect(jsonPath("$.reject8h").value(DEFAULT_REJECT_8_H))
            .andExpect(jsonPath("$.reject9h").value(DEFAULT_REJECT_9_H))
            .andExpect(jsonPath("$.reject10h").value(DEFAULT_REJECT_10_H))
            .andExpect(jsonPath("$.reject11h").value(DEFAULT_REJECT_11_H))
            .andExpect(jsonPath("$.reject12h").value(DEFAULT_REJECT_12_H))
            .andExpect(jsonPath("$.reject13h").value(DEFAULT_REJECT_13_H))
            .andExpect(jsonPath("$.reject14h").value(DEFAULT_REJECT_14_H))
            .andExpect(jsonPath("$.man1h").value(DEFAULT_MAN_1_H))
            .andExpect(jsonPath("$.man2h").value(DEFAULT_MAN_2_H))
            .andExpect(jsonPath("$.man3h").value(DEFAULT_MAN_3_H))
            .andExpect(jsonPath("$.man4h").value(DEFAULT_MAN_4_H))
            .andExpect(jsonPath("$.man5h").value(DEFAULT_MAN_5_H))
            .andExpect(jsonPath("$.man6h").value(DEFAULT_MAN_6_H))
            .andExpect(jsonPath("$.man7h").value(DEFAULT_MAN_7_H))
            .andExpect(jsonPath("$.man8h").value(DEFAULT_MAN_8_H))
            .andExpect(jsonPath("$.man9h").value(DEFAULT_MAN_9_H))
            .andExpect(jsonPath("$.man10h").value(DEFAULT_MAN_10_H))
            .andExpect(jsonPath("$.man11h").value(DEFAULT_MAN_11_H))
            .andExpect(jsonPath("$.man12h").value(DEFAULT_MAN_12_H))
            .andExpect(jsonPath("$.man13h").value(DEFAULT_MAN_13_H))
            .andExpect(jsonPath("$.man14h").value(DEFAULT_MAN_14_H))
            .andExpect(jsonPath("$.manOnly7Hours").value(DEFAULT_MAN_ONLY_7_HOURS))
            .andExpect(jsonPath("$.fix1h").value(DEFAULT_FIX_1_H))
            .andExpect(jsonPath("$.fix2h").value(DEFAULT_FIX_2_H))
            .andExpect(jsonPath("$.fix3h").value(DEFAULT_FIX_3_H))
            .andExpect(jsonPath("$.fix4h").value(DEFAULT_FIX_4_H))
            .andExpect(jsonPath("$.fix5h").value(DEFAULT_FIX_5_H))
            .andExpect(jsonPath("$.fix6h").value(DEFAULT_FIX_6_H))
            .andExpect(jsonPath("$.fix7h").value(DEFAULT_FIX_7_H))
            .andExpect(jsonPath("$.fix8h").value(DEFAULT_FIX_8_H))
            .andExpect(jsonPath("$.fix9h").value(DEFAULT_FIX_9_H))
            .andExpect(jsonPath("$.fix10h").value(DEFAULT_FIX_10_H))
            .andExpect(jsonPath("$.fix11h").value(DEFAULT_FIX_11_H))
            .andExpect(jsonPath("$.fix12h").value(DEFAULT_FIX_12_H))
            .andExpect(jsonPath("$.fix13h").value(DEFAULT_FIX_13_H))
            .andExpect(jsonPath("$.fix14h").value(DEFAULT_FIX_14_H));
    }

    @Test
    @Transactional
    public void getNonExistingProNSCL() throws Exception {
        // Get the proNSCL
        restProNSCLMockMvc.perform(get("/api/pro-nscls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProNSCL() throws Exception {
        // Initialize the database
        proNSCLRepository.saveAndFlush(proNSCL);

        int databaseSizeBeforeUpdate = proNSCLRepository.findAll().size();

        // Update the proNSCL
        ProNSCL updatedProNSCL = proNSCLRepository.findById(proNSCL.getId()).get();
        // Disconnect from session so that the updates on updatedProNSCL are not directly saved in db
        em.detach(updatedProNSCL);
        updatedProNSCL
            .taiKhoan(UPDATED_TAI_KHOAN)
            .ngaySanXuat(UPDATED_NGAY_SAN_XUAT)
            .stt(UPDATED_STT)
            .mucTieuNgay(UPDATED_MUC_TIEU_NGAY)
            .thucHienNgay(UPDATED_THUC_HIEN_NGAY)
            .loiNgay(UPDATED_LOI_NGAY)
            .suaNgay(UPDATED_SUA_NGAY)
            .btpCap(UPDATED_BTP_CAP)
            .qty1h(UPDATED_QTY_1_H)
            .qty2h(UPDATED_QTY_2_H)
            .qty3h(UPDATED_QTY_3_H)
            .qty4h(UPDATED_QTY_4_H)
            .qty5h(UPDATED_QTY_5_H)
            .qty6h(UPDATED_QTY_6_H)
            .qty7h(UPDATED_QTY_7_H)
            .qty8h(UPDATED_QTY_8_H)
            .qty9h(UPDATED_QTY_9_H)
            .qty10h(UPDATED_QTY_10_H)
            .qty11h(UPDATED_QTY_11_H)
            .qty12h(UPDATED_QTY_12_H)
            .qty13h(UPDATED_QTY_13_H)
            .qty14h(UPDATED_QTY_14_H)
            .reject1h(UPDATED_REJECT_1_H)
            .reject2h(UPDATED_REJECT_2_H)
            .reject3h(UPDATED_REJECT_3_H)
            .reject4h(UPDATED_REJECT_4_H)
            .reject5h(UPDATED_REJECT_5_H)
            .reject6h(UPDATED_REJECT_6_H)
            .reject7h(UPDATED_REJECT_7_H)
            .reject8h(UPDATED_REJECT_8_H)
            .reject9h(UPDATED_REJECT_9_H)
            .reject10h(UPDATED_REJECT_10_H)
            .reject11h(UPDATED_REJECT_11_H)
            .reject12h(UPDATED_REJECT_12_H)
            .reject13h(UPDATED_REJECT_13_H)
            .reject14h(UPDATED_REJECT_14_H)
            .man1h(UPDATED_MAN_1_H)
            .man2h(UPDATED_MAN_2_H)
            .man3h(UPDATED_MAN_3_H)
            .man4h(UPDATED_MAN_4_H)
            .man5h(UPDATED_MAN_5_H)
            .man6h(UPDATED_MAN_6_H)
            .man7h(UPDATED_MAN_7_H)
            .man8h(UPDATED_MAN_8_H)
            .man9h(UPDATED_MAN_9_H)
            .man10h(UPDATED_MAN_10_H)
            .man11h(UPDATED_MAN_11_H)
            .man12h(UPDATED_MAN_12_H)
            .man13h(UPDATED_MAN_13_H)
            .man14h(UPDATED_MAN_14_H)
            .manOnly7Hours(UPDATED_MAN_ONLY_7_HOURS)
            .fix1h(UPDATED_FIX_1_H)
            .fix2h(UPDATED_FIX_2_H)
            .fix3h(UPDATED_FIX_3_H)
            .fix4h(UPDATED_FIX_4_H)
            .fix5h(UPDATED_FIX_5_H)
            .fix6h(UPDATED_FIX_6_H)
            .fix7h(UPDATED_FIX_7_H)
            .fix8h(UPDATED_FIX_8_H)
            .fix9h(UPDATED_FIX_9_H)
            .fix10h(UPDATED_FIX_10_H)
            .fix11h(UPDATED_FIX_11_H)
            .fix12h(UPDATED_FIX_12_H)
            .fix13h(UPDATED_FIX_13_H)
            .fix14h(UPDATED_FIX_14_H);
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(updatedProNSCL);

        restProNSCLMockMvc.perform(put("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isOk());

        // Validate the ProNSCL in the database
        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeUpdate);
        ProNSCL testProNSCL = proNSCLList.get(proNSCLList.size() - 1);
        assertThat(testProNSCL.getTaiKhoan()).isEqualTo(UPDATED_TAI_KHOAN);
        assertThat(testProNSCL.getNgaySanXuat()).isEqualTo(UPDATED_NGAY_SAN_XUAT);
        assertThat(testProNSCL.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testProNSCL.getMucTieuNgay()).isEqualTo(UPDATED_MUC_TIEU_NGAY);
        assertThat(testProNSCL.getThucHienNgay()).isEqualTo(UPDATED_THUC_HIEN_NGAY);
        assertThat(testProNSCL.getLoiNgay()).isEqualTo(UPDATED_LOI_NGAY);
        assertThat(testProNSCL.getSuaNgay()).isEqualTo(UPDATED_SUA_NGAY);
        assertThat(testProNSCL.getBtpCap()).isEqualTo(UPDATED_BTP_CAP);
        assertThat(testProNSCL.getQty1h()).isEqualTo(UPDATED_QTY_1_H);
        assertThat(testProNSCL.getQty2h()).isEqualTo(UPDATED_QTY_2_H);
        assertThat(testProNSCL.getQty3h()).isEqualTo(UPDATED_QTY_3_H);
        assertThat(testProNSCL.getQty4h()).isEqualTo(UPDATED_QTY_4_H);
        assertThat(testProNSCL.getQty5h()).isEqualTo(UPDATED_QTY_5_H);
        assertThat(testProNSCL.getQty6h()).isEqualTo(UPDATED_QTY_6_H);
        assertThat(testProNSCL.getQty7h()).isEqualTo(UPDATED_QTY_7_H);
        assertThat(testProNSCL.getQty8h()).isEqualTo(UPDATED_QTY_8_H);
        assertThat(testProNSCL.getQty9h()).isEqualTo(UPDATED_QTY_9_H);
        assertThat(testProNSCL.getQty10h()).isEqualTo(UPDATED_QTY_10_H);
        assertThat(testProNSCL.getQty11h()).isEqualTo(UPDATED_QTY_11_H);
        assertThat(testProNSCL.getQty12h()).isEqualTo(UPDATED_QTY_12_H);
        assertThat(testProNSCL.getQty13h()).isEqualTo(UPDATED_QTY_13_H);
        assertThat(testProNSCL.getQty14h()).isEqualTo(UPDATED_QTY_14_H);
        assertThat(testProNSCL.getReject1h()).isEqualTo(UPDATED_REJECT_1_H);
        assertThat(testProNSCL.getReject2h()).isEqualTo(UPDATED_REJECT_2_H);
        assertThat(testProNSCL.getReject3h()).isEqualTo(UPDATED_REJECT_3_H);
        assertThat(testProNSCL.getReject4h()).isEqualTo(UPDATED_REJECT_4_H);
        assertThat(testProNSCL.getReject5h()).isEqualTo(UPDATED_REJECT_5_H);
        assertThat(testProNSCL.getReject6h()).isEqualTo(UPDATED_REJECT_6_H);
        assertThat(testProNSCL.getReject7h()).isEqualTo(UPDATED_REJECT_7_H);
        assertThat(testProNSCL.getReject8h()).isEqualTo(UPDATED_REJECT_8_H);
        assertThat(testProNSCL.getReject9h()).isEqualTo(UPDATED_REJECT_9_H);
        assertThat(testProNSCL.getReject10h()).isEqualTo(UPDATED_REJECT_10_H);
        assertThat(testProNSCL.getReject11h()).isEqualTo(UPDATED_REJECT_11_H);
        assertThat(testProNSCL.getReject12h()).isEqualTo(UPDATED_REJECT_12_H);
        assertThat(testProNSCL.getReject13h()).isEqualTo(UPDATED_REJECT_13_H);
        assertThat(testProNSCL.getReject14h()).isEqualTo(UPDATED_REJECT_14_H);
        assertThat(testProNSCL.getMan1h()).isEqualTo(UPDATED_MAN_1_H);
        assertThat(testProNSCL.getMan2h()).isEqualTo(UPDATED_MAN_2_H);
        assertThat(testProNSCL.getMan3h()).isEqualTo(UPDATED_MAN_3_H);
        assertThat(testProNSCL.getMan4h()).isEqualTo(UPDATED_MAN_4_H);
        assertThat(testProNSCL.getMan5h()).isEqualTo(UPDATED_MAN_5_H);
        assertThat(testProNSCL.getMan6h()).isEqualTo(UPDATED_MAN_6_H);
        assertThat(testProNSCL.getMan7h()).isEqualTo(UPDATED_MAN_7_H);
        assertThat(testProNSCL.getMan8h()).isEqualTo(UPDATED_MAN_8_H);
        assertThat(testProNSCL.getMan9h()).isEqualTo(UPDATED_MAN_9_H);
        assertThat(testProNSCL.getMan10h()).isEqualTo(UPDATED_MAN_10_H);
        assertThat(testProNSCL.getMan11h()).isEqualTo(UPDATED_MAN_11_H);
        assertThat(testProNSCL.getMan12h()).isEqualTo(UPDATED_MAN_12_H);
        assertThat(testProNSCL.getMan13h()).isEqualTo(UPDATED_MAN_13_H);
        assertThat(testProNSCL.getMan14h()).isEqualTo(UPDATED_MAN_14_H);
        assertThat(testProNSCL.getManOnly7Hours()).isEqualTo(UPDATED_MAN_ONLY_7_HOURS);
        assertThat(testProNSCL.getFix1h()).isEqualTo(UPDATED_FIX_1_H);
        assertThat(testProNSCL.getFix2h()).isEqualTo(UPDATED_FIX_2_H);
        assertThat(testProNSCL.getFix3h()).isEqualTo(UPDATED_FIX_3_H);
        assertThat(testProNSCL.getFix4h()).isEqualTo(UPDATED_FIX_4_H);
        assertThat(testProNSCL.getFix5h()).isEqualTo(UPDATED_FIX_5_H);
        assertThat(testProNSCL.getFix6h()).isEqualTo(UPDATED_FIX_6_H);
        assertThat(testProNSCL.getFix7h()).isEqualTo(UPDATED_FIX_7_H);
        assertThat(testProNSCL.getFix8h()).isEqualTo(UPDATED_FIX_8_H);
        assertThat(testProNSCL.getFix9h()).isEqualTo(UPDATED_FIX_9_H);
        assertThat(testProNSCL.getFix10h()).isEqualTo(UPDATED_FIX_10_H);
        assertThat(testProNSCL.getFix11h()).isEqualTo(UPDATED_FIX_11_H);
        assertThat(testProNSCL.getFix12h()).isEqualTo(UPDATED_FIX_12_H);
        assertThat(testProNSCL.getFix13h()).isEqualTo(UPDATED_FIX_13_H);
        assertThat(testProNSCL.getFix14h()).isEqualTo(UPDATED_FIX_14_H);
    }

    @Test
    @Transactional
    public void updateNonExistingProNSCL() throws Exception {
        int databaseSizeBeforeUpdate = proNSCLRepository.findAll().size();

        // Create the ProNSCL
        ProNSCLDTO proNSCLDTO = proNSCLMapper.toDto(proNSCL);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProNSCLMockMvc.perform(put("/api/pro-nscls")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proNSCLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProNSCL in the database
        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProNSCL() throws Exception {
        // Initialize the database
        proNSCLRepository.saveAndFlush(proNSCL);

        int databaseSizeBeforeDelete = proNSCLRepository.findAll().size();

        // Delete the proNSCL
        restProNSCLMockMvc.perform(delete("/api/pro-nscls/{id}", proNSCL.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProNSCL> proNSCLList = proNSCLRepository.findAll();
        assertThat(proNSCLList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
