package bloom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author spencer.stewart and jacob.ashcraft
 */
public class Bloom {

    static String content = new String();
        
    static File file = new File("wordlist.txt");
    static String hash = "35454B055CC325EA1AF2126E27707052";
    static MessageDigest md;

   /*
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try {
            LinkedList<String> list = new LinkedList<>();
            LinkedList<String> hashedList = new LinkedList<>();
            Scanner in;
            String hashedWord;
            String word;
            boolean inDict;
            try (Scanner sc = new Scanner(new FileInputStream(file))) {
                in = new Scanner(System.in);
                inDict = false;
                md = MessageDigest.getInstance("MD5");
                while (sc.hasNextLine())
                {
                    content = sc.nextLine();
                    list.add(content);
                }
            }
            hashedList = hasher(list);

            System.out.println("Please enter a word to check with the dictionary: ");
            word = in.nextLine();
            in.close();
            
            hashedWord = wordHash(word);
            for(int i = 0; i < hashedList.size(); i++)
            {
                if(hashedList.get(i) == null ? hashedWord == null : hashedList.get(i).equals(hashedWord))
                {
                    inDict = true;
                    break;
                }
            }
            if(inDict)
                System.out.println("Your word, " + word + ", is in our hashed dictionary.");
            else
                System.out.println("Your word, " + word + ", is not in our hashed dictionary.");

        }catch (NoSuchAlgorithmException e){
            System.out.println("NoSuchAlgorithmException was thrown here" + e);
        }
        catch(FileNotFoundException fnf){
            System.out.println("\nProgram terminated Safely...");
        }
        catch (Exception e) {
            System.out.println("\nProgram terminated Safely...");
        }
    }
    public static String wordHash(String word) throws NoSuchAlgorithmException
    {
        byte[] digest;
        md.update(word.getBytes());
        digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    
    public static LinkedList<String> hasher(LinkedList<String> list) throws NoSuchAlgorithmException 
    {
        byte[] digest;
        LinkedList<String> myHash = new LinkedList<>();
        
        for(int i = 0; i < list.size(); i++)
        {
            md.update(list.get(i).getBytes());
            digest = md.digest();
            myHash.add(DatatypeConverter.printHexBinary(digest).toUpperCase());
        }
        
        /*for(int i = 0; i < myHash.size(); i++){
            System.out.println(myHash.get(i));
        }*/
        return myHash;
    }  
}
