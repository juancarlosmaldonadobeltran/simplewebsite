package io.github.jhipster.simplewebsite.service.mapper;

import io.github.jhipster.simplewebsite.domain.*;
import io.github.jhipster.simplewebsite.service.dto.NoticiaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Noticia and its DTO NoticiaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoticiaMapper extends EntityMapper <NoticiaDTO, Noticia> {
    
    

}
