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
import java.util.ArrayList;
import objetos.Categoria;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class Categorias {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public Categorias() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("restaurantes");
        collection = database.getCollection("categorias");
    }

    private Document objectToDocument(Categoria c) {
        return new Document()
                .append("nombre", c.getNombre())
                .append("idRestaurante", c.getIdRestaurante());
    }

    public ObjectId insertCategoria(Categoria c) {
        try {
            ObjectId id = new ObjectId();  // Genera un nuevo ObjectId
            Document categoriaDoc = objectToDocument(c).append("_id", id); // Crea el documento
            collection.insertOne(categoriaDoc);  // Inserta el documento
            System.out.println("Inserted categoria with id: " + id);  // Confirmación
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateCategoria(Categoria c) {
        try {
            Bson idQuery = Filters.eq("_id", c.getId());
            Bson updates = Updates.combine(
                    Updates.set("nombre", c.getNombre()),
                    Updates.set("idRestaurante", c.getIdRestaurante())
            );
            UpdateResult result = collection.updateOne(idQuery, updates);
            System.out.println(result.getModifiedCount());
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategoriaById(ObjectId id) {
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

    public ArrayList<Categoria> getAllCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Categoria c = new Categoria(
                        d.getObjectId("_id"),
                        d.getString("nombre"),
                        d.getObjectId("idRestaurante")
                );
                
                System.out.println(c.toString());
                categorias.add(c);
            }
            return categorias;
        } finally {
            cursor.close();
        }
    }

    public Categoria getCategoriaById(ObjectId id) {
        try {
            Document doc = collection.find(Filters.eq("_id", id)).first();
            if (doc != null) {
                return new Categoria(
                        doc.getObjectId("_id"),
                        doc.getString("nombre"),
                        doc.getObjectId("idRestaurante")
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void checkDatabase() {
        MongoIterable<String> collections = database.listCollectionNames();
        System.out.println("Colecciones en la base de datos 'restaurantes':");
        for (String collectionName : collections) {
            System.out.println(" - " + collectionName);
        }
    }
    
    
}
