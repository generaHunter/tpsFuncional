/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import java.math.BigDecimal;

/**
 *
 * @author JoseM
 */
public class Calificacion {
     private BigDecimal idCalificacion;
     private double Calificacion;
     
    public Calificacion()
    {
        
    }
    
    public Calificacion(BigDecimal idCalificacion,double Calificacion) {
        this.idCalificacion = idCalificacion;
        this.Calificacion=Calificacion;
    }
    
    public BigDecimal getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(BigDecimal idGrado) {
        this.idCalificacion = idCalificacion;
    }
    
    public double getCalificacion()
    {
        return Calificacion;
    }
    
    public void setCalificacion(double Calificacion)
    {
        this.Calificacion=Calificacion;
    }
}
