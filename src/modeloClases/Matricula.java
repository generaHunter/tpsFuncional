/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloClases;

/**
 *
 * @author ronal
 */
public class Matricula {
    
    private String gradoName;
    private String turnoName;
    private int IDAlumno;
    
    
    public Matricula(){}

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
