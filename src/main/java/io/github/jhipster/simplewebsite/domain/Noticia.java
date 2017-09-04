package io.github.jhipster.simplewebsite.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Noticia.
 */
@Document(collection = "noticia")
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @NotNull
    @Field("fecha")
    private LocalDate fecha;

    @NotNull
    @Size(max = 256)
    @Field("titulo")
    private String titulo;

    @NotNull
    @Size(max = 5000)
    @Field("contenido")
    private String contenido;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Noticia fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public Noticia titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public Noticia contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Noticia noticia = (Noticia) o;
        if (noticia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Noticia{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", contenido='" + getContenido() + "'" +
            "}";
    }
}
