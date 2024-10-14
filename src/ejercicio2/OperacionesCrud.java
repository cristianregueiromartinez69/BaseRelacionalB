package ejercicio2;

import java.sql.*;

/**
 * Clase donde realizamos los metodos de las operaciones crud
 * @author cristian
 * @version 1.0
 */
public class OperacionesCrud {

    /**
     * Metodo para listar todos los elementos de una tabla
     */
    public void listarContenido() {

        //preparamos la consulta
        String consulta = "select * from produtos";

        try {
            //establecemos la conexion
            Connection con = EstablecerConexion.conectar();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(consulta);

            //mientras haya elementos los leemos
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String descricion = rs.getString("descricion");
                int prezo = rs.getInt("prezo");
                Date datac = rs.getDate("datac");

                System.out.println("Codigo: " + codigo + "\nDescricion: " + descricion
                        + "\nPrezo: " + prezo + "\nFecha de caducidad: " + datac + "\n");
            }

        } catch (SQLException e) {
            System.out.println("Ups, no se ha podido leer los registros de la base de datos");
        }

    }

    /**
     * Metodo para actualizar una fila de la tabla
     * @param codigo el codigo del producto
     * @param prezo el nuevo precio del producto
     */
    public void updateProdutos(String codigo, int prezo) {

        //preparamos la consulta
        String consulta = "select * from produtos where codigo = '" + codigo + "'";

        try {
            //establecemos la conexion
            Connection con = EstablecerConexion.conectar();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(consulta);

            //si encuentra la fila, actualizamos el campo
            if (rs.next()) {
                rs.updateInt("prezo", prezo);
                rs.updateRow();
                System.out.println("produto actualizado con exito");
            } else {
                System.out.println("No existe el codigo proporcionado");
            }

        } catch (SQLException e) {
            System.out.println("Ups, ha habido un error a la hora de actualizar el producto");
        }

    }

    /**
     * Metodo para insertar un nuevo elemento en la tabla productos
     * @param codigo el codigo del produto
     * @param descricion la descricion del produto
     * @param prezo el precio del produto
     * @param datac la fecha del produto
     */
    public void insertarProdutos(String codigo, String descricion, int prezo, Date datac) {

        //preparamos la consulta
        String consulta = "select * from produtos";
        
        try{
            //establecemos la conexion
            Connection conn = EstablecerConexion.conectar();
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = st.executeQuery(consulta);
            
            //nos movemos a la fila que queremos insertar
            rs.moveToInsertRow();
            //insertamos los valores y lo acabamos con el metodo insertRow()
            rs.updateString("codigo", codigo);
            rs.updateString("descricion", descricion);
            rs.updateInt("prezo", prezo);
            rs.updateDate("datac", datac);
            rs.insertRow();
            
            System.out.println("Elementos introducidos con exito en la tabla produtos");
            
        }catch(SQLException e){
            System.out.println("Ups, no se pudo insertar los datos en la tabla");
        }
    }
    
    /**
     * Metodo para borrar una fila de un producto
     * @param codigo el codigo del producto
     */
    public void borrarFila(String codigo){
        
        //preparamos la consulta
        String consulta = "select * from produtos where codigo = '" + codigo + "'";
        
        try{
            
            //establecemos la conexion
            Connection con = EstablecerConexion.conectar();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = st.executeQuery(consulta);
            
            //si hay coincidencias, borramos la fila con el codigo dado
            if(rs.next()){
                rs.deleteRow();
                System.out.println("Fila borrada con éxito");
            }
            else{
                System.out.println("No se encontro la fila a borrar");
            }
            
        }catch(SQLException e){
            System.out.println("ups, no se pudo borrar el registro");
        }
        
    }

}
