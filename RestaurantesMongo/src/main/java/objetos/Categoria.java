/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class Categoria {
    private ObjectId id;
    private String nombre;
    private ObjectId idRestaurante;

    public Categoria() {
    }

    public Categoria(ObjectId id, String nombre, ObjectId idRestaurante) {
        this.id = id;
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }

    public Categoria(String nombre, ObjectId idRestaurante) {
        this.nombre = nombre;
        this.idRestaurante = idRestaurante;
    }

    public Categoria(ObjectId id) {
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

    public ObjectId getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(ObjectId idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", idRestaurante=" + idRestaurante + '}';
    }

    
    
    
}
