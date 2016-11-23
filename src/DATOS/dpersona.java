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
public class dpersona {
    private String nombre;
    private String apellido;
    private String dni;
    private String sexo;
    private int coddist;
    public dpersona(){
    }
    public dpersona(int coddist,String nombre,String apellido,String dni,String sexo){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.sexo=sexo;
        this.coddist=coddist;
        
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public void setApellido(String apellido){
        this.apellido=apellido;
    }
    public String getDni(){
        return dni;
    }
    public void setDni(String dni){
        this.dni=dni;
    }
    public String getSexo(){
        return sexo;
    }
    public void setSexo(String sexo){
        this.sexo=sexo;
    }
    public int getCoddist(){
        return coddist;
    }
    public void setCoddist(int coddist){
        this.coddist=coddist;
    }
    
    
    
    
}

