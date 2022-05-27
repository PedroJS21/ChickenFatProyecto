package idat.proyecto.chickenfatmovil.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.model.Mesa;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> {

    private final List<Mesa> mesaList;

    public MesaAdapter(List<Mesa> mesaList) {
        this.mesaList = mesaList;
    }

    @NonNull
    @Override
    public MesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.mesa_card, parent, false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MesaViewHolder holder, int position) {
        Mesa mesa = mesaList.get(position);
        holder.textId.setText(String.valueOf(mesa.getId_mesa()));
        if (mesa.getEstado().equals("1")){
            holder.li.setBackgroundColor(Color.RED);
            holder.textId.setTextColor(Color.WHITE);
        }else{
            holder.li.setBackgroundColor(Color.GRAY);
        }

    }



    @Override
    public int getItemCount() {
        return mesaList.size();
    }

    static class MesaViewHolder extends RecyclerView.ViewHolder {

        TextView textId;
        LinearLayout li;


        public MesaViewHolder(@NonNull View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.mesa_numero);
            
            li = itemView.findViewById(R.id.mesa_layout);
        }
    }
}
