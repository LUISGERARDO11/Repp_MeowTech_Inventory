package com.example.appsistema.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.appsistema.Modelo.Adaptador;
import com.example.appsistema.Presentador.Presentador_ListaProductos;
import com.example.appsistema.R;
import com.squareup.picasso.Picasso;

public class Principal extends AppCompatActivity {
    TextView txtUsuario;
    ImageView imgFoto;
    RecyclerView rcvlista;
    EditText txtBuscar;
    Button btnBuscar;
    public static Context VistaPrincipal;
    Presentador_ListaProductos P;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtBuscar = (EditText) findViewById(R.id.txtBuscar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        rcvlista = (RecyclerView) findViewById(R.id.rcvlista);
        rcvlista.setLayoutManager(new GridLayoutManager(this, 1));
        Bundle parametros = getIntent().getExtras();
        String dato = parametros.getString("usuario");
        String foto = parametros.getString("foto");
        txtUsuario.setText("Bienvenido->" + dato);
        Picasso.get().load("http://189.240.192.140/Wservices20221016/srcImagenes/" + foto).resize(250, 250).into(imgFoto);
        btnBuscar.setOnClickListener(this::buscarProducto);
        VistaPrincipal = this;
        P = new Presentador_ListaProductos(this);
    }
    private void buscarProducto(View view) {
        P.buscarProducto(txtBuscar.getText().toString());
    }
    public void cargarlistaproductos(Adaptador AdaptadorRecycler) {
        rcvlista.setAdapter(AdaptadorRecycler);
    }
}