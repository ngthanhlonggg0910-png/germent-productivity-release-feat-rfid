package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.CauHinhZalo;
import vn.nvsoft.repository.CauHinhZaloRepository;
import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.service.dto.CauHinhZaloDTO;
import vn.nvsoft.service.mapper.CauHinhZaloMapper;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CauHinhZaloResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class CauHinhZaloResourceIT {

    private static final String DEFAULT_ACCESS_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_REFRESH_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_REFRESH_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_ZALO_OA = "AAAAAAAAAA";
    private static final String UPDATED_ZALO_OA = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SECRET_KEY = "BBBBBBBBBB";

    private static final Long DEFAULT_APP_ID = 1L;
    private static final Long UPDATED_APP_ID = 2L;

    private static final LocalDateTime DEFAULT_EXPIRE_TIME = LocalDateTime.now();
    private static final LocalDateTime UPDATED_EXPIRE_TIME = LocalDateTime.now(ZoneId.systemDefault());

    @Autowired
    private CauHinhZaloRepository cauHinhZaloRepository;

    @Autowired
    private CauHinhZaloMapper cauHinhZaloMapper;

    @Autowired
    private CauHinhZaloService cauHinhZaloService;

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

    private MockMvc restCauHinhZaloMockMvc;

    private CauHinhZalo cauHinhZalo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CauHinhZaloResource cauHinhZaloResource = new CauHinhZaloResource(cauHinhZaloService);
        this.restCauHinhZaloMockMvc = MockMvcBuilders.standaloneSetup(cauHinhZaloResource)
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
    public static CauHinhZalo createEntity(EntityManager em) {
        CauHinhZalo cauHinhZalo = new CauHinhZalo()
            .accessToken(DEFAULT_ACCESS_TOKEN)
            .refreshToken(DEFAULT_REFRESH_TOKEN)
            .zaloOA(DEFAULT_ZALO_OA)
            .secretKey(DEFAULT_SECRET_KEY)
            .appId(DEFAULT_APP_ID)
            .expireTime(DEFAULT_EXPIRE_TIME);
        return cauHinhZalo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhZalo createUpdatedEntity(EntityManager em) {
        CauHinhZalo cauHinhZalo = new CauHinhZalo()
            .accessToken(UPDATED_ACCESS_TOKEN)
            .refreshToken(UPDATED_REFRESH_TOKEN)
            .zaloOA(UPDATED_ZALO_OA)
            .secretKey(UPDATED_SECRET_KEY)
            .appId(UPDATED_APP_ID)
            .expireTime(UPDATED_EXPIRE_TIME);
        return cauHinhZalo;
    }

    @BeforeEach
    public void initTest() {
        cauHinhZalo = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllCauHinhZalos() throws Exception {
        // Initialize the database
        cauHinhZaloRepository.saveAndFlush(cauHinhZalo);

        // Get all the cauHinhZaloList
        restCauHinhZaloMockMvc.perform(get("/api/cau-hinh-zalos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHinhZalo.getId().intValue())))
            .andExpect(jsonPath("$.[*].accessToken").value(hasItem(DEFAULT_ACCESS_TOKEN)))
            .andExpect(jsonPath("$.[*].refreshToken").value(hasItem(DEFAULT_REFRESH_TOKEN)))
            .andExpect(jsonPath("$.[*].zaloOA").value(hasItem(DEFAULT_ZALO_OA)))
            .andExpect(jsonPath("$.[*].secretKey").value(hasItem(DEFAULT_SECRET_KEY)))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID.intValue())))
            .andExpect(jsonPath("$.[*].expireTime").value(hasItem(DEFAULT_EXPIRE_TIME.toString())));
    }

    @Test
    @Transactional
    public void getCauHinhZalo() throws Exception {
        // Initialize the database
        cauHinhZaloRepository.saveAndFlush(cauHinhZalo);

        // Get the cauHinhZalo
        restCauHinhZaloMockMvc.perform(get("/api/cau-hinh-zalos/{id}", cauHinhZalo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cauHinhZalo.getId().intValue()))
            .andExpect(jsonPath("$.accessToken").value(DEFAULT_ACCESS_TOKEN))
            .andExpect(jsonPath("$.refreshToken").value(DEFAULT_REFRESH_TOKEN))
            .andExpect(jsonPath("$.zaloOA").value(DEFAULT_ZALO_OA))
            .andExpect(jsonPath("$.secretKey").value(DEFAULT_SECRET_KEY))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID.intValue()))
            .andExpect(jsonPath("$.expireTime").value(DEFAULT_EXPIRE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCauHinhZalo() throws Exception {
        // Get the cauHinhZalo
        restCauHinhZaloMockMvc.perform(get("/api/cau-hinh-zalos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
