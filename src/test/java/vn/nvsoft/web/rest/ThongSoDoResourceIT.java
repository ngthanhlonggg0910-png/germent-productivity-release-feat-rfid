package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ThongSoDo;
import vn.nvsoft.repository.ThongSoDoRepository;
import vn.nvsoft.service.ThongSoDoService;
import vn.nvsoft.service.dto.ThongSoDoDTO;
import vn.nvsoft.service.mapper.ThongSoDoMapper;
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
 * Integration tests for the {@link ThongSoDoResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class ThongSoDoResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    @Autowired
    private ThongSoDoRepository thongSoDoRepository;

    @Autowired
    private ThongSoDoMapper thongSoDoMapper;

    @Autowired
    private ThongSoDoService thongSoDoService;

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

    private MockMvc restThongSoDoMockMvc;

    private ThongSoDo thongSoDo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ThongSoDoResource thongSoDoResource = new ThongSoDoResource(thongSoDoService);
        this.restThongSoDoMockMvc = MockMvcBuilders.standaloneSetup(thongSoDoResource)
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
    public static ThongSoDo createEntity(EntityManager em) {
        ThongSoDo thongSoDo = new ThongSoDo()
            .code(DEFAULT_CODE)
            .ten(DEFAULT_TEN);
        return thongSoDo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ThongSoDo createUpdatedEntity(EntityManager em) {
        ThongSoDo thongSoDo = new ThongSoDo()
            .code(UPDATED_CODE)
            .ten(UPDATED_TEN);
        return thongSoDo;
    }

    @BeforeEach
    public void initTest() {
        thongSoDo = createEntity(em);
    }

    @Test
    @Transactional
    public void createThongSoDo() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoRepository.findAll().size();

        // Create the ThongSoDo
        ThongSoDoDTO thongSoDoDTO = thongSoDoMapper.toDto(thongSoDo);
        restThongSoDoMockMvc.perform(post("/api/thong-so-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoDTO)))
            .andExpect(status().isCreated());

        // Validate the ThongSoDo in the database
        List<ThongSoDo> thongSoDoList = thongSoDoRepository.findAll();
        assertThat(thongSoDoList).hasSize(databaseSizeBeforeCreate + 1);
        ThongSoDo testThongSoDo = thongSoDoList.get(thongSoDoList.size() - 1);
        assertThat(testThongSoDo.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testThongSoDo.getTen()).isEqualTo(DEFAULT_TEN);
    }

    @Test
    @Transactional
    public void createThongSoDoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = thongSoDoRepository.findAll().size();

        // Create the ThongSoDo with an existing ID
        thongSoDo.setId(1L);
        ThongSoDoDTO thongSoDoDTO = thongSoDoMapper.toDto(thongSoDo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restThongSoDoMockMvc.perform(post("/api/thong-so-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDo in the database
        List<ThongSoDo> thongSoDoList = thongSoDoRepository.findAll();
        assertThat(thongSoDoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllThongSoDos() throws Exception {
        // Initialize the database
        thongSoDoRepository.saveAndFlush(thongSoDo);

        // Get all the thongSoDoList
        restThongSoDoMockMvc.perform(get("/api/thong-so-dos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(thongSoDo.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN)));
    }
    
    @Test
    @Transactional
    public void getThongSoDo() throws Exception {
        // Initialize the database
        thongSoDoRepository.saveAndFlush(thongSoDo);

        // Get the thongSoDo
        restThongSoDoMockMvc.perform(get("/api/thong-so-dos/{id}", thongSoDo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(thongSoDo.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN));
    }

    @Test
    @Transactional
    public void getNonExistingThongSoDo() throws Exception {
        // Get the thongSoDo
        restThongSoDoMockMvc.perform(get("/api/thong-so-dos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThongSoDo() throws Exception {
        // Initialize the database
        thongSoDoRepository.saveAndFlush(thongSoDo);

        int databaseSizeBeforeUpdate = thongSoDoRepository.findAll().size();

        // Update the thongSoDo
        ThongSoDo updatedThongSoDo = thongSoDoRepository.findById(thongSoDo.getId()).get();
        // Disconnect from session so that the updates on updatedThongSoDo are not directly saved in db
        em.detach(updatedThongSoDo);
        updatedThongSoDo
            .code(UPDATED_CODE)
            .ten(UPDATED_TEN);
        ThongSoDoDTO thongSoDoDTO = thongSoDoMapper.toDto(updatedThongSoDo);

        restThongSoDoMockMvc.perform(put("/api/thong-so-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoDTO)))
            .andExpect(status().isOk());

        // Validate the ThongSoDo in the database
        List<ThongSoDo> thongSoDoList = thongSoDoRepository.findAll();
        assertThat(thongSoDoList).hasSize(databaseSizeBeforeUpdate);
        ThongSoDo testThongSoDo = thongSoDoList.get(thongSoDoList.size() - 1);
        assertThat(testThongSoDo.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testThongSoDo.getTen()).isEqualTo(UPDATED_TEN);
    }

    @Test
    @Transactional
    public void updateNonExistingThongSoDo() throws Exception {
        int databaseSizeBeforeUpdate = thongSoDoRepository.findAll().size();

        // Create the ThongSoDo
        ThongSoDoDTO thongSoDoDTO = thongSoDoMapper.toDto(thongSoDo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restThongSoDoMockMvc.perform(put("/api/thong-so-dos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(thongSoDoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ThongSoDo in the database
        List<ThongSoDo> thongSoDoList = thongSoDoRepository.findAll();
        assertThat(thongSoDoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteThongSoDo() throws Exception {
        // Initialize the database
        thongSoDoRepository.saveAndFlush(thongSoDo);

        int databaseSizeBeforeDelete = thongSoDoRepository.findAll().size();

        // Delete the thongSoDo
        restThongSoDoMockMvc.perform(delete("/api/thong-so-dos/{id}", thongSoDo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ThongSoDo> thongSoDoList = thongSoDoRepository.findAll();
        assertThat(thongSoDoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
