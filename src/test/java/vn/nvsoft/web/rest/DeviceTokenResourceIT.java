package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.DeviceToken;
import vn.nvsoft.repository.DeviceTokenRepository;
import vn.nvsoft.service.DeviceTokenService;
import vn.nvsoft.service.dto.DeviceTokenDTO;
import vn.nvsoft.service.mapper.DeviceTokenMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DeviceTokenResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeviceTokenResourceIT {

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE = "BBBBBBBBBB";

    @Autowired
    private DeviceTokenRepository deviceTokenRepository;

    @Autowired
    private DeviceTokenMapper deviceTokenMapper;

    @Autowired
    private DeviceTokenService deviceTokenService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeviceTokenMockMvc;

    private DeviceToken deviceToken;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceToken createEntity(EntityManager em) {
        DeviceToken deviceToken = new DeviceToken()
            .user(DEFAULT_USER)
            .device(DEFAULT_DEVICE);
        return deviceToken;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviceToken createUpdatedEntity(EntityManager em) {
        DeviceToken deviceToken = new DeviceToken()
            .user(UPDATED_USER)
            .device(UPDATED_DEVICE);
        return deviceToken;
    }

    @BeforeEach
    public void initTest() {
        deviceToken = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeviceToken() throws Exception {
        int databaseSizeBeforeCreate = deviceTokenRepository.findAll().size();
        // Create the DeviceToken
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(deviceToken);
        restDeviceTokenMockMvc.perform(post("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isCreated());

        // Validate the DeviceToken in the database
        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeCreate + 1);
        DeviceToken testDeviceToken = deviceTokenList.get(deviceTokenList.size() - 1);
        assertThat(testDeviceToken.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testDeviceToken.getDevice()).isEqualTo(DEFAULT_DEVICE);
    }

    @Test
    @Transactional
    public void createDeviceTokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deviceTokenRepository.findAll().size();

        // Create the DeviceToken with an existing ID
        deviceToken.setId(1L);
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(deviceToken);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviceTokenMockMvc.perform(post("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceToken in the database
        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceTokenRepository.findAll().size();
        // set the field null
        deviceToken.setUser(null);

        // Create the DeviceToken, which fails.
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(deviceToken);


        restDeviceTokenMockMvc.perform(post("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeviceIsRequired() throws Exception {
        int databaseSizeBeforeTest = deviceTokenRepository.findAll().size();
        // set the field null
        deviceToken.setDevice(null);

        // Create the DeviceToken, which fails.
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(deviceToken);


        restDeviceTokenMockMvc.perform(post("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isBadRequest());

        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeviceTokens() throws Exception {
        // Initialize the database
        deviceTokenRepository.saveAndFlush(deviceToken);

        // Get all the deviceTokenList
        restDeviceTokenMockMvc.perform(get("/api/device-tokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviceToken.getId().intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].device").value(hasItem(DEFAULT_DEVICE)));
    }
    
    @Test
    @Transactional
    public void getDeviceToken() throws Exception {
        // Initialize the database
        deviceTokenRepository.saveAndFlush(deviceToken);

        // Get the deviceToken
        restDeviceTokenMockMvc.perform(get("/api/device-tokens/{id}", deviceToken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deviceToken.getId().intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.device").value(DEFAULT_DEVICE));
    }
    @Test
    @Transactional
    public void getNonExistingDeviceToken() throws Exception {
        // Get the deviceToken
        restDeviceTokenMockMvc.perform(get("/api/device-tokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeviceToken() throws Exception {
        // Initialize the database
        deviceTokenRepository.saveAndFlush(deviceToken);

        int databaseSizeBeforeUpdate = deviceTokenRepository.findAll().size();

        // Update the deviceToken
        DeviceToken updatedDeviceToken = deviceTokenRepository.findById(deviceToken.getId()).get();
        // Disconnect from session so that the updates on updatedDeviceToken are not directly saved in db
        em.detach(updatedDeviceToken);
        updatedDeviceToken
            .user(UPDATED_USER)
            .device(UPDATED_DEVICE);
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(updatedDeviceToken);

        restDeviceTokenMockMvc.perform(put("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isOk());

        // Validate the DeviceToken in the database
        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeUpdate);
        DeviceToken testDeviceToken = deviceTokenList.get(deviceTokenList.size() - 1);
        assertThat(testDeviceToken.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testDeviceToken.getDevice()).isEqualTo(UPDATED_DEVICE);
    }

    @Test
    @Transactional
    public void updateNonExistingDeviceToken() throws Exception {
        int databaseSizeBeforeUpdate = deviceTokenRepository.findAll().size();

        // Create the DeviceToken
        DeviceTokenDTO deviceTokenDTO = deviceTokenMapper.toDto(deviceToken);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviceTokenMockMvc.perform(put("/api/device-tokens")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(deviceTokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeviceToken in the database
        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeviceToken() throws Exception {
        // Initialize the database
        deviceTokenRepository.saveAndFlush(deviceToken);

        int databaseSizeBeforeDelete = deviceTokenRepository.findAll().size();

        // Delete the deviceToken
        restDeviceTokenMockMvc.perform(delete("/api/device-tokens/{id}", deviceToken.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeviceToken> deviceTokenList = deviceTokenRepository.findAll();
        assertThat(deviceTokenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
