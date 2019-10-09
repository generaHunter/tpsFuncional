/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

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

/**
 *
 * @author JoseM
 */
public class NotaController {
    DB conn;
            
    public void insertar_nota(Alumno NotaAlumno,Materia NotaMateria,Usuario NotaUsuario,Periodo NotaPeriodo,Calificacion NotaNueva)throws SQLException
    {
        conn=new DB();
            CallableStatement pstm;
        try {
            pstm = conn.getConection().prepareCall("{call insertar_nota(?,?,?,?,?)}");
                    
                        pstm.setInt(1, Integer.parseInt(NotaAlumno.getIdAlumno().toString()));
                        pstm.setInt(2, Integer.parseInt(NotaMateria.getIdMateria().toString()));
                        pstm.setInt(3, Integer.parseInt(NotaUsuario.getIdUsuario().toString()));
                        pstm.setString(4, NotaPeriodo.getPeriodo());
                        pstm.setDouble(5, NotaNueva.getCalificacion());
                        pstm.execute();
                        conn.getConection().close();
        } catch (SQLException ex) {
            Logger.getLogger(NotasForm.class.getName()).log(Level.SEVERE, null, ex);     
        }
    }
}
