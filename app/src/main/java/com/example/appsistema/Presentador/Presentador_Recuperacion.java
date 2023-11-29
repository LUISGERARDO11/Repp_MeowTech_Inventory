package com.example.appsistema.Presentador;

import android.content.Intent;

import com.example.appsistema.Modelo.Interface_ModeloL;
import com.example.appsistema.Modelo.Interface_ModeloR;
import com.example.appsistema.Modelo.Modelo_Login;
import com.example.appsistema.Modelo.Modelo_Recuperacion;
import com.example.appsistema.Vista.MainActivity;
import com.example.appsistema.Vista.Recuperacion;
import com.example.appsistema.Vista.ReestablecerDatos;

public class Presentador_Recuperacion implements Interface_PresentadorR{
    Recuperacion V;
    ReestablecerDatos v2;
    Interface_ModeloR M;
    public Presentador_Recuperacion(Recuperacion V){
        this.V= V;
        M= new Modelo_Recuperacion(this);
    }
    public Presentador_Recuperacion(ReestablecerDatos v){
        this.v2=v;
        M= new Modelo_Recuperacion(this);
    }
    @Override
    public void validarUsuario(String usuario, String QuestionS) {
        M.validarUsuario(usuario,QuestionS);
    }

    @Override
    public void acceso(String idUsuario) {
        V.acceso(idUsuario);
    }

    @Override
    public void reestablecerPass(String usuario, String nuevContrasenia) {
        M.reestablecerPass(usuario, nuevContrasenia);
    }

}
