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
@Table(name = "alquiler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a")
    , @NamedQuery(name = "Alquiler.findByIdalquiler", query = "SELECT a FROM Alquiler a WHERE a.idalquiler = :idalquiler")
    , @NamedQuery(name = "Alquiler.findByFechaentrada", query = "SELECT a FROM Alquiler a WHERE a.fechaentrada = :fechaentrada")
    , @NamedQuery(name = "Alquiler.findByFechasalida", query = "SELECT a FROM Alquiler a WHERE a.fechasalida = :fechasalida")
    , @NamedQuery(name = "Alquiler.findByHoraentrada", query = "SELECT a FROM Alquiler a WHERE a.horaentrada = :horaentrada")
    , @NamedQuery(name = "Alquiler.findByHorasalida", query = "SELECT a FROM Alquiler a WHERE a.horasalida = :horasalida")
    , @NamedQuery(name = "Alquiler.findByTotaldedias", query = "SELECT a FROM Alquiler a WHERE a.totaldedias = :totaldedias")
    , @NamedQuery(name = "Alquiler.findByTotaldehoras", query = "SELECT a FROM Alquiler a WHERE a.totaldehoras = :totaldehoras")
    , @NamedQuery(name = "Alquiler.findByPreciototal", query = "SELECT a FROM Alquiler a WHERE a.preciototal = :preciototal")
    , @NamedQuery(name = "Alquiler.findByHoralimpieza", query = "SELECT a FROM Alquiler a WHERE a.horalimpieza = :horalimpieza")})
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalquiler")
    private Integer idalquiler;
    @Column(name = "fechaentrada")
    @Temporal(TemporalType.DATE)
    private Date fechaentrada;
    @Column(name = "fechasalida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @Column(name = "horaentrada")
    @Temporal(TemporalType.TIME)
    private Date horaentrada;
    @Column(name = "horasalida")
    @Temporal(TemporalType.TIME)
    private Date horasalida;
    @Column(name = "totaldedias")
    private Integer totaldedias;
    @Column(name = "totaldehoras")
    private Integer totaldehoras;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciototal")
    private BigDecimal preciototal;
    @Column(name = "horalimpieza")
    @Temporal(TemporalType.TIME)
    private Date horalimpieza;
    @JoinColumn(name = "clientes_", referencedColumnName = "clientes_")
    @ManyToOne
    private Clientes clientes;
    @JoinColumn(name = "idhabitacion", referencedColumnName = "idhabitacion")
    @ManyToOne
    private Habitacion idhabitacion;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private Usuario idUser;

    public Alquiler() {
    }

    public Alquiler(Integer idalquiler) {
        this.idalquiler = idalquiler;
    }

    public Integer getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(Integer idalquiler) {
        this.idalquiler = idalquiler;
    }

    public Date getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Date getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(Date horaentrada) {
        this.horaentrada = horaentrada;
    }

    public Date getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(Date horasalida) {
        this.horasalida = horasalida;
    }

    public Integer getTotaldedias() {
        return totaldedias;
    }

    public void setTotaldedias(Integer totaldedias) {
        this.totaldedias = totaldedias;
    }

    public Integer getTotaldehoras() {
        return totaldehoras;
    }

    public void setTotaldehoras(Integer totaldehoras) {
        this.totaldehoras = totaldehoras;
    }

    public BigDecimal getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(BigDecimal preciototal) {
        this.preciototal = preciototal;
    }

    public Date getHoralimpieza() {
        return horalimpieza;
    }

    public void setHoralimpieza(Date horalimpieza) {
        this.horalimpieza = horalimpieza;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Habitacion getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(Habitacion idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalquiler != null ? idalquiler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.idalquiler == null && other.idalquiler != null) || (this.idalquiler != null && !this.idalquiler.equals(other.idalquiler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Alquiler[ idalquiler=" + idalquiler + " ]";
    }
    
}
