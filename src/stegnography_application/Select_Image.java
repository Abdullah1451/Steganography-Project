/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegnography_application;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Azmi
 */
public class Select_Image extends javax.swing.JFrame {

    //My Private Variables Declaration
    private static String imagePath;
     private static String ImageMimeType;
    private static boolean SelectImageButtonIsPressed = false;
    //End Private Variables Declaration
    
    /**
     * Creates new form Select_Image
     */
    public Select_Image() {
        SelectImageButtonIsPressed = false;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageLabel = new javax.swing.JLabel();
        SelectImageButton = new javax.swing.JButton();
        FinishButton = new javax.swing.JButton();
        InformationLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SelectImageButton.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        SelectImageButton.setText("Select Image");
        SelectImageButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        SelectImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectImageButtonActionPerformed(evt);
            }
        });

        FinishButton.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        FinishButton.setText("Finish");
        FinishButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        FinishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishButtonActionPerformed(evt);
            }
        });

        InformationLabel.setBackground(new java.awt.Color(255, 255, 255));
        InformationLabel.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        InformationLabel.setForeground(new java.awt.Color(0, 0, 255));
        InformationLabel.setText("<html>This Image Should Be 8 Times Smaller Than The Cover Image</html>");
        InformationLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InformationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelectImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(FinishButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SelectImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InformationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(FinishButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishButtonActionPerformed
        // TODO add your handling code here:
        if(SelectImageButtonIsPressed == true){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Select Any Image","IMAGE NOT SELECTED",JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_FinishButtonActionPerformed

    private void SelectImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectImageButtonActionPerformed
        // TODO add your handling code here:
        File_Chooser fileChooser = new File_Chooser();
        File file = fileChooser.file_Chooser(ImageLabel);
        
        try {
            String mimetype = Files.probeContentType(file.toPath());
            set_ImageMimeType(mimetype.split("/")[1]);
        }
        catch (IOException ex) {
            Logger.getLogger(Select_Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        set_ImagePath(file.getAbsolutePath());

    }//GEN-LAST:event_SelectImageButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Select_Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Select_Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Select_Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Select_Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Select_Image().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FinishButton;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel InformationLabel;
    private javax.swing.JButton SelectImageButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Create setMessage_Type() method to set image path
     */
    private void set_ImagePath(String text) {
        imagePath = text;
        SelectImageButtonIsPressed = true;
    }
    
    /**
     * Create setMessage_Type() method to set the type of the message
     * @return 
     */
    public String get_ImagePath() {
        return imagePath;
    }
    
    
    /**
     * Create set_ImageMimeType() method to set image mime type
     */
    private void set_ImageMimeType(String text) {
        ImageMimeType = text;
    }
    
    /**
     * Create get_ImageMimeType() method to get image mime type
     * @return 
     */
    public String get_ImageMimeType() {
        return ImageMimeType;
    }
}
