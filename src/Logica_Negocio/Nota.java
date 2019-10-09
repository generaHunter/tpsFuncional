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
public class Nota {
     private BigDecimal idNota;
     private BigDecimal idMatusu;
     private BigDecimal idPeriodo;
     private BigDecimal idCalificacion;
    
    public Nota(BigDecimal idNota,BigDecimal idMatusu,BigDecimal idPeriodo,BigDecimal idCalificacion) {
    this.idNota = idNota;
    this.idMatusu=idMatusu;
    this.idPeriodo=idPeriodo;
    this.idCalificacion=idCalificacion;
    }
    
    public BigDecimal getIdNota() {
        return idNota;
    }

    public void setIdNota(BigDecimal idGrado) {
        this.idNota = idNota;
    }
    
    public BigDecimal getIdMatusu() {
        return idMatusu;
    }

    public void setIdMatusu(BigDecimal idMatusu) {
        this.idMatusu = idMatusu;
    }

    public BigDecimal getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(BigDecimal idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    
    public BigDecimal getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(BigDecimal idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
 

}
