package com.uva.users.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "VinoConRelacion")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class VinoConRelacion {
    @Id
    @GeneratedValue
    private Integer Id;
    //@Size(max = 50) // si no se pone esta anotación lo creo por defecto con size=255
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    //@Size(max = 30)
    private String denominacion;
    //@Size(max = 30)
    private String categoria;
    @Column(nullable = false)
    private Float precio;

    @JoinColumn(name ="Bodega_Id",referencedColumnName = "ID")

    @ManyToOne(optional=false,fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
    private Bodega bodegaId;

    VinoConRelacion() {
    }

    VinoConRelacion(String nombre_comercial, String denominacion, String categoria, Float precio, Bodega bodega) {
        this.nombreComercial = nombre_comercial;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.bodegaId = bodega;
    }

    
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Bodega getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Bodega bodegaId) {
        this.bodegaId = bodegaId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre_comercial() {
        return nombreComercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombreComercial = nombre_comercial;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Bodega getBodega_id() {
        return bodegaId;
    }

    public void setBodega_id(Bodega bodegaId) {
        this.bodegaId = bodegaId;
    }

    
}