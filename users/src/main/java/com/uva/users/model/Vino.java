package com.uva.users.model;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@Table(name = "Vino")
@NamedQueries({
    @NamedQuery(
        name = "Vino.findByDenominacionOrdenadoNombreDesc",
        query = "SELECT V FROM Vino V WHERE V.denominacion = ?1 ORDER BY V.nombreComercial DESC"),
    @NamedQuery(
        name = "Vino.findByDenominacionYCategoria",
        query = "SELECT V FROM Vino V WHERE V.denominacion = ?1 AND V.categoria = ?2"
    )
})
public class Vino implements Serializable {
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
    private Integer bodega_id;

    Vino() {
    }

    Vino(String nombre_comercial, String denominacion, String categoria, Float precio, Integer bodega) {
        this.nombreComercial = nombre_comercial;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.bodega_id = bodega;
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

    public Integer getBodega_id() {
        return bodega_id;
    }

    public void setBodega_id(Integer bodega_id) {
        this.bodega_id = bodega_id;
    }

    
}