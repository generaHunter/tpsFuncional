/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.AlumnoProfesorJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoseM
 */
@Entity
@Table(name = "ALUMNO_PROFESOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlumnoProfesor.findAll", query = "SELECT a FROM AlumnoProfesor a")
    , @NamedQuery(name = "AlumnoProfesor.findByIdAlumnoProfesor", query = "SELECT a FROM AlumnoProfesor a WHERE a.idAlumnoProfesor = :idAlumnoProfesor")})
public class AlumnoProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ALUMNO_PROFESOR")
    private BigDecimal idAlumnoProfesor;
    @JoinColumn(name = "ID_MATUSU", referencedColumnName = "ID_MATUSU")
    @ManyToOne(optional = false)
    private MateriaUsuario idMatusu;
    @JoinColumn(name = "ID_MATRICULA", referencedColumnName = "ID_MATRICULA")
    @ManyToOne(optional = false)
    private Matricula idMatricula;

    public AlumnoProfesor() {
    }

    public AlumnoProfesor(BigDecimal idAlumnoProfesor,MateriaUsuario idMatusu,Matricula idMatricula) {
        this.idAlumnoProfesor = idAlumnoProfesor;
        this.idMatusu=idMatusu;
        this.idMatricula=idMatricula;
    }

    public BigDecimal getIdAlumnoProfesor() {
        return idAlumnoProfesor;
    }

    public void setIdAlumnoProfesor(BigDecimal idAlumnoProfesor) {
        this.idAlumnoProfesor = idAlumnoProfesor;
    }

    public MateriaUsuario getIdMatusu() {
        return idMatusu;
    }

    public void setIdMatusu(MateriaUsuario idMatusu) {
        this.idMatusu = idMatusu;
    }

    public Matricula getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Matricula idMatricula) {
        this.idMatricula = idMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlumnoProfesor != null ? idAlumnoProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoProfesor)) {
            return false;
        }
        AlumnoProfesor other = (AlumnoProfesor) object;
        if ((this.idAlumnoProfesor == null && other.idAlumnoProfesor != null) || (this.idAlumnoProfesor != null && !this.idAlumnoProfesor.equals(other.idAlumnoProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idMatricula.getIdAlumno().getNombre()+" "+idMatricula.getIdAlumno().getApellido();
    }
    
    public void ComboAlumnoProfesor(JComboBox<AlumnoProfesor> cbAlumPro,BigDecimal idMatusu,BigDecimal idgrado)
    {
        try {
            cbAlumPro.removeAllItems();
            AlumnoProfesorJpaController CMatUsu= new AlumnoProfesorJpaController();
            List<AlumnoProfesor> ListcbAlumPro = CMatUsu.findAlumnoProfesorEntities();
            for (int i = 0; i < ListcbAlumPro.size(); i++) {
              
                if(ListcbAlumPro.get(i).getIdMatusu().getIdMatusu().equals(idMatusu) && ListcbAlumPro.get(i).getIdMatricula().getIdGrado().getIdGrado().equals(idgrado) )
                { 
                    cbAlumPro.addItem(
                            new AlumnoProfesor(
                            ListcbAlumPro.get(i).getIdAlumnoProfesor(),
                            ListcbAlumPro.get(i).getIdMatusu(),
                            ListcbAlumPro.get(i).getIdMatricula()
                            )     
                    );                 
                }

            }
        } catch (Exception e) {
        }
        
    }
    
}
