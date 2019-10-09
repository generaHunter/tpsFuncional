/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloClases;

import ConexionDB.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class InsertarMatricula {
    
    Conexion con;
    Matricula matriculaInsert;
    String Res;
    
    CallableStatement cat;
    ResultSet r;
    Statement sentencia;

    public InsertarMatricula() {
       con = new Conexion();
    }
    
    public void agregarMatricula(Matricula matriculaAdd) throws SQLException{
    
        
        //PreparedStatement pstm;
       // pstm = con.getConnection().prepareStatement("{call insertar_matricula(?,?,?,?)}");
    //{
       // pstm.setInt(1, matriculaAdd.getIDAlumno());
       // pstm.setString(2, matriculaAdd.getTurnoName());
       // pstm.setString(3, matriculaAdd.getGradoName());
       // pstm.get
    //}
    
    
           // Connection cn = null;
     // Carga el driver de oracle
           // DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            // Conecta con la base de datos XE con el usuario system y la contraseña password
            //cn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.1:1521:XE", "sys as sysdba", "admin");
            
            //if(cn != null){
            //System.out.println("Conexion Exitosa");
            //}
            
           System.out.println(matriculaAdd.getGradoName()); 
           System.out.println(matriculaAdd.getIDAlumno()); 
           System.out.println(matriculaAdd.getTurnoName()); 
    
   // CallableStatement cst = cn.prepareCall("{call insertar_matricula(?,?,?,?)}");
   
   CallableStatement cst = con.getConnection().prepareCall("{call insertar_matricula(?,?,?,?)}");
    
    cst.setInt(1, matriculaAdd.getIDAlumno());
    cst.setString(2, matriculaAdd.getTurnoName());
    cst.setString(3, matriculaAdd.getGradoName());  
    cst.registerOutParameter(4, java.sql.Types.VARCHAR);
    
    // Ejecuta el procedimiento almacenado
                cst.execute();
                
       int resultado  = cst.getInt(4);
       
     //System.out.println("Resultado: " + resultado);
     
        if (resultado == 1) {
           JOptionPane.showMessageDialog(null, "Se ha matriculado correctamente al alumno");
        }
        else if (resultado == 2){
         JOptionPane.showMessageDialog(null, "Este alumno ya se encuentra matriculado");
        }
       
       
       con.getConnection().close();
    }

  }
    
    
    
    
    
    
    

