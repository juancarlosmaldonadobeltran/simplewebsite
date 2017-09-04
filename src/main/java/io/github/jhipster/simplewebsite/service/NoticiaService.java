package io.github.jhipster.simplewebsite.service;

import io.github.jhipster.simplewebsite.service.dto.NoticiaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Noticia.
 */
public interface NoticiaService {

    /**
     * Save a noticia.
     *
     * @param noticiaDTO the entity to save
     * @return the persisted entity
     */
    NoticiaDTO save(NoticiaDTO noticiaDTO);

    /**
     *  Get all the noticias.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<NoticiaDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" noticia.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    NoticiaDTO findOne(String id);

    /**
     *  Delete the "id" noticia.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
