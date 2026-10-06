package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.DungSai;
import vn.nvsoft.repository.DungSaiRepository;
import vn.nvsoft.service.DungSaiService;
import vn.nvsoft.service.dto.DungSaiDTO;
import vn.nvsoft.service.mapper.DungSaiMapper;
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
 * Integration tests for the {@link DungSaiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class DungSaiResourceIT {

    private static final String DEFAULT_CM_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_CM_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_INCH_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_INCH_LABEL = "BBBBBBBBBB";

    private static final Float DEFAULT_CM_VALUE = 1F;
    private static final Float UPDATED_CM_VALUE = 2F;

    private static final Float DEFAULT_INCH_VALUE = 1F;
    private static final Float UPDATED_INCH_VALUE = 2F;

    @Autowired
    private DungSaiRepository dungSaiRepository;

    @Autowired
    private DungSaiMapper dungSaiMapper;

    @Autowired
    private DungSaiService dungSaiService;

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

    private MockMvc restDungSaiMockMvc;

    private DungSai dungSai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DungSaiResource dungSaiResource = new DungSaiResource(dungSaiService);
        this.restDungSaiMockMvc = MockMvcBuilders.standaloneSetup(dungSaiResource)
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
    public static DungSai createEntity(EntityManager em) {
        DungSai dungSai = new DungSai()
            .cmLabel(DEFAULT_CM_LABEL)
            .inchLabel(DEFAULT_INCH_LABEL)
            .cmValue(DEFAULT_CM_VALUE)
            .inchValue(DEFAULT_INCH_VALUE);
        return dungSai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DungSai createUpdatedEntity(EntityManager em) {
        DungSai dungSai = new DungSai()
            .cmLabel(UPDATED_CM_LABEL)
            .inchLabel(UPDATED_INCH_LABEL)
            .cmValue(UPDATED_CM_VALUE)
            .inchValue(UPDATED_INCH_VALUE);
        return dungSai;
    }

    @BeforeEach
    public void initTest() {
        dungSai = createEntity(em);
    }

    @Test
    @Transactional
    public void createDungSai() throws Exception {
        int databaseSizeBeforeCreate = dungSaiRepository.findAll().size();

        // Create the DungSai
        DungSaiDTO dungSaiDTO = dungSaiMapper.toDto(dungSai);
        restDungSaiMockMvc.perform(post("/api/dung-sais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dungSaiDTO)))
            .andExpect(status().isCreated());

        // Validate the DungSai in the database
        List<DungSai> dungSaiList = dungSaiRepository.findAll();
        assertThat(dungSaiList).hasSize(databaseSizeBeforeCreate + 1);
        DungSai testDungSai = dungSaiList.get(dungSaiList.size() - 1);
        assertThat(testDungSai.getCmLabel()).isEqualTo(DEFAULT_CM_LABEL);
        assertThat(testDungSai.getInchLabel()).isEqualTo(DEFAULT_INCH_LABEL);
        assertThat(testDungSai.getCmValue()).isEqualTo(DEFAULT_CM_VALUE);
        assertThat(testDungSai.getInchValue()).isEqualTo(DEFAULT_INCH_VALUE);
    }

    @Test
    @Transactional
    public void createDungSaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dungSaiRepository.findAll().size();

        // Create the DungSai with an existing ID
        dungSai.setId(1L);
        DungSaiDTO dungSaiDTO = dungSaiMapper.toDto(dungSai);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDungSaiMockMvc.perform(post("/api/dung-sais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dungSaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DungSai in the database
        List<DungSai> dungSaiList = dungSaiRepository.findAll();
        assertThat(dungSaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDungSais() throws Exception {
        // Initialize the database
        dungSaiRepository.saveAndFlush(dungSai);

        // Get all the dungSaiList
        restDungSaiMockMvc.perform(get("/api/dung-sais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dungSai.getId().intValue())))
            .andExpect(jsonPath("$.[*].cmLabel").value(hasItem(DEFAULT_CM_LABEL)))
            .andExpect(jsonPath("$.[*].inchLabel").value(hasItem(DEFAULT_INCH_LABEL)))
            .andExpect(jsonPath("$.[*].cmValue").value(hasItem(DEFAULT_CM_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].inchValue").value(hasItem(DEFAULT_INCH_VALUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDungSai() throws Exception {
        // Initialize the database
        dungSaiRepository.saveAndFlush(dungSai);

        // Get the dungSai
        restDungSaiMockMvc.perform(get("/api/dung-sais/{id}", dungSai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dungSai.getId().intValue()))
            .andExpect(jsonPath("$.cmLabel").value(DEFAULT_CM_LABEL))
            .andExpect(jsonPath("$.inchLabel").value(DEFAULT_INCH_LABEL))
            .andExpect(jsonPath("$.cmValue").value(DEFAULT_CM_VALUE.doubleValue()))
            .andExpect(jsonPath("$.inchValue").value(DEFAULT_INCH_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDungSai() throws Exception {
        // Get the dungSai
        restDungSaiMockMvc.perform(get("/api/dung-sais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDungSai() throws Exception {
        // Initialize the database
        dungSaiRepository.saveAndFlush(dungSai);

        int databaseSizeBeforeUpdate = dungSaiRepository.findAll().size();

        // Update the dungSai
        DungSai updatedDungSai = dungSaiRepository.findById(dungSai.getId()).get();
        // Disconnect from session so that the updates on updatedDungSai are not directly saved in db
        em.detach(updatedDungSai);
        updatedDungSai
            .cmLabel(UPDATED_CM_LABEL)
            .inchLabel(UPDATED_INCH_LABEL)
            .cmValue(UPDATED_CM_VALUE)
            .inchValue(UPDATED_INCH_VALUE);
        DungSaiDTO dungSaiDTO = dungSaiMapper.toDto(updatedDungSai);

        restDungSaiMockMvc.perform(put("/api/dung-sais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dungSaiDTO)))
            .andExpect(status().isOk());

        // Validate the DungSai in the database
        List<DungSai> dungSaiList = dungSaiRepository.findAll();
        assertThat(dungSaiList).hasSize(databaseSizeBeforeUpdate);
        DungSai testDungSai = dungSaiList.get(dungSaiList.size() - 1);
        assertThat(testDungSai.getCmLabel()).isEqualTo(UPDATED_CM_LABEL);
        assertThat(testDungSai.getInchLabel()).isEqualTo(UPDATED_INCH_LABEL);
        assertThat(testDungSai.getCmValue()).isEqualTo(UPDATED_CM_VALUE);
        assertThat(testDungSai.getInchValue()).isEqualTo(UPDATED_INCH_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDungSai() throws Exception {
        int databaseSizeBeforeUpdate = dungSaiRepository.findAll().size();

        // Create the DungSai
        DungSaiDTO dungSaiDTO = dungSaiMapper.toDto(dungSai);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDungSaiMockMvc.perform(put("/api/dung-sais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dungSaiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DungSai in the database
        List<DungSai> dungSaiList = dungSaiRepository.findAll();
        assertThat(dungSaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDungSai() throws Exception {
        // Initialize the database
        dungSaiRepository.saveAndFlush(dungSai);

        int databaseSizeBeforeDelete = dungSaiRepository.findAll().size();

        // Delete the dungSai
        restDungSaiMockMvc.perform(delete("/api/dung-sais/{id}", dungSai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DungSai> dungSaiList = dungSaiRepository.findAll();
        assertThat(dungSaiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
