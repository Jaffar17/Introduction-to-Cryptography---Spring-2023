package Assignment1;

import java.util.*;
import java.io.*;

public class VignereCipher {
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
        message = message.toLowerCase();

        output.write("*** Final output will be in all lowercase letters *** \n");
        output.flush();

        output.write("Enter the key: ");
        output.flush();
        st = new StringTokenizer(br.readLine());
        String key = st.nextToken();
        key = key.toLowerCase();

        output.write("Press following buttons: \n 1. Encrypt \n 2. Decrypt \n");
        output.flush();

        st = new StringTokenizer(br.readLine());
        int userInput = Integer.parseInt(st.nextToken());

        if(userInput == 1){
            // encrypt case
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

    static String encrypt(String message, String key){
        //97-122: a-z
        StringBuilder cipherText = new StringBuilder();
        int j = 0; // pointer to go over key
        for(int i=0; i<message.length(); i++){
            if(message.charAt(i) != ' '){
                if(j == key.length()){
                    j = 0;
                }
                int value = (((message.charAt(i) - 97) + (key.charAt(j) - 97))%26)+97;
                char c = (char)value;
                cipherText.append(c);
                j++;
            }
            else{
                // space case
                cipherText.append(message.charAt(i));
            }
        }
        return cipherText.toString();
    }

    static String decrypt(String message, String key){
        StringBuilder plainText = new StringBuilder();
        int j=0; // pointer to go over key
        for(int i=0; i<message.length(); i++){
            if(message.charAt(i) != ' '){
                if(j == key.length()){
                    j = 0;
                }
                int value = (message.charAt(i) - 97) - (key.charAt(j) - 97);
                if(value < 0){
                    // Theorem: Rule 1: Add 26
                    value = ((value + 26)%26)+97;
                }
                else{
                    value = (value%26)+97;
                }
                char c = (char)value;
                plainText.append(c);
                j++;
            }
            else{
                // spaces case
                plainText.append(message.charAt(i));
            }
        }
        return plainText.toString();
    }
}
