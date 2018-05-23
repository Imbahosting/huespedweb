/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.entidades.Alquiler;
import modelo.entidades.Clientes;
import modelo.entidades.Habitacion;
import modelo.entidades.Roles;
import modelo.entidades.Sucursal;
import modelo.entidades.Tipohabitacion;
import modelo.entidades.Usuario;

public class Managerapp {

    private ManagerDAO managerDAO;
    public String nombresuser;
    public int idusermanager;
    public int idsucursal;
    public int idempresa;
    public String nombrerolmanager;
    public int idinscripcionmanager = 0;
    public String Usuariocajero;
    public Date actual = new Date();

    public String nombresucursal;

    public Managerapp() {
        managerDAO = new ManagerDAO();

    }

    public boolean login(String Usuario, String Contrasenia) {
        List<Usuario> user = managerDAO.findAll(Usuario.class);
        for (Usuario u : user) {
            if (u.getUsuario().equals(Usuario) && u.getContrasenia().equals(Contrasenia)) {
                idusermanager = u.getIdUser();
                nombresuser = u.getNombre() + " " + u.getApellido();
                nombrerolmanager = u.getIdrol().getCargo();
                idsucursal = u.getIdsucursal().getIdsucursal();
                nombresucursal = u.getIdsucursal().getNombrecomercial();

                return true;
            }
        }
        return false;

    }

    public List<Habitacion> listahabitaciones() {
        return managerDAO.findAll(Habitacion.class,"o.numero");
    }

    public List<Clientes> listahuespedes() {
        return managerDAO.findAll(Clientes.class,"o.clientes");
    }

    public List<Usuario> listaempleados() {
        return managerDAO.findAll(Usuario.class,"o.idUser");
    }

    public List<Tipohabitacion> listatipos() {
        return managerDAO.findAll(Tipohabitacion.class);
    }

    public List<Habitacion> listaubicaciones() {
        List<Habitacion> temp = new ArrayList<>();

        // List<Productos> prod=managerDAO.execJPQL("select d from Productos d where d.nombreProducto like '%CER%'");
        //Debido a que son insuficientes los metodos genericos de ManagerDAO,
        //creamos un nuevo Query:
        EntityManager em = managerDAO.getEntityManager();
        String sql = "select h.ubicacion from Habitacion h group by h.ubicacion order by h.ubicacion";
        Query query = em.createQuery(sql);
        //pasamos los parametros a la consulta:

        //ejecutamos la consulta:
        temp = (List<Habitacion>) query.getResultList();

        return temp;
    }

    public void crearhuesped(String cedularuc, String nombreCliente, String apellido, String telefono, String ciudad) throws Exception {
        Clientes c = new Clientes();
        c.setCedularuc(cedularuc);
        c.setNombreCliente(nombreCliente);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setCiudad(ciudad);
        managerDAO.insertar(c);

    }

    public int retornaidcli(String cedula) {
        int idvalor = 0;
        List<Clientes> cl = managerDAO.findAll(Clientes.class);
        for (Clientes c : cl) {
            if (cedula.equals(c.getCedularuc())) {
                idvalor = c.getClientes();
            }

        }
        System.out.println("el valor id es " + idvalor);
        return idvalor;
    }

    public int retornaidhabitacion(Integer numero) {
        int idvalor = 0;
        List<Habitacion> ha = managerDAO.findAll(Habitacion.class);
        for (Habitacion h : ha) {
            if (numero.equals(h.getNumero())) {
                idvalor = h.getIdhabitacion();
            }

        }
        System.out.println("el valor id es " + idvalor);
        return idvalor;
    }

    //buscar un cliente por la cedula-----
    public Clientes findClienteById(String cedularuc) throws Exception {
        Clientes cliente = null;
        try {
            cliente = (Clientes) managerDAO.findById(Clientes.class, retornaidcli(cedularuc));

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar cliente metodo: " + e.getMessage());
        }
        return cliente;
    }
    //buscar un habitacion por numero-----

    public Habitacion findHabitacionByNumero(Integer numero) throws Exception {
        Habitacion habit = null;
        try {
            habit = (Habitacion) managerDAO.findById(Habitacion.class, retornaidhabitacion(numero));

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar habitacion metodo: " + e.getMessage());
        }
        return habit;
    }

    //buscar un cliente por la Empleado-----
    public Usuario findempleadobyId(String cedula) throws Exception {
        Usuario empleados = null;
        try {
            empleados = (Usuario) managerDAO.findById(Usuario.class, retornaidempleado(cedula));

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar cliente metodo: " + e.getMessage());
        }
        return empleados;
    }

    public int retornaidempleado(String cedula) {
        int idvalor = 0;
        List<Usuario> cl = managerDAO.findAll(Usuario.class);
        for (Usuario c : cl) {
            if (cedula.equals(c.getCedula())) {
                idvalor = c.getIdUser();
            }

        }
        System.out.println("el valor id es " + idvalor);
        return idvalor;
    }

    //elementos del comborolesusuario
    public List<Roles> listaroles() {
        return managerDAO.findAll(Roles.class);
    }

    public Sucursal findsucursalbyid(int idsucursal) throws Exception {
        Sucursal sucursal = null;
        try {
            sucursal = (Sucursal) managerDAO.findById(Sucursal.class, idsucursal);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al buscar sucursal metodo: " + e.getMessage());
        }
        return sucursal;
    }

    public void ingresarusuario(Integer idrol, Integer idsucursal, String cedula, String nombre, String apellido,
            String direccion, String telefono, String usuario, String contrasenia, boolean estado) throws Exception {
        Usuario u = new Usuario();
        Roles r = (Roles) managerDAO.findById(Roles.class, idrol);
        if (r == null) {
            throw new Exception("Usuario invalido");
        }
        Sucursal s = (Sucursal) managerDAO.findById(Sucursal.class, idsucursal);
        if (s == null) {
            throw new Exception("sucursal invalido");
        }
        u.setIdrol(r);
        u.setIdsucursal(s);
        u.setCedula(cedula);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setDireccion(direccion);
        u.setTelefono(telefono);
        u.setUsuario(usuario);
        u.setContrasenia(contrasenia);
        u.setEstado(estado);

        managerDAO.insertar(u);

    }

    public void editarempleado(Integer idusuario, Integer idrol, Integer idsucursal, String cedula,
            String nombre, String apellido, String direccion, String telefono, String usuario, String contrasenia, boolean estado) throws Exception {
        Usuario u = findusuariobyid(idusuario);

        Roles pr = (Roles) managerDAO.findById(Roles.class, idrol);
        if (pr == null) {
            throw new Exception("rol invalido");
        }
        Sucursal su = (Sucursal) managerDAO.findById(Sucursal.class, idsucursal);
        if (su == null) {
            throw new Exception("sucursal invalido");
        }
        u.setIdrol(pr);
        u.setIdsucursal(su);
        u.setCedula(cedula);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setDireccion(direccion);
        u.setTelefono(telefono);
        u.setUsuario(usuario);
        u.setContrasenia(contrasenia);
        u.setEstado(estado);
        managerDAO.actualizar(u);

    }

    public Usuario findusuariobyid(Integer idusuario) throws Exception {
        return (Usuario) managerDAO.findById(Usuario.class, idusuario);
    }
    
    public void creartipo(String nombre, String descripcion, double precio, double preciofraccion) throws Exception {
        Tipohabitacion c = new Tipohabitacion();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setPrecio(BigDecimal.valueOf(precio));
        c.setPreciofraccion(BigDecimal.valueOf(preciofraccion));
        managerDAO.insertar(c);
    }

    public void crearhabita(Integer idtipo, Integer numero, String ubicacion, String estado) throws Exception {
        Habitacion c = new Habitacion();
        Tipohabitacion r = (Tipohabitacion) managerDAO.findById(Tipohabitacion.class, idtipo);
        if (r == null) {
            throw new Exception("Tipo invalido");
        }
        c.setIdtipo(r);
        c.setNumero(numero);
        c.setUbicacion(ubicacion);
        c.setEstado(estado);
        managerDAO.insertar(c);
    }

    public void editarhabita(Integer idhabitacion, Integer idtipo, Integer numero, String ubicacion, String estado) throws Exception {
        Habitacion h = finbyidhabitacion(idhabitacion);

        Tipohabitacion t = (Tipohabitacion) managerDAO.findById(Tipohabitacion.class, idtipo);
        if (t == null) {
            throw new Exception("Tipo de habitacion invalido");
        }

        h.setIdtipo(t);
        h.setNumero(numero);
        h.setUbicacion(ubicacion);
        h.setEstado(estado);

        managerDAO.actualizar(h);

    }

    public Habitacion finbyidhabitacion(Integer idhabitacion) throws Exception {
        return (Habitacion) managerDAO.findById(Habitacion.class, idhabitacion);
    }

    public void editartipo(Integer idtipo, String nombre, String descripcion, double precio, double preciofraccion) throws Exception {
        Tipohabitacion t = finbyidtipohabitacion(idtipo);

        t.setNombre(nombre);
        t.setDescripcion(descripcion);
        t.setPrecio(BigDecimal.valueOf(precio));
        t.setPreciofraccion(BigDecimal.valueOf(preciofraccion));

        managerDAO.actualizar(t);

    }

    public Tipohabitacion finbyidtipohabitacion(Integer idtipo) throws Exception {
        return (Tipohabitacion) managerDAO.findById(Tipohabitacion.class, idtipo);
    }

    public void editarhuesped(Integer clientes, String cedularuc, String nombreCliente, String apellido, String telefono, String ciudad) throws Exception {
        Clientes c = finbyidclientes(clientes);
        c.setCedularuc(cedularuc);
        c.setNombreCliente(nombreCliente);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setCiudad(ciudad);

        managerDAO.actualizar(c);

    }

    public Clientes finbyidclientes(Integer clientes) throws Exception {
        return (Clientes) managerDAO.findById(Clientes.class, clientes);
    }

    public void alquilar(Integer iduser, Integer idhabitacion, Integer clientes, Date fechaentrada, Date fechasalida, Date horaentrada, Date horasalida,Date horalimpieza, Integer totaldedias, Integer totaldehoras, double preciototal) throws Exception {
        Alquiler a = new Alquiler();
        Usuario u = (Usuario) managerDAO.findById(Usuario.class, iduser);
        if (u == null) {
            throw new Exception("Usuario invalido");
        }
        Habitacion h = (Habitacion) managerDAO.findById(Habitacion.class, idhabitacion);
        if (h == null) {
            throw new Exception("Habitacion invalida");
        }
        Clientes c = (Clientes) managerDAO.findById(Clientes.class, clientes);
        if (c == null) {
            throw new Exception("Cliente invalido");
        }
        a.setIdUser(u);
        a.setIdhabitacion(h);
        a.setHoralimpieza(horalimpieza);
        a.setClientes(c);
        a.setFechaentrada(fechaentrada);
        a.setFechasalida(fechasalida);
        a.setHoraentrada(horaentrada);
        a.setHorasalida(horasalida);
        a.setTotaldedias(totaldedias);
        a.setTotaldehoras(totaldehoras);
        a.setPreciototal(BigDecimal.valueOf(preciototal));        
        managerDAO.insertar(a);
        h.setEstado("danger");
        managerDAO.actualizar(h);
    }

    public List<Alquiler> listaalquiler() {
        return managerDAO.findAll(Alquiler.class);
    }
    public List<Alquiler> listaalquilerdia() {
         double x=0.0;
   
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
        List<Alquiler> alui=new ArrayList<>();
        Date hoy=new Date();
    for (Alquiler a : listaalquiler())
    {
        
        if (d.format(hoy).compareTo(d.format(a.getFechaentrada()))==0)
        {
           alui.add(a);
        }
        
    }
    return alui;
    }
    
    public void editarcolor(Integer idhabitacion) throws Exception{
        Habitacion h= finbyidhabitacion(idhabitacion);
        h.setEstado("success");
        managerDAO.actualizar(h);
    }
     public void editarcolorrojo(Integer idhabitacion) throws Exception{
        Habitacion h= finbyidhabitacion(idhabitacion);
        h.setEstado("danger");
        managerDAO.actualizar(h);
    }
    
    public void editarcoloramarillo(Integer idhabitacion) throws Exception{
      Habitacion h= finbyidhabitacion(idhabitacion);
        h.setEstado("warning");
        managerDAO.actualizar(h);
    }
    
    public void editaralquilerhabitacion(int idalquiler,Date fechasalida,Date horasalida, Date horlimpieza) throws Exception
{
   Alquiler a=(Alquiler) managerDAO.findById(Alquiler.class,idalquiler);
   a.setFechasalida(fechasalida);
   a.setHorasalida(horasalida);
   a.setHoralimpieza(horlimpieza);
}


}
