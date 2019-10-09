/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.PeriodoJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoseM
 */
@Entity
@Table(name = "PERIODO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p")
    , @NamedQuery(name = "Periodo.findByIdPeriodo", query = "SELECT p FROM Periodo p WHERE p.idPeriodo = :idPeriodo")
    , @NamedQuery(name = "Periodo.findByPeriodo", query = "SELECT p FROM Periodo p WHERE p.periodo = :periodo")})
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PERIODO")
    private BigDecimal idPeriodo;
    @Basic(optional = false)
    @Column(name = "PERIODO")
    private String periodo;

    public Periodo() {
    }

    public Periodo(BigDecimal idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Periodo(BigDecimal idPeriodo, String periodo) {
        this.idPeriodo = idPeriodo;
        this.periodo = periodo;
    }

    public BigDecimal getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(BigDecimal idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return periodo;
    }
    
    public void ComboPeriodo(JComboBox<Periodo> cbPeriodo)
    {
        try {
            PeriodoJpaController CPeriodo= new PeriodoJpaController();
            List<Periodo> ListPeriodo = CPeriodo.findPeriodoEntities();
            for (int i = 0; i < ListPeriodo.size(); i++) {             
                cbPeriodo.addItem(
                   new Periodo(
                           ListPeriodo.get(i).getIdPeriodo(),
                           ListPeriodo.get(i).getPeriodo()
                   )           
                ); 
            }
        } catch (Exception e) {
        }
    }
}
