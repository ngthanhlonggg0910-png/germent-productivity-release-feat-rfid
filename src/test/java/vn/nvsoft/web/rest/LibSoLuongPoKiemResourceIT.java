package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.LibSoLuongPoKiem;
import vn.nvsoft.repository.LibSoLuongPoKiemRepository;
import vn.nvsoft.service.LibSoLuongPoKiemService;
import vn.nvsoft.service.dto.LibSoLuongPoKiemDTO;
import vn.nvsoft.service.mapper.LibSoLuongPoKiemMapper;
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
 * Integration tests for the {@link LibSoLuongPoKiemResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class LibSoLuongPoKiemResourceIT {

    private static final Integer DEFAULT_VALUE = 1;
    private static final Integer UPDATED_VALUE = 2;

    @Autowired
    private LibSoLuongPoKiemRepository libSoLuongPoKiemRepository;

    @Autowired
    private LibSoLuongPoKiemMapper libSoLuongPoKiemMapper;

    @Autowired
    private LibSoLuongPoKiemService libSoLuongPoKiemService;

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

    private MockMvc restLibSoLuongPoKiemMockMvc;

    private LibSoLuongPoKiem libSoLuongPoKiem;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LibSoLuongPoKiemResource libSoLuongPoKiemResource = new LibSoLuongPoKiemResource(libSoLuongPoKiemService);
        this.restLibSoLuongPoKiemMockMvc = MockMvcBuilders.standaloneSetup(libSoLuongPoKiemResource)
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
    public static LibSoLuongPoKiem createEntity(EntityManager em) {
        LibSoLuongPoKiem libSoLuongPoKiem = new LibSoLuongPoKiem()
            .value(DEFAULT_VALUE);
        return libSoLuongPoKiem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LibSoLuongPoKiem createUpdatedEntity(EntityManager em) {
        LibSoLuongPoKiem libSoLuongPoKiem = new LibSoLuongPoKiem()
            .value(UPDATED_VALUE);
        return libSoLuongPoKiem;
    }

    @BeforeEach
    public void initTest() {
        libSoLuongPoKiem = createEntity(em);
    }

    @Test
    @Transactional
    public void createLibSoLuongPoKiem() throws Exception {
        int databaseSizeBeforeCreate = libSoLuongPoKiemRepository.findAll().size();

        // Create the LibSoLuongPoKiem
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO = libSoLuongPoKiemMapper.toDto(libSoLuongPoKiem);
        restLibSoLuongPoKiemMockMvc.perform(post("/api/lib-so-luong-po-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libSoLuongPoKiemDTO)))
            .andExpect(status().isCreated());

        // Validate the LibSoLuongPoKiem in the database
        List<LibSoLuongPoKiem> libSoLuongPoKiemList = libSoLuongPoKiemRepository.findAll();
        assertThat(libSoLuongPoKiemList).hasSize(databaseSizeBeforeCreate + 1);
        LibSoLuongPoKiem testLibSoLuongPoKiem = libSoLuongPoKiemList.get(libSoLuongPoKiemList.size() - 1);
        assertThat(testLibSoLuongPoKiem.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createLibSoLuongPoKiemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = libSoLuongPoKiemRepository.findAll().size();

        // Create the LibSoLuongPoKiem with an existing ID
        libSoLuongPoKiem.setId(1L);
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO = libSoLuongPoKiemMapper.toDto(libSoLuongPoKiem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibSoLuongPoKiemMockMvc.perform(post("/api/lib-so-luong-po-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libSoLuongPoKiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LibSoLuongPoKiem in the database
        List<LibSoLuongPoKiem> libSoLuongPoKiemList = libSoLuongPoKiemRepository.findAll();
        assertThat(libSoLuongPoKiemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLibSoLuongPoKiems() throws Exception {
        // Initialize the database
        libSoLuongPoKiemRepository.saveAndFlush(libSoLuongPoKiem);

        // Get all the libSoLuongPoKiemList
        restLibSoLuongPoKiemMockMvc.perform(get("/api/lib-so-luong-po-kiems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(libSoLuongPoKiem.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getLibSoLuongPoKiem() throws Exception {
        // Initialize the database
        libSoLuongPoKiemRepository.saveAndFlush(libSoLuongPoKiem);

        // Get the libSoLuongPoKiem
        restLibSoLuongPoKiemMockMvc.perform(get("/api/lib-so-luong-po-kiems/{id}", libSoLuongPoKiem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(libSoLuongPoKiem.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getNonExistingLibSoLuongPoKiem() throws Exception {
        // Get the libSoLuongPoKiem
        restLibSoLuongPoKiemMockMvc.perform(get("/api/lib-so-luong-po-kiems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLibSoLuongPoKiem() throws Exception {
        // Initialize the database
        libSoLuongPoKiemRepository.saveAndFlush(libSoLuongPoKiem);

        int databaseSizeBeforeUpdate = libSoLuongPoKiemRepository.findAll().size();

        // Update the libSoLuongPoKiem
        LibSoLuongPoKiem updatedLibSoLuongPoKiem = libSoLuongPoKiemRepository.findById(libSoLuongPoKiem.getId()).get();
        // Disconnect from session so that the updates on updatedLibSoLuongPoKiem are not directly saved in db
        em.detach(updatedLibSoLuongPoKiem);
        updatedLibSoLuongPoKiem
            .value(UPDATED_VALUE);
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO = libSoLuongPoKiemMapper.toDto(updatedLibSoLuongPoKiem);

        restLibSoLuongPoKiemMockMvc.perform(put("/api/lib-so-luong-po-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libSoLuongPoKiemDTO)))
            .andExpect(status().isOk());

        // Validate the LibSoLuongPoKiem in the database
        List<LibSoLuongPoKiem> libSoLuongPoKiemList = libSoLuongPoKiemRepository.findAll();
        assertThat(libSoLuongPoKiemList).hasSize(databaseSizeBeforeUpdate);
        LibSoLuongPoKiem testLibSoLuongPoKiem = libSoLuongPoKiemList.get(libSoLuongPoKiemList.size() - 1);
        assertThat(testLibSoLuongPoKiem.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingLibSoLuongPoKiem() throws Exception {
        int databaseSizeBeforeUpdate = libSoLuongPoKiemRepository.findAll().size();

        // Create the LibSoLuongPoKiem
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO = libSoLuongPoKiemMapper.toDto(libSoLuongPoKiem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibSoLuongPoKiemMockMvc.perform(put("/api/lib-so-luong-po-kiems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(libSoLuongPoKiemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LibSoLuongPoKiem in the database
        List<LibSoLuongPoKiem> libSoLuongPoKiemList = libSoLuongPoKiemRepository.findAll();
        assertThat(libSoLuongPoKiemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLibSoLuongPoKiem() throws Exception {
        // Initialize the database
        libSoLuongPoKiemRepository.saveAndFlush(libSoLuongPoKiem);

        int databaseSizeBeforeDelete = libSoLuongPoKiemRepository.findAll().size();

        // Delete the libSoLuongPoKiem
        restLibSoLuongPoKiemMockMvc.perform(delete("/api/lib-so-luong-po-kiems/{id}", libSoLuongPoKiem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LibSoLuongPoKiem> libSoLuongPoKiemList = libSoLuongPoKiemRepository.findAll();
        assertThat(libSoLuongPoKiemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
