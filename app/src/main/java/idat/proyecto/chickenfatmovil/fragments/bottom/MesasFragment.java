package idat.proyecto.chickenfatmovil.fragments.bottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.adapter.MesaAdapter;
import idat.proyecto.chickenfatmovil.model.Mesa;


public class MesasFragment extends Fragment {

    private static final String URL_mesa = "http://192.168.0.73/chickenfat/mostrar_mesas.php";

    List<Mesa> mesaList;
    RecyclerView listaMesa;
    MesaAdapter mesaAdapter;

    public static MesasFragment newInstance() {
        return new MesasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mesas, container, false);


        listaMesa = view.findViewById(R.id.listaMesas);
        listaMesa.setHasFixedSize(true);
        listaMesa.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));

        mesaList = new ArrayList<>();


        loadMesa();
        return view;
    }

    private void loadMesa() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_mesa,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject mesa = array.getJSONObject(i);

                            mesaList.add(new Mesa(
                                    mesa.getInt("id"),
                                    mesa.getString("estado")

                            ));
                        }

                        mesaAdapter = new MesaAdapter(mesaList);
                        listaMesa.setAdapter(mesaAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }

}