/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import DATOS.conexion;
import DATOS.dproducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MXCATTV12
 */
public class vcproducto {
    public dproducto objpro=new dproducto();
    public conexion objcon=new conexion();
    public PreparedStatement prsta;
    public ResultSet rs;
    
    public DefaultTableModel mostrar_productos(String letranom){
        objcon.conexion();
        DefaultTableModel productostabla;
        String[] titulos={"CODIGO","NOMBRE","PRECIO","STOCK","ESTADO","CATEGORIA"};
        String[] datospro=new String[6];
        productostabla=new DefaultTableModel(null,titulos);
        
        try{
            prsta=objcon.con.prepareStatement("SELECT COD_PRO,NOM_PRO,PRE_U_PRO,STOCK_PRO,ESTADO,NOM_CAT FROM "+
                    "PRODUCTOS INNER JOIN CATEGORIA ON (PRODUCTOS.COD_CAT=CATEGORIA.COD_CAT) "+
                    "WHERE NOM_PRO LIKE ?+'%'"+
                    "ORDER BY CAST(SUBSTRING(COD_PRO,2,10)AS INT) ASC");
            if(letranom==null){
                prsta.setString(1, "");
            }
            else{
                prsta.setString(1, letranom);
            }
            rs=prsta.executeQuery();
            while(rs.next()){
                datospro[0]=rs.getString("COD_PRO");
                datospro[1]=rs.getString("NOM_PRO");
                datospro[2]=rs.getString("PRE_U_PRO");
                datospro[3]=rs.getString("STOCK_PRO");
                datospro[4]=rs.getString("ESTADO");
                datospro[5]=rs.getString("NOM_CAT");
                
                productostabla.addRow(datospro);
            }
            objcon.cerrarconexion(rs, prsta);
            return productostabla;
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+ e);
            objcon.cerrarconexion(rs, prsta);
            return null;
            
        }
    }
    public DefaultComboBoxModel nombrepro(String letras){
        objcon.conexion();
        DefaultComboBoxModel modelopro=new DefaultComboBoxModel();
        try{
            prsta=objcon.con.prepareStatement("SELECT NOM_PRO FROM PRODUCTOS WHERE NOM_PRO LIKE ?+'%' ORDER BY CAST(SUBSTRING(COD_PRO,2,10)AS INT)");
            if(letras==null){
                prsta.setString(1, "");
            }
            else{
                prsta.setString(1, letras);
            }
            rs=prsta.executeQuery();
            while(rs.next()){
                modelopro.addElement(rs.getString("NOM_PRO"));
            }
            objcon.cerrarconexion(rs, prsta);
            return modelopro;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR"+ e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    public DefaultComboBoxModel codpro(String letras){
        objcon.conexion();
        DefaultComboBoxModel modelopro=new DefaultComboBoxModel();
        try{
            prsta=objcon.con.prepareStatement("SELECT COD_PRO FROM PRODUCTOS WHERE COD_PRO LIKE 'P'+?+'%' ORDER BY CAST(SUBSTRING(COD_PRO,2,10)AS INT)");
            if(letras==null){
                prsta.setString(1, "");
            }
            else{
                prsta.setString(1, letras);
            }
            rs=prsta.executeQuery();
            while(rs.next()){
                modelopro.addElement(rs.getString("NOM_PRO"));
            }
            objcon.cerrarconexion(rs, prsta);
            return modelopro;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR"+ e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    
}
