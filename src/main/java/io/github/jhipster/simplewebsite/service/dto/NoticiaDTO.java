package io.github.jhipster.simplewebsite.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Noticia entity.
 */
public class NoticiaDTO implements Serializable {

    private String id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    @Size(max = 256)
    private String titulo;

    @NotNull
    @Size(max = 5000)
    private String contenido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoticiaDTO noticiaDTO = (NoticiaDTO) o;
        if(noticiaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noticiaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoticiaDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", contenido='" + getContenido() + "'" +
            "}";
    }
}
