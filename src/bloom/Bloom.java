package bloom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author spencer.stewart and jacob.ashcraft
 */
public class Bloom {

   /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String content = new String();
        
        File file = new File("wordlist.txt");
        
        
        LinkedList<String> list = new LinkedList<>();

        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            while (sc.hasNextLine()){
            content = sc.nextLine();
            list.add(content);
        }
        
        sc.close();
        }catch(FileNotFoundException fnf){
        fnf.printStackTrace();
        }
        catch (Exception e) {
        e.printStackTrace();
        System.out.println("\nProgram terminated Safely...");
        }

        Collections.reverse(list);
        Iterator i = list.iterator();
    
        try{
            hasher(list);    
        } catch (NoSuchAlgorithmException e){
            System.out.println("NoSuchAlgorithmException was thrown here" + e);
        }
        
        
    }
    
    
    public static void hasher(LinkedList<String> list) throws NoSuchAlgorithmException {
    
        String hash = "35454B055CC325EA1AF2126E27707052";
        //String password = "ILoveJava";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest;
        LinkedList<String> myHash = new LinkedList<String>();
        
        for(int i = 0; i < list.size(); i++)
        {
            md.update(list.get(i).getBytes());
            digest = md.digest();
            myHash.add(DatatypeConverter.printHexBinary(digest).toUpperCase());
        }
        
        for(int i = 0; i < myHash.size(); i++){
            System.out.println(myHash.get(i));
        }
    }  
}
