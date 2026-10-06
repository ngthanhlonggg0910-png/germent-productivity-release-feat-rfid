package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProCumLoi;
import vn.nvsoft.repository.ProCumLoiRepository;
import vn.nvsoft.service.ProCumLoiService;
import vn.nvsoft.service.dto.ProCumLoiDTO;
import vn.nvsoft.service.mapper.ProCumLoiMapper;
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
 * Integration tests for the {@link ProCumLoiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProCumLoiResourceIT {

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MAU = "AAAAAAAAAA";
    private static final String UPDATED_MAU = "BBBBBBBBBB";

    @Autowired
    private ProCumLoiRepository proCumLoiRepository;

    @Autowired
    private ProCumLoiMapper proCumLoiMapper;

    @Autowired
    private ProCumLoiService proCumLoiService;

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

    private MockMvc restProCumLoiMockMvc;

    private ProCumLoi proCumLoi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProCumLoiResource proCumLoiResource = new ProCumLoiResource(proCumLoiService);
        this.restProCumLoiMockMvc = MockMvcBuilders.standaloneSetup(proCumLoiResource)
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
    public static ProCumLoi createEntity(EntityManager em) {
        ProCumLoi proCumLoi = new ProCumLoi()
            .ten(DEFAULT_TEN)
            .mau(DEFAULT_MAU);
        return proCumLoi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProCumLoi createUpdatedEntity(EntityManager em) {
        ProCumLoi proCumLoi = new ProCumLoi()
            .ten(UPDATED_TEN)
            .mau(UPDATED_MAU);
        return proCumLoi;
    }

    @BeforeEach
    public void initTest() {
        proCumLoi = createEntity(em);
    }

    @Test
    @Transactional
    public void createProCumLoi() throws Exception {
        int databaseSizeBeforeCreate = proCumLoiRepository.findAll().size();

        // Create the ProCumLoi
        ProCumLoiDTO proCumLoiDTO = proCumLoiMapper.toDto(proCumLoi);
        restProCumLoiMockMvc.perform(post("/api/pro-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCumLoiDTO)))
            .andExpect(status().isCreated());

        // Validate the ProCumLoi in the database
        List<ProCumLoi> proCumLoiList = proCumLoiRepository.findAll();
        assertThat(proCumLoiList).hasSize(databaseSizeBeforeCreate + 1);
        ProCumLoi testProCumLoi = proCumLoiList.get(proCumLoiList.size() - 1);
        assertThat(testProCumLoi.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testProCumLoi.getMau()).isEqualTo(DEFAULT_MAU);
    }

    @Test
    @Transactional
    public void createProCumLoiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proCumLoiRepository.findAll().size();

        // Create the ProCumLoi with an existing ID
        proCumLoi.setId(1L);
        ProCumLoiDTO proCumLoiDTO = proCumLoiMapper.toDto(proCumLoi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProCumLoiMockMvc.perform(post("/api/pro-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCumLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCumLoi in the database
        List<ProCumLoi> proCumLoiList = proCumLoiRepository.findAll();
        assertThat(proCumLoiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProCumLois() throws Exception {
        // Initialize the database
        proCumLoiRepository.saveAndFlush(proCumLoi);

        // Get all the proCumLoiList
        restProCumLoiMockMvc.perform(get("/api/pro-cum-lois?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proCumLoi.getId().intValue())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)))
            .andExpect(jsonPath("$.[*].mau").value(hasItem(DEFAULT_MAU)));
    }
    
    @Test
    @Transactional
    public void getProCumLoi() throws Exception {
        // Initialize the database
        proCumLoiRepository.saveAndFlush(proCumLoi);

        // Get the proCumLoi
        restProCumLoiMockMvc.perform(get("/api/pro-cum-lois/{id}", proCumLoi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proCumLoi.getId().intValue()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN))
            .andExpect(jsonPath("$.mau").value(DEFAULT_MAU));
    }

    @Test
    @Transactional
    public void getNonExistingProCumLoi() throws Exception {
        // Get the proCumLoi
        restProCumLoiMockMvc.perform(get("/api/pro-cum-lois/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProCumLoi() throws Exception {
        // Initialize the database
        proCumLoiRepository.saveAndFlush(proCumLoi);

        int databaseSizeBeforeUpdate = proCumLoiRepository.findAll().size();

        // Update the proCumLoi
        ProCumLoi updatedProCumLoi = proCumLoiRepository.findById(proCumLoi.getId()).get();
        // Disconnect from session so that the updates on updatedProCumLoi are not directly saved in db
        em.detach(updatedProCumLoi);
        updatedProCumLoi
            .ten(UPDATED_TEN)
            .mau(UPDATED_MAU);
        ProCumLoiDTO proCumLoiDTO = proCumLoiMapper.toDto(updatedProCumLoi);

        restProCumLoiMockMvc.perform(put("/api/pro-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCumLoiDTO)))
            .andExpect(status().isOk());

        // Validate the ProCumLoi in the database
        List<ProCumLoi> proCumLoiList = proCumLoiRepository.findAll();
        assertThat(proCumLoiList).hasSize(databaseSizeBeforeUpdate);
        ProCumLoi testProCumLoi = proCumLoiList.get(proCumLoiList.size() - 1);
        assertThat(testProCumLoi.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testProCumLoi.getMau()).isEqualTo(UPDATED_MAU);
    }

    @Test
    @Transactional
    public void updateNonExistingProCumLoi() throws Exception {
        int databaseSizeBeforeUpdate = proCumLoiRepository.findAll().size();

        // Create the ProCumLoi
        ProCumLoiDTO proCumLoiDTO = proCumLoiMapper.toDto(proCumLoi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProCumLoiMockMvc.perform(put("/api/pro-cum-lois")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proCumLoiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProCumLoi in the database
        List<ProCumLoi> proCumLoiList = proCumLoiRepository.findAll();
        assertThat(proCumLoiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProCumLoi() throws Exception {
        // Initialize the database
        proCumLoiRepository.saveAndFlush(proCumLoi);

        int databaseSizeBeforeDelete = proCumLoiRepository.findAll().size();

        // Delete the proCumLoi
        restProCumLoiMockMvc.perform(delete("/api/pro-cum-lois/{id}", proCumLoi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProCumLoi> proCumLoiList = proCumLoiRepository.findAll();
        assertThat(proCumLoiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
