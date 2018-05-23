/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.entidades.Alquiler;
import modelo.entidades.Clientes;
import modelo.entidades.Habitacion;
import modelo.entidades.Usuario;
import modelo.manager.Managerapp;
import modelo.util.JSFUtil;

/**
 *
 * @author Paulina
 */
@ManagedBean
@SessionScoped
public class controladorecepcion {

    @ManagedProperty(value = "#{controladorusuarios}")
    private controladorusuarios usuarios;
    @ManagedProperty(value = "#{controladorhabitaciones}")
    private controladorhabitaciones habitacion;
    @ManagedProperty(value = "#{controladorhuespedes}")
    private controladorhuespedes huesped;
    @ManagedProperty(value = "#{controladorcaja}")
    private controladorcaja alquiler;
    private Managerapp managerapp;
    private Habitacion habit;
    private Clientes client;
    private Integer iduser;
    private Integer idhabitacion;
    private Integer idcliente;
    private Integer numero;
    private Date fechaentrada;
    private Date fechasalida;
    private Date horaentrada;
    private Date horasalida;
    private Integer totaldedias;
    private Integer totaldehoras;
    private Integer numdiashoras;
    private double preciototal;
    private Usuario user;
    private boolean estadoedit = true;
    private boolean activar = false;
    private List<Habitacion> listahabitaciones;
    private List<Alquiler> listaalquiler;
    private String actualizar="refresh";

        public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }
    
    public String getActualizar() {
        return actualizar;
    }

    public void setActualizar(String actualizar) {
        this.actualizar = actualizar;
    }

    
    public boolean isEstadoedit() {
        return estadoedit;
    }

    public void setEstadoedit(boolean estadoedit) {
        this.estadoedit = estadoedit;
    }

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
    }

    public List<Alquiler> getListaalquiler() {
        return listaalquiler;
    }

    public void setListaalquiler(List<Alquiler> listaalquiler) {
        this.listaalquiler = listaalquiler;
    }

    public List<Habitacion> getListahabitaciones() {
        listahabitaciones = managerapp.listahabitaciones();
        return listahabitaciones;
    }

    public void setListahabitaciones(List<Habitacion> listahabitaciones) {
        this.listahabitaciones = listahabitaciones;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public controladorcaja getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(controladorcaja alquiler) {
        this.alquiler = alquiler;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Clientes getClient() {
        return client;
    }

    public void setClient(Clientes client) {
        this.client = client;
    }

    public controladorhabitaciones getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(controladorhabitaciones habitacion) {
        this.habitacion = habitacion;
    }

    public controladorhuespedes getHuesped() {
        return huesped;
    }

    public void setHuesped(controladorhuespedes huesped) {
        this.huesped = huesped;
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

    public controladorusuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(controladorusuarios usuarios) {
        this.usuarios = usuarios;
    }

    public controladorecepcion() {

        managerapp = new Managerapp();

    }

    public Habitacion getHabit() {
        return habit;
    }

    public void setHabit(Habitacion habit) {
        this.habit = habit;
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Date getFechaentrada() {
        fechaentrada = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaentrada);
        calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        fechaentrada = calendar.getTime();
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
        horaentrada = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaentrada);
        calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        horaentrada = calendar.getTime();
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

    public Integer getNumdiashoras() {
        return numdiashoras;
    }

    public void setNumdiashoras(Integer numdiashoras) {
        this.numdiashoras = numdiashoras;
    }



    public void cargarnumerohabitacion(Habitacion h) {
        habit = h;
        numero = h.getNumero();
        huesped.setNombreCliente("");
        huesped.setCedularuc("");
        huesped.setApellido("");
        huesped.setCiudad("");
        huesped.setTelefono("");
        fechasalida = null;
        horasalida = null;
        numdiashoras = null;
        preciototal = 0.0;
        estadoedit = true;
    }

    public void calcularpreciopordia() throws ParseException {
        preciototal = numdiashoras * habit.getIdtipo().getPrecio().doubleValue();

        fechasalida = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechasalida);
        calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        calendar.add(Calendar.DAY_OF_YEAR, numdiashoras);
        fechasalida = calendar.getTime();
        String salida="12:00:00";
        SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
        horasalida = formattime.parse(salida);

    }
    

    public void calcularprecioporhoras() {
        preciototal = habit.getIdtipo().getPreciofraccion().doubleValue();
        horasalida = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horasalida);
        calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        calendar.add(Calendar.HOUR, numdiashoras);
        horasalida = calendar.getTime();
        fechasalida = new Date();
        calendar.setTime(fechasalida);
        fechasalida = calendar.getTime();

    }

    public void alquilarhabitacion() {
        try {
            Clientes c = managerapp.findClienteById(huesped.getCedularuc());
            Habitacion h = managerapp.findHabitacionByNumero(habit.getNumero());
             SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
             Calendar calendar=Calendar.getInstance();
                    calendar.setTime(horasalida);
                    calendar.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
                    calendar.add(Calendar.MINUTE, 30);
                    Date horalimpieza = calendar.getTime();
                    actualizar="refresh";
            
            managerapp.alquilar(usuarios.getIduser(), h.getIdhabitacion(), c.getClientes(), fechaentrada, fechasalida, horaentrada, horasalida,horalimpieza,numdiashoras, numdiashoras, preciototal);
            huesped.setEstadoedit(true);
            JSFUtil.crearMensajeINFO("Habitación alquilada");
            
        } catch (Exception ex) {
            JSFUtil.crearMensajeERROR("" + ex);
            Logger.getLogger(controladortipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean desactivarhabitacion(Integer idhabitacion) throws Exception {
        Habitacion h = managerapp.finbyidhabitacion(idhabitacion);
        if (h.getEstado().equals("danger") || h.getEstado().equals("warning")) {
            activar = true;
        } else {
            activar = false;
        }
        return activar;
    }

    public String bloquearmodal(Integer idhabitacion) throws Exception {
        Habitacion h = managerapp.finbyidhabitacion(idhabitacion);
        String res = "";
        if (h.getEstado().equals("warning")) {
            res = "";
        } else {
            res = "modal";
        }
        return res;
    }

    public String actualizarmodal(Integer idhabitacion) throws Exception {
        Habitacion h = managerapp.finbyidhabitacion(idhabitacion);
        String res = "";
        if (h.getEstado().equals("success")) {
            res = "modalform";
        } else if (h.getEstado().equals("danger")) {
            res = "modalformdes";
        }
        return res;
    }

    public String abrirmodal(Integer idhabitacion) throws Exception {
        
        actualizar="#";
        Habitacion h = managerapp.finbyidhabitacion(idhabitacion);
        String res = "";
        if (h.getEstado().equals("success")) {
            res = "#modalr";
        } else if (h.getEstado().equals("danger")) {
            res = "#modaldes";
        }
        return res;
    }

    public String Descripcionestado(Integer idhabitacion) throws Exception { 
        Habitacion h = managerapp.finbyidhabitacion(idhabitacion);
        String res = "";
        if (h.getEstado().equals("danger")) {
            res = "OCUPADO";
        } else if (h.getEstado().equals("warning")) {
            res = "LIMPIEZA";
        } else if (h.getEstado().equals("success")) {
            res = "DISPONIBLE";
        }
        return res;
    }


public void metodosemaforo() throws Exception {
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        List<Habitacion> listaha=managerapp.listahabitaciones();
      
            for (Alquiler a : listalquiler) {
                       
                for (int i = 0; i < listaha.size(); i++) {
              
            if(a.getIdhabitacion().getIdhabitacion()==listaha.get(i).getIdhabitacion())
            {
                    //Formato de las fechas
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat formattime = new SimpleDateFormat("HH:mm:ss");
                    //Fecha actual
                   Date hoy =new Date();          
                   String fechaac=formatter.format(hoy);
                   
                   //FECHA SALIDA BDD
                   String fechasa=formatter.format(a.getFechasalida());
                   
                   //MANEJO EN HORAS 
                   String horaac=formattime.format(hoy);
                   String horasa=formattime.format(a.getHorasalida());
                   
                   //HORA LIMPIEZA
                   String horalimp=formattime.format(a.getHoralimpieza());
                  
                 
                   
                   //METODO QUE CONTROLA LAS HORAS y METODO QUE CONTROLA LA FECHA
                   
                   
          
                    if(fechaac.compareTo(fechasa)==0)
                    {
                         
                      if (horaac.compareTo(horasa)>=0)
                    {
                       managerapp.editarcoloramarillo(a.getIdhabitacion().getIdhabitacion());

                    } 
                    
                     if(horaac.compareTo(horalimp)>=0)
                      {
                           managerapp.editarcolor(a.getIdhabitacion().getIdhabitacion());

                      }
                   
                    }
                    
           
                    
                    
            } 
            
            
                }
            }

    }



      


    public String nombrehuesped(Integer idhabitacion) {
        String res = "";
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getIdhabitacion();
            if (nuevoid.equals(idhabitacion) && a.getIdhabitacion().getEstado().equals("danger")) {
                res = a.getClientes().getNombreCliente() + " " + a.getClientes().getApellido();
            }

        }
        return res;

    }

    public String nombrehuespedm2() {
        String res = "";
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
                res = a.getClientes().getNombreCliente() + " " + a.getClientes().getApellido();
            }
        }
        return res;
    }
    
     public Date diaentrada() {
        Date res=new Date();
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
             res =a.getFechaentrada();
            }
        }
        return res;
    }
     
     public Date horaentrada() {
        Date res=new Date();
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
             res =a.getHoraentrada();
            }
        }
        return res;
    }
    
     public Date diasalida() {
        Date res=new Date();
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
             res =a.getFechasalida();
            }
        }
        return res;
    }
    
     public Date horasalida() {
        Date res=new Date();
        List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
             res =a.getHorasalida();
            }
        }
        return res;
    }
     
     public String iteracion(Integer numero) throws Exception{
         String res="";
         List<Alquiler> listalquiler = managerapp.listaalquiler();
        for (Alquiler a : listalquiler) {
            Integer nuevoid = a.getIdhabitacion().getNumero();
            if (nuevoid.equals(numero) && a.getIdhabitacion().getEstado().equals("danger")) {
             res="5";
            }else{
                res="0";
            }
        }
         return res;
     }
     
     
     public void habilitarcuarto()
{
    List<Alquiler> listalquiler = managerapp.listaalquiler();
    
        
        int x=0;
      
            for (Alquiler a : listalquiler) {
             
            
            if(a.getIdhabitacion().getIdhabitacion()==habit.getIdhabitacion())
            {
            x=a.getIdalquiler();
            }
                
            
            }
          
        try {
            managerapp.editaralquilerhabitacion(x,new Date(),new Date(), new Date());
        } catch (Exception ex) {
            Logger.getLogger(controladorecepcion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         
   
}
    
 
}
