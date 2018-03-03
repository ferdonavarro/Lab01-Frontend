/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author Fernando
 */

import AccesoDatos.ServicioProducto;
import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Interfaz.Desktop.Desktop;
import AccesoDatos.ServicioProducto;
import LogicaNegocio.Producto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
public class Control implements Observer{
    
    Desktop view;
    ServicioProducto model;
    
    public Control() {
     view = new Desktop(this);
    }

    public ServicioProducto getModel() {
        return model;
    }

    public void setModel(ServicioProducto model) {
        this.model = model;
    }
    
    public void startApplication() {
        // View the application's GUI
        
        view.setVisible(true);
        
        
        cargarProductos();
    }
    
    public void agregarProducto(int cod,String nomb,Boolean imp,BigDecimal prec,String tip){
        Producto p=new Producto(cod,nomb,imp,prec,tip);
        try {
            model.insertarproducto(p);
        } catch (GlobalException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     public void cargarProductos(){
        try {
            view.mostrarProductos(model.allproductos());
        } catch (GlobalException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
   
    public static void main(String args[]) {
        
        Control c=new Control();
        ServicioProducto s =ServicioProducto.getServicioProducto(c);
        c.setModel(s);
        c.startApplication();
        
    }

   
    @Override
    public void update(Observable o, Object arg) {
        System.out.print("observado");
        cargarProductos();
    }
}
