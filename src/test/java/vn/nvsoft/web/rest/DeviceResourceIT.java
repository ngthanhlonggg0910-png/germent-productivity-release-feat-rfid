package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.Device;
import vn.nvsoft.repository.DeviceRepository;
import vn.nvsoft.service.DeviceService;
import vn.nvsoft.service.dto.DeviceDTO;
import vn.nvsoft.service.mapper.DeviceMapper;
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

import vn.nvsoft.domain.enumeration.LWT;
/**
 * Integration tests for the {@link DeviceResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class DeviceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEV_IDS = "AAAAAAAAAA";
    private static final String UPDATED_DEV_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_STATE_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_LWT_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_LWT_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_SW = "AAAAAAAAAA";
    private static final String UPDATED_SW = "BBBBBBBBBB";

    private static final String DEFAULT_MODULE = "AAAAAAAAAA";
    private static final String UPDATED_MODULE = "BBBBBBBBBB";

    private static final String DEFAULT_RESTART_REASON = "AAAAAAAAAA";
    private static final String UPDATED_RESTART_REASON = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPTIME = "AAAAAAAAAA";
    private static final String UPDATED_UPTIME = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final LWT DEFAULT_LWT = LWT.ON_LINE;
    private static final LWT UPDATED_LWT = LWT.OFF_LINE;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceService deviceService;

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

    private MockMvc restDeviceMockMvc;

    private Device device;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeviceResource deviceResource = new DeviceResource(deviceService);
        this.restDeviceMockMvc = MockMvcBuilders.standaloneSetup(deviceResource)
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
    public static Device createEntity(EntityManager em) {
        Device device = new Device()
            .name(DEFAULT_NAME)
            .devIds(DEFAULT_DEV_IDS)
            .stateTopic(DEFAULT_STATE_TOPIC)
            .lwtTopic(DEFAULT_LWT_TOPIC)
            .sw(DEFAULT_SW)
            .module(DEFAULT_MODULE)
            .restartReason(DEFAULT_RESTART_REASON)
            .time(DEFAULT_TIME)
            .uptime(DEFAULT_UPTIME)
            .hostName(DEFAULT_HOST_NAME)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .lwt(DEFAULT_LWT);
        return device;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Device createUpdatedEntity(EntityManager em) {
        Device device = new Device()
            .name(UPDATED_NAME)
            .devIds(UPDATED_DEV_IDS)
            .stateTopic(UPDATED_STATE_TOPIC)
            .lwtTopic(UPDATED_LWT_TOPIC)
            .sw(UPDATED_SW)
            .module(UPDATED_MODULE)
            .restartReason(UPDATED_RESTART_REASON)
            .time(UPDATED_TIME)
            .uptime(UPDATED_UPTIME)
            .hostName(UPDATED_HOST_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .lwt(UPDATED_LWT);
        return device;
    }

    @BeforeEach
    public void initTest() {
        device = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevice() throws Exception {
        int databaseSizeBeforeCreate = deviceRepository.findAll().size();

        // Create the Device
        DeviceDTO deviceDTO = deviceMapper.toDto(device);
        restDeviceMockMvc.perform(post("/api/devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceDTO)))
            .andExpect(status().isCreated());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeCreate + 1);
        Device testDevice = deviceList.get(deviceList.size() - 1);
        assertThat(testDevice.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDevice.getDevIds()).isEqualTo(DEFAULT_DEV_IDS);
        assertThat(testDevice.getStateTopic()).isEqualTo(DEFAULT_STATE_TOPIC);
        assertThat(testDevice.getLwtTopic()).isEqualTo(DEFAULT_LWT_TOPIC);
        assertThat(testDevice.getSw()).isEqualTo(DEFAULT_SW);
        assertThat(testDevice.getModule()).isEqualTo(DEFAULT_MODULE);
        assertThat(testDevice.getRestartReason()).isEqualTo(DEFAULT_RESTART_REASON);
        assertThat(testDevice.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testDevice.getUptime()).isEqualTo(DEFAULT_UPTIME);
        assertThat(testDevice.getHostName()).isEqualTo(DEFAULT_HOST_NAME);
        assertThat(testDevice.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testDevice.getLwt()).isEqualTo(DEFAULT_LWT);
    }

    @Test
    @Transactional
    public void createDeviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceRepository.findAll().size();

        // Create the Device with an existing ID
        device.setId(1L);
        DeviceDTO deviceDTO = deviceMapper.toDto(device);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceMockMvc.perform(post("/api/devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDevices() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        // Get all the deviceList
        restDeviceMockMvc.perform(get("/api/devices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(device.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].devIds").value(hasItem(DEFAULT_DEV_IDS)))
            .andExpect(jsonPath("$.[*].stateTopic").value(hasItem(DEFAULT_STATE_TOPIC)))
            .andExpect(jsonPath("$.[*].lwtTopic").value(hasItem(DEFAULT_LWT_TOPIC)))
            .andExpect(jsonPath("$.[*].sw").value(hasItem(DEFAULT_SW)))
            .andExpect(jsonPath("$.[*].module").value(hasItem(DEFAULT_MODULE)))
            .andExpect(jsonPath("$.[*].restartReason").value(hasItem(DEFAULT_RESTART_REASON)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].uptime").value(hasItem(DEFAULT_UPTIME)))
            .andExpect(jsonPath("$.[*].hostName").value(hasItem(DEFAULT_HOST_NAME)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].lwt").value(hasItem(DEFAULT_LWT.toString())));
    }
    
    @Test
    @Transactional
    public void getDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        // Get the device
        restDeviceMockMvc.perform(get("/api/devices/{id}", device.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(device.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.devIds").value(DEFAULT_DEV_IDS))
            .andExpect(jsonPath("$.stateTopic").value(DEFAULT_STATE_TOPIC))
            .andExpect(jsonPath("$.lwtTopic").value(DEFAULT_LWT_TOPIC))
            .andExpect(jsonPath("$.sw").value(DEFAULT_SW))
            .andExpect(jsonPath("$.module").value(DEFAULT_MODULE))
            .andExpect(jsonPath("$.restartReason").value(DEFAULT_RESTART_REASON))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.uptime").value(DEFAULT_UPTIME))
            .andExpect(jsonPath("$.hostName").value(DEFAULT_HOST_NAME))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.lwt").value(DEFAULT_LWT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDevice() throws Exception {
        // Get the device
        restDeviceMockMvc.perform(get("/api/devices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        int databaseSizeBeforeUpdate = deviceRepository.findAll().size();

        // Update the device
        Device updatedDevice = deviceRepository.findById(device.getId()).get();
        // Disconnect from session so that the updates on updatedDevice are not directly saved in db
        em.detach(updatedDevice);
        updatedDevice
            .name(UPDATED_NAME)
            .devIds(UPDATED_DEV_IDS)
            .stateTopic(UPDATED_STATE_TOPIC)
            .lwtTopic(UPDATED_LWT_TOPIC)
            .sw(UPDATED_SW)
            .module(UPDATED_MODULE)
            .restartReason(UPDATED_RESTART_REASON)
            .time(UPDATED_TIME)
            .uptime(UPDATED_UPTIME)
            .hostName(UPDATED_HOST_NAME)
            .ipAddress(UPDATED_IP_ADDRESS)
            .lwt(UPDATED_LWT);
        DeviceDTO deviceDTO = deviceMapper.toDto(updatedDevice);

        restDeviceMockMvc.perform(put("/api/devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceDTO)))
            .andExpect(status().isOk());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeUpdate);
        Device testDevice = deviceList.get(deviceList.size() - 1);
        assertThat(testDevice.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDevice.getDevIds()).isEqualTo(UPDATED_DEV_IDS);
        assertThat(testDevice.getStateTopic()).isEqualTo(UPDATED_STATE_TOPIC);
        assertThat(testDevice.getLwtTopic()).isEqualTo(UPDATED_LWT_TOPIC);
        assertThat(testDevice.getSw()).isEqualTo(UPDATED_SW);
        assertThat(testDevice.getModule()).isEqualTo(UPDATED_MODULE);
        assertThat(testDevice.getRestartReason()).isEqualTo(UPDATED_RESTART_REASON);
        assertThat(testDevice.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testDevice.getUptime()).isEqualTo(UPDATED_UPTIME);
        assertThat(testDevice.getHostName()).isEqualTo(UPDATED_HOST_NAME);
        assertThat(testDevice.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testDevice.getLwt()).isEqualTo(UPDATED_LWT);
    }

    @Test
    @Transactional
    public void updateNonExistingDevice() throws Exception {
        int databaseSizeBeforeUpdate = deviceRepository.findAll().size();

        // Create the Device
        DeviceDTO deviceDTO = deviceMapper.toDto(device);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceMockMvc.perform(put("/api/devices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Device in the database
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDevice() throws Exception {
        // Initialize the database
        deviceRepository.saveAndFlush(device);

        int databaseSizeBeforeDelete = deviceRepository.findAll().size();

        // Delete the device
        restDeviceMockMvc.perform(delete("/api/devices/{id}", device.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
