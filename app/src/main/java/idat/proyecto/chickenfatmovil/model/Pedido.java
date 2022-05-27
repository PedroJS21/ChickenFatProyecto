package idat.proyecto.chickenfatmovil.model;

import java.util.ArrayList;

public class Pedido {
    private int id_pedido;
    private String tipo;
    private String estado;
    private String observacion;
    private String mesero;
    private ArrayList<String> mesas;

    public Pedido(int id_pedido, String tipo, String estado, String observacion, String mesero, ArrayList<String> mesas) {
        this.id_pedido = id_pedido;
        this.tipo = tipo;
        this.estado = estado;
        this.observacion = observacion;
        this.mesero = mesero;
        this.mesas = mesas;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getMesero() {
        return mesero;
    }

    public ArrayList<String> getMesas() {
        return mesas;
    }
}
