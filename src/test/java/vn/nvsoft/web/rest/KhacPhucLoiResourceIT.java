package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.KhacPhucLoi;
import vn.nvsoft.repository.KhacPhucLoiRepository;
import vn.nvsoft.service.KhacPhucLoiService;
import vn.nvsoft.service.dto.KhacPhucLoiDTO;
import vn.nvsoft.service.mapper.KhacPhucLoiMapper;
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
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link KhacPhucLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class KhacPhucLoiResourceIT {

    private static final String DEFAULT_MA_KP = "AAAAAAAAAA";
    private static final String UPDATED_MA_KP = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KP = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KP = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    @Autowired
    private KhacPhucLoiRepository khacPhucLoiRepository;

    @Autowired
    private KhacPhucLoiMapper khacPhucLoiMapper;

    @Autowired
    private KhacPhucLoiService khacPhucLoiService;

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

    private MockMvc restKhacPhucLoiMockMvc;

    private KhacPhucLoi khacPhucLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KhacPhucLoiResource khacPhucLoiResource = new KhacPhucLoiResource(khacPhucLoiService);
        this.restKhacPhucLoiMockMvc = MockMvcBuilders.standaloneSetup(khacPhucLoiResource)
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
    public static KhacPhucLoi createEntity(EntityManager em) {
        KhacPhucLoi khacPhucLoi = new KhacPhucLoi()
            .maKP(DEFAULT_MA_KP)
            .tenKP(DEFAULT_TEN_KP)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .ngayTao(DEFAULT_NGAY_TAO)
            .userId(DEFAULT_USER_ID);
        return khacPhucLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KhacPhucLoi createUpdatedEntity(EntityManager em) {
        KhacPhucLoi khacPhucLoi = new KhacPhucLoi()
            .maKP(UPDATED_MA_KP)
            .tenKP(UPDATED_TEN_KP)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        return khacPhucLoi;
    }

    @BeforeEach
    public void initTest() {
        khacPhucLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createKhacPhucLoi() throws Exception {
        int databaseSizeBeforeCreate = khacPhucLoiRepository.findAll().size();

        // Create the KhacPhucLoi
        KhacPhucLoiDTO khacPhucLoiDTO = khacPhucLoiMapper.toDto(khacPhucLoi);
        restKhacPhucLoiMockMvc.perform(post("/api/khac-phuc-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khacPhucLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the KhacPhucLoi in the database
        List<KhacPhucLoi> khacPhucLoiList = khacPhucLoiRepository.findAll();
        assertThat(khacPhucLoiList).hasSize(databaseSizeBeforeCreate + 1);
        KhacPhucLoi testKhacPhucLoi = khacPhucLoiList.get(khacPhucLoiList.size() - 1);
        assertThat(testKhacPhucLoi.getMaKP()).isEqualTo(DEFAULT_MA_KP);
        assertThat(testKhacPhucLoi.getTenKP()).isEqualTo(DEFAULT_TEN_KP);
        assertThat(testKhacPhucLoi.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testKhacPhucLoi.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testKhacPhucLoi.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createKhacPhucLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = khacPhucLoiRepository.findAll().size();

        // Create the KhacPhucLoi with an existing ID
        khacPhucLoi.setId(1L);
        KhacPhucLoiDTO khacPhucLoiDTO = khacPhucLoiMapper.toDto(khacPhucLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKhacPhucLoiMockMvc.perform(post("/api/khac-phuc-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khacPhucLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhacPhucLoi in the database
        List<KhacPhucLoi> khacPhucLoiList = khacPhucLoiRepository.findAll();
        assertThat(khacPhucLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllKhacPhucLois() throws Exception {
        // Initialize the database
        khacPhucLoiRepository.saveAndFlush(khacPhucLoi);

        // Get all the khacPhucLoiList
        restKhacPhucLoiMockMvc.perform(get("/api/khac-phuc-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(khacPhucLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maKP").value(hasItem(DEFAULT_MA_KP)))
            .andExpect(jsonPath("$.[*].tenKP").value(hasItem(DEFAULT_TEN_KP)))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getKhacPhucLoi() throws Exception {
        // Initialize the database
        khacPhucLoiRepository.saveAndFlush(khacPhucLoi);

        // Get the khacPhucLoi
        restKhacPhucLoiMockMvc.perform(get("/api/khac-phuc-lois/{id}", khacPhucLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(khacPhucLoi.getId().intValue()))
            .andExpect(jsonPath("$.maKP").value(DEFAULT_MA_KP))
            .andExpect(jsonPath("$.tenKP").value(DEFAULT_TEN_KP))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingKhacPhucLoi() throws Exception {
        // Get the khacPhucLoi
        restKhacPhucLoiMockMvc.perform(get("/api/khac-phuc-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKhacPhucLoi() throws Exception {
        // Initialize the database
        khacPhucLoiRepository.saveAndFlush(khacPhucLoi);

        int databaseSizeBeforeUpdate = khacPhucLoiRepository.findAll().size();

        // Update the khacPhucLoi
        KhacPhucLoi updatedKhacPhucLoi = khacPhucLoiRepository.findById(khacPhucLoi.getId()).get();
        // Disconnect from session so that the updates on updatedKhacPhucLoi are not directly saved in db
        em.detach(updatedKhacPhucLoi);
        updatedKhacPhucLoi
            .maKP(UPDATED_MA_KP)
            .tenKP(UPDATED_TEN_KP)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        KhacPhucLoiDTO khacPhucLoiDTO = khacPhucLoiMapper.toDto(updatedKhacPhucLoi);

        restKhacPhucLoiMockMvc.perform(put("/api/khac-phuc-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khacPhucLoiDTO)))
            .andExpect(status().isOk());

        // Validate the KhacPhucLoi in the database
        List<KhacPhucLoi> khacPhucLoiList = khacPhucLoiRepository.findAll();
        assertThat(khacPhucLoiList).hasSize(databaseSizeBeforeUpdate);
        KhacPhucLoi testKhacPhucLoi = khacPhucLoiList.get(khacPhucLoiList.size() - 1);
        assertThat(testKhacPhucLoi.getMaKP()).isEqualTo(UPDATED_MA_KP);
        assertThat(testKhacPhucLoi.getTenKP()).isEqualTo(UPDATED_TEN_KP);
        assertThat(testKhacPhucLoi.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testKhacPhucLoi.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testKhacPhucLoi.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingKhacPhucLoi() throws Exception {
        int databaseSizeBeforeUpdate = khacPhucLoiRepository.findAll().size();

        // Create the KhacPhucLoi
        KhacPhucLoiDTO khacPhucLoiDTO = khacPhucLoiMapper.toDto(khacPhucLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKhacPhucLoiMockMvc.perform(put("/api/khac-phuc-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(khacPhucLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the KhacPhucLoi in the database
        List<KhacPhucLoi> khacPhucLoiList = khacPhucLoiRepository.findAll();
        assertThat(khacPhucLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKhacPhucLoi() throws Exception {
        // Initialize the database
        khacPhucLoiRepository.saveAndFlush(khacPhucLoi);

        int databaseSizeBeforeDelete = khacPhucLoiRepository.findAll().size();

        // Delete the khacPhucLoi
        restKhacPhucLoiMockMvc.perform(delete("/api/khac-phuc-lois/{id}", khacPhucLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KhacPhucLoi> khacPhucLoiList = khacPhucLoiRepository.findAll();
        assertThat(khacPhucLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
