/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esperant
 */
public class LoginModel extends Base {
    
    public ArrayList<String>getAdmin(String user){
        ArrayList credentials  = new ArrayList();
       
        String sql;
        try{
            getConnection();
            sql="SELECT * FROM ADMINS WHERE Username=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,user);
            rst=pst.executeQuery();
            if(rst.next()){
                credentials.add(rst.getString("Username"));
                credentials.add(rst.getString("Password"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        return credentials;
    }
    public String getUsername(String table,String user){
        String use="";
        String sql;
        try{
            getConnection();
            switch(table){
                case "admin":
                    sql="SELECT * FROM ADMINS WHERE Username=?";
                    pst=conn.prepareStatement(sql);
                    pst.setString(1,user);
                    rst=pst.executeQuery();
                    if(rst.next()){
                        use=rst.getString("Username");
                    }
                    break;
                    
                case "customer":
                    
                    sql="SELECT * FROM USER WHERE Username=?";
                    pst=conn.prepareStatement(sql);
                    pst.setString(1,user);
                    rst =pst.executeQuery();
                    if(rst.next()){
                        use=rst.getString("Username");
                    }
                    break;
                    
                default:
                    System.out.println("Unsupported Operation Exception");
                    break;
                                       
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return use;
    }
    public String getPassword(String table,String pass){
        String pas="";
        String sql;
        try{
            getConnection();
           switch(table){
                case "admin":
                    sql="SELECT * FROM ADMINS WHERE Password=?";
                    pst=conn.prepareStatement(sql);
                    pst.setString(1,pass);
                    rst=pst.executeQuery();
                    if(rst.next()){
                        pas=rst.getString("Password");
                    }
                    break;
                    
                case "customer":
                    
                    sql="SELECT * FROM USER WHERE Password=?";
                    pst=conn.prepareStatement(sql);
                    pst.setString(1,pass);
                    rst =pst.executeQuery();
                    if(rst.next()){
                        pas=rst.getString("Password");
                    }
                    break;
                    
                default:
                    System.out.println("Unsupported Operation Exception");
                    break;                                       
            }            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pas;
    }
    
}
