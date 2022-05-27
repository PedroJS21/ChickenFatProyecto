package idat.proyecto.chickenfatmovil.fragments.bottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import idat.proyecto.chickenfatmovil.R;


public class FinalizadoFragment extends Fragment {


    public static FinalizadoFragment newInstance() {
        return new FinalizadoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finalizado, container, false);
    }
}