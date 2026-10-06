package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.GroupTopic;
import vn.nvsoft.repository.GroupTopicRepository;
import vn.nvsoft.service.GroupTopicService;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mapper.GroupTopicMapper;
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

import vn.nvsoft.domain.enumeration.TopicType;
import vn.nvsoft.domain.enumeration.GroupButtonType;
/**
 * Integration tests for the {@link GroupTopicResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class GroupTopicResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final TopicType DEFAULT_TOPIC_TYPE = TopicType.CMND;
    private static final TopicType UPDATED_TOPIC_TYPE = TopicType.TELE;

    private static final GroupButtonType DEFAULT_TYPE = GroupButtonType.LIGHT;
    private static final GroupButtonType UPDATED_TYPE = GroupButtonType.SEWING;

    @Autowired
    private GroupTopicRepository groupTopicRepository;

    @Autowired
    private GroupTopicMapper groupTopicMapper;

    @Autowired
    private GroupTopicService groupTopicService;

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

    private MockMvc restGroupTopicMockMvc;

    private GroupTopic groupTopic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupTopicResource groupTopicResource = new GroupTopicResource(groupTopicService);
        this.restGroupTopicMockMvc = MockMvcBuilders.standaloneSetup(groupTopicResource)
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
    public static GroupTopic createEntity(EntityManager em) {
        GroupTopic groupTopic = new GroupTopic()
            .name(DEFAULT_NAME)
            .topicType(DEFAULT_TOPIC_TYPE)
            .type(DEFAULT_TYPE);
        return groupTopic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupTopic createUpdatedEntity(EntityManager em) {
        GroupTopic groupTopic = new GroupTopic()
            .name(UPDATED_NAME)
            .topicType(UPDATED_TOPIC_TYPE)
            .type(UPDATED_TYPE);
        return groupTopic;
    }

    @BeforeEach
    public void initTest() {
        groupTopic = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupTopic() throws Exception {
        int databaseSizeBeforeCreate = groupTopicRepository.findAll().size();

        // Create the GroupTopic
        GroupTopicDTO groupTopicDTO = groupTopicMapper.toDto(groupTopic);
        restGroupTopicMockMvc.perform(post("/api/group-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTopicDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupTopic in the database
        List<GroupTopic> groupTopicList = groupTopicRepository.findAll();
        assertThat(groupTopicList).hasSize(databaseSizeBeforeCreate + 1);
        GroupTopic testGroupTopic = groupTopicList.get(groupTopicList.size() - 1);
        assertThat(testGroupTopic.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGroupTopic.getTopicType()).isEqualTo(DEFAULT_TOPIC_TYPE);
        assertThat(testGroupTopic.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createGroupTopicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupTopicRepository.findAll().size();

        // Create the GroupTopic with an existing ID
        groupTopic.setId(1L);
        GroupTopicDTO groupTopicDTO = groupTopicMapper.toDto(groupTopic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupTopicMockMvc.perform(post("/api/group-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupTopic in the database
        List<GroupTopic> groupTopicList = groupTopicRepository.findAll();
        assertThat(groupTopicList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGroupTopics() throws Exception {
        // Initialize the database
        groupTopicRepository.saveAndFlush(groupTopic);

        // Get all the groupTopicList
        restGroupTopicMockMvc.perform(get("/api/group-topics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupTopic.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].topicType").value(hasItem(DEFAULT_TOPIC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getGroupTopic() throws Exception {
        // Initialize the database
        groupTopicRepository.saveAndFlush(groupTopic);

        // Get the groupTopic
        restGroupTopicMockMvc.perform(get("/api/group-topics/{id}", groupTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupTopic.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.topicType").value(DEFAULT_TOPIC_TYPE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGroupTopic() throws Exception {
        // Get the groupTopic
        restGroupTopicMockMvc.perform(get("/api/group-topics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupTopic() throws Exception {
        // Initialize the database
        groupTopicRepository.saveAndFlush(groupTopic);

        int databaseSizeBeforeUpdate = groupTopicRepository.findAll().size();

        // Update the groupTopic
        GroupTopic updatedGroupTopic = groupTopicRepository.findById(groupTopic.getId()).get();
        // Disconnect from session so that the updates on updatedGroupTopic are not directly saved in db
        em.detach(updatedGroupTopic);
        updatedGroupTopic
            .name(UPDATED_NAME)
            .topicType(UPDATED_TOPIC_TYPE)
            .type(UPDATED_TYPE);
        GroupTopicDTO groupTopicDTO = groupTopicMapper.toDto(updatedGroupTopic);

        restGroupTopicMockMvc.perform(put("/api/group-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTopicDTO)))
            .andExpect(status().isOk());

        // Validate the GroupTopic in the database
        List<GroupTopic> groupTopicList = groupTopicRepository.findAll();
        assertThat(groupTopicList).hasSize(databaseSizeBeforeUpdate);
        GroupTopic testGroupTopic = groupTopicList.get(groupTopicList.size() - 1);
        assertThat(testGroupTopic.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGroupTopic.getTopicType()).isEqualTo(UPDATED_TOPIC_TYPE);
        assertThat(testGroupTopic.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupTopic() throws Exception {
        int databaseSizeBeforeUpdate = groupTopicRepository.findAll().size();

        // Create the GroupTopic
        GroupTopicDTO groupTopicDTO = groupTopicMapper.toDto(groupTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupTopicMockMvc.perform(put("/api/group-topics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupTopicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupTopic in the database
        List<GroupTopic> groupTopicList = groupTopicRepository.findAll();
        assertThat(groupTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupTopic() throws Exception {
        // Initialize the database
        groupTopicRepository.saveAndFlush(groupTopic);

        int databaseSizeBeforeDelete = groupTopicRepository.findAll().size();

        // Delete the groupTopic
        restGroupTopicMockMvc.perform(delete("/api/group-topics/{id}", groupTopic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupTopic> groupTopicList = groupTopicRepository.findAll();
        assertThat(groupTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
