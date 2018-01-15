/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class Book extends Base {
    public boolean makeBooking(String username,String make,String model,int hourly_price,int hours_booked,int total_price ) throws SQLException{
        String sql;
        try{
            getConnection();
            sql="INSERT INTO BOOKING(USERNAME,MAKE,MODEL,PRICE_HOURLY,HOURS_BOOKED,TOTAL_PRICE) VALUES(?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,make);
            pst.setString(3,model);
            pst.setInt(4, hourly_price);
            pst.setInt(5,hours_booked);
            pst.setInt(6,total_price);
            pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }        
        return true;
    
    }
    public List<Object> getPricePerHour(String model) throws SQLException{
        String sql;
        List<Object> pricedetails = new ArrayList();
        try{
            getConnection();
            sql="SELECT * FROM CARS_AVAILABLE WHERE MODEL =?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,model);
            rst=pst.executeQuery();
            if(rst.next()){
                pricedetails.add(rst.getInt("PRICE_PERHOUR"));
            }           
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return pricedetails;
    }
    public List<Object> getBookingDetails(String username)throws SQLException{
        String sql;
        List<Object> booking_details = new ArrayList<>();
        try{
            getConnection();
            sql="SELECT * FROM BOOKING WHERE USERNAME=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, username);
            rst=pst.executeQuery();
            if(rst.next()){
                booking_details.add(rst.getString("USERNAME"));
                booking_details.add(rst.getString("MAKE"));
                booking_details.add(rst.getString("MODEL"));                
                booking_details.add(rst.getInt("HOURS_BOOKED"));
                booking_details.add(rst.getString("TOTAL_PRICE"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }       
        return booking_details;
    }
    public boolean createReturnLog(String username,String make,String model,int hours)throws SQLException{
       String sql;
        try{
            getConnection();
            sql="INSERT INTO RETURN_LOGS(USERNAME,MAKE,MODEL,HOURS_BOOKED) VALUES(?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,make);
            pst.setString(3, model);
            pst.setInt(4,hours);
            pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return true;
    }
    public boolean returnCar(String username)throws SQLException{
        String sql;
        try{
            getConnection();
            sql="DELETE FROM BOOKING WHERE USERNAME =?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }        
        return true;
    }
}
