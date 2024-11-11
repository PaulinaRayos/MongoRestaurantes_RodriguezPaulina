/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Categoria;
import objetos.Restaurante;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;

/**
 *
 * @author pauli
 */
public class Restaurantes {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public Restaurantes() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("restaurantes");
        collection = database.getCollection("restaurantes");
    }

    private Document objectToDocument(Restaurante r) {
        List<Document> categoriasDocs = new ArrayList<>();
        for (Categoria c : r.getCategorias()) {
            categoriasDocs.add(new Document("nombre", c.getNombre()));
        }

        return new Document()
                .append("nombre", r.getNombre())
                .append("rating", r.getRating())
                .append("fechainauguracion", r.getFechainauguracion())
                .append("categorias", categoriasDocs);
    }

    public ObjectId insertRestaurant(Restaurante r) {
         try {
            // Asegúrate de que la fecha esté correctamente establecida
            if (r.getFechainauguracion() == null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = dateFormat.parse("2024-01-01");  // Fecha predeterminada
                r.setFechainauguracion(fecha);
            }

            // Ahora inserta el restaurante con la fecha correcta
            ObjectId id = new ObjectId();  // Genera un nuevo ObjectId
            Document restaurante = objectToDocument(r).append("_id", id); // Crea el documento
            collection.insertOne(restaurante);  // Inserta el documento
            System.out.println("Inserted document with id: " + id);  // Confirmación
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }

    public boolean updateRestaurant(Restaurante r) {
        try {
            Bson idQuery = Filters.eq("_id", r.getId());
            Bson updates = Updates.combine(
                    Updates.set("nombre", r.getNombre()),
                    Updates.set("rating", r.getRating()),
                    Updates.set("fechainauguracion", r.getFechainauguracion())
            );
            UpdateResult result = collection.updateOne(idQuery, updates);
            System.out.println(result.getModifiedCount());
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRestaurantById(ObjectId id) {
        try {
            Bson idQuery = Filters.eq("_id", id);
            DeleteResult result = collection.deleteOne(idQuery);
            System.out.println(result.getDeletedCount());
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Restaurante> getAllRestaurants() {
    ArrayList<Restaurante> restaurantes = new ArrayList<>();

    MongoCursor<Document> cursor = collection.find().iterator();
    try {
        while (cursor.hasNext()) {
            Document d = cursor.next();
            
            // Extraer categorías del documento
            List<Document> categoriasDocs = (List<Document>) d.get("categorias");
            List<Categoria> categorias = new ArrayList<>();
            for (Document catDoc : categoriasDocs) {
                ObjectId id = catDoc.getObjectId("_id");
                String nombre = catDoc.getString("nombre");
                ObjectId idRestaurante = catDoc.getObjectId("idRestaurante");
                categorias.add(new Categoria(id, nombre, idRestaurante));
            }

            Restaurante r = new Restaurante(
                    d.getObjectId("_id"),
                    d.getString("nombre"),
                    d.getInteger("rating"),
                    d.getDate("fechainauguracion"),
                    categorias
            );
            
            System.out.println(r.toString());
            restaurantes.add(r);
        }
        return restaurantes;
    } finally {
        cursor.close();
    }
}

    public void checkDatabase() {
        MongoIterable<String> collections = database.listCollectionNames();
        System.out.println("Colecciones en la base de datos 'miBD':");
        for (String collectionName : collections) {
            System.out.println(" - " + collectionName);
        }
    }
    public List<Restaurante> consultarRestaurantesPorCategoria(ObjectId id) {
        MongoCollection<Document> collection = database.getCollection("restaurantes");
        MongoCursor<Document> cursor = collection.find(new Document("categorias.nombre", id)).iterator();

        List<Restaurante> restaurantes = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                // Obtener las categorías del restaurante
                List<Document> categoriasDoc = (List<Document>) doc.get("categorias");
                List<Categoria> categorias = new ArrayList<>();
                for (Document categoriaDoc : categoriasDoc) {
                    categorias.add(new Categoria(categoriaDoc.getObjectId("nombre")));
                }

                // Crear objeto Restaurante
                Restaurante restaurante = new Restaurante(
                        doc.getString("nombre"),
                        doc.getInteger("rating"),
                        doc.getDate("fechainauguracion"),
                        categorias
                );
                restaurantes.add(restaurante);
            }
        } finally {
            cursor.close();
        }

        return restaurantes;
    }
    
    // Método para consultar restaurantes por categoría
    public List<Restaurante> consultarPorCategoria(String categoriaNombre) {
        List<Restaurante> restaurantes = new ArrayList<>();
        
        // Consulta para obtener restaurantes que tengan la categoría 'categoriaNombre'
        MongoCursor<Document> cursor = collection.find(
            new Document("categorias.nombre", categoriaNombre)
        ).iterator();
        
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                // Mapear los datos del documento a un objeto Restaurante
                List<Categoria> categorias = new ArrayList<>();
                List<Document> categoriasDocs = (List<Document>) doc.get("categorias");
                for (Document categoriaDoc : categoriasDocs) {
                    Categoria categoria = new Categoria();
                    categoria.setNombre(categoriaDoc.getString("nombre"));
                    categorias.add(categoria);
                }
                
                // Crear el restaurante
                Restaurante restaurante = new Restaurante(
                    doc.getObjectId("_id"),
                    doc.getString("nombre"),
                    doc.getInteger("rating"),
                    doc.getDate("fechainauguracion"),
                    categorias
                );
                restaurantes.add(restaurante);
            }
        } finally {
            cursor.close();
        }
        
        return restaurantes;
    }
    
}