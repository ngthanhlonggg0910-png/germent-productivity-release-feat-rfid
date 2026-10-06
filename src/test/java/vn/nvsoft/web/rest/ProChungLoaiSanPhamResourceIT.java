package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProChungLoaiSanPham;
import vn.nvsoft.repository.ProChungLoaiSanPhamRepository;
import vn.nvsoft.service.ProChungLoaiSanPhamService;
import vn.nvsoft.service.dto.ProChungLoaiSanPhamDTO;
import vn.nvsoft.service.mapper.ProChungLoaiSanPhamMapper;
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
 * Integration tests for the {@link ProChungLoaiSanPhamResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ProChungLoaiSanPhamResourceIT {

    private static final String DEFAULT_TEN_CHUNG_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CHUNG_LOAI = "BBBBBBBBBB";

    @Autowired
    private ProChungLoaiSanPhamRepository proChungLoaiSanPhamRepository;

    @Autowired
    private ProChungLoaiSanPhamMapper proChungLoaiSanPhamMapper;

    @Autowired
    private ProChungLoaiSanPhamService proChungLoaiSanPhamService;

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

    private MockMvc restProChungLoaiSanPhamMockMvc;

    private ProChungLoaiSanPham proChungLoaiSanPham;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProChungLoaiSanPhamResource proChungLoaiSanPhamResource = new ProChungLoaiSanPhamResource(proChungLoaiSanPhamService);
        this.restProChungLoaiSanPhamMockMvc = MockMvcBuilders.standaloneSetup(proChungLoaiSanPhamResource)
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
    public static ProChungLoaiSanPham createEntity(EntityManager em) {
        ProChungLoaiSanPham proChungLoaiSanPham = new ProChungLoaiSanPham()
            .tenChungLoai(DEFAULT_TEN_CHUNG_LOAI);
        return proChungLoaiSanPham;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProChungLoaiSanPham createUpdatedEntity(EntityManager em) {
        ProChungLoaiSanPham proChungLoaiSanPham = new ProChungLoaiSanPham()
            .tenChungLoai(UPDATED_TEN_CHUNG_LOAI);
        return proChungLoaiSanPham;
    }

    @BeforeEach
    public void initTest() {
        proChungLoaiSanPham = createEntity(em);
    }

    @Test
    @Transactional
    public void createProChungLoaiSanPham() throws Exception {
        int databaseSizeBeforeCreate = proChungLoaiSanPhamRepository.findAll().size();

        // Create the ProChungLoaiSanPham
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO = proChungLoaiSanPhamMapper.toDto(proChungLoaiSanPham);
        restProChungLoaiSanPhamMockMvc.perform(post("/api/pro-chung-loai-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChungLoaiSanPhamDTO)))
            .andExpect(status().isCreated());

        // Validate the ProChungLoaiSanPham in the database
        List<ProChungLoaiSanPham> proChungLoaiSanPhamList = proChungLoaiSanPhamRepository.findAll();
        assertThat(proChungLoaiSanPhamList).hasSize(databaseSizeBeforeCreate + 1);
        ProChungLoaiSanPham testProChungLoaiSanPham = proChungLoaiSanPhamList.get(proChungLoaiSanPhamList.size() - 1);
        assertThat(testProChungLoaiSanPham.getTenChungLoai()).isEqualTo(DEFAULT_TEN_CHUNG_LOAI);
    }

    @Test
    @Transactional
    public void createProChungLoaiSanPhamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = proChungLoaiSanPhamRepository.findAll().size();

        // Create the ProChungLoaiSanPham with an existing ID
        proChungLoaiSanPham.setId(1L);
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO = proChungLoaiSanPhamMapper.toDto(proChungLoaiSanPham);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProChungLoaiSanPhamMockMvc.perform(post("/api/pro-chung-loai-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChungLoaiSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProChungLoaiSanPham in the database
        List<ProChungLoaiSanPham> proChungLoaiSanPhamList = proChungLoaiSanPhamRepository.findAll();
        assertThat(proChungLoaiSanPhamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProChungLoaiSanPhams() throws Exception {
        // Initialize the database
        proChungLoaiSanPhamRepository.saveAndFlush(proChungLoaiSanPham);

        // Get all the proChungLoaiSanPhamList
        restProChungLoaiSanPhamMockMvc.perform(get("/api/pro-chung-loai-san-phams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proChungLoaiSanPham.getId().intValue())))
            .andExpect(jsonPath("$.[*].tenChungLoai").value(hasItem(DEFAULT_TEN_CHUNG_LOAI)));
    }
    
    @Test
    @Transactional
    public void getProChungLoaiSanPham() throws Exception {
        // Initialize the database
        proChungLoaiSanPhamRepository.saveAndFlush(proChungLoaiSanPham);

        // Get the proChungLoaiSanPham
        restProChungLoaiSanPhamMockMvc.perform(get("/api/pro-chung-loai-san-phams/{id}", proChungLoaiSanPham.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(proChungLoaiSanPham.getId().intValue()))
            .andExpect(jsonPath("$.tenChungLoai").value(DEFAULT_TEN_CHUNG_LOAI));
    }

    @Test
    @Transactional
    public void getNonExistingProChungLoaiSanPham() throws Exception {
        // Get the proChungLoaiSanPham
        restProChungLoaiSanPhamMockMvc.perform(get("/api/pro-chung-loai-san-phams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProChungLoaiSanPham() throws Exception {
        // Initialize the database
        proChungLoaiSanPhamRepository.saveAndFlush(proChungLoaiSanPham);

        int databaseSizeBeforeUpdate = proChungLoaiSanPhamRepository.findAll().size();

        // Update the proChungLoaiSanPham
        ProChungLoaiSanPham updatedProChungLoaiSanPham = proChungLoaiSanPhamRepository.findById(proChungLoaiSanPham.getId()).get();
        // Disconnect from session so that the updates on updatedProChungLoaiSanPham are not directly saved in db
        em.detach(updatedProChungLoaiSanPham);
        updatedProChungLoaiSanPham
            .tenChungLoai(UPDATED_TEN_CHUNG_LOAI);
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO = proChungLoaiSanPhamMapper.toDto(updatedProChungLoaiSanPham);

        restProChungLoaiSanPhamMockMvc.perform(put("/api/pro-chung-loai-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChungLoaiSanPhamDTO)))
            .andExpect(status().isOk());

        // Validate the ProChungLoaiSanPham in the database
        List<ProChungLoaiSanPham> proChungLoaiSanPhamList = proChungLoaiSanPhamRepository.findAll();
        assertThat(proChungLoaiSanPhamList).hasSize(databaseSizeBeforeUpdate);
        ProChungLoaiSanPham testProChungLoaiSanPham = proChungLoaiSanPhamList.get(proChungLoaiSanPhamList.size() - 1);
        assertThat(testProChungLoaiSanPham.getTenChungLoai()).isEqualTo(UPDATED_TEN_CHUNG_LOAI);
    }

    @Test
    @Transactional
    public void updateNonExistingProChungLoaiSanPham() throws Exception {
        int databaseSizeBeforeUpdate = proChungLoaiSanPhamRepository.findAll().size();

        // Create the ProChungLoaiSanPham
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO = proChungLoaiSanPhamMapper.toDto(proChungLoaiSanPham);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProChungLoaiSanPhamMockMvc.perform(put("/api/pro-chung-loai-san-phams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(proChungLoaiSanPhamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProChungLoaiSanPham in the database
        List<ProChungLoaiSanPham> proChungLoaiSanPhamList = proChungLoaiSanPhamRepository.findAll();
        assertThat(proChungLoaiSanPhamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProChungLoaiSanPham() throws Exception {
        // Initialize the database
        proChungLoaiSanPhamRepository.saveAndFlush(proChungLoaiSanPham);

        int databaseSizeBeforeDelete = proChungLoaiSanPhamRepository.findAll().size();

        // Delete the proChungLoaiSanPham
        restProChungLoaiSanPhamMockMvc.perform(delete("/api/pro-chung-loai-san-phams/{id}", proChungLoaiSanPham.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProChungLoaiSanPham> proChungLoaiSanPhamList = proChungLoaiSanPhamRepository.findAll();
        assertThat(proChungLoaiSanPhamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
