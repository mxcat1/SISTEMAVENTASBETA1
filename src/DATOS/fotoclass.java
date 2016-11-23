
package DATOS;

import DATOS.conexion;
import Interfaces.ManteEmpleado;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class fotoclass {

    conexion con;
    private Image data;
    private PreparedStatement pstm;
    private ResultSet res;
    
    public fotoclass() {
        con = new conexion();
    }
    //metodo que dada una cadena de bytes la convierte en una imagen con extension jpeg
    private Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }
   
    //METODO PARA RECUPERAR LA IMAGEN DE LA BASE DE DATOS
    public Image recuperarfotosme(Dempleados dts) {
        con.conexion();
        try {
            pstm = con.con.prepareStatement("SELECT FOTO_EMP FROM EMPLEADOS where COD_EMP= ? ");
            pstm.setString(1, dts.getCodemp());
            res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                // se lee la cadena de bytes de la base de datos
                byte[] b = res.getBytes("FOTO_EMP");
                //esta cadena de bytes sera convertida en una imagen
                data = ConvertirImagen(b);                   
                i++;
            }
            con.cerrarconexion(res, pstm);
            return data;
                
        } catch (IOException | SQLException ex) {
            Logger.getLogger(DATOS.fotoclass.class.getName()).log(Level.SEVERE, null, ex);
            con.cerrarconexion(res, pstm);
            return null;
            
            
        }
        
        

    }
    public Image recuperarfotoslo(Dempleados dts) {
        con.conexion();
        try {
            PreparedStatement pstm = con.con.prepareStatement("SELECT FOTO_EMP FROM EMPLEADOS where USUARIO=?");
            pstm.setString(1, dts.getUsuario());
            ResultSet res = pstm.executeQuery();
            int i = 0;
                while (res.next()) {
                    // se lee la cadena de bytes de la base de datos
                    byte[] b = res.getBytes("FOTO_EMP");
                    //esta cadena de bytes sera convertida en una imagen
                    data = ConvertirImagen(b);                   
                    i++;
                }
            res.close();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(DATOS.fotoclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarconexion(res, pstm);
        return data;

    }
    public boolean fotoexiste(Dempleados dts){
        con.conexion();
        try{
            pstm=con.con.prepareStatement("SELECT FOTO_EMP FROM EMPLEADOS WHERE COD_EMP=?");
            pstm.setString(1, dts.getCodemp());
            res=pstm.executeQuery();
            res.next();
            if(res.getBinaryStream(1)==null){
                con.cerrarconexion(res, pstm);
                return false;
            }
            else{
                con.cerrarconexion(res, pstm);
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e);
            con.cerrarconexion(res, pstm);
            return false;
        }
    }
    public boolean fotoexistelo(Dempleados dts){
        con.conexion();
        try{
            pstm=con.con.prepareStatement("SELECT FOTO_EMP FROM EMPLEADOS WHERE USUARIO=?");
            pstm.setString(1, dts.getUsuario());
            res=pstm.executeQuery();
            res.next();
            if(res.getBinaryStream(1)==null){
                con.cerrarconexion(res, pstm);
                return false;
            }
            else{
                con.cerrarconexion(res, pstm);
                return true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e);
            con.cerrarconexion(res, pstm);
            return false;
        }
    }
}