/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import java.io.FileInputStream;

/**
 *
 * @author MXCATTV12
 */
public class Dempleados extends dpersona {
    private String codemp;
    private String direcemp;
    private String fenac;
    private String fecon;
    private String usuario;
    private String pass;
    private String estado;
    public Dempleados(){
    }
    public Dempleados(String codemp,String direcemp,String fenac,String fecon,String usuario,String pass,String estado){
        this.codemp=codemp;
        this.direcemp=direcemp;
        this.fenac=fenac;
        this.fecon=fecon;
        this.usuario=usuario;
        this.pass=pass;
        this.estado=estado;
    }
    public String getCodemp(){
        return codemp;
    }
    public void setCodemp(String codemp){
        this.codemp=codemp;
    }
    public String getDirecemp(){
        return direcemp;
    }
    public void setDirecemp(String direcemp){
        this.direcemp=direcemp;
    }
    public String getFenac(){
        return fenac;
    }
    public void setFenac(String fenac){
        this.fenac=fenac;
    }
    public String getFecon(){
        return fecon;
    }
    public void setFecon(String fecon){
        this.fecon=fecon;
    }
    public String getUsuario(){
        return usuario;
    }
    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado=estado;
    }
}
