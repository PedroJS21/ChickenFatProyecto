package idat.proyecto.chickenfatmovil.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.model.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<Producto> productoList;
    private final ArrayList<Producto> listaOriginal;

    public ProductoAdapter(List<Producto> productoList) {
        this.productoList = productoList;

        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(productoList);
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_card, parent, false);
        return new ProductoViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        String Costo = "S/." + producto.getCosto();

        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewCosto.setText(Costo);
        holder.textViewCategoria.setText(producto.getCateg());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();
        if(longitud == 0) {
            productoList.clear();
            productoList.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Producto> colleccion = productoList.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                productoList.clear();
                productoList.addAll(colleccion);
            } else {
                for (Producto p : listaOriginal) {
                    if (p.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        productoList.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return productoList.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre, textViewCosto,textViewCategoria;

        public ProductoViewHolder(View itemView) {
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.producto_title);
            textViewCosto = itemView.findViewById(R.id.producto_costo);
            textViewCategoria = itemView.findViewById(R.id.producto_categ);
        }
    }
}
