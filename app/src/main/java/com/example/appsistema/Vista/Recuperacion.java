package com.example.appsistema.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appsistema.Presentador.Presentador_Login;
import com.example.appsistema.Presentador.Presentador_Recuperacion;
import com.example.appsistema.R;

public class Recuperacion extends AppCompatActivity {

    EditText txtUsuario;
    TextView txtQuestionS;
    EditText txtAnswerS;
    TextView lblQuestion;
    Button btnReestablecer;
    Presentador_Recuperacion P;
    public static Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion);
        lblQuestion=(TextView) findViewById(R.id.txtQuestionS);
        lblQuestion.setText("¿Apellido de tu maestro favorito?");
        txtUsuario= (EditText) findViewById(R.id.txtUsuario);
        txtAnswerS=(EditText) findViewById(R.id.txtAnswerS);
        btnReestablecer=(Button) findViewById(R.id.btnRecuperarC);
        btnReestablecer.setOnClickListener(this::eventoConfirmarUsuario);
        P= new Presentador_Recuperacion(this);
        contexto= this;
    }
    public void eventoConfirmarUsuario(View Vista){
        P.validarUsuario(txtUsuario.getText().toString(), txtAnswerS.getText().toString());
    }
    public void acceso(String idUsuario) {

        if(!(idUsuario.trim()).isEmpty()==true){
            Intent reestablecerView= new Intent(this, ReestablecerDatos.class);
            reestablecerView.putExtra("usuario", idUsuario);
            startActivity(reestablecerView);
        }
    }
}