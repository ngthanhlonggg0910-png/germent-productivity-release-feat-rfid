package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SysModule;
import vn.nvsoft.repository.SysModuleRepository;
import vn.nvsoft.service.SysModuleService;
import vn.nvsoft.service.dto.SysModuleDTO;
import vn.nvsoft.service.mapper.SysModuleMapper;
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
 * Integration tests for the {@link SysModuleResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SysModuleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URI = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URI = "BBBBBBBBBB";

    @Autowired
    private SysModuleRepository sysModuleRepository;

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Autowired
    private SysModuleService sysModuleService;

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

    private MockMvc restSysModuleMockMvc;

    private SysModule sysModule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysModuleResource sysModuleResource = new SysModuleResource(sysModuleService);
        this.restSysModuleMockMvc = MockMvcBuilders.standaloneSetup(sysModuleResource)
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
    public static SysModule createEntity(EntityManager em) {
        SysModule sysModule = new SysModule()
            .name(DEFAULT_NAME)
            .caption(DEFAULT_CAPTION)
            .imageUri(DEFAULT_IMAGE_URI);
        return sysModule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysModule createUpdatedEntity(EntityManager em) {
        SysModule sysModule = new SysModule()
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .imageUri(UPDATED_IMAGE_URI);
        return sysModule;
    }

    @BeforeEach
    public void initTest() {
        sysModule = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysModule() throws Exception {
        int databaseSizeBeforeCreate = sysModuleRepository.findAll().size();

        // Create the SysModule
        SysModuleDTO sysModuleDTO = sysModuleMapper.toDto(sysModule);
        restSysModuleMockMvc.perform(post("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModuleDTO)))
            .andExpect(status().isCreated());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeCreate + 1);
        SysModule testSysModule = sysModuleList.get(sysModuleList.size() - 1);
        assertThat(testSysModule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysModule.getCaption()).isEqualTo(DEFAULT_CAPTION);
        assertThat(testSysModule.getImageUri()).isEqualTo(DEFAULT_IMAGE_URI);
    }

    @Test
    @Transactional
    public void createSysModuleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysModuleRepository.findAll().size();

        // Create the SysModule with an existing ID
        sysModule.setId(1L);
        SysModuleDTO sysModuleDTO = sysModuleMapper.toDto(sysModule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysModuleMockMvc.perform(post("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModuleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysModuleRepository.findAll().size();
        // set the field null
        sysModule.setName(null);

        // Create the SysModule, which fails.
        SysModuleDTO sysModuleDTO = sysModuleMapper.toDto(sysModule);

        restSysModuleMockMvc.perform(post("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModuleDTO)))
            .andExpect(status().isBadRequest());

        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysModules() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        // Get all the sysModuleList
        restSysModuleMockMvc.perform(get("/api/sys-modules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysModule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].imageUri").value(hasItem(DEFAULT_IMAGE_URI)));
    }
    
    @Test
    @Transactional
    public void getSysModule() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        // Get the sysModule
        restSysModuleMockMvc.perform(get("/api/sys-modules/{id}", sysModule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysModule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.imageUri").value(DEFAULT_IMAGE_URI));
    }

    @Test
    @Transactional
    public void getNonExistingSysModule() throws Exception {
        // Get the sysModule
        restSysModuleMockMvc.perform(get("/api/sys-modules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysModule() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        int databaseSizeBeforeUpdate = sysModuleRepository.findAll().size();

        // Update the sysModule
        SysModule updatedSysModule = sysModuleRepository.findById(sysModule.getId()).get();
        // Disconnect from session so that the updates on updatedSysModule are not directly saved in db
        em.detach(updatedSysModule);
        updatedSysModule
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .imageUri(UPDATED_IMAGE_URI);
        SysModuleDTO sysModuleDTO = sysModuleMapper.toDto(updatedSysModule);

        restSysModuleMockMvc.perform(put("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModuleDTO)))
            .andExpect(status().isOk());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeUpdate);
        SysModule testSysModule = sysModuleList.get(sysModuleList.size() - 1);
        assertThat(testSysModule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysModule.getCaption()).isEqualTo(UPDATED_CAPTION);
        assertThat(testSysModule.getImageUri()).isEqualTo(UPDATED_IMAGE_URI);
    }

    @Test
    @Transactional
    public void updateNonExistingSysModule() throws Exception {
        int databaseSizeBeforeUpdate = sysModuleRepository.findAll().size();

        // Create the SysModule
        SysModuleDTO sysModuleDTO = sysModuleMapper.toDto(sysModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysModuleMockMvc.perform(put("/api/sys-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysModuleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysModule in the database
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysModule() throws Exception {
        // Initialize the database
        sysModuleRepository.saveAndFlush(sysModule);

        int databaseSizeBeforeDelete = sysModuleRepository.findAll().size();

        // Delete the sysModule
        restSysModuleMockMvc.perform(delete("/api/sys-modules/{id}", sysModule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysModule> sysModuleList = sysModuleRepository.findAll();
        assertThat(sysModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
