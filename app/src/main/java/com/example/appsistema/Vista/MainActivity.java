package com.example.appsistema.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView;

import com.example.appsistema.Presentador.Interface_PresentadorL;
import com.example.appsistema.Presentador.Presentador_Login;
import com.example.appsistema.R;

public class MainActivity extends AppCompatActivity implements Interface_Login {

    EditText txtUsuario;
    EditText txtPassword;
    Button btnAcceso;
    TextView txtRecuperacion;
    Interface_PresentadorL P;

    public static Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario= (EditText) findViewById(R.id.txtusuario);
        txtPassword= (EditText) findViewById(R.id.txtpassword);
        btnAcceso= (Button) findViewById(R.id.btnAcceso);
        txtRecuperacion= (TextView) findViewById(R.id.txtRecuperacion);
        btnAcceso.setOnClickListener(this::eventoAcceder);
        txtRecuperacion.setOnClickListener(this::eventoRecuperar);
        P= new Presentador_Login(this);
        contexto= this;
    }
    public void eventoAcceder(View Vista){
        P.login(txtUsuario.getText().toString(), txtPassword.getText().toString());
    }
    public void eventoRecuperar(View Vista){
        Intent vistaR= new Intent(this, Recuperacion.class);
        startActivity(vistaR);
    }
    @Override
    public void acceso(String miusuario, String foto) {
        if(!(miusuario.trim()).isEmpty()==true){
            Intent vista2= new Intent(this, Principal.class);
            vista2.putExtra("usuario", miusuario);
            vista2.putExtra("foto", foto);
            startActivity(vista2);
        }
    }
}