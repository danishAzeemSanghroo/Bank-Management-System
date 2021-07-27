/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.AccountNoBean;
import BeanClasses.AccountTypeBean;
import BeanClasses.CustomerBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;

/**
 *
 * @author Danish
 */
public class AccountNoFrame extends javax.swing.JFrame {

    /**
     * Creates new form AccountNoFrame
     */
    public AccountNoFrame() {
        initComponents();
        getCustomer();
        getAccountType();
    }
private void getCustomer(){
	try{
		java.util.Vector v=DatabaseManager.getCustomer();		
                customerComboBox.removeAllItems();
                for(int i=0; i<v.size(); i++){
                  CustomerBean bean=(CustomerBean)v.elementAt(i);
                    customerComboBox.addItem(bean);
                }
	}catch(Exception e){
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end Method  
private void getAccountType(){
	try{
		java.util.Vector v=DatabaseManager.getAccountType();		
                accountTypeComboBox.removeAllItems();
                     for(int i=0; i<v.size(); i++){
                        AccountTypeBean bean=(AccountTypeBean)v.elementAt(i);
                        accountTypeComboBox.addItem(bean);
                     }
	}catch(Exception e){
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end Method
private void getAccountNo(){
        AccountTypeBean accTypeBean=(AccountTypeBean)accountTypeComboBox.getSelectedItem();
        if(accTypeBean==null)return;
        CustomerBean customerBean=(CustomerBean)customerComboBox.getSelectedItem();
        if(customerBean==null)return;
	try{
		java.util.Vector v=DatabaseManager.getAccountNo(accTypeBean.getAccountTypeId(),customerBean.getCustomerId());		
                accountsList.setListData(v);
	}catch(Exception e){
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end Method 
    private void addAccountNo(){
        AccountTypeBean accTypeBean=(AccountTypeBean)accountTypeComboBox.getSelectedItem();
        if(accTypeBean==null)return;
        CustomerBean customerBean=(CustomerBean)customerComboBox.getSelectedItem();
        if(customerBean==null)return;
        
    String pinCode=pinCodeTextField.getText();
    String dateOfCreate=Decoder.getDateFormat(dateOfCreateDateChooser.getDate());
    String remarks=remarksTextArea.getText();

    try{
               	int rows=DatabaseManager.addAccountNo(accTypeBean.getAccountTypeId(),customerBean.getCustomerId(),pinCode,dateOfCreate,remarks);
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getAccountNo();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end 
private void deleteAccountNo(){
    int rows = 0;
    
    Object obj[]=(Object[])accountsList.getSelectedValues();
    for(int i=0; i<obj.length;i++){ 
        AccountNoBean bean = (AccountNoBean)obj[i];
        if(bean==null)return;
        try{
           rows += DatabaseManager.deleteAccountNo(bean.getAccountNoId());
            }catch(Exception e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
            }
    }//end loop
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+"Record Removed.");
           getAccountNo();
           clear();
           }


}

private void updateAccountNo(){
        AccountTypeBean accTypeBean=(AccountTypeBean)accountTypeComboBox.getSelectedItem();
        if(accTypeBean==null)return;
        CustomerBean customerBean=(CustomerBean)customerComboBox.getSelectedItem();
        if(customerBean==null)return;
        
    String pinCode=pinCodeTextField.getText();
    String dateOfCreate=Decoder.getDateFormat(dateOfCreateDateChooser.getDate());
    String remarks=remarksTextArea.getText();
    int rows = 0;
    
    Object obj[]=(Object[])accountsList.getSelectedValues();
    for(int i=0; i<obj.length;i++){ 
        AccountNoBean bean = (AccountNoBean)obj[i];
        if(bean==null)return;
            try{
                rows += DatabaseManager.updateAccountNo(accTypeBean.getAccountTypeId(),customerBean.getCustomerId(),bean.getAccountNoId(),pinCode,dateOfCreate,remarks);
                }catch(Exception e){
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        } 
    }//end loop
	if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getAccountNo();
	    clear();
        	}
 
}

private void clear(){
    accountNoIdTextField.setText("");
    pinCodeTextField.setText("");
    dateOfCreateDateChooser.setDate(null);
    remarksTextArea.setText("");
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountNoLabel = new javax.swing.JLabel();
        accountNoIdLabel = new javax.swing.JLabel();
        customerLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        accountNoIdTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        accountsList = new javax.swing.JList();
        accountsLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        accountTypeLabel = new javax.swing.JLabel();
        dateOfCreateLabel = new javax.swing.JLabel();
        pinCodeTextField = new javax.swing.JTextField();
        pinCodeLabel = new javax.swing.JLabel();
        dateOfCreateDateChooser = new com.toedter.calendar.JDateChooser();
        accountTypeComboBox = new javax.swing.JComboBox();
        customerComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(2000, 1000));
        setPreferredSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        accountNoLabel.setFont(new java.awt.Font("Stencil Std", 1, 60)); // NOI18N
        accountNoLabel.setText("ACCOUNT NO");
        getContentPane().add(accountNoLabel);
        accountNoLabel.setBounds(600, 30, 410, 60);

        accountNoIdLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountNoIdLabel.setText("ACCOUNT NO ID");
        getContentPane().add(accountNoIdLabel);
        accountNoIdLabel.setBounds(110, 360, 170, 25);

        customerLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        customerLabel.setText("CUSTOMER");
        getContentPane().add(customerLabel);
        customerLabel.setBounds(160, 180, 120, 25);

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(170, 550, 101, 30);

        accountNoIdTextField.setEditable(false);
        accountNoIdTextField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(accountNoIdTextField);
        accountNoIdTextField.setBounds(320, 350, 100, 40);

        remarksTextArea.setColumns(20);
        remarksTextArea.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(320, 540, 590, 240);

        accountsList.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        accountsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                accountsListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(accountsList);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(930, 160, 380, 620);

        accountsLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountsLabel.setText("ACCOUNTS");
        getContentPane().add(accountsLabel);
        accountsLabel.setBounds(930, 130, 130, 25);

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(930, 820, 380, 50);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(320, 820, 120, 50);

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(470, 820, 120, 50);

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton);
        clearButton.setBounds(620, 820, 120, 50);

        backButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(780, 820, 120, 50);

        accountTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountTypeLabel.setText("ACCOUNT TYPE");
        getContentPane().add(accountTypeLabel);
        accountTypeLabel.setBounds(120, 270, 160, 25);

        dateOfCreateLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        dateOfCreateLabel.setText("DATE OF CREATE");
        getContentPane().add(dateOfCreateLabel);
        dateOfCreateLabel.setBounds(110, 480, 180, 25);

        pinCodeTextField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(pinCodeTextField);
        pinCodeTextField.setBounds(320, 410, 160, 40);

        pinCodeLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pinCodeLabel.setText("PIN CODE");
        getContentPane().add(pinCodeLabel);
        pinCodeLabel.setBounds(180, 420, 100, 25);
        getContentPane().add(dateOfCreateDateChooser);
        dateOfCreateDateChooser.setBounds(320, 470, 220, 40);

        accountTypeComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        accountTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountTypeComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(accountTypeComboBox);
        accountTypeComboBox.setBounds(320, 250, 590, 50);

        customerComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        customerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(customerComboBox);
        customerComboBox.setBounds(320, 170, 590, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accountsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_accountsListValueChanged
        AccountNoBean bean=(AccountNoBean)accountsList.getSelectedValue();
        if(bean==null)return;

        accountNoIdTextField.setText(""+bean.getCustomerId());
        pinCodeTextField.setText(bean.getPinCode());
        dateOfCreateDateChooser.setDate(bean.getDateOfCreate());
        remarksTextArea.setText(bean.getRemarks());
    }//GEN-LAST:event_accountsListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteAccountNo();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addAccountNo();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updateAccountNo();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void customerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerComboBoxActionPerformed
        getAccountNo();
    }//GEN-LAST:event_customerComboBoxActionPerformed

    private void accountTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountTypeComboBoxActionPerformed
        getAccountNo();
    }//GEN-LAST:event_accountTypeComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountNoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountNoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountNoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountNoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountNoFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNoIdLabel;
    private javax.swing.JTextField accountNoIdTextField;
    private javax.swing.JLabel accountNoLabel;
    private javax.swing.JComboBox accountTypeComboBox;
    private javax.swing.JLabel accountTypeLabel;
    private javax.swing.JLabel accountsLabel;
    private javax.swing.JList accountsList;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox customerComboBox;
    private javax.swing.JLabel customerLabel;
    private com.toedter.calendar.JDateChooser dateOfCreateDateChooser;
    private javax.swing.JLabel dateOfCreateLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pinCodeLabel;
    private javax.swing.JTextField pinCodeTextField;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
