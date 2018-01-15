/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.*;
import Model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 *
 */
public class Deposits {
    static DepositsView deposit = new DepositsView();
    static DepositHandler depositHandler = new DepositHandler();
    static User usermodel = new User();
    static class DepositHandler implements  ActionListener{
        private int index =0;
        private int maxIndex = 100;
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                int new_credit = (int)deposit.getDepositAmount().getValue();
                String username = deposit.getUsername().getText();
                if(usermodel.updateAccount(new_credit, username)){
                    deposit.getProgress().setValue(0);
                    deposit.getProgress().setStringPainted(true);
                        if(index<maxIndex){
                            for(int i =0;i<=maxIndex;i++){
                            deposit.getProgress().setValue(index);
                            index++;
                    }
                            JOptionPane.showMessageDialog(deposit, "Update Success");
                }
                }else{
                    deposit.getProgress().setValue(0);
                    deposit.getProgress().setStringPainted(true);
                        if(index<maxIndex){
                            for(int i =0;i<=50;i++){
                            deposit.getProgress().setValue(index);
                            index++;
                    }
                            JOptionPane.showMessageDialog(deposit,"Unable to Update Account");
                    
                }
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
        }
        
        
    }
    
    public JFrame showPage(){
        deposit.setResizable(false);
        deposit.setLocationRelativeTo(null);
        deposit.setVisible(true);
        deposit.getDeposit().addActionListener(depositHandler);
        return deposit;
    }
}
