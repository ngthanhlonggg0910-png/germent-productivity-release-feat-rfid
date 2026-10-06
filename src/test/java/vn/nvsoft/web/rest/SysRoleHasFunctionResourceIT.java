package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SysRoleHasFunction;
import vn.nvsoft.repository.SysRoleHasFunctionRepository;
import vn.nvsoft.service.SysRoleHasFunctionService;
import vn.nvsoft.service.dto.SysRoleHasFunctionDTO;
import vn.nvsoft.service.mapper.SysRoleHasFunctionMapper;
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
 * Integration tests for the {@link SysRoleHasFunctionResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SysRoleHasFunctionResourceIT {

    private static final Boolean DEFAULT_ACTIVATED = false;
    private static final Boolean UPDATED_ACTIVATED = true;

    @Autowired
    private SysRoleHasFunctionRepository sysRoleHasFunctionRepository;

    @Autowired
    private SysRoleHasFunctionMapper sysRoleHasFunctionMapper;

    @Autowired
    private SysRoleHasFunctionService sysRoleHasFunctionService;

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

    private MockMvc restSysRoleHasFunctionMockMvc;

    private SysRoleHasFunction sysRoleHasFunction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysRoleHasFunctionResource sysRoleHasFunctionResource = new SysRoleHasFunctionResource(sysRoleHasFunctionService);
        this.restSysRoleHasFunctionMockMvc = MockMvcBuilders.standaloneSetup(sysRoleHasFunctionResource)
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
    public static SysRoleHasFunction createEntity(EntityManager em) {
        SysRoleHasFunction sysRoleHasFunction = new SysRoleHasFunction()
            .activated(DEFAULT_ACTIVATED);
        return sysRoleHasFunction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysRoleHasFunction createUpdatedEntity(EntityManager em) {
        SysRoleHasFunction sysRoleHasFunction = new SysRoleHasFunction()
            .activated(UPDATED_ACTIVATED);
        return sysRoleHasFunction;
    }

    @BeforeEach
    public void initTest() {
        sysRoleHasFunction = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysRoleHasFunction() throws Exception {
        int databaseSizeBeforeCreate = sysRoleHasFunctionRepository.findAll().size();

        // Create the SysRoleHasFunction
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO = sysRoleHasFunctionMapper.toDto(sysRoleHasFunction);
        restSysRoleHasFunctionMockMvc.perform(post("/api/sys-role-has-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleHasFunctionDTO)))
            .andExpect(status().isCreated());

        // Validate the SysRoleHasFunction in the database
        List<SysRoleHasFunction> sysRoleHasFunctionList = sysRoleHasFunctionRepository.findAll();
        assertThat(sysRoleHasFunctionList).hasSize(databaseSizeBeforeCreate + 1);
        SysRoleHasFunction testSysRoleHasFunction = sysRoleHasFunctionList.get(sysRoleHasFunctionList.size() - 1);
        assertThat(testSysRoleHasFunction.isActivated()).isEqualTo(DEFAULT_ACTIVATED);
    }

    @Test
    @Transactional
    public void createSysRoleHasFunctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysRoleHasFunctionRepository.findAll().size();

        // Create the SysRoleHasFunction with an existing ID
        sysRoleHasFunction.setId(1L);
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO = sysRoleHasFunctionMapper.toDto(sysRoleHasFunction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysRoleHasFunctionMockMvc.perform(post("/api/sys-role-has-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleHasFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleHasFunction in the database
        List<SysRoleHasFunction> sysRoleHasFunctionList = sysRoleHasFunctionRepository.findAll();
        assertThat(sysRoleHasFunctionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysRoleHasFunctions() throws Exception {
        // Initialize the database
        sysRoleHasFunctionRepository.saveAndFlush(sysRoleHasFunction);

        // Get all the sysRoleHasFunctionList
        restSysRoleHasFunctionMockMvc.perform(get("/api/sys-role-has-functions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysRoleHasFunction.getId().intValue())))
            .andExpect(jsonPath("$.[*].activated").value(hasItem(DEFAULT_ACTIVATED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSysRoleHasFunction() throws Exception {
        // Initialize the database
        sysRoleHasFunctionRepository.saveAndFlush(sysRoleHasFunction);

        // Get the sysRoleHasFunction
        restSysRoleHasFunctionMockMvc.perform(get("/api/sys-role-has-functions/{id}", sysRoleHasFunction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysRoleHasFunction.getId().intValue()))
            .andExpect(jsonPath("$.activated").value(DEFAULT_ACTIVATED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSysRoleHasFunction() throws Exception {
        // Get the sysRoleHasFunction
        restSysRoleHasFunctionMockMvc.perform(get("/api/sys-role-has-functions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysRoleHasFunction() throws Exception {
        // Initialize the database
        sysRoleHasFunctionRepository.saveAndFlush(sysRoleHasFunction);

        int databaseSizeBeforeUpdate = sysRoleHasFunctionRepository.findAll().size();

        // Update the sysRoleHasFunction
        SysRoleHasFunction updatedSysRoleHasFunction = sysRoleHasFunctionRepository.findById(sysRoleHasFunction.getId()).get();
        // Disconnect from session so that the updates on updatedSysRoleHasFunction are not directly saved in db
        em.detach(updatedSysRoleHasFunction);
        updatedSysRoleHasFunction
            .activated(UPDATED_ACTIVATED);
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO = sysRoleHasFunctionMapper.toDto(updatedSysRoleHasFunction);

        restSysRoleHasFunctionMockMvc.perform(put("/api/sys-role-has-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleHasFunctionDTO)))
            .andExpect(status().isOk());

        // Validate the SysRoleHasFunction in the database
        List<SysRoleHasFunction> sysRoleHasFunctionList = sysRoleHasFunctionRepository.findAll();
        assertThat(sysRoleHasFunctionList).hasSize(databaseSizeBeforeUpdate);
        SysRoleHasFunction testSysRoleHasFunction = sysRoleHasFunctionList.get(sysRoleHasFunctionList.size() - 1);
        assertThat(testSysRoleHasFunction.isActivated()).isEqualTo(UPDATED_ACTIVATED);
    }

    @Test
    @Transactional
    public void updateNonExistingSysRoleHasFunction() throws Exception {
        int databaseSizeBeforeUpdate = sysRoleHasFunctionRepository.findAll().size();

        // Create the SysRoleHasFunction
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO = sysRoleHasFunctionMapper.toDto(sysRoleHasFunction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysRoleHasFunctionMockMvc.perform(put("/api/sys-role-has-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysRoleHasFunctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysRoleHasFunction in the database
        List<SysRoleHasFunction> sysRoleHasFunctionList = sysRoleHasFunctionRepository.findAll();
        assertThat(sysRoleHasFunctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysRoleHasFunction() throws Exception {
        // Initialize the database
        sysRoleHasFunctionRepository.saveAndFlush(sysRoleHasFunction);

        int databaseSizeBeforeDelete = sysRoleHasFunctionRepository.findAll().size();

        // Delete the sysRoleHasFunction
        restSysRoleHasFunctionMockMvc.perform(delete("/api/sys-role-has-functions/{id}", sysRoleHasFunction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysRoleHasFunction> sysRoleHasFunctionList = sysRoleHasFunctionRepository.findAll();
        assertThat(sysRoleHasFunctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
