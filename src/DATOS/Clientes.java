
package DATOS;

public class Clientes extends dpersona{
    private String codclie;
    private String fonoclie;
    private String rucclie;
    private String direcli;
    
    public Clientes(){
    }
    public Clientes(String codclie,String fonoclie,String rucclie,String direcli){
        this.codclie=codclie;
        this.fonoclie=fonoclie;
        this.rucclie=rucclie;
        this.direcli=direcli;
    }
    
    public String getCodclie(){
        return codclie;
    }
    public void setCodclie(String codclie){
        this.codclie=codclie;
    }
    public String getFonoclie(){
        return fonoclie;
    }
    public void setFonoclie(String fonoclie){
        this.fonoclie=fonoclie;
    }
    public String getRucclie(){
        return rucclie;
    }
    public void setRucclie(String rucclie){
        this.rucclie=rucclie;
    }
    public String getDirecli(){
        return direcli;
    }
    public void setDirecli(String direclie){
        this.direcli=direclie;
    }
    
    
}
