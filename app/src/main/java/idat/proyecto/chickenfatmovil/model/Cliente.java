package idat.proyecto.chickenfatmovil.model;

public class Cliente {
    private int id_cliente;
    private String nombre;
    private String telefono;
    private String ubicacion;

    public Cliente(int id_cliente, String nombre, String telefono, String ubicacion) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
