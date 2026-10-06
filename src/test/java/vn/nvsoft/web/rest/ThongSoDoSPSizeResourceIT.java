package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ThongSoDoSPSize;
import vn.nvsoft.repository.ThongSoDoSPSizeRepository;
import vn.nvsoft.service.ThongSoDoSPSizeService;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;
import vn.nvsoft.service.mapper.ThongSoDoSPSizeMapper;
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
 * Integration tests for the {@link ThongSoDoSPSizeResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ThongSoDoSPSizeResourceIT {

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_CM_THONG_SO_CHUAN = "AAAAAAAAAA";
    private static final String UPDATED_CM_THONG_SO_CHUAN = "BBBBBBBBBB";

    private static final String DEFAULT_INCH_THONG_SO_CHUAN = "AAAAAAAAAA";
    private static final String UPDATED_INCH_THONG_SO_CHUAN = "BBBBBBBBBB";

    private static final Float DEFAULT_CM_THONG_SO_CHUAN_VALUE = 1F;
    private static final Float UPDATED_CM_THONG_SO_CHUAN_VALUE = 2F;

    private static final Float DEFAULT_INCH_THONG_SO_CHUAN_VALUE = 1F;
    private static final Float UPDATED_INCH_THONG_SO_CHUAN_VALUE = 2F;

    @Autowired
    private ThongSoDoSPSizeRepository thongSoDoSPSizeRepository;

    @Autowired
    private ThongSoDoSPSizeMapper thongSoDoSPSizeMapper;

    @Autowired
    private ThongSoDoSPSizeService thongSoDoSPSizeService;

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

    private MockMvc restThongSoDoSPSizeMockMvc;

    private ThongSoDoSPSize thongSoDoSPSize;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongSoDoSPSizeResource thongSoDoSPSizeResource = new ThongSoDoSPSizeResource(thongSoDoSPSizeService);
        this.restThongSoDoSPSizeMockMvc = MockMvcBuilders.standaloneSetup(thongSoDoSPSizeResource)
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
    public static ThongSoDoSPSize createEntity(EntityManager em) {
        ThongSoDoSPSize thongSoDoSPSize = new ThongSoDoSPSize()
            .size(DEFAULT_SIZE)
            .cmThongSoChuan(DEFAULT_CM_THONG_SO_CHUAN)
            .inchThongSoChuan(DEFAULT_INCH_THONG_SO_CHUAN)
            .cmThongSoChuanValue(DEFAULT_CM_THONG_SO_CHUAN_VALUE)
            .inchThongSoChuanValue(DEFAULT_INCH_THONG_SO_CHUAN_VALUE);
        return thongSoDoSPSize;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongSoDoSPSize createUpdatedEntity(EntityManager em) {
        ThongSoDoSPSize thongSoDoSPSize = new ThongSoDoSPSize()
            .size(UPDATED_SIZE)
            .cmThongSoChuan(UPDATED_CM_THONG_SO_CHUAN)
            .inchThongSoChuan(UPDATED_INCH_THONG_SO_CHUAN)
            .cmThongSoChuanValue(UPDATED_CM_THONG_SO_CHUAN_VALUE)
            .inchThongSoChuanValue(UPDATED_INCH_THONG_SO_CHUAN_VALUE);
        return thongSoDoSPSize;
    }

    @BeforeEach
    public void initTest() {
        thongSoDoSPSize = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongSoDoSPSize() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoSPSizeRepository.findAll().size();

        // Create the ThongSoDoSPSize
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO = thongSoDoSPSizeMapper.toDto(thongSoDoSPSize);
        restThongSoDoSPSizeMockMvc.perform(post("/api/thong-so-do-sp-sizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPSizeDTO)))
            .andExpect(status().isCreated());

        // Validate the ThongSoDoSPSize in the database
        List<ThongSoDoSPSize> thongSoDoSPSizeList = thongSoDoSPSizeRepository.findAll();
        assertThat(thongSoDoSPSizeList).hasSize(databaseSizeBeforeCreate + 1);
        ThongSoDoSPSize testThongSoDoSPSize = thongSoDoSPSizeList.get(thongSoDoSPSizeList.size() - 1);
        assertThat(testThongSoDoSPSize.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testThongSoDoSPSize.getCmThongSoChuan()).isEqualTo(DEFAULT_CM_THONG_SO_CHUAN);
        assertThat(testThongSoDoSPSize.getInchThongSoChuan()).isEqualTo(DEFAULT_INCH_THONG_SO_CHUAN);
        assertThat(testThongSoDoSPSize.getCmThongSoChuanValue()).isEqualTo(DEFAULT_CM_THONG_SO_CHUAN_VALUE);
        assertThat(testThongSoDoSPSize.getInchThongSoChuanValue()).isEqualTo(DEFAULT_INCH_THONG_SO_CHUAN_VALUE);
    }

    @Test
    @Transactional
    public void createThongSoDoSPSizeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoSPSizeRepository.findAll().size();

        // Create the ThongSoDoSPSize with an existing ID
        thongSoDoSPSize.setId(1L);
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO = thongSoDoSPSizeMapper.toDto(thongSoDoSPSize);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongSoDoSPSizeMockMvc.perform(post("/api/thong-so-do-sp-sizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPSizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDoSPSize in the database
        List<ThongSoDoSPSize> thongSoDoSPSizeList = thongSoDoSPSizeRepository.findAll();
        assertThat(thongSoDoSPSizeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllThongSoDoSPSizes() throws Exception {
        // Initialize the database
        thongSoDoSPSizeRepository.saveAndFlush(thongSoDoSPSize);

        // Get all the thongSoDoSPSizeList
        restThongSoDoSPSizeMockMvc.perform(get("/api/thong-so-do-sp-sizes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongSoDoSPSize.getId().intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].cmThongSoChuan").value(hasItem(DEFAULT_CM_THONG_SO_CHUAN)))
            .andExpect(jsonPath("$.[*].inchThongSoChuan").value(hasItem(DEFAULT_INCH_THONG_SO_CHUAN)))
            .andExpect(jsonPath("$.[*].cmThongSoChuanValue").value(hasItem(DEFAULT_CM_THONG_SO_CHUAN_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].inchThongSoChuanValue").value(hasItem(DEFAULT_INCH_THONG_SO_CHUAN_VALUE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getThongSoDoSPSize() throws Exception {
        // Initialize the database
        thongSoDoSPSizeRepository.saveAndFlush(thongSoDoSPSize);

        // Get the thongSoDoSPSize
        restThongSoDoSPSizeMockMvc.perform(get("/api/thong-so-do-sp-sizes/{id}", thongSoDoSPSize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongSoDoSPSize.getId().intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.cmThongSoChuan").value(DEFAULT_CM_THONG_SO_CHUAN))
            .andExpect(jsonPath("$.inchThongSoChuan").value(DEFAULT_INCH_THONG_SO_CHUAN))
            .andExpect(jsonPath("$.cmThongSoChuanValue").value(DEFAULT_CM_THONG_SO_CHUAN_VALUE.doubleValue()))
            .andExpect(jsonPath("$.inchThongSoChuanValue").value(DEFAULT_INCH_THONG_SO_CHUAN_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingThongSoDoSPSize() throws Exception {
        // Get the thongSoDoSPSize
        restThongSoDoSPSizeMockMvc.perform(get("/api/thong-so-do-sp-sizes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongSoDoSPSize() throws Exception {
        // Initialize the database
        thongSoDoSPSizeRepository.saveAndFlush(thongSoDoSPSize);

        int databaseSizeBeforeUpdate = thongSoDoSPSizeRepository.findAll().size();

        // Update the thongSoDoSPSize
        ThongSoDoSPSize updatedThongSoDoSPSize = thongSoDoSPSizeRepository.findById(thongSoDoSPSize.getId()).get();
        // Disconnect from session so that the updates on updatedThongSoDoSPSize are not directly saved in db
        em.detach(updatedThongSoDoSPSize);
        updatedThongSoDoSPSize
            .size(UPDATED_SIZE)
            .cmThongSoChuan(UPDATED_CM_THONG_SO_CHUAN)
            .inchThongSoChuan(UPDATED_INCH_THONG_SO_CHUAN)
            .cmThongSoChuanValue(UPDATED_CM_THONG_SO_CHUAN_VALUE)
            .inchThongSoChuanValue(UPDATED_INCH_THONG_SO_CHUAN_VALUE);
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO = thongSoDoSPSizeMapper.toDto(updatedThongSoDoSPSize);

        restThongSoDoSPSizeMockMvc.perform(put("/api/thong-so-do-sp-sizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPSizeDTO)))
            .andExpect(status().isOk());

        // Validate the ThongSoDoSPSize in the database
        List<ThongSoDoSPSize> thongSoDoSPSizeList = thongSoDoSPSizeRepository.findAll();
        assertThat(thongSoDoSPSizeList).hasSize(databaseSizeBeforeUpdate);
        ThongSoDoSPSize testThongSoDoSPSize = thongSoDoSPSizeList.get(thongSoDoSPSizeList.size() - 1);
        assertThat(testThongSoDoSPSize.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testThongSoDoSPSize.getCmThongSoChuan()).isEqualTo(UPDATED_CM_THONG_SO_CHUAN);
        assertThat(testThongSoDoSPSize.getInchThongSoChuan()).isEqualTo(UPDATED_INCH_THONG_SO_CHUAN);
        assertThat(testThongSoDoSPSize.getCmThongSoChuanValue()).isEqualTo(UPDATED_CM_THONG_SO_CHUAN_VALUE);
        assertThat(testThongSoDoSPSize.getInchThongSoChuanValue()).isEqualTo(UPDATED_INCH_THONG_SO_CHUAN_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingThongSoDoSPSize() throws Exception {
        int databaseSizeBeforeUpdate = thongSoDoSPSizeRepository.findAll().size();

        // Create the ThongSoDoSPSize
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO = thongSoDoSPSizeMapper.toDto(thongSoDoSPSize);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongSoDoSPSizeMockMvc.perform(put("/api/thong-so-do-sp-sizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPSizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDoSPSize in the database
        List<ThongSoDoSPSize> thongSoDoSPSizeList = thongSoDoSPSizeRepository.findAll();
        assertThat(thongSoDoSPSizeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThongSoDoSPSize() throws Exception {
        // Initialize the database
        thongSoDoSPSizeRepository.saveAndFlush(thongSoDoSPSize);

        int databaseSizeBeforeDelete = thongSoDoSPSizeRepository.findAll().size();

        // Delete the thongSoDoSPSize
        restThongSoDoSPSizeMockMvc.perform(delete("/api/thong-so-do-sp-sizes/{id}", thongSoDoSPSize.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThongSoDoSPSize> thongSoDoSPSizeList = thongSoDoSPSizeRepository.findAll();
        assertThat(thongSoDoSPSizeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
