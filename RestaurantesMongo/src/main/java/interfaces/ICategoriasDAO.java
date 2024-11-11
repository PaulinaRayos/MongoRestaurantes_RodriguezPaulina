/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Categoria;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public interface ICategoriasDAO {

    boolean actualizar(Categoria c);

    boolean agregar(Categoria c);

    Categoria consultar(ObjectId id);

    List<Categoria> consultarTodos();

    boolean eliminar(Categoria c);
    
}
