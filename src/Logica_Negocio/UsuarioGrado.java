/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoseM
 */
@Entity
@Table(name = "USUARIO_GRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioGrado.findAll", query = "SELECT u FROM UsuarioGrado u")
    , @NamedQuery(name = "UsuarioGrado.findByIdUsuarioGrado", query = "SELECT u FROM UsuarioGrado u WHERE u.idUsuarioGrado = :idUsuarioGrado")})
public class UsuarioGrado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_GRADO")
    private BigDecimal idUsuarioGrado;

    public UsuarioGrado() {
    }

    public UsuarioGrado(BigDecimal idUsuarioGrado) {
        this.idUsuarioGrado = idUsuarioGrado;
    }

    public BigDecimal getIdUsuarioGrado() {
        return idUsuarioGrado;
    }

    public void setIdUsuarioGrado(BigDecimal idUsuarioGrado) {
        this.idUsuarioGrado = idUsuarioGrado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioGrado != null ? idUsuarioGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioGrado)) {
            return false;
        }
        UsuarioGrado other = (UsuarioGrado) object;
        if ((this.idUsuarioGrado == null && other.idUsuarioGrado != null) || (this.idUsuarioGrado != null && !this.idUsuarioGrado.equals(other.idUsuarioGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Logica_Negocio.UsuarioGrado[ idUsuarioGrado=" + idUsuarioGrado + " ]";
    }
    
}
