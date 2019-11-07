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
     private BigDecimal idAlumno;
     private double nota1;
     private double nota2;
     private double nota3;
    
    public Nota(BigDecimal idNota,BigDecimal idMatusu,BigDecimal idPeriodo,BigDecimal idCalificacion,BigDecimal idAlumno,double nota1,double nota2,double nota3) {
    this.idNota = idNota;
    this.idMatusu=idMatusu;
    this.idPeriodo=idPeriodo;
    this.idCalificacion=idCalificacion;
    this.idAlumno=idAlumno;
    this.nota1=nota1;
    this.nota2=nota2;
    this.nota3=nota3;
    }
    
//    public void Nota(BigDecimal idNota,double nota1,double nota2,double nota3)
//    {
//        this.idNota = idNota;
//        this.nota1=nota1;
//        this.nota2=nota2;
//        this.nota3=nota3;
//    }
    
    public Nota()
    {
        
    }
    
    public BigDecimal getIdNota() {
        return idNota;
    }

    public void setIdNota(BigDecimal idNota) {
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
    
    public BigDecimal getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(BigDecimal idAlumno) {
        this.idAlumno = idAlumno;
    }
 
    public double getNota1()
    {
        return nota1;
    }
    
    public void setNota1(double nota1)
    {
        this.nota1=nota1;
    }
    
        public double getNota2()
    {
        return nota2;
    }
    
    public void setNota2(double nota2)
    {
        this.nota2=nota2;
    }
    
        public double getNota3()
    {
        return nota3;
    }
    
    public void setNota3(double nota3)
    {
        this.nota3=nota3;
    }

}
