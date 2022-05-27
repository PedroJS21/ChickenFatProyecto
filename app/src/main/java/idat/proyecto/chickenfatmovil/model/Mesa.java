package idat.proyecto.chickenfatmovil.model;

public class Mesa {
    private final int id_mesa;
    private final String estado;

    public Mesa(int id_mesa, String estado) {
        this.id_mesa = id_mesa;
        this.estado = estado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public String getEstado() {
        return estado;
    }
}
