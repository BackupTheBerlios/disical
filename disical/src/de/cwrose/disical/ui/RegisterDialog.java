/*
 * RegisterDialog.java
 *
 * Created on 26. Januar 2002, 16:48
 */

package de.cwrose.disical.ui;

/**
 *
 * @author  user1
 */
public class RegisterDialog extends javax.swing.JDialog {
    public String name = "";
    public String login = "";
    public String email = "";
    public String password = "";
    public String confirm = "";
    public boolean cancel = false;
    

    /** Creates new form RegisterDialog */
    public RegisterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nameinput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        logininput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        emailinput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        passwordConfirm = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        setTitle("New Disical User");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jPanel2.setLayout(new java.awt.GridLayout(5, 2));
        
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 80));
        jPanel2.setPreferredSize(new java.awt.Dimension(230, 80));
        jLabel6.setText("Name:");
        jPanel2.add(jLabel6);
        
        nameinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameinputActionPerformed(evt);
            }
        });
        
        jPanel2.add(nameinput);
        
        jLabel7.setText("Login:");
        jPanel2.add(jLabel7);
        
        logininput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logininputActionPerformed(evt);
            }
        });
        
        jPanel2.add(logininput);
        
        jLabel8.setText("Email:");
        jPanel2.add(jLabel8);
        
        emailinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailinputActionPerformed(evt);
            }
        });
        
        jPanel2.add(emailinput);
        
        jLabel10.setText("Password confirm:");
        jPanel2.add(jLabel10);
        
        jPanel2.add(passwordField);
        
        jLabel9.setText("Password:");
        jPanel2.add(jLabel9);
        
        passwordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordConfirmActionPerformed(evt);
            }
        });
        
        jPanel2.add(passwordConfirm);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.ipady = 52;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel2, gridBagConstraints1);
        
        okButton.setText("ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        
        jPanel3.add(okButton);
        
        cancelButton.setText("cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        
        jPanel3.add(cancelButton);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 10;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.SOUTH;
        getContentPane().add(jPanel3, gridBagConstraints1);
        
        message.setForeground(java.awt.Color.red);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        getContentPane().add(message, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // Add your handling code here:
        System.out.println("Hallo");
        this.login = logininput.getText();
        this.password = passwordField.getText();
        this.email = emailinput.getText();
        this.name = nameinput.getText();
        this.password = passwordField.getText();
        cancel = false;
        setVisible(false);
        dispose();
        
        
    }//GEN-LAST:event_okButtonActionPerformed

    private void emailinputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailinputActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_emailinputActionPerformed

    private void nameinputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameinputActionPerformed
        // Add your handling code here:
        name = this.nameinput.getText();
        email = emailinput.getText();
        password = passwordField.getText();
        confirm = passwordConfirm.getText();
        cancel = false;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_nameinputActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // Add your handling code here:
        cancel = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void passwordConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordConfirmActionPerformed
        // Add your handling code here:
   
    }//GEN-LAST:event_passwordConfirmActionPerformed

    private void logininputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logininputActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_logininputActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        cancel = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        new RegisterDialog(new javax.swing.JFrame(), true).show();
    }
    
   public void setMessage(String msg) {
       this.message.setText(msg);
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel jPanel2;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JTextField nameinput;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JTextField logininput;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JTextField emailinput;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JPasswordField passwordField;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPasswordField passwordConfirm;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JButton okButton;
   private javax.swing.JButton cancelButton;
   private javax.swing.JLabel message;
   // End of variables declaration//GEN-END:variables

}
