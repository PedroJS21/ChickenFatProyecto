package idat.proyecto.chickenfatmovil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.Objects;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.fragments.SearchProductFragment;

public class GenerarComandaActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_comanda);

        fragmentManager = getSupportFragmentManager();
        fragment = SearchProductFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fcGenerarComanda, fragment).commit();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Carta");
    }


}