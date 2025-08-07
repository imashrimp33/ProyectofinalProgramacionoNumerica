
package Modelo;

public class Producto {
    private int idproducto;
    private String nombreproducto;
    private double precioproducto;
    private int cantidad;
    
    

    public int getId() {
        return idproducto;
    }

    public void setId(int id) {
        this.idproducto = id;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public double getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(double precioproducto) {
        this.precioproducto = precioproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}

