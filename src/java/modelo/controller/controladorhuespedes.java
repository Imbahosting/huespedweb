/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.entidades.Clientes;
import modelo.manager.Managerapp;
import modelo.util.JSFUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Paulina
 */
@ManagedBean
@SessionScoped
public class controladorhuespedes {
    private Managerapp managerapp;
     private List<Clientes> listahuespedes;
     private String cedularuc;
     private String nombreCliente;
     private String apellido;
     private String telefono;
     private String ciudad;
     private boolean estadobtn=true;
    private boolean  estadoedit=true;
    private Clientes cliente;
     
    public controladorhuespedes(){
        managerapp=new Managerapp();

    }
    
        public void nuevohuesped() {
        estadobtn=false;
        estadoedit=true;
        cedularuc="";
        nombreCliente="";
        apellido="";
        telefono="";
        ciudad="";
    }
    
     public void crearhuesped()
 {
         try {
       
             managerapp.crearhuesped(cedularuc, nombreCliente, apellido, telefono, ciudad);
           JSFUtil.crearMensajeINFO("Huesped creado");
           estadoedit=false;
           estadobtn=true;
         } catch (Exception ex) {
             JSFUtil.crearMensajeERROR(""+ex);
             Logger.getLogger(controladorhuespedes.class.getName()).log(Level.SEVERE, null, ex);
         }
 }
     

    public List<Clientes> getListahuespedes() {
        listahuespedes=managerapp.listahuespedes();
        return listahuespedes;
    }
    
    public void setListahuespedes(List<Clientes> listahuespedes) {
        this.listahuespedes = listahuespedes;
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

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }


    
        
    public void verificarcliente() {

        try {
            Clientes c = managerapp.findClienteById(cedularuc);
                                                	                          
            // verificamos la existencia del cliente:                    
            if (c == null) {
                String[] datosCiudadano = consultarDatosCiudadano(cedularuc);
                JSFUtil.crearMensajeERROR("Registre el Cliente :" + cedularuc);
                
                estadoedit=true;
                                
                nombreCliente = "";
                apellido = "";
                
                if(datosCiudadano != null){
                    nombreCliente = datosCiudadano[1];
                    apellido = datosCiudadano[0];
                    
                    
                }                                
               
                telefono = "";
                ciudad= "";
                estadobtn=false;
            } else {
                //caso contrario si ya existe el cliente, recuperamos la informacion
                // para presentarla en la pagina de pedidos:
                nombreCliente = c.getNombreCliente();
                apellido = c.getApellido();
                telefono = c.getTelefono();
                ciudad= c.getCiudad();
                estadobtn=true;
                estadoedit=false;

                JSFUtil.crearMensajeINFO("Cliente encontrado");
            }

        } catch (Exception ex) {
            JSFUtil.crearMensajeERROR("error de servidor");
            Logger.getLogger(controladorhuespedes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  
    public String[] consultarDatosCiudadano(String identificacion){
        try{            
        
            String url = "https://www.aduana.gob.ec/wp-admin/admin-ajax.php";
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);           

            List<NameValuePair> urlParameters = new ArrayList<>();               

            urlParameters.add(new BasicNameValuePair("tipo", "MIGR"));
            urlParameters.add(new BasicNameValuePair("cedula", identificacion));
            urlParameters.add(new BasicNameValuePair("action", "crm_load_consultamigrantes"));            

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);            

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
                        
            String datos = result.toString().split(",")[4];
            datos = datos.replace("{", "").replace(":", "");
            datos = datos.replace("}", "");
            datos = datos.split("\"")[5];                        
            
            String cadena[] = datos.split(" ");
            
            String apellidos = cadena[0] + " " + cadena[1];
            String nombres = cadena[2] + " " + cadena[3];
                        
            return new String[]{apellidos, nombres};
        }
        catch (Exception ex) {              
            Logger.getLogger(controladorhuespedes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
        public void cargarhuesped(Clientes c)
     {
         cliente=c;
         estadobtn=true;
         estadoedit=false;
         cedularuc=c.getCedularuc();
         nombreCliente=c.getNombreCliente();
         apellido=c.getApellido();
         telefono=c.getTelefono();
         ciudad=c.getCiudad();  
     }
   
   public void editarhuesped()
   {
   
          try {
              Clientes c=managerapp.findClienteById(cedularuc);
              managerapp.editarhuesped(c.getClientes(),cedularuc, nombreCliente,apellido,telefono,ciudad);
              JSFUtil.crearMensajeWARN("Se edito el huesped");
          } catch (Exception ex) {
              JSFUtil.crearMensajeERROR("error de ser+ex"+ex);
              Logger.getLogger(controladortipo.class.getName()).log(Level.SEVERE, null, ex);
          }
       
   }
   

    
  
    }
