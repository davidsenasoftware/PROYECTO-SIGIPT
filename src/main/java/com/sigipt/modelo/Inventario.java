package com.sigipt.modelo;

public class Inventario {

    private int idInventario;
    private String tipo;
    private int cantidadDisponible;
    private String ubicacion;
    private int stockMinimo;
    private int stockMaximo;
    private int idProducto; // Llave foránea vinculada a Producto

    // Constructor vacío
    public Inventario() {
    }

    // Constructor con parámetros (sin ID para inserciones nuevas)
    public Inventario(String tipo, int cantidadDisponible, String ubicacion, int stockMinimo, int stockMaximo, int idProducto) {
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
        this.ubicacion = ubicacion;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.idProducto = idProducto;
    }

    // Constructor completo (con ID para consultas y actualizaciones)
    public Inventario(int idInventario, String tipo, int cantidadDisponible, String ubicacion, int stockMinimo, int stockMaximo, int idProducto) {
        this.idInventario = idInventario;
        this.tipo = tipo;
        this.cantidadDisponible = cantidadDisponible;
        this.ubicacion = ubicacion;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.idProducto = idProducto;
    }

    // Métodos Getters y Setters
    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}