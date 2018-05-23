/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.entidades.Tipohabitacion;
import modelo.manager.Managerapp;
import modelo.util.JSFUtil;

/**
 *
 * @author Paulina
 */
@ManagedBean
@SessionScoped
public class controladortipo {

    private Managerapp managerapp;
    private String nombre;
    private String descripcion;
    private double precio;
    private double preciofraccion;
    private List<Tipohabitacion> listatipos;
    private boolean estadobtn=false;
    private boolean  estadoedit=true;
    private Tipohabitacion tipoh;

    public controladortipo() {
        managerapp = new Managerapp();

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPreciofraccion() {
        return preciofraccion;
    }

    public void setPreciofraccion(double preciofraccion) {
        this.preciofraccion = preciofraccion;
    }

    public List<Tipohabitacion> getListatipos() {
        listatipos = managerapp.listatipos();
        return listatipos;
    }

    public void setListatipos(List<Tipohabitacion> listatipos) {
        this.listatipos = listatipos;
    }

    public boolean isEstadobtn() {
        return estadobtn;
    }

    public void setEstadobtn(boolean estadobtn) {
        this.estadobtn = estadobtn;
    }

    public boolean isEstadoedit() {
        return estadoedit;
    }

    public void setEstadoedit(boolean estadoedit) {
        this.estadoedit = estadoedit;
    }

    public Tipohabitacion getTipoh() {
        return tipoh;
    }

    public void setTipoh(Tipohabitacion tipoh) {
        this.tipoh = tipoh;
    }
    
    

     public void creartipohabitacion()
 {
         try {
       
             managerapp.creartipo(nombre, descripcion, precio, preciofraccion);
           JSFUtil.crearMensajeINFO("Tipo de habitacion creado");
           limpiarcampostipo();
         } catch (Exception ex) {
             JSFUtil.crearMensajeERROR(""+ex);
             Logger.getLogger(controladortipo.class.getName()).log(Level.SEVERE, null, ex);
         }
 }

    public void limpiarcampostipo() {
        nombre = "";
        descripcion = "";
        precio = 0;
        preciofraccion = 0;
         estadobtn=false;
    estadoedit=true;
    }
    
    public void cargartipo(Tipohabitacion th)
     {
         tipoh=th;
         estadobtn=true;
         estadoedit=false;
         nombre=th.getNombre();
         descripcion=th.getDescripcion();
         precio=th.getPrecio().doubleValue();
         preciofraccion=th.getPreciofraccion().doubleValue();    
     }
    
           public void editartipohabitacion()
   {
   
          try {
              managerapp.editartipo(tipoh.getIdtipo(), nombre, descripcion, precio, preciofraccion);
              JSFUtil.crearMensajeWARN("Se edito el tipo de habitacion");
          } catch (Exception ex) {
              JSFUtil.crearMensajeERROR("error de ser+ex"+ex);
              Logger.getLogger(controladortipo.class.getName()).log(Level.SEVERE, null, ex);
          }
       
   }
}
