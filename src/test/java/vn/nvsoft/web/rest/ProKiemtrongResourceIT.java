package vn.nvsoft.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vn.nvsoft.web.rest.TestUtil.createFormattingConversionService;

import java.util.List;
import javax.persistence.EntityManager;
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
import vn.nvsoft.CtApp;
import vn.nvsoft.domain.ProCumLoi;
import vn.nvsoft.domain.ProKiemtrongChiTietLoi;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.repository.ProKiemtrongChiTietLoiRepository;
import vn.nvsoft.repository.ProKiemtrongSanPhamRepository;
import vn.nvsoft.service.ProKiemtrongService;
import vn.nvsoft.service.dto.CreateKiemTrongChiTietLoiRequest;
import vn.nvsoft.web.rest.errors.ExceptionTranslator;

@SpringBootTest(classes = CtApp.class)
public class ProKiemtrongResourceIT {

    private static final Long CHECKPOINT_ID = 1L;
    private static final Long KHSX_ID = 1L;

    @Autowired
    private ProKiemtrongChiTietLoiRepository chiTietLoiRepository;

    @Autowired
    private ProKiemtrongSanPhamRepository sanPhamRepository;

    @Autowired
    private ProKiemtrongService proKiemtrongService;

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

    private MockMvc restProKiemtrongMockMvc;

    private Long proLoiId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProKiemtrongResource resource = new ProKiemtrongResource(proKiemtrongService);
        this.restProKiemtrongMockMvc = MockMvcBuilders.standaloneSetup(resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator)
            .build();
        proLoiId = createProLoi(em).getId();
    }

    private static ProLoi createProLoi(EntityManager em) {
        ProCumLoi cumLoi = new ProCumLoi().ten("KT_TEST_CUM");
        em.persist(cumLoi);
        em.flush();
        ProLoi proLoi = new ProLoi()
            .maLoi("KT_TEST_LOI")
            .tenLoi("KT Test Loi")
            .stt(1);
        proLoi.setProCumLoi(cumLoi);
        em.persist(proLoi);
        em.flush();
        return proLoi;
    }

    @Test
    @Transactional
    public void createChiTietLoi_insertsNChiTietLoiAndNSanPhamWithNullPosition() throws Exception {
        int chiTietBefore = chiTietLoiRepository.findAll().size();
        int sanPhamBefore = sanPhamRepository.countLoiByCheckpointAndKhsx(CHECKPOINT_ID, KHSX_ID);

        CreateKiemTrongChiTietLoiRequest req = new CreateKiemTrongChiTietLoiRequest();
        req.setCheckpointId(CHECKPOINT_ID);
        req.setKhsxId(KHSX_ID);
        req.setProLoiId(proLoiId);
        req.setMaLoi("TEST");
        req.setQuantity(3);
        req.setRfidDisplayName("(01)123(21)ABC");
        req.setRfidHex("3038313233");

        restProKiemtrongMockMvc.perform(post("/api/pro-kiem-trong/chi-tiet-lois")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(req)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].dx").isEmpty())
            .andExpect(jsonPath("$[0].dy").isEmpty());

        assertThat(chiTietLoiRepository.findAll()).hasSize(chiTietBefore + 3);
        assertThat(sanPhamRepository.countLoiByCheckpointAndKhsx(CHECKPOINT_ID, KHSX_ID))
            .isEqualTo(sanPhamBefore + 3);

        List<ProKiemtrongChiTietLoi> created = chiTietLoiRepository
            .findByProCheckpointIdAndProKeHoachSanXuatIdOrderByThoiGianDesc(CHECKPOINT_ID, KHSX_ID);
        assertThat(created.get(0).getDx()).isNull();
        assertThat(created.get(0).getDy()).isNull();
    }
}
