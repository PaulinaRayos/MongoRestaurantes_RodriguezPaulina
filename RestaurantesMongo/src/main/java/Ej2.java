
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import datos.RestaurantesDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Restaurante;
import org.bson.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pauli
 */
public class Ej2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();
        
        // Consultar restaurantes con más de 4 estrellas
        int ratingMinimo = 4;  
        List<Restaurante> restaurantesConMasDe4Estrellas = restaurantesDAO.consultarRestaurantesPorRatingMayorA(ratingMinimo);
        
        // Mostrar los resultados
        for (Restaurante restaurante : restaurantesConMasDe4Estrellas) {
            System.out.println("Restaurante: " + restaurante.getNombre() + " | Rating: " + restaurante.getRating());
        }
    }

}
