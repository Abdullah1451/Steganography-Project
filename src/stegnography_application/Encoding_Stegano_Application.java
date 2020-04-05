/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegnography_application;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Azmi
 */
public class Encoding_Stegano_Application extends javax.swing.JFrame {

    
    //My Private Variables Declaration
    private static String coverImagePath;
    private static String path;
    private static boolean selectCoverImageButtonIsPressed = false;
    private static boolean MessageButtonIsPressed = false;
    //End Private Variables Declaration
    
    /**
     * Creates new form Encoding_Stegano_Application
     */
    public Encoding_Stegano_Application() {
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

        SelectCoverImageButton = new javax.swing.JButton();
        MessageButton = new javax.swing.JButton();
        jLabelImage = new javax.swing.JLabel();
        FinishButton = new javax.swing.JButton();
        PasswordButton = new javax.swing.JButton();
        PasswordTextField = new javax.swing.JTextField();
        MessageTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Encoding");

        SelectCoverImageButton.setFont(new java.awt.Font("Arial", 3, 30)); // NOI18N
        SelectCoverImageButton.setText("Select Image");
        SelectCoverImageButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.darkGray));
        SelectCoverImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectCoverImageButtonActionPerformed(evt);
            }
        });

        MessageButton.setFont(new java.awt.Font("Arial", 3, 30)); // NOI18N
        MessageButton.setText("Message");
        MessageButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.darkGray));
        MessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageButtonActionPerformed(evt);
            }
        });

        jLabelImage.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelImage.setText("    No Image Selected");

        FinishButton.setFont(new java.awt.Font("Arial", 3, 30)); // NOI18N
        FinishButton.setText("Finish");
        FinishButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.darkGray));
        FinishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishButtonActionPerformed(evt);
            }
        });

        PasswordButton.setFont(new java.awt.Font("Arial", 3, 30)); // NOI18N
        PasswordButton.setText("Password");
        PasswordButton.setActionCommand("<html>Password(optional)</html>");
        PasswordButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.darkGray));
        PasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordButtonActionPerformed(evt);
            }
        });

        PasswordTextField.setEditable(false);
        PasswordTextField.setBackground(new java.awt.Color(255, 255, 255));
        PasswordTextField.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        PasswordTextField.setForeground(new java.awt.Color(0, 0, 255));
        PasswordTextField.setText("Password Is Optional");

        MessageTextField.setEditable(false);
        MessageTextField.setBackground(new java.awt.Color(255, 255, 255));
        MessageTextField.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        MessageTextField.setForeground(new java.awt.Color(0, 0, 255));
        MessageTextField.setText("Message Not Selected");
        MessageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MessageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SelectCoverImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(FinishButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PasswordTextField)
                    .addComponent(MessageTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SelectCoverImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(MessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(FinishButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectCoverImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectCoverImageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        chooser.addChoosableFileFilter(fnef);
        int showOpenDialog = chooser.showOpenDialog(null);
        
        if(showOpenDialog == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            String imagePath = file.getAbsolutePath();
            set_CoverImagePath(imagePath);
            setPath(file.getParent());
            
            ImageIcon imageicon = new ImageIcon(imagePath);
            Image image = imageicon.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(), Image.SCALE_SMOOTH);
            jLabelImage.setIcon(new ImageIcon(image));
        }
    }//GEN-LAST:event_SelectCoverImageButtonActionPerformed

    private void MessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageButtonActionPerformed
        // TODO add your handling code here:
        MessageButtonIsPressed = true;
        Message_Type messageType = new Message_Type();
        messageType.setVisible(true);
        delay_MessageSelected();
    }//GEN-LAST:event_MessageButtonActionPerformed

    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishButtonActionPerformed
        // TODO add your handling code here:
        if(MessageButtonIsPressed == true && selectCoverImageButtonIsPressed == true){
            String steganoImageName;
            steganoImageName = JOptionPane.showInputDialog("Enter The Name Of Stegano Image");
            Calling_Encode(steganoImageName);
            
            int again = JOptionPane.showConfirmDialog(null, "Do you want to do again?", "AGAIN",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(again == 0){//0 = yes
                MessageButtonIsPressed = false;
                MessageTextField.setText("Message Not Selected");
                PasswordTextField.setText("Password Is Optional");
                selectCoverImageButtonIsPressed = false;
                jLabelImage.setIcon(null);
                Stegano_Application stegano_Application = new Stegano_Application();
                dispose();
                stegano_Application.setVisible(true);
            }
            else{
                dispose();
                System.exit(0);
            }
            
        } 
        else if(MessageButtonIsPressed == true && selectCoverImageButtonIsPressed == false){
            JOptionPane.showMessageDialog(null, "Select Any Image","IMAGE NOT SELECTED",JOptionPane.ERROR_MESSAGE);
        }
        else if(MessageButtonIsPressed == false && selectCoverImageButtonIsPressed == true){
            JOptionPane.showMessageDialog(null, "Select Message","MESSAGE NOT SELECTED",JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Select Any Image And Messaage","MESSAGE AND IMAGE NOT SELECTED",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_FinishButtonActionPerformed

    private void PasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordButtonActionPerformed
        // TODO add your handling code here:
        Password pword = new Password();
        pword.setVisible(true);
        delay_PasswordSelected();
    }//GEN-LAST:event_PasswordButtonActionPerformed

    private void MessageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MessageTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(Encoding_Stegano_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Encoding_Stegano_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Encoding_Stegano_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Encoding_Stegano_Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Encoding_Stegano_Application().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FinishButton;
    private javax.swing.JButton MessageButton;
    private javax.swing.JTextField MessageTextField;
    private javax.swing.JButton PasswordButton;
    private javax.swing.JTextField PasswordTextField;
    private javax.swing.JButton SelectCoverImageButton;
    private javax.swing.JLabel jLabelImage;
    // End of variables declaration//GEN-END:variables

    
    /**
     * Create setPath() method to set the path of the cover image
     */
    private void set_CoverImagePath(String text) {
        coverImagePath = text;
        selectCoverImageButtonIsPressed = true;
    }
    
    /**
     * Create getPath() method to get the path of the cover image
     * @return 
     */
    public String get_CoverImagePath(){
       return coverImagePath;
    }

    /**
     * Create setPath() method to set the path without any fileName
     */
    private void setPath(String text) {
        path = text;
    }
    
    /**
     * Create getPath() method to get the path
     * @return 
     */
    public String getPath(){
       return path;
    }
    
    /**
     * Create message_Selected() method to print this
     */
    private void message_Selected(){
        MessageTextField.setText("Message Is Selected");
    }
    
    /**
     * Create password_Selected() method to print this
     */
    public void password_Selected() {
        
        Password encode_Password = new Password();
        
        if(encode_Password.get_PasswordSelected()){
          PasswordTextField.setText("Password Is Entered");
        }

    }
    
    
    
    /****CALLING ENCODE METHOD IN CLASS Steganography_Application() TO ENCODE SECRET MESSAGE****/
    private void Calling_Encode(String steganoImageName) {
        boolean bool = false;
        steganoImageName = path + "\\" + steganoImageName + "." + "png";
        Encoding_Stegnography_Application stegnography_Application = new Encoding_Stegnography_Application();
        Message_Type message_Type = new Message_Type();
        
        if(message_Type.getMessage_Type().equals(" TEXT FILE")){
            Select_Text_File select_text_file = new Select_Text_File();
            bool = stegnography_Application.encode(coverImagePath, steganoImageName, select_text_file.get_TextFilePath());
        }
        
        else if(message_Type.getMessage_Type().equals(" IMAGE")){
            Select_Image select_image = new Select_Image();
            bool = stegnography_Application.encode(coverImagePath, steganoImageName, select_image.get_ImagePath());
        }
        
        else if(message_Type.getMessage_Type().equals(" PDF FILE")){
            Select_Pdf_File select_pdf_file = new Select_Pdf_File();
            bool = stegnography_Application.encode(coverImagePath, steganoImageName, select_pdf_file.get_PdfFilePath());
        }
        
        else if(message_Type.getMessage_Type().equals(" MESSAGE ")){
            Enter_Message enter_Message = new Enter_Message();
            bool = stegnography_Application.encode(coverImagePath, steganoImageName, enter_Message.get_EnteredMessage());
        }


        if(bool == true){
            JOptionPane.showMessageDialog(null, "DONE YEAH HURRAY");
        }
        else{
             JOptionPane.showMessageDialog(null, "NOT DONE YEAH HURRAY");
        }
    }

    
    private void delay_MessageSelected() {
        
        Timer timer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              message_Selected();
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start();
    }
    
    
    private void delay_PasswordSelected() {
        
        Timer timer = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              password_Selected();
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start();
    }
}