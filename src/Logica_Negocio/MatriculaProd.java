/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

/**
 *
 * @author ronal
 */
public class MatriculaProd {
    
    private String gradoName;
    private String turnoName;
    private int IDAlumno;
    private int IdMatricula;
    private int opcion;

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public int getIdMatricula() {
        return IdMatricula;
    }

    public void setIdMatricula(int IdMatricula) {
        this.IdMatricula = IdMatricula;
    }
    
    
    
    public MatriculaProd(){}

    public String getGradoName() {
        return gradoName;
    }

    public void setGradoName(String gradoName) {
        this.gradoName = gradoName;
    }

    public String getTurnoName() {
        return turnoName;
    }

    public void setTurnoName(String turnoName) {
        this.turnoName = turnoName;
    }

    public int getIDAlumno() {
        return IDAlumno;
    }

    public void setIDAlumno(int IDAlumno) {
        this.IDAlumno = IDAlumno;
    }       
    
}
