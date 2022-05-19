package idat.proyecto.chickenfatmovil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import idat.proyecto.chickenfatmovil.model.Producto;

public class SearchProductFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String URL_producto = "http://192.168.0.73/chickenfat/mostrar_productos.php";

    List<Producto> productoList;
    SearchView txtBuscar;
    RecyclerView listaProducto;
    Adapter adapter;

    public static SearchProductFragment newInstance() {
        SearchProductFragment fragment = new SearchProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_product, container, false);

        txtBuscar = view.findViewById(R.id.txtBuscar);
        listaProducto = view.findViewById(R.id.listaProductos);
        listaProducto.setHasFixedSize(true);
        listaProducto.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        productoList = new ArrayList<>();


        txtBuscar.setOnQueryTextListener(this);
        loadProducto();

        return view;


    }

    private void loadProducto() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_producto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject producto = array.getJSONObject(i);

                                productoList.add(new Producto(
                                        producto.getInt("id"),
                                        producto.getString("nombre"),
                                        producto.getDouble("costo"),
                                        producto.getString("categ")
                                ));
                            }

                            adapter = new Adapter(getActivity(), productoList);
                            listaProducto.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}