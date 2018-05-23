/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Paulina
 */
@Entity
@Table(name = "sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s")
    , @NamedQuery(name = "Sucursal.findByIdsucursal", query = "SELECT s FROM Sucursal s WHERE s.idsucursal = :idsucursal")
    , @NamedQuery(name = "Sucursal.findByCiudad", query = "SELECT s FROM Sucursal s WHERE s.ciudad = :ciudad")
    , @NamedQuery(name = "Sucursal.findByNombrecomercial", query = "SELECT s FROM Sucursal s WHERE s.nombrecomercial = :nombrecomercial")
    , @NamedQuery(name = "Sucursal.findByDireccion", query = "SELECT s FROM Sucursal s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Sucursal.findByTelefono", query = "SELECT s FROM Sucursal s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Sucursal.findByEmail", query = "SELECT s FROM Sucursal s WHERE s.email = :email")
    , @NamedQuery(name = "Sucursal.findByEstado", query = "SELECT s FROM Sucursal s WHERE s.estado = :estado")
    , @NamedQuery(name = "Sucursal.findByNumerohabita", query = "SELECT s FROM Sucursal s WHERE s.numerohabita = :numerohabita")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsucursal")
    private Integer idsucursal;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "nombrecomercial")
    private String nombrecomercial;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "numerohabita")
    private Integer numerohabita;
    @JoinColumn(name = "idempresa", referencedColumnName = "idempresa")
    @ManyToOne
    private Datosempresa idempresa;
    @OneToMany(mappedBy = "idsucursal")
    private List<Usuario> usuarioList;

    public Sucursal() {
    }

    public Sucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getNumerohabita() {
        return numerohabita;
    }

    public void setNumerohabita(Integer numerohabita) {
        this.numerohabita = numerohabita;
    }

    public Datosempresa getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Datosempresa idempresa) {
        this.idempresa = idempresa;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsucursal != null ? idsucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idsucursal == null && other.idsucursal != null) || (this.idsucursal != null && !this.idsucursal.equals(other.idsucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Sucursal[ idsucursal=" + idsucursal + " ]";
    }
    
}
