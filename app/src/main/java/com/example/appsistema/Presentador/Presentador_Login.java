package com.example.appsistema.Presentador;

import android.widget.Toast;

import com.example.appsistema.Modelo.Interface_ModeloL;
import com.example.appsistema.Modelo.Modelo_Login;
import com.example.appsistema.Vista.MainActivity;

public class Presentador_Login implements Interface_PresentadorL{
    MainActivity V;
    Interface_ModeloL M;
    public Presentador_Login(MainActivity V){
        this.V= V;
        M= new Modelo_Login(this);
    }
    @Override
    public void login(String usuario, String passw) {

        M.login(usuario, passw);
    }

    @Override
    public void acceso(String miusuario, String foto) {
        V.acceso(miusuario, foto);
    }
}
