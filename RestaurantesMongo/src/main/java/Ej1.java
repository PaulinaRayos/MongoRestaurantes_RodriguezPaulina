
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import datos.RestaurantesDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import objetos.Categoria;
import objetos.Restaurante;
import org.bson.Document;
import org.bson.types.ObjectId;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author pauli
 */
public class Ej1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear instancia de RestaurantesDAO para insertar los restaurantes
        RestaurantesDAO restaurantesDAO = new RestaurantesDAO();
        
        // Crear 3 restaurantes
        Restaurante restaurante1 = new Restaurante("Sushilito", 5, new Date(), null);
        Restaurante restaurante2 = new Restaurante("PastaMania", 4, new Date(), null);
        Restaurante restaurante3 = new Restaurante("BurgerKing", 4, new Date(), null);

        // Insertar los restaurantes para obtener sus ObjectId
        ObjectId idRestaurante1 = restaurantesDAO.agregar(restaurante1);
        ObjectId idRestaurante2 = restaurantesDAO.agregar(restaurante2);
        ObjectId idRestaurante3 = restaurantesDAO.agregar(restaurante3);

        // Crear categorías y asociarles el ObjectId del restaurante
        Categoria sushi = new Categoria("Sushi", idRestaurante1);
        Categoria tempura = new Categoria("Tempura", idRestaurante1);
        Categoria pasta = new Categoria("Pasta", idRestaurante2);
        Categoria pizza = new Categoria("Pizza", idRestaurante2);
        Categoria hamburguesa = new Categoria("Hamburguesa", idRestaurante3);
        Categoria frita = new Categoria("Frita", idRestaurante3);

        // Asignar las categorías a cada restaurante
        restaurante1.setCategorias(Arrays.asList(sushi, tempura));
        restaurante2.setCategorias(Arrays.asList(pasta, pizza));
        restaurante3.setCategorias(Arrays.asList(hamburguesa, frita));

        // Actualizar los restaurantes con las categorías asociadas
        restaurantesDAO.actualizar(restaurante1);
        restaurantesDAO.actualizar(restaurante2);
        restaurantesDAO.actualizar(restaurante3);

        System.out.println("Tres restaurantes insertados correctamente con sus categorías.");
    }

}
