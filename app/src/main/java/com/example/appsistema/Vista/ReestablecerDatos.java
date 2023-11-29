package com.example.appsistema.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsistema.Presentador.Presentador_Recuperacion;
import com.example.appsistema.R;

public class ReestablecerDatos extends AppCompatActivity {
    EditText txtPass;
    EditText txtConfirmPass;
    Button btnReestablecer;

    String usuario;
    Presentador_Recuperacion P;
    public static Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reestablecer_datos);
        txtPass= (EditText) findViewById(R.id.txtPassword);
        txtConfirmPass=(EditText) findViewById(R.id.txtConfirmPassword);
        btnReestablecer=(Button) findViewById(R.id.btnRecuperar);
        Bundle parametros = getIntent().getExtras();
        usuario = parametros.getString("usuario");
        btnReestablecer.setOnClickListener(this::eventoReestablecer);
        P= new Presentador_Recuperacion(this);
        contexto= this;
    }

    private void eventoReestablecer(View Vista) {
        // Validar que los campos no estén vacíos
        String pass= txtPass.getText().toString();
        String confirmPass= txtConfirmPass.getText().toString();
        if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmPass)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que las contraseñas coincidan
        if (!pass.equals(confirmPass)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        P.reestablecerPass(usuario.toString(), pass);
    }


}