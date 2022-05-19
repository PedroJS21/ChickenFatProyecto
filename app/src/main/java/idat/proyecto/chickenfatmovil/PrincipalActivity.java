package idat.proyecto.chickenfatmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import idat.proyecto.chickenfatmovil.fragments.DeliveryFragment;
import idat.proyecto.chickenfatmovil.fragments.LlevarFragment;
import idat.proyecto.chickenfatmovil.fragments.RestaurantFragment;

public class PrincipalActivity extends AppCompatActivity {

    BottomNavigationView bnvMenu;
    Fragment fragment;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bnvMenu = findViewById(R.id.bnvMenu);
        manager = getSupportFragmentManager();
        loadFirstFragment();
        BottomNavigationListener();
    }


    private void BottomNavigationListener(){
        bnvMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idMenu = item.getItemId();
                switch (idMenu){
                    case R.id.menu_restaurante:
                        fragment = RestaurantFragment.newInstance();
                        openFragment(fragment);
                        return true;
                    case R.id.menu_llevar:
                        fragment = LlevarFragment.newInstance();
                        openFragment(fragment);
                        return true;
                    case R.id.menu_delivery:
                        fragment = DeliveryFragment.newInstance();
                        openFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void openFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.frameContainer,fragment).commit();
    }

    private void loadFirstFragment(){
        fragment = RestaurantFragment.newInstance();
        openFragment(fragment);
    }

    //Mostrar y ocultar menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    //Asignar funciones a los items del menu
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.itGenerarComanda){ irGenerarComanda();
        }else if (id==R.id.itPedTerminados){
            Toast.makeText(this,"Se muestran los pedidos terminados",Toast.LENGTH_SHORT).show();
        } else if (id ==R.id.itCerrarSesion){
            cerrarSesion();
        }
        return super.onOptionsItemSelected(item);
    }

    public void cerrarSesion(){
        SharedPreferences preferences = getSharedPreferences("spChickenFat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("estado_sesion",false).apply();
        irLogin();
    }

    public void irLogin(){
        Intent Login = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(Login);
    }

    public void irGenerarComanda(){
        Intent GenerarComanda = new Intent(getApplicationContext(),GenerarComandaActivity.class);
        startActivity(GenerarComanda);
    }
}