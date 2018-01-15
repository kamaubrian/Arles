/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
import javax.swing.JFrame;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * 
 */
public class ReturnCarController {
    
    static ReturnCar returnview = new ReturnCar();  
    static RequestView requestview = new RequestView();
    static ReturnCarHandler returncar = new ReturnCarHandler();
    static Book bookmodels = new Book();
    private static  List<Object> formdetails = new ArrayList<>();
    static class ReturnCarHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String username =returnview.getUsername().getText();
                String make = returnview.getMake().getText();
                String model = returnview.getModel().getText();
                int hours = (int) returnview.getHours().getValue();
                if(bookmodels.createReturnLog(username, make, model, hours)){
                    if(bookmodels.returnCar(username)){
                        JOptionPane.showMessageDialog(returnview, "Car Returned Success");
                        returnview.dispose();
                    }
                }
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }        
    }
    
    public static JFrame showReturnPage(){
        try{
       formdetails = bookmodels.getBookingDetails(RequestCar.getUsername());
        System.out.println(formdetails);
        returnview.getUsername().setText(formdetails.get(0).toString());
        returnview.getMake().setText(formdetails.get(1).toString());
        returnview.getModel().setText(formdetails.get(2).toString());
        returnview.getHours().setValue(formdetails.get(3));
        returnview.returnCar().addActionListener(returncar);
        returnview.setLocationRelativeTo(null);
        returnview.setResizable(false);
        returnview.setVisible(true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return returnview;
    }
}
