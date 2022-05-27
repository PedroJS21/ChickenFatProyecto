package idat.proyecto.chickenfatmovil.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.model.Pedido;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private final List<Pedido> pedidoList;

    public PedidoAdapter(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;

    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pedido_card, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder holder, int position) {

        Pedido pedido = pedidoList.get(position);
        String encabezado = pedido.getTipo()+" "+pedido.getMesas().toString().replace("[", "").replace("]", "");
        String mesero = "Mesero: " +  pedido.getMesero();
        holder.textViewEncabezado.setText(encabezado);
        holder.textViewEstado.setText(pedido.getEstado());
        if(pedido.getObservacion().equals("null")){
            holder.textViewObservacion.setVisibility(View.GONE);
        }else{
            holder.textViewObservacion.setVisibility(View.VISIBLE);
            holder.textViewObservacion.setText(pedido.getObservacion());
        }
        holder.textViewMesero.setText(mesero);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pedidoList.size();
    }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEncabezado,textViewEstado,textViewObservacion,textViewMesero;

        public PedidoViewHolder(View itemView) {
            super(itemView);

            textViewEncabezado = itemView.findViewById(R.id.pedido_encabezado);
            textViewEstado = itemView.findViewById(R.id.pedido_estado);
            textViewObservacion = itemView.findViewById(R.id.pedido_observacion);
            textViewMesero = itemView.findViewById(R.id.pedido_mesero);
        }
    }
}
