/*
 * SessionFrame.java
 *
 * Created on 20. M�rz 2002, 01:04
 */

package de.cwrose.disical.ui;

import de.cwrose.disical.corba.*;
import de.cwrose.disical.corba.disiorb.*;
import javax.swing.*;

/**
 *
 * @author  conny
 */

public class SessionFrame extends javax.swing.JInternalFrame {

    /** Creates new form SessionFrame */
    public SessionFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel3 = new javax.swing.JPanel();
        jList3 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jList3.setMaximumSize(new java.awt.Dimension(30, 60));
        jList3.setMinimumSize(new java.awt.Dimension(30, 60));
        jList3.setPreferredSize(new java.awt.Dimension(150, 200));
        jPanel3.add(jList3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));
        
        jLabel1.setText("Termine");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        getContentPane().add(jPanel3, gridBagConstraints1);
        
        jButton2.setText("edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        jPanel4.add(jButton2);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        getContentPane().add(jPanel4, gridBagConstraints1);
        
        jButton4.setText("new");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        jPanel5.add(jButton4);
        
        jButton5.setText("delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        
        jPanel5.add(jButton5);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        getContentPane().add(jPanel5, gridBagConstraints1);
        
        pack();
    }//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JList jList3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    // End of variables declaration//GEN-END:variables

    
 class MyCellRenderer extends JLabel implements ListCellRenderer {
      //  final static ImageIcon longIcon = new ImageIcon("long.gif");
    // final static ImageIcon shortIcon = new ImageIcon("short.gif");

     // This is the only method defined by ListCellRenderer.
     // We just reconfigure the JLabel each time we're called.

     public java.awt.Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)    // the list and the cell have the focus
     {
         Date date = (Date) value;
         
         String s = date.getSubject();
         s = s + " " + new java.util.Date(date.getStartTime()).toString();
         setText(s);
    //     setIcon((s.length() > 10) ? longIcon : shortIcon);
   	   if (isSelected) {
             setBackground(list.getSelectionBackground());
	       setForeground(list.getSelectionForeground());
	   }
         else {
	       setBackground(list.getBackground());
	       setForeground(list.getForeground());
	   }
	   setEnabled(list.isEnabled());
	   setFont(list.getFont());
         setOpaque(true);
         return this;
     }
 }

}
