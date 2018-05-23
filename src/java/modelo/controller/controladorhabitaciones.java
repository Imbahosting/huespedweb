/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import modelo.entidades.Habitacion;
import modelo.entidades.Tipohabitacion;
import modelo.manager.Managerapp;
import modelo.util.JSFUtil;

/**
 *
 * @author Paulina
 */
@ManagedBean
@SessionScoped
public class controladorhabitaciones {
    private Managerapp managerapp;
     private List<Habitacion> listahabitaciones;
     private Integer numero;
     private Integer idtipo;
     private String ubicacion;
     private String estado;
     private Habitacion habita;
    private boolean estadobtn=false;
    private boolean  estadoedit=true;
     
     private List<SelectItem> selecttipo;
     private List<Tipohabitacion> tipos;
  
    
    public controladorhabitaciones() {
    
    managerapp=new Managerapp();
    
    }

    public List<Tipohabitacion> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipohabitacion> tipos) {
        this.tipos = tipos;
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
    
    

    public List<Habitacion> getListahabitaciones() {
        listahabitaciones=managerapp.listahabitaciones();
        return listahabitaciones;
    }


    public void setListahabitaciones(List<Habitacion> listahabitaciones) {
        this.listahabitaciones = listahabitaciones;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Habitacion getHabita() {
        return habita;
    }

    public void setHabita(Habitacion habita) {
        this.habita = habita;
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



    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }
    
    //PASO 2 COMBO TIPO
     
     public List<SelectItem> getSelecttipo() {
        
         selecttipo=new ArrayList<SelectItem>();
         selecttipo.clear();
         tipos= managerapp.listatipos();
         for (Tipohabitacion r: tipos)
         {
           SelectItem item=new SelectItem(r.getIdtipo(),r.getNombre());
      selecttipo.add(item);
         }
         return selecttipo;
    
    }
     
      public void crearhabitacion()
 {
         try {
       
             managerapp.crearhabita(idtipo, numero, ubicacion, "success");
           JSFUtil.crearMensajeINFO("Tipo de habitación creado");
           limpiarcamposhabitacion();
         } catch (Exception ex) {
             JSFUtil.crearMensajeERROR(""+ex);
             Logger.getLogger(controladortipo.class.getName()).log(Level.SEVERE, null, ex);
         }
 }
    
    public void limpiarcamposhabitacion()
{
    numero=null;
    idtipo=null;
    ubicacion="";
    estadobtn=false;
    estadoedit=true;
}
    
         public void cargarhabitacion(Habitacion h)
     {
         habita=h;
         estadobtn=true;
         estadoedit=false;
         numero=h.getNumero();
         idtipo=h.getIdtipo().getIdtipo();
         ubicacion=h.getUbicacion();
         estado=h.getEstado();     
     }
         
       public void editarhabitacion()
   {
   
          try {
              managerapp.editarhabita(habita.getIdhabitacion(), idtipo, numero, ubicacion, estado);
              JSFUtil.crearMensajeWARN("Se editó la habitación");
          } catch (Exception ex) {
              JSFUtil.crearMensajeERROR("error de ser+ex"+ex);
              Logger.getLogger(controladorhabitaciones.class.getName()).log(Level.SEVERE, null, ex);
          }
       
   }
    
}
