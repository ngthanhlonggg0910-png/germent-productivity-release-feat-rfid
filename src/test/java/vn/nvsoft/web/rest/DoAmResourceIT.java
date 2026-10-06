package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.DoAm;
import vn.nvsoft.repository.DoAmRepository;
import vn.nvsoft.service.DoAmService;
import vn.nvsoft.service.dto.DoAmDTO;
import vn.nvsoft.service.mapper.DoAmMapper;
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
 * Integration tests for the {@link DoAmResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class DoAmResourceIT {

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final String DEFAULT_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY = "BBBBBBBBBB";

    @Autowired
    private DoAmRepository doAmRepository;

    @Autowired
    private DoAmMapper doAmMapper;

    @Autowired
    private DoAmService doAmService;

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

    private MockMvc restDoAmMockMvc;

    private DoAm doAm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DoAmResource doAmResource = new DoAmResource(doAmService);
        this.restDoAmMockMvc = MockMvcBuilders.standaloneSetup(doAmResource)
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
    public static DoAm createEntity(EntityManager em) {
        DoAm doAm = new DoAm()
            .value(DEFAULT_VALUE)
            .display(DEFAULT_DISPLAY);
        return doAm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoAm createUpdatedEntity(EntityManager em) {
        DoAm doAm = new DoAm()
            .value(UPDATED_VALUE)
            .display(UPDATED_DISPLAY);
        return doAm;
    }

    @BeforeEach
    public void initTest() {
        doAm = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoAm() throws Exception {
        int databaseSizeBeforeCreate = doAmRepository.findAll().size();

        // Create the DoAm
        DoAmDTO doAmDTO = doAmMapper.toDto(doAm);
        restDoAmMockMvc.perform(post("/api/do-ams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doAmDTO)))
            .andExpect(status().isCreated());

        // Validate the DoAm in the database
        List<DoAm> doAmList = doAmRepository.findAll();
        assertThat(doAmList).hasSize(databaseSizeBeforeCreate + 1);
        DoAm testDoAm = doAmList.get(doAmList.size() - 1);
        assertThat(testDoAm.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testDoAm.getDisplay()).isEqualTo(DEFAULT_DISPLAY);
    }

    @Test
    @Transactional
    public void createDoAmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doAmRepository.findAll().size();

        // Create the DoAm with an existing ID
        doAm.setId(1L);
        DoAmDTO doAmDTO = doAmMapper.toDto(doAm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoAmMockMvc.perform(post("/api/do-ams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doAmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoAm in the database
        List<DoAm> doAmList = doAmRepository.findAll();
        assertThat(doAmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDoAms() throws Exception {
        // Initialize the database
        doAmRepository.saveAndFlush(doAm);

        // Get all the doAmList
        restDoAmMockMvc.perform(get("/api/do-ams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doAm.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].display").value(hasItem(DEFAULT_DISPLAY)));
    }
    
    @Test
    @Transactional
    public void getDoAm() throws Exception {
        // Initialize the database
        doAmRepository.saveAndFlush(doAm);

        // Get the doAm
        restDoAmMockMvc.perform(get("/api/do-ams/{id}", doAm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doAm.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.display").value(DEFAULT_DISPLAY));
    }

    @Test
    @Transactional
    public void getNonExistingDoAm() throws Exception {
        // Get the doAm
        restDoAmMockMvc.perform(get("/api/do-ams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoAm() throws Exception {
        // Initialize the database
        doAmRepository.saveAndFlush(doAm);

        int databaseSizeBeforeUpdate = doAmRepository.findAll().size();

        // Update the doAm
        DoAm updatedDoAm = doAmRepository.findById(doAm.getId()).get();
        // Disconnect from session so that the updates on updatedDoAm are not directly saved in db
        em.detach(updatedDoAm);
        updatedDoAm
            .value(UPDATED_VALUE)
            .display(UPDATED_DISPLAY);
        DoAmDTO doAmDTO = doAmMapper.toDto(updatedDoAm);

        restDoAmMockMvc.perform(put("/api/do-ams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doAmDTO)))
            .andExpect(status().isOk());

        // Validate the DoAm in the database
        List<DoAm> doAmList = doAmRepository.findAll();
        assertThat(doAmList).hasSize(databaseSizeBeforeUpdate);
        DoAm testDoAm = doAmList.get(doAmList.size() - 1);
        assertThat(testDoAm.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testDoAm.getDisplay()).isEqualTo(UPDATED_DISPLAY);
    }

    @Test
    @Transactional
    public void updateNonExistingDoAm() throws Exception {
        int databaseSizeBeforeUpdate = doAmRepository.findAll().size();

        // Create the DoAm
        DoAmDTO doAmDTO = doAmMapper.toDto(doAm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoAmMockMvc.perform(put("/api/do-ams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doAmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoAm in the database
        List<DoAm> doAmList = doAmRepository.findAll();
        assertThat(doAmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoAm() throws Exception {
        // Initialize the database
        doAmRepository.saveAndFlush(doAm);

        int databaseSizeBeforeDelete = doAmRepository.findAll().size();

        // Delete the doAm
        restDoAmMockMvc.perform(delete("/api/do-ams/{id}", doAm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoAm> doAmList = doAmRepository.findAll();
        assertThat(doAmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
