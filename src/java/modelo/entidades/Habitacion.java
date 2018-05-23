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
@Table(name = "habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h")
    , @NamedQuery(name = "Habitacion.findByIdhabitacion", query = "SELECT h FROM Habitacion h WHERE h.idhabitacion = :idhabitacion")
    , @NamedQuery(name = "Habitacion.findByNumero", query = "SELECT h FROM Habitacion h WHERE h.numero = :numero")
    , @NamedQuery(name = "Habitacion.findByUbicacion", query = "SELECT h FROM Habitacion h WHERE h.ubicacion = :ubicacion")
    , @NamedQuery(name = "Habitacion.findByEstado", query = "SELECT h FROM Habitacion h WHERE h.estado = :estado")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhabitacion")
    private Integer idhabitacion;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idhabitacion")
    private List<Alquiler> alquilerList;
    @JoinColumn(name = "idtipo", referencedColumnName = "idtipo")
    @ManyToOne
    private Tipohabitacion idtipo;

    public Habitacion() {
    }

    public Habitacion(Integer idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public Integer getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(Integer idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Alquiler> getAlquilerList() {
        return alquilerList;
    }

    public void setAlquilerList(List<Alquiler> alquilerList) {
        this.alquilerList = alquilerList;
    }

    public Tipohabitacion getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipohabitacion idtipo) {
        this.idtipo = idtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhabitacion != null ? idhabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.idhabitacion == null && other.idhabitacion != null) || (this.idhabitacion != null && !this.idhabitacion.equals(other.idhabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Habitacion[ idhabitacion=" + idhabitacion + " ]";
    }
    
}
