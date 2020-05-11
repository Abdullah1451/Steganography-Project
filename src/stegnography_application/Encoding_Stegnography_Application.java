/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegnography_application;
import java.io.File;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.*;

/**
 *
 * @author Azmi
 */
public class Encoding_Stegnography_Application {

    public static Scanner input;
    static int offset;
    Encoding_Stegnography_Application(){
        input = new Scanner(System.in);
        offset = 0;
    }

    public boolean encode(String image_name, String stegano_image_name, String ext,String secretMessageOrPath){
        BufferedImage orignal_image = getImage(image_name);
        
        //user space is not necessary for Encrypting
        BufferedImage new_image = user_space(orignal_image);
        new_image = add_text(new_image,secretMessageOrPath);

        return(setImage(new_image,new File(stegano_image_name),ext));
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




    private String[] secret_File_Into_Bytes(String fileOrImagePath){

        try{

            File file = new File(fileOrImagePath);
            InputStream fis = new FileInputStream(file);
            //DataInputStream dis = new DataInputStream(fis);
            BufferedInputStream dis = new BufferedInputStream(fis);

            int i = 0;
            int small_img[] = new int[dis.available()];

            while(dis.available() > 0){
                    small_img[i] = dis.read();
                    i++;
            }
            String[] secretFileBytes = message_Into_Binary(small_img);

            return secretFileBytes;
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File Not Found Exception", "Error",JOptionPane.ERROR_MESSAGE);
            return null;
        }


    }





    private BufferedImage add_text(BufferedImage new_image,String secretMessageOrPath){

        Message_Type message_Type = new Message_Type();
        Select_Image select_Image = new Select_Image();
        Password pword = new Password();
        //convert all items to byte arrays: image, message, message length
        int[] img = get_Int_Data(new_image);
                //System.out.println("Image length  ::  "+img.length);

        try{
            /*
             *
             IF THEY WANT TO HIDE FILE OR IMAGE THEN 
             THIS CONDITION WILL BE TRUE 
             *
             */
            String secretMessageType = message_Type.getMessage_Type();
            if(secretMessageType.equals(" TEXT FILE") || secretMessageType.equals(" IMAGE") || secretMessageType.equals(" PDF FILE")){

                //converting file daa into binary string 
                String[] secretFileBytes = secret_File_Into_Bytes(secretMessageOrPath);
                String[] secretFileLengthBytes = messageLength_Into_Binary(secretFileBytes.length);

                //converting file Extesion into bytes and length of file extension
                String extension = null;
                switch(secretMessageType){

                    case " TEXT FILE":
                        extension = "txt";
                        break;

                    case " IMAGE":
                        extension = select_Image.get_ImageMimeType();
                        break;

                    case " PDF FILE":
                        extension = "pdf";
                        break; 
                }

                String[] extensionBytes = extension_Into_Bytes(extension);
                String[] extensionLengthBytes = oneByte_Into_Binary(extensionBytes.length);

                /*
                 *
                 *
                 CHECKING IF THEY ENTERED A PASSWORD OR NOT
                 *
                 *
                 */
                if(pword.get_PasswordSelected() == true){
                    
                    String[] passwordBytes = password_Into_Bytes();
                    String[] passwordLengthBytes = oneByte_Into_Binary(passwordBytes.length);

                    //calling encode_text method to replace last two bits to images with secret file or message bits
                    encode_Text(img, passwordLengthBytes); //0 first positiong Hiding password length
                    encode_Text(img, passwordBytes); //hiding password
                    encode_Text(img, secretFileLengthBytes); //0 first positiong
                    encode_Text(img, secretFileBytes); //4 bytes of space for length: 4bytes*8bit = 32 bits
                    encode_Text(img, extensionLengthBytes); //replacing img bits with extension length bits in 1 bytes
                    encode_Text(img, extensionBytes); //replacing img bits with extension bits in 3 or 4 bytes

                }
                else if(pword.get_PasswordSelected() == false){

                    //calling encode_text method to replace last two bits to images with secret file or message bits
                    encode_Text(img, secretFileLengthBytes); //0 first positiong 
                    encode_Text(img, secretFileBytes); //4 bytes of space for length: 4bytes*8bit = 32 bits
                    encode_Text(img, extensionLengthBytes); //replacing img bits with extension length bits in 1 bytes
                    encode_Text(img, extensionBytes); //replacing img bits with extension bits in 3 or 4 bytes 
                }
                else{
                    System.out.print("\n\nSorry Wrong!!");
                    System.exit(0);
                }

            }


            /*
             *
             IF THEY WANT TO A MESSAGE THEN 
             THIS CONDITION WILL BE TRUE 
             *
             */
            else if(secretMessageType.equals(" MESSAGE ")){

                int[] val = message_Into_Int(secretMessageOrPath);//Calling message_Into_Int() to covert message into int
                String[] secretMessageBytes = message_Into_Binary(val);
                String[] secretMessageLengthBytes = messageLength_Into_Binary(secretMessageOrPath.length());


                /*
                 *
                 *
                 ASKING WANT TO ADD PASSWORD OR NOT
                 *
                 *
                 */
                if(pword.get_PasswordSelected() == true){

                    String[] passwordBytes = password_Into_Bytes();
                    String[] passwordLengthBytes = oneByte_Into_Binary(passwordBytes.length);

                    //calling encode_text method to replace last two bits to images with secret file or message bits
                    encode_Text(img, passwordLengthBytes); //0 first positiong Hiding password length
                    encode_Text(img, passwordBytes); //hiding password
                    encode_Text(img, secretMessageLengthBytes); //0 first positiong
                    encode_Text(img, secretMessageBytes); //4 bytes of space for length: 4bytes*8bit = 32 bits

                }
                else if(pword.get_PasswordSelected() == false){
                    //calling encode_text method to replace last two bits to images with secret file or message bits
                    encode_Text(img, secretMessageLengthBytes); //0 first positiong
                    encode_Text(img, secretMessageBytes); //4 bytes of space for length: 4bytes*8bit = 32 bits
                }
                else{

                    System.out.print("\n\nSorry Wrong input bye bye!!");
                    System.exit(0);
                }

            }
            /*
             *
             IF NON OF THEM IS THEN THE PROGRAM WILL EXIT IN ELSE BLOCK 
             *
             */
            else{
                System.out.print("\n\nWrong!!");
                System.exit(0);
            }
        }
        catch(Exception e){

            System.out.println("EXCEPTION  ::::  "+e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Target File cannot hold message!", "Error",JOptionPane.ERROR_MESSAGE);
        }
        return new_image;
    }




    private String[] extension_Into_Bytes(String extension){
        int[] extensionInInt = message_Into_Int(extension);
        return message_Into_Binary(extensionInInt);
    }




    private String[] password_Into_Bytes(){

        Password pword = new Password();
        String password = pword.get_Password();

        int[] passwordInInt = message_Into_Int(password);
        return message_Into_Binary(passwordInInt);
    }





    private int[] message_Into_Int(String secretMessage){

        int[] val = new int[secretMessage.length()];

        for (int i=0; i<secretMessage.length(); i++){
            val[i] = Integer.valueOf(secretMessage.charAt(i));
        }
        return val;
    }





    private String[] message_Into_Binary(int[] small_img_or_message){

        int len = 8;
        //int[] val = new int[msg.length()];
        String[] str = new String[small_img_or_message.length];

        // convert each char to 
        // ASCII value 
        for (int i=0; i<small_img_or_message.length; i++){
            str[i] = String.format("%" + len + "s",Integer.toBinaryString(small_img_or_message[i])).replaceAll(" ", "0");//Integer.toBinaryString(val[i]);//CONVERTING INT TO BINARY STRING
        }
        return str;
    }



    
    private String[] oneByte_Into_Binary(int passwordLength){
        String[] passwordLengthBytes = new String[1];
        passwordLengthBytes[0] = String.format("%" + 8 + "s",Integer.toBinaryString(passwordLength)).replaceAll(" ", "0");
        return passwordLengthBytes;
    }
    
    
    


    private String[] messageLength_Into_Binary(int messageLength){
        String[] messageLengthBytes = new String[4];

        if(messageLength <=255){
            messageLengthBytes[0] = "00000000";
            messageLengthBytes[1] = "00000000";
            messageLengthBytes[2] = "00000000";
            messageLengthBytes[3] = String.format("%" + 8 + "s",Integer.toBinaryString(messageLength)).replaceAll(" ", "0");

        }
        else if(messageLength >= 256 && messageLength <= 65535){
            messageLengthBytes[0] = "00000000";
            messageLengthBytes[1] = "00000000";
            String s = String.format("%" + 16 + "s",Integer.toBinaryString(messageLength)).replaceAll(" ", "0");
            messageLengthBytes[2] = s.substring(0,8);
            messageLengthBytes[3] = s.substring(8,16);

        }
        return messageLengthBytes;
    }




    private int[] encode_Text(int[] img, String[] messageOrLength){
        if(messageOrLength.length*4 > img.length){
            throw new IllegalArgumentException("File not long enough!");
        }

        for(int i=0; i<messageOrLength.length; i++){
            //loop through the 8 bits of each byte
            String dataByte = messageOrLength[i];

            for(int bit=0; bit<=7; bit++, offset++){ //ensure the new offset value carries on through both loops

                if(img[offset] < 2){
                    bit=bit-1;
                    continue;
                }
                else{
                    String oneByteOfImage = Integer.toBinaryString(img[offset]);// One Image Byte Is Converting To Binary String
                                    System.out.println("\n\noneByteOfImage  ::::  "+oneByteOfImage+"  img[offset] ::  "+offset+"   ::::   " +dataByte.charAt(bit) + dataByte.charAt(bit+1));
                    //Replacing Last One Bit Of Message Or Length Binary String To Image Binary String 
                    oneByteOfImage = oneByteOfImage.substring(0, oneByteOfImage.length() - 2) + dataByte.charAt(bit) + dataByte.charAt(bit+1);
                    bit++;
                    img[offset] = Integer.parseInt(oneByteOfImage,2);//Converting Binary String Into Int
                                    //System.out.println("AFTERoneByteOfImage  ::::  "+oneByteOfImage+"  img["+offset+"] ::  "+img[offset]);
                }
            }
        }
        return img;
    }





    private int[] get_Int_Data(BufferedImage new_image){

        WritableRaster raster   = new_image.getRaster();
        DataBufferInt buffer = (DataBufferInt)raster.getDataBuffer();
        return buffer.getData();
    }




   /* public static void main(String[] args) {

        Encoding_Stegnography_Application obj = new Encoding_Stegnography_Application();


        System.out.print("Select One Option :-\n  1.Encode  2.Decode  :: ");
        int option = input.nextInt();
        input.nextLine();

        if(option == 1){
            System.out.print("\n\nEnter The Path Of Image Folder  :: ");
            String path = "D:\\Abdullah Working\\Java\\Steganography In Java";

            System.out.print("\nEnter Image Name  :: ");
            String imageName = input.nextLine();

            System.out.print("\nEnter New Image Name  :: ");
            String newImageName = input.nextLine();

            newImageName = path  + "/" + newImageName;
            path = path + "/" + imageName;

            boolean bool = true;//obj.encode(path, newImageName);
            if(bool == true){
                System.out.println("DONE!!");
            }
            else{
                System.out.println("SORRY!!");
            }
        }
        else{
            System.out.println("Wrong Input Sorry!");
        }
    }*///main ends

}
