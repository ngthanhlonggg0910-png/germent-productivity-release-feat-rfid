package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ThuVienLoi;
import vn.nvsoft.repository.ThuVienLoiRepository;
import vn.nvsoft.service.ThuVienLoiService;
import vn.nvsoft.service.dto.ThuVienLoiDTO;
import vn.nvsoft.service.mapper.ThuVienLoiMapper;
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
 * Integration tests for the {@link ThuVienLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ThuVienLoiResourceIT {

    private static final String DEFAULT_MA_LOI = "AAAAAAAAAA";
    private static final String UPDATED_MA_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_LOI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOI = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_TAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    @Autowired
    private ThuVienLoiRepository thuVienLoiRepository;

    @Autowired
    private ThuVienLoiMapper thuVienLoiMapper;

    @Autowired
    private ThuVienLoiService thuVienLoiService;

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

    private MockMvc restThuVienLoiMockMvc;

    private ThuVienLoi thuVienLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThuVienLoiResource thuVienLoiResource = new ThuVienLoiResource(thuVienLoiService);
        this.restThuVienLoiMockMvc = MockMvcBuilders.standaloneSetup(thuVienLoiResource)
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
    public static ThuVienLoi createEntity(EntityManager em) {
        ThuVienLoi thuVienLoi = new ThuVienLoi()
            .maLoi(DEFAULT_MA_LOI)
            .tenLoi(DEFAULT_TEN_LOI)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .ngayTao(DEFAULT_NGAY_TAO)
            .userId(DEFAULT_USER_ID);
        return thuVienLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThuVienLoi createUpdatedEntity(EntityManager em) {
        ThuVienLoi thuVienLoi = new ThuVienLoi()
            .maLoi(UPDATED_MA_LOI)
            .tenLoi(UPDATED_TEN_LOI)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        return thuVienLoi;
    }

    @BeforeEach
    public void initTest() {
        thuVienLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createThuVienLoi() throws Exception {
        int databaseSizeBeforeCreate = thuVienLoiRepository.findAll().size();

        // Create the ThuVienLoi
        ThuVienLoiDTO thuVienLoiDTO = thuVienLoiMapper.toDto(thuVienLoi);
        restThuVienLoiMockMvc.perform(post("/api/thu-vien-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thuVienLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ThuVienLoi in the database
        List<ThuVienLoi> thuVienLoiList = thuVienLoiRepository.findAll();
        assertThat(thuVienLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ThuVienLoi testThuVienLoi = thuVienLoiList.get(thuVienLoiList.size() - 1);
        assertThat(testThuVienLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testThuVienLoi.getTenLoi()).isEqualTo(DEFAULT_TEN_LOI);
        assertThat(testThuVienLoi.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testThuVienLoi.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testThuVienLoi.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createThuVienLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thuVienLoiRepository.findAll().size();

        // Create the ThuVienLoi with an existing ID
        thuVienLoi.setId(1L);
        ThuVienLoiDTO thuVienLoiDTO = thuVienLoiMapper.toDto(thuVienLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThuVienLoiMockMvc.perform(post("/api/thu-vien-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thuVienLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThuVienLoi in the database
        List<ThuVienLoi> thuVienLoiList = thuVienLoiRepository.findAll();
        assertThat(thuVienLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllThuVienLois() throws Exception {
        // Initialize the database
        thuVienLoiRepository.saveAndFlush(thuVienLoi);

        // Get all the thuVienLoiList
        restThuVienLoiMockMvc.perform(get("/api/thu-vien-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thuVienLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].tenLoi").value(hasItem(DEFAULT_TEN_LOI)))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getThuVienLoi() throws Exception {
        // Initialize the database
        thuVienLoiRepository.saveAndFlush(thuVienLoi);

        // Get the thuVienLoi
        restThuVienLoiMockMvc.perform(get("/api/thu-vien-lois/{id}", thuVienLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thuVienLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.tenLoi").value(DEFAULT_TEN_LOI))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingThuVienLoi() throws Exception {
        // Get the thuVienLoi
        restThuVienLoiMockMvc.perform(get("/api/thu-vien-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThuVienLoi() throws Exception {
        // Initialize the database
        thuVienLoiRepository.saveAndFlush(thuVienLoi);

        int databaseSizeBeforeUpdate = thuVienLoiRepository.findAll().size();

        // Update the thuVienLoi
        ThuVienLoi updatedThuVienLoi = thuVienLoiRepository.findById(thuVienLoi.getId()).get();
        // Disconnect from session so that the updates on updatedThuVienLoi are not directly saved in db
        em.detach(updatedThuVienLoi);
        updatedThuVienLoi
            .maLoi(UPDATED_MA_LOI)
            .tenLoi(UPDATED_TEN_LOI)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        ThuVienLoiDTO thuVienLoiDTO = thuVienLoiMapper.toDto(updatedThuVienLoi);

        restThuVienLoiMockMvc.perform(put("/api/thu-vien-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thuVienLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ThuVienLoi in the database
        List<ThuVienLoi> thuVienLoiList = thuVienLoiRepository.findAll();
        assertThat(thuVienLoiList).hasSize(databaseSizeBeforeUpdate);
        ThuVienLoi testThuVienLoi = thuVienLoiList.get(thuVienLoiList.size() - 1);
        assertThat(testThuVienLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testThuVienLoi.getTenLoi()).isEqualTo(UPDATED_TEN_LOI);
        assertThat(testThuVienLoi.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testThuVienLoi.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testThuVienLoi.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingThuVienLoi() throws Exception {
        int databaseSizeBeforeUpdate = thuVienLoiRepository.findAll().size();

        // Create the ThuVienLoi
        ThuVienLoiDTO thuVienLoiDTO = thuVienLoiMapper.toDto(thuVienLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThuVienLoiMockMvc.perform(put("/api/thu-vien-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thuVienLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThuVienLoi in the database
        List<ThuVienLoi> thuVienLoiList = thuVienLoiRepository.findAll();
        assertThat(thuVienLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThuVienLoi() throws Exception {
        // Initialize the database
        thuVienLoiRepository.saveAndFlush(thuVienLoi);

        int databaseSizeBeforeDelete = thuVienLoiRepository.findAll().size();

        // Delete the thuVienLoi
        restThuVienLoiMockMvc.perform(delete("/api/thu-vien-lois/{id}", thuVienLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThuVienLoi> thuVienLoiList = thuVienLoiRepository.findAll();
        assertThat(thuVienLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
