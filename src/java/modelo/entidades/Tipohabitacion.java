/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tipohabitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipohabitacion.findAll", query = "SELECT t FROM Tipohabitacion t")
    , @NamedQuery(name = "Tipohabitacion.findByIdtipo", query = "SELECT t FROM Tipohabitacion t WHERE t.idtipo = :idtipo")
    , @NamedQuery(name = "Tipohabitacion.findByNombre", query = "SELECT t FROM Tipohabitacion t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tipohabitacion.findByDescripcion", query = "SELECT t FROM Tipohabitacion t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tipohabitacion.findByPrecio", query = "SELECT t FROM Tipohabitacion t WHERE t.precio = :precio")
    , @NamedQuery(name = "Tipohabitacion.findByPreciofraccion", query = "SELECT t FROM Tipohabitacion t WHERE t.preciofraccion = :preciofraccion")})
public class Tipohabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo")
    private Integer idtipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "preciofraccion")
    private BigDecimal preciofraccion;
    @OneToMany(mappedBy = "idtipo")
    private List<Habitacion> habitacionList;

    public Tipohabitacion() {
    }

    public Tipohabitacion(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPreciofraccion() {
        return preciofraccion;
    }

    public void setPreciofraccion(BigDecimal preciofraccion) {
        this.preciofraccion = preciofraccion;
    }

    @XmlTransient
    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public void setHabitacionList(List<Habitacion> habitacionList) {
        this.habitacionList = habitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipo != null ? idtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipohabitacion)) {
            return false;
        }
        Tipohabitacion other = (Tipohabitacion) object;
        if ((this.idtipo == null && other.idtipo != null) || (this.idtipo != null && !this.idtipo.equals(other.idtipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Tipohabitacion[ idtipo=" + idtipo + " ]";
    }
    
}
