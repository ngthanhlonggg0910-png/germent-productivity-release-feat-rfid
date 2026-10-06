package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProRefinalCCChiTietLoi;
import vn.nvsoft.repository.ProRefinalCCChiTietLoiRepository;
import vn.nvsoft.service.ProRefinalCCChiTietLoiService;
import vn.nvsoft.service.dto.ProRefinalCCChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProRefinalCCChiTietLoiMapper;
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
 * Integration tests for the {@link ProRefinalCCChiTietLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProRefinalCCChiTietLoiResourceIT {

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
    private ProRefinalCCChiTietLoiRepository proRefinalCCChiTietLoiRepository;

    @Autowired
    private ProRefinalCCChiTietLoiMapper proRefinalCCChiTietLoiMapper;

    @Autowired
    private ProRefinalCCChiTietLoiService proRefinalCCChiTietLoiService;

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

    private MockMvc restProRefinalCCChiTietLoiMockMvc;

    private ProRefinalCCChiTietLoi proRefinalCCChiTietLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProRefinalCCChiTietLoiResource proRefinalCCChiTietLoiResource = new ProRefinalCCChiTietLoiResource(proRefinalCCChiTietLoiService);
        this.restProRefinalCCChiTietLoiMockMvc = MockMvcBuilders.standaloneSetup(proRefinalCCChiTietLoiResource)
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
    public static ProRefinalCCChiTietLoi createEntity(EntityManager em) {
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi = new ProRefinalCCChiTietLoi()
            .maLoi(DEFAULT_MA_LOI)
            .thoiGian(DEFAULT_THOI_GIAN)
            .dx(DEFAULT_DX)
            .dy(DEFAULT_DY)
            .isFront(DEFAULT_IS_FRONT)
            .isInside(DEFAULT_IS_INSIDE)
            .warning(DEFAULT_WARNING)
            .nguoiKiem(DEFAULT_NGUOI_KIEM);
        return proRefinalCCChiTietLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProRefinalCCChiTietLoi createUpdatedEntity(EntityManager em) {
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi = new ProRefinalCCChiTietLoi()
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        return proRefinalCCChiTietLoi;
    }

    @BeforeEach
    public void initTest() {
        proRefinalCCChiTietLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProRefinalCCChiTietLoi() throws Exception {
        int databaseSizeBeforeCreate = proRefinalCCChiTietLoiRepository.findAll().size();

        // Create the ProRefinalCCChiTietLoi
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiMapper.toDto(proRefinalCCChiTietLoi);
        restProRefinalCCChiTietLoiMockMvc.perform(post("/api/pro-refinal-cc-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCChiTietLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProRefinalCCChiTietLoi in the database
        List<ProRefinalCCChiTietLoi> proRefinalCCChiTietLoiList = proRefinalCCChiTietLoiRepository.findAll();
        assertThat(proRefinalCCChiTietLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProRefinalCCChiTietLoi testProRefinalCCChiTietLoi = proRefinalCCChiTietLoiList.get(proRefinalCCChiTietLoiList.size() - 1);
        assertThat(testProRefinalCCChiTietLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProRefinalCCChiTietLoi.getThoiGian()).isEqualTo(DEFAULT_THOI_GIAN);
        assertThat(testProRefinalCCChiTietLoi.getDx()).isEqualTo(DEFAULT_DX);
        assertThat(testProRefinalCCChiTietLoi.getDy()).isEqualTo(DEFAULT_DY);
        assertThat(testProRefinalCCChiTietLoi.isIsFront()).isEqualTo(DEFAULT_IS_FRONT);
        assertThat(testProRefinalCCChiTietLoi.isIsInside()).isEqualTo(DEFAULT_IS_INSIDE);
        assertThat(testProRefinalCCChiTietLoi.isWarning()).isEqualTo(DEFAULT_WARNING);
        assertThat(testProRefinalCCChiTietLoi.getNguoiKiem()).isEqualTo(DEFAULT_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void createProRefinalCCChiTietLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proRefinalCCChiTietLoiRepository.findAll().size();

        // Create the ProRefinalCCChiTietLoi with an existing ID
        proRefinalCCChiTietLoi.setId(1L);
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiMapper.toDto(proRefinalCCChiTietLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProRefinalCCChiTietLoiMockMvc.perform(post("/api/pro-refinal-cc-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalCCChiTietLoi in the database
        List<ProRefinalCCChiTietLoi> proRefinalCCChiTietLoiList = proRefinalCCChiTietLoiRepository.findAll();
        assertThat(proRefinalCCChiTietLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProRefinalCCChiTietLois() throws Exception {
        // Initialize the database
        proRefinalCCChiTietLoiRepository.saveAndFlush(proRefinalCCChiTietLoi);

        // Get all the proRefinalCCChiTietLoiList
        restProRefinalCCChiTietLoiMockMvc.perform(get("/api/pro-refinal-cc-chi-tiet-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proRefinalCCChiTietLoi.getId().intValue())))
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
    public void getProRefinalCCChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalCCChiTietLoiRepository.saveAndFlush(proRefinalCCChiTietLoi);

        // Get the proRefinalCCChiTietLoi
        restProRefinalCCChiTietLoiMockMvc.perform(get("/api/pro-refinal-cc-chi-tiet-lois/{id}", proRefinalCCChiTietLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proRefinalCCChiTietLoi.getId().intValue()))
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
    public void getNonExistingProRefinalCCChiTietLoi() throws Exception {
        // Get the proRefinalCCChiTietLoi
        restProRefinalCCChiTietLoiMockMvc.perform(get("/api/pro-refinal-cc-chi-tiet-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProRefinalCCChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalCCChiTietLoiRepository.saveAndFlush(proRefinalCCChiTietLoi);

        int databaseSizeBeforeUpdate = proRefinalCCChiTietLoiRepository.findAll().size();

        // Update the proRefinalCCChiTietLoi
        ProRefinalCCChiTietLoi updatedProRefinalCCChiTietLoi = proRefinalCCChiTietLoiRepository.findById(proRefinalCCChiTietLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProRefinalCCChiTietLoi are not directly saved in db
        em.detach(updatedProRefinalCCChiTietLoi);
        updatedProRefinalCCChiTietLoi
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING)
            .nguoiKiem(UPDATED_NGUOI_KIEM);
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiMapper.toDto(updatedProRefinalCCChiTietLoi);

        restProRefinalCCChiTietLoiMockMvc.perform(put("/api/pro-refinal-cc-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCChiTietLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProRefinalCCChiTietLoi in the database
        List<ProRefinalCCChiTietLoi> proRefinalCCChiTietLoiList = proRefinalCCChiTietLoiRepository.findAll();
        assertThat(proRefinalCCChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
        ProRefinalCCChiTietLoi testProRefinalCCChiTietLoi = proRefinalCCChiTietLoiList.get(proRefinalCCChiTietLoiList.size() - 1);
        assertThat(testProRefinalCCChiTietLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProRefinalCCChiTietLoi.getThoiGian()).isEqualTo(UPDATED_THOI_GIAN);
        assertThat(testProRefinalCCChiTietLoi.getDx()).isEqualTo(UPDATED_DX);
        assertThat(testProRefinalCCChiTietLoi.getDy()).isEqualTo(UPDATED_DY);
        assertThat(testProRefinalCCChiTietLoi.isIsFront()).isEqualTo(UPDATED_IS_FRONT);
        assertThat(testProRefinalCCChiTietLoi.isIsInside()).isEqualTo(UPDATED_IS_INSIDE);
        assertThat(testProRefinalCCChiTietLoi.isWarning()).isEqualTo(UPDATED_WARNING);
        assertThat(testProRefinalCCChiTietLoi.getNguoiKiem()).isEqualTo(UPDATED_NGUOI_KIEM);
    }

    @Test
    @Transactional
    public void updateNonExistingProRefinalCCChiTietLoi() throws Exception {
        int databaseSizeBeforeUpdate = proRefinalCCChiTietLoiRepository.findAll().size();

        // Create the ProRefinalCCChiTietLoi
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiMapper.toDto(proRefinalCCChiTietLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProRefinalCCChiTietLoiMockMvc.perform(put("/api/pro-refinal-cc-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalCCChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalCCChiTietLoi in the database
        List<ProRefinalCCChiTietLoi> proRefinalCCChiTietLoiList = proRefinalCCChiTietLoiRepository.findAll();
        assertThat(proRefinalCCChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProRefinalCCChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalCCChiTietLoiRepository.saveAndFlush(proRefinalCCChiTietLoi);

        int databaseSizeBeforeDelete = proRefinalCCChiTietLoiRepository.findAll().size();

        // Delete the proRefinalCCChiTietLoi
        restProRefinalCCChiTietLoiMockMvc.perform(delete("/api/pro-refinal-cc-chi-tiet-lois/{id}", proRefinalCCChiTietLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProRefinalCCChiTietLoi> proRefinalCCChiTietLoiList = proRefinalCCChiTietLoiRepository.findAll();
        assertThat(proRefinalCCChiTietLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
