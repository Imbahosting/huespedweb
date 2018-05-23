/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.entidades.Alquiler;
import modelo.manager.Managerapp;

/**
 *
 * @author Paulina
 */
@ManagedBean
@SessionScoped
public class controladorcaja {
    private Managerapp managerapp;
    
    private List<Alquiler> listaalquiler;
    private double valordeldiavar=0.0;
    
    
    public controladorcaja(){
        managerapp = new Managerapp();
    }
public void valordeldia()
{
    
     double x=0.0;
     double var2=0.0;
    listaalquiler=managerapp.listaalquiler();
       
    for (Alquiler a : listaalquiler)
    {
        Date hoy = new Date();
         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        if (formatter.format(hoy).compareTo(formatter.format(a.getFechasalida()))==0) {
            x=a.getPreciototal().doubleValue();
            var2=var2+x;   
        }
            
    }
    valordeldiavar=var2;
}
    
    public List<Alquiler> getListaalquiler() {
         listaalquiler=managerapp.listaalquilerdia();
        return listaalquiler;
    }

    public void setListaalquiler(List<Alquiler> listaalquiler) {
        this.listaalquiler = listaalquiler;
    }

    public double getValordeldiavar() {
        return valordeldiavar;
    }

    public void setValordeldiavar(double valordeldiavar) {
        this.valordeldiavar = valordeldiavar;
    }

   
    
  
    
    
}
