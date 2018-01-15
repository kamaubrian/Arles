/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.*;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;

/**
 *
 * 
 */

public class Login {
        static{
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }
    
    public static ArrayList<String> details;
    static User usermodel = new User(); 
    static LoginView loginview = new LoginView();
    static NewUserV userview = new NewUserV();
    static loginHandler login = new loginHandler();
    static exitHandler exit = new exitHandler();
    static showSignup signup = new showSignup();
    static LoginModel loginmodel = new LoginModel();
    static RequestCar requestview = new RequestCar();
    static RequestView requestv = new RequestView();
    static AdminView adminview = new AdminView();
    static requestHandler request = new requestHandler();
    static SplashScreen splash = new SplashScreen();
    
    static class loginHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String username = loginview.getUsername().getText();
                String password = loginview.getPassword().getText();
                //String pass =loginmodel.getPassword(password);
             //   String usern = loginmodel.getUsername(username);
                if(username.isEmpty()){
                    JOptionPane.showMessageDialog(loginview,"Please Enter Username");
                    return;
                }
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(loginview, "Please Enter Password");
                    return;
                }
                if(loginview.verifyUser().getSelectedItem().toString().toLowerCase().equals("admin")){
                    System.out.println("Logging in as Administrator");
                    String table ="admin";
                    if(loginmodel.getUsername(table,username).equals(username) && loginmodel.getPassword(table,password)
                            .equals(password)){
                        System.out.println("Welcome Admin");
                        AdminController.showPage();
                        
                        
                    }else{
                        System.out.println("Administrator not found, check Credentials");
                        JOptionPane.showMessageDialog(null,"Admin Not Found");
                    }
                    
                    
                }else if(loginview.verifyUser().getSelectedItem().toString().toLowerCase().equals("customer")){
                    String table="customer";
                    if(username.equals(loginmodel.getUsername(table, username)) && password.equals(loginmodel.getPassword(table, password))){
                    System.out.println("Correct Credentials ");
                    details = usermodel.getRequestDetails(username);
                    RequestCar.showPage();
                   // requestv.getCustomername().setText(username);
                    
                    
                }else{
                    System.out.println("Incorrect Password");
                    JOptionPane.showMessageDialog(null,"User Not Found");
                    System.out.println(loginmodel.getUsername("customer",username));
                    System.out.println(loginmodel.getPassword("customer",password));
                    
                }
                    
              
                }
                

            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
        }
        
    }
    static class exitHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }
 
    static class showSignup implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            NewUserController.showPage();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }       
    }
    static class requestHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        showPage();
    }
    
    public static JFrame showPage(){
        /* if(loginview.verifyUser().getItemAt(0).equals("Admin")){
                loginview.getUsername().setEnabled(false);
            }else{
                loginview.getUsername().setEnabled(true);
            }*/
        showSplashScreen();
        loginview.createAccount().addMouseListener(signup);
        loginview.exitSystem().addActionListener(exit);
        loginview.loginSystem().addActionListener(login);
        loginview.setVisible(true);
        return loginview;
       
    }
    public static void showSplashScreen(){
        splash.setResizable(false);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        try{
            for(int i=0;i<=100;i++){
                Thread.sleep(40);
                splash.getProgressBar().setValue(i);
                
            }
            splash.dispose();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
 
}

