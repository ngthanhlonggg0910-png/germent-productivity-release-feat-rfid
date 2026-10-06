package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProCongTy;
import vn.nvsoft.repository.ProCongTyRepository;
import vn.nvsoft.service.ProCongTyService;
import vn.nvsoft.service.dto.ProCongTyDTO;
import vn.nvsoft.service.mapper.ProCongTyMapper;
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
 * Integration tests for the {@link ProCongTyResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProCongTyResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_MST = "AAAAAAAAAA";
    private static final String UPDATED_MST = "BBBBBBBBBB";

    @Autowired
    private ProCongTyRepository proCongTyRepository;

    @Autowired
    private ProCongTyMapper proCongTyMapper;

    @Autowired
    private ProCongTyService proCongTyService;

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

    private MockMvc restProCongTyMockMvc;

    private ProCongTy proCongTy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProCongTyResource proCongTyResource = new ProCongTyResource(proCongTyService);
        this.restProCongTyMockMvc = MockMvcBuilders.standaloneSetup(proCongTyResource)
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
    public static ProCongTy createEntity(EntityManager em) {
        ProCongTy proCongTy = new ProCongTy()
            .ten(DEFAULT_TEN)
            .diaChi(DEFAULT_DIA_CHI)
            .mst(DEFAULT_MST);
        return proCongTy;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProCongTy createUpdatedEntity(EntityManager em) {
        ProCongTy proCongTy = new ProCongTy()
            .ten(UPDATED_TEN)
            .diaChi(UPDATED_DIA_CHI)
            .mst(UPDATED_MST);
        return proCongTy;
    }

    @BeforeEach
    public void initTest() {
        proCongTy = createEntity(em);
    }

    @Test
    @Transactional
    public void createProCongTy() throws Exception {
        int databaseSizeBeforeCreate = proCongTyRepository.findAll().size();

        // Create the ProCongTy
        ProCongTyDTO proCongTyDTO = proCongTyMapper.toDto(proCongTy);
        restProCongTyMockMvc.perform(post("/api/pro-cong-ties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongTyDTO)))
            .andExpect(status().isCreated());

        // Validate the ProCongTy in the database
        List<ProCongTy> proCongTyList = proCongTyRepository.findAll();
        assertThat(proCongTyList).hasSize(databaseSizeBeforeCreate + 1);
        ProCongTy testProCongTy = proCongTyList.get(proCongTyList.size() - 1);
        assertThat(testProCongTy.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProCongTy.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testProCongTy.getMst()).isEqualTo(DEFAULT_MST);
    }

    @Test
    @Transactional
    public void createProCongTyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proCongTyRepository.findAll().size();

        // Create the ProCongTy with an existing ID
        proCongTy.setId(1L);
        ProCongTyDTO proCongTyDTO = proCongTyMapper.toDto(proCongTy);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProCongTyMockMvc.perform(post("/api/pro-cong-ties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongTyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCongTy in the database
        List<ProCongTy> proCongTyList = proCongTyRepository.findAll();
        assertThat(proCongTyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProCongTies() throws Exception {
        // Initialize the database
        proCongTyRepository.saveAndFlush(proCongTy);

        // Get all the proCongTyList
        restProCongTyMockMvc.perform(get("/api/pro-cong-ties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proCongTy.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI)))
            .andExpect(jsonPath("$.[*].mst").value(hasItem(DEFAULT_MST)));
    }
    
    @Test
    @Transactional
    public void getProCongTy() throws Exception {
        // Initialize the database
        proCongTyRepository.saveAndFlush(proCongTy);

        // Get the proCongTy
        restProCongTyMockMvc.perform(get("/api/pro-cong-ties/{id}", proCongTy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proCongTy.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI))
            .andExpect(jsonPath("$.mst").value(DEFAULT_MST));
    }

    @Test
    @Transactional
    public void getNonExistingProCongTy() throws Exception {
        // Get the proCongTy
        restProCongTyMockMvc.perform(get("/api/pro-cong-ties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProCongTy() throws Exception {
        // Initialize the database
        proCongTyRepository.saveAndFlush(proCongTy);

        int databaseSizeBeforeUpdate = proCongTyRepository.findAll().size();

        // Update the proCongTy
        ProCongTy updatedProCongTy = proCongTyRepository.findById(proCongTy.getId()).get();
        // Disconnect from session so that the updates on updatedProCongTy are not directly saved in db
        em.detach(updatedProCongTy);
        updatedProCongTy
            .ten(UPDATED_TEN)
            .diaChi(UPDATED_DIA_CHI)
            .mst(UPDATED_MST);
        ProCongTyDTO proCongTyDTO = proCongTyMapper.toDto(updatedProCongTy);

        restProCongTyMockMvc.perform(put("/api/pro-cong-ties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongTyDTO)))
            .andExpect(status().isOk());

        // Validate the ProCongTy in the database
        List<ProCongTy> proCongTyList = proCongTyRepository.findAll();
        assertThat(proCongTyList).hasSize(databaseSizeBeforeUpdate);
        ProCongTy testProCongTy = proCongTyList.get(proCongTyList.size() - 1);
        assertThat(testProCongTy.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProCongTy.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testProCongTy.getMst()).isEqualTo(UPDATED_MST);
    }

    @Test
    @Transactional
    public void updateNonExistingProCongTy() throws Exception {
        int databaseSizeBeforeUpdate = proCongTyRepository.findAll().size();

        // Create the ProCongTy
        ProCongTyDTO proCongTyDTO = proCongTyMapper.toDto(proCongTy);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProCongTyMockMvc.perform(put("/api/pro-cong-ties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCongTyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCongTy in the database
        List<ProCongTy> proCongTyList = proCongTyRepository.findAll();
        assertThat(proCongTyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProCongTy() throws Exception {
        // Initialize the database
        proCongTyRepository.saveAndFlush(proCongTy);

        int databaseSizeBeforeDelete = proCongTyRepository.findAll().size();

        // Delete the proCongTy
        restProCongTyMockMvc.perform(delete("/api/pro-cong-ties/{id}", proCongTy.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProCongTy> proCongTyList = proCongTyRepository.findAll();
        assertThat(proCongTyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
