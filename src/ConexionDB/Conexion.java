/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronal
 */
public class Conexion {
    
     Connection  cn = null;
    private  String user="system";
    private  String pass="admin";
    private  String url="jdbc:oracle:thin:@localhost:1521:xe"; 
    public Conexion(){
     
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //cn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.1:1521:XE", "sys as sysdba", "admin");
            cn=DriverManager.getConnection(url,user,pass);
            
            if(cn != null){
            System.out.println("Conexion Exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Connection getConnection(){
    return cn;
    }
}
