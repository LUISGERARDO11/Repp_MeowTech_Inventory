package com.example.appsistema.Modelo;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appsistema.Presentador.Presentador_Login;
import com.example.appsistema.Presentador.Presentador_Recuperacion;
import com.example.appsistema.Vista.DetalleProducto;
import com.example.appsistema.Vista.MainActivity;
import com.example.appsistema.Vista.Recuperacion;
import com.example.appsistema.Vista.ReestablecerDatos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Modelo_Recuperacion implements Interface_ModeloR {
    Presentador_Recuperacion P;
    String Usuario,answerS;
    String uss, nuevContrasenia;
    String idUsuarioEncontrado;

    public Modelo_Recuperacion(Presentador_Recuperacion P){
        this.P=P;
    }
    @Override
    public void validarUsuario(String usuario, String answerS) {
        this.Usuario=usuario;
        this.answerS=answerS;
        consultaUsuario(Usuario,answerS);
    }
    @Override
    public void consultaUsuario(String usuario, String answerS) {
        String URL="http://189.240.192.140/Wservices20221016/buscarUsuario.php"; //Servicio Web
        // Esta es la petición de Volley
        StringRequest respuesta= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray datos = new JSONArray(response);
                    for (int x = 0; x < datos.length(); x++) {
                        JSONObject valores = datos.getJSONObject(x);
                        idUsuarioEncontrado = valores.getString("id");
                    }
                    P.acceso(idUsuarioEncontrado);
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
                parametros.put("usuario",Usuario);
                parametros.put("answerS",answerS);
                return parametros;
            }
        };
        RequestQueue envio= Volley.newRequestQueue(Recuperacion.contexto);
        envio.add(respuesta);
    }

    @Override
    public void reestablecerPass(String usuario, String nuevContrasenia) {
        this.uss=usuario;
        this.nuevContrasenia=nuevContrasenia;
        editUser(uss,nuevContrasenia);
    }

    @Override
    public void editUser(String usuario, String nuevContrasenia) {
        String URL = "http://189.240.192.140/Wservices20221016/reestablecerPass.php";

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Procesar la respuesta del servidor
                if (response.equals("Contraseña actualizada")) {
                    // Contraseña actualizada con éxito, puedes realizar acciones adicionales si es necesario
                    Toast.makeText(ReestablecerDatos.contexto, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show();

                    // Puedes redirigir al usuario a otra actividad o realizar otras acciones aquí
                    // Redirigir al usuario a MainActivity
                    Context context= ReestablecerDatos.contexto;
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

                } else {
                    // Error al actualizar la contraseña
                    Toast.makeText(ReestablecerDatos.contexto, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar errores de Volley, por ejemplo, mostrar un mensaje de error
                Toast.makeText(ReestablecerDatos.contexto, "Error en la solicitud al servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // Aquí debes incluir los parámetros necesarios para actualizar la contraseña
                // Por ejemplo, el nombre de usuario (vchnomuser) y la nueva contraseña (vchpassword)
                params.put("user", usuario);
                params.put("password", nuevContrasenia);
                return params;
            }
        };

        // Agregar la solicitud a la cola
        RequestQueue queue = Volley.newRequestQueue(ReestablecerDatos.contexto);
        queue.add(request);
    }


}
