/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import ConexionDB.Conexion;
import ConexionDB.ConexionSystem;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author ronal
 */
public class GenerarReportes {
    ConexionSystem con;
    
    public void reporte_listadoAlumnos(String id_turno, String id_grado){
    
        try {
            con = new ConexionSystem();
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject("listadoPrimerGrado.jasper");
            
            Map parametros = new HashMap();
            
            parametros.put("id_grado", id_grado);
            parametros.put("id_turno",id_turno);
            
            JasperPrint j = JasperFillManager.fillReport(reporte, parametros, con.getConnection());
            
            JasperViewer jv = new JasperViewer(j, false);
            
            jv.setTitle("Listado de alumnos");
            jv.setVisible(true);
            
            
                
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al mostrar el reporte: " + e);
        }
    
    }
    
}
