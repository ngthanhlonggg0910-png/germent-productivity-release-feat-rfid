package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.MapButtonAction;
import vn.nvsoft.repository.MapButtonActionRepository;
import vn.nvsoft.service.MapButtonActionService;
import vn.nvsoft.service.dto.MapButtonActionDTO;
import vn.nvsoft.service.mapper.MapButtonActionMapper;
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

import vn.nvsoft.domain.enumeration.NVButtonState;
/**
 * Integration tests for the {@link MapButtonActionResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class MapButtonActionResourceIT {

    private static final Integer DEFAULT_SOURCE_ALIAS_STATE = 1;
    private static final Integer UPDATED_SOURCE_ALIAS_STATE = 2;

    private static final NVButtonState DEFAULT_TARGET_STATE = NVButtonState.ON;
    private static final NVButtonState UPDATED_TARGET_STATE = NVButtonState.OFF;

    @Autowired
    private MapButtonActionRepository mapButtonActionRepository;

    @Autowired
    private MapButtonActionMapper mapButtonActionMapper;

    @Autowired
    private MapButtonActionService mapButtonActionService;

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

    private MockMvc restMapButtonActionMockMvc;

    private MapButtonAction mapButtonAction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapButtonActionResource mapButtonActionResource = new MapButtonActionResource(mapButtonActionService);
        this.restMapButtonActionMockMvc = MockMvcBuilders.standaloneSetup(mapButtonActionResource)
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
    public static MapButtonAction createEntity(EntityManager em) {
        MapButtonAction mapButtonAction = new MapButtonAction()
            .sourceAliasState(DEFAULT_SOURCE_ALIAS_STATE)
            .targetState(DEFAULT_TARGET_STATE);
        return mapButtonAction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MapButtonAction createUpdatedEntity(EntityManager em) {
        MapButtonAction mapButtonAction = new MapButtonAction()
            .sourceAliasState(UPDATED_SOURCE_ALIAS_STATE)
            .targetState(UPDATED_TARGET_STATE);
        return mapButtonAction;
    }

    @BeforeEach
    public void initTest() {
        mapButtonAction = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapButtonAction() throws Exception {
        int databaseSizeBeforeCreate = mapButtonActionRepository.findAll().size();

        // Create the MapButtonAction
        MapButtonActionDTO mapButtonActionDTO = mapButtonActionMapper.toDto(mapButtonAction);
        restMapButtonActionMockMvc.perform(post("/api/map-button-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapButtonActionDTO)))
            .andExpect(status().isCreated());

        // Validate the MapButtonAction in the database
        List<MapButtonAction> mapButtonActionList = mapButtonActionRepository.findAll();
        assertThat(mapButtonActionList).hasSize(databaseSizeBeforeCreate + 1);
        MapButtonAction testMapButtonAction = mapButtonActionList.get(mapButtonActionList.size() - 1);
        assertThat(testMapButtonAction.getSourceAliasState()).isEqualTo(DEFAULT_SOURCE_ALIAS_STATE);
        assertThat(testMapButtonAction.getTargetState()).isEqualTo(DEFAULT_TARGET_STATE);
    }

    @Test
    @Transactional
    public void createMapButtonActionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapButtonActionRepository.findAll().size();

        // Create the MapButtonAction with an existing ID
        mapButtonAction.setId(1L);
        MapButtonActionDTO mapButtonActionDTO = mapButtonActionMapper.toDto(mapButtonAction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapButtonActionMockMvc.perform(post("/api/map-button-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapButtonActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapButtonAction in the database
        List<MapButtonAction> mapButtonActionList = mapButtonActionRepository.findAll();
        assertThat(mapButtonActionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMapButtonActions() throws Exception {
        // Initialize the database
        mapButtonActionRepository.saveAndFlush(mapButtonAction);

        // Get all the mapButtonActionList
        restMapButtonActionMockMvc.perform(get("/api/map-button-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapButtonAction.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceAliasState").value(hasItem(DEFAULT_SOURCE_ALIAS_STATE)))
            .andExpect(jsonPath("$.[*].targetState").value(hasItem(DEFAULT_TARGET_STATE.toString())));
    }
    
    @Test
    @Transactional
    public void getMapButtonAction() throws Exception {
        // Initialize the database
        mapButtonActionRepository.saveAndFlush(mapButtonAction);

        // Get the mapButtonAction
        restMapButtonActionMockMvc.perform(get("/api/map-button-actions/{id}", mapButtonAction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapButtonAction.getId().intValue()))
            .andExpect(jsonPath("$.sourceAliasState").value(DEFAULT_SOURCE_ALIAS_STATE))
            .andExpect(jsonPath("$.targetState").value(DEFAULT_TARGET_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMapButtonAction() throws Exception {
        // Get the mapButtonAction
        restMapButtonActionMockMvc.perform(get("/api/map-button-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapButtonAction() throws Exception {
        // Initialize the database
        mapButtonActionRepository.saveAndFlush(mapButtonAction);

        int databaseSizeBeforeUpdate = mapButtonActionRepository.findAll().size();

        // Update the mapButtonAction
        MapButtonAction updatedMapButtonAction = mapButtonActionRepository.findById(mapButtonAction.getId()).get();
        // Disconnect from session so that the updates on updatedMapButtonAction are not directly saved in db
        em.detach(updatedMapButtonAction);
        updatedMapButtonAction
            .sourceAliasState(UPDATED_SOURCE_ALIAS_STATE)
            .targetState(UPDATED_TARGET_STATE);
        MapButtonActionDTO mapButtonActionDTO = mapButtonActionMapper.toDto(updatedMapButtonAction);

        restMapButtonActionMockMvc.perform(put("/api/map-button-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapButtonActionDTO)))
            .andExpect(status().isOk());

        // Validate the MapButtonAction in the database
        List<MapButtonAction> mapButtonActionList = mapButtonActionRepository.findAll();
        assertThat(mapButtonActionList).hasSize(databaseSizeBeforeUpdate);
        MapButtonAction testMapButtonAction = mapButtonActionList.get(mapButtonActionList.size() - 1);
        assertThat(testMapButtonAction.getSourceAliasState()).isEqualTo(UPDATED_SOURCE_ALIAS_STATE);
        assertThat(testMapButtonAction.getTargetState()).isEqualTo(UPDATED_TARGET_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMapButtonAction() throws Exception {
        int databaseSizeBeforeUpdate = mapButtonActionRepository.findAll().size();

        // Create the MapButtonAction
        MapButtonActionDTO mapButtonActionDTO = mapButtonActionMapper.toDto(mapButtonAction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMapButtonActionMockMvc.perform(put("/api/map-button-actions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapButtonActionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapButtonAction in the database
        List<MapButtonAction> mapButtonActionList = mapButtonActionRepository.findAll();
        assertThat(mapButtonActionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapButtonAction() throws Exception {
        // Initialize the database
        mapButtonActionRepository.saveAndFlush(mapButtonAction);

        int databaseSizeBeforeDelete = mapButtonActionRepository.findAll().size();

        // Delete the mapButtonAction
        restMapButtonActionMockMvc.perform(delete("/api/map-button-actions/{id}", mapButtonAction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MapButtonAction> mapButtonActionList = mapButtonActionRepository.findAll();
        assertThat(mapButtonActionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
