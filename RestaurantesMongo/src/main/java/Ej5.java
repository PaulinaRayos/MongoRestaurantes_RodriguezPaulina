
import datos.CategoriasDAO;
import datos.RestaurantesDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Categoria;
import objetos.Restaurante;
import org.bson.types.ObjectId;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author pauli
 */
public class Ej5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        
        // Crear una nueva categoría (por ejemplo, "Comida Asiática")
        Categoria nuevaCategoria = new Categoria("Comida Asiática", new ObjectId());
        
        // Buscar el restaurante "Sushilito"
        List<Restaurante> restaurantesSushilito = restaurantesDAO.consultarRestaurantesPorNombre("Sushilito");

        if (!restaurantesSushilito.isEmpty()) {
            Restaurante restauranteSushilito = restaurantesSushilito.get(0);  // Obtener el primer restaurante de la lista
            // Agregar la categoría al restaurante
            boolean exito = restaurantesDAO.agregarCategoriaARestaurante(restauranteSushilito, nuevaCategoria);
            
            if (exito) {
                System.out.println("Categoría 'Comida Asiática' agregada correctamente a 'Sushilito'.");
            } else {
                System.out.println("No se pudo agregar la categoría a 'Sushilito'.");
            }
        } else {
            // Si no existe, lo creamos
            Restaurante nuevoRestaurante = new Restaurante("Sushilito", 5, new Date(), new ArrayList<>());
            nuevoRestaurante.getCategorias().add(nuevaCategoria);  // Agregar la categoría inicial al crear el restaurante

            // Agregar el nuevo restaurante a la base de datos
            ObjectId idRestaurante = restaurantesDAO.agregar(nuevoRestaurante);
            
            if (idRestaurante != null) {
                System.out.println("Restaurante 'Sushilito' creado y categoría 'Comida Asiática' agregada.");
            } else {
                System.out.println("Hubo un problema al crear el restaurante 'Sushilito'.");
            }
        }
    }
    
}
