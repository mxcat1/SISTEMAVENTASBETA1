/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Consultas;

import DATOS.Dempleados;
import DATOS.conexion;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Semaphore;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mxcatv
 */
public class vcempleado {
    private Dempleados dtsemp=new Dempleados();
    private conexion objcone=new conexion();
    private PreparedStatement prts;
    private ResultSet rs;
    private DefaultComboBoxModel modelo1=new DefaultComboBoxModel();
    
    public DefaultComboBoxModel buscaremp(){
        objcone.conexion();
        String[] cbclientes=new String[99];
        int n=0;
        try{
            prts=objcone.con.prepareStatement("SELECT NOM_EMP,APE_EMP FROM EMPLEADOS ORDER BY CAST(substring(COD_EMP,4,10) AS INT)");
            rs=prts.executeQuery();
            while(rs.next()){
                cbclientes[n]=rs.getString("NOM_EMP")+" "+rs.getString("APE_EMP");
                modelo1.addElement(cbclientes[n]);
                n++;
            }
            objcone.cerrarconexion(rs, prts);
            return modelo1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    public String[] codigoemp(){
        objcone.conexion();
        String[] codemp=new String[99];
        int n=0;
        try{
            prts=objcone.con.prepareStatement("SELECT COD_EMP FROM EMPLEADOS ORDER BY CAST(substring(COD_EMP,4,10) AS INT)");
            rs=prts.executeQuery();
            while(rs.next()){
                codemp[n]=rs.getString("COD_EMP");
                n++;
            }
            objcone.cerrarconexion(rs, prts);
            return codemp;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    public String codigoempunico(){
        objcone.conexion();
        String codemp;
        try{
            prts=objcone.con.prepareStatement("SELECT TOP 1 COD_EMP FROM EMPLEADOS ORDER BY CAST(substring(COD_EMP,4,10) AS INT) DESC");
            rs=prts.executeQuery();
            rs.next();
            codemp=rs.getString("COD_EMP");
            objcone.cerrarconexion(rs, prts);
            return codemp;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    public DefaultComboBoxModel usuariosemp(){
        objcone.conexion();
        DefaultComboBoxModel users=new DefaultComboBoxModel();
        try{
            prts=objcone.con.prepareStatement("SELECT USUARIO FROM EMPLEADOS ORDER BY CAST(SUBSTRING(COD_EMP,4,10) AS INT)");
            rs=prts.executeQuery();
            while(rs.next()){
                users.addElement(rs.getString("USUARIO"));
            }
            objcone.cerrarconexion(rs, prts);
            return users;
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    public String[] datosemp(Dempleados dts){
        objcone.conexion();
        String[] dtemp=new String[12];
        try{
            prts=objcone.con.prepareStatement("SELECT * FROM EMPLEADOS WHERE COD_EMP=?");
            prts.setString(1, dts.getCodemp());
            rs=prts.executeQuery();
            while(rs.next()){
                dtemp[0]=rs.getString("COD_EMP");
                dtemp[1]=rs.getString("NOM_EMP");
                dtemp[2]=rs.getString("APE_EMP");
                dtemp[3]=rs.getString("DIREC_EMP");
                dtemp[4]=rs.getString("COD_DIST");
                dtemp[5]=rs.getString("DNI_EMP");
                dtemp[6]=rs.getString("FEC_NAC_EMP");
                dtemp[7]=rs.getString("FEC_CON_EMP");
                dtemp[8]=rs.getString("USUARIO");
                dtemp[9]=rs.getString("PASS");
                dtemp[10]=rs.getString("ESTADO_CIVIL_EMP");
                dtemp[11]=rs.getString("SEXO_EMP");
            }
            objcone.cerrarconexion(rs, prts);
            return dtemp;
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
            
        }
    }
    public boolean ingredata(Dempleados dts,String cargarfoto){
        objcone.conexion();
        FileInputStream fotico;
        try{
            prts=objcone.con.prepareStatement("INSERT INTO EMPLEADOS(COD_EMP,NOM_EMP,APE_EMP,DIREC_EMP,COD_DIST,DNI_EMP,FEC_NAC_EMP,FEC_CON_EMP,USUARIO,PASS,ESTADO_CIVIL_EMP,SEXO_EMP,FOTO_EMP)"+
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prts.setString(1, dts.getCodemp());
            prts.setString(2, dts.getNombre());
            prts.setString(3, dts.getApellido());
            prts.setString(4, dts.getDirecemp());
            prts.setInt(5, dts.getCoddist());
            prts.setString(6, dts.getDni());
            prts.setDate(7, Date.valueOf(dts.getFenac()));
            prts.setDate(8, Date.valueOf(dts.getFecon()));
            prts.setString(9, dts.getUsuario());
            prts.setString(10, dts.getPass());
            prts.setString(11, dts.getEstado());
            prts.setString(12, dts.getSexo());
            if(cargarfoto==null){
                prts.setBinaryStream(13, null);
            }
            else{
                fotico=new FileInputStream(cargarfoto);
                prts.setBinaryStream(13, fotico);
            }
            int n=prts.executeUpdate();
            if(n!=0){
                objcone.cerrarconexion(rs, prts);
                return true;
            }
            else{
                objcone.cerrarconexion(rs, prts);
                return false;
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR " + e );
            objcone.cerrarconexion(rs, prts);
            return false;
        }
    }
    public boolean actualizardatos(Dempleados dts,String cargarfoto){
        objcone.conexion();
        FileInputStream fotoso;
        try{
            prts=objcone.con.prepareStatement("UPDATE EMPLEADOS SET NOM_EMP=?,APE_EMP=?,DIREC_EMP=?,COD_DIST=?,DNI_EMP=?,FEC_NAC_EMP=?,FEC_CON_EMP=?,USUARIO=?,PASS=?,ESTADO_CIVIL_EMP=?,SEXO_EMP=?,FOTO_EMP=?"+
                    " WHERE COD_EMP=?");
            prts.setString(1, dts.getNombre());
            prts.setString(2, dts.getApellido());
            prts.setString(3, dts.getDirecemp());
            prts.setInt(4, dts.getCoddist());
            prts.setString(5, dts.getDni());
            prts.setDate(6,Date.valueOf(dts.getFenac()));
            prts.setDate(7, Date.valueOf(dts.getFecon()));
            prts.setString(8, dts.getUsuario());
            prts.setString(9, dts.getPass());
            prts.setString(10, dts.getEstado());
            prts.setString(11, dts.getSexo());
            if(cargarfoto==null){
                prts.setBinaryStream(12, null);
            }
            else{
                 fotoso=new FileInputStream(cargarfoto);
                 prts.setBinaryStream(12, fotoso);
            }
           
            prts.setString(13, dts.getCodemp());
            int n=prts.executeUpdate();
            if(n!=0){
                objcone.cerrarconexion(rs, prts);
                return true;
            }
            else{
                objcone.cerrarconexion(rs, prts);
                return false;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return false;
        }
    }
    public String[] datosempleado(String nomemp){
        objcone.conexion();
        String []datos=new String[12];
        
        try{
            prts=objcone.con.prepareStatement("SELECT * FROM EMPLEADOS WHERE USUARIO=?");
            prts.setString(1, nomemp);
            rs=prts.executeQuery();
            while(rs.next()){
                datos[0]=rs.getString("COD_EMP");
                datos[1]=rs.getString("NOM_EMP");
                datos[2]=rs.getString("APE_EMP");
                datos[3]=rs.getString("DIREC_EMP");
                datos[4]=rs.getString("COD_DIST");
                datos[5]=rs.getString("DNI_EMP");
                datos[6]=rs.getString("FEC_NAC_EMP");
                datos[7]=rs.getString("FEC_CON_EMP");
                datos[8]=rs.getString("USUARIO");
                datos[9]=rs.getString("PASS");
                datos[10]=rs.getString("ESTADO_CIVIL_EMP");
                datos[11]=rs.getString("SEXO_EMP");
            }
            objcone.cerrarconexion(rs, prts);
            return datos;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    public String codigoempuser(String user){
        objcone.conexion();
        String cod_emp;
        try{
            prts=objcone.con.prepareStatement("SELECT COD_EMP FROM EMPLEADOS WHERE USUARIO=?");
            prts.setString(1, user);
            rs=prts.executeQuery();
            rs.next();
            cod_emp=rs.getString("COD_EMP");
            objcone.cerrarconexion(rs, prts);
            return cod_emp;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            objcone.cerrarconexion(rs, prts);
            return null;
        }
    }
    
}
