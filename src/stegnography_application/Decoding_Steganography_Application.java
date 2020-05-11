/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegnography_application;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Azmi
 */
public class Decoding_Steganography_Application {
    
    static int offset;
    Decoding_Steganography_Application(){
        offset = 0;
    }
    
    public String decode(String path, String stegano_image_name){

            try{
                //user space is necessary for decrypting
                BufferedImage stegano_image  = user_space(getImage(stegano_image_name));

                String decode = decode_Text(stegano_image);
                
                return decode;
            }
            catch(Exception e){
                System.out.println("EXCEPTION  ::::  "+e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "There is no hidden message in this image!","Error",JOptionPane.ERROR_MESSAGE);
                return "";
            }
	}





	private BufferedImage getImage(String image_name){

            BufferedImage orignal_image	= null;
            File file = new File(image_name);

            try{
                orignal_image = ImageIO.read(file);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Image could not be read!","Error",JOptionPane.ERROR_MESSAGE);
            }
            return orignal_image;
	}





	private boolean setImage(BufferedImage new_image, File file, String ext){
            try{
                file.delete(); //delete resources used by the File
                ImageIO.write(new_image,ext,file);
                return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"File could not be saved!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
	}





	private BufferedImage user_space(BufferedImage orignal_image){

            //create new_image with the attributes of image
            BufferedImage new_image  = new BufferedImage(orignal_image.getWidth(), orignal_image.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics2D	graphics = new_image.createGraphics();
            graphics.drawRenderedImage(orignal_image, null);
            graphics.dispose(); //release all allocated memory for this image
            return new_image;
	}





	private String decode_Text(BufferedImage stegano_image){

            int[] image = get_Int_data(stegano_image);

            String password = check_Password_Exist(image);


            if(password == null){//checking password is null if it is than there is no Password hidden
                int messageLength = messageLengthBytes_Into_Int(image);//getting message length

                int[] messageInInt = message_In_Int(image, messageLength);

                String extension = check_Extension_Exist(image);//checking extension if exist it will return
                
                if(extension == null){//checking extension is null if it is than there is no hidden file only a message
                    
                    String secretMessage = int_Into_String(messageInInt);
                    return secretMessage;
                }
                else{   
                    
                    imageOrFile_Write(messageInInt ,extension);
                    return "done";
                    
                }
            }
            else{ //if there is password hidden
                boolean bool = matching_Password_WithUserInput(password);

                int messageLength = messageLengthBytes_Into_Int(image);//getting message length

                int[] messageInInt = message_In_Int(image, messageLength);
                
                String extension = check_Extension_Exist(image);//checking extension if exist it will return
                
                if(extension == null){//checking extension is null if t is than there is no hidden file only a message
                    
                    String secretMessage = int_Into_String(messageInInt);
                    return secretMessage;
                }
                else{   
                    
                    imageOrFile_Write(messageInInt ,extension);
                    return "done";
                }

            }


	}
        
        
        
        
        private String check_Extension_Exist(int[] image){
            
            String extensionLengthBytes = "";

            //loop through 1 bytes of data to determine extension exist or not
            for(int i=0; i<1; i++){

                //loop through each bit within a byte of text
                for(int bit=0; bit<4; bit++, offset++){

                    if(image[offset] < 2){
                        bit = bit-1; 
                        continue;
                    }
                    else{
                        String oneByteOfImage = Integer.toBinaryString(image[offset]);
                        extensionLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                        extensionLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-1);
                    }
                }
            }
            
            int extensionLength = Integer.parseInt(extensionLengthBytes,2);
            
            if(extensionLength == 3 || extensionLength == 4){
                
                return extracting_Extension(image, extensionLength);
            }
            else{
                return null;
            }
        }

        
        
        
        
        private String extracting_Extension(int[] image, int extensionLength){
            
            String[] extensionBytes = new String[extensionLength];
            //loop through each byte of text
            for(int i=0; i<extensionLength; i++){

                extensionBytes[i] = "";
                //loop through each bit within a byte of text
                for(int bit=0; bit<4; bit++, offset++){

                    if(image[offset] < 2){
                        bit = bit-1; 
                        continue;
                    }
                    else{
                        String oneByteOfImage = Integer.toBinaryString(image[offset]);
                        extensionBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                        extensionBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-1);
                    }
                }
            }

            return int_Into_String(binaryString_Into_int(extensionBytes));
        }
        
        
        



	private int[] message_In_Int(int[] image, int messageLength){

            String[] messageBytes = new String[messageLength];
            //loop through each byte of text
            for(int i=0; i<messageBytes.length; i++ ){

                messageBytes[i] = "";
                //loop through each bit within a byte of text
                for(int j=0; j<4; j++, offset++){

                    if(image[offset] < 2){
                        j=j-1; 
                        continue;
                    }
                    else{
                        String oneByteOfImage = Integer.toBinaryString(image[offset]);
                        messageBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                        messageBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-1);
                    }
                }
            }

            return binaryString_Into_int(messageBytes);
	}





	private int messageLengthBytes_Into_Int(int[] image){

            //initialize messageLengthBytes here
            String messageLengthBytes = "";
            int offset2 = offset + 16;
            //loop through 16 bytes of data to determine text length
            for(int i=offset; i<offset2; i++,offset++){

                String oneByteOfImage = Integer.toBinaryString(image[offset]);
                messageLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                messageLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-1);
            }

            int messageLength = Integer.parseInt(messageLengthBytes,2);
            return messageLength;
	}





	private String check_Password_Exist(int[] image){

            String passwordLengthBytes = "";

            //loop through 4 bytes of data to determine Password exist or not
            for(int i=0; i<4; i++){

                String oneByteOfImage = Integer.toBinaryString(image[i]);
                passwordLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                passwordLengthBytes += oneByteOfImage.charAt(oneByteOfImage.length()-1);
            }

            int passwordLength = Integer.parseInt(passwordLengthBytes,2);
            if(passwordLength == 0){
                return null;
            }
            else{
                return extracting_Password(image, passwordLength);
            }

	}




	private String extracting_Password(int[] image, int passwordLength){

            String[] passwordBytes = new String[passwordLength];
            offset = 4;
            //loop through each byte of text
            for(int i=0; i<passwordLength; i++){

                passwordBytes[i] = "";
                //loop through each bit within a byte of text
                for(int bit=0; bit<4; bit++, offset++){

                    if(image[offset] < 2){
                        bit = bit-1; 
                        continue;
                    }
                    else{
                        String oneByteOfImage = Integer.toBinaryString(image[offset]);
                        passwordBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-2);
                        passwordBytes[i] += oneByteOfImage.charAt(oneByteOfImage.length()-1);
                    }
                }
            }

            return int_Into_String(binaryString_Into_int(passwordBytes));
	}




	private int[] binaryString_Into_int(String[] messageBytes){

            int[] message_In_Int = new int[messageBytes.length];

            for(int i=0; i<messageBytes.length; i++){

                message_In_Int[i] = Integer.parseInt(messageBytes[i],2);//CONVERTING BINARY STRING TO INT
            }
            return message_In_Int;
	}





	private String int_Into_String(int[] message_In_Int){

            String message = "";

            for(int i=0; i<message_In_Int.length; i++){

        	message += Character.toString((char) message_In_Int[i]); //converting int to string
            }
            return message;
	}





	private boolean matching_Password_WithUserInput(String password){

            String enteredPassword;
            enteredPassword = JOptionPane.showInputDialog("Enter Password");

            if(password.equals(enteredPassword)){
                    return true;
            }
            else{
                    JOptionPane.showMessageDialog(null, "WRONG PASSWORD !!","Wrong Password",JOptionPane.ERROR_MESSAGE);
                    int again = JOptionPane.showConfirmDialog(null, "Do you want to enter password agin?", "RETRY",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(again == 0){//0 = yes
                    matching_Password_WithUserInput(password);
                }
                else{
                    System.exit(0);
                }
                return false;
            }
	}





	private boolean imageOrFile_Write(int[] message_In_Int, String extension){
            
            Decoding_Stegano_Application decoding_Stegano_Application = new Decoding_Stegano_Application();
            
            String path = decoding_Stegano_Application.getPath();
            String name;
            
            try{
                
                switch(extension){
                    
                    case "txt":
                        name = JOptionPane.showInputDialog("Enter Text File Name");
                        path = path + "\\" + name + "." + extension;
                        break;

                    case "pdf":
                        name = JOptionPane.showInputDialog("Enter PDF File Name");
                        path = path + "\\" + name + "." + extension;
                        break;

                    case "png":
                        name = JOptionPane.showInputDialog("Enter Image Name");
                        path = path + "\\" + name + "." + extension;
                        break;

                    case "jpeg":
                        name = JOptionPane.showInputDialog("Enter Image Name");
                        path = path + "\\" + name + "." + extension;
                        break;
                        
                    case "bmp":
                        name = JOptionPane.showInputDialog("Enter Image Name");
                        path = path + "\\" + name + "." + extension;
                        break;
                }
                File ob	  =   new File(path);
                FileOutputStream nf = new FileOutputStream(ob);

                if(!ob.exists()){
                    ob.createNewFile();
                }

                for (int i=0; i<message_In_Int.length; i++) {
                    nf.write(message_In_Int[i]);
                }
                return true;
            }
            catch (Exception e) {
                System.out.println("EXCEPTION  ::::  "+e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "There is no hidden message in this image!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
	}





	private int[] get_Int_data(BufferedImage new_image){

            WritableRaster raster   = new_image.getRaster();
            DataBufferInt buffer = (DataBufferInt)raster.getDataBuffer();
            return buffer.getData();
	}





	

	
	/*public static void main(String[] args) {
		
            Example2 obj = new Example2();
            Scanner input = new Scanner(System.in);


            System.out.print("Select One Option :-\n  1.Encode  2.Decode  :: ");
            int option = input.nextInt();
            input.nextLine();

            if(option == 1){
                System.out.print("\n\nEnter The Path Of Image Folder  :: ");
                String path = "D:\\Abdullah Working\\Java\\Steganography In Java";

                System.out.print("\nEnter Image Name  :: ");
                String imageName = input.nextLine();

                System.out.print("\nEnter Image Type(jpg,png)  :: ");
                String imageType = input.nextLine();

                System.out.print("\nEnter New Image Name  :: ");
                String newImageName = input.nextLine();

                System.out.print("\nEnter Secret Message  :: ");
                String secretMessage = input.nextLine();

                boolean bool = obj.encode(path, imageName, imageType, newImageName, secretMessage);
                if(bool == true){
                    System.out.println("DONE!!");
                }
                else{
                    System.out.println("SORRY!!");
                }
            }
            else if(option == 2){
                System.out.print("\n\nEnter The Path Of Image Folder  :: ");
                String path = "D:\\Abdullah Working\\Java\\Steganography In Java";


                System.out.print("\nEnter Name Of Steganographic Image  :: ");
                String imageName = input.next();

                obj.decode(path, imageName);
            }
            else{
                System.out.println("Wrong Input Sorry!");
            }
	}
    */
}
