/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 */
public class Admin extends Base {
    public ArrayList<ArrayList<String>> populateTable() throws SQLException{
        String sql;
        ArrayList<ArrayList<String>> tableitems = new ArrayList();
        try{
            getConnection();
            sql="SELECT * FROM BOOKING";
            pst=conn.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                ArrayList<String> items = new ArrayList();
                items.add(rst.getString("Username"));
                items.add(rst.getString("MAKE"));
                items.add(rst.getString("MODEL"));
                items.add(rst.getString("Created_At"));
                
                tableitems.add(items);
            }                                                            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }        
        return tableitems;
    }
    public boolean disableUser(String passport) throws SQLException{
       String sql;
        try{
            getConnection();
            sql="DELETE FROM USER WHERE ID_PASSPORT=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, passport);
            pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return true;
    }
}
