package com.example.appsistema.Modelo;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appsistema.Presentador.Presentador_Login;
import com.example.appsistema.Vista.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modelo_Login implements Interface_ModeloL {
    Presentador_Login P;
    String nomuser, pass;
    String datoUsuarioEncontrado;
    String foto;
    public Modelo_Login(Presentador_Login P){
        this.P=P;
    }
    @Override
    public void login(String usuario, String passw) {

        this.nomuser=usuario;
        this.pass=passw;
        consulta(nomuser, pass);
    }

    @Override
    public void consulta(String momuser, String pass) {
        String URL="http://189.240.192.140/Wservices20221016/login.php"; //Servicio Web
        // Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray datos = new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        datoUsuarioEncontrado = valores.getString("vchnomuser");
                        foto= valores.getString("foto");
                    }
                    P.acceso(datoUsuarioEncontrado, foto);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.contexto, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.contexto, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("usuario",nomuser);
                parametros.put("password", pass);
                return parametros;
            }
        };
        RequestQueue envio= Volley.newRequestQueue(MainActivity.contexto);
        envio.add(respuesta);
    }

}
