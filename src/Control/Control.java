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

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import Interfaz.Desktop.Desktop;
import AccesoDatos.ServicioProducto;
import LogicaNegocio.Producto;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Control {
    
    Desktop view;
    ServicioProducto model;
    
    public Control() {
    }
    
    public void startApplication() {
        // View the application's GUI
        view = new Desktop(this);
        view.setVisible(true);
        model=ServicioProducto.getServicioProducto();
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
    
    public static void main(String args[]) {
        Control c=new Control();
        c.startApplication();
    }
}
