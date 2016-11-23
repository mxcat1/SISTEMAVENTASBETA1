/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mxcatv
 */
public class datos {
    conexion objcone=new conexion();
    PreparedStatement instrucion=null;
    ResultSet registro=null;
    public boolean validauser(String user,String pass){
        objcone.conexion();
        try{
            instrucion=objcone.con.prepareStatement("select * from EMPLEADOS where USUARIO=? and PASS=?");
            instrucion.setString(1, user);
            instrucion.setString(2, pass);
            registro=instrucion.executeQuery();
            
            if(registro.next()){
                
                objcone.cerrarconexion(registro, instrucion);
                return  true;
            }
            else{
                objcone.cerrarconexion(registro, instrucion);
                return false;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR CONSULTA "+e);
            objcone.cerrarconexion(registro, instrucion);
            return false;
        }
    }
    
}
