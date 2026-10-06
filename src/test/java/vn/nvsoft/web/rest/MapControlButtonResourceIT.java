package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.MapControlButton;
import vn.nvsoft.repository.MapControlButtonRepository;
import vn.nvsoft.service.MapControlButtonService;
import vn.nvsoft.service.dto.MapControlButtonDTO;
import vn.nvsoft.service.mapper.MapControlButtonMapper;
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
 * Integration tests for the {@link MapControlButtonResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class MapControlButtonResourceIT {

    @Autowired
    private MapControlButtonRepository mapControlButtonRepository;

    @Autowired
    private MapControlButtonMapper mapControlButtonMapper;

    @Autowired
    private MapControlButtonService mapControlButtonService;

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

    private MockMvc restMapControlButtonMockMvc;

    private MapControlButton mapControlButton;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapControlButtonResource mapControlButtonResource = new MapControlButtonResource(mapControlButtonService);
        this.restMapControlButtonMockMvc = MockMvcBuilders.standaloneSetup(mapControlButtonResource)
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
    public static MapControlButton createEntity(EntityManager em) {
        MapControlButton mapControlButton = new MapControlButton();
        return mapControlButton;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MapControlButton createUpdatedEntity(EntityManager em) {
        MapControlButton mapControlButton = new MapControlButton();
        return mapControlButton;
    }

    @BeforeEach
    public void initTest() {
        mapControlButton = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapControlButton() throws Exception {
        int databaseSizeBeforeCreate = mapControlButtonRepository.findAll().size();

        // Create the MapControlButton
        MapControlButtonDTO mapControlButtonDTO = mapControlButtonMapper.toDto(mapControlButton);
        restMapControlButtonMockMvc.perform(post("/api/map-control-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapControlButtonDTO)))
            .andExpect(status().isCreated());

        // Validate the MapControlButton in the database
        List<MapControlButton> mapControlButtonList = mapControlButtonRepository.findAll();
        assertThat(mapControlButtonList).hasSize(databaseSizeBeforeCreate + 1);
        MapControlButton testMapControlButton = mapControlButtonList.get(mapControlButtonList.size() - 1);
    }

    @Test
    @Transactional
    public void createMapControlButtonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapControlButtonRepository.findAll().size();

        // Create the MapControlButton with an existing ID
        mapControlButton.setId(1L);
        MapControlButtonDTO mapControlButtonDTO = mapControlButtonMapper.toDto(mapControlButton);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapControlButtonMockMvc.perform(post("/api/map-control-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapControlButtonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapControlButton in the database
        List<MapControlButton> mapControlButtonList = mapControlButtonRepository.findAll();
        assertThat(mapControlButtonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMapControlButtons() throws Exception {
        // Initialize the database
        mapControlButtonRepository.saveAndFlush(mapControlButton);

        // Get all the mapControlButtonList
        restMapControlButtonMockMvc.perform(get("/api/map-control-buttons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapControlButton.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getMapControlButton() throws Exception {
        // Initialize the database
        mapControlButtonRepository.saveAndFlush(mapControlButton);

        // Get the mapControlButton
        restMapControlButtonMockMvc.perform(get("/api/map-control-buttons/{id}", mapControlButton.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapControlButton.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMapControlButton() throws Exception {
        // Get the mapControlButton
        restMapControlButtonMockMvc.perform(get("/api/map-control-buttons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapControlButton() throws Exception {
        // Initialize the database
        mapControlButtonRepository.saveAndFlush(mapControlButton);

        int databaseSizeBeforeUpdate = mapControlButtonRepository.findAll().size();

        // Update the mapControlButton
        MapControlButton updatedMapControlButton = mapControlButtonRepository.findById(mapControlButton.getId()).get();
        // Disconnect from session so that the updates on updatedMapControlButton are not directly saved in db
        em.detach(updatedMapControlButton);
        MapControlButtonDTO mapControlButtonDTO = mapControlButtonMapper.toDto(updatedMapControlButton);

        restMapControlButtonMockMvc.perform(put("/api/map-control-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapControlButtonDTO)))
            .andExpect(status().isOk());

        // Validate the MapControlButton in the database
        List<MapControlButton> mapControlButtonList = mapControlButtonRepository.findAll();
        assertThat(mapControlButtonList).hasSize(databaseSizeBeforeUpdate);
        MapControlButton testMapControlButton = mapControlButtonList.get(mapControlButtonList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMapControlButton() throws Exception {
        int databaseSizeBeforeUpdate = mapControlButtonRepository.findAll().size();

        // Create the MapControlButton
        MapControlButtonDTO mapControlButtonDTO = mapControlButtonMapper.toDto(mapControlButton);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMapControlButtonMockMvc.perform(put("/api/map-control-buttons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapControlButtonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MapControlButton in the database
        List<MapControlButton> mapControlButtonList = mapControlButtonRepository.findAll();
        assertThat(mapControlButtonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMapControlButton() throws Exception {
        // Initialize the database
        mapControlButtonRepository.saveAndFlush(mapControlButton);

        int databaseSizeBeforeDelete = mapControlButtonRepository.findAll().size();

        // Delete the mapControlButton
        restMapControlButtonMockMvc.perform(delete("/api/map-control-buttons/{id}", mapControlButton.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MapControlButton> mapControlButtonList = mapControlButtonRepository.findAll();
        assertThat(mapControlButtonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
