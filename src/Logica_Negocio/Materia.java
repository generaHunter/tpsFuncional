/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.MateriaJpaController;
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
@Table(name = "MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria")
    , @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia")})
public class Materia implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private List<MateriaUsuario> materiaUsuarioList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_id_materia", sequenceName = "seq_id_materia", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="seq_id_materia")
    @Column(name = "ID_MATERIA")
    private BigDecimal idMateria;
    @Column(name = "MATERIA")
    private String materia;

    public Materia() {
    }

    public Materia(BigDecimal idMateria,String materia) {
        this.idMateria = idMateria;
        this.materia=materia;
    }

    public BigDecimal getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(BigDecimal idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  materia;
    }
    
    public void ComboMateria(JComboBox<Materia> cbMateria)
    {
        try {
            MateriaJpaController CMateria= new MateriaJpaController();
            List<Materia> ListMateria = CMateria.findMateriaEntities();
            for (int i = 0; i < ListMateria.size(); i++) {             
                cbMateria.addItem(
                   new Materia(
                           ListMateria.get(i).getIdMateria(),
                           ListMateria.get(i).getMateria()
                   )           
                ); 
            }
        } catch (Exception e) {
        }
    }

    @XmlTransient
    public List<MateriaUsuario> getMateriaUsuarioList() {
        return materiaUsuarioList;
    }

    public void setMateriaUsuarioList(List<MateriaUsuario> materiaUsuarioList) {
        this.materiaUsuarioList = materiaUsuarioList;
    }
    
}
