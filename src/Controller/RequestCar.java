/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import javax.swing.JFrame;
import View.*;
import java.awt.event.ActionEvent;
import Model.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
/**
 *
 * 
 */
public class RequestCar {
    static RequestView request = new RequestView();
    static Booking bookingview = new Booking();
    static searchHandler searchcar = new searchHandler();
    static DepositHandler deposit = new DepositHandler();
    static AvailableCars carsmodel = new AvailableCars();
    static BookHandler bookhandler = new BookHandler();
    static selectHandler select = new selectHandler();
    static User usermodel = new User();
    static Login login = new Login();
    static Deposits depo = new Deposits();
    static Book bookingmodel = new Book();
    public static List<Object> subscription_details;
    static GeneratePriceHandler generateprice  = new GeneratePriceHandler();
    static SubscriptionHandler subscription = new SubscriptionHandler();
    
    
    static class SubscriptionHandler implements ActionListener{

        @Override
        
        public void actionPerformed(ActionEvent e) {
            String username;
            try{
                username = request.getUsername().getText();
                if(!bookingmodel.getBookingDetails(username).isEmpty()){
                  // JOptionPane.showMessageDialog(request,"Details Uploaded in Next Release");
                   ReturnCarController.showReturnPage();
                }else{
                    JOptionPane.showMessageDialog(request,"You have no Subscriptions yet");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    
    static class GeneratePriceHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
            int number_of_hours =(int) bookingview.getNumberOfHours().getValue();
            int price_per_hour = (int) bookingview.getPriceHour().getValue();
            
            int total_price = (number_of_hours * price_per_hour)-1000;
            bookingview.getTotalPrice().setValue(total_price);
            bookingview.completeBooking().setEnabled(true);
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
                        
        }
        
    }
     
    
    static class BookHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          int credit_balance;
          int price;
            try{
                credit_balance = (int) usermodel.getCreditBalance(bookingview.getUsername().getText()).get(0);
                price = (int) bookingview.getTotalPrice().getValue();
                if(credit_balance < price){
                    JOptionPane.showMessageDialog(bookingview,"Insufficient Funds to Complete Transaction");
                }else{
                    createDocumentFile("receipt.docx");
                    String username =bookingview.getUsername().getText();
                    String make = bookingview.getMake().getText();
                    String model =bookingview.getModel().getText();
                    int hourly_price = (int)bookingview.getPriceHour().getValue();
                    int hours_booked = (int) bookingview.getNumberOfHours().getValue();
                    int total_price =(int) bookingview.getTotalPrice().getValue();
                    bookingmodel.makeBooking(username, make, model, hourly_price, hours_booked, total_price);
                    JOptionPane.showMessageDialog(bookingview,"Transaction Completed Successfully");
                    bookingview.dispose();
                    
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
          
            
          
        }
          public static void GeneratePdf(){
                //PdfDocument doc = new PdfDocument();
            }
        
        
        
    }
    static class selectHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            showBookingview();
        }
        
    }
    
    
    static class DepositHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            depo.showPage();
        }
        
        
        
    }
    static class searchHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String model = request.getModel().getText();
                String make = request.getMake().getSelectedItem().toString();
                if(!model.isEmpty()){
                if(carsmodel.findCarExists(model, make)){
                    
                    carsmodel.getCarExists(model, make);
                    request.getTagNo().setText(carsmodel.getCarExists(model, make).get(0));
                    request.getCategory().setText(carsmodel.getCarExists(model, make).get(1));
                    request.getMaker().setText(make);
                    
                    request.getModeler().setText(model);
                    request.getYear().setText(carsmodel.getCarExists(model, make).get(4));
                    request.getMaker().setEditable(false);
                    request.getTagNo().setEditable(false);
                    request.getCategory().setEditable(false);
                    request.getModeler().setEditable(false);
                    request.getYear().setEditable(false);
                    request.getBookingView().setEnabled(true);
                /*    try{
                    Icon icon = new ImageIcon("Resources/availableCars/coupe.png");
                    //request.carPanel().setLayout(new FlowLayout(FlowLayout.CENTER));
                    request.carsIcon().setIcon(icon);
                    request.carPanel().add(request.carsIcon());
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    
                    */
                    
                }else{
                    System.out.println("Car Not Found");
                    JOptionPane.showMessageDialog(request, "Car Not Found");
                }
                }else{
                    JOptionPane.showMessageDialog(request,"Enter Car Model");
                    return;
                }
                
                
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
                                    
            
        }        
    }
    
    
    
    public static JFrame showPage() {
        try{
        request.getCustomername().setText(Login.details.get(0));
        request.getPassport().setText(Login.details.get(1));
        request.getUsername().setText(Login.details.get(2));
        subscription_details=usermodel.getSubscriptionDetails(request.getUsername().getText());
        request.getAccount().setText(subscription_details.get(0).toString());
        request.getCreditBalance().setText(subscription_details.get(1).toString());
        request.getSubscription().setText(subscription_details.get(2).toString());
        request.setVisible(true);
        request.getAccount().setEnabled(false);
        request.getSubscription().setEnabled(false);
        request.getCreditBalance().setEnabled(false);
        request.searchCar().addActionListener(searchcar);
        request.getDeposit().addActionListener(deposit);
        request.getBookingView().addActionListener(select);
        request.getSubscriptions().addActionListener(subscription);
        }catch(Exception ex){
            ex.printStackTrace();
        }
          return request;
    }
    public static JFrame showBookingview(){
        try{
        bookingview.getUsername().setText(request.getUsername().getText());
        bookingview.getModel().setText(request.getModeler().getText());
        bookingview.getMake().setText(request.getMaker().getText());
        bookingview.getPriceHour().setValue(bookingmodel.getPricePerHour(bookingview.getModel().getText()).get(0));
        bookingview.getGeneratePrice().addActionListener(generateprice);
        bookingview.completeBooking().addActionListener(bookhandler);
        bookingview.setLocationRelativeTo(null);
        bookingview.setResizable(false);
        bookingview.setVisible(true);
      
        }catch(Exception ex){
            ex.printStackTrace();
        }
          return bookingview;
    }
    
    public static String getUsername(){
        return request.getUsername().getText();
    }
    public static void createDocumentFile(String filename){
        try{
            File file = new File(filename);
            FileOutputStream outputstraeam = new FileOutputStream(file.getAbsoluteFile());
            
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph temp_paragraph = document.createParagraph();
            XWPFRun temp_run = temp_paragraph.createRun();
            
            temp_run.setText("Sample Text");
            temp_run.setFontSize(12);
            document.write(outputstraeam);
            outputstraeam.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
