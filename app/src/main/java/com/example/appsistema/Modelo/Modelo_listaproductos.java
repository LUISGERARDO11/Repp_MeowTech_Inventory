package com.example.appsistema.Modelo;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appsistema.Presentador.Presentador_ListaProductos;
import com.example.appsistema.Vista.Principal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Modelo_listaproductos implements  Interface_Listaproductos{

    Presentador_ListaProductos P;
    ArrayList<ClaseDatosProductos> productos= new ArrayList<>();

    String URL="http://189.240.192.140/Wservices20221016/listaproductos.php";

    public Modelo_listaproductos(Presentador_ListaProductos P) {
        this.P = P;
        MostrarLista();
    }
    @Override
    public void MostrarLista() {
        StringRequest respuesta = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray datos = new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        String nombre = valores.getString("nombre");
                        double precio = valores.getDouble("precio_venta");
                        double precioCompra = valores.getDouble("precio_compra");
                        int existencia = valores.getInt("existencia");
                        String imagen = valores.getString("img");
                        String proveedor = valores.getString("id_prov");
                        String detalle = valores.getString("descripcion");
                        productos.add(new ClaseDatosProductos(nombre,precio,existencia,imagen,precioCompra,proveedor,detalle));
                    }
                    Adaptador AdaptadorRecycler= new Adaptador(productos);
                    P.cargarlistaproductos(AdaptadorRecycler);
                } catch (Exception e) {
                    Toast.makeText(Principal.VistaPrincipal, "error en la extraciòn de los datos del JASON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Principal.VistaPrincipal, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue envio = Volley.newRequestQueue(Principal.VistaPrincipal);
        envio.add(respuesta);
    }

    @Override
    public void MostrarListaBusqueda(String busqueda) {
        productos.clear();
        String busquedaURL = "http://189.240.192.140/Wservices20221016/buscarProductos.php"; // URL diferente para búsqueda
        StringRequest respuesta = new StringRequest(Request.Method.POST, busquedaURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray datos = new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        String nombre = valores.getString("nombre");
                        double precio = valores.getDouble("precio_venta");
                        double precioCompra = valores.getDouble("precio_compra");
                        int existencia = valores.getInt("existencia");
                        String imagen = valores.getString("img");
                        String proveedor = valores.getString("id_prov");
                        String detalle = valores.getString("descripcion");
                        productos.add(new ClaseDatosProductos(nombre,precio,existencia,imagen,precioCompra,proveedor,detalle));
                    }
                    Adaptador AdaptadorRecycler= new Adaptador(productos);
                    P.cargarlistaproductos(AdaptadorRecycler);
                } catch (Exception e) {
                    Toast.makeText(Principal.VistaPrincipal, "error en la extraciòn de los datos del JASON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Principal.VistaPrincipal, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("busqueda", busqueda);
                return parametros;
            }
        };

        RequestQueue envio = Volley.newRequestQueue(Principal.VistaPrincipal);
        envio.add(respuesta);

    }
}
