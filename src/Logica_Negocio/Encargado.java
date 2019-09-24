/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocio;

import Datos.EncargadoJpaController;
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
@Table(name = "ENCARGADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargado.findAll", query = "SELECT e FROM Encargado e")
    , @NamedQuery(name = "Encargado.findByIdEncargado", query = "SELECT e FROM Encargado e WHERE e.idEncargado = :idEncargado")
    , @NamedQuery(name = "Encargado.findByNombre", query = "SELECT e FROM Encargado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Encargado.findByApellido", query = "SELECT e FROM Encargado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Encargado.findByDui", query = "SELECT e FROM Encargado e WHERE e.dui = :dui")
    , @NamedQuery(name = "Encargado.findByDireccion", query = "SELECT e FROM Encargado e WHERE e.direccion = :direccion")})
public class Encargado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_id_encargado", sequenceName = "seq_id_encargado", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="seq_id_encargado")
    @Column(name = "ID_ENCARGADO")
    private BigDecimal idEncargado;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "DUI")
    private String dui;
    @Column(name = "DIRECCION")
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncargado")
    private List<Alumno> alumnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncargado")
    private List<TelefonoEncargado> telefonoEncargadoList;

    public Encargado() {
    }

    public Encargado(BigDecimal idEncargado) {
        this.idEncargado = idEncargado;
    }

    public Encargado(BigDecimal idEncargado, String nombre, String apellido, String dui) {
        this.idEncargado = idEncargado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
    }

    public BigDecimal getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(BigDecimal idEncargado) {
        this.idEncargado = idEncargado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @XmlTransient
    public List<TelefonoEncargado> getTelefonoEncargadoList() {
        return telefonoEncargadoList;
    }

    public void setTelefonoEncargadoList(List<TelefonoEncargado> telefonoEncargadoList) {
        this.telefonoEncargadoList = telefonoEncargadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncargado != null ? idEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargado)) {
            return false;
        }
        Encargado other = (Encargado) object;
        if ((this.idEncargado == null && other.idEncargado != null) || (this.idEncargado != null && !this.idEncargado.equals(other.idEncargado))) {
            return false;
        }
        return true;
    }
    
    public void ComboEncargado(JComboBox<Encargado> cbEncargado)
    {
        try {
            EncargadoJpaController CEncargado= new EncargadoJpaController();
            List<Encargado> ListEncargado = CEncargado.findEncargadoEntities();
            for (int i = 0; i < ListEncargado.size(); i++) {
                cbEncargado.addItem(
                        new Encargado(
                        ListEncargado.get(i).getIdEncargado(),
                        ListEncargado.get(i).getNombre(),
                        ListEncargado.get(i).getApellido(),
                        ListEncargado.get(i).getDui()
                        )           
                );
            }
        } catch (Exception e) {
        }
        
    }

    @Override
    public String toString() {
       return nombre +" "+apellido;
    }
    
}
