/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

/**
 *
 * @author MXCATTV12
 */
public class dproducto {
    private String codpro;
    private String nompro;
    private double preupro;
    private int stock;
    private String estado;
    private String codcategoria;
    
    public dproducto(){
    }
    public dproducto(String codpro,String nompro,double preupro,int stock,String estado,String codcategoria){
        this.codpro=codpro;
        this.nompro=nompro;
        this.preupro=preupro;
        this.stock=stock;
        this.estado=estado;
        this.codcategoria=codcategoria;
        /*incompleto falat*/
    }
    public String getCodpro(){
        return codpro;
    }
    public void setCodpro(String codpro){
        this.codpro=codpro;
    }
    public String getNompro(){
        return nompro;
    }
    public void setNompro(String nompro){
        this.nompro=nompro;
    }
    public double getPreupro(){
        return preupro;
    }
    public void setPreupro(double preupro){
        this.preupro=preupro;
    }
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado=estado;
    }
    public String getCodcategoria(){
        return codcategoria;
    }
    public void setCodcategoria(String codcategoria){
        this.codcategoria=codcategoria;
    }
}