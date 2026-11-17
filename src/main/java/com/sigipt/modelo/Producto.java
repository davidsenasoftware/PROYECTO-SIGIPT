package com.sigipt.modelo;

public class Producto {

    private int idProducto;
    private String modelo;
    private String talla;
    private String color;
    private String descripcion;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros (sin ID para inserciones nuevas)
    public Producto(String modelo, String talla, String color, String descripcion) {
        this.modelo = modelo;
        this.talla = talla;
        this.color = color;
        this.descripcion = descripcion;
    }

    // Constructor completo (con ID para consultas y actualizaciones)
    public Producto(int idProducto, String modelo, String talla, String color, String descripcion) {
        this.idProducto = idProducto;
        this.modelo = modelo;
        this.talla = talla;
        this.color = color;
        this.descripcion = descripcion;
    }

    // Métodos Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}