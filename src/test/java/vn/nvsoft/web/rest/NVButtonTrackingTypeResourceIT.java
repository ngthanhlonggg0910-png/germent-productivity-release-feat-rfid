package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.repository.NVButtonTrackingTypeRepository;
import vn.nvsoft.service.NVButtonTrackingTypeService;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;
import vn.nvsoft.service.mapper.NVButtonTrackingTypeMapper;
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
 * Integration tests for the {@link NVButtonTrackingTypeResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class NVButtonTrackingTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_STATE = 1;
    private static final Integer UPDATED_NUMBER_OF_STATE = 2;

    @Autowired
    private NVButtonTrackingTypeRepository nVButtonTrackingTypeRepository;

    @Autowired
    private NVButtonTrackingTypeMapper nVButtonTrackingTypeMapper;

    @Autowired
    private NVButtonTrackingTypeService nVButtonTrackingTypeService;

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

    private MockMvc restNVButtonTrackingTypeMockMvc;

    private NVButtonTrackingType nVButtonTrackingType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NVButtonTrackingTypeResource nVButtonTrackingTypeResource = new NVButtonTrackingTypeResource(nVButtonTrackingTypeService);
        this.restNVButtonTrackingTypeMockMvc = MockMvcBuilders.standaloneSetup(nVButtonTrackingTypeResource)
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
    public static NVButtonTrackingType createEntity(EntityManager em) {
        NVButtonTrackingType nVButtonTrackingType = new NVButtonTrackingType()
            .name(DEFAULT_NAME)
            .numberOfState(DEFAULT_NUMBER_OF_STATE);
        return nVButtonTrackingType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NVButtonTrackingType createUpdatedEntity(EntityManager em) {
        NVButtonTrackingType nVButtonTrackingType = new NVButtonTrackingType()
            .name(UPDATED_NAME)
            .numberOfState(UPDATED_NUMBER_OF_STATE);
        return nVButtonTrackingType;
    }

    @BeforeEach
    public void initTest() {
        nVButtonTrackingType = createEntity(em);
    }

    @Test
    @Transactional
    public void createNVButtonTrackingType() throws Exception {
        int databaseSizeBeforeCreate = nVButtonTrackingTypeRepository.findAll().size();

        // Create the NVButtonTrackingType
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO = nVButtonTrackingTypeMapper.toDto(nVButtonTrackingType);
        restNVButtonTrackingTypeMockMvc.perform(post("/api/nv-button-tracking-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonTrackingTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the NVButtonTrackingType in the database
        List<NVButtonTrackingType> nVButtonTrackingTypeList = nVButtonTrackingTypeRepository.findAll();
        assertThat(nVButtonTrackingTypeList).hasSize(databaseSizeBeforeCreate + 1);
        NVButtonTrackingType testNVButtonTrackingType = nVButtonTrackingTypeList.get(nVButtonTrackingTypeList.size() - 1);
        assertThat(testNVButtonTrackingType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNVButtonTrackingType.getNumberOfState()).isEqualTo(DEFAULT_NUMBER_OF_STATE);
    }

    @Test
    @Transactional
    public void createNVButtonTrackingTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nVButtonTrackingTypeRepository.findAll().size();

        // Create the NVButtonTrackingType with an existing ID
        nVButtonTrackingType.setId(1L);
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO = nVButtonTrackingTypeMapper.toDto(nVButtonTrackingType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNVButtonTrackingTypeMockMvc.perform(post("/api/nv-button-tracking-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonTrackingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NVButtonTrackingType in the database
        List<NVButtonTrackingType> nVButtonTrackingTypeList = nVButtonTrackingTypeRepository.findAll();
        assertThat(nVButtonTrackingTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNVButtonTrackingTypes() throws Exception {
        // Initialize the database
        nVButtonTrackingTypeRepository.saveAndFlush(nVButtonTrackingType);

        // Get all the nVButtonTrackingTypeList
        restNVButtonTrackingTypeMockMvc.perform(get("/api/nv-button-tracking-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nVButtonTrackingType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].numberOfState").value(hasItem(DEFAULT_NUMBER_OF_STATE)));
    }
    
    @Test
    @Transactional
    public void getNVButtonTrackingType() throws Exception {
        // Initialize the database
        nVButtonTrackingTypeRepository.saveAndFlush(nVButtonTrackingType);

        // Get the nVButtonTrackingType
        restNVButtonTrackingTypeMockMvc.perform(get("/api/nv-button-tracking-types/{id}", nVButtonTrackingType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nVButtonTrackingType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.numberOfState").value(DEFAULT_NUMBER_OF_STATE));
    }

    @Test
    @Transactional
    public void getNonExistingNVButtonTrackingType() throws Exception {
        // Get the nVButtonTrackingType
        restNVButtonTrackingTypeMockMvc.perform(get("/api/nv-button-tracking-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNVButtonTrackingType() throws Exception {
        // Initialize the database
        nVButtonTrackingTypeRepository.saveAndFlush(nVButtonTrackingType);

        int databaseSizeBeforeUpdate = nVButtonTrackingTypeRepository.findAll().size();

        // Update the nVButtonTrackingType
        NVButtonTrackingType updatedNVButtonTrackingType = nVButtonTrackingTypeRepository.findById(nVButtonTrackingType.getId()).get();
        // Disconnect from session so that the updates on updatedNVButtonTrackingType are not directly saved in db
        em.detach(updatedNVButtonTrackingType);
        updatedNVButtonTrackingType
            .name(UPDATED_NAME)
            .numberOfState(UPDATED_NUMBER_OF_STATE);
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO = nVButtonTrackingTypeMapper.toDto(updatedNVButtonTrackingType);

        restNVButtonTrackingTypeMockMvc.perform(put("/api/nv-button-tracking-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonTrackingTypeDTO)))
            .andExpect(status().isOk());

        // Validate the NVButtonTrackingType in the database
        List<NVButtonTrackingType> nVButtonTrackingTypeList = nVButtonTrackingTypeRepository.findAll();
        assertThat(nVButtonTrackingTypeList).hasSize(databaseSizeBeforeUpdate);
        NVButtonTrackingType testNVButtonTrackingType = nVButtonTrackingTypeList.get(nVButtonTrackingTypeList.size() - 1);
        assertThat(testNVButtonTrackingType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNVButtonTrackingType.getNumberOfState()).isEqualTo(UPDATED_NUMBER_OF_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingNVButtonTrackingType() throws Exception {
        int databaseSizeBeforeUpdate = nVButtonTrackingTypeRepository.findAll().size();

        // Create the NVButtonTrackingType
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO = nVButtonTrackingTypeMapper.toDto(nVButtonTrackingType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNVButtonTrackingTypeMockMvc.perform(put("/api/nv-button-tracking-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nVButtonTrackingTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NVButtonTrackingType in the database
        List<NVButtonTrackingType> nVButtonTrackingTypeList = nVButtonTrackingTypeRepository.findAll();
        assertThat(nVButtonTrackingTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNVButtonTrackingType() throws Exception {
        // Initialize the database
        nVButtonTrackingTypeRepository.saveAndFlush(nVButtonTrackingType);

        int databaseSizeBeforeDelete = nVButtonTrackingTypeRepository.findAll().size();

        // Delete the nVButtonTrackingType
        restNVButtonTrackingTypeMockMvc.perform(delete("/api/nv-button-tracking-types/{id}", nVButtonTrackingType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NVButtonTrackingType> nVButtonTrackingTypeList = nVButtonTrackingTypeRepository.findAll();
        assertThat(nVButtonTrackingTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
