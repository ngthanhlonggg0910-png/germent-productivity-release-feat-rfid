package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.DoKimMd;
import vn.nvsoft.repository.DoKimMdRepository;
import vn.nvsoft.service.DoKimMdService;
import vn.nvsoft.service.dto.DoKimMdDTO;
import vn.nvsoft.service.mapper.DoKimMdMapper;
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
 * Integration tests for the {@link DoKimMdResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class DoKimMdResourceIT {

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final String DEFAULT_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY = "BBBBBBBBBB";

    @Autowired
    private DoKimMdRepository doKimMdRepository;

    @Autowired
    private DoKimMdMapper doKimMdMapper;

    @Autowired
    private DoKimMdService doKimMdService;

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

    private MockMvc restDoKimMdMockMvc;

    private DoKimMd doKimMd;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DoKimMdResource doKimMdResource = new DoKimMdResource(doKimMdService);
        this.restDoKimMdMockMvc = MockMvcBuilders.standaloneSetup(doKimMdResource)
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
    public static DoKimMd createEntity(EntityManager em) {
        DoKimMd doKimMd = new DoKimMd()
            .value(DEFAULT_VALUE)
            .display(DEFAULT_DISPLAY);
        return doKimMd;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoKimMd createUpdatedEntity(EntityManager em) {
        DoKimMd doKimMd = new DoKimMd()
            .value(UPDATED_VALUE)
            .display(UPDATED_DISPLAY);
        return doKimMd;
    }

    @BeforeEach
    public void initTest() {
        doKimMd = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoKimMd() throws Exception {
        int databaseSizeBeforeCreate = doKimMdRepository.findAll().size();

        // Create the DoKimMd
        DoKimMdDTO doKimMdDTO = doKimMdMapper.toDto(doKimMd);
        restDoKimMdMockMvc.perform(post("/api/do-kim-mds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doKimMdDTO)))
            .andExpect(status().isCreated());

        // Validate the DoKimMd in the database
        List<DoKimMd> doKimMdList = doKimMdRepository.findAll();
        assertThat(doKimMdList).hasSize(databaseSizeBeforeCreate + 1);
        DoKimMd testDoKimMd = doKimMdList.get(doKimMdList.size() - 1);
        assertThat(testDoKimMd.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testDoKimMd.getDisplay()).isEqualTo(DEFAULT_DISPLAY);
    }

    @Test
    @Transactional
    public void createDoKimMdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doKimMdRepository.findAll().size();

        // Create the DoKimMd with an existing ID
        doKimMd.setId(1L);
        DoKimMdDTO doKimMdDTO = doKimMdMapper.toDto(doKimMd);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoKimMdMockMvc.perform(post("/api/do-kim-mds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doKimMdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoKimMd in the database
        List<DoKimMd> doKimMdList = doKimMdRepository.findAll();
        assertThat(doKimMdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDoKimMds() throws Exception {
        // Initialize the database
        doKimMdRepository.saveAndFlush(doKimMd);

        // Get all the doKimMdList
        restDoKimMdMockMvc.perform(get("/api/do-kim-mds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doKimMd.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].display").value(hasItem(DEFAULT_DISPLAY)));
    }
    
    @Test
    @Transactional
    public void getDoKimMd() throws Exception {
        // Initialize the database
        doKimMdRepository.saveAndFlush(doKimMd);

        // Get the doKimMd
        restDoKimMdMockMvc.perform(get("/api/do-kim-mds/{id}", doKimMd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(doKimMd.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.display").value(DEFAULT_DISPLAY));
    }

    @Test
    @Transactional
    public void getNonExistingDoKimMd() throws Exception {
        // Get the doKimMd
        restDoKimMdMockMvc.perform(get("/api/do-kim-mds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoKimMd() throws Exception {
        // Initialize the database
        doKimMdRepository.saveAndFlush(doKimMd);

        int databaseSizeBeforeUpdate = doKimMdRepository.findAll().size();

        // Update the doKimMd
        DoKimMd updatedDoKimMd = doKimMdRepository.findById(doKimMd.getId()).get();
        // Disconnect from session so that the updates on updatedDoKimMd are not directly saved in db
        em.detach(updatedDoKimMd);
        updatedDoKimMd
            .value(UPDATED_VALUE)
            .display(UPDATED_DISPLAY);
        DoKimMdDTO doKimMdDTO = doKimMdMapper.toDto(updatedDoKimMd);

        restDoKimMdMockMvc.perform(put("/api/do-kim-mds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doKimMdDTO)))
            .andExpect(status().isOk());

        // Validate the DoKimMd in the database
        List<DoKimMd> doKimMdList = doKimMdRepository.findAll();
        assertThat(doKimMdList).hasSize(databaseSizeBeforeUpdate);
        DoKimMd testDoKimMd = doKimMdList.get(doKimMdList.size() - 1);
        assertThat(testDoKimMd.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testDoKimMd.getDisplay()).isEqualTo(UPDATED_DISPLAY);
    }

    @Test
    @Transactional
    public void updateNonExistingDoKimMd() throws Exception {
        int databaseSizeBeforeUpdate = doKimMdRepository.findAll().size();

        // Create the DoKimMd
        DoKimMdDTO doKimMdDTO = doKimMdMapper.toDto(doKimMd);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoKimMdMockMvc.perform(put("/api/do-kim-mds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(doKimMdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoKimMd in the database
        List<DoKimMd> doKimMdList = doKimMdRepository.findAll();
        assertThat(doKimMdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoKimMd() throws Exception {
        // Initialize the database
        doKimMdRepository.saveAndFlush(doKimMd);

        int databaseSizeBeforeDelete = doKimMdRepository.findAll().size();

        // Delete the doKimMd
        restDoKimMdMockMvc.perform(delete("/api/do-kim-mds/{id}", doKimMd.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoKimMd> doKimMdList = doKimMdRepository.findAll();
        assertThat(doKimMdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
