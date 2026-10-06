package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProTenMua;
import vn.nvsoft.repository.ProTenMuaRepository;
import vn.nvsoft.service.ProTenMuaService;
import vn.nvsoft.service.dto.ProTenMuaDTO;
import vn.nvsoft.service.mapper.ProTenMuaMapper;
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
 * Integration tests for the {@link ProTenMuaResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProTenMuaResourceIT {

    private static final String DEFAULT_TEN_MUA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_MUA = "BBBBBBBBBB";

    @Autowired
    private ProTenMuaRepository proTenMuaRepository;

    @Autowired
    private ProTenMuaMapper proTenMuaMapper;

    @Autowired
    private ProTenMuaService proTenMuaService;

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

    private MockMvc restProTenMuaMockMvc;

    private ProTenMua proTenMua;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProTenMuaResource proTenMuaResource = new ProTenMuaResource(proTenMuaService);
        this.restProTenMuaMockMvc = MockMvcBuilders.standaloneSetup(proTenMuaResource)
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
    public static ProTenMua createEntity(EntityManager em) {
        ProTenMua proTenMua = new ProTenMua()
            .tenMua(DEFAULT_TEN_MUA);
        return proTenMua;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProTenMua createUpdatedEntity(EntityManager em) {
        ProTenMua proTenMua = new ProTenMua()
            .tenMua(UPDATED_TEN_MUA);
        return proTenMua;
    }

    @BeforeEach
    public void initTest() {
        proTenMua = createEntity(em);
    }

    @Test
    @Transactional
    public void createProTenMua() throws Exception {
        int databaseSizeBeforeCreate = proTenMuaRepository.findAll().size();

        // Create the ProTenMua
        ProTenMuaDTO proTenMuaDTO = proTenMuaMapper.toDto(proTenMua);
        restProTenMuaMockMvc.perform(post("/api/pro-ten-muas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTenMuaDTO)))
            .andExpect(status().isCreated());

        // Validate the ProTenMua in the database
        List<ProTenMua> proTenMuaList = proTenMuaRepository.findAll();
        assertThat(proTenMuaList).hasSize(databaseSizeBeforeCreate + 1);
        ProTenMua testProTenMua = proTenMuaList.get(proTenMuaList.size() - 1);
        assertThat(testProTenMua.getTenMua()).isEqualTo(DEFAULT_TEN_MUA);
    }

    @Test
    @Transactional
    public void createProTenMuaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proTenMuaRepository.findAll().size();

        // Create the ProTenMua with an existing ID
        proTenMua.setId(1L);
        ProTenMuaDTO proTenMuaDTO = proTenMuaMapper.toDto(proTenMua);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProTenMuaMockMvc.perform(post("/api/pro-ten-muas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTenMuaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProTenMua in the database
        List<ProTenMua> proTenMuaList = proTenMuaRepository.findAll();
        assertThat(proTenMuaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProTenMuas() throws Exception {
        // Initialize the database
        proTenMuaRepository.saveAndFlush(proTenMua);

        // Get all the proTenMuaList
        restProTenMuaMockMvc.perform(get("/api/pro-ten-muas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proTenMua.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenMua").value(hasItem(DEFAULT_TEN_MUA)));
    }
    
    @Test
    @Transactional
    public void getProTenMua() throws Exception {
        // Initialize the database
        proTenMuaRepository.saveAndFlush(proTenMua);

        // Get the proTenMua
        restProTenMuaMockMvc.perform(get("/api/pro-ten-muas/{id}", proTenMua.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proTenMua.getId().intValue()))
            .andExpect(jsonPath("$.tenMua").value(DEFAULT_TEN_MUA));
    }

    @Test
    @Transactional
    public void getNonExistingProTenMua() throws Exception {
        // Get the proTenMua
        restProTenMuaMockMvc.perform(get("/api/pro-ten-muas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProTenMua() throws Exception {
        // Initialize the database
        proTenMuaRepository.saveAndFlush(proTenMua);

        int databaseSizeBeforeUpdate = proTenMuaRepository.findAll().size();

        // Update the proTenMua
        ProTenMua updatedProTenMua = proTenMuaRepository.findById(proTenMua.getId()).get();
        // Disconnect from session so that the updates on updatedProTenMua are not directly saved in db
        em.detach(updatedProTenMua);
        updatedProTenMua
            .tenMua(UPDATED_TEN_MUA);
        ProTenMuaDTO proTenMuaDTO = proTenMuaMapper.toDto(updatedProTenMua);

        restProTenMuaMockMvc.perform(put("/api/pro-ten-muas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTenMuaDTO)))
            .andExpect(status().isOk());

        // Validate the ProTenMua in the database
        List<ProTenMua> proTenMuaList = proTenMuaRepository.findAll();
        assertThat(proTenMuaList).hasSize(databaseSizeBeforeUpdate);
        ProTenMua testProTenMua = proTenMuaList.get(proTenMuaList.size() - 1);
        assertThat(testProTenMua.getTenMua()).isEqualTo(UPDATED_TEN_MUA);
    }

    @Test
    @Transactional
    public void updateNonExistingProTenMua() throws Exception {
        int databaseSizeBeforeUpdate = proTenMuaRepository.findAll().size();

        // Create the ProTenMua
        ProTenMuaDTO proTenMuaDTO = proTenMuaMapper.toDto(proTenMua);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProTenMuaMockMvc.perform(put("/api/pro-ten-muas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proTenMuaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProTenMua in the database
        List<ProTenMua> proTenMuaList = proTenMuaRepository.findAll();
        assertThat(proTenMuaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProTenMua() throws Exception {
        // Initialize the database
        proTenMuaRepository.saveAndFlush(proTenMua);

        int databaseSizeBeforeDelete = proTenMuaRepository.findAll().size();

        // Delete the proTenMua
        restProTenMuaMockMvc.perform(delete("/api/pro-ten-muas/{id}", proTenMua.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProTenMua> proTenMuaList = proTenMuaRepository.findAll();
        assertThat(proTenMuaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
