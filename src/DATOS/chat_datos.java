/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import java.io.Serializable;

/**
 *
 * @author MXCATTV12
 */
public class chat_datos implements Serializable{
    public String nick,msg,ip;
    
    public chat_datos(){
        
    }
    public chat_datos(String nick,String msg){
        this.msg=msg;
        this.nick=nick;
    }
    public String getNick(){
        return nick;
    }
    
    public void setNick(String nick){
        this.nick=nick;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getIp(){
        return ip;
    }
    public void setIp(String ip){
        this.ip=ip;
    }
}
