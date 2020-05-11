/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegnography_application;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Azmi
 */
public class File_Chooser {
    
    private static String path = "d:/";
    File file = null;
    
    public File file_Chooser(JLabel jLabelImage){
        JFileChooser chooser = new JFileChooser(path);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg","bmp");
        chooser.addChoosableFileFilter(fnef);
        int showOpenDialog = chooser.showOpenDialog(null);
        
        if(showOpenDialog == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            String imagePath = file.getAbsolutePath();

            Image image;
            try {
                image = ImageIO.read(file);
                
                if(imagePath != null && (imagePath.endsWith(".jpeg") || imagePath.endsWith(".png") || imagePath.endsWith(".bmp") || imagePath.endsWith(".jpg"))){
                    jLabelImage.setText(" ");
                    jLabelImage.setIcon(new ImageIcon(image));
                    ImageIcon icon = new ImageIcon(scaledimage(imagePath, jLabelImage.getWidth(), jLabelImage.getHeight()));   
                    jLabelImage.setIcon(icon);
                    
                    path = imagePath;
                    return file;
                }
                else{
                     JOptionPane.showMessageDialog(null, "File Is Not An Image","WRONG FILE",JOptionPane.ERROR_MESSAGE);
                }
            } 
            catch (IOException ex) {
                Logger.getLogger(Encoding_Stegano_Application.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Image scaledimage (String locate , int wi , int hi){

        File f = new File(locate);
        BufferedImage resizedimg = null;
        try {
            BufferedImage img = ImageIO.read(f);
            Image imag = img;
            resizedimg = new BufferedImage(wi, hi, BufferedImage.TYPE_INT_BGR);
            Graphics2D g2 = resizedimg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
            g2.drawImage(imag, 0,0,wi,hi,null);
            g2.dispose();
        }
        catch (IOException ex) {
        }

        return resizedimg;
    }
    
}
