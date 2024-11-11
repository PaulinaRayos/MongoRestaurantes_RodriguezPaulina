/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class Restaurante {
    
    private ObjectId id;
    private String nombre;
    private int rating;
    private Date fechainauguracion;
    private List<Categoria> categorias;

    public Restaurante() {
    }
    
    
    public Restaurante(String nombre, int rating, Date fechainauguracion, List<Categoria> categorias) {
        this.nombre = nombre;
        this.rating = rating;
        this.fechainauguracion = fechainauguracion;
        this.categorias = categorias;
    }

    public Restaurante(ObjectId id, String nombre, int rating, Date fechainauguracion, List<Categoria> categorias) {
        this.id = id;
        this.nombre = nombre;
        this.rating = rating;
        this.fechainauguracion = fechainauguracion;
        this.categorias = categorias;
    }

    public Restaurante(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getFechainauguracion() {
        return fechainauguracion;
    }

    public void setFechainauguracion(Date fechainauguracion) {
        this.fechainauguracion = fechainauguracion;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "id=" + id + ", nombre=" + nombre + ", rating=" + rating + ", fechainauguracion=" + fechainauguracion + ", categorias=" + categorias + '}';
    }



    
}
