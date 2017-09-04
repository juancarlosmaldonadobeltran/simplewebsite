package io.github.jhipster.simplewebsite.repository;

import io.github.jhipster.simplewebsite.domain.Noticia;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Noticia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoticiaRepository extends MongoRepository<Noticia, String> {

}
