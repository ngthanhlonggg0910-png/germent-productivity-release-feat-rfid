package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SysPermission;
import vn.nvsoft.repository.SysPermissionRepository;
import vn.nvsoft.service.SysPermissionService;
import vn.nvsoft.service.dto.SysPermissionDTO;
import vn.nvsoft.service.mapper.SysPermissionMapper;
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
 * Integration tests for the {@link SysPermissionResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SysPermissionResourceIT {

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
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysPermissionService sysPermissionService;

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

    private MockMvc restSysPermissionMockMvc;

    private SysPermission sysPermission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysPermissionResource sysPermissionResource = new SysPermissionResource(sysPermissionService);
        this.restSysPermissionMockMvc = MockMvcBuilders.standaloneSetup(sysPermissionResource)
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
    public static SysPermission createEntity(EntityManager em) {
        SysPermission sysPermission = new SysPermission()
            .name(DEFAULT_NAME)
            .caption(DEFAULT_CAPTION)
            .level(DEFAULT_LEVEL)
            .imageUri(DEFAULT_IMAGE_URI)
            .eventName(DEFAULT_EVENT_NAME)
            .hotkey(DEFAULT_HOTKEY);
        return sysPermission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysPermission createUpdatedEntity(EntityManager em) {
        SysPermission sysPermission = new SysPermission()
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .level(UPDATED_LEVEL)
            .imageUri(UPDATED_IMAGE_URI)
            .eventName(UPDATED_EVENT_NAME)
            .hotkey(UPDATED_HOTKEY);
        return sysPermission;
    }

    @BeforeEach
    public void initTest() {
        sysPermission = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysPermission() throws Exception {
        int databaseSizeBeforeCreate = sysPermissionRepository.findAll().size();

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);
        restSysPermissionMockMvc.perform(post("/api/sys-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO)))
            .andExpect(status().isCreated());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeCreate + 1);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysPermission.getCaption()).isEqualTo(DEFAULT_CAPTION);
        assertThat(testSysPermission.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testSysPermission.getImageUri()).isEqualTo(DEFAULT_IMAGE_URI);
        assertThat(testSysPermission.getEventName()).isEqualTo(DEFAULT_EVENT_NAME);
        assertThat(testSysPermission.getHotkey()).isEqualTo(DEFAULT_HOTKEY);
    }

    @Test
    @Transactional
    public void createSysPermissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysPermissionRepository.findAll().size();

        // Create the SysPermission with an existing ID
        sysPermission.setId(1L);
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysPermissionMockMvc.perform(post("/api/sys-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysPermissionRepository.findAll().size();
        // set the field null
        sysPermission.setName(null);

        // Create the SysPermission, which fails.
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        restSysPermissionMockMvc.perform(post("/api/sys-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO)))
            .andExpect(status().isBadRequest());

        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysPermissions() throws Exception {
        // Initialize the database
        sysPermissionRepository.saveAndFlush(sysPermission);

        // Get all the sysPermissionList
        restSysPermissionMockMvc.perform(get("/api/sys-permissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysPermission.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].imageUri").value(hasItem(DEFAULT_IMAGE_URI)))
            .andExpect(jsonPath("$.[*].eventName").value(hasItem(DEFAULT_EVENT_NAME)))
            .andExpect(jsonPath("$.[*].hotkey").value(hasItem(DEFAULT_HOTKEY)));
    }
    
    @Test
    @Transactional
    public void getSysPermission() throws Exception {
        // Initialize the database
        sysPermissionRepository.saveAndFlush(sysPermission);

        // Get the sysPermission
        restSysPermissionMockMvc.perform(get("/api/sys-permissions/{id}", sysPermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysPermission.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.imageUri").value(DEFAULT_IMAGE_URI))
            .andExpect(jsonPath("$.eventName").value(DEFAULT_EVENT_NAME))
            .andExpect(jsonPath("$.hotkey").value(DEFAULT_HOTKEY));
    }

    @Test
    @Transactional
    public void getNonExistingSysPermission() throws Exception {
        // Get the sysPermission
        restSysPermissionMockMvc.perform(get("/api/sys-permissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysPermission() throws Exception {
        // Initialize the database
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();

        // Update the sysPermission
        SysPermission updatedSysPermission = sysPermissionRepository.findById(sysPermission.getId()).get();
        // Disconnect from session so that the updates on updatedSysPermission are not directly saved in db
        em.detach(updatedSysPermission);
        updatedSysPermission
            .name(UPDATED_NAME)
            .caption(UPDATED_CAPTION)
            .level(UPDATED_LEVEL)
            .imageUri(UPDATED_IMAGE_URI)
            .eventName(UPDATED_EVENT_NAME)
            .hotkey(UPDATED_HOTKEY);
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(updatedSysPermission);

        restSysPermissionMockMvc.perform(put("/api/sys-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO)))
            .andExpect(status().isOk());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
        SysPermission testSysPermission = sysPermissionList.get(sysPermissionList.size() - 1);
        assertThat(testSysPermission.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysPermission.getCaption()).isEqualTo(UPDATED_CAPTION);
        assertThat(testSysPermission.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testSysPermission.getImageUri()).isEqualTo(UPDATED_IMAGE_URI);
        assertThat(testSysPermission.getEventName()).isEqualTo(UPDATED_EVENT_NAME);
        assertThat(testSysPermission.getHotkey()).isEqualTo(UPDATED_HOTKEY);
    }

    @Test
    @Transactional
    public void updateNonExistingSysPermission() throws Exception {
        int databaseSizeBeforeUpdate = sysPermissionRepository.findAll().size();

        // Create the SysPermission
        SysPermissionDTO sysPermissionDTO = sysPermissionMapper.toDto(sysPermission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysPermissionMockMvc.perform(put("/api/sys-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysPermissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysPermission in the database
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysPermission() throws Exception {
        // Initialize the database
        sysPermissionRepository.saveAndFlush(sysPermission);

        int databaseSizeBeforeDelete = sysPermissionRepository.findAll().size();

        // Delete the sysPermission
        restSysPermissionMockMvc.perform(delete("/api/sys-permissions/{id}", sysPermission.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysPermission> sysPermissionList = sysPermissionRepository.findAll();
        assertThat(sysPermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
