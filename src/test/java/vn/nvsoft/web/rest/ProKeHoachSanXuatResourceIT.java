package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.domain.ProChuyen;
import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.repository.ProKeHoachSanXuatRepository;
import vn.nvsoft.service.ProKeHoachSanXuatService;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.mapper.ProKeHoachSanXuatMapper;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;
import vn.nvsoft.service.dto.ProKeHoachSanXuatCriteria;
import vn.nvsoft.service.ProKeHoachSanXuatQueryService;

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
 * Integration tests for the {@link ProKeHoachSanXuatResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProKeHoachSanXuatResourceIT {

    private static final Instant DEFAULT_NGAY_SAN_XUAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_SAN_XUAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SO_LUONG_YEU_CAU = 1;
    private static final Integer UPDATED_SO_LUONG_YEU_CAU = 2;
    private static final Integer SMALLER_SO_LUONG_YEU_CAU = 1 - 1;

    private static final Integer DEFAULT_TINH_TRANG = 1;
    private static final Integer UPDATED_TINH_TRANG = 2;
    private static final Integer SMALLER_TINH_TRANG = 1 - 1;

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_SAM = 1;
    private static final Integer UPDATED_SAM = 2;
    private static final Integer SMALLER_SAM = 1 - 1;

    private static final Integer DEFAULT_LOT_NO = 1;
    private static final Integer UPDATED_LOT_NO = 2;
    private static final Integer SMALLER_LOT_NO = 1 - 1;

    @Autowired
    private ProKeHoachSanXuatRepository proKeHoachSanXuatRepository;

    @Autowired
    private ProKeHoachSanXuatMapper proKeHoachSanXuatMapper;

    @Autowired
    private ProKeHoachSanXuatService proKeHoachSanXuatService;

    @Autowired
    private ProKeHoachSanXuatQueryService proKeHoachSanXuatQueryService;

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

    private MockMvc restProKeHoachSanXuatMockMvc;

    private ProKeHoachSanXuat proKeHoachSanXuat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProKeHoachSanXuatResource proKeHoachSanXuatResource = new ProKeHoachSanXuatResource(proKeHoachSanXuatService, proKeHoachSanXuatQueryService);
        this.restProKeHoachSanXuatMockMvc = MockMvcBuilders.standaloneSetup(proKeHoachSanXuatResource)
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
    public static ProKeHoachSanXuat createEntity(EntityManager em) {
        ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat()
            .ngaySanXuat(DEFAULT_NGAY_SAN_XUAT)
            .soLuongYeuCau(DEFAULT_SO_LUONG_YEU_CAU)
            .tinhTrang(DEFAULT_TINH_TRANG)
            .ghiChu(DEFAULT_GHI_CHU)
            .sam(DEFAULT_SAM)
            .lotNo(DEFAULT_LOT_NO);
        return proKeHoachSanXuat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProKeHoachSanXuat createUpdatedEntity(EntityManager em) {
        ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat()
            .ngaySanXuat(UPDATED_NGAY_SAN_XUAT)
            .soLuongYeuCau(UPDATED_SO_LUONG_YEU_CAU)
            .tinhTrang(UPDATED_TINH_TRANG)
            .ghiChu(UPDATED_GHI_CHU)
            .sam(UPDATED_SAM)
            .lotNo(UPDATED_LOT_NO);
        return proKeHoachSanXuat;
    }

    @BeforeEach
    public void initTest() {
        proKeHoachSanXuat = createEntity(em);
    }

    @Test
    @Transactional
    public void createProKeHoachSanXuat() throws Exception {
        int databaseSizeBeforeCreate = proKeHoachSanXuatRepository.findAll().size();

        // Create the ProKeHoachSanXuat
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO = proKeHoachSanXuatMapper.toDto(proKeHoachSanXuat);
        restProKeHoachSanXuatMockMvc.perform(post("/api/pro-ke-hoach-san-xuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKeHoachSanXuatDTO)))
            .andExpect(status().isCreated());

        // Validate the ProKeHoachSanXuat in the database
        List<ProKeHoachSanXuat> proKeHoachSanXuatList = proKeHoachSanXuatRepository.findAll();
        assertThat(proKeHoachSanXuatList).hasSize(databaseSizeBeforeCreate + 1);
        ProKeHoachSanXuat testProKeHoachSanXuat = proKeHoachSanXuatList.get(proKeHoachSanXuatList.size() - 1);
        assertThat(testProKeHoachSanXuat.getNgaySanXuat()).isEqualTo(DEFAULT_NGAY_SAN_XUAT);
        assertThat(testProKeHoachSanXuat.getSoLuongYeuCau()).isEqualTo(DEFAULT_SO_LUONG_YEU_CAU);
        assertThat(testProKeHoachSanXuat.getTinhTrang()).isEqualTo(DEFAULT_TINH_TRANG);
        assertThat(testProKeHoachSanXuat.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testProKeHoachSanXuat.getSam()).isEqualTo(DEFAULT_SAM);
        assertThat(testProKeHoachSanXuat.getLotNo()).isEqualTo(DEFAULT_LOT_NO);
    }

    @Test
    @Transactional
    public void createProKeHoachSanXuatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proKeHoachSanXuatRepository.findAll().size();

        // Create the ProKeHoachSanXuat with an existing ID
        proKeHoachSanXuat.setId(1L);
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO = proKeHoachSanXuatMapper.toDto(proKeHoachSanXuat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProKeHoachSanXuatMockMvc.perform(post("/api/pro-ke-hoach-san-xuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKeHoachSanXuatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProKeHoachSanXuat in the database
        List<ProKeHoachSanXuat> proKeHoachSanXuatList = proKeHoachSanXuatRepository.findAll();
        assertThat(proKeHoachSanXuatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuats() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proKeHoachSanXuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].ngaySanXuat").value(hasItem(DEFAULT_NGAY_SAN_XUAT.toString())))
            .andExpect(jsonPath("$.[*].soLuongYeuCau").value(hasItem(DEFAULT_SO_LUONG_YEU_CAU)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].sam").value(hasItem(DEFAULT_SAM)))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO)));
    }
    
    @Test
    @Transactional
    public void getProKeHoachSanXuat() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get the proKeHoachSanXuat
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats/{id}", proKeHoachSanXuat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proKeHoachSanXuat.getId().intValue()))
            .andExpect(jsonPath("$.ngaySanXuat").value(DEFAULT_NGAY_SAN_XUAT.toString()))
            .andExpect(jsonPath("$.soLuongYeuCau").value(DEFAULT_SO_LUONG_YEU_CAU))
            .andExpect(jsonPath("$.tinhTrang").value(DEFAULT_TINH_TRANG))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU))
            .andExpect(jsonPath("$.sam").value(DEFAULT_SAM))
            .andExpect(jsonPath("$.lotNo").value(DEFAULT_LOT_NO));
    }


    @Test
    @Transactional
    public void getProKeHoachSanXuatsByIdFiltering() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        Long id = proKeHoachSanXuat.getId();

        defaultProKeHoachSanXuatShouldBeFound("id.equals=" + id);
        defaultProKeHoachSanXuatShouldNotBeFound("id.notEquals=" + id);

        defaultProKeHoachSanXuatShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProKeHoachSanXuatShouldNotBeFound("id.greaterThan=" + id);

        defaultProKeHoachSanXuatShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProKeHoachSanXuatShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByNgaySanXuatIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ngaySanXuat equals to DEFAULT_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldBeFound("ngaySanXuat.equals=" + DEFAULT_NGAY_SAN_XUAT);

        // Get all the proKeHoachSanXuatList where ngaySanXuat equals to UPDATED_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldNotBeFound("ngaySanXuat.equals=" + UPDATED_NGAY_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByNgaySanXuatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ngaySanXuat not equals to DEFAULT_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldNotBeFound("ngaySanXuat.notEquals=" + DEFAULT_NGAY_SAN_XUAT);

        // Get all the proKeHoachSanXuatList where ngaySanXuat not equals to UPDATED_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldBeFound("ngaySanXuat.notEquals=" + UPDATED_NGAY_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByNgaySanXuatIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ngaySanXuat in DEFAULT_NGAY_SAN_XUAT or UPDATED_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldBeFound("ngaySanXuat.in=" + DEFAULT_NGAY_SAN_XUAT + "," + UPDATED_NGAY_SAN_XUAT);

        // Get all the proKeHoachSanXuatList where ngaySanXuat equals to UPDATED_NGAY_SAN_XUAT
        defaultProKeHoachSanXuatShouldNotBeFound("ngaySanXuat.in=" + UPDATED_NGAY_SAN_XUAT);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByNgaySanXuatIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ngaySanXuat is not null
        defaultProKeHoachSanXuatShouldBeFound("ngaySanXuat.specified=true");

        // Get all the proKeHoachSanXuatList where ngaySanXuat is null
        defaultProKeHoachSanXuatShouldNotBeFound("ngaySanXuat.specified=false");
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau equals to DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.equals=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau equals to UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.equals=" + UPDATED_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau not equals to DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.notEquals=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau not equals to UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.notEquals=" + UPDATED_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau in DEFAULT_SO_LUONG_YEU_CAU or UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.in=" + DEFAULT_SO_LUONG_YEU_CAU + "," + UPDATED_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau equals to UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.in=" + UPDATED_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is not null
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.specified=true");

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is null
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.specified=false");
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is greater than or equal to DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.greaterThanOrEqual=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is greater than or equal to UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.greaterThanOrEqual=" + UPDATED_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is less than or equal to DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.lessThanOrEqual=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is less than or equal to SMALLER_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.lessThanOrEqual=" + SMALLER_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsLessThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is less than DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.lessThan=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is less than UPDATED_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.lessThan=" + UPDATED_SO_LUONG_YEU_CAU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySoLuongYeuCauIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is greater than DEFAULT_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldNotBeFound("soLuongYeuCau.greaterThan=" + DEFAULT_SO_LUONG_YEU_CAU);

        // Get all the proKeHoachSanXuatList where soLuongYeuCau is greater than SMALLER_SO_LUONG_YEU_CAU
        defaultProKeHoachSanXuatShouldBeFound("soLuongYeuCau.greaterThan=" + SMALLER_SO_LUONG_YEU_CAU);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang equals to DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.equals=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang equals to UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.equals=" + UPDATED_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang not equals to DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.notEquals=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang not equals to UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.notEquals=" + UPDATED_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang in DEFAULT_TINH_TRANG or UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.in=" + DEFAULT_TINH_TRANG + "," + UPDATED_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang equals to UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.in=" + UPDATED_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang is not null
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.specified=true");

        // Get all the proKeHoachSanXuatList where tinhTrang is null
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.specified=false");
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang is greater than or equal to DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.greaterThanOrEqual=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang is greater than or equal to UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.greaterThanOrEqual=" + UPDATED_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang is less than or equal to DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.lessThanOrEqual=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang is less than or equal to SMALLER_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.lessThanOrEqual=" + SMALLER_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsLessThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang is less than DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.lessThan=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang is less than UPDATED_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.lessThan=" + UPDATED_TINH_TRANG);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByTinhTrangIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where tinhTrang is greater than DEFAULT_TINH_TRANG
        defaultProKeHoachSanXuatShouldNotBeFound("tinhTrang.greaterThan=" + DEFAULT_TINH_TRANG);

        // Get all the proKeHoachSanXuatList where tinhTrang is greater than SMALLER_TINH_TRANG
        defaultProKeHoachSanXuatShouldBeFound("tinhTrang.greaterThan=" + SMALLER_TINH_TRANG);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu equals to DEFAULT_GHI_CHU
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the proKeHoachSanXuatList where ghiChu equals to UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu not equals to DEFAULT_GHI_CHU
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.notEquals=" + DEFAULT_GHI_CHU);

        // Get all the proKeHoachSanXuatList where ghiChu not equals to UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.notEquals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the proKeHoachSanXuatList where ghiChu equals to UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu is not null
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.specified=true");

        // Get all the proKeHoachSanXuatList where ghiChu is null
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.specified=false");
    }
                @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu contains DEFAULT_GHI_CHU
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.contains=" + DEFAULT_GHI_CHU);

        // Get all the proKeHoachSanXuatList where ghiChu contains UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where ghiChu does not contain DEFAULT_GHI_CHU
        defaultProKeHoachSanXuatShouldNotBeFound("ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);

        // Get all the proKeHoachSanXuatList where ghiChu does not contain UPDATED_GHI_CHU
        defaultProKeHoachSanXuatShouldBeFound("ghiChu.doesNotContain=" + UPDATED_GHI_CHU);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam equals to DEFAULT_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.equals=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam equals to UPDATED_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.equals=" + UPDATED_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam not equals to DEFAULT_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.notEquals=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam not equals to UPDATED_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.notEquals=" + UPDATED_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam in DEFAULT_SAM or UPDATED_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.in=" + DEFAULT_SAM + "," + UPDATED_SAM);

        // Get all the proKeHoachSanXuatList where sam equals to UPDATED_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.in=" + UPDATED_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam is not null
        defaultProKeHoachSanXuatShouldBeFound("sam.specified=true");

        // Get all the proKeHoachSanXuatList where sam is null
        defaultProKeHoachSanXuatShouldNotBeFound("sam.specified=false");
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam is greater than or equal to DEFAULT_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.greaterThanOrEqual=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam is greater than or equal to UPDATED_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.greaterThanOrEqual=" + UPDATED_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam is less than or equal to DEFAULT_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.lessThanOrEqual=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam is less than or equal to SMALLER_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.lessThanOrEqual=" + SMALLER_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsLessThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam is less than DEFAULT_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.lessThan=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam is less than UPDATED_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.lessThan=" + UPDATED_SAM);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsBySamIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where sam is greater than DEFAULT_SAM
        defaultProKeHoachSanXuatShouldNotBeFound("sam.greaterThan=" + DEFAULT_SAM);

        // Get all the proKeHoachSanXuatList where sam is greater than SMALLER_SAM
        defaultProKeHoachSanXuatShouldBeFound("sam.greaterThan=" + SMALLER_SAM);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo equals to DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.equals=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo equals to UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.equals=" + UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo not equals to DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.notEquals=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo not equals to UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.notEquals=" + UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsInShouldWork() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo in DEFAULT_LOT_NO or UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.in=" + DEFAULT_LOT_NO + "," + UPDATED_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo equals to UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.in=" + UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo is not null
        defaultProKeHoachSanXuatShouldBeFound("lotNo.specified=true");

        // Get all the proKeHoachSanXuatList where lotNo is null
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.specified=false");
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo is greater than or equal to DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.greaterThanOrEqual=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo is greater than or equal to UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.greaterThanOrEqual=" + UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo is less than or equal to DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.lessThanOrEqual=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo is less than or equal to SMALLER_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.lessThanOrEqual=" + SMALLER_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsLessThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo is less than DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.lessThan=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo is less than UPDATED_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.lessThan=" + UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByLotNoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        // Get all the proKeHoachSanXuatList where lotNo is greater than DEFAULT_LOT_NO
        defaultProKeHoachSanXuatShouldNotBeFound("lotNo.greaterThan=" + DEFAULT_LOT_NO);

        // Get all the proKeHoachSanXuatList where lotNo is greater than SMALLER_LOT_NO
        defaultProKeHoachSanXuatShouldBeFound("lotNo.greaterThan=" + SMALLER_LOT_NO);
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByProChuyenIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);
        ProChuyen proChuyen = ProChuyenResourceIT.createEntity(em);
        em.persist(proChuyen);
        em.flush();
        proKeHoachSanXuat.setProChuyen(proChuyen);
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);
        Long proChuyenId = proChuyen.getId();

        // Get all the proKeHoachSanXuatList where proChuyen equals to proChuyenId
        defaultProKeHoachSanXuatShouldBeFound("proChuyenId.equals=" + proChuyenId);

        // Get all the proKeHoachSanXuatList where proChuyen equals to proChuyenId + 1
        defaultProKeHoachSanXuatShouldNotBeFound("proChuyenId.equals=" + (proChuyenId + 1));
    }


    @Test
    @Transactional
    public void getAllProKeHoachSanXuatsByProSanPhamIsEqualToSomething() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);
        ProSanPham proSanPham = ProSanPhamResourceIT.createEntity(em);
        em.persist(proSanPham);
        em.flush();
        proKeHoachSanXuat.setProSanPham(proSanPham);
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);
        Long proSanPhamId = proSanPham.getId();

        // Get all the proKeHoachSanXuatList where proSanPham equals to proSanPhamId
        defaultProKeHoachSanXuatShouldBeFound("proSanPhamId.equals=" + proSanPhamId);

        // Get all the proKeHoachSanXuatList where proSanPham equals to proSanPhamId + 1
        defaultProKeHoachSanXuatShouldNotBeFound("proSanPhamId.equals=" + (proSanPhamId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProKeHoachSanXuatShouldBeFound(String filter) throws Exception {
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proKeHoachSanXuat.getId().intValue())))
            .andExpect(jsonPath("$.[*].ngaySanXuat").value(hasItem(DEFAULT_NGAY_SAN_XUAT.toString())))
            .andExpect(jsonPath("$.[*].soLuongYeuCau").value(hasItem(DEFAULT_SO_LUONG_YEU_CAU)))
            .andExpect(jsonPath("$.[*].tinhTrang").value(hasItem(DEFAULT_TINH_TRANG)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)))
            .andExpect(jsonPath("$.[*].sam").value(hasItem(DEFAULT_SAM)))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO)));

        // Check, that the count call also returns 1
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProKeHoachSanXuatShouldNotBeFound(String filter) throws Exception {
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProKeHoachSanXuat() throws Exception {
        // Get the proKeHoachSanXuat
        restProKeHoachSanXuatMockMvc.perform(get("/api/pro-ke-hoach-san-xuats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProKeHoachSanXuat() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        int databaseSizeBeforeUpdate = proKeHoachSanXuatRepository.findAll().size();

        // Update the proKeHoachSanXuat
        ProKeHoachSanXuat updatedProKeHoachSanXuat = proKeHoachSanXuatRepository.findById(proKeHoachSanXuat.getId()).get();
        // Disconnect from session so that the updates on updatedProKeHoachSanXuat are not directly saved in db
        em.detach(updatedProKeHoachSanXuat);
        updatedProKeHoachSanXuat
            .ngaySanXuat(UPDATED_NGAY_SAN_XUAT)
            .soLuongYeuCau(UPDATED_SO_LUONG_YEU_CAU)
            .tinhTrang(UPDATED_TINH_TRANG)
            .ghiChu(UPDATED_GHI_CHU)
            .sam(UPDATED_SAM)
            .lotNo(UPDATED_LOT_NO);
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO = proKeHoachSanXuatMapper.toDto(updatedProKeHoachSanXuat);

        restProKeHoachSanXuatMockMvc.perform(put("/api/pro-ke-hoach-san-xuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKeHoachSanXuatDTO)))
            .andExpect(status().isOk());

        // Validate the ProKeHoachSanXuat in the database
        List<ProKeHoachSanXuat> proKeHoachSanXuatList = proKeHoachSanXuatRepository.findAll();
        assertThat(proKeHoachSanXuatList).hasSize(databaseSizeBeforeUpdate);
        ProKeHoachSanXuat testProKeHoachSanXuat = proKeHoachSanXuatList.get(proKeHoachSanXuatList.size() - 1);
        assertThat(testProKeHoachSanXuat.getNgaySanXuat()).isEqualTo(UPDATED_NGAY_SAN_XUAT);
        assertThat(testProKeHoachSanXuat.getSoLuongYeuCau()).isEqualTo(UPDATED_SO_LUONG_YEU_CAU);
        assertThat(testProKeHoachSanXuat.getTinhTrang()).isEqualTo(UPDATED_TINH_TRANG);
        assertThat(testProKeHoachSanXuat.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testProKeHoachSanXuat.getSam()).isEqualTo(UPDATED_SAM);
        assertThat(testProKeHoachSanXuat.getLotNo()).isEqualTo(UPDATED_LOT_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingProKeHoachSanXuat() throws Exception {
        int databaseSizeBeforeUpdate = proKeHoachSanXuatRepository.findAll().size();

        // Create the ProKeHoachSanXuat
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO = proKeHoachSanXuatMapper.toDto(proKeHoachSanXuat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProKeHoachSanXuatMockMvc.perform(put("/api/pro-ke-hoach-san-xuats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKeHoachSanXuatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProKeHoachSanXuat in the database
        List<ProKeHoachSanXuat> proKeHoachSanXuatList = proKeHoachSanXuatRepository.findAll();
        assertThat(proKeHoachSanXuatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProKeHoachSanXuat() throws Exception {
        // Initialize the database
        proKeHoachSanXuatRepository.saveAndFlush(proKeHoachSanXuat);

        int databaseSizeBeforeDelete = proKeHoachSanXuatRepository.findAll().size();

        // Delete the proKeHoachSanXuat
        restProKeHoachSanXuatMockMvc.perform(delete("/api/pro-ke-hoach-san-xuats/{id}", proKeHoachSanXuat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProKeHoachSanXuat> proKeHoachSanXuatList = proKeHoachSanXuatRepository.findAll();
        assertThat(proKeHoachSanXuatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
