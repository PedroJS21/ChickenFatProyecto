package idat.proyecto.chickenfatmovil.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.fragments.bottom.MasFragment;
import idat.proyecto.chickenfatmovil.fragments.bottom.MesasFragment;
import idat.proyecto.chickenfatmovil.fragments.bottom.FinalizadoFragment;
import idat.proyecto.chickenfatmovil.fragments.bottom.ComandasFragment;

public class PrincipalActivity extends AppCompatActivity {

    BottomNavigationView bnvMenu;
    Fragment fragment;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bnvMenu = findViewById(R.id.bottom_navigation);
        manager = getSupportFragmentManager();
        loadFirstFragment();
        BottomNavigationListener();
    }


    @SuppressLint("NonConstantResourceId")
    private void BottomNavigationListener(){
        bnvMenu.setOnItemSelectedListener(item -> {
            int idMenu = item.getItemId();
            switch (idMenu){
                case R.id.bottom_comandas:
                    fragment = ComandasFragment.newInstance();
                    openFragment(fragment);
                    return true;
                case R.id.bottom_finalizado:
                    fragment = FinalizadoFragment.newInstance();
                    openFragment(fragment);
                    return true;
                case R.id.bottom_mesas:
                    fragment = MesasFragment.newInstance();
                    openFragment(fragment);
                    return true;
                case R.id.bottom_mas:
                    fragment = MasFragment.newInstance();
                    openFragment(fragment);
                    return true;
            }
            return false;
        });
    }

    private void openFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.frameContainer,fragment).commit();
    }

    private void loadFirstFragment(){
        SharedPreferences preferences = this.getSharedPreferences("spChickenFat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getBoolean("volver_mas",false)){
            fragment = MasFragment.newInstance();
            bnvMenu.setSelectedItemId(R.id.bottom_mas);
            editor.putBoolean("volver_mas",false).apply();
        }else{
            fragment = ComandasFragment.newInstance();
        }
        openFragment(fragment);
    }
}