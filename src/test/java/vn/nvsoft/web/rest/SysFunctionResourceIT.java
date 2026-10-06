package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SysFunction;
import vn.nvsoft.repository.SysFunctionRepository;
import vn.nvsoft.service.SysFunctionService;
import vn.nvsoft.service.dto.SysFunctionDTO;
import vn.nvsoft.service.mapper.SysFunctionMapper;
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
 * Integration tests for the {@link SysFunctionResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SysFunctionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final String DEFAULT_IMAGE_URI = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URI = "BBBBBBBBBB";

    private static final String DEFAULT_EVENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOTKEY = "AAAAA";
    private static final String UPDATED_HOTKEY = "BBBBB";

    @Autowired
    private SysFunctionRepository sysFunctionRepository;

    @Autowired
    private SysFunctionMapper sysFunctionMapper;

    @Autowired
    private SysFunctionService sysFunctionService;

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

    private MockMvc restSysFunctionMockMvc;

    private SysFunction sysFunction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysFunctionResource sysFunctionResource = new SysFunctionResource(sysFunctionService);
        this.restSysFunctionMockMvc = MockMvcBuilders.standaloneSetup(sysFunctionResource)
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
    public static SysFunction createEntity(EntityManager em) {
        SysFunction sysFunction = new SysFunction()
            .name(DEFAULT_NAME)
            .caption(DEFAULT_CAPTION)
            .level(DEFAULT_LEVEL)
            .imageUri(DEFAULT_IMAGE_URI)
            .eventName(DEFAULT_EVENT_NAME)
            .hotkey(DEFAULT_HOTKEY);
        return sysFunction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysFunction createUpdatedEntity(EntityManager em) {
        SysFunction sysFunction = new SysFunction()
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .level(UPDATED_LEVEL)
            .imageUri(UPDATED_IMAGE_URI)
            .eventName(UPDATED_EVENT_NAME)
            .hotkey(UPDATED_HOTKEY);
        return sysFunction;
    }

    @BeforeEach
    public void initTest() {
        sysFunction = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysFunction() throws Exception {
        int databaseSizeBeforeCreate = sysFunctionRepository.findAll().size();

        // Create the SysFunction
        SysFunctionDTO sysFunctionDTO = sysFunctionMapper.toDto(sysFunction);
        restSysFunctionMockMvc.perform(post("/api/sys-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFunctionDTO)))
            .andExpect(status().isCreated());

        // Validate the SysFunction in the database
        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeCreate + 1);
        SysFunction testSysFunction = sysFunctionList.get(sysFunctionList.size() - 1);
        assertThat(testSysFunction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysFunction.getCaption()).isEqualTo(DEFAULT_CAPTION);
        assertThat(testSysFunction.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSysFunction.getImageUri()).isEqualTo(DEFAULT_IMAGE_URI);
        assertThat(testSysFunction.getEventName()).isEqualTo(DEFAULT_EVENT_NAME);
        assertThat(testSysFunction.getHotkey()).isEqualTo(DEFAULT_HOTKEY);
    }

    @Test
    @Transactional
    public void createSysFunctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysFunctionRepository.findAll().size();

        // Create the SysFunction with an existing ID
        sysFunction.setId(1L);
        SysFunctionDTO sysFunctionDTO = sysFunctionMapper.toDto(sysFunction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysFunctionMockMvc.perform(post("/api/sys-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysFunction in the database
        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysFunctionRepository.findAll().size();
        // set the field null
        sysFunction.setName(null);

        // Create the SysFunction, which fails.
        SysFunctionDTO sysFunctionDTO = sysFunctionMapper.toDto(sysFunction);

        restSysFunctionMockMvc.perform(post("/api/sys-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFunctionDTO)))
            .andExpect(status().isBadRequest());

        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysFunctions() throws Exception {
        // Initialize the database
        sysFunctionRepository.saveAndFlush(sysFunction);

        // Get all the sysFunctionList
        restSysFunctionMockMvc.perform(get("/api/sys-functions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysFunction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].imageUri").value(hasItem(DEFAULT_IMAGE_URI)))
            .andExpect(jsonPath("$.[*].eventName").value(hasItem(DEFAULT_EVENT_NAME)))
            .andExpect(jsonPath("$.[*].hotkey").value(hasItem(DEFAULT_HOTKEY)));
    }
    
    @Test
    @Transactional
    public void getSysFunction() throws Exception {
        // Initialize the database
        sysFunctionRepository.saveAndFlush(sysFunction);

        // Get the sysFunction
        restSysFunctionMockMvc.perform(get("/api/sys-functions/{id}", sysFunction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysFunction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.imageUri").value(DEFAULT_IMAGE_URI))
            .andExpect(jsonPath("$.eventName").value(DEFAULT_EVENT_NAME))
            .andExpect(jsonPath("$.hotkey").value(DEFAULT_HOTKEY));
    }

    @Test
    @Transactional
    public void getNonExistingSysFunction() throws Exception {
        // Get the sysFunction
        restSysFunctionMockMvc.perform(get("/api/sys-functions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysFunction() throws Exception {
        // Initialize the database
        sysFunctionRepository.saveAndFlush(sysFunction);

        int databaseSizeBeforeUpdate = sysFunctionRepository.findAll().size();

        // Update the sysFunction
        SysFunction updatedSysFunction = sysFunctionRepository.findById(sysFunction.getId()).get();
        // Disconnect from session so that the updates on updatedSysFunction are not directly saved in db
        em.detach(updatedSysFunction);
        updatedSysFunction
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .level(UPDATED_LEVEL)
            .imageUri(UPDATED_IMAGE_URI)
            .eventName(UPDATED_EVENT_NAME)
            .hotkey(UPDATED_HOTKEY);
        SysFunctionDTO sysFunctionDTO = sysFunctionMapper.toDto(updatedSysFunction);

        restSysFunctionMockMvc.perform(put("/api/sys-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFunctionDTO)))
            .andExpect(status().isOk());

        // Validate the SysFunction in the database
        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeUpdate);
        SysFunction testSysFunction = sysFunctionList.get(sysFunctionList.size() - 1);
        assertThat(testSysFunction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysFunction.getCaption()).isEqualTo(UPDATED_CAPTION);
        assertThat(testSysFunction.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSysFunction.getImageUri()).isEqualTo(UPDATED_IMAGE_URI);
        assertThat(testSysFunction.getEventName()).isEqualTo(UPDATED_EVENT_NAME);
        assertThat(testSysFunction.getHotkey()).isEqualTo(UPDATED_HOTKEY);
    }

    @Test
    @Transactional
    public void updateNonExistingSysFunction() throws Exception {
        int databaseSizeBeforeUpdate = sysFunctionRepository.findAll().size();

        // Create the SysFunction
        SysFunctionDTO sysFunctionDTO = sysFunctionMapper.toDto(sysFunction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysFunctionMockMvc.perform(put("/api/sys-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysFunction in the database
        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysFunction() throws Exception {
        // Initialize the database
        sysFunctionRepository.saveAndFlush(sysFunction);

        int databaseSizeBeforeDelete = sysFunctionRepository.findAll().size();

        // Delete the sysFunction
        restSysFunctionMockMvc.perform(delete("/api/sys-functions/{id}", sysFunction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysFunction> sysFunctionList = sysFunctionRepository.findAll();
        assertThat(sysFunctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
