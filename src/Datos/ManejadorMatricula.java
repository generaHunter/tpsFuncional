/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica_Negocio.MatriculaProd;
import ConexionDB.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ronal
 */
public class ManejadorMatricula {
    
    Conexion con;
    MatriculaProd matriculaInsert;
    String Res;
    
    CallableStatement cat;
    ResultSet r;
    Statement sentencia;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
   

    public ManejadorMatricula() {
      
    }
    
    public void agregarMatricula(MatriculaProd matriculaAdd) throws SQLException{
     con = new Conexion();
        
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
            
           //System.out.println(matriculaAdd.getGradoName()); 
         //  System.out.println(matriculaAdd.getIDAlumno()); 
           //System.out.println(matriculaAdd.getTurnoName()); 
    
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
    
 public boolean ejecutar(String sql) {
      con = new Conexion();
       try {
        Statement sentencia = con.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,  ResultSet.CONCUR_READ_ONLY);
        sentencia.executeUpdate(sql);
        con.getConnection().commit();
          con.getConnection().close();
        sentencia.close();
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
return false;
}
 
 
 public ResultSet consultar(String sql) {
      con = new Conexion();
        ResultSet resultado = null;
        try {
           Statement sentencia;
            sentencia = con.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);
              con.getConnection().close();
            
            if (sentencia != null) {
                sentencia.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        return resultado;
    }
 
 public void extraerDatos(JTable tabla){
      con = new Conexion();
         DefaultTableModel tablaMatricula = null;
         String[] titulo = {"Id", "Nombre", "Grado", "Turno", "Fecha de Matriula"};
         tablaMatricula = new DefaultTableModel(null, titulo);
         
   
 
 String sql = "select m.id_matricula, CONCAT(a.nombre,CONCAT(' ', a.apellido)) as Nombre, g.grado as Grado, t.turno as Turno, m.fecha_matricula as \"Fecha de Matriculacion\" from SYSTEM.matricula m\n" +
"inner join SYSTEM.alumno a on a.id_alumno = m.id_alumno " +
"inner join SYSTEM.grado g on g.id_grado = m.id_grado " +
"inner join SYSTEM.turno t on t.id_turno = m.id_turno " +
"where m.estado = 1" ;
 
 Statement st = null ;
 ResultSet rs = null ;
 
 String[] filas = new String[5];
 
     try {
         
          st = con.getConnection().createStatement();
             rs = st.executeQuery(sql);
         while (rs.next()) {
             
            
             
                   // System.out.println(""+resultados.getBigDecimal("IDENTIFICADOR")+"       "+resultados.getString("DESCRIPCION"));
                   //Object fila[];
                   
                   
                   //fila = new Object[]{String.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), fechaTexto};
                  // tablaMatricula.addRow(fila);
                   
                   for (int i = 0; i < 5; i++) {
                 
                       if (i + 1 == 5) {
                        String fechaTexto = formatter.format(rs.getDate(5));
                        filas[i] = fechaTexto;
                       }else{
                       filas[i] = rs.getString(i +1);
                       }
                       
             }
                   tablaMatricula.addRow(filas);

               
                //tablaMatricula.setValueAt(resultados.getString("Nombre"), resultados.getRow(), 0);
                   
                   
                }
         tabla.setModel(tablaMatricula);
         
         con.getConnection().close();
              //return tablaMatricula;
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.toString());
     }
  

    // return tablaMatricula;        
 }
 
 
 
 //////////////////////////////////////////////Editar//////////////////////////////////////////////
 
     public void editarMatricula(MatriculaProd matriculaEdit) throws SQLException{
         
 con = new Conexion();
   CallableStatement cst = con.getConnection().prepareCall("{call editar_matricula(?,?,?,?,?)}");
    
    cst.setInt(1, matriculaEdit.getIdMatricula());
    cst.setInt(2, matriculaEdit.getOpcion());
    cst.setString(3, matriculaEdit.getTurnoName());  
    cst.setString(4, matriculaEdit.getGradoName());  
    cst.registerOutParameter(5, java.sql.Types.VARCHAR);
    
    // Ejecuta el procedimiento almacenado
                cst.execute();
                
       int resultado  = cst.getInt(5);
       
     //System.out.println("Resultado: " + resultado);
     
        if (resultado == 1) {
         //  JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente el registro");
        }
        else if (resultado == 2){
         //JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el registro");
        }
       
       
       con.getConnection().close();
    }
    
    
    

  }
    
    
    
    
    
    
    

