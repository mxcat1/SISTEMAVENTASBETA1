/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import DATOS.conexion;
import DATOS.dventas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author MXCATTV12
 */
public class vcventas {
    public conexion objcon = new conexion();
    public dventas dts=new dventas();
    public PreparedStatement prst;
    public ResultSet rs;
    
    public String nueva_venta(String codventa){
        objcon.conexion();
        String newcodventa;
        try{
            prst=objcon.con.prepareStatement("SELECT TOP 1 CAST(SUBSTRING(COD_CABE,2,10)AS INT)+1 AS NEWCOD FROM VENTA_CABECERA WHERE COD_CABE LIKE ?+'%' ORDER BY CAST(SUBSTRING(COD_CABE,2,10)AS INT) DESC");
            prst.setString(1, codventa);
            rs=prst.executeQuery();
            rs.next();
            newcodventa=String.valueOf(rs.getInt("NEWCOD"));
            objcon.cerrarconexion(rs, prst);
            return newcodventa;
            
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            objcon.cerrarconexion(rs, prst);
            return null;
        }
    }
    public boolean ingresoventacabe(dventas dts){
        objcon.conexion();
        try{
            prst=objcon.con.prepareStatement("INSERT INTO VENTA_CABECERA VALUES(?,?,?,?,?,?)");
            prst.setString(1, dts.cod_cabe);
            prst.setString(2, dts.cod_cli);
            prst.setString(3, dts.nombre);
            prst.setString(4, dts.fecha);
            prst.setDouble(5, dts.total);
            prst.setString(6, dts.cod_emp);
            int n=prst.executeUpdate();
            if(n!=0){
                objcon.cerrarconexion(rs, prst);
                return true;
            }
            else{
                objcon.cerrarconexion(rs, prst);
                return false;
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            objcon.cerrarconexion(rs, prst);
            return false;
        }
    }
    
    public boolean ingreventadetalle(dventas dts){
        objcon.conexion();
        try{
            prst=objcon.con.prepareStatement("INSERT INTO VENTA_DETALLE VALUES(?,?,?,?)");
            prst.setString(1, dts.cod_cabe);
            prst.setString(2, dts.cod_pro);
            prst.setInt(3, dts.canti);
            prst.setDouble(4, dts.parcial);
            
            int n=prst.executeUpdate();
            if(n!=0){
                objcon.cerrarconexion(rs, prst);
                return true;
            }
            else{
                objcon.cerrarconexion(rs, prst);
                return false;
            }
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            objcon.cerrarconexion(rs, prst);
            return false;
        }
    }
    
    
    
}
