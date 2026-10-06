package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.Tracking;
import vn.nvsoft.repository.TrackingRepository;
import vn.nvsoft.service.TrackingService;
import vn.nvsoft.service.dto.TrackingDTO;
import vn.nvsoft.service.mapper.TrackingMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TrackingResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class TrackingResourceIT {

    private static final Long DEFAULT_NV_BUTTON_ID = 1L;
    private static final Long UPDATED_NV_BUTTON_ID = 2L;

    private static final Instant DEFAULT_STATE_1_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_1_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STATE_2_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_2_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STATE_3_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_3_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STATE_4_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_4_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STATE_5_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_5_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_STATE_6_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATE_6_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private TrackingMapper trackingMapper;

    @Autowired
    private TrackingService trackingService;

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

    private MockMvc restTrackingMockMvc;

    private Tracking tracking;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrackingResource trackingResource = new TrackingResource(trackingService);
        this.restTrackingMockMvc = MockMvcBuilders.standaloneSetup(trackingResource)
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
    public static Tracking createEntity(EntityManager em) {
        Tracking tracking = new Tracking()
            .nvButtonId(DEFAULT_NV_BUTTON_ID)
            .state1Time(DEFAULT_STATE_1_TIME)
            .state2Time(DEFAULT_STATE_2_TIME)
            .state3Time(DEFAULT_STATE_3_TIME)
            .state4Time(DEFAULT_STATE_4_TIME)
            .state5Time(DEFAULT_STATE_5_TIME)
            .state6Time(DEFAULT_STATE_6_TIME);
        return tracking;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tracking createUpdatedEntity(EntityManager em) {
        Tracking tracking = new Tracking()
            .nvButtonId(UPDATED_NV_BUTTON_ID)
            .state1Time(UPDATED_STATE_1_TIME)
            .state2Time(UPDATED_STATE_2_TIME)
            .state3Time(UPDATED_STATE_3_TIME)
            .state4Time(UPDATED_STATE_4_TIME)
            .state5Time(UPDATED_STATE_5_TIME)
            .state6Time(UPDATED_STATE_6_TIME);
        return tracking;
    }

    @BeforeEach
    public void initTest() {
        tracking = createEntity(em);
    }

    @Test
    @Transactional
    public void createTracking() throws Exception {
        int databaseSizeBeforeCreate = trackingRepository.findAll().size();

        // Create the Tracking
        TrackingDTO trackingDTO = trackingMapper.toDto(tracking);
        restTrackingMockMvc.perform(post("/api/trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trackingDTO)))
            .andExpect(status().isCreated());

        // Validate the Tracking in the database
        List<Tracking> trackingList = trackingRepository.findAll();
        assertThat(trackingList).hasSize(databaseSizeBeforeCreate + 1);
        Tracking testTracking = trackingList.get(trackingList.size() - 1);
        assertThat(testTracking.getNvButtonId()).isEqualTo(DEFAULT_NV_BUTTON_ID);
        assertThat(testTracking.getState1Time()).isEqualTo(DEFAULT_STATE_1_TIME);
        assertThat(testTracking.getState2Time()).isEqualTo(DEFAULT_STATE_2_TIME);
        assertThat(testTracking.getState3Time()).isEqualTo(DEFAULT_STATE_3_TIME);
        assertThat(testTracking.getState4Time()).isEqualTo(DEFAULT_STATE_4_TIME);
        assertThat(testTracking.getState5Time()).isEqualTo(DEFAULT_STATE_5_TIME);
        assertThat(testTracking.getState6Time()).isEqualTo(DEFAULT_STATE_6_TIME);
    }

    @Test
    @Transactional
    public void createTrackingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trackingRepository.findAll().size();

        // Create the Tracking with an existing ID
        tracking.setId(1L);
        TrackingDTO trackingDTO = trackingMapper.toDto(tracking);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrackingMockMvc.perform(post("/api/trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trackingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tracking in the database
        List<Tracking> trackingList = trackingRepository.findAll();
        assertThat(trackingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTrackings() throws Exception {
        // Initialize the database
        trackingRepository.saveAndFlush(tracking);

        // Get all the trackingList
        restTrackingMockMvc.perform(get("/api/trackings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tracking.getId().intValue())))
            .andExpect(jsonPath("$.[*].nvButtonId").value(hasItem(DEFAULT_NV_BUTTON_ID.intValue())))
            .andExpect(jsonPath("$.[*].state1Time").value(hasItem(DEFAULT_STATE_1_TIME.toString())))
            .andExpect(jsonPath("$.[*].state2Time").value(hasItem(DEFAULT_STATE_2_TIME.toString())))
            .andExpect(jsonPath("$.[*].state3Time").value(hasItem(DEFAULT_STATE_3_TIME.toString())))
            .andExpect(jsonPath("$.[*].state4Time").value(hasItem(DEFAULT_STATE_4_TIME.toString())))
            .andExpect(jsonPath("$.[*].state5Time").value(hasItem(DEFAULT_STATE_5_TIME.toString())))
            .andExpect(jsonPath("$.[*].state6Time").value(hasItem(DEFAULT_STATE_6_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getTracking() throws Exception {
        // Initialize the database
        trackingRepository.saveAndFlush(tracking);

        // Get the tracking
        restTrackingMockMvc.perform(get("/api/trackings/{id}", tracking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tracking.getId().intValue()))
            .andExpect(jsonPath("$.nvButtonId").value(DEFAULT_NV_BUTTON_ID.intValue()))
            .andExpect(jsonPath("$.state1Time").value(DEFAULT_STATE_1_TIME.toString()))
            .andExpect(jsonPath("$.state2Time").value(DEFAULT_STATE_2_TIME.toString()))
            .andExpect(jsonPath("$.state3Time").value(DEFAULT_STATE_3_TIME.toString()))
            .andExpect(jsonPath("$.state4Time").value(DEFAULT_STATE_4_TIME.toString()))
            .andExpect(jsonPath("$.state5Time").value(DEFAULT_STATE_5_TIME.toString()))
            .andExpect(jsonPath("$.state6Time").value(DEFAULT_STATE_6_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTracking() throws Exception {
        // Get the tracking
        restTrackingMockMvc.perform(get("/api/trackings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTracking() throws Exception {
        // Initialize the database
        trackingRepository.saveAndFlush(tracking);

        int databaseSizeBeforeUpdate = trackingRepository.findAll().size();

        // Update the tracking
        Tracking updatedTracking = trackingRepository.findById(tracking.getId()).get();
        // Disconnect from session so that the updates on updatedTracking are not directly saved in db
        em.detach(updatedTracking);
        updatedTracking
            .nvButtonId(UPDATED_NV_BUTTON_ID)
            .state1Time(UPDATED_STATE_1_TIME)
            .state2Time(UPDATED_STATE_2_TIME)
            .state3Time(UPDATED_STATE_3_TIME)
            .state4Time(UPDATED_STATE_4_TIME)
            .state5Time(UPDATED_STATE_5_TIME)
            .state6Time(UPDATED_STATE_6_TIME);
        TrackingDTO trackingDTO = trackingMapper.toDto(updatedTracking);

        restTrackingMockMvc.perform(put("/api/trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trackingDTO)))
            .andExpect(status().isOk());

        // Validate the Tracking in the database
        List<Tracking> trackingList = trackingRepository.findAll();
        assertThat(trackingList).hasSize(databaseSizeBeforeUpdate);
        Tracking testTracking = trackingList.get(trackingList.size() - 1);
        assertThat(testTracking.getNvButtonId()).isEqualTo(UPDATED_NV_BUTTON_ID);
        assertThat(testTracking.getState1Time()).isEqualTo(UPDATED_STATE_1_TIME);
        assertThat(testTracking.getState2Time()).isEqualTo(UPDATED_STATE_2_TIME);
        assertThat(testTracking.getState3Time()).isEqualTo(UPDATED_STATE_3_TIME);
        assertThat(testTracking.getState4Time()).isEqualTo(UPDATED_STATE_4_TIME);
        assertThat(testTracking.getState5Time()).isEqualTo(UPDATED_STATE_5_TIME);
        assertThat(testTracking.getState6Time()).isEqualTo(UPDATED_STATE_6_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingTracking() throws Exception {
        int databaseSizeBeforeUpdate = trackingRepository.findAll().size();

        // Create the Tracking
        TrackingDTO trackingDTO = trackingMapper.toDto(tracking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrackingMockMvc.perform(put("/api/trackings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trackingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tracking in the database
        List<Tracking> trackingList = trackingRepository.findAll();
        assertThat(trackingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTracking() throws Exception {
        // Initialize the database
        trackingRepository.saveAndFlush(tracking);

        int databaseSizeBeforeDelete = trackingRepository.findAll().size();

        // Delete the tracking
        restTrackingMockMvc.perform(delete("/api/trackings/{id}", tracking.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tracking> trackingList = trackingRepository.findAll();
        assertThat(trackingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
