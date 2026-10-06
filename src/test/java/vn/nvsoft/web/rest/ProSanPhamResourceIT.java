package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.domain.ProKhachHang;
import vn.nvsoft.domain.ProChungLoaiSanPham;
import vn.nvsoft.domain.ProTenMua;
import vn.nvsoft.repository.ProSanPhamRepository;
import vn.nvsoft.service.ProSanPhamService;
import vn.nvsoft.service.dto.ProSanPhamDTO;
import vn.nvsoft.service.mapper.ProSanPhamMapper;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;
import vn.nvsoft.service.dto.ProSanPhamCriteria;
import vn.nvsoft.service.ProSanPhamQueryService;

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
 * Integration tests for the {@link ProSanPhamResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProSanPhamResourceIT {

    private static final String DEFAULT_MA_SAN_PHAM = "AAAAAAAAAA";
    private static final String UPDATED_MA_SAN_PHAM = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DON_HANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_DON_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_PO = "AAAAAAAAAA";
    private static final String UPDATED_PO = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_LUONG_SAN_XUAT = 1;
    private static final Integer UPDATED_SO_LUONG_SAN_XUAT = 2;
    private static final Integer SMALLER_SO_LUONG_SAN_XUAT = 1 - 1;

    private static final String DEFAULT_THONG_SO_VALUE_1 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_1 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_1 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_1 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_1 = true;

    private static final String DEFAULT_THONG_SO_VALUE_2 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_2 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_2 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_2 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_2 = true;

    private static final String DEFAULT_THONG_SO_VALUE_3 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_3 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_3 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_3 = true;

    private static final String DEFAULT_THONG_SO_VALUE_4 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_4 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_4 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_4 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_4 = true;

    private static final String DEFAULT_THONG_SO_VALUE_5 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_5 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_5 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_5 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_5 = true;

    private static final String DEFAULT_THONG_SO_VALUE_6 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_6 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_6 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_6 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_6 = true;

    private static final String DEFAULT_THONG_SO_VALUE_7 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_7 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_7 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_7 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_7 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_7 = true;

    private static final String DEFAULT_THONG_SO_VALUE_8 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_8 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_8 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_8 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_8 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_8 = true;

    private static final String DEFAULT_THONG_SO_VALUE_9 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_9 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_9 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_9 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_9 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_9 = true;

    private static final String DEFAULT_THONG_SO_VALUE_10 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_VALUE_10 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_SO_CAPTION_10 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_SO_CAPTION_10 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THONG_SO_CHECK_10 = false;
    private static final Boolean UPDATED_THONG_SO_CHECK_10 = true;

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_HINH_MAT_TRUOC_URL = "AAAAAAAAAA";
    private static final String UPDATED_HINH_MAT_TRUOC_URL = "BBBBBBBBBB";

    private static final String DEFAULT_HINH_MAT_SAU_URL = "AAAAAAAAAA";
    private static final String UPDATED_HINH_MAT_SAU_URL = "BBBBBBBBBB";

    @Autowired
    private ProSanPhamRepository proSanPhamRepository;

    @Autowired
    private ProSanPhamMapper proSanPhamMapper;

    @Autowired
    private ProSanPhamService proSanPhamService;

    @Autowired
    private ProSanPhamQueryService proSanPhamQueryService;

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

    private MockMvc restProSanPhamMockMvc;

    private ProSanPham proSanPham;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProSanPhamResource proSanPhamResource = new ProSanPhamResource(proSanPhamService, proSanPhamQueryService);
        this.restProSanPhamMockMvc = MockMvcBuilders.standaloneSetup(proSanPhamResource)
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
    public static ProSanPham createEntity(EntityManager em) {
        ProSanPham proSanPham = new ProSanPham()
            .maSanPham(DEFAULT_MA_SAN_PHAM)
            .maDonHang(DEFAULT_MA_DON_HANG)
            .po(DEFAULT_PO)
            .soLuongSanXuat(DEFAULT_SO_LUONG_SAN_XUAT)
            .thongSoValue1(DEFAULT_THONG_SO_VALUE_1)
            .thongSoCaption1(DEFAULT_THONG_SO_CAPTION_1)
            .thongSoCheck1(DEFAULT_THONG_SO_CHECK_1)
            .thongSoValue2(DEFAULT_THONG_SO_VALUE_2)
            .thongSoCaption2(DEFAULT_THONG_SO_CAPTION_2)
            .thongSoCheck2(DEFAULT_THONG_SO_CHECK_2)
            .thongSoValue3(DEFAULT_THONG_SO_VALUE_3)
            .thongSoCaption3(DEFAULT_THONG_SO_CAPTION_3)
            .thongSoCheck3(DEFAULT_THONG_SO_CHECK_3)
            .thongSoValue4(DEFAULT_THONG_SO_VALUE_4)
            .thongSoCaption4(DEFAULT_THONG_SO_CAPTION_4)
            .thongSoCheck4(DEFAULT_THONG_SO_CHECK_4)
            .thongSoValue5(DEFAULT_THONG_SO_VALUE_5)
            .thongSoCaption5(DEFAULT_THONG_SO_CAPTION_5)
            .thongSoCheck5(DEFAULT_THONG_SO_CHECK_5)
            .thongSoValue6(DEFAULT_THONG_SO_VALUE_6)
            .thongSoCaption6(DEFAULT_THONG_SO_CAPTION_6)
            .thongSoCheck6(DEFAULT_THONG_SO_CHECK_6)
            .thongSoValue7(DEFAULT_THONG_SO_VALUE_7)
            .thongSoCaption7(DEFAULT_THONG_SO_CAPTION_7)
            .thongSoCheck7(DEFAULT_THONG_SO_CHECK_7)
            .thongSoValue8(DEFAULT_THONG_SO_VALUE_8)
            .thongSoCaption8(DEFAULT_THONG_SO_CAPTION_8)
            .thongSoCheck8(DEFAULT_THONG_SO_CHECK_8)
            .thongSoValue9(DEFAULT_THONG_SO_VALUE_9)
            .thongSoCaption9(DEFAULT_THONG_SO_CAPTION_9)
            .thongSoCheck9(DEFAULT_THONG_SO_CHECK_9)
            .thongSoValue10(DEFAULT_THONG_SO_VALUE_10)
            .thongSoCaption10(DEFAULT_THONG_SO_CAPTION_10)
            .thongSoCheck10(DEFAULT_THONG_SO_CHECK_10)
            .color(DEFAULT_COLOR)
            .hinhMatTruocURL(DEFAULT_HINH_MAT_TRUOC_URL)
            .hinhMatSauURL(DEFAULT_HINH_MAT_SAU_URL);
        return proSanPham;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProSanPham createUpdatedEntity(EntityManager em) {
        ProSanPham proSanPham = new ProSanPham()
            .maSanPham(UPDATED_MA_SAN_PHAM)
            .maDonHang(UPDATED_MA_DON_HANG)
            .po(UPDATED_PO)
            .soLuongSanXuat(UPDATED_SO_LUONG_SAN_XUAT)
            .thongSoValue1(UPDATED_THONG_SO_VALUE_1)
            .thongSoCaption1(UPDATED_THONG_SO_CAPTION_1)
            .thongSoCheck1(UPDATED_THONG_SO_CHECK_1)
            .thongSoValue2(UPDATED_THONG_SO_VALUE_2)
            .thongSoCaption2(UPDATED_THONG_SO_CAPTION_2)
            .thongSoCheck2(UPDATED_THONG_SO_CHECK_2)
            .thongSoValue3(UPDATED_THONG_SO_VALUE_3)
            .thongSoCaption3(UPDATED_THONG_SO_CAPTION_3)
            .thongSoCheck3(UPDATED_THONG_SO_CHECK_3)
            .thongSoValue4(UPDATED_THONG_SO_VALUE_4)
            .thongSoCaption4(UPDATED_THONG_SO_CAPTION_4)
            .thongSoCheck4(UPDATED_THONG_SO_CHECK_4)
            .thongSoValue5(UPDATED_THONG_SO_VALUE_5)
            .thongSoCaption5(UPDATED_THONG_SO_CAPTION_5)
            .thongSoCheck5(UPDATED_THONG_SO_CHECK_5)
            .thongSoValue6(UPDATED_THONG_SO_VALUE_6)
            .thongSoCaption6(UPDATED_THONG_SO_CAPTION_6)
            .thongSoCheck6(UPDATED_THONG_SO_CHECK_6)
            .thongSoValue7(UPDATED_THONG_SO_VALUE_7)
            .thongSoCaption7(UPDATED_THONG_SO_CAPTION_7)
            .thongSoCheck7(UPDATED_THONG_SO_CHECK_7)
            .thongSoValue8(UPDATED_THONG_SO_VALUE_8)
            .thongSoCaption8(UPDATED_THONG_SO_CAPTION_8)
            .thongSoCheck8(UPDATED_THONG_SO_CHECK_8)
            .thongSoValue9(UPDATED_THONG_SO_VALUE_9)
            .thongSoCaption9(UPDATED_THONG_SO_CAPTION_9)
            .thongSoCheck9(UPDATED_THONG_SO_CHECK_9)
            .thongSoValue10(UPDATED_THONG_SO_VALUE_10)
            .thongSoCaption10(UPDATED_THONG_SO_CAPTION_10)
            .thongSoCheck10(UPDATED_THONG_SO_CHECK_10)
            .color(UPDATED_COLOR)
            .hinhMatTruocURL(UPDATED_HINH_MAT_TRUOC_URL)
            .hinhMatSauURL(UPDATED_HINH_MAT_SAU_URL);
        return proSanPham;
    }

    @BeforeEach
    public void initTest() {
        proSanPham = createEntity(em);
    }

    @Test
    @Transactional
    public void createProSanPham() throws Exception {
        int databaseSizeBeforeCreate = proSanPhamRepository.findAll().size();

        // Create the ProSanPham
        ProSanPhamDTO proSanPhamDTO = proSanPhamMapper.toDto(proSanPham);
        restProSanPhamMockMvc.perform(post("/api/pro-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proSanPhamDTO)))
            .andExpect(status().isCreated());

        // Validate the ProSanPham in the database
        List<ProSanPham> proSanPhamList = proSanPhamRepository.findAll();
        assertThat(proSanPhamList).hasSize(databaseSizeBeforeCreate + 1);
        ProSanPham testProSanPham = proSanPhamList.get(proSanPhamList.size() - 1);
        assertThat(testProSanPham.getMaSanPham()).isEqualTo(DEFAULT_MA_SAN_PHAM);
        assertThat(testProSanPham.getMaDonHang()).isEqualTo(DEFAULT_MA_DON_HANG);
        assertThat(testProSanPham.getPo()).isEqualTo(DEFAULT_PO);
        assertThat(testProSanPham.getSoLuongSanXuat()).isEqualTo(DEFAULT_SO_LUONG_SAN_XUAT);
        assertThat(testProSanPham.getThongSoValue1()).isEqualTo(DEFAULT_THONG_SO_VALUE_1);
        assertThat(testProSanPham.getThongSoCaption1()).isEqualTo(DEFAULT_THONG_SO_CAPTION_1);
        assertThat(testProSanPham.isThongSoCheck1()).isEqualTo(DEFAULT_THONG_SO_CHECK_1);
        assertThat(testProSanPham.getThongSoValue2()).isEqualTo(DEFAULT_THONG_SO_VALUE_2);
        assertThat(testProSanPham.getThongSoCaption2()).isEqualTo(DEFAULT_THONG_SO_CAPTION_2);
        assertThat(testProSanPham.isThongSoCheck2()).isEqualTo(DEFAULT_THONG_SO_CHECK_2);
        assertThat(testProSanPham.getThongSoValue3()).isEqualTo(DEFAULT_THONG_SO_VALUE_3);
        assertThat(testProSanPham.getThongSoCaption3()).isEqualTo(DEFAULT_THONG_SO_CAPTION_3);
        assertThat(testProSanPham.isThongSoCheck3()).isEqualTo(DEFAULT_THONG_SO_CHECK_3);
        assertThat(testProSanPham.getThongSoValue4()).isEqualTo(DEFAULT_THONG_SO_VALUE_4);
        assertThat(testProSanPham.getThongSoCaption4()).isEqualTo(DEFAULT_THONG_SO_CAPTION_4);
        assertThat(testProSanPham.isThongSoCheck4()).isEqualTo(DEFAULT_THONG_SO_CHECK_4);
        assertThat(testProSanPham.getThongSoValue5()).isEqualTo(DEFAULT_THONG_SO_VALUE_5);
        assertThat(testProSanPham.getThongSoCaption5()).isEqualTo(DEFAULT_THONG_SO_CAPTION_5);
        assertThat(testProSanPham.isThongSoCheck5()).isEqualTo(DEFAULT_THONG_SO_CHECK_5);
        assertThat(testProSanPham.getThongSoValue6()).isEqualTo(DEFAULT_THONG_SO_VALUE_6);
        assertThat(testProSanPham.getThongSoCaption6()).isEqualTo(DEFAULT_THONG_SO_CAPTION_6);
        assertThat(testProSanPham.isThongSoCheck6()).isEqualTo(DEFAULT_THONG_SO_CHECK_6);
        assertThat(testProSanPham.getThongSoValue7()).isEqualTo(DEFAULT_THONG_SO_VALUE_7);
        assertThat(testProSanPham.getThongSoCaption7()).isEqualTo(DEFAULT_THONG_SO_CAPTION_7);
        assertThat(testProSanPham.isThongSoCheck7()).isEqualTo(DEFAULT_THONG_SO_CHECK_7);
        assertThat(testProSanPham.getThongSoValue8()).isEqualTo(DEFAULT_THONG_SO_VALUE_8);
        assertThat(testProSanPham.getThongSoCaption8()).isEqualTo(DEFAULT_THONG_SO_CAPTION_8);
        assertThat(testProSanPham.isThongSoCheck8()).isEqualTo(DEFAULT_THONG_SO_CHECK_8);
        assertThat(testProSanPham.getThongSoValue9()).isEqualTo(DEFAULT_THONG_SO_VALUE_9);
        assertThat(testProSanPham.getThongSoCaption9()).isEqualTo(DEFAULT_THONG_SO_CAPTION_9);
        assertThat(testProSanPham.isThongSoCheck9()).isEqualTo(DEFAULT_THONG_SO_CHECK_9);
        assertThat(testProSanPham.getThongSoValue10()).isEqualTo(DEFAULT_THONG_SO_VALUE_10);
        assertThat(testProSanPham.getThongSoCaption10()).isEqualTo(DEFAULT_THONG_SO_CAPTION_10);
        assertThat(testProSanPham.isThongSoCheck10()).isEqualTo(DEFAULT_THONG_SO_CHECK_10);
        assertThat(testProSanPham.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testProSanPham.getHinhMatTruocURL()).isEqualTo(DEFAULT_HINH_MAT_TRUOC_URL);
        assertThat(testProSanPham.getHinhMatSauURL()).isEqualTo(DEFAULT_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void createProSanPhamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proSanPhamRepository.findAll().size();

        // Create the ProSanPham with an existing ID
        proSanPham.setId(1L);
        ProSanPhamDTO proSanPhamDTO = proSanPhamMapper.toDto(proSanPham);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProSanPhamMockMvc.perform(post("/api/pro-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProSanPham in the database
        List<ProSanPham> proSanPhamList = proSanPhamRepository.findAll();
        assertThat(proSanPhamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProSanPhams() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proSanPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].maSanPham").value(hasItem(DEFAULT_MA_SAN_PHAM)))
            .andExpect(jsonPath("$.[*].maDonHang").value(hasItem(DEFAULT_MA_DON_HANG)))
            .andExpect(jsonPath("$.[*].po").value(hasItem(DEFAULT_PO)))
            .andExpect(jsonPath("$.[*].soLuongSanXuat").value(hasItem(DEFAULT_SO_LUONG_SAN_XUAT)))
            .andExpect(jsonPath("$.[*].thongSoValue1").value(hasItem(DEFAULT_THONG_SO_VALUE_1)))
            .andExpect(jsonPath("$.[*].thongSoCaption1").value(hasItem(DEFAULT_THONG_SO_CAPTION_1)))
            .andExpect(jsonPath("$.[*].thongSoCheck1").value(hasItem(DEFAULT_THONG_SO_CHECK_1.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue2").value(hasItem(DEFAULT_THONG_SO_VALUE_2)))
            .andExpect(jsonPath("$.[*].thongSoCaption2").value(hasItem(DEFAULT_THONG_SO_CAPTION_2)))
            .andExpect(jsonPath("$.[*].thongSoCheck2").value(hasItem(DEFAULT_THONG_SO_CHECK_2.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue3").value(hasItem(DEFAULT_THONG_SO_VALUE_3)))
            .andExpect(jsonPath("$.[*].thongSoCaption3").value(hasItem(DEFAULT_THONG_SO_CAPTION_3)))
            .andExpect(jsonPath("$.[*].thongSoCheck3").value(hasItem(DEFAULT_THONG_SO_CHECK_3.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue4").value(hasItem(DEFAULT_THONG_SO_VALUE_4)))
            .andExpect(jsonPath("$.[*].thongSoCaption4").value(hasItem(DEFAULT_THONG_SO_CAPTION_4)))
            .andExpect(jsonPath("$.[*].thongSoCheck4").value(hasItem(DEFAULT_THONG_SO_CHECK_4.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue5").value(hasItem(DEFAULT_THONG_SO_VALUE_5)))
            .andExpect(jsonPath("$.[*].thongSoCaption5").value(hasItem(DEFAULT_THONG_SO_CAPTION_5)))
            .andExpect(jsonPath("$.[*].thongSoCheck5").value(hasItem(DEFAULT_THONG_SO_CHECK_5.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue6").value(hasItem(DEFAULT_THONG_SO_VALUE_6)))
            .andExpect(jsonPath("$.[*].thongSoCaption6").value(hasItem(DEFAULT_THONG_SO_CAPTION_6)))
            .andExpect(jsonPath("$.[*].thongSoCheck6").value(hasItem(DEFAULT_THONG_SO_CHECK_6.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue7").value(hasItem(DEFAULT_THONG_SO_VALUE_7)))
            .andExpect(jsonPath("$.[*].thongSoCaption7").value(hasItem(DEFAULT_THONG_SO_CAPTION_7)))
            .andExpect(jsonPath("$.[*].thongSoCheck7").value(hasItem(DEFAULT_THONG_SO_CHECK_7.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue8").value(hasItem(DEFAULT_THONG_SO_VALUE_8)))
            .andExpect(jsonPath("$.[*].thongSoCaption8").value(hasItem(DEFAULT_THONG_SO_CAPTION_8)))
            .andExpect(jsonPath("$.[*].thongSoCheck8").value(hasItem(DEFAULT_THONG_SO_CHECK_8.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue9").value(hasItem(DEFAULT_THONG_SO_VALUE_9)))
            .andExpect(jsonPath("$.[*].thongSoCaption9").value(hasItem(DEFAULT_THONG_SO_CAPTION_9)))
            .andExpect(jsonPath("$.[*].thongSoCheck9").value(hasItem(DEFAULT_THONG_SO_CHECK_9.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue10").value(hasItem(DEFAULT_THONG_SO_VALUE_10)))
            .andExpect(jsonPath("$.[*].thongSoCaption10").value(hasItem(DEFAULT_THONG_SO_CAPTION_10)))
            .andExpect(jsonPath("$.[*].thongSoCheck10").value(hasItem(DEFAULT_THONG_SO_CHECK_10.booleanValue())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].hinhMatTruocURL").value(hasItem(DEFAULT_HINH_MAT_TRUOC_URL)))
            .andExpect(jsonPath("$.[*].hinhMatSauURL").value(hasItem(DEFAULT_HINH_MAT_SAU_URL)));
    }
    
    @Test
    @Transactional
    public void getProSanPham() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get the proSanPham
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams/{id}", proSanPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proSanPham.getId().intValue()))
            .andExpect(jsonPath("$.maSanPham").value(DEFAULT_MA_SAN_PHAM))
            .andExpect(jsonPath("$.maDonHang").value(DEFAULT_MA_DON_HANG))
            .andExpect(jsonPath("$.po").value(DEFAULT_PO))
            .andExpect(jsonPath("$.soLuongSanXuat").value(DEFAULT_SO_LUONG_SAN_XUAT))
            .andExpect(jsonPath("$.thongSoValue1").value(DEFAULT_THONG_SO_VALUE_1))
            .andExpect(jsonPath("$.thongSoCaption1").value(DEFAULT_THONG_SO_CAPTION_1))
            .andExpect(jsonPath("$.thongSoCheck1").value(DEFAULT_THONG_SO_CHECK_1.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue2").value(DEFAULT_THONG_SO_VALUE_2))
            .andExpect(jsonPath("$.thongSoCaption2").value(DEFAULT_THONG_SO_CAPTION_2))
            .andExpect(jsonPath("$.thongSoCheck2").value(DEFAULT_THONG_SO_CHECK_2.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue3").value(DEFAULT_THONG_SO_VALUE_3))
            .andExpect(jsonPath("$.thongSoCaption3").value(DEFAULT_THONG_SO_CAPTION_3))
            .andExpect(jsonPath("$.thongSoCheck3").value(DEFAULT_THONG_SO_CHECK_3.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue4").value(DEFAULT_THONG_SO_VALUE_4))
            .andExpect(jsonPath("$.thongSoCaption4").value(DEFAULT_THONG_SO_CAPTION_4))
            .andExpect(jsonPath("$.thongSoCheck4").value(DEFAULT_THONG_SO_CHECK_4.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue5").value(DEFAULT_THONG_SO_VALUE_5))
            .andExpect(jsonPath("$.thongSoCaption5").value(DEFAULT_THONG_SO_CAPTION_5))
            .andExpect(jsonPath("$.thongSoCheck5").value(DEFAULT_THONG_SO_CHECK_5.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue6").value(DEFAULT_THONG_SO_VALUE_6))
            .andExpect(jsonPath("$.thongSoCaption6").value(DEFAULT_THONG_SO_CAPTION_6))
            .andExpect(jsonPath("$.thongSoCheck6").value(DEFAULT_THONG_SO_CHECK_6.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue7").value(DEFAULT_THONG_SO_VALUE_7))
            .andExpect(jsonPath("$.thongSoCaption7").value(DEFAULT_THONG_SO_CAPTION_7))
            .andExpect(jsonPath("$.thongSoCheck7").value(DEFAULT_THONG_SO_CHECK_7.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue8").value(DEFAULT_THONG_SO_VALUE_8))
            .andExpect(jsonPath("$.thongSoCaption8").value(DEFAULT_THONG_SO_CAPTION_8))
            .andExpect(jsonPath("$.thongSoCheck8").value(DEFAULT_THONG_SO_CHECK_8.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue9").value(DEFAULT_THONG_SO_VALUE_9))
            .andExpect(jsonPath("$.thongSoCaption9").value(DEFAULT_THONG_SO_CAPTION_9))
            .andExpect(jsonPath("$.thongSoCheck9").value(DEFAULT_THONG_SO_CHECK_9.booleanValue()))
            .andExpect(jsonPath("$.thongSoValue10").value(DEFAULT_THONG_SO_VALUE_10))
            .andExpect(jsonPath("$.thongSoCaption10").value(DEFAULT_THONG_SO_CAPTION_10))
            .andExpect(jsonPath("$.thongSoCheck10").value(DEFAULT_THONG_SO_CHECK_10.booleanValue()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.hinhMatTruocURL").value(DEFAULT_HINH_MAT_TRUOC_URL))
            .andExpect(jsonPath("$.hinhMatSauURL").value(DEFAULT_HINH_MAT_SAU_URL));
    }


    @Test
    @Transactional
    public void getProSanPhamsByIdFiltering() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        Long id = proSanPham.getId();

        defaultProSanPhamShouldBeFound("id.equals=" + id);
        defaultProSanPhamShouldNotBeFound("id.notEquals=" + id);

        defaultProSanPhamShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProSanPhamShouldNotBeFound("id.greaterThan=" + id);

        defaultProSanPhamShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProSanPhamShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham equals to DEFAULT_MA_SAN_PHAM
        defaultProSanPhamShouldBeFound("maSanPham.equals=" + DEFAULT_MA_SAN_PHAM);

        // Get all the proSanPhamList where maSanPham equals to UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldNotBeFound("maSanPham.equals=" + UPDATED_MA_SAN_PHAM);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham not equals to DEFAULT_MA_SAN_PHAM
        defaultProSanPhamShouldNotBeFound("maSanPham.notEquals=" + DEFAULT_MA_SAN_PHAM);

        // Get all the proSanPhamList where maSanPham not equals to UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldBeFound("maSanPham.notEquals=" + UPDATED_MA_SAN_PHAM);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham in DEFAULT_MA_SAN_PHAM or UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldBeFound("maSanPham.in=" + DEFAULT_MA_SAN_PHAM + "," + UPDATED_MA_SAN_PHAM);

        // Get all the proSanPhamList where maSanPham equals to UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldNotBeFound("maSanPham.in=" + UPDATED_MA_SAN_PHAM);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham is not null
        defaultProSanPhamShouldBeFound("maSanPham.specified=true");

        // Get all the proSanPhamList where maSanPham is null
        defaultProSanPhamShouldNotBeFound("maSanPham.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham contains DEFAULT_MA_SAN_PHAM
        defaultProSanPhamShouldBeFound("maSanPham.contains=" + DEFAULT_MA_SAN_PHAM);

        // Get all the proSanPhamList where maSanPham contains UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldNotBeFound("maSanPham.contains=" + UPDATED_MA_SAN_PHAM);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaSanPhamNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maSanPham does not contain DEFAULT_MA_SAN_PHAM
        defaultProSanPhamShouldNotBeFound("maSanPham.doesNotContain=" + DEFAULT_MA_SAN_PHAM);

        // Get all the proSanPhamList where maSanPham does not contain UPDATED_MA_SAN_PHAM
        defaultProSanPhamShouldBeFound("maSanPham.doesNotContain=" + UPDATED_MA_SAN_PHAM);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang equals to DEFAULT_MA_DON_HANG
        defaultProSanPhamShouldBeFound("maDonHang.equals=" + DEFAULT_MA_DON_HANG);

        // Get all the proSanPhamList where maDonHang equals to UPDATED_MA_DON_HANG
        defaultProSanPhamShouldNotBeFound("maDonHang.equals=" + UPDATED_MA_DON_HANG);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang not equals to DEFAULT_MA_DON_HANG
        defaultProSanPhamShouldNotBeFound("maDonHang.notEquals=" + DEFAULT_MA_DON_HANG);

        // Get all the proSanPhamList where maDonHang not equals to UPDATED_MA_DON_HANG
        defaultProSanPhamShouldBeFound("maDonHang.notEquals=" + UPDATED_MA_DON_HANG);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang in DEFAULT_MA_DON_HANG or UPDATED_MA_DON_HANG
        defaultProSanPhamShouldBeFound("maDonHang.in=" + DEFAULT_MA_DON_HANG + "," + UPDATED_MA_DON_HANG);

        // Get all the proSanPhamList where maDonHang equals to UPDATED_MA_DON_HANG
        defaultProSanPhamShouldNotBeFound("maDonHang.in=" + UPDATED_MA_DON_HANG);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang is not null
        defaultProSanPhamShouldBeFound("maDonHang.specified=true");

        // Get all the proSanPhamList where maDonHang is null
        defaultProSanPhamShouldNotBeFound("maDonHang.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang contains DEFAULT_MA_DON_HANG
        defaultProSanPhamShouldBeFound("maDonHang.contains=" + DEFAULT_MA_DON_HANG);

        // Get all the proSanPhamList where maDonHang contains UPDATED_MA_DON_HANG
        defaultProSanPhamShouldNotBeFound("maDonHang.contains=" + UPDATED_MA_DON_HANG);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByMaDonHangNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where maDonHang does not contain DEFAULT_MA_DON_HANG
        defaultProSanPhamShouldNotBeFound("maDonHang.doesNotContain=" + DEFAULT_MA_DON_HANG);

        // Get all the proSanPhamList where maDonHang does not contain UPDATED_MA_DON_HANG
        defaultProSanPhamShouldBeFound("maDonHang.doesNotContain=" + UPDATED_MA_DON_HANG);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByPoIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po equals to DEFAULT_PO
        defaultProSanPhamShouldBeFound("po.equals=" + DEFAULT_PO);

        // Get all the proSanPhamList where po equals to UPDATED_PO
        defaultProSanPhamShouldNotBeFound("po.equals=" + UPDATED_PO);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByPoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po not equals to DEFAULT_PO
        defaultProSanPhamShouldNotBeFound("po.notEquals=" + DEFAULT_PO);

        // Get all the proSanPhamList where po not equals to UPDATED_PO
        defaultProSanPhamShouldBeFound("po.notEquals=" + UPDATED_PO);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByPoIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po in DEFAULT_PO or UPDATED_PO
        defaultProSanPhamShouldBeFound("po.in=" + DEFAULT_PO + "," + UPDATED_PO);

        // Get all the proSanPhamList where po equals to UPDATED_PO
        defaultProSanPhamShouldNotBeFound("po.in=" + UPDATED_PO);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByPoIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po is not null
        defaultProSanPhamShouldBeFound("po.specified=true");

        // Get all the proSanPhamList where po is null
        defaultProSanPhamShouldNotBeFound("po.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByPoContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po contains DEFAULT_PO
        defaultProSanPhamShouldBeFound("po.contains=" + DEFAULT_PO);

        // Get all the proSanPhamList where po contains UPDATED_PO
        defaultProSanPhamShouldNotBeFound("po.contains=" + UPDATED_PO);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByPoNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where po does not contain DEFAULT_PO
        defaultProSanPhamShouldNotBeFound("po.doesNotContain=" + DEFAULT_PO);

        // Get all the proSanPhamList where po does not contain UPDATED_PO
        defaultProSanPhamShouldBeFound("po.doesNotContain=" + UPDATED_PO);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat equals to DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.equals=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat equals to UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.equals=" + UPDATED_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat not equals to DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.notEquals=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat not equals to UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.notEquals=" + UPDATED_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat in DEFAULT_SO_LUONG_SAN_XUAT or UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.in=" + DEFAULT_SO_LUONG_SAN_XUAT + "," + UPDATED_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat equals to UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.in=" + UPDATED_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat is not null
        defaultProSanPhamShouldBeFound("soLuongSanXuat.specified=true");

        // Get all the proSanPhamList where soLuongSanXuat is null
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat is greater than or equal to DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.greaterThanOrEqual=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat is greater than or equal to UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.greaterThanOrEqual=" + UPDATED_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat is less than or equal to DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.lessThanOrEqual=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat is less than or equal to SMALLER_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.lessThanOrEqual=" + SMALLER_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsLessThanSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat is less than DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.lessThan=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat is less than UPDATED_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.lessThan=" + UPDATED_SO_LUONG_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsBySoLuongSanXuatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where soLuongSanXuat is greater than DEFAULT_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldNotBeFound("soLuongSanXuat.greaterThan=" + DEFAULT_SO_LUONG_SAN_XUAT);

        // Get all the proSanPhamList where soLuongSanXuat is greater than SMALLER_SO_LUONG_SAN_XUAT
        defaultProSanPhamShouldBeFound("soLuongSanXuat.greaterThan=" + SMALLER_SO_LUONG_SAN_XUAT);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 equals to DEFAULT_THONG_SO_VALUE_1
        defaultProSanPhamShouldBeFound("thongSoValue1.equals=" + DEFAULT_THONG_SO_VALUE_1);

        // Get all the proSanPhamList where thongSoValue1 equals to UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldNotBeFound("thongSoValue1.equals=" + UPDATED_THONG_SO_VALUE_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 not equals to DEFAULT_THONG_SO_VALUE_1
        defaultProSanPhamShouldNotBeFound("thongSoValue1.notEquals=" + DEFAULT_THONG_SO_VALUE_1);

        // Get all the proSanPhamList where thongSoValue1 not equals to UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldBeFound("thongSoValue1.notEquals=" + UPDATED_THONG_SO_VALUE_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 in DEFAULT_THONG_SO_VALUE_1 or UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldBeFound("thongSoValue1.in=" + DEFAULT_THONG_SO_VALUE_1 + "," + UPDATED_THONG_SO_VALUE_1);

        // Get all the proSanPhamList where thongSoValue1 equals to UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldNotBeFound("thongSoValue1.in=" + UPDATED_THONG_SO_VALUE_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 is not null
        defaultProSanPhamShouldBeFound("thongSoValue1.specified=true");

        // Get all the proSanPhamList where thongSoValue1 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue1.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 contains DEFAULT_THONG_SO_VALUE_1
        defaultProSanPhamShouldBeFound("thongSoValue1.contains=" + DEFAULT_THONG_SO_VALUE_1);

        // Get all the proSanPhamList where thongSoValue1 contains UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldNotBeFound("thongSoValue1.contains=" + UPDATED_THONG_SO_VALUE_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue1NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue1 does not contain DEFAULT_THONG_SO_VALUE_1
        defaultProSanPhamShouldNotBeFound("thongSoValue1.doesNotContain=" + DEFAULT_THONG_SO_VALUE_1);

        // Get all the proSanPhamList where thongSoValue1 does not contain UPDATED_THONG_SO_VALUE_1
        defaultProSanPhamShouldBeFound("thongSoValue1.doesNotContain=" + UPDATED_THONG_SO_VALUE_1);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 equals to DEFAULT_THONG_SO_CAPTION_1
        defaultProSanPhamShouldBeFound("thongSoCaption1.equals=" + DEFAULT_THONG_SO_CAPTION_1);

        // Get all the proSanPhamList where thongSoCaption1 equals to UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.equals=" + UPDATED_THONG_SO_CAPTION_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 not equals to DEFAULT_THONG_SO_CAPTION_1
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.notEquals=" + DEFAULT_THONG_SO_CAPTION_1);

        // Get all the proSanPhamList where thongSoCaption1 not equals to UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldBeFound("thongSoCaption1.notEquals=" + UPDATED_THONG_SO_CAPTION_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 in DEFAULT_THONG_SO_CAPTION_1 or UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldBeFound("thongSoCaption1.in=" + DEFAULT_THONG_SO_CAPTION_1 + "," + UPDATED_THONG_SO_CAPTION_1);

        // Get all the proSanPhamList where thongSoCaption1 equals to UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.in=" + UPDATED_THONG_SO_CAPTION_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption1.specified=true");

        // Get all the proSanPhamList where thongSoCaption1 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 contains DEFAULT_THONG_SO_CAPTION_1
        defaultProSanPhamShouldBeFound("thongSoCaption1.contains=" + DEFAULT_THONG_SO_CAPTION_1);

        // Get all the proSanPhamList where thongSoCaption1 contains UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.contains=" + UPDATED_THONG_SO_CAPTION_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption1NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption1 does not contain DEFAULT_THONG_SO_CAPTION_1
        defaultProSanPhamShouldNotBeFound("thongSoCaption1.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_1);

        // Get all the proSanPhamList where thongSoCaption1 does not contain UPDATED_THONG_SO_CAPTION_1
        defaultProSanPhamShouldBeFound("thongSoCaption1.doesNotContain=" + UPDATED_THONG_SO_CAPTION_1);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck1IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck1 equals to DEFAULT_THONG_SO_CHECK_1
        defaultProSanPhamShouldBeFound("thongSoCheck1.equals=" + DEFAULT_THONG_SO_CHECK_1);

        // Get all the proSanPhamList where thongSoCheck1 equals to UPDATED_THONG_SO_CHECK_1
        defaultProSanPhamShouldNotBeFound("thongSoCheck1.equals=" + UPDATED_THONG_SO_CHECK_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck1 not equals to DEFAULT_THONG_SO_CHECK_1
        defaultProSanPhamShouldNotBeFound("thongSoCheck1.notEquals=" + DEFAULT_THONG_SO_CHECK_1);

        // Get all the proSanPhamList where thongSoCheck1 not equals to UPDATED_THONG_SO_CHECK_1
        defaultProSanPhamShouldBeFound("thongSoCheck1.notEquals=" + UPDATED_THONG_SO_CHECK_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck1IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck1 in DEFAULT_THONG_SO_CHECK_1 or UPDATED_THONG_SO_CHECK_1
        defaultProSanPhamShouldBeFound("thongSoCheck1.in=" + DEFAULT_THONG_SO_CHECK_1 + "," + UPDATED_THONG_SO_CHECK_1);

        // Get all the proSanPhamList where thongSoCheck1 equals to UPDATED_THONG_SO_CHECK_1
        defaultProSanPhamShouldNotBeFound("thongSoCheck1.in=" + UPDATED_THONG_SO_CHECK_1);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck1IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck1 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck1.specified=true");

        // Get all the proSanPhamList where thongSoCheck1 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck1.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 equals to DEFAULT_THONG_SO_VALUE_2
        defaultProSanPhamShouldBeFound("thongSoValue2.equals=" + DEFAULT_THONG_SO_VALUE_2);

        // Get all the proSanPhamList where thongSoValue2 equals to UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldNotBeFound("thongSoValue2.equals=" + UPDATED_THONG_SO_VALUE_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 not equals to DEFAULT_THONG_SO_VALUE_2
        defaultProSanPhamShouldNotBeFound("thongSoValue2.notEquals=" + DEFAULT_THONG_SO_VALUE_2);

        // Get all the proSanPhamList where thongSoValue2 not equals to UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldBeFound("thongSoValue2.notEquals=" + UPDATED_THONG_SO_VALUE_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 in DEFAULT_THONG_SO_VALUE_2 or UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldBeFound("thongSoValue2.in=" + DEFAULT_THONG_SO_VALUE_2 + "," + UPDATED_THONG_SO_VALUE_2);

        // Get all the proSanPhamList where thongSoValue2 equals to UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldNotBeFound("thongSoValue2.in=" + UPDATED_THONG_SO_VALUE_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 is not null
        defaultProSanPhamShouldBeFound("thongSoValue2.specified=true");

        // Get all the proSanPhamList where thongSoValue2 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue2.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 contains DEFAULT_THONG_SO_VALUE_2
        defaultProSanPhamShouldBeFound("thongSoValue2.contains=" + DEFAULT_THONG_SO_VALUE_2);

        // Get all the proSanPhamList where thongSoValue2 contains UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldNotBeFound("thongSoValue2.contains=" + UPDATED_THONG_SO_VALUE_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue2NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue2 does not contain DEFAULT_THONG_SO_VALUE_2
        defaultProSanPhamShouldNotBeFound("thongSoValue2.doesNotContain=" + DEFAULT_THONG_SO_VALUE_2);

        // Get all the proSanPhamList where thongSoValue2 does not contain UPDATED_THONG_SO_VALUE_2
        defaultProSanPhamShouldBeFound("thongSoValue2.doesNotContain=" + UPDATED_THONG_SO_VALUE_2);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 equals to DEFAULT_THONG_SO_CAPTION_2
        defaultProSanPhamShouldBeFound("thongSoCaption2.equals=" + DEFAULT_THONG_SO_CAPTION_2);

        // Get all the proSanPhamList where thongSoCaption2 equals to UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.equals=" + UPDATED_THONG_SO_CAPTION_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 not equals to DEFAULT_THONG_SO_CAPTION_2
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.notEquals=" + DEFAULT_THONG_SO_CAPTION_2);

        // Get all the proSanPhamList where thongSoCaption2 not equals to UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldBeFound("thongSoCaption2.notEquals=" + UPDATED_THONG_SO_CAPTION_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 in DEFAULT_THONG_SO_CAPTION_2 or UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldBeFound("thongSoCaption2.in=" + DEFAULT_THONG_SO_CAPTION_2 + "," + UPDATED_THONG_SO_CAPTION_2);

        // Get all the proSanPhamList where thongSoCaption2 equals to UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.in=" + UPDATED_THONG_SO_CAPTION_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption2.specified=true");

        // Get all the proSanPhamList where thongSoCaption2 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 contains DEFAULT_THONG_SO_CAPTION_2
        defaultProSanPhamShouldBeFound("thongSoCaption2.contains=" + DEFAULT_THONG_SO_CAPTION_2);

        // Get all the proSanPhamList where thongSoCaption2 contains UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.contains=" + UPDATED_THONG_SO_CAPTION_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption2NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption2 does not contain DEFAULT_THONG_SO_CAPTION_2
        defaultProSanPhamShouldNotBeFound("thongSoCaption2.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_2);

        // Get all the proSanPhamList where thongSoCaption2 does not contain UPDATED_THONG_SO_CAPTION_2
        defaultProSanPhamShouldBeFound("thongSoCaption2.doesNotContain=" + UPDATED_THONG_SO_CAPTION_2);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck2IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck2 equals to DEFAULT_THONG_SO_CHECK_2
        defaultProSanPhamShouldBeFound("thongSoCheck2.equals=" + DEFAULT_THONG_SO_CHECK_2);

        // Get all the proSanPhamList where thongSoCheck2 equals to UPDATED_THONG_SO_CHECK_2
        defaultProSanPhamShouldNotBeFound("thongSoCheck2.equals=" + UPDATED_THONG_SO_CHECK_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck2 not equals to DEFAULT_THONG_SO_CHECK_2
        defaultProSanPhamShouldNotBeFound("thongSoCheck2.notEquals=" + DEFAULT_THONG_SO_CHECK_2);

        // Get all the proSanPhamList where thongSoCheck2 not equals to UPDATED_THONG_SO_CHECK_2
        defaultProSanPhamShouldBeFound("thongSoCheck2.notEquals=" + UPDATED_THONG_SO_CHECK_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck2IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck2 in DEFAULT_THONG_SO_CHECK_2 or UPDATED_THONG_SO_CHECK_2
        defaultProSanPhamShouldBeFound("thongSoCheck2.in=" + DEFAULT_THONG_SO_CHECK_2 + "," + UPDATED_THONG_SO_CHECK_2);

        // Get all the proSanPhamList where thongSoCheck2 equals to UPDATED_THONG_SO_CHECK_2
        defaultProSanPhamShouldNotBeFound("thongSoCheck2.in=" + UPDATED_THONG_SO_CHECK_2);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck2IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck2 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck2.specified=true");

        // Get all the proSanPhamList where thongSoCheck2 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck2.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 equals to DEFAULT_THONG_SO_VALUE_3
        defaultProSanPhamShouldBeFound("thongSoValue3.equals=" + DEFAULT_THONG_SO_VALUE_3);

        // Get all the proSanPhamList where thongSoValue3 equals to UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldNotBeFound("thongSoValue3.equals=" + UPDATED_THONG_SO_VALUE_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 not equals to DEFAULT_THONG_SO_VALUE_3
        defaultProSanPhamShouldNotBeFound("thongSoValue3.notEquals=" + DEFAULT_THONG_SO_VALUE_3);

        // Get all the proSanPhamList where thongSoValue3 not equals to UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldBeFound("thongSoValue3.notEquals=" + UPDATED_THONG_SO_VALUE_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 in DEFAULT_THONG_SO_VALUE_3 or UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldBeFound("thongSoValue3.in=" + DEFAULT_THONG_SO_VALUE_3 + "," + UPDATED_THONG_SO_VALUE_3);

        // Get all the proSanPhamList where thongSoValue3 equals to UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldNotBeFound("thongSoValue3.in=" + UPDATED_THONG_SO_VALUE_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 is not null
        defaultProSanPhamShouldBeFound("thongSoValue3.specified=true");

        // Get all the proSanPhamList where thongSoValue3 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue3.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 contains DEFAULT_THONG_SO_VALUE_3
        defaultProSanPhamShouldBeFound("thongSoValue3.contains=" + DEFAULT_THONG_SO_VALUE_3);

        // Get all the proSanPhamList where thongSoValue3 contains UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldNotBeFound("thongSoValue3.contains=" + UPDATED_THONG_SO_VALUE_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue3NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue3 does not contain DEFAULT_THONG_SO_VALUE_3
        defaultProSanPhamShouldNotBeFound("thongSoValue3.doesNotContain=" + DEFAULT_THONG_SO_VALUE_3);

        // Get all the proSanPhamList where thongSoValue3 does not contain UPDATED_THONG_SO_VALUE_3
        defaultProSanPhamShouldBeFound("thongSoValue3.doesNotContain=" + UPDATED_THONG_SO_VALUE_3);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 equals to DEFAULT_THONG_SO_CAPTION_3
        defaultProSanPhamShouldBeFound("thongSoCaption3.equals=" + DEFAULT_THONG_SO_CAPTION_3);

        // Get all the proSanPhamList where thongSoCaption3 equals to UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.equals=" + UPDATED_THONG_SO_CAPTION_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 not equals to DEFAULT_THONG_SO_CAPTION_3
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.notEquals=" + DEFAULT_THONG_SO_CAPTION_3);

        // Get all the proSanPhamList where thongSoCaption3 not equals to UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldBeFound("thongSoCaption3.notEquals=" + UPDATED_THONG_SO_CAPTION_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 in DEFAULT_THONG_SO_CAPTION_3 or UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldBeFound("thongSoCaption3.in=" + DEFAULT_THONG_SO_CAPTION_3 + "," + UPDATED_THONG_SO_CAPTION_3);

        // Get all the proSanPhamList where thongSoCaption3 equals to UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.in=" + UPDATED_THONG_SO_CAPTION_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption3.specified=true");

        // Get all the proSanPhamList where thongSoCaption3 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 contains DEFAULT_THONG_SO_CAPTION_3
        defaultProSanPhamShouldBeFound("thongSoCaption3.contains=" + DEFAULT_THONG_SO_CAPTION_3);

        // Get all the proSanPhamList where thongSoCaption3 contains UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.contains=" + UPDATED_THONG_SO_CAPTION_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption3NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption3 does not contain DEFAULT_THONG_SO_CAPTION_3
        defaultProSanPhamShouldNotBeFound("thongSoCaption3.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_3);

        // Get all the proSanPhamList where thongSoCaption3 does not contain UPDATED_THONG_SO_CAPTION_3
        defaultProSanPhamShouldBeFound("thongSoCaption3.doesNotContain=" + UPDATED_THONG_SO_CAPTION_3);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck3IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck3 equals to DEFAULT_THONG_SO_CHECK_3
        defaultProSanPhamShouldBeFound("thongSoCheck3.equals=" + DEFAULT_THONG_SO_CHECK_3);

        // Get all the proSanPhamList where thongSoCheck3 equals to UPDATED_THONG_SO_CHECK_3
        defaultProSanPhamShouldNotBeFound("thongSoCheck3.equals=" + UPDATED_THONG_SO_CHECK_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck3 not equals to DEFAULT_THONG_SO_CHECK_3
        defaultProSanPhamShouldNotBeFound("thongSoCheck3.notEquals=" + DEFAULT_THONG_SO_CHECK_3);

        // Get all the proSanPhamList where thongSoCheck3 not equals to UPDATED_THONG_SO_CHECK_3
        defaultProSanPhamShouldBeFound("thongSoCheck3.notEquals=" + UPDATED_THONG_SO_CHECK_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck3IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck3 in DEFAULT_THONG_SO_CHECK_3 or UPDATED_THONG_SO_CHECK_3
        defaultProSanPhamShouldBeFound("thongSoCheck3.in=" + DEFAULT_THONG_SO_CHECK_3 + "," + UPDATED_THONG_SO_CHECK_3);

        // Get all the proSanPhamList where thongSoCheck3 equals to UPDATED_THONG_SO_CHECK_3
        defaultProSanPhamShouldNotBeFound("thongSoCheck3.in=" + UPDATED_THONG_SO_CHECK_3);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck3IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck3 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck3.specified=true");

        // Get all the proSanPhamList where thongSoCheck3 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck3.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 equals to DEFAULT_THONG_SO_VALUE_4
        defaultProSanPhamShouldBeFound("thongSoValue4.equals=" + DEFAULT_THONG_SO_VALUE_4);

        // Get all the proSanPhamList where thongSoValue4 equals to UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldNotBeFound("thongSoValue4.equals=" + UPDATED_THONG_SO_VALUE_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 not equals to DEFAULT_THONG_SO_VALUE_4
        defaultProSanPhamShouldNotBeFound("thongSoValue4.notEquals=" + DEFAULT_THONG_SO_VALUE_4);

        // Get all the proSanPhamList where thongSoValue4 not equals to UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldBeFound("thongSoValue4.notEquals=" + UPDATED_THONG_SO_VALUE_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 in DEFAULT_THONG_SO_VALUE_4 or UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldBeFound("thongSoValue4.in=" + DEFAULT_THONG_SO_VALUE_4 + "," + UPDATED_THONG_SO_VALUE_4);

        // Get all the proSanPhamList where thongSoValue4 equals to UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldNotBeFound("thongSoValue4.in=" + UPDATED_THONG_SO_VALUE_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 is not null
        defaultProSanPhamShouldBeFound("thongSoValue4.specified=true");

        // Get all the proSanPhamList where thongSoValue4 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue4.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 contains DEFAULT_THONG_SO_VALUE_4
        defaultProSanPhamShouldBeFound("thongSoValue4.contains=" + DEFAULT_THONG_SO_VALUE_4);

        // Get all the proSanPhamList where thongSoValue4 contains UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldNotBeFound("thongSoValue4.contains=" + UPDATED_THONG_SO_VALUE_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue4NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue4 does not contain DEFAULT_THONG_SO_VALUE_4
        defaultProSanPhamShouldNotBeFound("thongSoValue4.doesNotContain=" + DEFAULT_THONG_SO_VALUE_4);

        // Get all the proSanPhamList where thongSoValue4 does not contain UPDATED_THONG_SO_VALUE_4
        defaultProSanPhamShouldBeFound("thongSoValue4.doesNotContain=" + UPDATED_THONG_SO_VALUE_4);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 equals to DEFAULT_THONG_SO_CAPTION_4
        defaultProSanPhamShouldBeFound("thongSoCaption4.equals=" + DEFAULT_THONG_SO_CAPTION_4);

        // Get all the proSanPhamList where thongSoCaption4 equals to UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.equals=" + UPDATED_THONG_SO_CAPTION_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 not equals to DEFAULT_THONG_SO_CAPTION_4
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.notEquals=" + DEFAULT_THONG_SO_CAPTION_4);

        // Get all the proSanPhamList where thongSoCaption4 not equals to UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldBeFound("thongSoCaption4.notEquals=" + UPDATED_THONG_SO_CAPTION_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 in DEFAULT_THONG_SO_CAPTION_4 or UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldBeFound("thongSoCaption4.in=" + DEFAULT_THONG_SO_CAPTION_4 + "," + UPDATED_THONG_SO_CAPTION_4);

        // Get all the proSanPhamList where thongSoCaption4 equals to UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.in=" + UPDATED_THONG_SO_CAPTION_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption4.specified=true");

        // Get all the proSanPhamList where thongSoCaption4 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 contains DEFAULT_THONG_SO_CAPTION_4
        defaultProSanPhamShouldBeFound("thongSoCaption4.contains=" + DEFAULT_THONG_SO_CAPTION_4);

        // Get all the proSanPhamList where thongSoCaption4 contains UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.contains=" + UPDATED_THONG_SO_CAPTION_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption4NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption4 does not contain DEFAULT_THONG_SO_CAPTION_4
        defaultProSanPhamShouldNotBeFound("thongSoCaption4.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_4);

        // Get all the proSanPhamList where thongSoCaption4 does not contain UPDATED_THONG_SO_CAPTION_4
        defaultProSanPhamShouldBeFound("thongSoCaption4.doesNotContain=" + UPDATED_THONG_SO_CAPTION_4);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck4IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck4 equals to DEFAULT_THONG_SO_CHECK_4
        defaultProSanPhamShouldBeFound("thongSoCheck4.equals=" + DEFAULT_THONG_SO_CHECK_4);

        // Get all the proSanPhamList where thongSoCheck4 equals to UPDATED_THONG_SO_CHECK_4
        defaultProSanPhamShouldNotBeFound("thongSoCheck4.equals=" + UPDATED_THONG_SO_CHECK_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck4 not equals to DEFAULT_THONG_SO_CHECK_4
        defaultProSanPhamShouldNotBeFound("thongSoCheck4.notEquals=" + DEFAULT_THONG_SO_CHECK_4);

        // Get all the proSanPhamList where thongSoCheck4 not equals to UPDATED_THONG_SO_CHECK_4
        defaultProSanPhamShouldBeFound("thongSoCheck4.notEquals=" + UPDATED_THONG_SO_CHECK_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck4IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck4 in DEFAULT_THONG_SO_CHECK_4 or UPDATED_THONG_SO_CHECK_4
        defaultProSanPhamShouldBeFound("thongSoCheck4.in=" + DEFAULT_THONG_SO_CHECK_4 + "," + UPDATED_THONG_SO_CHECK_4);

        // Get all the proSanPhamList where thongSoCheck4 equals to UPDATED_THONG_SO_CHECK_4
        defaultProSanPhamShouldNotBeFound("thongSoCheck4.in=" + UPDATED_THONG_SO_CHECK_4);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck4IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck4 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck4.specified=true");

        // Get all the proSanPhamList where thongSoCheck4 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck4.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 equals to DEFAULT_THONG_SO_VALUE_5
        defaultProSanPhamShouldBeFound("thongSoValue5.equals=" + DEFAULT_THONG_SO_VALUE_5);

        // Get all the proSanPhamList where thongSoValue5 equals to UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldNotBeFound("thongSoValue5.equals=" + UPDATED_THONG_SO_VALUE_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 not equals to DEFAULT_THONG_SO_VALUE_5
        defaultProSanPhamShouldNotBeFound("thongSoValue5.notEquals=" + DEFAULT_THONG_SO_VALUE_5);

        // Get all the proSanPhamList where thongSoValue5 not equals to UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldBeFound("thongSoValue5.notEquals=" + UPDATED_THONG_SO_VALUE_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 in DEFAULT_THONG_SO_VALUE_5 or UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldBeFound("thongSoValue5.in=" + DEFAULT_THONG_SO_VALUE_5 + "," + UPDATED_THONG_SO_VALUE_5);

        // Get all the proSanPhamList where thongSoValue5 equals to UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldNotBeFound("thongSoValue5.in=" + UPDATED_THONG_SO_VALUE_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 is not null
        defaultProSanPhamShouldBeFound("thongSoValue5.specified=true");

        // Get all the proSanPhamList where thongSoValue5 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue5.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 contains DEFAULT_THONG_SO_VALUE_5
        defaultProSanPhamShouldBeFound("thongSoValue5.contains=" + DEFAULT_THONG_SO_VALUE_5);

        // Get all the proSanPhamList where thongSoValue5 contains UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldNotBeFound("thongSoValue5.contains=" + UPDATED_THONG_SO_VALUE_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue5NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue5 does not contain DEFAULT_THONG_SO_VALUE_5
        defaultProSanPhamShouldNotBeFound("thongSoValue5.doesNotContain=" + DEFAULT_THONG_SO_VALUE_5);

        // Get all the proSanPhamList where thongSoValue5 does not contain UPDATED_THONG_SO_VALUE_5
        defaultProSanPhamShouldBeFound("thongSoValue5.doesNotContain=" + UPDATED_THONG_SO_VALUE_5);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 equals to DEFAULT_THONG_SO_CAPTION_5
        defaultProSanPhamShouldBeFound("thongSoCaption5.equals=" + DEFAULT_THONG_SO_CAPTION_5);

        // Get all the proSanPhamList where thongSoCaption5 equals to UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.equals=" + UPDATED_THONG_SO_CAPTION_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 not equals to DEFAULT_THONG_SO_CAPTION_5
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.notEquals=" + DEFAULT_THONG_SO_CAPTION_5);

        // Get all the proSanPhamList where thongSoCaption5 not equals to UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldBeFound("thongSoCaption5.notEquals=" + UPDATED_THONG_SO_CAPTION_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 in DEFAULT_THONG_SO_CAPTION_5 or UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldBeFound("thongSoCaption5.in=" + DEFAULT_THONG_SO_CAPTION_5 + "," + UPDATED_THONG_SO_CAPTION_5);

        // Get all the proSanPhamList where thongSoCaption5 equals to UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.in=" + UPDATED_THONG_SO_CAPTION_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption5.specified=true");

        // Get all the proSanPhamList where thongSoCaption5 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 contains DEFAULT_THONG_SO_CAPTION_5
        defaultProSanPhamShouldBeFound("thongSoCaption5.contains=" + DEFAULT_THONG_SO_CAPTION_5);

        // Get all the proSanPhamList where thongSoCaption5 contains UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.contains=" + UPDATED_THONG_SO_CAPTION_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption5NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption5 does not contain DEFAULT_THONG_SO_CAPTION_5
        defaultProSanPhamShouldNotBeFound("thongSoCaption5.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_5);

        // Get all the proSanPhamList where thongSoCaption5 does not contain UPDATED_THONG_SO_CAPTION_5
        defaultProSanPhamShouldBeFound("thongSoCaption5.doesNotContain=" + UPDATED_THONG_SO_CAPTION_5);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck5IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck5 equals to DEFAULT_THONG_SO_CHECK_5
        defaultProSanPhamShouldBeFound("thongSoCheck5.equals=" + DEFAULT_THONG_SO_CHECK_5);

        // Get all the proSanPhamList where thongSoCheck5 equals to UPDATED_THONG_SO_CHECK_5
        defaultProSanPhamShouldNotBeFound("thongSoCheck5.equals=" + UPDATED_THONG_SO_CHECK_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck5 not equals to DEFAULT_THONG_SO_CHECK_5
        defaultProSanPhamShouldNotBeFound("thongSoCheck5.notEquals=" + DEFAULT_THONG_SO_CHECK_5);

        // Get all the proSanPhamList where thongSoCheck5 not equals to UPDATED_THONG_SO_CHECK_5
        defaultProSanPhamShouldBeFound("thongSoCheck5.notEquals=" + UPDATED_THONG_SO_CHECK_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck5IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck5 in DEFAULT_THONG_SO_CHECK_5 or UPDATED_THONG_SO_CHECK_5
        defaultProSanPhamShouldBeFound("thongSoCheck5.in=" + DEFAULT_THONG_SO_CHECK_5 + "," + UPDATED_THONG_SO_CHECK_5);

        // Get all the proSanPhamList where thongSoCheck5 equals to UPDATED_THONG_SO_CHECK_5
        defaultProSanPhamShouldNotBeFound("thongSoCheck5.in=" + UPDATED_THONG_SO_CHECK_5);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck5IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck5 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck5.specified=true");

        // Get all the proSanPhamList where thongSoCheck5 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck5.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 equals to DEFAULT_THONG_SO_VALUE_6
        defaultProSanPhamShouldBeFound("thongSoValue6.equals=" + DEFAULT_THONG_SO_VALUE_6);

        // Get all the proSanPhamList where thongSoValue6 equals to UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldNotBeFound("thongSoValue6.equals=" + UPDATED_THONG_SO_VALUE_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 not equals to DEFAULT_THONG_SO_VALUE_6
        defaultProSanPhamShouldNotBeFound("thongSoValue6.notEquals=" + DEFAULT_THONG_SO_VALUE_6);

        // Get all the proSanPhamList where thongSoValue6 not equals to UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldBeFound("thongSoValue6.notEquals=" + UPDATED_THONG_SO_VALUE_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 in DEFAULT_THONG_SO_VALUE_6 or UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldBeFound("thongSoValue6.in=" + DEFAULT_THONG_SO_VALUE_6 + "," + UPDATED_THONG_SO_VALUE_6);

        // Get all the proSanPhamList where thongSoValue6 equals to UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldNotBeFound("thongSoValue6.in=" + UPDATED_THONG_SO_VALUE_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 is not null
        defaultProSanPhamShouldBeFound("thongSoValue6.specified=true");

        // Get all the proSanPhamList where thongSoValue6 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue6.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 contains DEFAULT_THONG_SO_VALUE_6
        defaultProSanPhamShouldBeFound("thongSoValue6.contains=" + DEFAULT_THONG_SO_VALUE_6);

        // Get all the proSanPhamList where thongSoValue6 contains UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldNotBeFound("thongSoValue6.contains=" + UPDATED_THONG_SO_VALUE_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue6NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue6 does not contain DEFAULT_THONG_SO_VALUE_6
        defaultProSanPhamShouldNotBeFound("thongSoValue6.doesNotContain=" + DEFAULT_THONG_SO_VALUE_6);

        // Get all the proSanPhamList where thongSoValue6 does not contain UPDATED_THONG_SO_VALUE_6
        defaultProSanPhamShouldBeFound("thongSoValue6.doesNotContain=" + UPDATED_THONG_SO_VALUE_6);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 equals to DEFAULT_THONG_SO_CAPTION_6
        defaultProSanPhamShouldBeFound("thongSoCaption6.equals=" + DEFAULT_THONG_SO_CAPTION_6);

        // Get all the proSanPhamList where thongSoCaption6 equals to UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.equals=" + UPDATED_THONG_SO_CAPTION_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 not equals to DEFAULT_THONG_SO_CAPTION_6
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.notEquals=" + DEFAULT_THONG_SO_CAPTION_6);

        // Get all the proSanPhamList where thongSoCaption6 not equals to UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldBeFound("thongSoCaption6.notEquals=" + UPDATED_THONG_SO_CAPTION_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 in DEFAULT_THONG_SO_CAPTION_6 or UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldBeFound("thongSoCaption6.in=" + DEFAULT_THONG_SO_CAPTION_6 + "," + UPDATED_THONG_SO_CAPTION_6);

        // Get all the proSanPhamList where thongSoCaption6 equals to UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.in=" + UPDATED_THONG_SO_CAPTION_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption6.specified=true");

        // Get all the proSanPhamList where thongSoCaption6 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 contains DEFAULT_THONG_SO_CAPTION_6
        defaultProSanPhamShouldBeFound("thongSoCaption6.contains=" + DEFAULT_THONG_SO_CAPTION_6);

        // Get all the proSanPhamList where thongSoCaption6 contains UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.contains=" + UPDATED_THONG_SO_CAPTION_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption6NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption6 does not contain DEFAULT_THONG_SO_CAPTION_6
        defaultProSanPhamShouldNotBeFound("thongSoCaption6.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_6);

        // Get all the proSanPhamList where thongSoCaption6 does not contain UPDATED_THONG_SO_CAPTION_6
        defaultProSanPhamShouldBeFound("thongSoCaption6.doesNotContain=" + UPDATED_THONG_SO_CAPTION_6);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck6IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck6 equals to DEFAULT_THONG_SO_CHECK_6
        defaultProSanPhamShouldBeFound("thongSoCheck6.equals=" + DEFAULT_THONG_SO_CHECK_6);

        // Get all the proSanPhamList where thongSoCheck6 equals to UPDATED_THONG_SO_CHECK_6
        defaultProSanPhamShouldNotBeFound("thongSoCheck6.equals=" + UPDATED_THONG_SO_CHECK_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck6 not equals to DEFAULT_THONG_SO_CHECK_6
        defaultProSanPhamShouldNotBeFound("thongSoCheck6.notEquals=" + DEFAULT_THONG_SO_CHECK_6);

        // Get all the proSanPhamList where thongSoCheck6 not equals to UPDATED_THONG_SO_CHECK_6
        defaultProSanPhamShouldBeFound("thongSoCheck6.notEquals=" + UPDATED_THONG_SO_CHECK_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck6IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck6 in DEFAULT_THONG_SO_CHECK_6 or UPDATED_THONG_SO_CHECK_6
        defaultProSanPhamShouldBeFound("thongSoCheck6.in=" + DEFAULT_THONG_SO_CHECK_6 + "," + UPDATED_THONG_SO_CHECK_6);

        // Get all the proSanPhamList where thongSoCheck6 equals to UPDATED_THONG_SO_CHECK_6
        defaultProSanPhamShouldNotBeFound("thongSoCheck6.in=" + UPDATED_THONG_SO_CHECK_6);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck6IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck6 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck6.specified=true");

        // Get all the proSanPhamList where thongSoCheck6 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck6.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 equals to DEFAULT_THONG_SO_VALUE_7
        defaultProSanPhamShouldBeFound("thongSoValue7.equals=" + DEFAULT_THONG_SO_VALUE_7);

        // Get all the proSanPhamList where thongSoValue7 equals to UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldNotBeFound("thongSoValue7.equals=" + UPDATED_THONG_SO_VALUE_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 not equals to DEFAULT_THONG_SO_VALUE_7
        defaultProSanPhamShouldNotBeFound("thongSoValue7.notEquals=" + DEFAULT_THONG_SO_VALUE_7);

        // Get all the proSanPhamList where thongSoValue7 not equals to UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldBeFound("thongSoValue7.notEquals=" + UPDATED_THONG_SO_VALUE_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 in DEFAULT_THONG_SO_VALUE_7 or UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldBeFound("thongSoValue7.in=" + DEFAULT_THONG_SO_VALUE_7 + "," + UPDATED_THONG_SO_VALUE_7);

        // Get all the proSanPhamList where thongSoValue7 equals to UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldNotBeFound("thongSoValue7.in=" + UPDATED_THONG_SO_VALUE_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 is not null
        defaultProSanPhamShouldBeFound("thongSoValue7.specified=true");

        // Get all the proSanPhamList where thongSoValue7 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue7.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 contains DEFAULT_THONG_SO_VALUE_7
        defaultProSanPhamShouldBeFound("thongSoValue7.contains=" + DEFAULT_THONG_SO_VALUE_7);

        // Get all the proSanPhamList where thongSoValue7 contains UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldNotBeFound("thongSoValue7.contains=" + UPDATED_THONG_SO_VALUE_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue7NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue7 does not contain DEFAULT_THONG_SO_VALUE_7
        defaultProSanPhamShouldNotBeFound("thongSoValue7.doesNotContain=" + DEFAULT_THONG_SO_VALUE_7);

        // Get all the proSanPhamList where thongSoValue7 does not contain UPDATED_THONG_SO_VALUE_7
        defaultProSanPhamShouldBeFound("thongSoValue7.doesNotContain=" + UPDATED_THONG_SO_VALUE_7);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 equals to DEFAULT_THONG_SO_CAPTION_7
        defaultProSanPhamShouldBeFound("thongSoCaption7.equals=" + DEFAULT_THONG_SO_CAPTION_7);

        // Get all the proSanPhamList where thongSoCaption7 equals to UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.equals=" + UPDATED_THONG_SO_CAPTION_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 not equals to DEFAULT_THONG_SO_CAPTION_7
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.notEquals=" + DEFAULT_THONG_SO_CAPTION_7);

        // Get all the proSanPhamList where thongSoCaption7 not equals to UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldBeFound("thongSoCaption7.notEquals=" + UPDATED_THONG_SO_CAPTION_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 in DEFAULT_THONG_SO_CAPTION_7 or UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldBeFound("thongSoCaption7.in=" + DEFAULT_THONG_SO_CAPTION_7 + "," + UPDATED_THONG_SO_CAPTION_7);

        // Get all the proSanPhamList where thongSoCaption7 equals to UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.in=" + UPDATED_THONG_SO_CAPTION_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption7.specified=true");

        // Get all the proSanPhamList where thongSoCaption7 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 contains DEFAULT_THONG_SO_CAPTION_7
        defaultProSanPhamShouldBeFound("thongSoCaption7.contains=" + DEFAULT_THONG_SO_CAPTION_7);

        // Get all the proSanPhamList where thongSoCaption7 contains UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.contains=" + UPDATED_THONG_SO_CAPTION_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption7NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption7 does not contain DEFAULT_THONG_SO_CAPTION_7
        defaultProSanPhamShouldNotBeFound("thongSoCaption7.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_7);

        // Get all the proSanPhamList where thongSoCaption7 does not contain UPDATED_THONG_SO_CAPTION_7
        defaultProSanPhamShouldBeFound("thongSoCaption7.doesNotContain=" + UPDATED_THONG_SO_CAPTION_7);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck7IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck7 equals to DEFAULT_THONG_SO_CHECK_7
        defaultProSanPhamShouldBeFound("thongSoCheck7.equals=" + DEFAULT_THONG_SO_CHECK_7);

        // Get all the proSanPhamList where thongSoCheck7 equals to UPDATED_THONG_SO_CHECK_7
        defaultProSanPhamShouldNotBeFound("thongSoCheck7.equals=" + UPDATED_THONG_SO_CHECK_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck7 not equals to DEFAULT_THONG_SO_CHECK_7
        defaultProSanPhamShouldNotBeFound("thongSoCheck7.notEquals=" + DEFAULT_THONG_SO_CHECK_7);

        // Get all the proSanPhamList where thongSoCheck7 not equals to UPDATED_THONG_SO_CHECK_7
        defaultProSanPhamShouldBeFound("thongSoCheck7.notEquals=" + UPDATED_THONG_SO_CHECK_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck7IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck7 in DEFAULT_THONG_SO_CHECK_7 or UPDATED_THONG_SO_CHECK_7
        defaultProSanPhamShouldBeFound("thongSoCheck7.in=" + DEFAULT_THONG_SO_CHECK_7 + "," + UPDATED_THONG_SO_CHECK_7);

        // Get all the proSanPhamList where thongSoCheck7 equals to UPDATED_THONG_SO_CHECK_7
        defaultProSanPhamShouldNotBeFound("thongSoCheck7.in=" + UPDATED_THONG_SO_CHECK_7);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck7IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck7 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck7.specified=true");

        // Get all the proSanPhamList where thongSoCheck7 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck7.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 equals to DEFAULT_THONG_SO_VALUE_8
        defaultProSanPhamShouldBeFound("thongSoValue8.equals=" + DEFAULT_THONG_SO_VALUE_8);

        // Get all the proSanPhamList where thongSoValue8 equals to UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldNotBeFound("thongSoValue8.equals=" + UPDATED_THONG_SO_VALUE_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 not equals to DEFAULT_THONG_SO_VALUE_8
        defaultProSanPhamShouldNotBeFound("thongSoValue8.notEquals=" + DEFAULT_THONG_SO_VALUE_8);

        // Get all the proSanPhamList where thongSoValue8 not equals to UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldBeFound("thongSoValue8.notEquals=" + UPDATED_THONG_SO_VALUE_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 in DEFAULT_THONG_SO_VALUE_8 or UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldBeFound("thongSoValue8.in=" + DEFAULT_THONG_SO_VALUE_8 + "," + UPDATED_THONG_SO_VALUE_8);

        // Get all the proSanPhamList where thongSoValue8 equals to UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldNotBeFound("thongSoValue8.in=" + UPDATED_THONG_SO_VALUE_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 is not null
        defaultProSanPhamShouldBeFound("thongSoValue8.specified=true");

        // Get all the proSanPhamList where thongSoValue8 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue8.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 contains DEFAULT_THONG_SO_VALUE_8
        defaultProSanPhamShouldBeFound("thongSoValue8.contains=" + DEFAULT_THONG_SO_VALUE_8);

        // Get all the proSanPhamList where thongSoValue8 contains UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldNotBeFound("thongSoValue8.contains=" + UPDATED_THONG_SO_VALUE_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue8NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue8 does not contain DEFAULT_THONG_SO_VALUE_8
        defaultProSanPhamShouldNotBeFound("thongSoValue8.doesNotContain=" + DEFAULT_THONG_SO_VALUE_8);

        // Get all the proSanPhamList where thongSoValue8 does not contain UPDATED_THONG_SO_VALUE_8
        defaultProSanPhamShouldBeFound("thongSoValue8.doesNotContain=" + UPDATED_THONG_SO_VALUE_8);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 equals to DEFAULT_THONG_SO_CAPTION_8
        defaultProSanPhamShouldBeFound("thongSoCaption8.equals=" + DEFAULT_THONG_SO_CAPTION_8);

        // Get all the proSanPhamList where thongSoCaption8 equals to UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.equals=" + UPDATED_THONG_SO_CAPTION_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 not equals to DEFAULT_THONG_SO_CAPTION_8
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.notEquals=" + DEFAULT_THONG_SO_CAPTION_8);

        // Get all the proSanPhamList where thongSoCaption8 not equals to UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldBeFound("thongSoCaption8.notEquals=" + UPDATED_THONG_SO_CAPTION_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 in DEFAULT_THONG_SO_CAPTION_8 or UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldBeFound("thongSoCaption8.in=" + DEFAULT_THONG_SO_CAPTION_8 + "," + UPDATED_THONG_SO_CAPTION_8);

        // Get all the proSanPhamList where thongSoCaption8 equals to UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.in=" + UPDATED_THONG_SO_CAPTION_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption8.specified=true");

        // Get all the proSanPhamList where thongSoCaption8 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 contains DEFAULT_THONG_SO_CAPTION_8
        defaultProSanPhamShouldBeFound("thongSoCaption8.contains=" + DEFAULT_THONG_SO_CAPTION_8);

        // Get all the proSanPhamList where thongSoCaption8 contains UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.contains=" + UPDATED_THONG_SO_CAPTION_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption8NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption8 does not contain DEFAULT_THONG_SO_CAPTION_8
        defaultProSanPhamShouldNotBeFound("thongSoCaption8.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_8);

        // Get all the proSanPhamList where thongSoCaption8 does not contain UPDATED_THONG_SO_CAPTION_8
        defaultProSanPhamShouldBeFound("thongSoCaption8.doesNotContain=" + UPDATED_THONG_SO_CAPTION_8);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck8IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck8 equals to DEFAULT_THONG_SO_CHECK_8
        defaultProSanPhamShouldBeFound("thongSoCheck8.equals=" + DEFAULT_THONG_SO_CHECK_8);

        // Get all the proSanPhamList where thongSoCheck8 equals to UPDATED_THONG_SO_CHECK_8
        defaultProSanPhamShouldNotBeFound("thongSoCheck8.equals=" + UPDATED_THONG_SO_CHECK_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck8 not equals to DEFAULT_THONG_SO_CHECK_8
        defaultProSanPhamShouldNotBeFound("thongSoCheck8.notEquals=" + DEFAULT_THONG_SO_CHECK_8);

        // Get all the proSanPhamList where thongSoCheck8 not equals to UPDATED_THONG_SO_CHECK_8
        defaultProSanPhamShouldBeFound("thongSoCheck8.notEquals=" + UPDATED_THONG_SO_CHECK_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck8IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck8 in DEFAULT_THONG_SO_CHECK_8 or UPDATED_THONG_SO_CHECK_8
        defaultProSanPhamShouldBeFound("thongSoCheck8.in=" + DEFAULT_THONG_SO_CHECK_8 + "," + UPDATED_THONG_SO_CHECK_8);

        // Get all the proSanPhamList where thongSoCheck8 equals to UPDATED_THONG_SO_CHECK_8
        defaultProSanPhamShouldNotBeFound("thongSoCheck8.in=" + UPDATED_THONG_SO_CHECK_8);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck8IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck8 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck8.specified=true");

        // Get all the proSanPhamList where thongSoCheck8 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck8.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 equals to DEFAULT_THONG_SO_VALUE_9
        defaultProSanPhamShouldBeFound("thongSoValue9.equals=" + DEFAULT_THONG_SO_VALUE_9);

        // Get all the proSanPhamList where thongSoValue9 equals to UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldNotBeFound("thongSoValue9.equals=" + UPDATED_THONG_SO_VALUE_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 not equals to DEFAULT_THONG_SO_VALUE_9
        defaultProSanPhamShouldNotBeFound("thongSoValue9.notEquals=" + DEFAULT_THONG_SO_VALUE_9);

        // Get all the proSanPhamList where thongSoValue9 not equals to UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldBeFound("thongSoValue9.notEquals=" + UPDATED_THONG_SO_VALUE_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 in DEFAULT_THONG_SO_VALUE_9 or UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldBeFound("thongSoValue9.in=" + DEFAULT_THONG_SO_VALUE_9 + "," + UPDATED_THONG_SO_VALUE_9);

        // Get all the proSanPhamList where thongSoValue9 equals to UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldNotBeFound("thongSoValue9.in=" + UPDATED_THONG_SO_VALUE_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 is not null
        defaultProSanPhamShouldBeFound("thongSoValue9.specified=true");

        // Get all the proSanPhamList where thongSoValue9 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue9.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 contains DEFAULT_THONG_SO_VALUE_9
        defaultProSanPhamShouldBeFound("thongSoValue9.contains=" + DEFAULT_THONG_SO_VALUE_9);

        // Get all the proSanPhamList where thongSoValue9 contains UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldNotBeFound("thongSoValue9.contains=" + UPDATED_THONG_SO_VALUE_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue9NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue9 does not contain DEFAULT_THONG_SO_VALUE_9
        defaultProSanPhamShouldNotBeFound("thongSoValue9.doesNotContain=" + DEFAULT_THONG_SO_VALUE_9);

        // Get all the proSanPhamList where thongSoValue9 does not contain UPDATED_THONG_SO_VALUE_9
        defaultProSanPhamShouldBeFound("thongSoValue9.doesNotContain=" + UPDATED_THONG_SO_VALUE_9);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 equals to DEFAULT_THONG_SO_CAPTION_9
        defaultProSanPhamShouldBeFound("thongSoCaption9.equals=" + DEFAULT_THONG_SO_CAPTION_9);

        // Get all the proSanPhamList where thongSoCaption9 equals to UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.equals=" + UPDATED_THONG_SO_CAPTION_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 not equals to DEFAULT_THONG_SO_CAPTION_9
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.notEquals=" + DEFAULT_THONG_SO_CAPTION_9);

        // Get all the proSanPhamList where thongSoCaption9 not equals to UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldBeFound("thongSoCaption9.notEquals=" + UPDATED_THONG_SO_CAPTION_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 in DEFAULT_THONG_SO_CAPTION_9 or UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldBeFound("thongSoCaption9.in=" + DEFAULT_THONG_SO_CAPTION_9 + "," + UPDATED_THONG_SO_CAPTION_9);

        // Get all the proSanPhamList where thongSoCaption9 equals to UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.in=" + UPDATED_THONG_SO_CAPTION_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption9.specified=true");

        // Get all the proSanPhamList where thongSoCaption9 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 contains DEFAULT_THONG_SO_CAPTION_9
        defaultProSanPhamShouldBeFound("thongSoCaption9.contains=" + DEFAULT_THONG_SO_CAPTION_9);

        // Get all the proSanPhamList where thongSoCaption9 contains UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.contains=" + UPDATED_THONG_SO_CAPTION_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption9NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption9 does not contain DEFAULT_THONG_SO_CAPTION_9
        defaultProSanPhamShouldNotBeFound("thongSoCaption9.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_9);

        // Get all the proSanPhamList where thongSoCaption9 does not contain UPDATED_THONG_SO_CAPTION_9
        defaultProSanPhamShouldBeFound("thongSoCaption9.doesNotContain=" + UPDATED_THONG_SO_CAPTION_9);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck9IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck9 equals to DEFAULT_THONG_SO_CHECK_9
        defaultProSanPhamShouldBeFound("thongSoCheck9.equals=" + DEFAULT_THONG_SO_CHECK_9);

        // Get all the proSanPhamList where thongSoCheck9 equals to UPDATED_THONG_SO_CHECK_9
        defaultProSanPhamShouldNotBeFound("thongSoCheck9.equals=" + UPDATED_THONG_SO_CHECK_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck9 not equals to DEFAULT_THONG_SO_CHECK_9
        defaultProSanPhamShouldNotBeFound("thongSoCheck9.notEquals=" + DEFAULT_THONG_SO_CHECK_9);

        // Get all the proSanPhamList where thongSoCheck9 not equals to UPDATED_THONG_SO_CHECK_9
        defaultProSanPhamShouldBeFound("thongSoCheck9.notEquals=" + UPDATED_THONG_SO_CHECK_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck9IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck9 in DEFAULT_THONG_SO_CHECK_9 or UPDATED_THONG_SO_CHECK_9
        defaultProSanPhamShouldBeFound("thongSoCheck9.in=" + DEFAULT_THONG_SO_CHECK_9 + "," + UPDATED_THONG_SO_CHECK_9);

        // Get all the proSanPhamList where thongSoCheck9 equals to UPDATED_THONG_SO_CHECK_9
        defaultProSanPhamShouldNotBeFound("thongSoCheck9.in=" + UPDATED_THONG_SO_CHECK_9);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck9IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck9 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck9.specified=true");

        // Get all the proSanPhamList where thongSoCheck9 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck9.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 equals to DEFAULT_THONG_SO_VALUE_10
        defaultProSanPhamShouldBeFound("thongSoValue10.equals=" + DEFAULT_THONG_SO_VALUE_10);

        // Get all the proSanPhamList where thongSoValue10 equals to UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldNotBeFound("thongSoValue10.equals=" + UPDATED_THONG_SO_VALUE_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 not equals to DEFAULT_THONG_SO_VALUE_10
        defaultProSanPhamShouldNotBeFound("thongSoValue10.notEquals=" + DEFAULT_THONG_SO_VALUE_10);

        // Get all the proSanPhamList where thongSoValue10 not equals to UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldBeFound("thongSoValue10.notEquals=" + UPDATED_THONG_SO_VALUE_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 in DEFAULT_THONG_SO_VALUE_10 or UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldBeFound("thongSoValue10.in=" + DEFAULT_THONG_SO_VALUE_10 + "," + UPDATED_THONG_SO_VALUE_10);

        // Get all the proSanPhamList where thongSoValue10 equals to UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldNotBeFound("thongSoValue10.in=" + UPDATED_THONG_SO_VALUE_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 is not null
        defaultProSanPhamShouldBeFound("thongSoValue10.specified=true");

        // Get all the proSanPhamList where thongSoValue10 is null
        defaultProSanPhamShouldNotBeFound("thongSoValue10.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 contains DEFAULT_THONG_SO_VALUE_10
        defaultProSanPhamShouldBeFound("thongSoValue10.contains=" + DEFAULT_THONG_SO_VALUE_10);

        // Get all the proSanPhamList where thongSoValue10 contains UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldNotBeFound("thongSoValue10.contains=" + UPDATED_THONG_SO_VALUE_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoValue10NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoValue10 does not contain DEFAULT_THONG_SO_VALUE_10
        defaultProSanPhamShouldNotBeFound("thongSoValue10.doesNotContain=" + DEFAULT_THONG_SO_VALUE_10);

        // Get all the proSanPhamList where thongSoValue10 does not contain UPDATED_THONG_SO_VALUE_10
        defaultProSanPhamShouldBeFound("thongSoValue10.doesNotContain=" + UPDATED_THONG_SO_VALUE_10);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 equals to DEFAULT_THONG_SO_CAPTION_10
        defaultProSanPhamShouldBeFound("thongSoCaption10.equals=" + DEFAULT_THONG_SO_CAPTION_10);

        // Get all the proSanPhamList where thongSoCaption10 equals to UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.equals=" + UPDATED_THONG_SO_CAPTION_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 not equals to DEFAULT_THONG_SO_CAPTION_10
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.notEquals=" + DEFAULT_THONG_SO_CAPTION_10);

        // Get all the proSanPhamList where thongSoCaption10 not equals to UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldBeFound("thongSoCaption10.notEquals=" + UPDATED_THONG_SO_CAPTION_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 in DEFAULT_THONG_SO_CAPTION_10 or UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldBeFound("thongSoCaption10.in=" + DEFAULT_THONG_SO_CAPTION_10 + "," + UPDATED_THONG_SO_CAPTION_10);

        // Get all the proSanPhamList where thongSoCaption10 equals to UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.in=" + UPDATED_THONG_SO_CAPTION_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 is not null
        defaultProSanPhamShouldBeFound("thongSoCaption10.specified=true");

        // Get all the proSanPhamList where thongSoCaption10 is null
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10ContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 contains DEFAULT_THONG_SO_CAPTION_10
        defaultProSanPhamShouldBeFound("thongSoCaption10.contains=" + DEFAULT_THONG_SO_CAPTION_10);

        // Get all the proSanPhamList where thongSoCaption10 contains UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.contains=" + UPDATED_THONG_SO_CAPTION_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCaption10NotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCaption10 does not contain DEFAULT_THONG_SO_CAPTION_10
        defaultProSanPhamShouldNotBeFound("thongSoCaption10.doesNotContain=" + DEFAULT_THONG_SO_CAPTION_10);

        // Get all the proSanPhamList where thongSoCaption10 does not contain UPDATED_THONG_SO_CAPTION_10
        defaultProSanPhamShouldBeFound("thongSoCaption10.doesNotContain=" + UPDATED_THONG_SO_CAPTION_10);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck10IsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck10 equals to DEFAULT_THONG_SO_CHECK_10
        defaultProSanPhamShouldBeFound("thongSoCheck10.equals=" + DEFAULT_THONG_SO_CHECK_10);

        // Get all the proSanPhamList where thongSoCheck10 equals to UPDATED_THONG_SO_CHECK_10
        defaultProSanPhamShouldNotBeFound("thongSoCheck10.equals=" + UPDATED_THONG_SO_CHECK_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck10 not equals to DEFAULT_THONG_SO_CHECK_10
        defaultProSanPhamShouldNotBeFound("thongSoCheck10.notEquals=" + DEFAULT_THONG_SO_CHECK_10);

        // Get all the proSanPhamList where thongSoCheck10 not equals to UPDATED_THONG_SO_CHECK_10
        defaultProSanPhamShouldBeFound("thongSoCheck10.notEquals=" + UPDATED_THONG_SO_CHECK_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck10IsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck10 in DEFAULT_THONG_SO_CHECK_10 or UPDATED_THONG_SO_CHECK_10
        defaultProSanPhamShouldBeFound("thongSoCheck10.in=" + DEFAULT_THONG_SO_CHECK_10 + "," + UPDATED_THONG_SO_CHECK_10);

        // Get all the proSanPhamList where thongSoCheck10 equals to UPDATED_THONG_SO_CHECK_10
        defaultProSanPhamShouldNotBeFound("thongSoCheck10.in=" + UPDATED_THONG_SO_CHECK_10);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByThongSoCheck10IsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where thongSoCheck10 is not null
        defaultProSanPhamShouldBeFound("thongSoCheck10.specified=true");

        // Get all the proSanPhamList where thongSoCheck10 is null
        defaultProSanPhamShouldNotBeFound("thongSoCheck10.specified=false");
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByColorIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color equals to DEFAULT_COLOR
        defaultProSanPhamShouldBeFound("color.equals=" + DEFAULT_COLOR);

        // Get all the proSanPhamList where color equals to UPDATED_COLOR
        defaultProSanPhamShouldNotBeFound("color.equals=" + UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByColorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color not equals to DEFAULT_COLOR
        defaultProSanPhamShouldNotBeFound("color.notEquals=" + DEFAULT_COLOR);

        // Get all the proSanPhamList where color not equals to UPDATED_COLOR
        defaultProSanPhamShouldBeFound("color.notEquals=" + UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByColorIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color in DEFAULT_COLOR or UPDATED_COLOR
        defaultProSanPhamShouldBeFound("color.in=" + DEFAULT_COLOR + "," + UPDATED_COLOR);

        // Get all the proSanPhamList where color equals to UPDATED_COLOR
        defaultProSanPhamShouldNotBeFound("color.in=" + UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByColorIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color is not null
        defaultProSanPhamShouldBeFound("color.specified=true");

        // Get all the proSanPhamList where color is null
        defaultProSanPhamShouldNotBeFound("color.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByColorContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color contains DEFAULT_COLOR
        defaultProSanPhamShouldBeFound("color.contains=" + DEFAULT_COLOR);

        // Get all the proSanPhamList where color contains UPDATED_COLOR
        defaultProSanPhamShouldNotBeFound("color.contains=" + UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByColorNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where color does not contain DEFAULT_COLOR
        defaultProSanPhamShouldNotBeFound("color.doesNotContain=" + DEFAULT_COLOR);

        // Get all the proSanPhamList where color does not contain UPDATED_COLOR
        defaultProSanPhamShouldBeFound("color.doesNotContain=" + UPDATED_COLOR);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL equals to DEFAULT_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.equals=" + DEFAULT_HINH_MAT_TRUOC_URL);

        // Get all the proSanPhamList where hinhMatTruocURL equals to UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.equals=" + UPDATED_HINH_MAT_TRUOC_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL not equals to DEFAULT_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.notEquals=" + DEFAULT_HINH_MAT_TRUOC_URL);

        // Get all the proSanPhamList where hinhMatTruocURL not equals to UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.notEquals=" + UPDATED_HINH_MAT_TRUOC_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL in DEFAULT_HINH_MAT_TRUOC_URL or UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.in=" + DEFAULT_HINH_MAT_TRUOC_URL + "," + UPDATED_HINH_MAT_TRUOC_URL);

        // Get all the proSanPhamList where hinhMatTruocURL equals to UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.in=" + UPDATED_HINH_MAT_TRUOC_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL is not null
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.specified=true");

        // Get all the proSanPhamList where hinhMatTruocURL is null
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL contains DEFAULT_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.contains=" + DEFAULT_HINH_MAT_TRUOC_URL);

        // Get all the proSanPhamList where hinhMatTruocURL contains UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.contains=" + UPDATED_HINH_MAT_TRUOC_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatTruocURLNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatTruocURL does not contain DEFAULT_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldNotBeFound("hinhMatTruocURL.doesNotContain=" + DEFAULT_HINH_MAT_TRUOC_URL);

        // Get all the proSanPhamList where hinhMatTruocURL does not contain UPDATED_HINH_MAT_TRUOC_URL
        defaultProSanPhamShouldBeFound("hinhMatTruocURL.doesNotContain=" + UPDATED_HINH_MAT_TRUOC_URL);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL equals to DEFAULT_HINH_MAT_SAU_URL
        defaultProSanPhamShouldBeFound("hinhMatSauURL.equals=" + DEFAULT_HINH_MAT_SAU_URL);

        // Get all the proSanPhamList where hinhMatSauURL equals to UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.equals=" + UPDATED_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL not equals to DEFAULT_HINH_MAT_SAU_URL
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.notEquals=" + DEFAULT_HINH_MAT_SAU_URL);

        // Get all the proSanPhamList where hinhMatSauURL not equals to UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldBeFound("hinhMatSauURL.notEquals=" + UPDATED_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLIsInShouldWork() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL in DEFAULT_HINH_MAT_SAU_URL or UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldBeFound("hinhMatSauURL.in=" + DEFAULT_HINH_MAT_SAU_URL + "," + UPDATED_HINH_MAT_SAU_URL);

        // Get all the proSanPhamList where hinhMatSauURL equals to UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.in=" + UPDATED_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLIsNullOrNotNull() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL is not null
        defaultProSanPhamShouldBeFound("hinhMatSauURL.specified=true");

        // Get all the proSanPhamList where hinhMatSauURL is null
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.specified=false");
    }
                @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL contains DEFAULT_HINH_MAT_SAU_URL
        defaultProSanPhamShouldBeFound("hinhMatSauURL.contains=" + DEFAULT_HINH_MAT_SAU_URL);

        // Get all the proSanPhamList where hinhMatSauURL contains UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.contains=" + UPDATED_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void getAllProSanPhamsByHinhMatSauURLNotContainsSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        // Get all the proSanPhamList where hinhMatSauURL does not contain DEFAULT_HINH_MAT_SAU_URL
        defaultProSanPhamShouldNotBeFound("hinhMatSauURL.doesNotContain=" + DEFAULT_HINH_MAT_SAU_URL);

        // Get all the proSanPhamList where hinhMatSauURL does not contain UPDATED_HINH_MAT_SAU_URL
        defaultProSanPhamShouldBeFound("hinhMatSauURL.doesNotContain=" + UPDATED_HINH_MAT_SAU_URL);
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByProKhachHangIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);
        ProKhachHang proKhachHang = ProKhachHangResourceIT.createEntity(em);
        em.persist(proKhachHang);
        em.flush();
        proSanPham.setProKhachHang(proKhachHang);
        proSanPhamRepository.saveAndFlush(proSanPham);
        Long proKhachHangId = proKhachHang.getId();

        // Get all the proSanPhamList where proKhachHang equals to proKhachHangId
        defaultProSanPhamShouldBeFound("proKhachHangId.equals=" + proKhachHangId);

        // Get all the proSanPhamList where proKhachHang equals to proKhachHangId + 1
        defaultProSanPhamShouldNotBeFound("proKhachHangId.equals=" + (proKhachHangId + 1));
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByProChungLoaiSanPhamIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);
        ProChungLoaiSanPham proChungLoaiSanPham = ProChungLoaiSanPhamResourceIT.createEntity(em);
        em.persist(proChungLoaiSanPham);
        em.flush();
        proSanPham.setProChungLoaiSanPham(proChungLoaiSanPham);
        proSanPhamRepository.saveAndFlush(proSanPham);
        Long proChungLoaiSanPhamId = proChungLoaiSanPham.getId();

        // Get all the proSanPhamList where proChungLoaiSanPham equals to proChungLoaiSanPhamId
        defaultProSanPhamShouldBeFound("proChungLoaiSanPhamId.equals=" + proChungLoaiSanPhamId);

        // Get all the proSanPhamList where proChungLoaiSanPham equals to proChungLoaiSanPhamId + 1
        defaultProSanPhamShouldNotBeFound("proChungLoaiSanPhamId.equals=" + (proChungLoaiSanPhamId + 1));
    }


    @Test
    @Transactional
    public void getAllProSanPhamsByProTenMuaIsEqualToSomething() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);
        ProTenMua proTenMua = ProTenMuaResourceIT.createEntity(em);
        em.persist(proTenMua);
        em.flush();
        proSanPham.setProTenMua(proTenMua);
        proSanPhamRepository.saveAndFlush(proSanPham);
        Long proTenMuaId = proTenMua.getId();

        // Get all the proSanPhamList where proTenMua equals to proTenMuaId
        defaultProSanPhamShouldBeFound("proTenMuaId.equals=" + proTenMuaId);

        // Get all the proSanPhamList where proTenMua equals to proTenMuaId + 1
        defaultProSanPhamShouldNotBeFound("proTenMuaId.equals=" + (proTenMuaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProSanPhamShouldBeFound(String filter) throws Exception {
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proSanPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].maSanPham").value(hasItem(DEFAULT_MA_SAN_PHAM)))
            .andExpect(jsonPath("$.[*].maDonHang").value(hasItem(DEFAULT_MA_DON_HANG)))
            .andExpect(jsonPath("$.[*].po").value(hasItem(DEFAULT_PO)))
            .andExpect(jsonPath("$.[*].soLuongSanXuat").value(hasItem(DEFAULT_SO_LUONG_SAN_XUAT)))
            .andExpect(jsonPath("$.[*].thongSoValue1").value(hasItem(DEFAULT_THONG_SO_VALUE_1)))
            .andExpect(jsonPath("$.[*].thongSoCaption1").value(hasItem(DEFAULT_THONG_SO_CAPTION_1)))
            .andExpect(jsonPath("$.[*].thongSoCheck1").value(hasItem(DEFAULT_THONG_SO_CHECK_1.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue2").value(hasItem(DEFAULT_THONG_SO_VALUE_2)))
            .andExpect(jsonPath("$.[*].thongSoCaption2").value(hasItem(DEFAULT_THONG_SO_CAPTION_2)))
            .andExpect(jsonPath("$.[*].thongSoCheck2").value(hasItem(DEFAULT_THONG_SO_CHECK_2.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue3").value(hasItem(DEFAULT_THONG_SO_VALUE_3)))
            .andExpect(jsonPath("$.[*].thongSoCaption3").value(hasItem(DEFAULT_THONG_SO_CAPTION_3)))
            .andExpect(jsonPath("$.[*].thongSoCheck3").value(hasItem(DEFAULT_THONG_SO_CHECK_3.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue4").value(hasItem(DEFAULT_THONG_SO_VALUE_4)))
            .andExpect(jsonPath("$.[*].thongSoCaption4").value(hasItem(DEFAULT_THONG_SO_CAPTION_4)))
            .andExpect(jsonPath("$.[*].thongSoCheck4").value(hasItem(DEFAULT_THONG_SO_CHECK_4.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue5").value(hasItem(DEFAULT_THONG_SO_VALUE_5)))
            .andExpect(jsonPath("$.[*].thongSoCaption5").value(hasItem(DEFAULT_THONG_SO_CAPTION_5)))
            .andExpect(jsonPath("$.[*].thongSoCheck5").value(hasItem(DEFAULT_THONG_SO_CHECK_5.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue6").value(hasItem(DEFAULT_THONG_SO_VALUE_6)))
            .andExpect(jsonPath("$.[*].thongSoCaption6").value(hasItem(DEFAULT_THONG_SO_CAPTION_6)))
            .andExpect(jsonPath("$.[*].thongSoCheck6").value(hasItem(DEFAULT_THONG_SO_CHECK_6.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue7").value(hasItem(DEFAULT_THONG_SO_VALUE_7)))
            .andExpect(jsonPath("$.[*].thongSoCaption7").value(hasItem(DEFAULT_THONG_SO_CAPTION_7)))
            .andExpect(jsonPath("$.[*].thongSoCheck7").value(hasItem(DEFAULT_THONG_SO_CHECK_7.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue8").value(hasItem(DEFAULT_THONG_SO_VALUE_8)))
            .andExpect(jsonPath("$.[*].thongSoCaption8").value(hasItem(DEFAULT_THONG_SO_CAPTION_8)))
            .andExpect(jsonPath("$.[*].thongSoCheck8").value(hasItem(DEFAULT_THONG_SO_CHECK_8.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue9").value(hasItem(DEFAULT_THONG_SO_VALUE_9)))
            .andExpect(jsonPath("$.[*].thongSoCaption9").value(hasItem(DEFAULT_THONG_SO_CAPTION_9)))
            .andExpect(jsonPath("$.[*].thongSoCheck9").value(hasItem(DEFAULT_THONG_SO_CHECK_9.booleanValue())))
            .andExpect(jsonPath("$.[*].thongSoValue10").value(hasItem(DEFAULT_THONG_SO_VALUE_10)))
            .andExpect(jsonPath("$.[*].thongSoCaption10").value(hasItem(DEFAULT_THONG_SO_CAPTION_10)))
            .andExpect(jsonPath("$.[*].thongSoCheck10").value(hasItem(DEFAULT_THONG_SO_CHECK_10.booleanValue())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].hinhMatTruocURL").value(hasItem(DEFAULT_HINH_MAT_TRUOC_URL)))
            .andExpect(jsonPath("$.[*].hinhMatSauURL").value(hasItem(DEFAULT_HINH_MAT_SAU_URL)));

        // Check, that the count call also returns 1
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProSanPhamShouldNotBeFound(String filter) throws Exception {
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProSanPham() throws Exception {
        // Get the proSanPham
        restProSanPhamMockMvc.perform(get("/api/pro-san-phams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProSanPham() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        int databaseSizeBeforeUpdate = proSanPhamRepository.findAll().size();

        // Update the proSanPham
        ProSanPham updatedProSanPham = proSanPhamRepository.findById(proSanPham.getId()).get();
        // Disconnect from session so that the updates on updatedProSanPham are not directly saved in db
        em.detach(updatedProSanPham);
        updatedProSanPham
            .maSanPham(UPDATED_MA_SAN_PHAM)
            .maDonHang(UPDATED_MA_DON_HANG)
            .po(UPDATED_PO)
            .soLuongSanXuat(UPDATED_SO_LUONG_SAN_XUAT)
            .thongSoValue1(UPDATED_THONG_SO_VALUE_1)
            .thongSoCaption1(UPDATED_THONG_SO_CAPTION_1)
            .thongSoCheck1(UPDATED_THONG_SO_CHECK_1)
            .thongSoValue2(UPDATED_THONG_SO_VALUE_2)
            .thongSoCaption2(UPDATED_THONG_SO_CAPTION_2)
            .thongSoCheck2(UPDATED_THONG_SO_CHECK_2)
            .thongSoValue3(UPDATED_THONG_SO_VALUE_3)
            .thongSoCaption3(UPDATED_THONG_SO_CAPTION_3)
            .thongSoCheck3(UPDATED_THONG_SO_CHECK_3)
            .thongSoValue4(UPDATED_THONG_SO_VALUE_4)
            .thongSoCaption4(UPDATED_THONG_SO_CAPTION_4)
            .thongSoCheck4(UPDATED_THONG_SO_CHECK_4)
            .thongSoValue5(UPDATED_THONG_SO_VALUE_5)
            .thongSoCaption5(UPDATED_THONG_SO_CAPTION_5)
            .thongSoCheck5(UPDATED_THONG_SO_CHECK_5)
            .thongSoValue6(UPDATED_THONG_SO_VALUE_6)
            .thongSoCaption6(UPDATED_THONG_SO_CAPTION_6)
            .thongSoCheck6(UPDATED_THONG_SO_CHECK_6)
            .thongSoValue7(UPDATED_THONG_SO_VALUE_7)
            .thongSoCaption7(UPDATED_THONG_SO_CAPTION_7)
            .thongSoCheck7(UPDATED_THONG_SO_CHECK_7)
            .thongSoValue8(UPDATED_THONG_SO_VALUE_8)
            .thongSoCaption8(UPDATED_THONG_SO_CAPTION_8)
            .thongSoCheck8(UPDATED_THONG_SO_CHECK_8)
            .thongSoValue9(UPDATED_THONG_SO_VALUE_9)
            .thongSoCaption9(UPDATED_THONG_SO_CAPTION_9)
            .thongSoCheck9(UPDATED_THONG_SO_CHECK_9)
            .thongSoValue10(UPDATED_THONG_SO_VALUE_10)
            .thongSoCaption10(UPDATED_THONG_SO_CAPTION_10)
            .thongSoCheck10(UPDATED_THONG_SO_CHECK_10)
            .color(UPDATED_COLOR)
            .hinhMatTruocURL(UPDATED_HINH_MAT_TRUOC_URL)
            .hinhMatSauURL(UPDATED_HINH_MAT_SAU_URL);
        ProSanPhamDTO proSanPhamDTO = proSanPhamMapper.toDto(updatedProSanPham);

        restProSanPhamMockMvc.perform(put("/api/pro-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proSanPhamDTO)))
            .andExpect(status().isOk());

        // Validate the ProSanPham in the database
        List<ProSanPham> proSanPhamList = proSanPhamRepository.findAll();
        assertThat(proSanPhamList).hasSize(databaseSizeBeforeUpdate);
        ProSanPham testProSanPham = proSanPhamList.get(proSanPhamList.size() - 1);
        assertThat(testProSanPham.getMaSanPham()).isEqualTo(UPDATED_MA_SAN_PHAM);
        assertThat(testProSanPham.getMaDonHang()).isEqualTo(UPDATED_MA_DON_HANG);
        assertThat(testProSanPham.getPo()).isEqualTo(UPDATED_PO);
        assertThat(testProSanPham.getSoLuongSanXuat()).isEqualTo(UPDATED_SO_LUONG_SAN_XUAT);
        assertThat(testProSanPham.getThongSoValue1()).isEqualTo(UPDATED_THONG_SO_VALUE_1);
        assertThat(testProSanPham.getThongSoCaption1()).isEqualTo(UPDATED_THONG_SO_CAPTION_1);
        assertThat(testProSanPham.isThongSoCheck1()).isEqualTo(UPDATED_THONG_SO_CHECK_1);
        assertThat(testProSanPham.getThongSoValue2()).isEqualTo(UPDATED_THONG_SO_VALUE_2);
        assertThat(testProSanPham.getThongSoCaption2()).isEqualTo(UPDATED_THONG_SO_CAPTION_2);
        assertThat(testProSanPham.isThongSoCheck2()).isEqualTo(UPDATED_THONG_SO_CHECK_2);
        assertThat(testProSanPham.getThongSoValue3()).isEqualTo(UPDATED_THONG_SO_VALUE_3);
        assertThat(testProSanPham.getThongSoCaption3()).isEqualTo(UPDATED_THONG_SO_CAPTION_3);
        assertThat(testProSanPham.isThongSoCheck3()).isEqualTo(UPDATED_THONG_SO_CHECK_3);
        assertThat(testProSanPham.getThongSoValue4()).isEqualTo(UPDATED_THONG_SO_VALUE_4);
        assertThat(testProSanPham.getThongSoCaption4()).isEqualTo(UPDATED_THONG_SO_CAPTION_4);
        assertThat(testProSanPham.isThongSoCheck4()).isEqualTo(UPDATED_THONG_SO_CHECK_4);
        assertThat(testProSanPham.getThongSoValue5()).isEqualTo(UPDATED_THONG_SO_VALUE_5);
        assertThat(testProSanPham.getThongSoCaption5()).isEqualTo(UPDATED_THONG_SO_CAPTION_5);
        assertThat(testProSanPham.isThongSoCheck5()).isEqualTo(UPDATED_THONG_SO_CHECK_5);
        assertThat(testProSanPham.getThongSoValue6()).isEqualTo(UPDATED_THONG_SO_VALUE_6);
        assertThat(testProSanPham.getThongSoCaption6()).isEqualTo(UPDATED_THONG_SO_CAPTION_6);
        assertThat(testProSanPham.isThongSoCheck6()).isEqualTo(UPDATED_THONG_SO_CHECK_6);
        assertThat(testProSanPham.getThongSoValue7()).isEqualTo(UPDATED_THONG_SO_VALUE_7);
        assertThat(testProSanPham.getThongSoCaption7()).isEqualTo(UPDATED_THONG_SO_CAPTION_7);
        assertThat(testProSanPham.isThongSoCheck7()).isEqualTo(UPDATED_THONG_SO_CHECK_7);
        assertThat(testProSanPham.getThongSoValue8()).isEqualTo(UPDATED_THONG_SO_VALUE_8);
        assertThat(testProSanPham.getThongSoCaption8()).isEqualTo(UPDATED_THONG_SO_CAPTION_8);
        assertThat(testProSanPham.isThongSoCheck8()).isEqualTo(UPDATED_THONG_SO_CHECK_8);
        assertThat(testProSanPham.getThongSoValue9()).isEqualTo(UPDATED_THONG_SO_VALUE_9);
        assertThat(testProSanPham.getThongSoCaption9()).isEqualTo(UPDATED_THONG_SO_CAPTION_9);
        assertThat(testProSanPham.isThongSoCheck9()).isEqualTo(UPDATED_THONG_SO_CHECK_9);
        assertThat(testProSanPham.getThongSoValue10()).isEqualTo(UPDATED_THONG_SO_VALUE_10);
        assertThat(testProSanPham.getThongSoCaption10()).isEqualTo(UPDATED_THONG_SO_CAPTION_10);
        assertThat(testProSanPham.isThongSoCheck10()).isEqualTo(UPDATED_THONG_SO_CHECK_10);
        assertThat(testProSanPham.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testProSanPham.getHinhMatTruocURL()).isEqualTo(UPDATED_HINH_MAT_TRUOC_URL);
        assertThat(testProSanPham.getHinhMatSauURL()).isEqualTo(UPDATED_HINH_MAT_SAU_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingProSanPham() throws Exception {
        int databaseSizeBeforeUpdate = proSanPhamRepository.findAll().size();

        // Create the ProSanPham
        ProSanPhamDTO proSanPhamDTO = proSanPhamMapper.toDto(proSanPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProSanPhamMockMvc.perform(put("/api/pro-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProSanPham in the database
        List<ProSanPham> proSanPhamList = proSanPhamRepository.findAll();
        assertThat(proSanPhamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProSanPham() throws Exception {
        // Initialize the database
        proSanPhamRepository.saveAndFlush(proSanPham);

        int databaseSizeBeforeDelete = proSanPhamRepository.findAll().size();

        // Delete the proSanPham
        restProSanPhamMockMvc.perform(delete("/api/pro-san-phams/{id}", proSanPham.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProSanPham> proSanPhamList = proSanPhamRepository.findAll();
        assertThat(proSanPhamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
