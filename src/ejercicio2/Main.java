
package ejercicio2;
import java.sql.*;

/**
 * Clase main donde ejecutamos la aplicacion
 * @author cristian
 * @version 1.0
 */
public class Main {

  
    public static void main(String[] args) {
        
        //instanciamos el objeto de los metodos y los ejecutamos
        OperacionesCrud oc = new OperacionesCrud();
        
        
        System.out.println("Listamos todos los elementos:");
        oc.listarContenido();
        
        System.out.println("Actualizamos un produto");
        oc.updateProdutos("p2", 8);
        
        System.out.println("Insertamos un nuevo elemento");
        oc.insertarProdutos("p4", "martelo", 20, null);
        
        System.out.println("Borramos un elemento");
        oc.borrarFila("p3");
        
        
        
        

    }
    
}
