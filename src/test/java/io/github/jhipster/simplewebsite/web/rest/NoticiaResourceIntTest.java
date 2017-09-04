package io.github.jhipster.simplewebsite.web.rest;

import io.github.jhipster.simplewebsite.SimplewebsiteApp;

import io.github.jhipster.simplewebsite.domain.Noticia;
import io.github.jhipster.simplewebsite.repository.NoticiaRepository;
import io.github.jhipster.simplewebsite.service.NoticiaService;
import io.github.jhipster.simplewebsite.service.dto.NoticiaDTO;
import io.github.jhipster.simplewebsite.service.mapper.NoticiaMapper;
import io.github.jhipster.simplewebsite.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NoticiaResource REST controller.
 *
 * @see NoticiaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimplewebsiteApp.class)
public class NoticiaResourceIntTest {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDO = "BBBBBBBBBB";

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private NoticiaMapper noticiaMapper;

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restNoticiaMockMvc;

    private Noticia noticia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NoticiaResource noticiaResource = new NoticiaResource(noticiaService);
        this.restNoticiaMockMvc = MockMvcBuilders.standaloneSetup(noticiaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Noticia createEntity() {
        Noticia noticia = new Noticia()
            .fecha(DEFAULT_FECHA)
            .titulo(DEFAULT_TITULO)
            .contenido(DEFAULT_CONTENIDO);
        return noticia;
    }

    @Before
    public void initTest() {
        noticiaRepository.deleteAll();
        noticia = createEntity();
    }

    @Test
    public void createNoticia() throws Exception {
        int databaseSizeBeforeCreate = noticiaRepository.findAll().size();

        // Create the Noticia
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);
        restNoticiaMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isCreated());

        // Validate the Noticia in the database
        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeCreate + 1);
        Noticia testNoticia = noticiaList.get(noticiaList.size() - 1);
        assertThat(testNoticia.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testNoticia.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testNoticia.getContenido()).isEqualTo(DEFAULT_CONTENIDO);
    }

    @Test
    public void createNoticiaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noticiaRepository.findAll().size();

        // Create the Noticia with an existing ID
        noticia.setId("existing_id");
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoticiaMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiaRepository.findAll().size();
        // set the field null
        noticia.setFecha(null);

        // Create the Noticia, which fails.
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);

        restNoticiaMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isBadRequest());

        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiaRepository.findAll().size();
        // set the field null
        noticia.setTitulo(null);

        // Create the Noticia, which fails.
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);

        restNoticiaMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isBadRequest());

        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkContenidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = noticiaRepository.findAll().size();
        // set the field null
        noticia.setContenido(null);

        // Create the Noticia, which fails.
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);

        restNoticiaMockMvc.perform(post("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isBadRequest());

        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllNoticias() throws Exception {
        // Initialize the database
        noticiaRepository.save(noticia);

        // Get all the noticiaList
        restNoticiaMockMvc.perform(get("/api/noticias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noticia.getId())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())));
    }

    @Test
    public void getNoticia() throws Exception {
        // Initialize the database
        noticiaRepository.save(noticia);

        // Get the noticia
        restNoticiaMockMvc.perform(get("/api/noticias/{id}", noticia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noticia.getId()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.contenido").value(DEFAULT_CONTENIDO.toString()));
    }

    @Test
    public void getNonExistingNoticia() throws Exception {
        // Get the noticia
        restNoticiaMockMvc.perform(get("/api/noticias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateNoticia() throws Exception {
        // Initialize the database
        noticiaRepository.save(noticia);
        int databaseSizeBeforeUpdate = noticiaRepository.findAll().size();

        // Update the noticia
        Noticia updatedNoticia = noticiaRepository.findOne(noticia.getId());
        updatedNoticia
            .fecha(UPDATED_FECHA)
            .titulo(UPDATED_TITULO)
            .contenido(UPDATED_CONTENIDO);
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(updatedNoticia);

        restNoticiaMockMvc.perform(put("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isOk());

        // Validate the Noticia in the database
        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeUpdate);
        Noticia testNoticia = noticiaList.get(noticiaList.size() - 1);
        assertThat(testNoticia.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testNoticia.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testNoticia.getContenido()).isEqualTo(UPDATED_CONTENIDO);
    }

    @Test
    public void updateNonExistingNoticia() throws Exception {
        int databaseSizeBeforeUpdate = noticiaRepository.findAll().size();

        // Create the Noticia
        NoticiaDTO noticiaDTO = noticiaMapper.toDto(noticia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNoticiaMockMvc.perform(put("/api/noticias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noticiaDTO)))
            .andExpect(status().isCreated());

        // Validate the Noticia in the database
        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteNoticia() throws Exception {
        // Initialize the database
        noticiaRepository.save(noticia);
        int databaseSizeBeforeDelete = noticiaRepository.findAll().size();

        // Get the noticia
        restNoticiaMockMvc.perform(delete("/api/noticias/{id}", noticia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Noticia> noticiaList = noticiaRepository.findAll();
        assertThat(noticiaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Noticia.class);
        Noticia noticia1 = new Noticia();
        noticia1.setId("id1");
        Noticia noticia2 = new Noticia();
        noticia2.setId(noticia1.getId());
        assertThat(noticia1).isEqualTo(noticia2);
        noticia2.setId("id2");
        assertThat(noticia1).isNotEqualTo(noticia2);
        noticia1.setId(null);
        assertThat(noticia1).isNotEqualTo(noticia2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NoticiaDTO.class);
        NoticiaDTO noticiaDTO1 = new NoticiaDTO();
        noticiaDTO1.setId("id1");
        NoticiaDTO noticiaDTO2 = new NoticiaDTO();
        assertThat(noticiaDTO1).isNotEqualTo(noticiaDTO2);
        noticiaDTO2.setId(noticiaDTO1.getId());
        assertThat(noticiaDTO1).isEqualTo(noticiaDTO2);
        noticiaDTO2.setId("id2");
        assertThat(noticiaDTO1).isNotEqualTo(noticiaDTO2);
        noticiaDTO1.setId(null);
        assertThat(noticiaDTO1).isNotEqualTo(noticiaDTO2);
    }
}
