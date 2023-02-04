package Assignment1;

import java.util.*;
import java.io.*;

public class CaesarCipher {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter output = new PrintWriter(new BufferedOutputStream(System.out));

        output.write("Enter the message to encrypt/decrypt: ");
        output.flush();
        StringTokenizer st = new StringTokenizer(br.readLine());
        String message = st.nextToken();
        while(st.hasMoreTokens()){
            message = message + " " + st.nextToken();
        }

        output.write("Enter the key: ");
        output.flush();
        st = new StringTokenizer(br.readLine());
        int key = Integer.parseInt(st.nextToken());

        output.write("Press following buttons: \n 1. Encrypt \n 2. Decrypt \n");
        output.flush();

        st = new StringTokenizer(br.readLine());
        int userInput = Integer.parseInt(st.nextToken());

        if(userInput == 1){
            //encrypt case
            String cipherText = encrypt(message, key);
            output.write("The encrypted message is: \n");
            output.write(cipherText+"\n");
            output.flush();
        }
        else{
            String plainText = decrypt(message, key);
            output.write("The decrypted message is: \n");
            output.write(plainText+"\n");
            output.flush();
        }
    }

    static String encrypt(String message, int key){
        //65-90: A-Z
        //97-122: a-z
        StringBuilder cipherText = new StringBuilder();
        for(int i=0; i<message.length(); i++){
            if(message.charAt(i)!=' '){
                if(Character.isUpperCase(message.charAt(i))){
                    // Capital Letters Case
                    int value = ((((int)message.charAt(i)-65)+key)%26)+65;
                    char c = (char)value;
                    cipherText.append(c);
                }
                else{
                    // Small Letters Case
                    int value = ((((int)message.charAt(i)-97)+key)%26)+97;
                    char c = (char)value;
                    cipherText.append(c);
                }
            }
            else{
                // Space present in string
                cipherText.append(message.charAt(i));
            }
        }
        return cipherText.toString();
    }

    static String decrypt(String cipherText, int key){
        StringBuilder plainText = new StringBuilder();
        for(int i=0; i<cipherText.length(); i++){
            if(cipherText.charAt(i) != ' '){
                if(Character.isUpperCase(cipherText.charAt(i))){
                    // Upper case letters
                    int value = (((26-key)+(cipherText.charAt(i)-65))%26)+65;
                    char c = (char)value;
                    plainText.append(c);
                }
                else{
                    // Lower case letters
                    int value = (((26-key)+(cipherText.charAt(i)-97))%26)+97;
                    char c = (char)value;
                    plainText.append(c);
                }
            }
            else {
                plainText.append(cipherText.charAt(i));
            }
        }
        return plainText.toString();
    }
}
