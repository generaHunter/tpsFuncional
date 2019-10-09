/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author JoseM
 */
public class DB {
    
    private Connection conn=null;
    private  String user="system";
    private  String pass="admin";
    private  String url="jdbc:oracle:thin:@localhost:1521:xe"; 
    
    public DB()
    {
         try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn=DriverManager.getConnection(url,user,pass);
                conn.setAutoCommit(false);
//                if (conn!=null)
//                {
//                    System.out.println("Conexion Exitosa");
//                }
//                else
//                {
//                     System.out.println("Error en la conexión");
//                }                   
               
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Error en la conexión de la base de datos"+ex.toString());
            }
    }
    
    public  Connection getConection()
    {
        return conn;
    }
    
    public void desconexion()
    {
            try {
                conn.close();
                
            } catch (Exception ex) {
                System.out.println("Error en la desconexion");
            }
    }
    
//    public static void main(String[] args) {
////        try {
////                Class.forName("oracle.jdbc.driver.OracleDriver");
////                Connection  conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhot:1521:orcl","escuela","admin");
////            } catch (SQLException | ClassNotFoundException ex) {
////                System.out.println("Error en la conexión de la base de datos");
////            }
//        DB d = new DB();
//        d.getConection();
//    }
}
