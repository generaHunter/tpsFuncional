/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.UsuarioGradoJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
    @SequenceGenerator(name = "seq_id_usuario_grado", sequenceName = "seq_id_usuario_grado", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="seq_id_usuario_grado")
    @Column(name = "ID_USUARIO_GRADO")
    private BigDecimal idUsuarioGrado;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false)
    private Grado idGrado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public UsuarioGrado() {
    }

    public UsuarioGrado(BigDecimal idUsuarioGrado) {
        this.idUsuarioGrado = idUsuarioGrado;
    }
        public UsuarioGrado(BigDecimal idUsuarioGrado,Grado idGrado) {
        this.idUsuarioGrado = idUsuarioGrado;
         this.idGrado = idGrado;
    }

    public BigDecimal getIdUsuarioGrado() {
        return idUsuarioGrado;
    }

    public void setIdUsuarioGrado(BigDecimal idUsuarioGrado) {
        this.idUsuarioGrado = idUsuarioGrado;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        return idGrado.getGrado();
    }
    
        public void ComboAlumnoProfesor(JComboBox<UsuarioGrado> cbGradoUsu,BigDecimal idProfesor)
    {
        try {
             
            cbGradoUsu.removeAllItems();
            UsuarioGradoJpaController CMatUsu= new UsuarioGradoJpaController();
            List<UsuarioGrado> ListcbUsuGrado = CMatUsu.findUsuarioGradoEntities();
            for (int i = 0; i < ListcbUsuGrado.size(); i++) {
                if(ListcbUsuGrado.get(i).getIdUsuario().getIdUsuario().equals(idProfesor) )
                {
                    cbGradoUsu.addItem(
                            new UsuarioGrado(
                            ListcbUsuGrado.get(i).getIdUsuarioGrado(),
                            ListcbUsuGrado.get(i).getIdGrado()
                            )     
                    );                 
                }
            }
        } catch (Exception e) {
        }
        
    }
    
}
