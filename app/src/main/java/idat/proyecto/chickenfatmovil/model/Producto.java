package idat.proyecto.chickenfatmovil.model;

public class Producto {
    private int id_prod;
    private String nombre;
    private Double costo;
    private String categ;

    public Producto(int id_prod, String nombre, Double costo, String categ) {
        this.id_prod = id_prod;
        this.nombre = nombre;
        this.costo = costo;
        this.categ = categ;
    }



    public int getId_prod() {
        return id_prod;
    }

    public String getNombre() {
        return nombre;
    }
    public Double getCosto() {
        return costo;
    }
    public String getCateg() {
        return categ;
    }
}
