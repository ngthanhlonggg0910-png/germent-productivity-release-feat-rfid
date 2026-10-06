package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProRefinalChiTietLoi;
import vn.nvsoft.repository.ProRefinalChiTietLoiRepository;
import vn.nvsoft.service.ProRefinalChiTietLoiService;
import vn.nvsoft.service.dto.ProRefinalChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProRefinalChiTietLoiMapper;
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
 * Integration tests for the {@link ProRefinalChiTietLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProRefinalChiTietLoiResourceIT {

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

    @Autowired
    private ProRefinalChiTietLoiRepository proRefinalChiTietLoiRepository;

    @Autowired
    private ProRefinalChiTietLoiMapper proRefinalChiTietLoiMapper;

    @Autowired
    private ProRefinalChiTietLoiService proRefinalChiTietLoiService;

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

    private MockMvc restProRefinalChiTietLoiMockMvc;

    private ProRefinalChiTietLoi proRefinalChiTietLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProRefinalChiTietLoiResource proRefinalChiTietLoiResource = new ProRefinalChiTietLoiResource(proRefinalChiTietLoiService);
        this.restProRefinalChiTietLoiMockMvc = MockMvcBuilders.standaloneSetup(proRefinalChiTietLoiResource)
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
    public static ProRefinalChiTietLoi createEntity(EntityManager em) {
        ProRefinalChiTietLoi proRefinalChiTietLoi = new ProRefinalChiTietLoi()
            .maLoi(DEFAULT_MA_LOI)
            .thoiGian(DEFAULT_THOI_GIAN)
            .dx(DEFAULT_DX)
            .dy(DEFAULT_DY)
            .isFront(DEFAULT_IS_FRONT)
            .isInside(DEFAULT_IS_INSIDE)
            .warning(DEFAULT_WARNING);
        return proRefinalChiTietLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProRefinalChiTietLoi createUpdatedEntity(EntityManager em) {
        ProRefinalChiTietLoi proRefinalChiTietLoi = new ProRefinalChiTietLoi()
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING);
        return proRefinalChiTietLoi;
    }

    @BeforeEach
    public void initTest() {
        proRefinalChiTietLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProRefinalChiTietLoi() throws Exception {
        int databaseSizeBeforeCreate = proRefinalChiTietLoiRepository.findAll().size();

        // Create the ProRefinalChiTietLoi
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO = proRefinalChiTietLoiMapper.toDto(proRefinalChiTietLoi);
        restProRefinalChiTietLoiMockMvc.perform(post("/api/pro-refinal-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalChiTietLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProRefinalChiTietLoi in the database
        List<ProRefinalChiTietLoi> proRefinalChiTietLoiList = proRefinalChiTietLoiRepository.findAll();
        assertThat(proRefinalChiTietLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProRefinalChiTietLoi testProRefinalChiTietLoi = proRefinalChiTietLoiList.get(proRefinalChiTietLoiList.size() - 1);
        assertThat(testProRefinalChiTietLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProRefinalChiTietLoi.getThoiGian()).isEqualTo(DEFAULT_THOI_GIAN);
        assertThat(testProRefinalChiTietLoi.getDx()).isEqualTo(DEFAULT_DX);
        assertThat(testProRefinalChiTietLoi.getDy()).isEqualTo(DEFAULT_DY);
        assertThat(testProRefinalChiTietLoi.isIsFront()).isEqualTo(DEFAULT_IS_FRONT);
        assertThat(testProRefinalChiTietLoi.isIsInside()).isEqualTo(DEFAULT_IS_INSIDE);
        assertThat(testProRefinalChiTietLoi.isWarning()).isEqualTo(DEFAULT_WARNING);
    }

    @Test
    @Transactional
    public void createProRefinalChiTietLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proRefinalChiTietLoiRepository.findAll().size();

        // Create the ProRefinalChiTietLoi with an existing ID
        proRefinalChiTietLoi.setId(1L);
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO = proRefinalChiTietLoiMapper.toDto(proRefinalChiTietLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProRefinalChiTietLoiMockMvc.perform(post("/api/pro-refinal-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalChiTietLoi in the database
        List<ProRefinalChiTietLoi> proRefinalChiTietLoiList = proRefinalChiTietLoiRepository.findAll();
        assertThat(proRefinalChiTietLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProRefinalChiTietLois() throws Exception {
        // Initialize the database
        proRefinalChiTietLoiRepository.saveAndFlush(proRefinalChiTietLoi);

        // Get all the proRefinalChiTietLoiList
        restProRefinalChiTietLoiMockMvc.perform(get("/api/pro-refinal-chi-tiet-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proRefinalChiTietLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].thoiGian").value(hasItem(DEFAULT_THOI_GIAN.toString())))
            .andExpect(jsonPath("$.[*].dx").value(hasItem(DEFAULT_DX.doubleValue())))
            .andExpect(jsonPath("$.[*].dy").value(hasItem(DEFAULT_DY.doubleValue())))
            .andExpect(jsonPath("$.[*].isFront").value(hasItem(DEFAULT_IS_FRONT.booleanValue())))
            .andExpect(jsonPath("$.[*].isInside").value(hasItem(DEFAULT_IS_INSIDE.booleanValue())))
            .andExpect(jsonPath("$.[*].warning").value(hasItem(DEFAULT_WARNING.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProRefinalChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalChiTietLoiRepository.saveAndFlush(proRefinalChiTietLoi);

        // Get the proRefinalChiTietLoi
        restProRefinalChiTietLoiMockMvc.perform(get("/api/pro-refinal-chi-tiet-lois/{id}", proRefinalChiTietLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proRefinalChiTietLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.thoiGian").value(DEFAULT_THOI_GIAN.toString()))
            .andExpect(jsonPath("$.dx").value(DEFAULT_DX.doubleValue()))
            .andExpect(jsonPath("$.dy").value(DEFAULT_DY.doubleValue()))
            .andExpect(jsonPath("$.isFront").value(DEFAULT_IS_FRONT.booleanValue()))
            .andExpect(jsonPath("$.isInside").value(DEFAULT_IS_INSIDE.booleanValue()))
            .andExpect(jsonPath("$.warning").value(DEFAULT_WARNING.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProRefinalChiTietLoi() throws Exception {
        // Get the proRefinalChiTietLoi
        restProRefinalChiTietLoiMockMvc.perform(get("/api/pro-refinal-chi-tiet-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProRefinalChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalChiTietLoiRepository.saveAndFlush(proRefinalChiTietLoi);

        int databaseSizeBeforeUpdate = proRefinalChiTietLoiRepository.findAll().size();

        // Update the proRefinalChiTietLoi
        ProRefinalChiTietLoi updatedProRefinalChiTietLoi = proRefinalChiTietLoiRepository.findById(proRefinalChiTietLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProRefinalChiTietLoi are not directly saved in db
        em.detach(updatedProRefinalChiTietLoi);
        updatedProRefinalChiTietLoi
            .maLoi(UPDATED_MA_LOI)
            .thoiGian(UPDATED_THOI_GIAN)
            .dx(UPDATED_DX)
            .dy(UPDATED_DY)
            .isFront(UPDATED_IS_FRONT)
            .isInside(UPDATED_IS_INSIDE)
            .warning(UPDATED_WARNING);
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO = proRefinalChiTietLoiMapper.toDto(updatedProRefinalChiTietLoi);

        restProRefinalChiTietLoiMockMvc.perform(put("/api/pro-refinal-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalChiTietLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProRefinalChiTietLoi in the database
        List<ProRefinalChiTietLoi> proRefinalChiTietLoiList = proRefinalChiTietLoiRepository.findAll();
        assertThat(proRefinalChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
        ProRefinalChiTietLoi testProRefinalChiTietLoi = proRefinalChiTietLoiList.get(proRefinalChiTietLoiList.size() - 1);
        assertThat(testProRefinalChiTietLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProRefinalChiTietLoi.getThoiGian()).isEqualTo(UPDATED_THOI_GIAN);
        assertThat(testProRefinalChiTietLoi.getDx()).isEqualTo(UPDATED_DX);
        assertThat(testProRefinalChiTietLoi.getDy()).isEqualTo(UPDATED_DY);
        assertThat(testProRefinalChiTietLoi.isIsFront()).isEqualTo(UPDATED_IS_FRONT);
        assertThat(testProRefinalChiTietLoi.isIsInside()).isEqualTo(UPDATED_IS_INSIDE);
        assertThat(testProRefinalChiTietLoi.isWarning()).isEqualTo(UPDATED_WARNING);
    }

    @Test
    @Transactional
    public void updateNonExistingProRefinalChiTietLoi() throws Exception {
        int databaseSizeBeforeUpdate = proRefinalChiTietLoiRepository.findAll().size();

        // Create the ProRefinalChiTietLoi
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO = proRefinalChiTietLoiMapper.toDto(proRefinalChiTietLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProRefinalChiTietLoiMockMvc.perform(put("/api/pro-refinal-chi-tiet-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proRefinalChiTietLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProRefinalChiTietLoi in the database
        List<ProRefinalChiTietLoi> proRefinalChiTietLoiList = proRefinalChiTietLoiRepository.findAll();
        assertThat(proRefinalChiTietLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProRefinalChiTietLoi() throws Exception {
        // Initialize the database
        proRefinalChiTietLoiRepository.saveAndFlush(proRefinalChiTietLoi);

        int databaseSizeBeforeDelete = proRefinalChiTietLoiRepository.findAll().size();

        // Delete the proRefinalChiTietLoi
        restProRefinalChiTietLoiMockMvc.perform(delete("/api/pro-refinal-chi-tiet-lois/{id}", proRefinalChiTietLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProRefinalChiTietLoi> proRefinalChiTietLoiList = proRefinalChiTietLoiRepository.findAll();
        assertThat(proRefinalChiTietLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
