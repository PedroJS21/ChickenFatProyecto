package idat.proyecto.chickenfatmovil.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import idat.proyecto.chickenfatmovil.R;


public class LlevarFragment extends Fragment {


    public static LlevarFragment newInstance() {
        LlevarFragment fragment = new LlevarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_llevar, container, false);
    }
}