/*
 * LoginDialog.java
 *
 * Created on 24. Januar 2002, 15:54
 */

package de.cwrose.disical.ui;

/**
 *
 * @author  user1
 */
public class LoginDialog extends javax.swing.JDialog {
   public boolean cancel = false;
   public String login = "";
   public String password = "";

    /** Creates new form LoginDialog */
    public LoginDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        loginTextField = new javax.swing.JTextField();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        message = new javax.swing.JLabel();
        
        setTitle("Disical Login");
        setName("loginDialog");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanel1.setPreferredSize(new java.awt.Dimension(230, 180));
        jPanel1.setMinimumSize(new java.awt.Dimension(230, 180));
        loginTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginTextFieldActionPerformed(evt);
            }
        });
        
        jPanel1.add(loginTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 90, -1));
        
        loginLabel.setText("login:");
        jPanel1.add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 20));
        
        passwordLabel.setText("password:");
        jPanel1.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        
        cancelButton.setText("cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        
        okButton.setText("ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        
        jPanel1.add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));
        
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 90, -1));
        
        message.setForeground(java.awt.Color.red);
        jPanel1.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 10, 180, -1));
        
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        
        pack();
    }//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // Add your handling code here:
        System.out.println("Hallo");
        this.login = loginTextField.getText();
        this.password = passwordField.getText();
        cancel = false;
        setVisible(false);
        dispose();
       
        
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void loginTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginTextFieldActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_loginTextFieldActionPerformed

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
        new LoginDialog(new javax.swing.JFrame(), true).show();
    }
    
    public void setMessage(String msg) {
        this.message.setText(msg);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel message;
    // End of variables declaration//GEN-END:variables

}
