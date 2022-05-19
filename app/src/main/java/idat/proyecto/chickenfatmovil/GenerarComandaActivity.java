package idat.proyecto.chickenfatmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}