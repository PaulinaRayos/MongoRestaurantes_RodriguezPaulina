/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import interfaces.ICategoriasDAO;
import java.util.ArrayList;
import java.util.List;
import objetos.Categoria;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class CategoriasDAO implements ICategoriasDAO {

    private MongoCollection<Categoria> getCollection() {
        ConexionBD conexion = new ConexionBD();
        MongoDatabase database = conexion.crearConexion();
        MongoCollection<Categoria> collection = database.getCollection("categorias", Categoria.class);
        return collection;
    }

    public boolean agregar(Categoria c) {
        try {
            this.getCollection().insertOne(c);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Categoria c) {
        try {
            UpdateResult result = this.getCollection().updateOne(
                eq("_id", c.getId()), 
                combine(
                    set("nombre", c.getNombre()),
                    set("idRestaurante", c.getIdRestaurante())
                )
            );
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Categoria c) {
        try {
            DeleteResult result = this.getCollection().deleteOne(eq("_id", c.getId()));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Categoria consultar(ObjectId id) {
        try {
            Categoria result = this.getCollection().find(eq("_id", id)).first();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categoria> consultarTodos() {
        try {
            List<Categoria> categorias = new ArrayList<>();
            for (Categoria c : this.getCollection().find()) {
                categorias.add(c);
            }
            return categorias;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    
}
