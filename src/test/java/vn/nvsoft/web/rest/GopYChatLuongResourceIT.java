package vn.nvsoft.web.rest;

import vn.nvsoft.CtApp;
import vn.nvsoft.domain.GopYChatLuong;
import vn.nvsoft.repository.GopYChatLuongRepository;
import vn.nvsoft.service.GopYChatLuongService;
import vn.nvsoft.service.dto.GopYChatLuongDTO;
import vn.nvsoft.service.mapper.GopYChatLuongMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GopYChatLuongResource} REST controller.
 */
@SpringBootTest(classes = CtApp.class)
public class GopYChatLuongResourceIT {

    private static final Integer DEFAULT_CUS_ID = 1;
    private static final Integer UPDATED_CUS_ID = 2;

    private static final String DEFAULT_CUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUS_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHUYEN_ID = 1;
    private static final Integer UPDATED_CHUYEN_ID = 2;

    private static final String DEFAULT_CHUYEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHUYEN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MA_HANG = "AAAAAAAAAA";
    private static final String UPDATED_MA_HANG = "BBBBBBBBBB";

    private static final String DEFAULT_PO = "AAAAAAAAAA";
    private static final String UPDATED_PO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PO_ID = 1;
    private static final Integer UPDATED_PO_ID = 2;

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FIRST_CUT = false;
    private static final Boolean UPDATED_FIRST_CUT = true;

    private static final Boolean DEFAULT_INLINE = false;
    private static final Boolean UPDATED_INLINE = true;

    private static final Integer DEFAULT_LAN_BAN_HANH = 1;
    private static final Integer UPDATED_LAN_BAN_HANH = 2;

    private static final Instant DEFAULT_NGAY_BAO_CAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_BAO_CAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NGUOI_TAO = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_TAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_USER_ID = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_USER_ID = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_NGAY_TAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NGAY_TAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TEN_QUAN_LY_XUONG = "AAAAAAAAAA";
    private static final String UPDATED_TEN_QUAN_LY_XUONG = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_KI_THUAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_KI_THUAT = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_QAN_LY_CAT = "AAAAAAAAAA";
    private static final String UPDATED_TEN_QAN_LY_CAT = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_QA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_QA = "BBBBBBBBBB";

    @Autowired
    private GopYChatLuongRepository gopYChatLuongRepository;

    @Autowired
    private GopYChatLuongMapper gopYChatLuongMapper;

    @Autowired
    private GopYChatLuongService gopYChatLuongService;

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

    private MockMvc restGopYChatLuongMockMvc;

    private GopYChatLuong gopYChatLuong;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GopYChatLuongResource gopYChatLuongResource = new GopYChatLuongResource(gopYChatLuongService);
        this.restGopYChatLuongMockMvc = MockMvcBuilders.standaloneSetup(gopYChatLuongResource)
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
    public static GopYChatLuong createEntity(EntityManager em) {
        GopYChatLuong gopYChatLuong = new GopYChatLuong()
            .cusId(DEFAULT_CUS_ID)
            .cusName(DEFAULT_CUS_NAME)
            .chuyenId(DEFAULT_CHUYEN_ID)
            .chuyenName(DEFAULT_CHUYEN_NAME)
            .maHang(DEFAULT_MA_HANG)
            .po(DEFAULT_PO)
            .poId(DEFAULT_PO_ID)
            .color(DEFAULT_COLOR)
            .size(DEFAULT_SIZE)
            .firstCut(DEFAULT_FIRST_CUT)
            .inline(DEFAULT_INLINE)
            .lanBanHanh(DEFAULT_LAN_BAN_HANH)
            .ngayBaoCao(DEFAULT_NGAY_BAO_CAO)
            .nguoiTao(DEFAULT_NGUOI_TAO)
            .userId(DEFAULT_USER_ID)
            .ngayTao(DEFAULT_NGAY_TAO)
            .tenQuanLyXuong(DEFAULT_TEN_QUAN_LY_XUONG)
            .tenKiThuat(DEFAULT_TEN_KI_THUAT)
            .tenQanLyCat(DEFAULT_TEN_QAN_LY_CAT)
            .tenQA(DEFAULT_TEN_QA);
        return gopYChatLuong;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GopYChatLuong createUpdatedEntity(EntityManager em) {
        GopYChatLuong gopYChatLuong = new GopYChatLuong()
            .cusId(UPDATED_CUS_ID)
            .cusName(UPDATED_CUS_NAME)
            .chuyenId(UPDATED_CHUYEN_ID)
            .chuyenName(UPDATED_CHUYEN_NAME)
            .maHang(UPDATED_MA_HANG)
            .po(UPDATED_PO)
            .poId(UPDATED_PO_ID)
            .color(UPDATED_COLOR)
            .size(UPDATED_SIZE)
            .firstCut(UPDATED_FIRST_CUT)
            .inline(UPDATED_INLINE)
            .lanBanHanh(UPDATED_LAN_BAN_HANH)
            .ngayBaoCao(UPDATED_NGAY_BAO_CAO)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .userId(UPDATED_USER_ID)
            .ngayTao(UPDATED_NGAY_TAO)
            .tenQuanLyXuong(UPDATED_TEN_QUAN_LY_XUONG)
            .tenKiThuat(UPDATED_TEN_KI_THUAT)
            .tenQanLyCat(UPDATED_TEN_QAN_LY_CAT)
            .tenQA(UPDATED_TEN_QA);
        return gopYChatLuong;
    }

    @BeforeEach
    public void initTest() {
        gopYChatLuong = createEntity(em);
    }

    @Test
    @Transactional
    public void createGopYChatLuong() throws Exception {
        int databaseSizeBeforeCreate = gopYChatLuongRepository.findAll().size();

        // Create the GopYChatLuong
        GopYChatLuongDTO gopYChatLuongDTO = gopYChatLuongMapper.toDto(gopYChatLuong);
        restGopYChatLuongMockMvc.perform(post("/api/gop-y-chat-luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gopYChatLuongDTO)))
            .andExpect(status().isCreated());

        // Validate the GopYChatLuong in the database
        List<GopYChatLuong> gopYChatLuongList = gopYChatLuongRepository.findAll();
        assertThat(gopYChatLuongList).hasSize(databaseSizeBeforeCreate + 1);
        GopYChatLuong testGopYChatLuong = gopYChatLuongList.get(gopYChatLuongList.size() - 1);
        assertThat(testGopYChatLuong.getCusId()).isEqualTo(DEFAULT_CUS_ID);
        assertThat(testGopYChatLuong.getCusName()).isEqualTo(DEFAULT_CUS_NAME);
        assertThat(testGopYChatLuong.getChuyenId()).isEqualTo(DEFAULT_CHUYEN_ID);
        assertThat(testGopYChatLuong.getChuyenName()).isEqualTo(DEFAULT_CHUYEN_NAME);
        assertThat(testGopYChatLuong.getMaHang()).isEqualTo(DEFAULT_MA_HANG);
        assertThat(testGopYChatLuong.getPo()).isEqualTo(DEFAULT_PO);
        assertThat(testGopYChatLuong.getPoId()).isEqualTo(DEFAULT_PO_ID);
        assertThat(testGopYChatLuong.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testGopYChatLuong.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testGopYChatLuong.isFirstCut()).isEqualTo(DEFAULT_FIRST_CUT);
        assertThat(testGopYChatLuong.isInline()).isEqualTo(DEFAULT_INLINE);
        assertThat(testGopYChatLuong.getLanBanHanh()).isEqualTo(DEFAULT_LAN_BAN_HANH);
        assertThat(testGopYChatLuong.getNgayBaoCao()).isEqualTo(DEFAULT_NGAY_BAO_CAO);
        assertThat(testGopYChatLuong.getNguoiTao()).isEqualTo(DEFAULT_NGUOI_TAO);
        assertThat(testGopYChatLuong.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGopYChatLuong.getNgayTao()).isEqualTo(DEFAULT_NGAY_TAO);
        assertThat(testGopYChatLuong.getTenQuanLyXuong()).isEqualTo(DEFAULT_TEN_QUAN_LY_XUONG);
        assertThat(testGopYChatLuong.getTenKiThuat()).isEqualTo(DEFAULT_TEN_KI_THUAT);
        assertThat(testGopYChatLuong.getTenQanLyCat()).isEqualTo(DEFAULT_TEN_QAN_LY_CAT);
        assertThat(testGopYChatLuong.getTenQA()).isEqualTo(DEFAULT_TEN_QA);
    }

    @Test
    @Transactional
    public void createGopYChatLuongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gopYChatLuongRepository.findAll().size();

        // Create the GopYChatLuong with an existing ID
        gopYChatLuong.setId(1L);
        GopYChatLuongDTO gopYChatLuongDTO = gopYChatLuongMapper.toDto(gopYChatLuong);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGopYChatLuongMockMvc.perform(post("/api/gop-y-chat-luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gopYChatLuongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GopYChatLuong in the database
        List<GopYChatLuong> gopYChatLuongList = gopYChatLuongRepository.findAll();
        assertThat(gopYChatLuongList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGopYChatLuongs() throws Exception {
        // Initialize the database
        gopYChatLuongRepository.saveAndFlush(gopYChatLuong);

        // Get all the gopYChatLuongList
        restGopYChatLuongMockMvc.perform(get("/api/gop-y-chat-luongs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gopYChatLuong.getId().intValue())))
            .andExpect(jsonPath("$.[*].cusId").value(hasItem(DEFAULT_CUS_ID)))
            .andExpect(jsonPath("$.[*].cusName").value(hasItem(DEFAULT_CUS_NAME)))
            .andExpect(jsonPath("$.[*].chuyenId").value(hasItem(DEFAULT_CHUYEN_ID)))
            .andExpect(jsonPath("$.[*].chuyenName").value(hasItem(DEFAULT_CHUYEN_NAME)))
            .andExpect(jsonPath("$.[*].maHang").value(hasItem(DEFAULT_MA_HANG)))
            .andExpect(jsonPath("$.[*].po").value(hasItem(DEFAULT_PO)))
            .andExpect(jsonPath("$.[*].poId").value(hasItem(DEFAULT_PO_ID)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].firstCut").value(hasItem(DEFAULT_FIRST_CUT.booleanValue())))
            .andExpect(jsonPath("$.[*].inline").value(hasItem(DEFAULT_INLINE.booleanValue())))
            .andExpect(jsonPath("$.[*].lanBanHanh").value(hasItem(DEFAULT_LAN_BAN_HANH)))
            .andExpect(jsonPath("$.[*].ngayBaoCao").value(hasItem(DEFAULT_NGAY_BAO_CAO.toString())))
            .andExpect(jsonPath("$.[*].nguoiTao").value(hasItem(DEFAULT_NGUOI_TAO)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].ngayTao").value(hasItem(DEFAULT_NGAY_TAO.toString())))
            .andExpect(jsonPath("$.[*].tenQuanLyXuong").value(hasItem(DEFAULT_TEN_QUAN_LY_XUONG)))
            .andExpect(jsonPath("$.[*].tenKiThuat").value(hasItem(DEFAULT_TEN_KI_THUAT)))
            .andExpect(jsonPath("$.[*].tenQanLyCat").value(hasItem(DEFAULT_TEN_QAN_LY_CAT)))
            .andExpect(jsonPath("$.[*].tenQA").value(hasItem(DEFAULT_TEN_QA)));
    }
    
    @Test
    @Transactional
    public void getGopYChatLuong() throws Exception {
        // Initialize the database
        gopYChatLuongRepository.saveAndFlush(gopYChatLuong);

        // Get the gopYChatLuong
        restGopYChatLuongMockMvc.perform(get("/api/gop-y-chat-luongs/{id}", gopYChatLuong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gopYChatLuong.getId().intValue()))
            .andExpect(jsonPath("$.cusId").value(DEFAULT_CUS_ID))
            .andExpect(jsonPath("$.cusName").value(DEFAULT_CUS_NAME))
            .andExpect(jsonPath("$.chuyenId").value(DEFAULT_CHUYEN_ID))
            .andExpect(jsonPath("$.chuyenName").value(DEFAULT_CHUYEN_NAME))
            .andExpect(jsonPath("$.maHang").value(DEFAULT_MA_HANG))
            .andExpect(jsonPath("$.po").value(DEFAULT_PO))
            .andExpect(jsonPath("$.poId").value(DEFAULT_PO_ID))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.firstCut").value(DEFAULT_FIRST_CUT.booleanValue()))
            .andExpect(jsonPath("$.inline").value(DEFAULT_INLINE.booleanValue()))
            .andExpect(jsonPath("$.lanBanHanh").value(DEFAULT_LAN_BAN_HANH))
            .andExpect(jsonPath("$.ngayBaoCao").value(DEFAULT_NGAY_BAO_CAO.toString()))
            .andExpect(jsonPath("$.nguoiTao").value(DEFAULT_NGUOI_TAO))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.ngayTao").value(DEFAULT_NGAY_TAO.toString()))
            .andExpect(jsonPath("$.tenQuanLyXuong").value(DEFAULT_TEN_QUAN_LY_XUONG))
            .andExpect(jsonPath("$.tenKiThuat").value(DEFAULT_TEN_KI_THUAT))
            .andExpect(jsonPath("$.tenQanLyCat").value(DEFAULT_TEN_QAN_LY_CAT))
            .andExpect(jsonPath("$.tenQA").value(DEFAULT_TEN_QA));
    }

    @Test
    @Transactional
    public void getNonExistingGopYChatLuong() throws Exception {
        // Get the gopYChatLuong
        restGopYChatLuongMockMvc.perform(get("/api/gop-y-chat-luongs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGopYChatLuong() throws Exception {
        // Initialize the database
        gopYChatLuongRepository.saveAndFlush(gopYChatLuong);

        int databaseSizeBeforeUpdate = gopYChatLuongRepository.findAll().size();

        // Update the gopYChatLuong
        GopYChatLuong updatedGopYChatLuong = gopYChatLuongRepository.findById(gopYChatLuong.getId()).get();
        // Disconnect from session so that the updates on updatedGopYChatLuong are not directly saved in db
        em.detach(updatedGopYChatLuong);
        updatedGopYChatLuong
            .cusId(UPDATED_CUS_ID)
            .cusName(UPDATED_CUS_NAME)
            .chuyenId(UPDATED_CHUYEN_ID)
            .chuyenName(UPDATED_CHUYEN_NAME)
            .maHang(UPDATED_MA_HANG)
            .po(UPDATED_PO)
            .poId(UPDATED_PO_ID)
            .color(UPDATED_COLOR)
            .size(UPDATED_SIZE)
            .firstCut(UPDATED_FIRST_CUT)
            .inline(UPDATED_INLINE)
            .lanBanHanh(UPDATED_LAN_BAN_HANH)
            .ngayBaoCao(UPDATED_NGAY_BAO_CAO)
            .nguoiTao(UPDATED_NGUOI_TAO)
            .userId(UPDATED_USER_ID)
            .ngayTao(UPDATED_NGAY_TAO)
            .tenQuanLyXuong(UPDATED_TEN_QUAN_LY_XUONG)
            .tenKiThuat(UPDATED_TEN_KI_THUAT)
            .tenQanLyCat(UPDATED_TEN_QAN_LY_CAT)
            .tenQA(UPDATED_TEN_QA);
        GopYChatLuongDTO gopYChatLuongDTO = gopYChatLuongMapper.toDto(updatedGopYChatLuong);

        restGopYChatLuongMockMvc.perform(put("/api/gop-y-chat-luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gopYChatLuongDTO)))
            .andExpect(status().isOk());

        // Validate the GopYChatLuong in the database
        List<GopYChatLuong> gopYChatLuongList = gopYChatLuongRepository.findAll();
        assertThat(gopYChatLuongList).hasSize(databaseSizeBeforeUpdate);
        GopYChatLuong testGopYChatLuong = gopYChatLuongList.get(gopYChatLuongList.size() - 1);
        assertThat(testGopYChatLuong.getCusId()).isEqualTo(UPDATED_CUS_ID);
        assertThat(testGopYChatLuong.getCusName()).isEqualTo(UPDATED_CUS_NAME);
        assertThat(testGopYChatLuong.getChuyenId()).isEqualTo(UPDATED_CHUYEN_ID);
        assertThat(testGopYChatLuong.getChuyenName()).isEqualTo(UPDATED_CHUYEN_NAME);
        assertThat(testGopYChatLuong.getMaHang()).isEqualTo(UPDATED_MA_HANG);
        assertThat(testGopYChatLuong.getPo()).isEqualTo(UPDATED_PO);
        assertThat(testGopYChatLuong.getPoId()).isEqualTo(UPDATED_PO_ID);
        assertThat(testGopYChatLuong.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testGopYChatLuong.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testGopYChatLuong.isFirstCut()).isEqualTo(UPDATED_FIRST_CUT);
        assertThat(testGopYChatLuong.isInline()).isEqualTo(UPDATED_INLINE);
        assertThat(testGopYChatLuong.getLanBanHanh()).isEqualTo(UPDATED_LAN_BAN_HANH);
        assertThat(testGopYChatLuong.getNgayBaoCao()).isEqualTo(UPDATED_NGAY_BAO_CAO);
        assertThat(testGopYChatLuong.getNguoiTao()).isEqualTo(UPDATED_NGUOI_TAO);
        assertThat(testGopYChatLuong.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGopYChatLuong.getNgayTao()).isEqualTo(UPDATED_NGAY_TAO);
        assertThat(testGopYChatLuong.getTenQuanLyXuong()).isEqualTo(UPDATED_TEN_QUAN_LY_XUONG);
        assertThat(testGopYChatLuong.getTenKiThuat()).isEqualTo(UPDATED_TEN_KI_THUAT);
        assertThat(testGopYChatLuong.getTenQanLyCat()).isEqualTo(UPDATED_TEN_QAN_LY_CAT);
        assertThat(testGopYChatLuong.getTenQA()).isEqualTo(UPDATED_TEN_QA);
    }

    @Test
    @Transactional
    public void updateNonExistingGopYChatLuong() throws Exception {
        int databaseSizeBeforeUpdate = gopYChatLuongRepository.findAll().size();

        // Create the GopYChatLuong
        GopYChatLuongDTO gopYChatLuongDTO = gopYChatLuongMapper.toDto(gopYChatLuong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGopYChatLuongMockMvc.perform(put("/api/gop-y-chat-luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gopYChatLuongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GopYChatLuong in the database
        List<GopYChatLuong> gopYChatLuongList = gopYChatLuongRepository.findAll();
        assertThat(gopYChatLuongList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGopYChatLuong() throws Exception {
        // Initialize the database
        gopYChatLuongRepository.saveAndFlush(gopYChatLuong);

        int databaseSizeBeforeDelete = gopYChatLuongRepository.findAll().size();

        // Delete the gopYChatLuong
        restGopYChatLuongMockMvc.perform(delete("/api/gop-y-chat-luongs/{id}", gopYChatLuong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GopYChatLuong> gopYChatLuongList = gopYChatLuongRepository.findAll();
        assertThat(gopYChatLuongList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
