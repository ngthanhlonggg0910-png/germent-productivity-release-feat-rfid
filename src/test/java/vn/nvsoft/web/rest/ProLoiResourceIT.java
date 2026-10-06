package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.domain.ProCumLoi;
import vn.nvsoft.repository.ProLoiRepository;
import vn.nvsoft.service.ProLoiService;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.mapper.ProLoiMapper;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;
import vn.nvsoft.service.dto.ProLoiCriteria;
import vn.nvsoft.service.ProLoiQueryService;

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
 * Integration tests for the {@link ProLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProLoiResourceIT {

    private static final String DEFAULT_MA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_CUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LOI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TIENG_ANH = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TIENG_ANH = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;
    private static final Integer SMALLER_STT = 1 - 1;

    private static final String DEFAULT_MAU_SAC = "AAAAAAAAAA";
    private static final String UPDATED_MAU_SAC = "BBBBBBBBBB";

    private static final Boolean DEFAULT_QC = false;
    private static final Boolean UPDATED_QC = true;

    private static final Boolean DEFAULT_QA = false;
    private static final Boolean UPDATED_QA = true;

    private static final Boolean DEFAULT_CHECKED = false;
    private static final Boolean UPDATED_CHECKED = true;

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    @Autowired
    private ProLoiRepository proLoiRepository;

    @Autowired
    private ProLoiMapper proLoiMapper;

    @Autowired
    private ProLoiService proLoiService;

    @Autowired
    private ProLoiQueryService proLoiQueryService;

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

    private MockMvc restProLoiMockMvc;

    private ProLoi proLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProLoiResource proLoiResource = new ProLoiResource(proLoiService, proLoiQueryService);
        this.restProLoiMockMvc = MockMvcBuilders.standaloneSetup(proLoiResource)
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
    public static ProLoi createEntity(EntityManager em) {
        ProLoi proLoi = new ProLoi()
            .maLoi(DEFAULT_MA_LOI)
            .cusCode(DEFAULT_CUS_CODE)
            .tenLoi(DEFAULT_TEN_LOI)
            .tenTiengAnh(DEFAULT_TEN_TIENG_ANH)
            .label(DEFAULT_LABEL)
            .stt(DEFAULT_STT)
            .mauSac(DEFAULT_MAU_SAC)
            .qc(DEFAULT_QC)
            .qa(DEFAULT_QA)
            .checked(DEFAULT_CHECKED)
            .ghiChu(DEFAULT_GHI_CHU);
        return proLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProLoi createUpdatedEntity(EntityManager em) {
        ProLoi proLoi = new ProLoi()
            .maLoi(UPDATED_MA_LOI)
            .cusCode(UPDATED_CUS_CODE)
            .tenLoi(UPDATED_TEN_LOI)
            .tenTiengAnh(UPDATED_TEN_TIENG_ANH)
            .label(UPDATED_LABEL)
            .stt(UPDATED_STT)
            .mauSac(UPDATED_MAU_SAC)
            .qc(UPDATED_QC)
            .qa(UPDATED_QA)
            .checked(UPDATED_CHECKED)
            .ghiChu(UPDATED_GHI_CHU);
        return proLoi;
    }

    @BeforeEach
    public void initTest() {
        proLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProLoi() throws Exception {
        int databaseSizeBeforeCreate = proLoiRepository.findAll().size();

        // Create the ProLoi
        ProLoiDTO proLoiDTO = proLoiMapper.toDto(proLoi);
        restProLoiMockMvc.perform(post("/api/pro-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProLoi in the database
        List<ProLoi> proLoiList = proLoiRepository.findAll();
        assertThat(proLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProLoi testProLoi = proLoiList.get(proLoiList.size() - 1);
        assertThat(testProLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProLoi.getCusCode()).isEqualTo(DEFAULT_CUS_CODE);
        assertThat(testProLoi.getTenLoi()).isEqualTo(DEFAULT_TEN_LOI);
        assertThat(testProLoi.getTenTiengAnh()).isEqualTo(DEFAULT_TEN_TIENG_ANH);
        assertThat(testProLoi.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testProLoi.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testProLoi.getMauSac()).isEqualTo(DEFAULT_MAU_SAC);
        assertThat(testProLoi.isQc()).isEqualTo(DEFAULT_QC);
        assertThat(testProLoi.isQa()).isEqualTo(DEFAULT_QA);
        assertThat(testProLoi.isChecked()).isEqualTo(DEFAULT_CHECKED);
        assertThat(testProLoi.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    public void createProLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proLoiRepository.findAll().size();

        // Create the ProLoi with an existing ID
        proLoi.setId(1L);
        ProLoiDTO proLoiDTO = proLoiMapper.toDto(proLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProLoiMockMvc.perform(post("/api/pro-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProLoi in the database
        List<ProLoi> proLoiList = proLoiRepository.findAll();
        assertThat(proLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProLois() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList
        restProLoiMockMvc.perform(get("/api/pro-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].cusCode").value(hasItem(DEFAULT_CUS_CODE)))
            .andExpect(jsonPath("$.[*].tenLoi").value(hasItem(DEFAULT_TEN_LOI)))
            .andExpect(jsonPath("$.[*].tenTiengAnh").value(hasItem(DEFAULT_TEN_TIENG_ANH)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].mauSac").value(hasItem(DEFAULT_MAU_SAC)))
            .andExpect(jsonPath("$.[*].qc").value(hasItem(DEFAULT_QC.booleanValue())))
            .andExpect(jsonPath("$.[*].qa").value(hasItem(DEFAULT_QA.booleanValue())))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)));
    }
    
    @Test
    @Transactional
    public void getProLoi() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get the proLoi
        restProLoiMockMvc.perform(get("/api/pro-lois/{id}", proLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.cusCode").value(DEFAULT_CUS_CODE))
            .andExpect(jsonPath("$.tenLoi").value(DEFAULT_TEN_LOI))
            .andExpect(jsonPath("$.tenTiengAnh").value(DEFAULT_TEN_TIENG_ANH))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.mauSac").value(DEFAULT_MAU_SAC))
            .andExpect(jsonPath("$.qc").value(DEFAULT_QC.booleanValue()))
            .andExpect(jsonPath("$.qa").value(DEFAULT_QA.booleanValue()))
            .andExpect(jsonPath("$.checked").value(DEFAULT_CHECKED.booleanValue()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU));
    }


    @Test
    @Transactional
    public void getProLoisByIdFiltering() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        Long id = proLoi.getId();

        defaultProLoiShouldBeFound("id.equals=" + id);
        defaultProLoiShouldNotBeFound("id.notEquals=" + id);

        defaultProLoiShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProLoiShouldNotBeFound("id.greaterThan=" + id);

        defaultProLoiShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProLoiShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProLoisByMaLoiIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi equals to DEFAULT_MA_LOI
        defaultProLoiShouldBeFound("maLoi.equals=" + DEFAULT_MA_LOI);

        // Get all the proLoiList where maLoi equals to UPDATED_MA_LOI
        defaultProLoiShouldNotBeFound("maLoi.equals=" + UPDATED_MA_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByMaLoiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi not equals to DEFAULT_MA_LOI
        defaultProLoiShouldNotBeFound("maLoi.notEquals=" + DEFAULT_MA_LOI);

        // Get all the proLoiList where maLoi not equals to UPDATED_MA_LOI
        defaultProLoiShouldBeFound("maLoi.notEquals=" + UPDATED_MA_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByMaLoiIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi in DEFAULT_MA_LOI or UPDATED_MA_LOI
        defaultProLoiShouldBeFound("maLoi.in=" + DEFAULT_MA_LOI + "," + UPDATED_MA_LOI);

        // Get all the proLoiList where maLoi equals to UPDATED_MA_LOI
        defaultProLoiShouldNotBeFound("maLoi.in=" + UPDATED_MA_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByMaLoiIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi is not null
        defaultProLoiShouldBeFound("maLoi.specified=true");

        // Get all the proLoiList where maLoi is null
        defaultProLoiShouldNotBeFound("maLoi.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByMaLoiContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi contains DEFAULT_MA_LOI
        defaultProLoiShouldBeFound("maLoi.contains=" + DEFAULT_MA_LOI);

        // Get all the proLoiList where maLoi contains UPDATED_MA_LOI
        defaultProLoiShouldNotBeFound("maLoi.contains=" + UPDATED_MA_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByMaLoiNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where maLoi does not contain DEFAULT_MA_LOI
        defaultProLoiShouldNotBeFound("maLoi.doesNotContain=" + DEFAULT_MA_LOI);

        // Get all the proLoiList where maLoi does not contain UPDATED_MA_LOI
        defaultProLoiShouldBeFound("maLoi.doesNotContain=" + UPDATED_MA_LOI);
    }


    @Test
    @Transactional
    public void getAllProLoisByCusCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode equals to DEFAULT_CUS_CODE
        defaultProLoiShouldBeFound("cusCode.equals=" + DEFAULT_CUS_CODE);

        // Get all the proLoiList where cusCode equals to UPDATED_CUS_CODE
        defaultProLoiShouldNotBeFound("cusCode.equals=" + UPDATED_CUS_CODE);
    }

    @Test
    @Transactional
    public void getAllProLoisByCusCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode not equals to DEFAULT_CUS_CODE
        defaultProLoiShouldNotBeFound("cusCode.notEquals=" + DEFAULT_CUS_CODE);

        // Get all the proLoiList where cusCode not equals to UPDATED_CUS_CODE
        defaultProLoiShouldBeFound("cusCode.notEquals=" + UPDATED_CUS_CODE);
    }

    @Test
    @Transactional
    public void getAllProLoisByCusCodeIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode in DEFAULT_CUS_CODE or UPDATED_CUS_CODE
        defaultProLoiShouldBeFound("cusCode.in=" + DEFAULT_CUS_CODE + "," + UPDATED_CUS_CODE);

        // Get all the proLoiList where cusCode equals to UPDATED_CUS_CODE
        defaultProLoiShouldNotBeFound("cusCode.in=" + UPDATED_CUS_CODE);
    }

    @Test
    @Transactional
    public void getAllProLoisByCusCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode is not null
        defaultProLoiShouldBeFound("cusCode.specified=true");

        // Get all the proLoiList where cusCode is null
        defaultProLoiShouldNotBeFound("cusCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByCusCodeContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode contains DEFAULT_CUS_CODE
        defaultProLoiShouldBeFound("cusCode.contains=" + DEFAULT_CUS_CODE);

        // Get all the proLoiList where cusCode contains UPDATED_CUS_CODE
        defaultProLoiShouldNotBeFound("cusCode.contains=" + UPDATED_CUS_CODE);
    }

    @Test
    @Transactional
    public void getAllProLoisByCusCodeNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where cusCode does not contain DEFAULT_CUS_CODE
        defaultProLoiShouldNotBeFound("cusCode.doesNotContain=" + DEFAULT_CUS_CODE);

        // Get all the proLoiList where cusCode does not contain UPDATED_CUS_CODE
        defaultProLoiShouldBeFound("cusCode.doesNotContain=" + UPDATED_CUS_CODE);
    }


    @Test
    @Transactional
    public void getAllProLoisByTenLoiIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi equals to DEFAULT_TEN_LOI
        defaultProLoiShouldBeFound("tenLoi.equals=" + DEFAULT_TEN_LOI);

        // Get all the proLoiList where tenLoi equals to UPDATED_TEN_LOI
        defaultProLoiShouldNotBeFound("tenLoi.equals=" + UPDATED_TEN_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenLoiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi not equals to DEFAULT_TEN_LOI
        defaultProLoiShouldNotBeFound("tenLoi.notEquals=" + DEFAULT_TEN_LOI);

        // Get all the proLoiList where tenLoi not equals to UPDATED_TEN_LOI
        defaultProLoiShouldBeFound("tenLoi.notEquals=" + UPDATED_TEN_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenLoiIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi in DEFAULT_TEN_LOI or UPDATED_TEN_LOI
        defaultProLoiShouldBeFound("tenLoi.in=" + DEFAULT_TEN_LOI + "," + UPDATED_TEN_LOI);

        // Get all the proLoiList where tenLoi equals to UPDATED_TEN_LOI
        defaultProLoiShouldNotBeFound("tenLoi.in=" + UPDATED_TEN_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenLoiIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi is not null
        defaultProLoiShouldBeFound("tenLoi.specified=true");

        // Get all the proLoiList where tenLoi is null
        defaultProLoiShouldNotBeFound("tenLoi.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByTenLoiContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi contains DEFAULT_TEN_LOI
        defaultProLoiShouldBeFound("tenLoi.contains=" + DEFAULT_TEN_LOI);

        // Get all the proLoiList where tenLoi contains UPDATED_TEN_LOI
        defaultProLoiShouldNotBeFound("tenLoi.contains=" + UPDATED_TEN_LOI);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenLoiNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenLoi does not contain DEFAULT_TEN_LOI
        defaultProLoiShouldNotBeFound("tenLoi.doesNotContain=" + DEFAULT_TEN_LOI);

        // Get all the proLoiList where tenLoi does not contain UPDATED_TEN_LOI
        defaultProLoiShouldBeFound("tenLoi.doesNotContain=" + UPDATED_TEN_LOI);
    }


    @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh equals to DEFAULT_TEN_TIENG_ANH
        defaultProLoiShouldBeFound("tenTiengAnh.equals=" + DEFAULT_TEN_TIENG_ANH);

        // Get all the proLoiList where tenTiengAnh equals to UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldNotBeFound("tenTiengAnh.equals=" + UPDATED_TEN_TIENG_ANH);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh not equals to DEFAULT_TEN_TIENG_ANH
        defaultProLoiShouldNotBeFound("tenTiengAnh.notEquals=" + DEFAULT_TEN_TIENG_ANH);

        // Get all the proLoiList where tenTiengAnh not equals to UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldBeFound("tenTiengAnh.notEquals=" + UPDATED_TEN_TIENG_ANH);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh in DEFAULT_TEN_TIENG_ANH or UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldBeFound("tenTiengAnh.in=" + DEFAULT_TEN_TIENG_ANH + "," + UPDATED_TEN_TIENG_ANH);

        // Get all the proLoiList where tenTiengAnh equals to UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldNotBeFound("tenTiengAnh.in=" + UPDATED_TEN_TIENG_ANH);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh is not null
        defaultProLoiShouldBeFound("tenTiengAnh.specified=true");

        // Get all the proLoiList where tenTiengAnh is null
        defaultProLoiShouldNotBeFound("tenTiengAnh.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh contains DEFAULT_TEN_TIENG_ANH
        defaultProLoiShouldBeFound("tenTiengAnh.contains=" + DEFAULT_TEN_TIENG_ANH);

        // Get all the proLoiList where tenTiengAnh contains UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldNotBeFound("tenTiengAnh.contains=" + UPDATED_TEN_TIENG_ANH);
    }

    @Test
    @Transactional
    public void getAllProLoisByTenTiengAnhNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where tenTiengAnh does not contain DEFAULT_TEN_TIENG_ANH
        defaultProLoiShouldNotBeFound("tenTiengAnh.doesNotContain=" + DEFAULT_TEN_TIENG_ANH);

        // Get all the proLoiList where tenTiengAnh does not contain UPDATED_TEN_TIENG_ANH
        defaultProLoiShouldBeFound("tenTiengAnh.doesNotContain=" + UPDATED_TEN_TIENG_ANH);
    }


    @Test
    @Transactional
    public void getAllProLoisByLabelIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label equals to DEFAULT_LABEL
        defaultProLoiShouldBeFound("label.equals=" + DEFAULT_LABEL);

        // Get all the proLoiList where label equals to UPDATED_LABEL
        defaultProLoiShouldNotBeFound("label.equals=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    public void getAllProLoisByLabelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label not equals to DEFAULT_LABEL
        defaultProLoiShouldNotBeFound("label.notEquals=" + DEFAULT_LABEL);

        // Get all the proLoiList where label not equals to UPDATED_LABEL
        defaultProLoiShouldBeFound("label.notEquals=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    public void getAllProLoisByLabelIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label in DEFAULT_LABEL or UPDATED_LABEL
        defaultProLoiShouldBeFound("label.in=" + DEFAULT_LABEL + "," + UPDATED_LABEL);

        // Get all the proLoiList where label equals to UPDATED_LABEL
        defaultProLoiShouldNotBeFound("label.in=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    public void getAllProLoisByLabelIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label is not null
        defaultProLoiShouldBeFound("label.specified=true");

        // Get all the proLoiList where label is null
        defaultProLoiShouldNotBeFound("label.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByLabelContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label contains DEFAULT_LABEL
        defaultProLoiShouldBeFound("label.contains=" + DEFAULT_LABEL);

        // Get all the proLoiList where label contains UPDATED_LABEL
        defaultProLoiShouldNotBeFound("label.contains=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    public void getAllProLoisByLabelNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where label does not contain DEFAULT_LABEL
        defaultProLoiShouldNotBeFound("label.doesNotContain=" + DEFAULT_LABEL);

        // Get all the proLoiList where label does not contain UPDATED_LABEL
        defaultProLoiShouldBeFound("label.doesNotContain=" + UPDATED_LABEL);
    }


    @Test
    @Transactional
    public void getAllProLoisBySttIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt equals to DEFAULT_STT
        defaultProLoiShouldBeFound("stt.equals=" + DEFAULT_STT);

        // Get all the proLoiList where stt equals to UPDATED_STT
        defaultProLoiShouldNotBeFound("stt.equals=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt not equals to DEFAULT_STT
        defaultProLoiShouldNotBeFound("stt.notEquals=" + DEFAULT_STT);

        // Get all the proLoiList where stt not equals to UPDATED_STT
        defaultProLoiShouldBeFound("stt.notEquals=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt in DEFAULT_STT or UPDATED_STT
        defaultProLoiShouldBeFound("stt.in=" + DEFAULT_STT + "," + UPDATED_STT);

        // Get all the proLoiList where stt equals to UPDATED_STT
        defaultProLoiShouldNotBeFound("stt.in=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt is not null
        defaultProLoiShouldBeFound("stt.specified=true");

        // Get all the proLoiList where stt is null
        defaultProLoiShouldNotBeFound("stt.specified=false");
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt is greater than or equal to DEFAULT_STT
        defaultProLoiShouldBeFound("stt.greaterThanOrEqual=" + DEFAULT_STT);

        // Get all the proLoiList where stt is greater than or equal to UPDATED_STT
        defaultProLoiShouldNotBeFound("stt.greaterThanOrEqual=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt is less than or equal to DEFAULT_STT
        defaultProLoiShouldBeFound("stt.lessThanOrEqual=" + DEFAULT_STT);

        // Get all the proLoiList where stt is less than or equal to SMALLER_STT
        defaultProLoiShouldNotBeFound("stt.lessThanOrEqual=" + SMALLER_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsLessThanSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt is less than DEFAULT_STT
        defaultProLoiShouldNotBeFound("stt.lessThan=" + DEFAULT_STT);

        // Get all the proLoiList where stt is less than UPDATED_STT
        defaultProLoiShouldBeFound("stt.lessThan=" + UPDATED_STT);
    }

    @Test
    @Transactional
    public void getAllProLoisBySttIsGreaterThanSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where stt is greater than DEFAULT_STT
        defaultProLoiShouldNotBeFound("stt.greaterThan=" + DEFAULT_STT);

        // Get all the proLoiList where stt is greater than SMALLER_STT
        defaultProLoiShouldBeFound("stt.greaterThan=" + SMALLER_STT);
    }


    @Test
    @Transactional
    public void getAllProLoisByMauSacIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac equals to DEFAULT_MAU_SAC
        defaultProLoiShouldBeFound("mauSac.equals=" + DEFAULT_MAU_SAC);

        // Get all the proLoiList where mauSac equals to UPDATED_MAU_SAC
        defaultProLoiShouldNotBeFound("mauSac.equals=" + UPDATED_MAU_SAC);
    }

    @Test
    @Transactional
    public void getAllProLoisByMauSacIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac not equals to DEFAULT_MAU_SAC
        defaultProLoiShouldNotBeFound("mauSac.notEquals=" + DEFAULT_MAU_SAC);

        // Get all the proLoiList where mauSac not equals to UPDATED_MAU_SAC
        defaultProLoiShouldBeFound("mauSac.notEquals=" + UPDATED_MAU_SAC);
    }

    @Test
    @Transactional
    public void getAllProLoisByMauSacIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac in DEFAULT_MAU_SAC or UPDATED_MAU_SAC
        defaultProLoiShouldBeFound("mauSac.in=" + DEFAULT_MAU_SAC + "," + UPDATED_MAU_SAC);

        // Get all the proLoiList where mauSac equals to UPDATED_MAU_SAC
        defaultProLoiShouldNotBeFound("mauSac.in=" + UPDATED_MAU_SAC);
    }

    @Test
    @Transactional
    public void getAllProLoisByMauSacIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac is not null
        defaultProLoiShouldBeFound("mauSac.specified=true");

        // Get all the proLoiList where mauSac is null
        defaultProLoiShouldNotBeFound("mauSac.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByMauSacContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac contains DEFAULT_MAU_SAC
        defaultProLoiShouldBeFound("mauSac.contains=" + DEFAULT_MAU_SAC);

        // Get all the proLoiList where mauSac contains UPDATED_MAU_SAC
        defaultProLoiShouldNotBeFound("mauSac.contains=" + UPDATED_MAU_SAC);
    }

    @Test
    @Transactional
    public void getAllProLoisByMauSacNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where mauSac does not contain DEFAULT_MAU_SAC
        defaultProLoiShouldNotBeFound("mauSac.doesNotContain=" + DEFAULT_MAU_SAC);

        // Get all the proLoiList where mauSac does not contain UPDATED_MAU_SAC
        defaultProLoiShouldBeFound("mauSac.doesNotContain=" + UPDATED_MAU_SAC);
    }


    @Test
    @Transactional
    public void getAllProLoisByQcIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qc equals to DEFAULT_QC
        defaultProLoiShouldBeFound("qc.equals=" + DEFAULT_QC);

        // Get all the proLoiList where qc equals to UPDATED_QC
        defaultProLoiShouldNotBeFound("qc.equals=" + UPDATED_QC);
    }

    @Test
    @Transactional
    public void getAllProLoisByQcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qc not equals to DEFAULT_QC
        defaultProLoiShouldNotBeFound("qc.notEquals=" + DEFAULT_QC);

        // Get all the proLoiList where qc not equals to UPDATED_QC
        defaultProLoiShouldBeFound("qc.notEquals=" + UPDATED_QC);
    }

    @Test
    @Transactional
    public void getAllProLoisByQcIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qc in DEFAULT_QC or UPDATED_QC
        defaultProLoiShouldBeFound("qc.in=" + DEFAULT_QC + "," + UPDATED_QC);

        // Get all the proLoiList where qc equals to UPDATED_QC
        defaultProLoiShouldNotBeFound("qc.in=" + UPDATED_QC);
    }

    @Test
    @Transactional
    public void getAllProLoisByQcIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qc is not null
        defaultProLoiShouldBeFound("qc.specified=true");

        // Get all the proLoiList where qc is null
        defaultProLoiShouldNotBeFound("qc.specified=false");
    }

    @Test
    @Transactional
    public void getAllProLoisByQaIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qa equals to DEFAULT_QA
        defaultProLoiShouldBeFound("qa.equals=" + DEFAULT_QA);

        // Get all the proLoiList where qa equals to UPDATED_QA
        defaultProLoiShouldNotBeFound("qa.equals=" + UPDATED_QA);
    }

    @Test
    @Transactional
    public void getAllProLoisByQaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qa not equals to DEFAULT_QA
        defaultProLoiShouldNotBeFound("qa.notEquals=" + DEFAULT_QA);

        // Get all the proLoiList where qa not equals to UPDATED_QA
        defaultProLoiShouldBeFound("qa.notEquals=" + UPDATED_QA);
    }

    @Test
    @Transactional
    public void getAllProLoisByQaIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qa in DEFAULT_QA or UPDATED_QA
        defaultProLoiShouldBeFound("qa.in=" + DEFAULT_QA + "," + UPDATED_QA);

        // Get all the proLoiList where qa equals to UPDATED_QA
        defaultProLoiShouldNotBeFound("qa.in=" + UPDATED_QA);
    }

    @Test
    @Transactional
    public void getAllProLoisByQaIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where qa is not null
        defaultProLoiShouldBeFound("qa.specified=true");

        // Get all the proLoiList where qa is null
        defaultProLoiShouldNotBeFound("qa.specified=false");
    }

    @Test
    @Transactional
    public void getAllProLoisByCheckedIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where checked equals to DEFAULT_CHECKED
        defaultProLoiShouldBeFound("checked.equals=" + DEFAULT_CHECKED);

        // Get all the proLoiList where checked equals to UPDATED_CHECKED
        defaultProLoiShouldNotBeFound("checked.equals=" + UPDATED_CHECKED);
    }

    @Test
    @Transactional
    public void getAllProLoisByCheckedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where checked not equals to DEFAULT_CHECKED
        defaultProLoiShouldNotBeFound("checked.notEquals=" + DEFAULT_CHECKED);

        // Get all the proLoiList where checked not equals to UPDATED_CHECKED
        defaultProLoiShouldBeFound("checked.notEquals=" + UPDATED_CHECKED);
    }

    @Test
    @Transactional
    public void getAllProLoisByCheckedIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where checked in DEFAULT_CHECKED or UPDATED_CHECKED
        defaultProLoiShouldBeFound("checked.in=" + DEFAULT_CHECKED + "," + UPDATED_CHECKED);

        // Get all the proLoiList where checked equals to UPDATED_CHECKED
        defaultProLoiShouldNotBeFound("checked.in=" + UPDATED_CHECKED);
    }

    @Test
    @Transactional
    public void getAllProLoisByCheckedIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where checked is not null
        defaultProLoiShouldBeFound("checked.specified=true");

        // Get all the proLoiList where checked is null
        defaultProLoiShouldNotBeFound("checked.specified=false");
    }

    @Test
    @Transactional
    public void getAllProLoisByGhiChuIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu equals to DEFAULT_GHI_CHU
        defaultProLoiShouldBeFound("ghiChu.equals=" + DEFAULT_GHI_CHU);

        // Get all the proLoiList where ghiChu equals to UPDATED_GHI_CHU
        defaultProLoiShouldNotBeFound("ghiChu.equals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProLoisByGhiChuIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu not equals to DEFAULT_GHI_CHU
        defaultProLoiShouldNotBeFound("ghiChu.notEquals=" + DEFAULT_GHI_CHU);

        // Get all the proLoiList where ghiChu not equals to UPDATED_GHI_CHU
        defaultProLoiShouldBeFound("ghiChu.notEquals=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProLoisByGhiChuIsInShouldWork() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu in DEFAULT_GHI_CHU or UPDATED_GHI_CHU
        defaultProLoiShouldBeFound("ghiChu.in=" + DEFAULT_GHI_CHU + "," + UPDATED_GHI_CHU);

        // Get all the proLoiList where ghiChu equals to UPDATED_GHI_CHU
        defaultProLoiShouldNotBeFound("ghiChu.in=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProLoisByGhiChuIsNullOrNotNull() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu is not null
        defaultProLoiShouldBeFound("ghiChu.specified=true");

        // Get all the proLoiList where ghiChu is null
        defaultProLoiShouldNotBeFound("ghiChu.specified=false");
    }
                @Test
    @Transactional
    public void getAllProLoisByGhiChuContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu contains DEFAULT_GHI_CHU
        defaultProLoiShouldBeFound("ghiChu.contains=" + DEFAULT_GHI_CHU);

        // Get all the proLoiList where ghiChu contains UPDATED_GHI_CHU
        defaultProLoiShouldNotBeFound("ghiChu.contains=" + UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void getAllProLoisByGhiChuNotContainsSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        // Get all the proLoiList where ghiChu does not contain DEFAULT_GHI_CHU
        defaultProLoiShouldNotBeFound("ghiChu.doesNotContain=" + DEFAULT_GHI_CHU);

        // Get all the proLoiList where ghiChu does not contain UPDATED_GHI_CHU
        defaultProLoiShouldBeFound("ghiChu.doesNotContain=" + UPDATED_GHI_CHU);
    }


    @Test
    @Transactional
    public void getAllProLoisByProCumLoiIsEqualToSomething() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);
        ProCumLoi proCumLoi = ProCumLoiResourceIT.createEntity(em);
        em.persist(proCumLoi);
        em.flush();
        proLoi.setProCumLoi(proCumLoi);
        proLoiRepository.saveAndFlush(proLoi);
        Long proCumLoiId = proCumLoi.getId();

        // Get all the proLoiList where proCumLoi equals to proCumLoiId
        defaultProLoiShouldBeFound("proCumLoiId.equals=" + proCumLoiId);

        // Get all the proLoiList where proCumLoi equals to proCumLoiId + 1
        defaultProLoiShouldNotBeFound("proCumLoiId.equals=" + (proCumLoiId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProLoiShouldBeFound(String filter) throws Exception {
        restProLoiMockMvc.perform(get("/api/pro-lois?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].cusCode").value(hasItem(DEFAULT_CUS_CODE)))
            .andExpect(jsonPath("$.[*].tenLoi").value(hasItem(DEFAULT_TEN_LOI)))
            .andExpect(jsonPath("$.[*].tenTiengAnh").value(hasItem(DEFAULT_TEN_TIENG_ANH)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].mauSac").value(hasItem(DEFAULT_MAU_SAC)))
            .andExpect(jsonPath("$.[*].qc").value(hasItem(DEFAULT_QC.booleanValue())))
            .andExpect(jsonPath("$.[*].qa").value(hasItem(DEFAULT_QA.booleanValue())))
            .andExpect(jsonPath("$.[*].checked").value(hasItem(DEFAULT_CHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)));

        // Check, that the count call also returns 1
        restProLoiMockMvc.perform(get("/api/pro-lois/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProLoiShouldNotBeFound(String filter) throws Exception {
        restProLoiMockMvc.perform(get("/api/pro-lois?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProLoiMockMvc.perform(get("/api/pro-lois/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProLoi() throws Exception {
        // Get the proLoi
        restProLoiMockMvc.perform(get("/api/pro-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProLoi() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        int databaseSizeBeforeUpdate = proLoiRepository.findAll().size();

        // Update the proLoi
        ProLoi updatedProLoi = proLoiRepository.findById(proLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProLoi are not directly saved in db
        em.detach(updatedProLoi);
        updatedProLoi
            .maLoi(UPDATED_MA_LOI)
            .cusCode(UPDATED_CUS_CODE)
            .tenLoi(UPDATED_TEN_LOI)
            .tenTiengAnh(UPDATED_TEN_TIENG_ANH)
            .label(UPDATED_LABEL)
            .stt(UPDATED_STT)
            .mauSac(UPDATED_MAU_SAC)
            .qc(UPDATED_QC)
            .qa(UPDATED_QA)
            .checked(UPDATED_CHECKED)
            .ghiChu(UPDATED_GHI_CHU);
        ProLoiDTO proLoiDTO = proLoiMapper.toDto(updatedProLoi);

        restProLoiMockMvc.perform(put("/api/pro-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProLoi in the database
        List<ProLoi> proLoiList = proLoiRepository.findAll();
        assertThat(proLoiList).hasSize(databaseSizeBeforeUpdate);
        ProLoi testProLoi = proLoiList.get(proLoiList.size() - 1);
        assertThat(testProLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProLoi.getCusCode()).isEqualTo(UPDATED_CUS_CODE);
        assertThat(testProLoi.getTenLoi()).isEqualTo(UPDATED_TEN_LOI);
        assertThat(testProLoi.getTenTiengAnh()).isEqualTo(UPDATED_TEN_TIENG_ANH);
        assertThat(testProLoi.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testProLoi.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testProLoi.getMauSac()).isEqualTo(UPDATED_MAU_SAC);
        assertThat(testProLoi.isQc()).isEqualTo(UPDATED_QC);
        assertThat(testProLoi.isQa()).isEqualTo(UPDATED_QA);
        assertThat(testProLoi.isChecked()).isEqualTo(UPDATED_CHECKED);
        assertThat(testProLoi.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void updateNonExistingProLoi() throws Exception {
        int databaseSizeBeforeUpdate = proLoiRepository.findAll().size();

        // Create the ProLoi
        ProLoiDTO proLoiDTO = proLoiMapper.toDto(proLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProLoiMockMvc.perform(put("/api/pro-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProLoi in the database
        List<ProLoi> proLoiList = proLoiRepository.findAll();
        assertThat(proLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProLoi() throws Exception {
        // Initialize the database
        proLoiRepository.saveAndFlush(proLoi);

        int databaseSizeBeforeDelete = proLoiRepository.findAll().size();

        // Delete the proLoi
        restProLoiMockMvc.perform(delete("/api/pro-lois/{id}", proLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProLoi> proLoiList = proLoiRepository.findAll();
        assertThat(proLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
