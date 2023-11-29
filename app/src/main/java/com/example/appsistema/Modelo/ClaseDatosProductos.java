package com.example.appsistema.Modelo;

public class ClaseDatosProductos {
    String nombre;
    String imgproducto;
    double precio;
    double precioCompra;
    String proveedor;
    String detalle;
    int stock;

    public ClaseDatosProductos(String nombre,double precio, int stock,String imgproducto,double precioCompra,String proveedor,String detalle) {
        this.nombre = nombre;
        this.imgproducto = imgproducto;
        this.precio = precio;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
        this.detalle = detalle;
        this.stock = stock;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return imgproducto;
    }

    public void setFoto(String imgproducto) {
        this.imgproducto = imgproducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return stock;
    }

    public void setExistencia(int stock) {
        this.stock = stock;
    }
}
