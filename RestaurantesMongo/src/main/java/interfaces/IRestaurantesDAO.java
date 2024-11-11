/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Categoria;
import objetos.Restaurante;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public interface IRestaurantesDAO {
    
    ObjectId agregar(Restaurante r);
    
    boolean actualizar(Restaurante r);
    
    boolean eliminar(ObjectId id);
    
    Restaurante consultar(ObjectId id);
    
    List<Restaurante> consultarTodos();
    
    List<Restaurante> consultarRestaurantesPorRatingMayorA(int rating);
    
    List<Restaurante> consultarRestaurantesPorNombre(String nombre);
    
    public boolean agregarCategoriaARestaurante(Restaurante restaurante, Categoria categoria);
    
    public List<Restaurante> consultarRestaurantesPorRatingMenorA(int rating);
}
