/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.manager.Managerapp;
import modelo.util.JSFUtil;

@ManagedBean
@SessionScoped
public class controladorusuarios {

    private Managerapp managerapp;    
    private String nombreusuario;
    private String usuario;
    private String contrasenia;

    private int iduser;
    private String nombrerol;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
  

    private boolean estado;
    

    private int idsucursal;
    private String nombresucursal;


    public controladorusuarios() {

        managerapp = new Managerapp();
    }

public String login() {

        try {
            if (managerapp.login(getUsuario(), getContrasenia()) == true) {
                nombrerol = managerapp.nombrerolmanager;
                FacesContext context = FacesContext.getCurrentInstance();
                //JSFUtil.crearMensajeINFO( "Bienvenido :"+nombrerol);
                setUsuario("");
                setContrasenia("");

                if (nombrerol.equals("administrador")) {
                    iduser = managerapp.idusermanager;
                    idsucursal=managerapp.idsucursal;
                    nombresucursal=managerapp.nombresucursal;

                    setUsuario("");
                    setContrasenia("");

                 context.getExternalContext().getSessionMap().put("user", iduser);
			 context.getExternalContext().redirect("recepcionadmin.xhtml");

                }
                if (nombrerol.equals("recepcion")) {
                    setUsuario("");
                    setContrasenia("");
                    iduser = managerapp.idusermanager;

                    context.getExternalContext().getSessionMap().put("user", iduser);
			 context.getExternalContext().redirect("recepcion.xhtml");
                }

            } else {

                setUsuario("");
                setContrasenia("");
                JSFUtil.crearMensajeWARN("Intente una Vez más");
                return "";
            }

        } catch (Exception e) {

            JSFUtil.crearMensajeWARN("Error de servidor");
            setContrasenia("");
            return "";
        }

        return "index";
    }
    


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

 
    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getNombresucursal() {
        return nombresucursal;
    }

    public void setNombresucursal(String nombresucursal) {
        this.nombresucursal = nombresucursal;
    }

    public String getNombreusuario() {
        return managerapp.nombresuser;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario= nombreusuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
}
