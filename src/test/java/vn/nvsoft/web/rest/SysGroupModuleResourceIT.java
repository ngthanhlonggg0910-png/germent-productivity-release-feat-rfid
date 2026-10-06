package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SysGroupModule;
import vn.nvsoft.repository.SysGroupModuleRepository;
import vn.nvsoft.service.SysGroupModuleService;
import vn.nvsoft.service.dto.SysGroupModuleDTO;
import vn.nvsoft.service.mapper.SysGroupModuleMapper;
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
 * Integration tests for the {@link SysGroupModuleResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SysGroupModuleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_IMAGE_URI = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_IMAGE_URI = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_IMAGE_URI = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_IMAGE_URI = "BBBBBBBBBB";

    @Autowired
    private SysGroupModuleRepository sysGroupModuleRepository;

    @Autowired
    private SysGroupModuleMapper sysGroupModuleMapper;

    @Autowired
    private SysGroupModuleService sysGroupModuleService;

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

    private MockMvc restSysGroupModuleMockMvc;

    private SysGroupModule sysGroupModule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysGroupModuleResource sysGroupModuleResource = new SysGroupModuleResource(sysGroupModuleService);
        this.restSysGroupModuleMockMvc = MockMvcBuilders.standaloneSetup(sysGroupModuleResource)
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
    public static SysGroupModule createEntity(EntityManager em) {
        SysGroupModule sysGroupModule = new SysGroupModule()
            .name(DEFAULT_NAME)
            .caption(DEFAULT_CAPTION)
            .primaryImageUri(DEFAULT_PRIMARY_IMAGE_URI)
            .secondaryImageUri(DEFAULT_SECONDARY_IMAGE_URI);
        return sysGroupModule;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysGroupModule createUpdatedEntity(EntityManager em) {
        SysGroupModule sysGroupModule = new SysGroupModule()
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .primaryImageUri(UPDATED_PRIMARY_IMAGE_URI)
            .secondaryImageUri(UPDATED_SECONDARY_IMAGE_URI);
        return sysGroupModule;
    }

    @BeforeEach
    public void initTest() {
        sysGroupModule = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysGroupModule() throws Exception {
        int databaseSizeBeforeCreate = sysGroupModuleRepository.findAll().size();

        // Create the SysGroupModule
        SysGroupModuleDTO sysGroupModuleDTO = sysGroupModuleMapper.toDto(sysGroupModule);
        restSysGroupModuleMockMvc.perform(post("/api/sys-group-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGroupModuleDTO)))
            .andExpect(status().isCreated());

        // Validate the SysGroupModule in the database
        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeCreate + 1);
        SysGroupModule testSysGroupModule = sysGroupModuleList.get(sysGroupModuleList.size() - 1);
        assertThat(testSysGroupModule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysGroupModule.getCaption()).isEqualTo(DEFAULT_CAPTION);
        assertThat(testSysGroupModule.getPrimaryImageUri()).isEqualTo(DEFAULT_PRIMARY_IMAGE_URI);
        assertThat(testSysGroupModule.getSecondaryImageUri()).isEqualTo(DEFAULT_SECONDARY_IMAGE_URI);
    }

    @Test
    @Transactional
    public void createSysGroupModuleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysGroupModuleRepository.findAll().size();

        // Create the SysGroupModule with an existing ID
        sysGroupModule.setId(1L);
        SysGroupModuleDTO sysGroupModuleDTO = sysGroupModuleMapper.toDto(sysGroupModule);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysGroupModuleMockMvc.perform(post("/api/sys-group-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGroupModuleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysGroupModule in the database
        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysGroupModuleRepository.findAll().size();
        // set the field null
        sysGroupModule.setName(null);

        // Create the SysGroupModule, which fails.
        SysGroupModuleDTO sysGroupModuleDTO = sysGroupModuleMapper.toDto(sysGroupModule);

        restSysGroupModuleMockMvc.perform(post("/api/sys-group-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGroupModuleDTO)))
            .andExpect(status().isBadRequest());

        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysGroupModules() throws Exception {
        // Initialize the database
        sysGroupModuleRepository.saveAndFlush(sysGroupModule);

        // Get all the sysGroupModuleList
        restSysGroupModuleMockMvc.perform(get("/api/sys-group-modules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysGroupModule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].primaryImageUri").value(hasItem(DEFAULT_PRIMARY_IMAGE_URI)))
            .andExpect(jsonPath("$.[*].secondaryImageUri").value(hasItem(DEFAULT_SECONDARY_IMAGE_URI)));
    }
    
    @Test
    @Transactional
    public void getSysGroupModule() throws Exception {
        // Initialize the database
        sysGroupModuleRepository.saveAndFlush(sysGroupModule);

        // Get the sysGroupModule
        restSysGroupModuleMockMvc.perform(get("/api/sys-group-modules/{id}", sysGroupModule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysGroupModule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.primaryImageUri").value(DEFAULT_PRIMARY_IMAGE_URI))
            .andExpect(jsonPath("$.secondaryImageUri").value(DEFAULT_SECONDARY_IMAGE_URI));
    }

    @Test
    @Transactional
    public void getNonExistingSysGroupModule() throws Exception {
        // Get the sysGroupModule
        restSysGroupModuleMockMvc.perform(get("/api/sys-group-modules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysGroupModule() throws Exception {
        // Initialize the database
        sysGroupModuleRepository.saveAndFlush(sysGroupModule);

        int databaseSizeBeforeUpdate = sysGroupModuleRepository.findAll().size();

        // Update the sysGroupModule
        SysGroupModule updatedSysGroupModule = sysGroupModuleRepository.findById(sysGroupModule.getId()).get();
        // Disconnect from session so that the updates on updatedSysGroupModule are not directly saved in db
        em.detach(updatedSysGroupModule);
        updatedSysGroupModule
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .primaryImageUri(UPDATED_PRIMARY_IMAGE_URI)
            .secondaryImageUri(UPDATED_SECONDARY_IMAGE_URI);
        SysGroupModuleDTO sysGroupModuleDTO = sysGroupModuleMapper.toDto(updatedSysGroupModule);

        restSysGroupModuleMockMvc.perform(put("/api/sys-group-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGroupModuleDTO)))
            .andExpect(status().isOk());

        // Validate the SysGroupModule in the database
        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeUpdate);
        SysGroupModule testSysGroupModule = sysGroupModuleList.get(sysGroupModuleList.size() - 1);
        assertThat(testSysGroupModule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysGroupModule.getCaption()).isEqualTo(UPDATED_CAPTION);
        assertThat(testSysGroupModule.getPrimaryImageUri()).isEqualTo(UPDATED_PRIMARY_IMAGE_URI);
        assertThat(testSysGroupModule.getSecondaryImageUri()).isEqualTo(UPDATED_SECONDARY_IMAGE_URI);
    }

    @Test
    @Transactional
    public void updateNonExistingSysGroupModule() throws Exception {
        int databaseSizeBeforeUpdate = sysGroupModuleRepository.findAll().size();

        // Create the SysGroupModule
        SysGroupModuleDTO sysGroupModuleDTO = sysGroupModuleMapper.toDto(sysGroupModule);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysGroupModuleMockMvc.perform(put("/api/sys-group-modules")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGroupModuleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysGroupModule in the database
        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysGroupModule() throws Exception {
        // Initialize the database
        sysGroupModuleRepository.saveAndFlush(sysGroupModule);

        int databaseSizeBeforeDelete = sysGroupModuleRepository.findAll().size();

        // Delete the sysGroupModule
        restSysGroupModuleMockMvc.perform(delete("/api/sys-group-modules/{id}", sysGroupModule.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysGroupModule> sysGroupModuleList = sysGroupModuleRepository.findAll();
        assertThat(sysGroupModuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
