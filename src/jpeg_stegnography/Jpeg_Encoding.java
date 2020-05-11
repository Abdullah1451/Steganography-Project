/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpeg_stegnography;

import java.io.*;

/**
 *
 * @author Azmi
 */
public class Jpeg_Encoding {
    
    public static void main(String args[]) throws Exception{
        File ob = new File("D:\\Abdullah Working\\Java\\Java file\\af.jpg");
			BufferedInputStream r = new BufferedInputStream(new FileInputStream(ob));
			int l;
			System.out.println(ob.getName());
			while((r.available()) >1){
                            if(r.read() == 255 && r.read() == 218){
                                while((r.available()) >1)
                                    System.out.println(r.read());
                            }
                        }
				
    }
    
}
