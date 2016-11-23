/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import DATOS.Clientes;
import DATOS.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MXCATTV12
 */
public class vccliente {
    private Clientes dtsclie=new Clientes();
    private conexion objcone=new conexion();
    private PreparedStatement prsta;
    private Statement sta1;
    private ResultSet rstex;
    private DefaultListModel modelo = new DefaultListModel();
    private DefaultComboBoxModel cbmodel=new DefaultComboBoxModel();
    
    public DefaultComboBoxModel listaclienom(){
        objcone.conexion();
        //String [] dtcli=new String[9999];
        //int n=0;
        try{
            prsta=objcone.con.prepareStatement("SELECT NOM_CLI,APE_CLI FROM CLIENTES ORDER BY CAST(substring(COD_CLI,2,10) AS INT)");
            rstex=prsta.executeQuery();
            while(rstex.next()){
                /*dtcli[n]=(rstex.getString("NOM_CLI")) + "  " + rstex.getString("APE_CLI");*/
                
                cbmodel.addElement(rstex.getString("NOM_CLI")+" "+ rstex.getString("APE_CLI"));
                //n++;
            }
            objcone.cerrarconexion(rstex, prsta);
            return cbmodel;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return cbmodel;
        }
    }
    public String[] listacod_cli(){
        objcone.conexion();
        String[] listcodcli=new String[9999];
        int n=0;
        try{
            prsta=objcone.con.prepareStatement("SELECT COD_CLI FROM CLIENTES ORDER BY CAST(SUBSTRING(COD_CLI,2,10) AS INT)");
            rstex=prsta.executeQuery();
            while(rstex.next()){
                listcodcli[n]=rstex.getString("COD_CLI");
                n++;
            }
            objcone.cerrarconexion(rstex, prsta);
            return listcodcli;
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+ e);
            objcone.cerrarconexion(rstex, prsta);
            return null;
        }
    }
    public String[] busquedadclie(Clientes dts){
        objcone.conexion();
        String[] registrocli =new String[9];
        int n=0;
        
        try{
            prsta=objcone.con.prepareStatement("SELECT * FROM CLIENTES WHERE COD_CLI=?");
            prsta.setString(1, dts.getCodclie());
            rstex=prsta.executeQuery();
            if(rstex.next()){
                registrocli[0]=rstex.getString("COD_CLI");
                registrocli[1]=rstex.getString("NOM_CLI");
                registrocli[2]=rstex.getString("APE_CLI");
                registrocli[3]=rstex.getString("FONO_CLI");
                registrocli[4]=rstex.getString("DNI_CLI");
                registrocli[5]=rstex.getString("RUC_CLI");
                registrocli[6]=rstex.getString("SEXO_CLI");
                registrocli[7]=rstex.getString("COD_DIST");
                registrocli[8]=rstex.getString("DIRE_CLI");
            }
            objcone.cerrarconexion(rstex, prsta);
            return registrocli;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return registrocli;
        }
    }
    public String nuevocodclie(){
        objcone.conexion();
        String codcli="";
        try{
            prsta=objcone.con.prepareStatement("SELECT TOP 1 COD_CLI FROM CLIENTES ORDER BY CAST(substring(COD_CLI,2,10) AS INT) DESC");
            rstex=prsta.executeQuery();
            rstex.next();
            codcli=rstex.getString("COD_CLI");
            objcone.cerrarconexion(rstex, prsta);
            return codcli;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            e.printStackTrace();
            objcone.cerrarconexion(rstex, prsta);
            return codcli;
        }
    }
    public DefaultComboBoxModel dtdistritos(){
        objcone.conexion();
        DefaultComboBoxModel distmodel=new DefaultComboBoxModel();
        try{
            prsta=objcone.con.prepareStatement("SELECT DISTRITOS.NOM_DIST FROM DISTRITOS");
            rstex=prsta.executeQuery();
            while(rstex.next()){
                distmodel.addElement(rstex.getString("NOM_DIST"));
            }
            objcone.cerrarconexion(rstex, prsta);
            return distmodel;
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return distmodel;
        }
    }
    public boolean actualizardatos(Clientes dts){
        objcone.conexion();
        try{
            prsta=objcone.con.prepareStatement("UPDATE CLIENTES SET NOM_CLI=?,APE_CLI=?,FONO_CLI=?,"+
                                        "DNI_CLI=?,RUC_CLI=?,SEXO_CLI=?,COD_DIST=?,DIRE_CLI=? WHERE COD_CLI=?");
            prsta.setString(1, dts.getNombre());
            prsta.setString(2, dts.getApellido());
            prsta.setString(3, dts.getFonoclie());
            prsta.setString(4, dts.getDni());
            prsta.setString(5,dts.getRucclie());
            prsta.setString(6, dts.getSexo());
            prsta.setInt(7, dts.getCoddist());
            prsta.setString(8, dts.getDirecli());
            prsta.setString(9, dts.getCodclie());
            int n=prsta.executeUpdate();
            if(n!=0){
                objcone.cerrarconexion(rstex, prsta);
                return true;
            }
            else{
                objcone.cerrarconexion(rstex, prsta);
                return false;
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return false;
        }
    }
    public boolean insertarnuevocliente(Clientes dts){
        objcone.conexion();
        try{
            prsta=objcone.con.prepareStatement("INSERT INTO CLIENTES(COD_CLI,NOM_CLI,APE_CLI,FONO_CLI,DNI_CLI,RUC_CLI,SEXO_CLI,COD_DIST,DIRE_CLI) "+
                                                "VALUES (?,?,?,?,?,?,?,?,?)");
            prsta.setString(1, dts.getCodclie());
            prsta.setString(2, dts.getNombre());
            prsta.setString(3, dts.getApellido());
            prsta.setString(4, dts.getFonoclie());
            prsta.setString(5, dts.getDni());
            prsta.setString(6, dts.getRucclie());
            prsta.setString(7, dts.getSexo());
            prsta.setInt(8, dts.getCoddist());
            prsta.setString(9, dts.getDirecli());
            
            int n=prsta.executeUpdate();
            if(n!=0){
                objcone.cerrarconexion(rstex, prsta);
                return true;
            }
            else{
                objcone.cerrarconexion(rstex, prsta);
                return false;
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return false;
        }
    }
    public boolean eleminarcli(Clientes dts){
        objcone.conexion();
        try{
            prsta=objcone.con.prepareStatement("DELETE FROM CLIENTES WHERE (COD_CLI=?)");
            prsta.setString(1, dts.getCodclie());
            int n=prsta.executeUpdate();
            if(n!=0){
                objcone.cerrarconexion(rstex, prsta);
                return true;
            }
            else{
                objcone.cerrarconexion(rstex, prsta);
                return false;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return false;
        }
    }
    public DefaultTableModel mostrarclientes(){
        DefaultTableModel tablaclientes;
        String [] titulos={"CODIGO","NOMBRE","APELLIDO","TELEFONO","DNI","RUC","SEXO","DISTRITO","DIRECCION"};
        String [] datostabla=new String [9];
        tablaclientes=new DefaultTableModel(null,titulos);
        objcone.conexion();
        try{
            prsta=objcone.con.prepareStatement("SELECT COD_CLI,NOM_CLI,APE_CLI,FONO_CLI,DNI_CLI,RUC_CLI,SEXO_CLI,NOM_DIST,DIRE_CLI "+
                    " FROM CLIENTES INNER JOIN DISTRITOS ON (CLIENTES.COD_DIST=DISTRITOS.COD_DIST)");
            rstex=prsta.executeQuery();
            while(rstex.next()){
                datostabla[0]=rstex.getString("COD_CLI");
                datostabla[1]=rstex.getString("NOM_CLI");
                datostabla[2]=rstex.getString("APE_CLI");
                datostabla[3]=rstex.getString("FONO_CLI");
                datostabla[4]=rstex.getString("DNI_CLI");
                datostabla[5]=rstex.getString("RUC_CLI");
                datostabla[6]=rstex.getString("SEXO_CLI");
                datostabla[7]=rstex.getString("NOM_DIST");
                datostabla[8]=rstex.getString("DIRE_CLI");
                
                tablaclientes.addRow(datostabla);
            }
            objcone.cerrarconexion(rstex, prsta);
            return tablaclientes;
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rstex, prsta);
            return tablaclientes;
        }
    }
}
