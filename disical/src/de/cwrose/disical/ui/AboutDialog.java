/*
 * AboutDialog.java
 *
 * Created on 22. Januar 2002, 20:34
 */

package de.cwrose.disical.ui;

/**
 *
 * @author  conny
 */
public class AboutDialog extends javax.swing.JDialog {

    /** Creates new form AboutDialog */
    public AboutDialog (java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        
        getContentPane().setLayout(new java.awt.GridLayout(4, 0));
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
        jLabel1.setText("DiSiCal");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1);
        
        jLabel2.setText("Distibruted Simple Calendar");
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2);
        
        jLabel3.setText("(C) 2001/2002 Carsten Rose, Cornelius Keller, Fabian Mueller, Stefan Plantikow");
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3);
        
        jLabel4.setText("Licence: See doc/LICENCE");
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel4);
        
        pack();
    }//GEN-END:initComponents

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        new AboutDialog (new javax.swing.JFrame (), true).show ();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

}
