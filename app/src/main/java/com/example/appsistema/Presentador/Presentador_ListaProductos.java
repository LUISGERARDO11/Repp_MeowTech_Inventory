package com.example.appsistema.Presentador;

import com.example.appsistema.Modelo.Adaptador;
import com.example.appsistema.Modelo.Modelo_listaproductos;
import com.example.appsistema.Vista.Principal;

public class Presentador_ListaProductos {
    Principal V;
    Modelo_listaproductos M;

    public Presentador_ListaProductos(Principal V) {
        this.V = V;
        M= new Modelo_listaproductos(this);
    }
    public void cargarlistaproductos(Adaptador AdaptadorRecycler){
        V.cargarlistaproductos(AdaptadorRecycler);
    }
    public void buscarProducto(String busqueda){
        M.MostrarListaBusqueda(busqueda);
    }
}
