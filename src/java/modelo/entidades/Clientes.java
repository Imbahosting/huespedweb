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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByClientes", query = "SELECT c FROM Clientes c WHERE c.clientes = :clientes")
    , @NamedQuery(name = "Clientes.findByCedularuc", query = "SELECT c FROM Clientes c WHERE c.cedularuc = :cedularuc")
    , @NamedQuery(name = "Clientes.findByNombreCliente", query = "SELECT c FROM Clientes c WHERE c.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "Clientes.findByApellido", query = "SELECT c FROM Clientes c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Clientes.findByCiudad", query = "SELECT c FROM Clientes c WHERE c.ciudad = :ciudad")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clientes_")
    private Integer clientes;
    @Column(name = "cedularuc")
    private String cedularuc;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(mappedBy = "clientes")
    private List<Alquiler> alquilerList;

    public Clientes() {
    }

    public Clientes(Integer clientes) {
        this.clientes = clientes;
    }

    public Integer getClientes() {
        return clientes;
    }

    public void setClientes(Integer clientes) {
        this.clientes = clientes;
    }

    public String getCedularuc() {
        return cedularuc;
    }

    public void setCedularuc(String cedularuc) {
        this.cedularuc = cedularuc;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @XmlTransient
    public List<Alquiler> getAlquilerList() {
        return alquilerList;
    }

    public void setAlquilerList(List<Alquiler> alquilerList) {
        this.alquilerList = alquilerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientes != null ? clientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.clientes == null && other.clientes != null) || (this.clientes != null && !this.clientes.equals(other.clientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.entidades.Clientes[ clientes=" + clientes + " ]";
    }
    
}
