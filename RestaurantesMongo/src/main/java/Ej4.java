
import datos.RestaurantesDAO;
import java.util.List;
import objetos.Restaurante;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author pauli
 */
public class Ej4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();
        
        // Consultar restaurantes cuyo nombre contiene "sushi"
        String palabra = "sushi";  
        List<Restaurante> restaurantesConSushiEnNombre = restaurantesDAO.consultarRestaurantesPorNombre(palabra);
        
        // Mostrar los resultados
        for (Restaurante restaurante : restaurantesConSushiEnNombre) {
            System.out.println("Restaurante: " + restaurante.getNombre() + " | Rating: " + restaurante.getRating());
        }
    }
    
}
