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
public class dventas {
    /*AQUI EMPIEZA LOS DATOS DE LA VENTA
    */
    public String cod_cabe;
    public String cod_cli;
    public String nombre;
    public String fecha;
    public double total;
    public String cod_emp;
    public String cod_pro;
    public int canti;
    public double parcial;
    
    /**
     *
     */
    public dventas(){
        
    }
    public dventas(String codcabe,String cod_cli,String nombre,String fecha,double total,String cod_emp,String cod_pro,int canti,double parcial){
        this.cod_cabe=codcabe;
        this.cod_cli=cod_cli;
        this.cod_emp=cod_emp;
        this.cod_pro=cod_pro;
        this.fecha=fecha;
        this.nombre=nombre;
        this.canti=canti;
        this.parcial=parcial;
        this.total=total;
        
    }

    public String getCod_cabe() {
        return cod_cabe;
    }

    public void setCod_cabe(String cod_cabe) {
        this.cod_cabe = cod_cabe;
    }

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCod_emp() {
        return cod_emp;
    }

    public void setCod_emp(String cod_emp) {
        this.cod_emp = cod_emp;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public int getCanti() {
        return canti;
    }

    public void setCanti(int canti) {
        this.canti = canti;
    }

    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }
    
}
