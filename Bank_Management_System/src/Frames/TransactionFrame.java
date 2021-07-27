/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import BeanClasses.AccountNoBean;
import BeanClasses.AccountTypeBean;
import BeanClasses.CustomerBean;
import BeanClasses.TransactionBean;
import DatabaseManager.DatabaseManager;
import EnDeCoder.Decoder;

/**
 *
 * @author Danish
 */
public class TransactionFrame extends javax.swing.JFrame {

    /**
     * Creates new form TransactionFrame
     */
    public TransactionFrame() {
        initComponents();
        transactionTypeComboBox.addItem("CASH");
        transactionTypeComboBox.addItem("CHECK");
        transactionTypeComboBox.addItem("OTHER");
        
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
                accountComboBox.removeAllItems();
                     for(int i=0; i<v.size(); i++){
                        AccountNoBean bean=(AccountNoBean)v.elementAt(i);
                        accountComboBox.addItem(bean);
                     }
	}catch(Exception e){
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}
}//end Method 
private void getTransaction(){
    AccountNoBean accNoBean=(AccountNoBean)accountComboBox.getSelectedItem();
    if(accNoBean==null)return;
	try{
		java.util.Vector v=DatabaseManager.getTransaction(accNoBean.getAccountNoId());		
                transactionsList.setListData(v);
	}catch(Exception e){
		javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
	}    
}

    private void addTransaction(){
        AccountNoBean accNoBean=(AccountNoBean)accountComboBox.getSelectedItem();
        if(accNoBean==null)return;

        
    String amount=amountTextField.getText();
    String dateOfTransaction=Decoder.getDateFormat(dateOfTransactionDateChooser.getDate());
    String transactionType=(String)transactionTypeComboBox.getSelectedItem();
    String remarks=remarksTextArea.getText();

    try{
               	int rows=DatabaseManager.addTransaction(accNoBean.getAccountNoId(),amount,dateOfTransaction,transactionType,remarks);
	if(rows>=1){
	        	javax.swing.JOptionPane.showMessageDialog(this,rows+" Records Inserted."); 
		clear();
		getTransaction();				
	}
    }catch(Exception e){
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
    }

}   //end 
private void deleteTransaction(){
    int rows = 0;
    
    Object obj[]=(Object[])transactionsList.getSelectedValues();
    for(int i=0; i<obj.length;i++){ 
        TransactionBean bean = (TransactionBean)obj[i];
        if(bean==null)return;
        try{
           rows += DatabaseManager.deleteTransaction(bean.getTransactionId());
            }catch(Exception e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage()); 
            }
    }//end loop
           if(rows>=1){
           javax.swing.JOptionPane.showMessageDialog(this,rows+"Record Removed.");
           getTransaction();
           clear();
           }


}

private void updateTransaction(){
        AccountNoBean accNoBean=(AccountNoBean)accountComboBox.getSelectedItem();
        if(accNoBean==null)return;
        
    String amount=amountTextField.getText();
    String dateOfTransaction=Decoder.getDateFormat(dateOfTransactionDateChooser.getDate());
    String transactionType=(String)transactionTypeComboBox.getSelectedItem();
    String remarks=remarksTextArea.getText();
    int rows = 0;
    
    Object obj[]=(Object[])transactionsList.getSelectedValues();
    for(int i=0; i<obj.length;i++){ 
        TransactionBean bean = (TransactionBean)obj[i];
        if(bean==null)return;
            try{
                rows += DatabaseManager.updateTransaction(bean.getAccountId(),bean.getTransactionId(),amount,dateOfTransaction,transactionType,remarks);
                }catch(Exception e){
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        } 
    }//end loop
	if(rows>=1){
	    javax.swing.JOptionPane.showMessageDialog(this,rows+" Record Modified.");
	    getTransaction();
	    clear();
        	}
 
}

private void clear(){
    transactionIdTextField.setText("");
    amountTextField.setText("");
    dateOfTransactionDateChooser.setDate(null);
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

        transactionLabel = new javax.swing.JLabel();
        transactionIdLabel = new javax.swing.JLabel();
        customerLabel = new javax.swing.JLabel();
        remarksLabel = new javax.swing.JLabel();
        transactionIdTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        remarksTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        transactionsList = new javax.swing.JList();
        accountsLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        accountTypeLabel = new javax.swing.JLabel();
        dateOfTransactionLabel = new javax.swing.JLabel();
        amountTextField = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        dateOfTransactionDateChooser = new com.toedter.calendar.JDateChooser();
        accountTypeComboBox = new javax.swing.JComboBox();
        customerComboBox = new javax.swing.JComboBox();
        accountLabel = new javax.swing.JLabel();
        accountComboBox = new javax.swing.JComboBox();
        transactionTypeLabel = new javax.swing.JLabel();
        transactionTypeComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(2000, 1000));
        getContentPane().setLayout(null);

        transactionLabel.setFont(new java.awt.Font("Stencil Std", 1, 60)); // NOI18N
        transactionLabel.setText("TRANSACTION");
        getContentPane().add(transactionLabel);
        transactionLabel.setBounds(600, 30, 450, 60);

        transactionIdLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        transactionIdLabel.setText("TRANSACTION ID");
        getContentPane().add(transactionIdLabel);
        transactionIdLabel.setBounds(101, 420, 179, 25);

        customerLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        customerLabel.setText("CUSTOMER");
        getContentPane().add(customerLabel);
        customerLabel.setBounds(160, 180, 120, 25);

        remarksLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        remarksLabel.setText("REMARKS");
        getContentPane().add(remarksLabel);
        remarksLabel.setBounds(170, 590, 101, 30);

        transactionIdTextField.setEditable(false);
        transactionIdTextField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(transactionIdTextField);
        transactionIdTextField.setBounds(320, 410, 100, 40);

        remarksTextArea.setColumns(20);
        remarksTextArea.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        remarksTextArea.setRows(5);
        jScrollPane1.setViewportView(remarksTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(320, 580, 590, 240);

        transactionsList.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        transactionsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                transactionsListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(transactionsList);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(930, 160, 380, 660);

        accountsLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountsLabel.setText("TRANSACTIONS");
        getContentPane().add(accountsLabel);
        accountsLabel.setBounds(930, 130, 170, 25);

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(930, 870, 380, 50);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(320, 870, 120, 50);

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(updateButton);
        updateButton.setBounds(470, 870, 120, 50);

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearButton);
        clearButton.setBounds(620, 870, 120, 50);

        backButton.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton);
        backButton.setBounds(780, 870, 120, 50);

        accountTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountTypeLabel.setText("ACCOUNT TYPE");
        getContentPane().add(accountTypeLabel);
        accountTypeLabel.setBounds(120, 250, 160, 25);

        dateOfTransactionLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        dateOfTransactionLabel.setText("DATE OF TRANSACTION");
        getContentPane().add(dateOfTransactionLabel);
        dateOfTransactionLabel.setBounds(40, 540, 240, 25);

        amountTextField.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        getContentPane().add(amountTextField);
        amountTextField.setBounds(320, 470, 160, 40);

        amountLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        amountLabel.setText("AMOUNT");
        getContentPane().add(amountLabel);
        amountLabel.setBounds(180, 480, 100, 25);
        getContentPane().add(dateOfTransactionDateChooser);
        dateOfTransactionDateChooser.setBounds(320, 530, 160, 40);

        accountTypeComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        accountTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountTypeComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(accountTypeComboBox);
        accountTypeComboBox.setBounds(320, 230, 590, 50);

        customerComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        customerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(customerComboBox);
        customerComboBox.setBounds(320, 170, 590, 50);

        accountLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        accountLabel.setText("ACCOUNT");
        getContentPane().add(accountLabel);
        accountLabel.setBounds(180, 320, 100, 25);

        accountComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        accountComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(accountComboBox);
        accountComboBox.setBounds(320, 300, 590, 50);

        transactionTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        transactionTypeLabel.setText("TRANSACTION TYPE");
        getContentPane().add(transactionTypeLabel);
        transactionTypeLabel.setBounds(500, 540, 210, 25);

        transactionTypeComboBox.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        transactionTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionTypeComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(transactionTypeComboBox);
        transactionTypeComboBox.setBounds(710, 530, 200, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transactionsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_transactionsListValueChanged
        TransactionBean bean=(TransactionBean)transactionsList.getSelectedValue();
        if(bean==null)return;

        transactionIdTextField.setText(""+bean.getCustomerId());
        amountTextField.setText(bean.getAmount());
        
        String transactionType=Decoder.transactionTypeDecode(bean.getTransactionType());
        transactionTypeComboBox.setSelectedItem(transactionType);
        dateOfTransactionDateChooser.setDate(bean.getDateOfTransaction());
        remarksTextArea.setText(bean.getRemarks());
        
        for(int i=0; i<transactionTypeComboBox.getItemCount(); i++){
	String item=(String)transactionTypeComboBox.getItemAt(i);
	if(item.equals(bean.getTransactionType()))
		transactionTypeComboBox.setSelectedItem(item);
    }
        
    }//GEN-LAST:event_transactionsListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
       deleteTransaction();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
      addTransaction();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
      updateTransaction();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
      clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void accountTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountTypeComboBoxActionPerformed
        getAccountNo();
    }//GEN-LAST:event_accountTypeComboBoxActionPerformed

    private void customerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerComboBoxActionPerformed
        getAccountNo();
    }//GEN-LAST:event_customerComboBoxActionPerformed

    private void accountComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountComboBoxActionPerformed
        getTransaction();
    }//GEN-LAST:event_accountComboBoxActionPerformed

    private void transactionTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionTypeComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accountComboBox;
    private javax.swing.JLabel accountLabel;
    private javax.swing.JComboBox accountTypeComboBox;
    private javax.swing.JLabel accountTypeLabel;
    private javax.swing.JLabel accountsLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox customerComboBox;
    private javax.swing.JLabel customerLabel;
    private com.toedter.calendar.JDateChooser dateOfTransactionDateChooser;
    private javax.swing.JLabel dateOfTransactionLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel remarksLabel;
    private javax.swing.JTextArea remarksTextArea;
    private javax.swing.JLabel transactionIdLabel;
    private javax.swing.JTextField transactionIdTextField;
    private javax.swing.JLabel transactionLabel;
    private javax.swing.JComboBox transactionTypeComboBox;
    private javax.swing.JLabel transactionTypeLabel;
    private javax.swing.JList transactionsList;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
