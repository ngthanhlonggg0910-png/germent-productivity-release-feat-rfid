package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.GYCLQuyCachNPL;
import vn.nvsoft.repository.GYCLQuyCachNPLRepository;
import vn.nvsoft.service.GYCLQuyCachNPLService;
import vn.nvsoft.service.dto.GYCLQuyCachNPLDTO;
import vn.nvsoft.service.mapper.GYCLQuyCachNPLMapper;
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
 * Integration tests for the {@link GYCLQuyCachNPLResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class GYCLQuyCachNPLResourceIT {

    private static final String DEFAULT_TEN_NPL = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NPL = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_NGAY_TAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Boolean DEFAULT_YES_NO = false;
    private static final Boolean UPDATED_YES_NO = true;

    private static final String DEFAULT_N_A = "AAAAAAAAAA";
    private static final String UPDATED_N_A = "BBBBBBBBBB";

    @Autowired
    private GYCLQuyCachNPLRepository gYCLQuyCachNPLRepository;

    @Autowired
    private GYCLQuyCachNPLMapper gYCLQuyCachNPLMapper;

    @Autowired
    private GYCLQuyCachNPLService gYCLQuyCachNPLService;

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

    private MockMvc restGYCLQuyCachNPLMockMvc;

    private GYCLQuyCachNPL gYCLQuyCachNPL;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GYCLQuyCachNPLResource gYCLQuyCachNPLResource = new GYCLQuyCachNPLResource(gYCLQuyCachNPLService);
        this.restGYCLQuyCachNPLMockMvc = MockMvcBuilders.standaloneSetup(gYCLQuyCachNPLResource)
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
    public static GYCLQuyCachNPL createEntity(EntityManager em) {
        GYCLQuyCachNPL gYCLQuyCachNPL = new GYCLQuyCachNPL()
            .tenNPL(DEFAULT_TEN_NPL)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .ngayTao(DEFAULT_NGAY_TAO)
            .userId(DEFAULT_USER_ID)
            .yesNo(DEFAULT_YES_NO)
            .nA(DEFAULT_N_A);
        return gYCLQuyCachNPL;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GYCLQuyCachNPL createUpdatedEntity(EntityManager em) {
        GYCLQuyCachNPL gYCLQuyCachNPL = new GYCLQuyCachNPL()
            .tenNPL(UPDATED_TEN_NPL)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID)
            .yesNo(UPDATED_YES_NO)
            .nA(UPDATED_N_A);
        return gYCLQuyCachNPL;
    }

    @BeforeEach
    public void initTest() {
        gYCLQuyCachNPL = createEntity(em);
    }

    @Test
    @Transactional
    public void createGYCLQuyCachNPL() throws Exception {
        int databaseSizeBeforeCreate = gYCLQuyCachNPLRepository.findAll().size();

        // Create the GYCLQuyCachNPL
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO = gYCLQuyCachNPLMapper.toDto(gYCLQuyCachNPL);
        restGYCLQuyCachNPLMockMvc.perform(post("/api/gycl-quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLQuyCachNPLDTO)))
            .andExpect(status().isCreated());

        // Validate the GYCLQuyCachNPL in the database
        List<GYCLQuyCachNPL> gYCLQuyCachNPLList = gYCLQuyCachNPLRepository.findAll();
        assertThat(gYCLQuyCachNPLList).hasSize(databaseSizeBeforeCreate + 1);
        GYCLQuyCachNPL testGYCLQuyCachNPL = gYCLQuyCachNPLList.get(gYCLQuyCachNPLList.size() - 1);
        assertThat(testGYCLQuyCachNPL.getTenNPL()).isEqualTo(DEFAULT_TEN_NPL);
        assertThat(testGYCLQuyCachNPL.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testGYCLQuyCachNPL.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testGYCLQuyCachNPL.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGYCLQuyCachNPL.isYesNo()).isEqualTo(DEFAULT_YES_NO);
        assertThat(testGYCLQuyCachNPL.getnA()).isEqualTo(DEFAULT_N_A);
    }

    @Test
    @Transactional
    public void createGYCLQuyCachNPLWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gYCLQuyCachNPLRepository.findAll().size();

        // Create the GYCLQuyCachNPL with an existing ID
        gYCLQuyCachNPL.setId(1L);
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO = gYCLQuyCachNPLMapper.toDto(gYCLQuyCachNPL);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGYCLQuyCachNPLMockMvc.perform(post("/api/gycl-quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLQuyCachNPLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GYCLQuyCachNPL in the database
        List<GYCLQuyCachNPL> gYCLQuyCachNPLList = gYCLQuyCachNPLRepository.findAll();
        assertThat(gYCLQuyCachNPLList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGYCLQuyCachNPLS() throws Exception {
        // Initialize the database
        gYCLQuyCachNPLRepository.saveAndFlush(gYCLQuyCachNPL);

        // Get all the gYCLQuyCachNPLList
        restGYCLQuyCachNPLMockMvc.perform(get("/api/gycl-quy-cach-npls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gYCLQuyCachNPL.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNPL").value(hasItem(DEFAULT_TEN_NPL)))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].yesNo").value(hasItem(DEFAULT_YES_NO.booleanValue())))
            .andExpect(jsonPath("$.[*].nA").value(hasItem(DEFAULT_N_A)));
    }
    
    @Test
    @Transactional
    public void getGYCLQuyCachNPL() throws Exception {
        // Initialize the database
        gYCLQuyCachNPLRepository.saveAndFlush(gYCLQuyCachNPL);

        // Get the gYCLQuyCachNPL
        restGYCLQuyCachNPLMockMvc.perform(get("/api/gycl-quy-cach-npls/{id}", gYCLQuyCachNPL.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gYCLQuyCachNPL.getId().intValue()))
            .andExpect(jsonPath("$.tenNPL").value(DEFAULT_TEN_NPL))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.yesNo").value(DEFAULT_YES_NO.booleanValue()))
            .andExpect(jsonPath("$.nA").value(DEFAULT_N_A));
    }

    @Test
    @Transactional
    public void getNonExistingGYCLQuyCachNPL() throws Exception {
        // Get the gYCLQuyCachNPL
        restGYCLQuyCachNPLMockMvc.perform(get("/api/gycl-quy-cach-npls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGYCLQuyCachNPL() throws Exception {
        // Initialize the database
        gYCLQuyCachNPLRepository.saveAndFlush(gYCLQuyCachNPL);

        int databaseSizeBeforeUpdate = gYCLQuyCachNPLRepository.findAll().size();

        // Update the gYCLQuyCachNPL
        GYCLQuyCachNPL updatedGYCLQuyCachNPL = gYCLQuyCachNPLRepository.findById(gYCLQuyCachNPL.getId()).get();
        // Disconnect from session so that the updates on updatedGYCLQuyCachNPL are not directly saved in db
        em.detach(updatedGYCLQuyCachNPL);
        updatedGYCLQuyCachNPL
            .tenNPL(UPDATED_TEN_NPL)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID)
            .yesNo(UPDATED_YES_NO)
            .nA(UPDATED_N_A);
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO = gYCLQuyCachNPLMapper.toDto(updatedGYCLQuyCachNPL);

        restGYCLQuyCachNPLMockMvc.perform(put("/api/gycl-quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLQuyCachNPLDTO)))
            .andExpect(status().isOk());

        // Validate the GYCLQuyCachNPL in the database
        List<GYCLQuyCachNPL> gYCLQuyCachNPLList = gYCLQuyCachNPLRepository.findAll();
        assertThat(gYCLQuyCachNPLList).hasSize(databaseSizeBeforeUpdate);
        GYCLQuyCachNPL testGYCLQuyCachNPL = gYCLQuyCachNPLList.get(gYCLQuyCachNPLList.size() - 1);
        assertThat(testGYCLQuyCachNPL.getTenNPL()).isEqualTo(UPDATED_TEN_NPL);
        assertThat(testGYCLQuyCachNPL.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testGYCLQuyCachNPL.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testGYCLQuyCachNPL.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGYCLQuyCachNPL.isYesNo()).isEqualTo(UPDATED_YES_NO);
        assertThat(testGYCLQuyCachNPL.getnA()).isEqualTo(UPDATED_N_A);
    }

    @Test
    @Transactional
    public void updateNonExistingGYCLQuyCachNPL() throws Exception {
        int databaseSizeBeforeUpdate = gYCLQuyCachNPLRepository.findAll().size();

        // Create the GYCLQuyCachNPL
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO = gYCLQuyCachNPLMapper.toDto(gYCLQuyCachNPL);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGYCLQuyCachNPLMockMvc.perform(put("/api/gycl-quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gYCLQuyCachNPLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GYCLQuyCachNPL in the database
        List<GYCLQuyCachNPL> gYCLQuyCachNPLList = gYCLQuyCachNPLRepository.findAll();
        assertThat(gYCLQuyCachNPLList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGYCLQuyCachNPL() throws Exception {
        // Initialize the database
        gYCLQuyCachNPLRepository.saveAndFlush(gYCLQuyCachNPL);

        int databaseSizeBeforeDelete = gYCLQuyCachNPLRepository.findAll().size();

        // Delete the gYCLQuyCachNPL
        restGYCLQuyCachNPLMockMvc.perform(delete("/api/gycl-quy-cach-npls/{id}", gYCLQuyCachNPL.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GYCLQuyCachNPL> gYCLQuyCachNPLList = gYCLQuyCachNPLRepository.findAll();
        assertThat(gYCLQuyCachNPLList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
