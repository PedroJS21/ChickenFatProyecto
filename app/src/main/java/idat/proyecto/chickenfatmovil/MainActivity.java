package idat.proyecto.chickenfatmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtPassword;
    Button btnLogin;
    CheckBox cbRecordarUsuario;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = this.getSharedPreferences("spChickenFat", Context.MODE_PRIVATE);
        editor = preferences.edit();

        validarSesion();
        setTheme(R.style.Theme_ChickenFatMovil_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario=findViewById(R.id.edtUsuario);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        cbRecordarUsuario=findViewById(R.id.cbRecordarUsuario);

        recordarUsuario();
        btnLogin.setOnClickListener((View v) -> validarUsuario());
    }


    private void validarUsuario() {
        String URL = "http://192.168.0.73/chickenfat/validar_usuario.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarSesion();
                    guardarUsuario();
                    irPrincipal();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parametros=new HashMap<>();
                parametros.put("usuario", edtUsuario.getText().toString());
                parametros.put("password", edtPassword.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void irPrincipal(){
        Intent Principal = new Intent(getApplicationContext(),PrincipalActivity.class);
        startActivity(Principal);
    }


    public void guardarSesion(){
        editor.putBoolean("estado_sesion",true).apply();
    }
    public void validarSesion(){
        if(preferences.getBoolean("estado_sesion",false)){
            irPrincipal();
        }
    }


    public void guardarUsuario(){
        if(cbRecordarUsuario.isChecked()){
            editor.putString("usuario",edtUsuario.getText().toString())
                    .putBoolean("estado_cb",true).apply();
        }else {
            editor.putString("usuario","").putBoolean("estado_cb",false).apply();
        }
    }
    public void recordarUsuario() {
        if (preferences.getBoolean("estado_cb",false)){
            edtUsuario.setText(preferences.getString("usuario", ""));
            cbRecordarUsuario.setChecked(true);
        }
    }
}