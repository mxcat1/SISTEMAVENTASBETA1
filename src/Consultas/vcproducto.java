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
    public DefaultTableModel mostrarproductoscod(String cod){
        objcon.conexion();
        DefaultTableModel tablaprocod;
        String[] titulos={"CODIGO","NOMBRE","PRECIO","STOCK","ESTADO","CATEGORIA"};
        tablaprocod=new DefaultTableModel(null,titulos);
        String[] datospro=new String[6];
        try{
            prsta=objcon.con.prepareStatement("SELECT COD_PRO,NOM_PRO,PRE_U_PRO,STOCK_PRO,ESTADO,NOM_CAT "+
                    "FROM PRODUCTOS INNER JOIN CATEGORIA ON (PRODUCTOS.COD_CAT=CATEGORIA.COD_CAT) "+
                    "WHERE COD_PRO LIKE ?+'%' ORDER BY CAST(SUBSTRING(COD_PRO,2,10) AS INT) ASC");
            prsta.setString(1, cod);
            if(cod==null || cod.charAt(1)=='0'){
                
                prsta.setString(1, "P");
            }
            else{
                prsta.setString(1, cod);
            }
            rs=prsta.executeQuery();
            while(rs.next()){
                datospro[0]=rs.getString("COD_PRO");
                datospro[1]=rs.getString("NOM_PRO");
                datospro[2]=rs.getString("PRE_U_PRO");
                datospro[3]=rs.getString("STOCK_PRO");
                datospro[4]=rs.getString("ESTADO");
                datospro[5]=rs.getString("NOM_CAT");
                
                tablaprocod.addRow(datospro);
            }
            objcon.cerrarconexion(rs, prsta);
            return tablaprocod;
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    public String[] regrosdatospro(String cod,String nompro){
        objcon.conexion();
        String[] datospro=new String[6];
        try{
            if(cod!=null){
                prsta=objcon.con.prepareStatement("SELECT COD_PRO,NOM_PRO,PRE_U_PRO,STOCK_PRO,ESTADO,NOM_CAT "+
                    "FROM PRODUCTOS INNER JOIN CATEGORIA ON (PRODUCTOS.COD_CAT=CATEGORIA.COD_CAT) WHERE COD_PRO=? ");
                prsta.setString(1, cod);
            }
            else if (nompro!=null){
                prsta=objcon.con.prepareStatement("SELECT COD_PRO,NOM_PRO,PRE_U_PRO,STOCK_PRO,ESTADO,NOM_CAT "+
                    "FROM PRODUCTOS INNER JOIN CATEGORIA ON (PRODUCTOS.COD_CAT=CATEGORIA.COD_CAT) WHERE NOM_PRO=? ");
                prsta.setString(1, nompro);
                
            }
            rs=prsta.executeQuery();
            while(rs.next()){
                datospro[0]=rs.getString("COD_PRO");
                datospro[1]=rs.getString("NOM_PRO");
                datospro[2]=rs.getString("PRE_U_PRO");
                datospro[3]=rs.getString("STOCK_PRO");
                datospro[4]=rs.getString("ESTADO");
                datospro[5]=rs.getString("NOM_CAT");
            }
            objcon.cerrarconexion(rs, prsta);
            return datospro;

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    
    public DefaultComboBoxModel categoriapro(){
        objcon.conexion();
        DefaultComboBoxModel modelocat=new DefaultComboBoxModel();
        try{
            prsta=objcon.con.prepareStatement("SELECT NOM_CAT FROM CATEGORIA");
            rs=prsta.executeQuery();
            while(rs.next()){
                modelocat.addElement(rs.getString("NOM_CAT"));
            }
            return modelocat;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    public String paracodigonuevo(){
        objcon.conexion();
        String cod;
        try{
            prsta=objcon.con.prepareStatement("SELECT TOP 1 CAST(SUBSTRING(COD_PRO,2,10)AS INT)+1 AS NUMERO FROM PRODUCTOS ORDER BY CAST(SUBSTRING(COD_PRO,2,10) AS INT) DESC");
            rs=prsta.executeQuery();
            rs.next();
            cod=String.valueOf(rs.getInt("NUMERO"));
            objcon.cerrarconexion(rs, prsta);
            return cod;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    
    public boolean nuevopro(dproducto dts){
        objcon.conexion();
        try{
            prsta=objcon.con.prepareStatement("INSERT INTO PRODUCTOS VALUES(?,?,?,?,?,?)");
            prsta.setString(1, dts.getCodpro());
            prsta.setString(2, dts.getNompro());
            prsta.setDouble(3, dts.getPreupro());
            prsta.setInt(4, dts.getStock());
            prsta.setString(5, dts.getEstado());
            prsta.setString(6, dts.getCodcategoria());
            int n=prsta.executeUpdate();
            if(n!=0){
                objcon.cerrarconexion(rs, prsta);
                return true;
            }
            else{
                objcon.cerrarconexion(rs, prsta);
                return false;
            }
            
            
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e.getMessage());
            objcon.cerrarconexion(rs, prsta);
            return false;
        }
        
    }
    public boolean actualizarpro(dproducto dts){
        objcon.conexion();
        try{
            prsta=objcon.con.prepareStatement("UPDATE PRODUCTOS SET NOM_PRO=?,PRE_U_PRO=?,STOCK_PRO=?"+
                    ",ESTADO=?,COD_CAT=? WHERE COD_PRO=?");
            prsta.setString(1, dts.getNompro());
            prsta.setDouble(2, dts.getPreupro());
            prsta.setInt(3, dts.getStock());
            prsta.setString(4, dts.getEstado());
            prsta.setString(5, dts.getCodcategoria());
            prsta.setString(6, dts.getCodpro());
            
            int n=prsta.executeUpdate();
            
            if(n!=0){
                objcon.cerrarconexion(rs, prsta);
                return true;
            }
            else{
                objcon.cerrarconexion(rs, prsta);
                return false;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            objcon.cerrarconexion(rs, prsta);
            return false;
        }
    }
    
    public DefaultComboBoxModel nombrepro_preu(){
        objcon.conexion();
        DefaultComboBoxModel modelopro=new DefaultComboBoxModel();
        try{
            prsta=objcon.con.prepareStatement("SELECT NOM_PRO,PRE_U_PRO,STOCK_PRO FROM PRODUCTOS WHERE ESTADO= 'A' ORDER BY CAST(SUBSTRING(COD_PRO,2,10)AS INT)");
            rs=prsta.executeQuery();
            while(rs.next()){
                modelopro.addElement(rs.getString("NOM_PRO")+" ->S/. "+ rs.getString("PRE_U_PRO")+" ->#N "+rs.getString("STOCK_PRO"));
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
    public String[] regrosdatospro1(String cod_pro){
        objcon.conexion();
        String[] datospro=new String[4];
        try{
            prsta=objcon.con.prepareStatement("SELECT COD_PRO,NOM_PRO,PRE_U_PRO FROM PRODUCTOS WHERE COD_PRO=?");
            prsta.setString(1, cod_pro);
            
            rs=prsta.executeQuery();
            while(rs.next()){
                datospro[0]=rs.getString("COD_PRO");
                datospro[1]=rs.getString("NOM_PRO");
                datospro[2]=rs.getString("PRE_U_PRO");
            }
            objcon.cerrarconexion(rs, prsta);
            return datospro;

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
    public String[] codproducto(){
        objcon.conexion();
        String[] cod_pro=new String[99999999];
        int n=0;
        try{
            prsta=objcon.con.prepareStatement("SELECT COD_PRO FROM PRODUCTOS ORDER BY CAST(SUBSTRING(COD_PRO,2,10)AS INT )");
            rs=prsta.executeQuery();
            while(rs.next()){
                cod_pro[n]=rs.getString("COD_PRO");
                n++;
            }
            objcon.cerrarconexion(rs, prsta);
            return cod_pro;
            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            objcon.cerrarconexion(rs, prsta);
            return null;
        }
    }
}
