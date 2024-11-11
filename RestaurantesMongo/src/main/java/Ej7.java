
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
public class Ej7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();

        // Consultar los restaurantes con rating menor a 3
        List<Restaurante> restaurantesAEliminar = restaurantesDAO.consultarRestaurantesPorRatingMenorA(3);

        // Verificar si no se encontraron restaurantes con rating menor a 3
        if (restaurantesAEliminar.isEmpty()) {
            System.out.println("No se encontraron restaurantes con rating menor a 3.");
        } else {
            // Eliminar cada restaurante con rating menor a 3
            for (Restaurante restaurante : restaurantesAEliminar) {
                // Llamar al método eliminar usando el ObjectId del restaurante
                boolean exito = restaurantesDAO.eliminar(restaurante.getId());

                if (exito) {
                    System.out.println("El restaurante " + restaurante.getNombre() + " fue eliminado exitosamente.");
                } else {
                    System.out.println("Hubo un error al eliminar el restaurante " + restaurante.getNombre());
                }
            }
        }
    }
    
}
