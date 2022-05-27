package idat.proyecto.chickenfatmovil.fragments.bottom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import idat.proyecto.chickenfatmovil.activities.GenerarComandaActivity;
import idat.proyecto.chickenfatmovil.activities.MainActivity;
import idat.proyecto.chickenfatmovil.R;


public class MasFragment extends Fragment {

    LinearLayout llCerrarSesion, llCarta;

    public static MasFragment newInstance() {
        return new MasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mas, container, false);

        llCerrarSesion = view.findViewById(R.id.llCerrarSesion);
        llCarta = view.findViewById(R.id.llCarta);

        llCerrarSesion.setOnClickListener(v -> cerrarSesion());
        llCarta.setOnClickListener(v -> irGenerarComanda());

        return view;
    }

    public void cerrarSesion(){
        SharedPreferences preferences = this.requireContext().getSharedPreferences("spChickenFat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("estado_sesion",false).apply();
        irLogin();
    }

    public void irLogin(){
        Intent Login = new Intent(getContext(), MainActivity.class);
        startActivity(Login);
    }

    public void irGenerarComanda(){
        Intent GenerarComanda = new Intent(getContext(), GenerarComandaActivity.class);
        SharedPreferences preferences = this.requireContext().getSharedPreferences("spChickenFat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("volver_mas",true).apply();
        startActivity(GenerarComanda);
    }

}