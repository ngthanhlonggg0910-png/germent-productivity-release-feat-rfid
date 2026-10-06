package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProFinalChiTietLoi;
import vn.nvsoft.repository.ProFinalChiTietLoiRepository;
import vn.nvsoft.service.ProFinalChiTietLoiService;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProFinalChiTietLoiMapper;
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
 * Integration tests for the {@link ProFinalChiTietLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProFinalChiTietLoiResourceIT {

    private static final String DEFAULT_MA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOI = "BBBBBBBBBB";

    private static final Instant DEFAULT_THOI_GIAN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_THOI_GIAN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_DX = 1D;
    private static final Double UPDATED_DX = 2D;

    private static final Double DEFAULT_DY = 1D;
    private static final Double UPDATED_DY = 2D;

    private static final Boolean DEFAULT_IS_FRONT = false;
    private static final Boolean UPDATED_IS_FRONT = true;

    private static final Boolean DEFAULT_IS_INSIDE = false;
    private static final Boolean UPDATED_IS_INSIDE = true;

    private static final Boolean DEFAULT_WARNING = false;
    private static final Boolean UPDATED_WARNING = true;

    private static final String DEFAULT_NGUOI_KIEM = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_KIEM = "BBBBBBBBBB";

    @Autowired
    private ProFinalChiTietLoiRepository proFinalChiTietLoiRepository;

    @Autowired
    private ProFinalChiTietLoiMapper proFinalChiTietLoiMapper;

    @Autowired
    private ProFinalChiTietLoiService proFinalChiTietLoiService;

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

    private MockMvc restProFinalChiTietLoiMockMvc;

    private ProFinalChiTietLoi proFinalChiTietLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProFinalChiTietLoiResource proFinalChiTietLoiResource = new ProFinalChiTietLoiResource(proFinalChiTietLoiService);
        this.restProFinalChiTietLoiMockMvc = MockMvcBuilders.standaloneSetup(proFinalChiTietLoiResource)
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
    public static ProFinalChiTietLoi createEntity(EntityManager em) {
        ProFinalChiTietLoi proFinalChiTietLoi = new ProFinalChiTietLoi()
            .maLoi(DEFAULT_MA_LOI)
            .thoiGian(DEFAULT_THOI_GIAN)
            .dx(DEFAULT_DX)
            .dy(DEFAULT_DY)
            .isFront(DEFAULT_IS_FRONT)
            .isInside(DEFAULT_IS_INSIDE)
            .warning(DEFAULT_WARNING)
            .nguoiKiem(DEFAULT_NGUOI_KIEM);
        return proFinalChiTietLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProFinalChiTietLoi createUpdatedEntity(EntityManager em) {
        ProFinalChiTietLoi proFinalChiTietLoi = new ProFinalChiTietLoi()
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        return proFinalChiTietLoi;
    }

    @BeforeEach
    public void initTest() {
        proFinalChiTietLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProFinalChiTietLoi() throws Exception {
        int databaseSizeBeforeCreate = proFinalChiTietLoiRepository.findAll().size();

        // Create the ProFinalChiTietLoi
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO = proFinalChiTietLoiMapper.toDto(proFinalChiTietLoi);
        restProFinalChiTietLoiMockMvc.perform(post("/api/pro-final-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalChiTietLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProFinalChiTietLoi in the database
        List<ProFinalChiTietLoi> proFinalChiTietLoiList = proFinalChiTietLoiRepository.findAll();
        assertThat(proFinalChiTietLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProFinalChiTietLoi testProFinalChiTietLoi = proFinalChiTietLoiList.get(proFinalChiTietLoiList.size() - 1);
        assertThat(testProFinalChiTietLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProFinalChiTietLoi.getThoiGian()).isEqualTo(DEFAULT_THOI_GIAN);
        assertThat(testProFinalChiTietLoi.getDx()).isEqualTo(DEFAULT_DX);
        assertThat(testProFinalChiTietLoi.getDy()).isEqualTo(DEFAULT_DY);
        assertThat(testProFinalChiTietLoi.isIsFront()).isEqualTo(DEFAULT_IS_FRONT);
        assertThat(testProFinalChiTietLoi.isIsInside()).isEqualTo(DEFAULT_IS_INSIDE);
        assertThat(testProFinalChiTietLoi.isWarning()).isEqualTo(DEFAULT_WARNING);
        assertThat(testProFinalChiTietLoi.getNguoiKiem()).isEqualTo(DEFAULT_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void createProFinalChiTietLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proFinalChiTietLoiRepository.findAll().size();

        // Create the ProFinalChiTietLoi with an existing ID
        proFinalChiTietLoi.setId(1L);
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO = proFinalChiTietLoiMapper.toDto(proFinalChiTietLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProFinalChiTietLoiMockMvc.perform(post("/api/pro-final-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProFinalChiTietLoi in the database
        List<ProFinalChiTietLoi> proFinalChiTietLoiList = proFinalChiTietLoiRepository.findAll();
        assertThat(proFinalChiTietLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProFinalChiTietLois() throws Exception {
        // Initialize the database
        proFinalChiTietLoiRepository.saveAndFlush(proFinalChiTietLoi);

        // Get all the proFinalChiTietLoiList
        restProFinalChiTietLoiMockMvc.perform(get("/api/pro-final-chi-tiet-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proFinalChiTietLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].thoiGian").value(hasItem(DEFAULT_THOI_GIAN.toString())))
            .andExpect(jsonPath("$.[*].dx").value(hasItem(DEFAULT_DX.doubleValue())))
            .andExpect(jsonPath("$.[*].dy").value(hasItem(DEFAULT_DY.doubleValue())))
            .andExpect(jsonPath("$.[*].isFront").value(hasItem(DEFAULT_IS_FRONT.booleanValue())))
            .andExpect(jsonPath("$.[*].isInside").value(hasItem(DEFAULT_IS_INSIDE.booleanValue())))
            .andExpect(jsonPath("$.[*].warning").value(hasItem(DEFAULT_WARNING.booleanValue())))
            .andExpect(jsonPath("$.[*].nguoiKiem").value(hasItem(DEFAULT_NGUOI_KIEM)));
    }
    
    @Test
    @Transactional
    public void getProFinalChiTietLoi() throws Exception {
        // Initialize the database
        proFinalChiTietLoiRepository.saveAndFlush(proFinalChiTietLoi);

        // Get the proFinalChiTietLoi
        restProFinalChiTietLoiMockMvc.perform(get("/api/pro-final-chi-tiet-lois/{id}", proFinalChiTietLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proFinalChiTietLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.thoiGian").value(DEFAULT_THOI_GIAN.toString()))
            .andExpect(jsonPath("$.dx").value(DEFAULT_DX.doubleValue()))
            .andExpect(jsonPath("$.dy").value(DEFAULT_DY.doubleValue()))
            .andExpect(jsonPath("$.isFront").value(DEFAULT_IS_FRONT.booleanValue()))
            .andExpect(jsonPath("$.isInside").value(DEFAULT_IS_INSIDE.booleanValue()))
            .andExpect(jsonPath("$.warning").value(DEFAULT_WARNING.booleanValue()))
            .andExpect(jsonPath("$.nguoiKiem").value(DEFAULT_NGUOI_KIEM));
    }

    @Test
    @Transactional
    public void getNonExistingProFinalChiTietLoi() throws Exception {
        // Get the proFinalChiTietLoi
        restProFinalChiTietLoiMockMvc.perform(get("/api/pro-final-chi-tiet-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProFinalChiTietLoi() throws Exception {
        // Initialize the database
        proFinalChiTietLoiRepository.saveAndFlush(proFinalChiTietLoi);

        int databaseSizeBeforeUpdate = proFinalChiTietLoiRepository.findAll().size();

        // Update the proFinalChiTietLoi
        ProFinalChiTietLoi updatedProFinalChiTietLoi = proFinalChiTietLoiRepository.findById(proFinalChiTietLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProFinalChiTietLoi are not directly saved in db
        em.detach(updatedProFinalChiTietLoi);
        updatedProFinalChiTietLoi
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO = proFinalChiTietLoiMapper.toDto(updatedProFinalChiTietLoi);

        restProFinalChiTietLoiMockMvc.perform(put("/api/pro-final-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalChiTietLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProFinalChiTietLoi in the database
        List<ProFinalChiTietLoi> proFinalChiTietLoiList = proFinalChiTietLoiRepository.findAll();
        assertThat(proFinalChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
        ProFinalChiTietLoi testProFinalChiTietLoi = proFinalChiTietLoiList.get(proFinalChiTietLoiList.size() - 1);
        assertThat(testProFinalChiTietLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProFinalChiTietLoi.getThoiGian()).isEqualTo(UPDATED_THOI_GIAN);
        assertThat(testProFinalChiTietLoi.getDx()).isEqualTo(UPDATED_DX);
        assertThat(testProFinalChiTietLoi.getDy()).isEqualTo(UPDATED_DY);
        assertThat(testProFinalChiTietLoi.isIsFront()).isEqualTo(UPDATED_IS_FRONT);
        assertThat(testProFinalChiTietLoi.isIsInside()).isEqualTo(UPDATED_IS_INSIDE);
        assertThat(testProFinalChiTietLoi.isWarning()).isEqualTo(UPDATED_WARNING);
        assertThat(testProFinalChiTietLoi.getNguoiKiem()).isEqualTo(UPDATED_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingProFinalChiTietLoi() throws Exception {
        int databaseSizeBeforeUpdate = proFinalChiTietLoiRepository.findAll().size();

        // Create the ProFinalChiTietLoi
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO = proFinalChiTietLoiMapper.toDto(proFinalChiTietLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProFinalChiTietLoiMockMvc.perform(put("/api/pro-final-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proFinalChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProFinalChiTietLoi in the database
        List<ProFinalChiTietLoi> proFinalChiTietLoiList = proFinalChiTietLoiRepository.findAll();
        assertThat(proFinalChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProFinalChiTietLoi() throws Exception {
        // Initialize the database
        proFinalChiTietLoiRepository.saveAndFlush(proFinalChiTietLoi);

        int databaseSizeBeforeDelete = proFinalChiTietLoiRepository.findAll().size();

        // Delete the proFinalChiTietLoi
        restProFinalChiTietLoiMockMvc.perform(delete("/api/pro-final-chi-tiet-lois/{id}", proFinalChiTietLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProFinalChiTietLoi> proFinalChiTietLoiList = proFinalChiTietLoiRepository.findAll();
        assertThat(proFinalChiTietLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
