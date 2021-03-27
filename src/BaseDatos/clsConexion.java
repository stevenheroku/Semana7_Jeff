/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jefferson Rueda
 */
public class clsConexion {
    
     public Connection conexion;
    private final String servidor_conexion = "jdbc:mysql://127.0.0.1/nombres_p";
    static private final String usuario = "root";
    static private final String clave = "";
    private final String  enlistar ="SELECT * FROM nombres ";
    
    
    
    
    public void AbrirConexion(){
    try{
        conexion = (Connection) DriverManager.getConnection(servidor_conexion, usuario, clave);
        JOptionPane.showMessageDialog(null,"Conexión Exitosa");
    }catch(SQLException ex){
        System.out.println("Error: "+ex.getMessage());
    }
    }
    
    public void CerrarConexion(){
    try{
        conexion.close();
//        JOptionPane.showMessageDialog(null,"Conexión Cerrada");
    }catch(SQLException ex){
        System.out.println("Error: "+ex.getMessage());
    }
    }
    
    
    public ResultSet EjecutarSeleccion(String Sql) {

        AbrirConexion();

        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try {

            stmt = conexion.createStatement();
            rs = stmt.executeQuery(Sql);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException e) {
            throw new IllegalStateException("No se puede ejecutar el  query: " + Sql, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    CerrarConexion();

                }
            } catch (SQLException e) {
                return null;
             //   LOGGER.error("Ignored", e);
            }
        }

        return crs;

    }//end Seleccion
    

    public void ejecutarConsulta() {
try {
    AbrirConexion();
   // Connection con;
   String SQL = "SELECT * FROM nombres";
   Statement stmt = conexion.createStatement();
   ResultSet rs = stmt.executeQuery(SQL);

   while (rs.next()) {
   System.out.println(rs.getString("Nombre"));
}
  
  rs.close();
  stmt.close();
}
catch (Exception e) {
  e.printStackTrace();
}
}
    

    
}

