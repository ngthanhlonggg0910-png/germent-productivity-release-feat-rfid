package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ThongSoThucThi;
import vn.nvsoft.repository.ThongSoThucThiRepository;
import vn.nvsoft.service.ThongSoThucThiService;
import vn.nvsoft.service.dto.ThongSoThucThiDTO;
import vn.nvsoft.service.mapper.ThongSoThucThiMapper;
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
 * Integration tests for the {@link ThongSoThucThiResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ThongSoThucThiResourceIT {

    private static final String DEFAULT_SP_INDEX = "AAAAAAAAAA";
    private static final String UPDATED_SP_INDEX = "BBBBBBBBBB";

    @Autowired
    private ThongSoThucThiRepository thongSoThucThiRepository;

    @Autowired
    private ThongSoThucThiMapper thongSoThucThiMapper;

    @Autowired
    private ThongSoThucThiService thongSoThucThiService;

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

    private MockMvc restThongSoThucThiMockMvc;

    private ThongSoThucThi thongSoThucThi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongSoThucThiResource thongSoThucThiResource = new ThongSoThucThiResource(thongSoThucThiService);
        this.restThongSoThucThiMockMvc = MockMvcBuilders.standaloneSetup(thongSoThucThiResource)
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
    public static ThongSoThucThi createEntity(EntityManager em) {
        ThongSoThucThi thongSoThucThi = new ThongSoThucThi()
            .spIndex(DEFAULT_SP_INDEX);
        return thongSoThucThi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongSoThucThi createUpdatedEntity(EntityManager em) {
        ThongSoThucThi thongSoThucThi = new ThongSoThucThi()
            .spIndex(UPDATED_SP_INDEX);
        return thongSoThucThi;
    }

    @BeforeEach
    public void initTest() {
        thongSoThucThi = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongSoThucThi() throws Exception {
        int databaseSizeBeforeCreate = thongSoThucThiRepository.findAll().size();

        // Create the ThongSoThucThi
        ThongSoThucThiDTO thongSoThucThiDTO = thongSoThucThiMapper.toDto(thongSoThucThi);
        restThongSoThucThiMockMvc.perform(post("/api/thong-so-thuc-this")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoThucThiDTO)))
            .andExpect(status().isCreated());

        // Validate the ThongSoThucThi in the database
        List<ThongSoThucThi> thongSoThucThiList = thongSoThucThiRepository.findAll();
        assertThat(thongSoThucThiList).hasSize(databaseSizeBeforeCreate + 1);
        ThongSoThucThi testThongSoThucThi = thongSoThucThiList.get(thongSoThucThiList.size() - 1);
        assertThat(testThongSoThucThi.getSpIndex()).isEqualTo(DEFAULT_SP_INDEX);
    }

    @Test
    @Transactional
    public void createThongSoThucThiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongSoThucThiRepository.findAll().size();

        // Create the ThongSoThucThi with an existing ID
        thongSoThucThi.setId(1L);
        ThongSoThucThiDTO thongSoThucThiDTO = thongSoThucThiMapper.toDto(thongSoThucThi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongSoThucThiMockMvc.perform(post("/api/thong-so-thuc-this")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoThucThiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoThucThi in the database
        List<ThongSoThucThi> thongSoThucThiList = thongSoThucThiRepository.findAll();
        assertThat(thongSoThucThiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllThongSoThucThis() throws Exception {
        // Initialize the database
        thongSoThucThiRepository.saveAndFlush(thongSoThucThi);

        // Get all the thongSoThucThiList
        restThongSoThucThiMockMvc.perform(get("/api/thong-so-thuc-this?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongSoThucThi.getId().intValue())))
            .andExpect(jsonPath("$.[*].spIndex").value(hasItem(DEFAULT_SP_INDEX)));
    }
    
    @Test
    @Transactional
    public void getThongSoThucThi() throws Exception {
        // Initialize the database
        thongSoThucThiRepository.saveAndFlush(thongSoThucThi);

        // Get the thongSoThucThi
        restThongSoThucThiMockMvc.perform(get("/api/thong-so-thuc-this/{id}", thongSoThucThi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongSoThucThi.getId().intValue()))
            .andExpect(jsonPath("$.spIndex").value(DEFAULT_SP_INDEX));
    }

    @Test
    @Transactional
    public void getNonExistingThongSoThucThi() throws Exception {
        // Get the thongSoThucThi
        restThongSoThucThiMockMvc.perform(get("/api/thong-so-thuc-this/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongSoThucThi() throws Exception {
        // Initialize the database
        thongSoThucThiRepository.saveAndFlush(thongSoThucThi);

        int databaseSizeBeforeUpdate = thongSoThucThiRepository.findAll().size();

        // Update the thongSoThucThi
        ThongSoThucThi updatedThongSoThucThi = thongSoThucThiRepository.findById(thongSoThucThi.getId()).get();
        // Disconnect from session so that the updates on updatedThongSoThucThi are not directly saved in db
        em.detach(updatedThongSoThucThi);
        updatedThongSoThucThi
            .spIndex(UPDATED_SP_INDEX);
        ThongSoThucThiDTO thongSoThucThiDTO = thongSoThucThiMapper.toDto(updatedThongSoThucThi);

        restThongSoThucThiMockMvc.perform(put("/api/thong-so-thuc-this")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoThucThiDTO)))
            .andExpect(status().isOk());

        // Validate the ThongSoThucThi in the database
        List<ThongSoThucThi> thongSoThucThiList = thongSoThucThiRepository.findAll();
        assertThat(thongSoThucThiList).hasSize(databaseSizeBeforeUpdate);
        ThongSoThucThi testThongSoThucThi = thongSoThucThiList.get(thongSoThucThiList.size() - 1);
        assertThat(testThongSoThucThi.getSpIndex()).isEqualTo(UPDATED_SP_INDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingThongSoThucThi() throws Exception {
        int databaseSizeBeforeUpdate = thongSoThucThiRepository.findAll().size();

        // Create the ThongSoThucThi
        ThongSoThucThiDTO thongSoThucThiDTO = thongSoThucThiMapper.toDto(thongSoThucThi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongSoThucThiMockMvc.perform(put("/api/thong-so-thuc-this")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoThucThiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoThucThi in the database
        List<ThongSoThucThi> thongSoThucThiList = thongSoThucThiRepository.findAll();
        assertThat(thongSoThucThiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThongSoThucThi() throws Exception {
        // Initialize the database
        thongSoThucThiRepository.saveAndFlush(thongSoThucThi);

        int databaseSizeBeforeDelete = thongSoThucThiRepository.findAll().size();

        // Delete the thongSoThucThi
        restThongSoThucThiMockMvc.perform(delete("/api/thong-so-thuc-this/{id}", thongSoThucThi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThongSoThucThi> thongSoThucThiList = thongSoThucThiRepository.findAll();
        assertThat(thongSoThucThiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
