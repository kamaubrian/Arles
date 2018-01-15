/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import View.*;
import java.util.List;
import javax.swing.JOptionPane;
import Model.*;

/**
 *
 * @ 
 */
public class NewUserController {
        static newAccountHandler accounts = new newAccountHandler();
        static exitHandler exit = new exitHandler();
        static NewUserV newUser = new NewUserV();
        static LoginModel loginmodel = new LoginModel();
        static User usermodel = new User();
        static SetupAccount setAccount = new SetupAccount();
        static FinanceHandler finance = new FinanceHandler();
        public static List<Object> details;
        
        static class FinanceHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                String username = setAccount.getUsername().getText();
                int acc_number= (int)usermodel.getIDDetails(username).get(0);
                int initial_credit = (int)setAccount.getInitialAmount().getValue();
                String subscription = setAccount.getSubscription().getSelectedItem().toString();
                if(usermodel.addAccount(acc_number, username, initial_credit, subscription)){
                    JOptionPane.showMessageDialog(setAccount, "Account Saved Success");
                    setAccount.dispose();
                    newUser.dispose();
                    
                    
                }else{
                    JOptionPane.showMessageDialog(setAccount, "Failed to Activate Account");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
        }
            
        }
    
    
    
       static class newAccountHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            String passport = newUser.getPassport().getText();
            String firstname = newUser.getFirstName().getText();
            String lastname = newUser.getLastName().getText();
            String username = newUser.getUsername().getText();
            String phone = newUser.getPhone().getText();
            String password = newUser.getPassword().getText();
            String c_Password=newUser.getPassword2().getText();
            if(passport.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Enter Passport Number");
                return;
            }
            if(firstname.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Enter First Name");
                return;
            }
            if(lastname.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Enter Last Name");
                return;
            }
            
            if(username.isEmpty()){
                JOptionPane.showMessageDialog(newUser, "Enter Username");
                return;
            }
            if(phone.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Enter Phone Number");
                return;
            }
            if(password.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Enter Password");
                return;
            }if(c_Password.isEmpty()){
                JOptionPane.showMessageDialog(newUser,"Confirm Password");
                return;
            }
            if(!password.equals(c_Password)){
                JOptionPane.showMessageDialog(newUser,"Passwords Do Not Match");
            }
            else{
               String table ="customer";
               if(loginmodel.getUsername(table,username).toLowerCase().isEmpty()){
                   usermodel.addUser(passport,firstname,lastname,username,phone,password);
                   
                   JOptionPane.showMessageDialog(newUser,"User Added Successfully");
                  // clear();
                   //newUser.dispose();
                   showAccountPage();
                   
               }else{
                   JOptionPane.showMessageDialog(newUser, "Username Already Exists");
                   clear();
               }
            }           
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
        
    }
       static class exitHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            try{
                newUser.dispose();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
        }
           
           
           
       }
       
       public static JFrame showPage(){
           newUser.exit().addActionListener(exit);
           newUser.signUp().addActionListener(accounts);
           newUser.setVisible(true);
           return newUser;
       }
       public static void clear(){
           newUser.getFirstName().setText("");
           newUser.getUsername().setText("");
           newUser.getLastName().setText("");
           newUser.getPassport().setText("");
           newUser.getPassword2().setText("");
           newUser.getPassword().setText("");
           newUser.getPhone().setText("");
       }
       public static JFrame showAccountPage(){
           try{
           setAccount.setLocationRelativeTo(null);
           setAccount.activateAccount().addActionListener(finance);
           setAccount.setVisible(true);
           setAccount.getUsername().setText(newUser.getUsername().getText());
           setAccount.getAccountNumber().setText(usermodel.getIDDetails(newUser.getUsername().getText()).get(0).toString());
           }catch(Exception ex){
               ex.printStackTrace();
           }
           return setAccount;
       }
}
