package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProIssueLoi;
import vn.nvsoft.repository.ProIssueLoiRepository;
import vn.nvsoft.service.ProIssueLoiService;
import vn.nvsoft.service.dto.ProIssueLoiDTO;
import vn.nvsoft.service.mapper.ProIssueLoiMapper;
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
 * Integration tests for the {@link ProIssueLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProIssueLoiResourceIT {

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

    private static final String DEFAULT_MAU_SAC = "AAAAAAAAAA";
    private static final String UPDATED_MAU_SAC = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    @Autowired
    private ProIssueLoiRepository proIssueLoiRepository;

    @Autowired
    private ProIssueLoiMapper proIssueLoiMapper;

    @Autowired
    private ProIssueLoiService proIssueLoiService;

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

    private MockMvc restProIssueLoiMockMvc;

    private ProIssueLoi proIssueLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProIssueLoiResource proIssueLoiResource = new ProIssueLoiResource(proIssueLoiService);
        this.restProIssueLoiMockMvc = MockMvcBuilders.standaloneSetup(proIssueLoiResource)
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
    public static ProIssueLoi createEntity(EntityManager em) {
        ProIssueLoi proIssueLoi = new ProIssueLoi()
            .maLoi(DEFAULT_MA_LOI)
            .cusCode(DEFAULT_CUS_CODE)
            .tenLoi(DEFAULT_TEN_LOI)
            .tenTiengAnh(DEFAULT_TEN_TIENG_ANH)
            .label(DEFAULT_LABEL)
            .stt(DEFAULT_STT)
            .mauSac(DEFAULT_MAU_SAC)
            .ghiChu(DEFAULT_GHI_CHU);
        return proIssueLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProIssueLoi createUpdatedEntity(EntityManager em) {
        ProIssueLoi proIssueLoi = new ProIssueLoi()
            .maLoi(UPDATED_MA_LOI)
            .cusCode(UPDATED_CUS_CODE)
            .tenLoi(UPDATED_TEN_LOI)
            .tenTiengAnh(UPDATED_TEN_TIENG_ANH)
            .label(UPDATED_LABEL)
            .stt(UPDATED_STT)
            .mauSac(UPDATED_MAU_SAC)
            .ghiChu(UPDATED_GHI_CHU);
        return proIssueLoi;
    }

    @BeforeEach
    public void initTest() {
        proIssueLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProIssueLoi() throws Exception {
        int databaseSizeBeforeCreate = proIssueLoiRepository.findAll().size();

        // Create the ProIssueLoi
        ProIssueLoiDTO proIssueLoiDTO = proIssueLoiMapper.toDto(proIssueLoi);
        restProIssueLoiMockMvc.perform(post("/api/pro-issue-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProIssueLoi in the database
        List<ProIssueLoi> proIssueLoiList = proIssueLoiRepository.findAll();
        assertThat(proIssueLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProIssueLoi testProIssueLoi = proIssueLoiList.get(proIssueLoiList.size() - 1);
        assertThat(testProIssueLoi.getMaLoi()).isEqualTo(DEFAULT_MA_LOI);
        assertThat(testProIssueLoi.getCusCode()).isEqualTo(DEFAULT_CUS_CODE);
        assertThat(testProIssueLoi.getTenLoi()).isEqualTo(DEFAULT_TEN_LOI);
        assertThat(testProIssueLoi.getTenTiengAnh()).isEqualTo(DEFAULT_TEN_TIENG_ANH);
        assertThat(testProIssueLoi.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testProIssueLoi.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testProIssueLoi.getMauSac()).isEqualTo(DEFAULT_MAU_SAC);
        assertThat(testProIssueLoi.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
    }

    @Test
    @Transactional
    public void createProIssueLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proIssueLoiRepository.findAll().size();

        // Create the ProIssueLoi with an existing ID
        proIssueLoi.setId(1L);
        ProIssueLoiDTO proIssueLoiDTO = proIssueLoiMapper.toDto(proIssueLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProIssueLoiMockMvc.perform(post("/api/pro-issue-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssueLoi in the database
        List<ProIssueLoi> proIssueLoiList = proIssueLoiRepository.findAll();
        assertThat(proIssueLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProIssueLois() throws Exception {
        // Initialize the database
        proIssueLoiRepository.saveAndFlush(proIssueLoi);

        // Get all the proIssueLoiList
        restProIssueLoiMockMvc.perform(get("/api/pro-issue-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proIssueLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].maLoi").value(hasItem(DEFAULT_MA_LOI)))
            .andExpect(jsonPath("$.[*].cusCode").value(hasItem(DEFAULT_CUS_CODE)))
            .andExpect(jsonPath("$.[*].tenLoi").value(hasItem(DEFAULT_TEN_LOI)))
            .andExpect(jsonPath("$.[*].tenTiengAnh").value(hasItem(DEFAULT_TEN_TIENG_ANH)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].mauSac").value(hasItem(DEFAULT_MAU_SAC)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU)));
    }
    
    @Test
    @Transactional
    public void getProIssueLoi() throws Exception {
        // Initialize the database
        proIssueLoiRepository.saveAndFlush(proIssueLoi);

        // Get the proIssueLoi
        restProIssueLoiMockMvc.perform(get("/api/pro-issue-lois/{id}", proIssueLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proIssueLoi.getId().intValue()))
            .andExpect(jsonPath("$.maLoi").value(DEFAULT_MA_LOI))
            .andExpect(jsonPath("$.cusCode").value(DEFAULT_CUS_CODE))
            .andExpect(jsonPath("$.tenLoi").value(DEFAULT_TEN_LOI))
            .andExpect(jsonPath("$.tenTiengAnh").value(DEFAULT_TEN_TIENG_ANH))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.mauSac").value(DEFAULT_MAU_SAC))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU));
    }

    @Test
    @Transactional
    public void getNonExistingProIssueLoi() throws Exception {
        // Get the proIssueLoi
        restProIssueLoiMockMvc.perform(get("/api/pro-issue-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProIssueLoi() throws Exception {
        // Initialize the database
        proIssueLoiRepository.saveAndFlush(proIssueLoi);

        int databaseSizeBeforeUpdate = proIssueLoiRepository.findAll().size();

        // Update the proIssueLoi
        ProIssueLoi updatedProIssueLoi = proIssueLoiRepository.findById(proIssueLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProIssueLoi are not directly saved in db
        em.detach(updatedProIssueLoi);
        updatedProIssueLoi
            .maLoi(UPDATED_MA_LOI)
            .cusCode(UPDATED_CUS_CODE)
            .tenLoi(UPDATED_TEN_LOI)
            .tenTiengAnh(UPDATED_TEN_TIENG_ANH)
            .label(UPDATED_LABEL)
            .stt(UPDATED_STT)
            .mauSac(UPDATED_MAU_SAC)
            .ghiChu(UPDATED_GHI_CHU);
        ProIssueLoiDTO proIssueLoiDTO = proIssueLoiMapper.toDto(updatedProIssueLoi);

        restProIssueLoiMockMvc.perform(put("/api/pro-issue-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProIssueLoi in the database
        List<ProIssueLoi> proIssueLoiList = proIssueLoiRepository.findAll();
        assertThat(proIssueLoiList).hasSize(databaseSizeBeforeUpdate);
        ProIssueLoi testProIssueLoi = proIssueLoiList.get(proIssueLoiList.size() - 1);
        assertThat(testProIssueLoi.getMaLoi()).isEqualTo(UPDATED_MA_LOI);
        assertThat(testProIssueLoi.getCusCode()).isEqualTo(UPDATED_CUS_CODE);
        assertThat(testProIssueLoi.getTenLoi()).isEqualTo(UPDATED_TEN_LOI);
        assertThat(testProIssueLoi.getTenTiengAnh()).isEqualTo(UPDATED_TEN_TIENG_ANH);
        assertThat(testProIssueLoi.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testProIssueLoi.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testProIssueLoi.getMauSac()).isEqualTo(UPDATED_MAU_SAC);
        assertThat(testProIssueLoi.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
    }

    @Test
    @Transactional
    public void updateNonExistingProIssueLoi() throws Exception {
        int databaseSizeBeforeUpdate = proIssueLoiRepository.findAll().size();

        // Create the ProIssueLoi
        ProIssueLoiDTO proIssueLoiDTO = proIssueLoiMapper.toDto(proIssueLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProIssueLoiMockMvc.perform(put("/api/pro-issue-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proIssueLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProIssueLoi in the database
        List<ProIssueLoi> proIssueLoiList = proIssueLoiRepository.findAll();
        assertThat(proIssueLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProIssueLoi() throws Exception {
        // Initialize the database
        proIssueLoiRepository.saveAndFlush(proIssueLoi);

        int databaseSizeBeforeDelete = proIssueLoiRepository.findAll().size();

        // Delete the proIssueLoi
        restProIssueLoiMockMvc.perform(delete("/api/pro-issue-lois/{id}", proIssueLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProIssueLoi> proIssueLoiList = proIssueLoiRepository.findAll();
        assertThat(proIssueLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
