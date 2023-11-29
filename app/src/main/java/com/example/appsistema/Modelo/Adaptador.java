package com.example.appsistema.Modelo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsistema.R;
import com.example.appsistema.Vista.DetalleProducto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adaptador extends RecyclerView.Adapter<Adaptador.VistaHolderProductos> {
    ArrayList<ClaseDatosProductos>productos;
    //ArrayList<ClaseDatosProductos>listaProductos;
    public Adaptador(ArrayList<ClaseDatosProductos> productos) {

        this.productos = productos;
    }

    @NonNull
    @Override
    public VistaHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V= LayoutInflater.from(parent.getContext()).inflate(R.layout.productos, null, false);
        return new VistaHolderProductos(V);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaHolderProductos holder, int position) {

        Picasso.get().load("http://189.240.192.140/Wservices20221016/srcImagenes/"+productos.get(position).getFoto()).resize(250,200).into(holder.imgProducto);

        holder.txtNombre.setText(productos.get(position).getNombre());
        holder.txtPrecio.setText("Precio: $"+String.valueOf(productos.get(position).getPrecio()));
        holder.txtStock.setText("Existencia: "+String.valueOf(productos.get(position).getExistencia()));

        holder.btnVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context= v.getContext();
                Intent intent = new Intent(context, DetalleProducto.class);

                intent.putExtra("imagenUrl", productos.get(holder.getAdapterPosition()).getFoto());
                intent.putExtra("nombre", productos.get(holder.getAdapterPosition()).getNombre());
                intent.putExtra("precio", productos.get(holder.getAdapterPosition()).getPrecio());
                intent.putExtra("stock", productos.get(holder.getAdapterPosition()).getExistencia());
                intent.putExtra("precioCompra", productos.get(holder.getAdapterPosition()).getPrecioCompra());
                intent.putExtra("proveedor", productos.get(holder.getAdapterPosition()).getProveedor());
                intent.putExtra("descripcion", productos.get(holder.getAdapterPosition()).getDetalle());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return productos.size();
    }

    public static class VistaHolderProductos extends RecyclerView.ViewHolder{
        ImageView imgProducto;
        TextView txtNombre;
        TextView txtPrecioCompra;
        TextView txtStock;
        TextView txtProveedor;
        TextView txtPrecio;
        EditText txtDetalle;
        Button btnVerDetalles;
        public VistaHolderProductos(@NonNull View itemView) {
            super(itemView);
            imgProducto=itemView.findViewById(R.id.imgproducto);
            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtPrecio=itemView.findViewById(R.id.txtPrecio);
            txtStock=itemView.findViewById(R.id.txtStock);
            btnVerDetalles= itemView.findViewById(R.id.btnVerDetalles);
        }
    }
}
