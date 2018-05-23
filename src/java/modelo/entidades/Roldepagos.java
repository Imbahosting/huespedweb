/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paulina
 */
@Entity
@Table(name = "roldepagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roldepagos.findAll", query = "SELECT r FROM Roldepagos r")
    , @NamedQuery(name = "Roldepagos.findByIdroldepagos", query = "SELECT r FROM Roldepagos r WHERE r.idroldepagos = :idroldepagos")
    , @NamedQuery(name = "Roldepagos.findByIdUser", query = "SELECT r FROM Roldepagos r WHERE r.idUser = :idUser")
    , @NamedQuery(name = "Roldepagos.findByAnticipo", query = "SELECT r FROM Roldepagos r WHERE r.anticipo = :anticipo")
    , @NamedQuery(name = "Roldepagos.findByFechaanticipo", query = "SELECT r FROM Roldepagos r WHERE r.fechaanticipo = :fechaanticipo")
    , @NamedQuery(name = "Roldepagos.findByFechapago", query = "SELECT r FROM Roldepagos r WHERE r.fechapago = :fechapago")
    , @NamedQuery(name = "Roldepagos.findByTotalpago", query = "SELECT r FROM Roldepagos r WHERE r.totalpago = :totalpago")})
public class Roldepagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idroldepagos")
    private Integer idroldepagos;
    @Column(name = "id_user")
    private Integer idUser;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "anticipo")
    private BigDecimal anticipo;
    @Column(name = "fechaanticipo")
    @Temporal(TemporalType.DATE)
    private Date fechaanticipo;
    @Column(name = "fechapago")
    private BigDecimal fechapago;
    @Column(name = "totalpago")
    private BigDecimal totalpago;
    @JoinColumn(name = "idrol", referencedColumnName = "idrol")
    @ManyToOne
    private Roles idrol;

    public Roldepagos() {
    }

    public Roldepagos(Integer idroldepagos) {
        this.idroldepagos = idroldepagos;
    }

    public Integer getIdroldepagos() {
        return idroldepagos;
    }

    public void setIdroldepagos(Integer idroldepagos) {
        this.idroldepagos = idroldepagos;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(BigDecimal anticipo) {
        this.anticipo = anticipo;
    }

    public Date getFechaanticipo() {
        return fechaanticipo;
    }

    public void setFechaanticipo(Date fechaanticipo) {
        this.fechaanticipo = fechaanticipo;
    }

    public BigDecimal getFechapago() {
        return fechapago;
    }

    public void setFechapago(BigDecimal fechapago) {
        this.fechapago = fechapago;
    }

    public BigDecimal getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(BigDecimal totalpago) {
        this.totalpago = totalpago;
    }

    public Roles getIdrol() {
        return idrol;
    }

    public void setIdrol(Roles idrol) {
        this.idrol = idrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idroldepagos != null ? idroldepagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roldepagos)) {
            return false;
        }
        Roldepagos other = (Roldepagos) object;
        if ((this.idroldepagos == null && other.idroldepagos != null) || (this.idroldepagos != null && !this.idroldepagos.equals(other.idroldepagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Roldepagos[ idroldepagos=" + idroldepagos + " ]";
    }
    
}
