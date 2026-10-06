package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.QuyCachNPL;
import vn.nvsoft.repository.QuyCachNPLRepository;
import vn.nvsoft.service.QuyCachNPLService;
import vn.nvsoft.service.dto.QuyCachNPLDTO;
import vn.nvsoft.service.mapper.QuyCachNPLMapper;
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
 * Integration tests for the {@link QuyCachNPLResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class QuyCachNPLResourceIT {

    private static final String DEFAULT_TEN_NPL = "AAAAAAAAAA";
    private static final String UPDATED_TEN_NPL = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NPL = "AAAAAAAAAA";
    private static final String UPDATED_MA_NPL = "BBBBBBBBBB";

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final String DEFAULT_NGAY_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_TAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    @Autowired
    private QuyCachNPLRepository quyCachNPLRepository;

    @Autowired
    private QuyCachNPLMapper quyCachNPLMapper;

    @Autowired
    private QuyCachNPLService quyCachNPLService;

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

    private MockMvc restQuyCachNPLMockMvc;

    private QuyCachNPL quyCachNPL;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuyCachNPLResource quyCachNPLResource = new QuyCachNPLResource(quyCachNPLService);
        this.restQuyCachNPLMockMvc = MockMvcBuilders.standaloneSetup(quyCachNPLResource)
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
    public static QuyCachNPL createEntity(EntityManager em) {
        QuyCachNPL quyCachNPL = new QuyCachNPL()
            .tenNPL(DEFAULT_TEN_NPL)
            .maNPL(DEFAULT_MA_NPL)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .ngayTao(DEFAULT_NGAY_TAO)
            .userId(DEFAULT_USER_ID);
        return quyCachNPL;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuyCachNPL createUpdatedEntity(EntityManager em) {
        QuyCachNPL quyCachNPL = new QuyCachNPL()
            .tenNPL(UPDATED_TEN_NPL)
            .maNPL(UPDATED_MA_NPL)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        return quyCachNPL;
    }

    @BeforeEach
    public void initTest() {
        quyCachNPL = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuyCachNPL() throws Exception {
        int databaseSizeBeforeCreate = quyCachNPLRepository.findAll().size();

        // Create the QuyCachNPL
        QuyCachNPLDTO quyCachNPLDTO = quyCachNPLMapper.toDto(quyCachNPL);
        restQuyCachNPLMockMvc.perform(post("/api/quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyCachNPLDTO)))
            .andExpect(status().isCreated());

        // Validate the QuyCachNPL in the database
        List<QuyCachNPL> quyCachNPLList = quyCachNPLRepository.findAll();
        assertThat(quyCachNPLList).hasSize(databaseSizeBeforeCreate + 1);
        QuyCachNPL testQuyCachNPL = quyCachNPLList.get(quyCachNPLList.size() - 1);
        assertThat(testQuyCachNPL.getTenNPL()).isEqualTo(DEFAULT_TEN_NPL);
        assertThat(testQuyCachNPL.getMaNPL()).isEqualTo(DEFAULT_MA_NPL);
        assertThat(testQuyCachNPL.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testQuyCachNPL.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testQuyCachNPL.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createQuyCachNPLWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quyCachNPLRepository.findAll().size();

        // Create the QuyCachNPL with an existing ID
        quyCachNPL.setId(1L);
        QuyCachNPLDTO quyCachNPLDTO = quyCachNPLMapper.toDto(quyCachNPL);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuyCachNPLMockMvc.perform(post("/api/quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyCachNPLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuyCachNPL in the database
        List<QuyCachNPL> quyCachNPLList = quyCachNPLRepository.findAll();
        assertThat(quyCachNPLList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuyCachNPLS() throws Exception {
        // Initialize the database
        quyCachNPLRepository.saveAndFlush(quyCachNPL);

        // Get all the quyCachNPLList
        restQuyCachNPLMockMvc.perform(get("/api/quy-cach-npls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quyCachNPL.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenNPL").value(hasItem(DEFAULT_TEN_NPL)))
            .andExpect(jsonPath("$.[*].maNPL").value(hasItem(DEFAULT_MA_NPL)))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getQuyCachNPL() throws Exception {
        // Initialize the database
        quyCachNPLRepository.saveAndFlush(quyCachNPL);

        // Get the quyCachNPL
        restQuyCachNPLMockMvc.perform(get("/api/quy-cach-npls/{id}", quyCachNPL.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quyCachNPL.getId().intValue()))
            .andExpect(jsonPath("$.tenNPL").value(DEFAULT_TEN_NPL))
            .andExpect(jsonPath("$.maNPL").value(DEFAULT_MA_NPL))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingQuyCachNPL() throws Exception {
        // Get the quyCachNPL
        restQuyCachNPLMockMvc.perform(get("/api/quy-cach-npls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuyCachNPL() throws Exception {
        // Initialize the database
        quyCachNPLRepository.saveAndFlush(quyCachNPL);

        int databaseSizeBeforeUpdate = quyCachNPLRepository.findAll().size();

        // Update the quyCachNPL
        QuyCachNPL updatedQuyCachNPL = quyCachNPLRepository.findById(quyCachNPL.getId()).get();
        // Disconnect from session so that the updates on updatedQuyCachNPL are not directly saved in db
        em.detach(updatedQuyCachNPL);
        updatedQuyCachNPL
            .tenNPL(UPDATED_TEN_NPL)
            .maNPL(UPDATED_MA_NPL)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .ngayTao(UPDATED_NGAY_TAO)
            .userId(UPDATED_USER_ID);
        QuyCachNPLDTO quyCachNPLDTO = quyCachNPLMapper.toDto(updatedQuyCachNPL);

        restQuyCachNPLMockMvc.perform(put("/api/quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyCachNPLDTO)))
            .andExpect(status().isOk());

        // Validate the QuyCachNPL in the database
        List<QuyCachNPL> quyCachNPLList = quyCachNPLRepository.findAll();
        assertThat(quyCachNPLList).hasSize(databaseSizeBeforeUpdate);
        QuyCachNPL testQuyCachNPL = quyCachNPLList.get(quyCachNPLList.size() - 1);
        assertThat(testQuyCachNPL.getTenNPL()).isEqualTo(UPDATED_TEN_NPL);
        assertThat(testQuyCachNPL.getMaNPL()).isEqualTo(UPDATED_MA_NPL);
        assertThat(testQuyCachNPL.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testQuyCachNPL.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testQuyCachNPL.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingQuyCachNPL() throws Exception {
        int databaseSizeBeforeUpdate = quyCachNPLRepository.findAll().size();

        // Create the QuyCachNPL
        QuyCachNPLDTO quyCachNPLDTO = quyCachNPLMapper.toDto(quyCachNPL);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuyCachNPLMockMvc.perform(put("/api/quy-cach-npls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyCachNPLDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuyCachNPL in the database
        List<QuyCachNPL> quyCachNPLList = quyCachNPLRepository.findAll();
        assertThat(quyCachNPLList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuyCachNPL() throws Exception {
        // Initialize the database
        quyCachNPLRepository.saveAndFlush(quyCachNPL);

        int databaseSizeBeforeDelete = quyCachNPLRepository.findAll().size();

        // Delete the quyCachNPL
        restQuyCachNPLMockMvc.perform(delete("/api/quy-cach-npls/{id}", quyCachNPL.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuyCachNPL> quyCachNPLList = quyCachNPLRepository.findAll();
        assertThat(quyCachNPLList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
