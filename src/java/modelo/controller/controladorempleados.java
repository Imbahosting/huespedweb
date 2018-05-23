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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import modelo.entidades.Roles;
import modelo.entidades.Sucursal;
import modelo.entidades.Usuario;

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
public class controladorempleados {
    
    @ManagedProperty(value = "#{controladorusuarios}")
     private controladorusuarios usuarios;
    
    private Managerapp managerapp;
    private Integer idrol;
    private Integer idsucursal;
    private String ciudaduser;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String usuario;
    private String contrasenia;
    private boolean estado;
    private boolean estadobtn;
    private boolean  estadoedit;
    private List<Usuario> listaempleados;
    private boolean mostrarRegistroEmpleado = false;
    private Usuario user;
    
     private List<SelectItem> selectrol;
     private Roles rolesentidad;
     private List<Roles> roles;
     
      //PASO 2 COMBO ROL
     
     public List<SelectItem> getSelectrol() {
        
         selectrol=new ArrayList<SelectItem>();
         selectrol.clear();
         roles= managerapp.listaroles();
         for (Roles r: roles)
         {
           SelectItem item=new SelectItem(r.getIdrol(),r.getCargo());
      selectrol.add(item);
         }
         return selectrol;
    
    }
    
    public controladorempleados() {

        managerapp = new Managerapp();
    }
    
    public List<Usuario> getListaempleados() {
       listaempleados=managerapp.listaempleados();
        return listaempleados;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
    

    public void setListaempleados(List<Usuario> listaempleados) {
        this.listaempleados = listaempleados;
    }


    public Roles getRolesentidad() {
        return rolesentidad;
    }

    public void setRolesentidad(Roles rolesentidad) {
        this.rolesentidad = rolesentidad;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }



    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public controladorusuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(controladorusuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    

     
        public void verificarempleado() {

        try {
            Usuario u = managerapp.findempleadobyId(cedula);
                                                	                          
            // verificamos la existencia del empleado:                    
            if (u == null) {
                String[] datosCiudadano = consultarDatosCiudadano(cedula);
                JSFUtil.crearMensajeERROR("Registre al Empleado :" + cedula);
                this.mostrarRegistroEmpleado = true;
                                
                nombre = "";
                apellido = "";
                
                if(datosCiudadano != null){
                    nombre = datosCiudadano[1];
                    apellido = datosCiudadano[0];
                     setDireccion("");
                setTelefono("");
                setUsuario("");
                setContrasenia("");
                }                                
                
               estado=false;
               
            } else {
                //caso contrario si ya existe el cliente, recuperamos la informacion
                // para presentarla en la pagina de pedidos:
                nombre= u.getNombre();
                apellido = u.getApellido();
                direccion=u.getDireccion();
                telefono=u.getTelefono();
                usuario=u.getUsuario();
                contrasenia=u.getContrasenia();
                estado=u.getEstado();
                idrol=u.getIdrol().getIdrol();
             

                JSFUtil.crearMensajeINFO("Empleado encontrado");

            }

        } catch (Exception ex) {
            JSFUtil.crearMensajeERROR("error de servidor");
            Logger.getLogger(controladorempleados.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controladorempleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public String getCiudaduser() {
        String a = "";
        try {
            Sucursal s=managerapp.findsucursalbyid(usuarios.getIdsucursal());
            
            a= s.getNombrecomercial();
        } catch (Exception ex) {
            Logger.getLogger(controladorempleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public void setCiudaduser(String ciudaduser) {
        this.ciudaduser = ciudaduser;
    }


    
     public void insertarregistrodeusuario()
     {
           try {
             managerapp.ingresarusuario(idrol,usuarios.getIdsucursal(), cedula, nombre, apellido, direccion, telefono, usuario, contrasenia, estado);
               setCedula("");
             setNombre("");
             setApellido("");
             setDireccion("");
                setTelefono("");
                setUsuario("");
                setContrasenia("");
                JSFUtil.crearMensajeINFO("Registrado con éxito");
            
           } catch (Exception ex) {
               JSFUtil.crearMensajeERROR("Error de Servidor: contacte con el proveedor");
               Logger.getLogger(controladorempleados.class.getName()).log(Level.SEVERE, null, ex);
           }
     }
     
          public void limpiarcampos()
     {
          estadobtn=false;
          estadoedit=true;
         cedula="";
         nombre="";
         apellido="";
         estado=true;
         contrasenia="";
         direccion="";
         telefono="";
         usuario="";
       
     }
          
     public void editarempleado()
     {
        try {
            managerapp.editarempleado(user.getIdUser(), idrol,usuarios.getIdsucursal(), cedula, nombre, apellido, direccion, telefono, usuario, contrasenia, estado);
        JSFUtil.crearMensajeWARN("EDITADO");
        } catch (Exception ex) {
            JSFUtil.crearMensajeERROR(""+ex);
            Logger.getLogger(controladorempleados.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void cargarempleado(Usuario u)
     {
         user=u;
         estadobtn=true;
         estadoedit=false;
         cedula=u.getCedula();
        nombre=u.getNombre();
        apellido=u.getApellido();
        direccion=u.getDireccion();
        telefono=u.getTelefono();
        usuario=u.getUsuario();
        contrasenia=u.getContrasenia();
        idrol=u.getIdrol().getIdrol();
        estado=u.getEstado();
     
     }

}
