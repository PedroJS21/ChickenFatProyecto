package idat.proyecto.chickenfatmovil.fragments.toptab;

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
import java.util.Arrays;
import java.util.List;

import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.adapter.PedidoAdapter;
import idat.proyecto.chickenfatmovil.model.Pedido;


public class RestauranteFragment extends Fragment {

    private static final String URL_pedido = "http://192.168.0.73/chickenfat/mostrar_pedido.php";

    List<Pedido> pedidoList;
    RecyclerView listaPedido;
    PedidoAdapter pedidoAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        listaPedido = view.findViewById(R.id.listaPedidos);
        listaPedido.setHasFixedSize(true);
        listaPedido.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        pedidoList = new ArrayList<>();

        loadPedido();
        return view;
    }

    private void loadPedido() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_pedido,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject pedido = array.getJSONObject(i);

                            String mesas1 = pedido.getString("mesas");
                            ArrayList<String> mesas2 = new ArrayList<>(Arrays.asList(mesas1.split(",")));

                            pedido.getString("id");
                            pedidoList.add(new Pedido(
                                    pedido.getInt("id"),
                                    pedido.getString("tipo"),
                                    pedido.getString("estado"),
                                    pedido.getString("observacion"),
                                    pedido.getString("mesero"),
                                            mesas2
                            ));
                        }
                        pedidoAdapter = new PedidoAdapter(pedidoList);
                        listaPedido.setAdapter(pedidoAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }
}