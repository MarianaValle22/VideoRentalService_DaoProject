package edu.unisabana.dyas.samples.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mariana Valle
 */

public class Item implements Serializable {
    private TipoItem tipo;
    private int id;
    private String nombre;
    private String descripcion;
    private String fechalanzamiento;  // Se almacena como String para evitar errores con MyBatis
    private double tarifaxdia;
    private String formatorenta;
    private String genero;

    public Item(TipoItem tipo, int id, String nombre, String descripcion, String fechalanzamiento, long tarifaxDia, String formatoRenta, String genero) {
        this.tipo = tipo;
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechalanzamiento = fechalanzamiento;
        this.tarifaxdia = tarifaxDia;
        this.formatorenta = formatoRenta;
        this.genero = genero;
    }

    public Item() {
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {  // Se corrige el nombre del método
        this.descripcion = descripcion;
    }

    public String getFechalanzamiento() {
        return fechalanzamiento;
    }

    public void setFechalanzamiento(String fechalanzamiento) {
        this.fechalanzamiento = fechalanzamiento;
    }

    /**
     * Convierte `fechalanzamiento` de String a Date.
     */
    public Date getFechaLanzamientoAsDate() throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(fechalanzamiento);
    }

    public double getTarifaxdia() {
        return tarifaxdia;
    }

    public void setTarifaxdia(double tarifaxDia) {
        this.tarifaxdia = tarifaxDia;
    }

    public String getFormatorenta() {
        return formatorenta;
    }

    public void setFormatorenta(String formatoRenta) {
        this.formatorenta = formatoRenta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tipo=" + tipo +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechalanzamiento='" + fechalanzamiento + '\'' +
                ", tarifaxDia=" + tarifaxdia +
                '}';
    }
}
