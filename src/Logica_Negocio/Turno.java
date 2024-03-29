/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.TurnoJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoseM
 */
@Entity
@Table(name = "TURNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")
    , @NamedQuery(name = "Turno.findByIdTurno", query = "SELECT t FROM Turno t WHERE t.idTurno = :idTurno")
    , @NamedQuery(name = "Turno.findByTurno", query = "SELECT t FROM Turno t WHERE t.turno = :turno")})
public class Turno implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTurno")
    private List<Matricula> matriculaList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_id_turno", sequenceName = "seq_id_turno", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="seq_id_turno")
    @Column(name = "ID_TURNO")
    private BigDecimal idTurno;
    @Basic(optional = false)
    @Column(name = "TURNO")
    private String turno;

    public Turno() {
    }

    public Turno(BigDecimal idTurno) {
        this.idTurno = idTurno;
    }

    public Turno(BigDecimal idTurno, String turno) {
        this.idTurno = idTurno;
        this.turno = turno;
    }

    public BigDecimal getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(BigDecimal idTurno) {
        this.idTurno = idTurno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurno != null ? idTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.idTurno == null && other.idTurno != null) || (this.idTurno != null && !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
       // return "Logica_Negocio.Turno[ idTurno=" + idTurno + " ]";
       return getTurno();
    }
    
        public void ComboTurno(JComboBox<Turno> cbTurno)
    {
        try {
            TurnoJpaController CTurno = new TurnoJpaController();
            List<Turno> ListTurno = CTurno.findTurnoEntities();
            for (int i = 0; i < ListTurno.size(); i++) {             
                cbTurno.addItem(
                   new Turno(
                           ListTurno.get(i).getIdTurno(),
                           ListTurno.get(i).getTurno()
                   )           
                ); 
            }
        } catch (Exception e) {
        }
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }
    
}
