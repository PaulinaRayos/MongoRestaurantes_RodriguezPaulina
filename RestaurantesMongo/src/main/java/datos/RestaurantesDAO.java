/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import interfaces.IRestaurantesDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Categoria;
import objetos.Restaurante;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class RestaurantesDAO implements IRestaurantesDAO {

    private MongoCollection<Restaurante> getCollection() {
        ConexionBD conexion = new ConexionBD();
        MongoDatabase database = conexion.crearConexion();
        return database.getCollection("restaurantes", Restaurante.class);
    }

    public ObjectId agregar(Restaurante r) {
        try {
            // Insertar el restaurante y obtener el resultado de la operación de inserción
            this.getCollection().insertOne(r);
            return r.getId();  // Retornar el ObjectId del restaurante insertado
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Retornar null en caso de error
        }
    }

    public boolean actualizar(Restaurante r) {
        try {
            UpdateResult result = this.getCollection().updateOne(
                    eq("_id", r.getId()),
                    combine(
                            set("nombre", r.getNombre()),
                            set("rating", r.getRating()),
                            set("fechainauguracion", r.getFechainauguracion()),
                            set("categorias", r.getCategorias())
                    )
            );
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(ObjectId id) {
        try {
            DeleteResult result = this.getCollection().deleteOne(eq("_id", id));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Restaurante consultar(ObjectId id) {
        try {
            return this.getCollection().find(eq("_id", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Restaurante> consultarTodos() {
        List<Restaurante> restaurantes = new ArrayList<>();
        try {
            for (Restaurante r : this.getCollection().find()) {
                restaurantes.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantes;
    }

    // Método para consultar restaurantes por categoría
    public List<Restaurante> consultarPorCategoria(String categoriaNombre) {
        List<Restaurante> restaurantes = new ArrayList<>();

        // Realizamos la consulta sobre la colección de restaurantes
        for (Restaurante restaurante : getCollection().find()) {
            // Verificamos si alguna de las categorías del restaurante coincide con la categoría buscada
            for (Categoria categoria : restaurante.getCategorias()) {
                if (categoria.getNombre().equals(categoriaNombre)) {
                    restaurantes.add(restaurante);
                    break;
                }
            }
        }

        return restaurantes;
    }

    public List<Restaurante> consultarRestaurantesPorRatingMayorA(int rating) {
        List<Restaurante> restaurantes = new ArrayList<>();

        // Crear un filtro para buscar restaurantes con rating mayor al parámetro
        Bson filtro = Filters.gt("rating", rating);

        // Realizar la consulta con el filtro
        for (Restaurante r : this.getCollection().find(filtro)) {
            restaurantes.add(r);
        }

        return restaurantes;
    }

    public List<Restaurante> consultarRestaurantesPorRatingMenorA(int rating) {
        List<Restaurante> restaurantes = new ArrayList<>();

        // Crear un filtro para buscar restaurantes con rating menor al parámetro
        Bson filtro = Filters.lt("rating", rating);

        // Realizar la consulta con el filtro
        for (Restaurante r : this.getCollection().find(filtro)) {
            restaurantes.add(r);
        }

        return restaurantes;
    }

    public List<Restaurante> consultarRestaurantesPorNombre(String nombre) {
        List<Restaurante> restaurantes = new ArrayList<>();

        // Crear un filtro con expresión regular que busque el nombre del restaurante
        Bson filtro = Filters.regex("nombre", ".*" + nombre + ".*", "i"); // "i" es para hacer la búsqueda insensible a mayúsculas y minúsculas

        // Realizar la consulta con el filtro
        for (Restaurante r : this.getCollection().find(filtro)) {
            restaurantes.add(r);
        }

        return restaurantes;
    }

    public boolean agregarCategoriaARestaurante(Restaurante restaurante, Categoria categoria) {
        try {
            // Verificar si el restaurante existe
            Restaurante restauranteExistente = this.getCollection().find(eq("_id", restaurante.getId())).first();

            if (restauranteExistente != null) {
                // Verificar si la categoría ya está asociada al restaurante
                boolean categoriaExiste = false;
                for (Categoria cat : restauranteExistente.getCategorias()) {
                    if (cat.getNombre().equals(categoria.getNombre())) {
                        categoriaExiste = true;
                        break;
                    }
                }

                // Si la categoría no existe en el restaurante, agregarla
                if (!categoriaExiste) {
                    // Agregar la nueva categoría al restaurante
                    restauranteExistente.getCategorias().add(categoria);

                    // Actualizar el restaurante con la nueva categoría
                    UpdateResult result = this.getCollection().updateOne(
                            eq("_id", restauranteExistente.getId()),
                            combine(set("categorias", restauranteExistente.getCategorias()))
                    );

                    return result.getModifiedCount() == 1;  // Si se modificó correctamente el restaurante
                } else {
                    System.out.println("La categoría ya existe en este restaurante.");
                    return false;
                }
            } else {
                System.out.println("El restaurante no existe.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
