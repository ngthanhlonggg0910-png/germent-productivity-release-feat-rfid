package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.SoLuongHangKiem;
import vn.nvsoft.repository.SoLuongHangKiemRepository;
import vn.nvsoft.service.SoLuongHangKiemService;
import vn.nvsoft.service.dto.SoLuongHangKiemDTO;
import vn.nvsoft.service.mapper.SoLuongHangKiemMapper;
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
 * Integration tests for the {@link SoLuongHangKiemResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class SoLuongHangKiemResourceIT {

    private static final Integer DEFAULT_SO_LUONG = 1;
    private static final Integer UPDATED_SO_LUONG = 2;

    @Autowired
    private SoLuongHangKiemRepository soLuongHangKiemRepository;

    @Autowired
    private SoLuongHangKiemMapper soLuongHangKiemMapper;

    @Autowired
    private SoLuongHangKiemService soLuongHangKiemService;

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

    private MockMvc restSoLuongHangKiemMockMvc;

    private SoLuongHangKiem soLuongHangKiem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SoLuongHangKiemResource soLuongHangKiemResource = new SoLuongHangKiemResource(soLuongHangKiemService);
        this.restSoLuongHangKiemMockMvc = MockMvcBuilders.standaloneSetup(soLuongHangKiemResource)
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
    public static SoLuongHangKiem createEntity(EntityManager em) {
        SoLuongHangKiem soLuongHangKiem = new SoLuongHangKiem()
            .soLuong(DEFAULT_SO_LUONG);
        return soLuongHangKiem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoLuongHangKiem createUpdatedEntity(EntityManager em) {
        SoLuongHangKiem soLuongHangKiem = new SoLuongHangKiem()
            .soLuong(UPDATED_SO_LUONG);
        return soLuongHangKiem;
    }

    @BeforeEach
    public void initTest() {
        soLuongHangKiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createSoLuongHangKiem() throws Exception {
        int databaseSizeBeforeCreate = soLuongHangKiemRepository.findAll().size();

        // Create the SoLuongHangKiem
        SoLuongHangKiemDTO soLuongHangKiemDTO = soLuongHangKiemMapper.toDto(soLuongHangKiem);
        restSoLuongHangKiemMockMvc.perform(post("/api/so-luong-hang-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soLuongHangKiemDTO)))
            .andExpect(status().isCreated());

        // Validate the SoLuongHangKiem in the database
        List<SoLuongHangKiem> soLuongHangKiemList = soLuongHangKiemRepository.findAll();
        assertThat(soLuongHangKiemList).hasSize(databaseSizeBeforeCreate + 1);
        SoLuongHangKiem testSoLuongHangKiem = soLuongHangKiemList.get(soLuongHangKiemList.size() - 1);
        assertThat(testSoLuongHangKiem.getSoLuong()).isEqualTo(DEFAULT_SO_LUONG);
    }

    @Test
    @Transactional
    public void createSoLuongHangKiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = soLuongHangKiemRepository.findAll().size();

        // Create the SoLuongHangKiem with an existing ID
        soLuongHangKiem.setId(1L);
        SoLuongHangKiemDTO soLuongHangKiemDTO = soLuongHangKiemMapper.toDto(soLuongHangKiem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoLuongHangKiemMockMvc.perform(post("/api/so-luong-hang-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soLuongHangKiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoLuongHangKiem in the database
        List<SoLuongHangKiem> soLuongHangKiemList = soLuongHangKiemRepository.findAll();
        assertThat(soLuongHangKiemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSoLuongHangKiems() throws Exception {
        // Initialize the database
        soLuongHangKiemRepository.saveAndFlush(soLuongHangKiem);

        // Get all the soLuongHangKiemList
        restSoLuongHangKiemMockMvc.perform(get("/api/so-luong-hang-kiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soLuongHangKiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].soLuong").value(hasItem(DEFAULT_SO_LUONG)));
    }
    
    @Test
    @Transactional
    public void getSoLuongHangKiem() throws Exception {
        // Initialize the database
        soLuongHangKiemRepository.saveAndFlush(soLuongHangKiem);

        // Get the soLuongHangKiem
        restSoLuongHangKiemMockMvc.perform(get("/api/so-luong-hang-kiems/{id}", soLuongHangKiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(soLuongHangKiem.getId().intValue()))
            .andExpect(jsonPath("$.soLuong").value(DEFAULT_SO_LUONG));
    }

    @Test
    @Transactional
    public void getNonExistingSoLuongHangKiem() throws Exception {
        // Get the soLuongHangKiem
        restSoLuongHangKiemMockMvc.perform(get("/api/so-luong-hang-kiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSoLuongHangKiem() throws Exception {
        // Initialize the database
        soLuongHangKiemRepository.saveAndFlush(soLuongHangKiem);

        int databaseSizeBeforeUpdate = soLuongHangKiemRepository.findAll().size();

        // Update the soLuongHangKiem
        SoLuongHangKiem updatedSoLuongHangKiem = soLuongHangKiemRepository.findById(soLuongHangKiem.getId()).get();
        // Disconnect from session so that the updates on updatedSoLuongHangKiem are not directly saved in db
        em.detach(updatedSoLuongHangKiem);
        updatedSoLuongHangKiem
            .soLuong(UPDATED_SO_LUONG);
        SoLuongHangKiemDTO soLuongHangKiemDTO = soLuongHangKiemMapper.toDto(updatedSoLuongHangKiem);

        restSoLuongHangKiemMockMvc.perform(put("/api/so-luong-hang-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soLuongHangKiemDTO)))
            .andExpect(status().isOk());

        // Validate the SoLuongHangKiem in the database
        List<SoLuongHangKiem> soLuongHangKiemList = soLuongHangKiemRepository.findAll();
        assertThat(soLuongHangKiemList).hasSize(databaseSizeBeforeUpdate);
        SoLuongHangKiem testSoLuongHangKiem = soLuongHangKiemList.get(soLuongHangKiemList.size() - 1);
        assertThat(testSoLuongHangKiem.getSoLuong()).isEqualTo(UPDATED_SO_LUONG);
    }

    @Test
    @Transactional
    public void updateNonExistingSoLuongHangKiem() throws Exception {
        int databaseSizeBeforeUpdate = soLuongHangKiemRepository.findAll().size();

        // Create the SoLuongHangKiem
        SoLuongHangKiemDTO soLuongHangKiemDTO = soLuongHangKiemMapper.toDto(soLuongHangKiem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoLuongHangKiemMockMvc.perform(put("/api/so-luong-hang-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soLuongHangKiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoLuongHangKiem in the database
        List<SoLuongHangKiem> soLuongHangKiemList = soLuongHangKiemRepository.findAll();
        assertThat(soLuongHangKiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSoLuongHangKiem() throws Exception {
        // Initialize the database
        soLuongHangKiemRepository.saveAndFlush(soLuongHangKiem);

        int databaseSizeBeforeDelete = soLuongHangKiemRepository.findAll().size();

        // Delete the soLuongHangKiem
        restSoLuongHangKiemMockMvc.perform(delete("/api/so-luong-hang-kiems/{id}", soLuongHangKiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SoLuongHangKiem> soLuongHangKiemList = soLuongHangKiemRepository.findAll();
        assertThat(soLuongHangKiemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
