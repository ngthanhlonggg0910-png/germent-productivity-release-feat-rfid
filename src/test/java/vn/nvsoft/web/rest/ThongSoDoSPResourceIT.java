package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.repository.ThongSoDoSPRepository;
import vn.nvsoft.service.ThongSoDoSPService;
import vn.nvsoft.service.dto.ThongSoDoSPDTO;
import vn.nvsoft.service.mapper.ThongSoDoSPMapper;
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
 * Integration tests for the {@link ThongSoDoSPResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ThongSoDoSPResourceIT {

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private ThongSoDoSPRepository thongSoDoSPRepository;

    @Autowired
    private ThongSoDoSPMapper thongSoDoSPMapper;

    @Autowired
    private ThongSoDoSPService thongSoDoSPService;

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

    private MockMvc restThongSoDoSPMockMvc;

    private ThongSoDoSP thongSoDoSP;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongSoDoSPResource thongSoDoSPResource = new ThongSoDoSPResource(thongSoDoSPService);
        this.restThongSoDoSPMockMvc = MockMvcBuilders.standaloneSetup(thongSoDoSPResource)
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
    public static ThongSoDoSP createEntity(EntityManager em) {
        ThongSoDoSP thongSoDoSP = new ThongSoDoSP()
            .note(DEFAULT_NOTE);
        return thongSoDoSP;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongSoDoSP createUpdatedEntity(EntityManager em) {
        ThongSoDoSP thongSoDoSP = new ThongSoDoSP()
            .note(UPDATED_NOTE);
        return thongSoDoSP;
    }

    @BeforeEach
    public void initTest() {
        thongSoDoSP = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongSoDoSP() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoSPRepository.findAll().size();

        // Create the ThongSoDoSP
        ThongSoDoSPDTO thongSoDoSPDTO = thongSoDoSPMapper.toDto(thongSoDoSP);
        restThongSoDoSPMockMvc.perform(post("/api/thong-so-do-sps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPDTO)))
            .andExpect(status().isCreated());

        // Validate the ThongSoDoSP in the database
        List<ThongSoDoSP> thongSoDoSPList = thongSoDoSPRepository.findAll();
        assertThat(thongSoDoSPList).hasSize(databaseSizeBeforeCreate + 1);
        ThongSoDoSP testThongSoDoSP = thongSoDoSPList.get(thongSoDoSPList.size() - 1);
        assertThat(testThongSoDoSP.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createThongSoDoSPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoSPRepository.findAll().size();

        // Create the ThongSoDoSP with an existing ID
        thongSoDoSP.setId(1L);
        ThongSoDoSPDTO thongSoDoSPDTO = thongSoDoSPMapper.toDto(thongSoDoSP);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongSoDoSPMockMvc.perform(post("/api/thong-so-do-sps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDoSP in the database
        List<ThongSoDoSP> thongSoDoSPList = thongSoDoSPRepository.findAll();
        assertThat(thongSoDoSPList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllThongSoDoSPS() throws Exception {
        // Initialize the database
        thongSoDoSPRepository.saveAndFlush(thongSoDoSP);

        // Get all the thongSoDoSPList
        restThongSoDoSPMockMvc.perform(get("/api/thong-so-do-sps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongSoDoSP.getId().intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }
    
    @Test
    @Transactional
    public void getThongSoDoSP() throws Exception {
        // Initialize the database
        thongSoDoSPRepository.saveAndFlush(thongSoDoSP);

        // Get the thongSoDoSP
        restThongSoDoSPMockMvc.perform(get("/api/thong-so-do-sps/{id}", thongSoDoSP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongSoDoSP.getId().intValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    public void getNonExistingThongSoDoSP() throws Exception {
        // Get the thongSoDoSP
        restThongSoDoSPMockMvc.perform(get("/api/thong-so-do-sps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongSoDoSP() throws Exception {
        // Initialize the database
        thongSoDoSPRepository.saveAndFlush(thongSoDoSP);

        int databaseSizeBeforeUpdate = thongSoDoSPRepository.findAll().size();

        // Update the thongSoDoSP
        ThongSoDoSP updatedThongSoDoSP = thongSoDoSPRepository.findById(thongSoDoSP.getId()).get();
        // Disconnect from session so that the updates on updatedThongSoDoSP are not directly saved in db
        em.detach(updatedThongSoDoSP);
        updatedThongSoDoSP
            .note(UPDATED_NOTE);
        ThongSoDoSPDTO thongSoDoSPDTO = thongSoDoSPMapper.toDto(updatedThongSoDoSP);

        restThongSoDoSPMockMvc.perform(put("/api/thong-so-do-sps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPDTO)))
            .andExpect(status().isOk());

        // Validate the ThongSoDoSP in the database
        List<ThongSoDoSP> thongSoDoSPList = thongSoDoSPRepository.findAll();
        assertThat(thongSoDoSPList).hasSize(databaseSizeBeforeUpdate);
        ThongSoDoSP testThongSoDoSP = thongSoDoSPList.get(thongSoDoSPList.size() - 1);
        assertThat(testThongSoDoSP.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingThongSoDoSP() throws Exception {
        int databaseSizeBeforeUpdate = thongSoDoSPRepository.findAll().size();

        // Create the ThongSoDoSP
        ThongSoDoSPDTO thongSoDoSPDTO = thongSoDoSPMapper.toDto(thongSoDoSP);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongSoDoSPMockMvc.perform(put("/api/thong-so-do-sps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoSPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDoSP in the database
        List<ThongSoDoSP> thongSoDoSPList = thongSoDoSPRepository.findAll();
        assertThat(thongSoDoSPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThongSoDoSP() throws Exception {
        // Initialize the database
        thongSoDoSPRepository.saveAndFlush(thongSoDoSP);

        int databaseSizeBeforeDelete = thongSoDoSPRepository.findAll().size();

        // Delete the thongSoDoSP
        restThongSoDoSPMockMvc.perform(delete("/api/thong-so-do-sps/{id}", thongSoDoSP.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThongSoDoSP> thongSoDoSPList = thongSoDoSPRepository.findAll();
        assertThat(thongSoDoSPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
