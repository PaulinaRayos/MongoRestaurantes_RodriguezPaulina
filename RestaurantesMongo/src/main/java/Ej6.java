
import datos.RestaurantesDAO;
import org.bson.types.ObjectId;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author pauli
 */
public class Ej6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();

        ObjectId idRestaurante = new ObjectId("672aec854c34890ba3072f8c");  

        // Eliminar el restaurante por su identificador
        boolean exito = restaurantesDAO.eliminar(idRestaurante);

        if (exito) {
            System.out.println("El restaurante fue eliminado exitosamente.");
        } else {
            System.out.println("Hubo un error al eliminar el restaurante.");
        }
    }
}
