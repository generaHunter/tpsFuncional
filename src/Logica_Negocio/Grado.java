/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.GradoJpaController;
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
@Table(name = "GRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g")
    , @NamedQuery(name = "Grado.findByIdGrado", query = "SELECT g FROM Grado g WHERE g.idGrado = :idGrado")
    , @NamedQuery(name = "Grado.findByGrado", query = "SELECT g FROM Grado g WHERE g.grado = :grado")})
public class Grado implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrado")
    private List<UsuarioGrado> usuarioGradoList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_id_grado", sequenceName = "seq_id_grado", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="seq_id_grado")
    @Column(name = "ID_GRADO")
    private BigDecimal idGrado;
    @Basic(optional = false)
    @Column(name = "GRADO")
    private String grado;

    public Grado() {
    }

    public Grado(BigDecimal idGrado) {
        this.idGrado = idGrado;
    }

    public Grado(BigDecimal idGrado, String grado) {
        this.idGrado = idGrado;
        this.grado = grado;
    }

    public BigDecimal getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(BigDecimal idGrado) {
        this.idGrado = idGrado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrado != null ? idGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.idGrado == null && other.idGrado != null) || (this.idGrado != null && !this.idGrado.equals(other.idGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return grado.toString();
    }

    @XmlTransient
    public List<UsuarioGrado> getUsuarioGradoList() {
        return usuarioGradoList;
    }

    public void setUsuarioGradoList(List<UsuarioGrado> usuarioGradoList) {
        this.usuarioGradoList = usuarioGradoList;
    }
    
    public void ComboGrado(JComboBox<Grado> cbGrado)
    {
        try {
            GradoJpaController CGrado= new GradoJpaController();
            List<Grado> ListGrado = CGrado.findGradoEntities();
            for (int i = 0; i < ListGrado.size(); i++) {             
                cbGrado.addItem(
                   new Grado(
                           ListGrado.get(i).getIdGrado(),
                           ListGrado.get(i).getGrado()
                   )           
                ); 
            }
        } catch (Exception e) {
        }
    }
    
    
}
