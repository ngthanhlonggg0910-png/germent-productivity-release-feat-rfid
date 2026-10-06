package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProAql;
import vn.nvsoft.repository.ProAqlRepository;
import vn.nvsoft.service.ProAqlService;
import vn.nvsoft.service.dto.ProAqlDTO;
import vn.nvsoft.service.mapper.ProAqlMapper;
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
 * Integration tests for the {@link ProAqlResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProAqlResourceIT {

    private static final String DEFAULT_AQL = "AAAAAAAAAA";
    private static final String UPDATED_AQL = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_LUONG_DON_HANG_TU = 1;
    private static final Integer UPDATED_SO_LUONG_DON_HANG_TU = 2;

    private static final Integer DEFAULT_SO_LUONG_DON_HANG_DEN = 1;
    private static final Integer UPDATED_SO_LUONG_DON_HANG_DEN = 2;

    private static final Integer DEFAULT_SO_LUONG_KIEM = 1;
    private static final Integer UPDATED_SO_LUONG_KIEM = 2;

    private static final Integer DEFAULT_SO_LUONG_SE_FAIL = 1;
    private static final Integer UPDATED_SO_LUONG_SE_FAIL = 2;

    private static final Integer DEFAULT_SO_LUONG_SE_PASS = 1;
    private static final Integer UPDATED_SO_LUONG_SE_PASS = 2;

    @Autowired
    private ProAqlRepository proAqlRepository;

    @Autowired
    private ProAqlMapper proAqlMapper;

    @Autowired
    private ProAqlService proAqlService;

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

    private MockMvc restProAqlMockMvc;

    private ProAql proAql;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProAqlResource proAqlResource = new ProAqlResource(proAqlService);
        this.restProAqlMockMvc = MockMvcBuilders.standaloneSetup(proAqlResource)
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
    public static ProAql createEntity(EntityManager em) {
        ProAql proAql = new ProAql()
            .aql(DEFAULT_AQL)
            .soLuongDonHangTu(DEFAULT_SO_LUONG_DON_HANG_TU)
            .soLuongDonHangDen(DEFAULT_SO_LUONG_DON_HANG_DEN)
            .soLuongKiem(DEFAULT_SO_LUONG_KIEM)
            .soLuongSeFail(DEFAULT_SO_LUONG_SE_FAIL)
            .soLuongSePass(DEFAULT_SO_LUONG_SE_PASS);
        return proAql;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProAql createUpdatedEntity(EntityManager em) {
        ProAql proAql = new ProAql()
            .aql(UPDATED_AQL)
            .soLuongDonHangTu(UPDATED_SO_LUONG_DON_HANG_TU)
            .soLuongDonHangDen(UPDATED_SO_LUONG_DON_HANG_DEN)
            .soLuongKiem(UPDATED_SO_LUONG_KIEM)
            .soLuongSeFail(UPDATED_SO_LUONG_SE_FAIL)
            .soLuongSePass(UPDATED_SO_LUONG_SE_PASS);
        return proAql;
    }

    @BeforeEach
    public void initTest() {
        proAql = createEntity(em);
    }

    @Test
    @Transactional
    public void createProAql() throws Exception {
        int databaseSizeBeforeCreate = proAqlRepository.findAll().size();

        // Create the ProAql
        ProAqlDTO proAqlDTO = proAqlMapper.toDto(proAql);
        restProAqlMockMvc.perform(post("/api/pro-aqls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proAqlDTO)))
            .andExpect(status().isCreated());

        // Validate the ProAql in the database
        List<ProAql> proAqlList = proAqlRepository.findAll();
        assertThat(proAqlList).hasSize(databaseSizeBeforeCreate + 1);
        ProAql testProAql = proAqlList.get(proAqlList.size() - 1);
        assertThat(testProAql.getAql()).isEqualTo(DEFAULT_AQL);
        assertThat(testProAql.getSoLuongDonHangTu()).isEqualTo(DEFAULT_SO_LUONG_DON_HANG_TU);
        assertThat(testProAql.getSoLuongDonHangDen()).isEqualTo(DEFAULT_SO_LUONG_DON_HANG_DEN);
        assertThat(testProAql.getSoLuongKiem()).isEqualTo(DEFAULT_SO_LUONG_KIEM);
        assertThat(testProAql.getSoLuongSeFail()).isEqualTo(DEFAULT_SO_LUONG_SE_FAIL);
        assertThat(testProAql.getSoLuongSePass()).isEqualTo(DEFAULT_SO_LUONG_SE_PASS);
    }

    @Test
    @Transactional
    public void createProAqlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proAqlRepository.findAll().size();

        // Create the ProAql with an existing ID
        proAql.setId(1L);
        ProAqlDTO proAqlDTO = proAqlMapper.toDto(proAql);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProAqlMockMvc.perform(post("/api/pro-aqls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proAqlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProAql in the database
        List<ProAql> proAqlList = proAqlRepository.findAll();
        assertThat(proAqlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProAqls() throws Exception {
        // Initialize the database
        proAqlRepository.saveAndFlush(proAql);

        // Get all the proAqlList
        restProAqlMockMvc.perform(get("/api/pro-aqls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proAql.getId().intValue())))
            .andExpect(jsonPath("$.[*].aql").value(hasItem(DEFAULT_AQL)))
            .andExpect(jsonPath("$.[*].soLuongDonHangTu").value(hasItem(DEFAULT_SO_LUONG_DON_HANG_TU)))
            .andExpect(jsonPath("$.[*].soLuongDonHangDen").value(hasItem(DEFAULT_SO_LUONG_DON_HANG_DEN)))
            .andExpect(jsonPath("$.[*].soLuongKiem").value(hasItem(DEFAULT_SO_LUONG_KIEM)))
            .andExpect(jsonPath("$.[*].soLuongSeFail").value(hasItem(DEFAULT_SO_LUONG_SE_FAIL)))
            .andExpect(jsonPath("$.[*].soLuongSePass").value(hasItem(DEFAULT_SO_LUONG_SE_PASS)));
    }
    
    @Test
    @Transactional
    public void getProAql() throws Exception {
        // Initialize the database
        proAqlRepository.saveAndFlush(proAql);

        // Get the proAql
        restProAqlMockMvc.perform(get("/api/pro-aqls/{id}", proAql.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proAql.getId().intValue()))
            .andExpect(jsonPath("$.aql").value(DEFAULT_AQL))
            .andExpect(jsonPath("$.soLuongDonHangTu").value(DEFAULT_SO_LUONG_DON_HANG_TU))
            .andExpect(jsonPath("$.soLuongDonHangDen").value(DEFAULT_SO_LUONG_DON_HANG_DEN))
            .andExpect(jsonPath("$.soLuongKiem").value(DEFAULT_SO_LUONG_KIEM))
            .andExpect(jsonPath("$.soLuongSeFail").value(DEFAULT_SO_LUONG_SE_FAIL))
            .andExpect(jsonPath("$.soLuongSePass").value(DEFAULT_SO_LUONG_SE_PASS));
    }

    @Test
    @Transactional
    public void getNonExistingProAql() throws Exception {
        // Get the proAql
        restProAqlMockMvc.perform(get("/api/pro-aqls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProAql() throws Exception {
        // Initialize the database
        proAqlRepository.saveAndFlush(proAql);

        int databaseSizeBeforeUpdate = proAqlRepository.findAll().size();

        // Update the proAql
        ProAql updatedProAql = proAqlRepository.findById(proAql.getId()).get();
        // Disconnect from session so that the updates on updatedProAql are not directly saved in db
        em.detach(updatedProAql);
        updatedProAql
            .aql(UPDATED_AQL)
            .soLuongDonHangTu(UPDATED_SO_LUONG_DON_HANG_TU)
            .soLuongDonHangDen(UPDATED_SO_LUONG_DON_HANG_DEN)
            .soLuongKiem(UPDATED_SO_LUONG_KIEM)
            .soLuongSeFail(UPDATED_SO_LUONG_SE_FAIL)
            .soLuongSePass(UPDATED_SO_LUONG_SE_PASS);
        ProAqlDTO proAqlDTO = proAqlMapper.toDto(updatedProAql);

        restProAqlMockMvc.perform(put("/api/pro-aqls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proAqlDTO)))
            .andExpect(status().isOk());

        // Validate the ProAql in the database
        List<ProAql> proAqlList = proAqlRepository.findAll();
        assertThat(proAqlList).hasSize(databaseSizeBeforeUpdate);
        ProAql testProAql = proAqlList.get(proAqlList.size() - 1);
        assertThat(testProAql.getAql()).isEqualTo(UPDATED_AQL);
        assertThat(testProAql.getSoLuongDonHangTu()).isEqualTo(UPDATED_SO_LUONG_DON_HANG_TU);
        assertThat(testProAql.getSoLuongDonHangDen()).isEqualTo(UPDATED_SO_LUONG_DON_HANG_DEN);
        assertThat(testProAql.getSoLuongKiem()).isEqualTo(UPDATED_SO_LUONG_KIEM);
        assertThat(testProAql.getSoLuongSeFail()).isEqualTo(UPDATED_SO_LUONG_SE_FAIL);
        assertThat(testProAql.getSoLuongSePass()).isEqualTo(UPDATED_SO_LUONG_SE_PASS);
    }

    @Test
    @Transactional
    public void updateNonExistingProAql() throws Exception {
        int databaseSizeBeforeUpdate = proAqlRepository.findAll().size();

        // Create the ProAql
        ProAqlDTO proAqlDTO = proAqlMapper.toDto(proAql);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProAqlMockMvc.perform(put("/api/pro-aqls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proAqlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProAql in the database
        List<ProAql> proAqlList = proAqlRepository.findAll();
        assertThat(proAqlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProAql() throws Exception {
        // Initialize the database
        proAqlRepository.saveAndFlush(proAql);

        int databaseSizeBeforeDelete = proAqlRepository.findAll().size();

        // Delete the proAql
        restProAqlMockMvc.perform(delete("/api/pro-aqls/{id}", proAql.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProAql> proAqlList = proAqlRepository.findAll();
        assertThat(proAqlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
