/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * 
 */
public class AddCar {
   static AddCars addCars = new AddCars();
   static exitHandler exit = new exitHandler();
   static AddCarsHandler addcar = new AddCarsHandler();
   static AvailableCars carsmodel = new AvailableCars();
   
   static class AddCarsHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String make = addCars.getMake().getSelectedItem().toString();
                String model = addCars.getModel().getText();
                String year = addCars.getYear().getText();
                String category = addCars.getCategory().getSelectedItem().toString();
                int price =(int) addCars.getPrice().getValue();
                String resource_path = addCars.getResourcePath().getText();
                
                if(model.isEmpty()){
                    JOptionPane.showMessageDialog(addCars,"Enter Car Model");
                    return;
                }
                if(year.isEmpty()){
                    JOptionPane.showMessageDialog(addCars,"Enter Year");
                    return;
                }
                if(resource_path.isEmpty()){
                    JOptionPane.showMessageDialog(addCars,"Enter Resource Path");
                    return;
                }
                if(price<=130){
                    JOptionPane.showMessageDialog(addCars,"Price Per Hour Must be Greater than 130");
                }
                if(carsmodel.addCar(make, model,year,category, price, resource_path)){
                    JOptionPane.showMessageDialog(addCars, "Car Added Successfully");
                    addCars.getModel().setText("");
                    addCars.getYear().setText("");
                    addCars.getPrice().setValue(0);
                    addCars.getResourcePath().setText("");
                    
                    
                }else{
                    JOptionPane.showMessageDialog(addCars," Error Adding Car");
                    addCars.getYear().setText("");

                    
                }
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
       
   }
   
   static class exitHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addCars.dispose();            
        }       
   }
    
    
    public static JFrame showAddCarsPage(){
        addCars.setLocationRelativeTo(null);
        addCars.setResizable(false);
        addCars.AddCar().addActionListener(addcar);
        addCars.exit().addActionListener(exit);
        addCars.setVisible(true);
        
        return addCars;
    }
    
}
