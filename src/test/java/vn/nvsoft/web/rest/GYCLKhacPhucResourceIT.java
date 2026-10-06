package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.GYCLKhacPhuc;
import vn.nvsoft.repository.GYCLKhacPhucRepository;
import vn.nvsoft.service.GYCLKhacPhucService;
import vn.nvsoft.service.dto.GYCLKhacPhucDTO;
import vn.nvsoft.service.mapper.GYCLKhacPhucMapper;
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
 * Integration tests for the {@link GYCLKhacPhucResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class GYCLKhacPhucResourceIT {

    private static final Integer DEFAULT_CUM_ID = 1;
    private static final Integer UPDATED_CUM_ID = 2;

    private static final String DEFAULT_TEN_CUM = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CUM = "BBBBBBBBBB";

    private static final String DEFAULT_GOP_Y = "AAAAAAAAAA";
    private static final String UPDATED_GOP_Y = "BBBBBBBBBB";

    private static final String DEFAULT_KET_QUA = "AAAAAAAAAA";
    private static final String UPDATED_KET_QUA = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_USER_ID = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_USER_ID = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_TAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private GYCLKhacPhucRepository gYCLKhacPhucRepository;

    @Autowired
    private GYCLKhacPhucMapper gYCLKhacPhucMapper;

    @Autowired
    private GYCLKhacPhucService gYCLKhacPhucService;

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

    private MockMvc restGYCLKhacPhucMockMvc;

    private GYCLKhacPhuc gYCLKhacPhuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GYCLKhacPhucResource gYCLKhacPhucResource = new GYCLKhacPhucResource(gYCLKhacPhucService);
        this.restGYCLKhacPhucMockMvc = MockMvcBuilders.standaloneSetup(gYCLKhacPhucResource)
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
    public static GYCLKhacPhuc createEntity(EntityManager em) {
        GYCLKhacPhuc gYCLKhacPhuc = new GYCLKhacPhuc()
            .cumId(DEFAULT_CUM_ID)
            .tenCum(DEFAULT_TEN_CUM)
            .gopY(DEFAULT_GOP_Y)
            .ketQua(DEFAULT_KET_QUA)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .userId(DEFAULT_USER_ID)
            .ngayTao(DEFAULT_NGAY_TAO);
        return gYCLKhacPhuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GYCLKhacPhuc createUpdatedEntity(EntityManager em) {
        GYCLKhacPhuc gYCLKhacPhuc = new GYCLKhacPhuc()
            .cumId(UPDATED_CUM_ID)
            .tenCum(UPDATED_TEN_CUM)
            .gopY(UPDATED_GOP_Y)
            .ketQua(UPDATED_KET_QUA)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .userId(UPDATED_USER_ID)
            .ngayTao(UPDATED_NGAY_TAO);
        return gYCLKhacPhuc;
    }

    @BeforeEach
    public void initTest() {
        gYCLKhacPhuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createGYCLKhacPhuc() throws Exception {
        int databaseSizeBeforeCreate = gYCLKhacPhucRepository.findAll().size();

        // Create the GYCLKhacPhuc
        GYCLKhacPhucDTO gYCLKhacPhucDTO = gYCLKhacPhucMapper.toDto(gYCLKhacPhuc);
        restGYCLKhacPhucMockMvc.perform(post("/api/gycl-khac-phucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLKhacPhucDTO)))
            .andExpect(status().isCreated());

        // Validate the GYCLKhacPhuc in the database
        List<GYCLKhacPhuc> gYCLKhacPhucList = gYCLKhacPhucRepository.findAll();
        assertThat(gYCLKhacPhucList).hasSize(databaseSizeBeforeCreate + 1);
        GYCLKhacPhuc testGYCLKhacPhuc = gYCLKhacPhucList.get(gYCLKhacPhucList.size() - 1);
        assertThat(testGYCLKhacPhuc.getCumId()).isEqualTo(DEFAULT_CUM_ID);
        assertThat(testGYCLKhacPhuc.getTenCum()).isEqualTo(DEFAULT_TEN_CUM);
        assertThat(testGYCLKhacPhuc.getGopY()).isEqualTo(DEFAULT_GOP_Y);
        assertThat(testGYCLKhacPhuc.getKetQua()).isEqualTo(DEFAULT_KET_QUA);
        assertThat(testGYCLKhacPhuc.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testGYCLKhacPhuc.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGYCLKhacPhuc.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
    }

    @Test
    @Transactional
    public void createGYCLKhacPhucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gYCLKhacPhucRepository.findAll().size();

        // Create the GYCLKhacPhuc with an existing ID
        gYCLKhacPhuc.setId(1L);
        GYCLKhacPhucDTO gYCLKhacPhucDTO = gYCLKhacPhucMapper.toDto(gYCLKhacPhuc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGYCLKhacPhucMockMvc.perform(post("/api/gycl-khac-phucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLKhacPhucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GYCLKhacPhuc in the database
        List<GYCLKhacPhuc> gYCLKhacPhucList = gYCLKhacPhucRepository.findAll();
        assertThat(gYCLKhacPhucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGYCLKhacPhucs() throws Exception {
        // Initialize the database
        gYCLKhacPhucRepository.saveAndFlush(gYCLKhacPhuc);

        // Get all the gYCLKhacPhucList
        restGYCLKhacPhucMockMvc.perform(get("/api/gycl-khac-phucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gYCLKhacPhuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].cumId").value(hasItem(DEFAULT_CUM_ID)))
            .andExpect(jsonPath("$.[*].tenCum").value(hasItem(DEFAULT_TEN_CUM)))
            .andExpect(jsonPath("$.[*].gopY").value(hasItem(DEFAULT_GOP_Y)))
            .andExpect(jsonPath("$.[*].ketQua").value(hasItem(DEFAULT_KET_QUA)))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())));
    }
    
    @Test
    @Transactional
    public void getGYCLKhacPhuc() throws Exception {
        // Initialize the database
        gYCLKhacPhucRepository.saveAndFlush(gYCLKhacPhuc);

        // Get the gYCLKhacPhuc
        restGYCLKhacPhucMockMvc.perform(get("/api/gycl-khac-phucs/{id}", gYCLKhacPhuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gYCLKhacPhuc.getId().intValue()))
            .andExpect(jsonPath("$.cumId").value(DEFAULT_CUM_ID))
            .andExpect(jsonPath("$.tenCum").value(DEFAULT_TEN_CUM))
            .andExpect(jsonPath("$.gopY").value(DEFAULT_GOP_Y))
            .andExpect(jsonPath("$.ketQua").value(DEFAULT_KET_QUA))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGYCLKhacPhuc() throws Exception {
        // Get the gYCLKhacPhuc
        restGYCLKhacPhucMockMvc.perform(get("/api/gycl-khac-phucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGYCLKhacPhuc() throws Exception {
        // Initialize the database
        gYCLKhacPhucRepository.saveAndFlush(gYCLKhacPhuc);

        int databaseSizeBeforeUpdate = gYCLKhacPhucRepository.findAll().size();

        // Update the gYCLKhacPhuc
        GYCLKhacPhuc updatedGYCLKhacPhuc = gYCLKhacPhucRepository.findById(gYCLKhacPhuc.getId()).get();
        // Disconnect from session so that the updates on updatedGYCLKhacPhuc are not directly saved in db
        em.detach(updatedGYCLKhacPhuc);
        updatedGYCLKhacPhuc
            .cumId(UPDATED_CUM_ID)
            .tenCum(UPDATED_TEN_CUM)
            .gopY(UPDATED_GOP_Y)
            .ketQua(UPDATED_KET_QUA)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .userId(UPDATED_USER_ID)
            .ngayTao(UPDATED_NGAY_TAO);
        GYCLKhacPhucDTO gYCLKhacPhucDTO = gYCLKhacPhucMapper.toDto(updatedGYCLKhacPhuc);

        restGYCLKhacPhucMockMvc.perform(put("/api/gycl-khac-phucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLKhacPhucDTO)))
            .andExpect(status().isOk());

        // Validate the GYCLKhacPhuc in the database
        List<GYCLKhacPhuc> gYCLKhacPhucList = gYCLKhacPhucRepository.findAll();
        assertThat(gYCLKhacPhucList).hasSize(databaseSizeBeforeUpdate);
        GYCLKhacPhuc testGYCLKhacPhuc = gYCLKhacPhucList.get(gYCLKhacPhucList.size() - 1);
        assertThat(testGYCLKhacPhuc.getCumId()).isEqualTo(UPDATED_CUM_ID);
        assertThat(testGYCLKhacPhuc.getTenCum()).isEqualTo(UPDATED_TEN_CUM);
        assertThat(testGYCLKhacPhuc.getGopY()).isEqualTo(UPDATED_GOP_Y);
        assertThat(testGYCLKhacPhuc.getKetQua()).isEqualTo(UPDATED_KET_QUA);
        assertThat(testGYCLKhacPhuc.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testGYCLKhacPhuc.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGYCLKhacPhuc.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
    }

    @Test
    @Transactional
    public void updateNonExistingGYCLKhacPhuc() throws Exception {
        int databaseSizeBeforeUpdate = gYCLKhacPhucRepository.findAll().size();

        // Create the GYCLKhacPhuc
        GYCLKhacPhucDTO gYCLKhacPhucDTO = gYCLKhacPhucMapper.toDto(gYCLKhacPhuc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGYCLKhacPhucMockMvc.perform(put("/api/gycl-khac-phucs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLKhacPhucDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GYCLKhacPhuc in the database
        List<GYCLKhacPhuc> gYCLKhacPhucList = gYCLKhacPhucRepository.findAll();
        assertThat(gYCLKhacPhucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGYCLKhacPhuc() throws Exception {
        // Initialize the database
        gYCLKhacPhucRepository.saveAndFlush(gYCLKhacPhuc);

        int databaseSizeBeforeDelete = gYCLKhacPhucRepository.findAll().size();

        // Delete the gYCLKhacPhuc
        restGYCLKhacPhucMockMvc.perform(delete("/api/gycl-khac-phucs/{id}", gYCLKhacPhuc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GYCLKhacPhuc> gYCLKhacPhucList = gYCLKhacPhucRepository.findAll();
        assertThat(gYCLKhacPhucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
