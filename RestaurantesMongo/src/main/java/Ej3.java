
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import datos.RestaurantesDAO;
import java.util.ArrayList;
import java.util.List;
import objetos.Categoria;
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
public class Ej3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Usar la clase RestaurantesDAO para consultar restaurantes por categoría
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();
        List<Restaurante> restaurantesPizza = restaurantesDAO.consultarPorCategoria("Pizza");
        
        // Imprimir los restaurantes encontrados
        if (restaurantesPizza.isEmpty()) {
            System.out.println("No se encontraron restaurantes con la categoría 'Pizza'.");
        } else {
            for (Restaurante restaurante : restaurantesPizza) {
                System.out.println("Restaurante: " + restaurante.getNombre());
                System.out.println("Rating: " + restaurante.getRating());
                System.out.println("Fecha de inauguración: " + restaurante.getFechainauguracion());
                System.out.println("Categorías: ");
                for (Categoria categoria : restaurante.getCategorias()) {
                    System.out.println(" - " + categoria.getNombre());
                }
                System.out.println("----------------------");
            }
        }
    }
    
}
