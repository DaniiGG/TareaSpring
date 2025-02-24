package com.tarea.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;

@Validated
@Entity
@Table(name="Tarea")

public class Tarea {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)


        Long id;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name= "nombre")
       String nombre;

    @Size(max = 500)
    @Column(name= "descripcion")
        String descripcion;

    @NotNull
    @Column(name= "fechaIni")
        LocalDate fechaIni;

    @NotNull
    @Column(name= "fechaFin")
    LocalDate fechaFin;

    @NotNull
    @Column(name= "estado")
    Estado estado;

    @NotNull
    @Column(name= "prioridad")
    Prioridad prioridad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Tarea(){};

    public Tarea(Long id, String nombre, String descripcion, LocalDate fechaIni, LocalDate fechaFin, Estado estado, Prioridad prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.estado= estado;
        this.prioridad=prioridad;
    }


}
