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
@Table(name = "datosempresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datosempresa.findAll", query = "SELECT d FROM Datosempresa d")
    , @NamedQuery(name = "Datosempresa.findByIdempresa", query = "SELECT d FROM Datosempresa d WHERE d.idempresa = :idempresa")
    , @NamedQuery(name = "Datosempresa.findByNombreempresa", query = "SELECT d FROM Datosempresa d WHERE d.nombreempresa = :nombreempresa")
    , @NamedQuery(name = "Datosempresa.findByActividad", query = "SELECT d FROM Datosempresa d WHERE d.actividad = :actividad")
    , @NamedQuery(name = "Datosempresa.findByRuc", query = "SELECT d FROM Datosempresa d WHERE d.ruc = :ruc")
    , @NamedQuery(name = "Datosempresa.findByAutSri", query = "SELECT d FROM Datosempresa d WHERE d.autSri = :autSri")})
public class Datosempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresa")
    private Integer idempresa;
    @Column(name = "nombreempresa")
    private String nombreempresa;
    @Column(name = "actividad")
    private String actividad;
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "aut_sri")
    private String autSri;
    @OneToMany(mappedBy = "idempresa")
    private List<Sucursal> sucursalList;

    public Datosempresa() {
    }

    public Datosempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAutSri() {
        return autSri;
    }

    public void setAutSri(String autSri) {
        this.autSri = autSri;
    }

    @XmlTransient
    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempresa != null ? idempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datosempresa)) {
            return false;
        }
        Datosempresa other = (Datosempresa) object;
        if ((this.idempresa == null && other.idempresa != null) || (this.idempresa != null && !this.idempresa.equals(other.idempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Datosempresa[ idempresa=" + idempresa + " ]";
    }
    
}
