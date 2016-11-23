/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Mxcatv
 */
public class conexion {
/*    public String conecionurl="jdbc:sqlserver://localhost:1433;" +
            "databaseName=BANANITOS;user=sa; password=mxcat;";*/
/*    public String conecionurl="jdbc:sqlserver://sql96.sql.dinaserver.com:1433;" +
            "databaseName=BANANITOS;user=mxcat; password=159753;";*/
/*    public String conecionurl="jdbc:sqlserver://sql5007.smarterasp.net:1433;" +
            "databaseName=DB_A12AFD_BANANITOS;user=DB_A12AFD_BANANITOS_admin; password=mxcat159753;";*/
    public String conecionurl="jdbc:sqlserver://localhost:1433;" +
            "databaseName=BANANITOS;user=sa; password=mxcat;";
    public Connection con;
    
    public void conexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con=DriverManager.getConnection(conecionurl);
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR"+e);
        }
    }
    public void cerrarconexion(ResultSet rs,Statement st){
        try{
            if(con!=null ){
                
                rs.close();
                st.close();
                con.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
