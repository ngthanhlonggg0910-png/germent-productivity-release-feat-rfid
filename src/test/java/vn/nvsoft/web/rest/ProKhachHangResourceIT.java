package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProKhachHang;
import vn.nvsoft.repository.ProKhachHangRepository;
import vn.nvsoft.service.ProKhachHangService;
import vn.nvsoft.service.dto.ProKhachHangDTO;
import vn.nvsoft.service.mapper.ProKhachHangMapper;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;
import vn.nvsoft.service.dto.ProKhachHangCriteria;
import vn.nvsoft.service.ProKhachHangQueryService;

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
 * Integration tests for the {@link ProKhachHangResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProKhachHangResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_NHOM = "AAAAAAAAAA";
    private static final String UPDATED_NHOM = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    @Autowired
    private ProKhachHangRepository proKhachHangRepository;

    @Autowired
    private ProKhachHangMapper proKhachHangMapper;

    @Autowired
    private ProKhachHangService proKhachHangService;

    @Autowired
    private ProKhachHangQueryService proKhachHangQueryService;

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

    private MockMvc restProKhachHangMockMvc;

    private ProKhachHang proKhachHang;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProKhachHangResource proKhachHangResource = new ProKhachHangResource(proKhachHangService, proKhachHangQueryService);
        this.restProKhachHangMockMvc = MockMvcBuilders.standaloneSetup(proKhachHangResource)
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
    public static ProKhachHang createEntity(EntityManager em) {
        ProKhachHang proKhachHang = new ProKhachHang()
            .ten(DEFAULT_TEN)
            .nhom(DEFAULT_NHOM)
            .diaChi(DEFAULT_DIA_CHI);
        return proKhachHang;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProKhachHang createUpdatedEntity(EntityManager em) {
        ProKhachHang proKhachHang = new ProKhachHang()
            .ten(UPDATED_TEN)
            .nhom(UPDATED_NHOM)
            .diaChi(UPDATED_DIA_CHI);
        return proKhachHang;
    }

    @BeforeEach
    public void initTest() {
        proKhachHang = createEntity(em);
    }

    @Test
    @Transactional
    public void createProKhachHang() throws Exception {
        int databaseSizeBeforeCreate = proKhachHangRepository.findAll().size();

        // Create the ProKhachHang
        ProKhachHangDTO proKhachHangDTO = proKhachHangMapper.toDto(proKhachHang);
        restProKhachHangMockMvc.perform(post("/api/pro-khach-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKhachHangDTO)))
            .andExpect(status().isCreated());

        // Validate the ProKhachHang in the database
        List<ProKhachHang> proKhachHangList = proKhachHangRepository.findAll();
        assertThat(proKhachHangList).hasSize(databaseSizeBeforeCreate + 1);
        ProKhachHang testProKhachHang = proKhachHangList.get(proKhachHangList.size() - 1);
        assertThat(testProKhachHang.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProKhachHang.getNhom()).isEqualTo(DEFAULT_NHOM);
        assertThat(testProKhachHang.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
    }

    @Test
    @Transactional
    public void createProKhachHangWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proKhachHangRepository.findAll().size();

        // Create the ProKhachHang with an existing ID
        proKhachHang.setId(1L);
        ProKhachHangDTO proKhachHangDTO = proKhachHangMapper.toDto(proKhachHang);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProKhachHangMockMvc.perform(post("/api/pro-khach-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKhachHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProKhachHang in the database
        List<ProKhachHang> proKhachHangList = proKhachHangRepository.findAll();
        assertThat(proKhachHangList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProKhachHangs() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proKhachHang.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].nhom").value(hasItem(DEFAULT_NHOM)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)));
    }
    
    @Test
    @Transactional
    public void getProKhachHang() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get the proKhachHang
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs/{id}", proKhachHang.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proKhachHang.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.nhom").value(DEFAULT_NHOM))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI));
    }


    @Test
    @Transactional
    public void getProKhachHangsByIdFiltering() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        Long id = proKhachHang.getId();

        defaultProKhachHangShouldBeFound("id.equals=" + id);
        defaultProKhachHangShouldNotBeFound("id.notEquals=" + id);

        defaultProKhachHangShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProKhachHangShouldNotBeFound("id.greaterThan=" + id);

        defaultProKhachHangShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProKhachHangShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllProKhachHangsByTenIsEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten equals to DEFAULT_TEN
        defaultProKhachHangShouldBeFound("ten.equals=" + DEFAULT_TEN);

        // Get all the proKhachHangList where ten equals to UPDATED_TEN
        defaultProKhachHangShouldNotBeFound("ten.equals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByTenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten not equals to DEFAULT_TEN
        defaultProKhachHangShouldNotBeFound("ten.notEquals=" + DEFAULT_TEN);

        // Get all the proKhachHangList where ten not equals to UPDATED_TEN
        defaultProKhachHangShouldBeFound("ten.notEquals=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByTenIsInShouldWork() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten in DEFAULT_TEN or UPDATED_TEN
        defaultProKhachHangShouldBeFound("ten.in=" + DEFAULT_TEN + "," + UPDATED_TEN);

        // Get all the proKhachHangList where ten equals to UPDATED_TEN
        defaultProKhachHangShouldNotBeFound("ten.in=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByTenIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten is not null
        defaultProKhachHangShouldBeFound("ten.specified=true");

        // Get all the proKhachHangList where ten is null
        defaultProKhachHangShouldNotBeFound("ten.specified=false");
    }
                @Test
    @Transactional
    public void getAllProKhachHangsByTenContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten contains DEFAULT_TEN
        defaultProKhachHangShouldBeFound("ten.contains=" + DEFAULT_TEN);

        // Get all the proKhachHangList where ten contains UPDATED_TEN
        defaultProKhachHangShouldNotBeFound("ten.contains=" + UPDATED_TEN);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByTenNotContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where ten does not contain DEFAULT_TEN
        defaultProKhachHangShouldNotBeFound("ten.doesNotContain=" + DEFAULT_TEN);

        // Get all the proKhachHangList where ten does not contain UPDATED_TEN
        defaultProKhachHangShouldBeFound("ten.doesNotContain=" + UPDATED_TEN);
    }


    @Test
    @Transactional
    public void getAllProKhachHangsByNhomIsEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom equals to DEFAULT_NHOM
        defaultProKhachHangShouldBeFound("nhom.equals=" + DEFAULT_NHOM);

        // Get all the proKhachHangList where nhom equals to UPDATED_NHOM
        defaultProKhachHangShouldNotBeFound("nhom.equals=" + UPDATED_NHOM);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByNhomIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom not equals to DEFAULT_NHOM
        defaultProKhachHangShouldNotBeFound("nhom.notEquals=" + DEFAULT_NHOM);

        // Get all the proKhachHangList where nhom not equals to UPDATED_NHOM
        defaultProKhachHangShouldBeFound("nhom.notEquals=" + UPDATED_NHOM);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByNhomIsInShouldWork() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom in DEFAULT_NHOM or UPDATED_NHOM
        defaultProKhachHangShouldBeFound("nhom.in=" + DEFAULT_NHOM + "," + UPDATED_NHOM);

        // Get all the proKhachHangList where nhom equals to UPDATED_NHOM
        defaultProKhachHangShouldNotBeFound("nhom.in=" + UPDATED_NHOM);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByNhomIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom is not null
        defaultProKhachHangShouldBeFound("nhom.specified=true");

        // Get all the proKhachHangList where nhom is null
        defaultProKhachHangShouldNotBeFound("nhom.specified=false");
    }
                @Test
    @Transactional
    public void getAllProKhachHangsByNhomContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom contains DEFAULT_NHOM
        defaultProKhachHangShouldBeFound("nhom.contains=" + DEFAULT_NHOM);

        // Get all the proKhachHangList where nhom contains UPDATED_NHOM
        defaultProKhachHangShouldNotBeFound("nhom.contains=" + UPDATED_NHOM);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByNhomNotContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where nhom does not contain DEFAULT_NHOM
        defaultProKhachHangShouldNotBeFound("nhom.doesNotContain=" + DEFAULT_NHOM);

        // Get all the proKhachHangList where nhom does not contain UPDATED_NHOM
        defaultProKhachHangShouldBeFound("nhom.doesNotContain=" + UPDATED_NHOM);
    }


    @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiIsEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi equals to DEFAULT_DIA_CHI
        defaultProKhachHangShouldBeFound("diaChi.equals=" + DEFAULT_DIA_CHI);

        // Get all the proKhachHangList where diaChi equals to UPDATED_DIA_CHI
        defaultProKhachHangShouldNotBeFound("diaChi.equals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiIsNotEqualToSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi not equals to DEFAULT_DIA_CHI
        defaultProKhachHangShouldNotBeFound("diaChi.notEquals=" + DEFAULT_DIA_CHI);

        // Get all the proKhachHangList where diaChi not equals to UPDATED_DIA_CHI
        defaultProKhachHangShouldBeFound("diaChi.notEquals=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiIsInShouldWork() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi in DEFAULT_DIA_CHI or UPDATED_DIA_CHI
        defaultProKhachHangShouldBeFound("diaChi.in=" + DEFAULT_DIA_CHI + "," + UPDATED_DIA_CHI);

        // Get all the proKhachHangList where diaChi equals to UPDATED_DIA_CHI
        defaultProKhachHangShouldNotBeFound("diaChi.in=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiIsNullOrNotNull() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi is not null
        defaultProKhachHangShouldBeFound("diaChi.specified=true");

        // Get all the proKhachHangList where diaChi is null
        defaultProKhachHangShouldNotBeFound("diaChi.specified=false");
    }
                @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi contains DEFAULT_DIA_CHI
        defaultProKhachHangShouldBeFound("diaChi.contains=" + DEFAULT_DIA_CHI);

        // Get all the proKhachHangList where diaChi contains UPDATED_DIA_CHI
        defaultProKhachHangShouldNotBeFound("diaChi.contains=" + UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void getAllProKhachHangsByDiaChiNotContainsSomething() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        // Get all the proKhachHangList where diaChi does not contain DEFAULT_DIA_CHI
        defaultProKhachHangShouldNotBeFound("diaChi.doesNotContain=" + DEFAULT_DIA_CHI);

        // Get all the proKhachHangList where diaChi does not contain UPDATED_DIA_CHI
        defaultProKhachHangShouldBeFound("diaChi.doesNotContain=" + UPDATED_DIA_CHI);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProKhachHangShouldBeFound(String filter) throws Exception {
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proKhachHang.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].nhom").value(hasItem(DEFAULT_NHOM)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)));

        // Check, that the count call also returns 1
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProKhachHangShouldNotBeFound(String filter) throws Exception {
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProKhachHang() throws Exception {
        // Get the proKhachHang
        restProKhachHangMockMvc.perform(get("/api/pro-khach-hangs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProKhachHang() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        int databaseSizeBeforeUpdate = proKhachHangRepository.findAll().size();

        // Update the proKhachHang
        ProKhachHang updatedProKhachHang = proKhachHangRepository.findById(proKhachHang.getId()).get();
        // Disconnect from session so that the updates on updatedProKhachHang are not directly saved in db
        em.detach(updatedProKhachHang);
        updatedProKhachHang
            .ten(UPDATED_TEN)
            .nhom(UPDATED_NHOM)
            .diaChi(UPDATED_DIA_CHI);
        ProKhachHangDTO proKhachHangDTO = proKhachHangMapper.toDto(updatedProKhachHang);

        restProKhachHangMockMvc.perform(put("/api/pro-khach-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKhachHangDTO)))
            .andExpect(status().isOk());

        // Validate the ProKhachHang in the database
        List<ProKhachHang> proKhachHangList = proKhachHangRepository.findAll();
        assertThat(proKhachHangList).hasSize(databaseSizeBeforeUpdate);
        ProKhachHang testProKhachHang = proKhachHangList.get(proKhachHangList.size() - 1);
        assertThat(testProKhachHang.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProKhachHang.getNhom()).isEqualTo(UPDATED_NHOM);
        assertThat(testProKhachHang.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
    }

    @Test
    @Transactional
    public void updateNonExistingProKhachHang() throws Exception {
        int databaseSizeBeforeUpdate = proKhachHangRepository.findAll().size();

        // Create the ProKhachHang
        ProKhachHangDTO proKhachHangDTO = proKhachHangMapper.toDto(proKhachHang);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProKhachHangMockMvc.perform(put("/api/pro-khach-hangs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proKhachHangDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProKhachHang in the database
        List<ProKhachHang> proKhachHangList = proKhachHangRepository.findAll();
        assertThat(proKhachHangList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProKhachHang() throws Exception {
        // Initialize the database
        proKhachHangRepository.saveAndFlush(proKhachHang);

        int databaseSizeBeforeDelete = proKhachHangRepository.findAll().size();

        // Delete the proKhachHang
        restProKhachHangMockMvc.perform(delete("/api/pro-khach-hangs/{id}", proKhachHang.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProKhachHang> proKhachHangList = proKhachHangRepository.findAll();
        assertThat(proKhachHangList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
