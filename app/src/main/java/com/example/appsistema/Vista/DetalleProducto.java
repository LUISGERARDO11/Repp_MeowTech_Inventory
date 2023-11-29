package com.example.appsistema.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsistema.R;
import com.squareup.picasso.Picasso;

public class DetalleProducto extends AppCompatActivity {

    ImageView imgProducto;
    TextView txtNombre;
    TextView txtPrecioCompra;
    TextView txtStock;
    TextView txtProveedor;
    TextView txtPrecioVenta;
    EditText txtDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        imgProducto = (ImageView) findViewById(R.id.imgproducto);
        txtNombre= (TextView) findViewById(R.id.txtNombre1);
        txtPrecioCompra= (TextView) findViewById(R.id.txtPrecioCompra1);
        txtPrecioVenta= (TextView) findViewById(R.id.txtPrecio1);
        txtStock= (TextView) findViewById(R.id.txtStock1);
        txtProveedor=(TextView) findViewById(R.id.txtProveedor);
        txtDetalle=(EditText) findViewById(R.id.txtDescripcionProd);

        txtDetalle.setFocusable(false);
        txtDetalle.setClickable(false);
        txtDetalle.setFocusableInTouchMode(false);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imagenUrl");
        String nombre = intent.getStringExtra("nombre");
        double precioCompra = intent.getDoubleExtra("precioCompra", 0.0);
        double precioVenta = intent.getDoubleExtra("precio", 0.0);
        int stock = intent.getIntExtra("stock", 0);
        String proveedor = intent.getStringExtra("proveedor");
        String detalle = intent.getStringExtra("descripcion");


        txtNombre.setText(nombre);
        txtPrecioCompra.setText(String.valueOf(precioCompra));
        txtPrecioVenta.setText(String.valueOf(precioVenta));
        txtStock.setText(String.valueOf(stock));
        txtProveedor.setText(proveedor);
        txtDetalle.setText(detalle);

        Picasso.get().load("http://189.240.192.140/Wservices20221016/srcImagenes/"+imageUrl).resize(350,300).into(imgProducto);
    }
}