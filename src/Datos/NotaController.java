/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import ConexionDB.Conexion;
import Presentacion.NotasForm;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Datos.DB;
import Logica_Negocio.Alumno;
import Logica_Negocio.Calificacion;
import Logica_Negocio.Materia;
import Logica_Negocio.Nota;
import Logica_Negocio.Periodo;
import Logica_Negocio.Usuario;
import Logica_Negocio.Nota;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoseM
 */
public class NotaController {
    DB conn;
    Conexion conR ;
            
    public void insertar_nota(Alumno NotaAlumno,Materia NotaMateria,Usuario NotaUsuario,Periodo NotaPeriodo,Nota nota)throws SQLException
    {
        conn=new DB();
        //conR = new Conexion();
            CallableStatement pstm;
        try {
            pstm = conn.getConection().prepareCall("{call insertar_nota(?,?,?,?,?,?,?)}");
             //pstm = conR.getConnection().prepareCall("{call insertar_nota(?,?,?,?,?,?,?)}");
                    
                        pstm.setInt(1, Integer.parseInt(NotaAlumno.getIdAlumno().toString()));
                        pstm.setInt(2, Integer.parseInt(NotaMateria.getIdMateria().toString()));
                        pstm.setInt(3, Integer.parseInt(NotaUsuario.getIdUsuario().toString()));
                        pstm.setString(4, NotaPeriodo.getPeriodo());
                        pstm.setDouble(5, nota.getNota1());
                        pstm.setDouble(6, nota.getNota2());
                        pstm.setDouble(7, nota.getNota3());
                        pstm.execute();
                        
                        conn.getConection().close();
                         //conR.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasForm.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
    
    public void Consultar(JTable tabla,DefaultTableModel tablaNota)
    {
         conn=new DB(); 
         tablaNota.setRowCount(0);
         String sql ="SELECT id_nota,u.nombre,a.id_alumno,a.nombre ||' '|| a.apellido,m.materia,ROUND(SUM(nota1+nota2+nota3)/3,2) AS Primer_Periodo FROM nota n\n" +
        "JOIN periodo p ON n.id_periodo=p.id_periodo\n" +
        "JOIN materia_usuario mu ON n.id_matusu=mu.id_matusu\n" +
        "JOIN materia m ON mu.id_materia=m.id_materia\n" +
        "JOIN alumno a ON n.id_alumno=a.id_alumno\n" +
        "JOIN usuario u ON mu.id_usuario=u.id_usuario\n" +
        "WHERE p.id_periodo=1\n" +
        "GROUP BY id_nota,a.id_alumno,u.nombre,a.nombre ||' '|| a.apellido,m.materia\n" +
        "ORDER BY id_nota";
        Statement st = null ;
        ResultSet rs = null ;
        String[] filas = new String[10];
        Statement sta = null ;
        ResultSet rsa = null ;
        Statement sta3 = null ;
        ResultSet rsa3 = null ;
        boolean f1=false;
        boolean f2=false;
        boolean f3=false;
         try {
         
          st = conn.getConection().createStatement();
          rs = st.executeQuery(sql);
        
         while (rs.next()) {                                 
                   for (int i = 0; i < 6; i++) {
                       filas[i] = rs.getString(i+1);
                       f1=true;
                       //JOptionPane.showMessageDialog(null, "primer "+rs.getString(2));
                       if(i>=5)
                       {
                           i=6;
                            String sqla ="SELECT id_nota,ROUND(SUM(nota1+nota2+nota3)/3,2) AS Segundo_Periodo FROM nota n\n" +
                            "JOIN periodo p ON n.id_periodo=p.id_periodo\n" +
                            "JOIN materia_usuario mu ON n.id_matusu=mu.id_matusu\n" +
                            "JOIN materia m ON mu.id_materia=m.id_materia\n" +
                            "JOIN alumno a ON n.id_alumno=a.id_alumno\n" +
                            "WHERE p.id_periodo=2 AND n.id_alumno="+rs.getString(3)+"\n" +
                            "GROUP BY id_nota\n" +
                            "ORDER BY id_nota";
                            sta = conn.getConection().createStatement();
                            rsa = sta.executeQuery(sqla);
                           while (rsa.next()) {  
                             JOptionPane.showMessageDialog(null, ""+rsa.getString(1));
                                for (int j = 0; j < 2; j++) {
                                     //JOptionPane.showMessageDialog(null, "seg"+rsa.getString(2));
                                    filas[j+i] = rsa.getString(j+1);
                                    
                                    if(j>=1)
                                    {
                                        j=2;
                                        String sqla3 ="SELECT id_nota,ROUND(SUM(nota1+nota2+nota3)/3,2) AS Tercer_Periodo FROM nota n\n" +
                                        "JOIN periodo p ON n.id_periodo=p.id_periodo\n" +
                                        "JOIN materia_usuario mu ON n.id_matusu=mu.id_matusu\n" +
                                        "JOIN materia m ON mu.id_materia=m.id_materia\n" +
                                        "JOIN alumno a ON n.id_alumno=a.id_alumno\n" +
                                        "WHERE p.id_periodo=21 AND n.id_alumno="+rs.getString(3)+"\n" +
                                        "GROUP BY id_nota\n" +
                                        "ORDER BY id_nota";
                                        sta3 = conn.getConection().createStatement();
                                        rsa3 = sta3.executeQuery(sqla3);
                                        
                                        while(rsa3.next())
                                        {
                                            // JOptionPane.showMessageDialog(null, "tercero"+rsa3.getString(2));
                                            for (int k = 0; k < 2; k++) {
                                                 filas[k+j+i] = rsa3.getString(k+1);
                                            }
                                            if(rsa3.next())
                                            {
                                                
                                            }
                                             //tablaNota.addRow(filas);
                                        }
                                    }
                                  }
                          }
                       }
                     } 
//                   if(f1)
//                   {
                       tablaNota.addRow(filas);
//                   }
                   
                }
         tabla.setModel(tablaNota);         
         conn.getConection().close();
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.toString());
     }
  
    }
    
    public void detalleNota(JTable tabla,int idNotaP1,int idNotaP2,int idNotaP3,DefaultTableModel tablaNota)
    {
         conn=new DB();
         tablaNota.setRowCount(0);
         String sql ="SELECT id_nota,p.periodo,nota1,nota2,nota3 From nota n\n" +
        "JOIN periodo p ON n.id_periodo=p.id_periodo\n" +
        "WHERE id_nota="+idNotaP1+" OR id_nota="+idNotaP2+" OR id_nota="+idNotaP3;
        Statement st = null ;
        ResultSet rs = null ;
        String[] filas = new String[5];
        double[] notas=new double[1];
         try {
        st = conn.getConection().createStatement();
        rs = st.executeQuery(sql);
            while(rs.next())
            {
                //JOptionPane.showMessageDialog(null, "notass"+rs.getString(2));
                for (int i = 0; i < 5; i++) {
                    filas[i] = rs.getString(i+1).toString();          
                }
                 tablaNota.addRow(filas);
            }    
         conn.getConection().close();
         } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.toString());
     }
    }
    
        public void EditarNota(Nota nota)throws SQLException
    {
        conn=new DB();
        //conR = new Conexion();
            CallableStatement pstm;
        try {
            pstm = conn.getConection().prepareCall("{call edit_nota(?,?,?,?)}");
                    
                pstm.setInt(1, Integer.parseInt(nota.getIdNota().toString()));
                pstm.setDouble(2, nota.getNota1());
                pstm.setDouble(3, nota.getNota2());
                pstm.setDouble(4, nota.getNota3());
                pstm.execute();

                conn.getConection().close();
                 //conR.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasForm.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
}
