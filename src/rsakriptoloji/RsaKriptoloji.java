
package rsakriptoloji;

import java.io.DataInputStream;
import java.io.IOException;


public class RsaKriptoloji {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();

        System.out.println("-----------RSA Metin Sifreleme-----------" + "\n");
        System.out.println("Alice Sending Message:");

        DataInputStream input = new DataInputStream(System.in);
        String messageString;
        messageString = input.readLine();

        // encryption message
        byte[] cipher = rsa.Encryption(messageString.getBytes());
        System.out.println("Encrypting Bytes: " + bToString(cipher));

        // decryption message
        byte[] plain = rsa.Decryption(cipher);
        System.out.println("Decrypting Bytes: " + bToString(plain));

        // alice message
        System.out.println("Message is: " + new String(plain));
    }

    private static String bToString(byte[] cipher) {
        String tmp = "";
        for (byte b : cipher) {
            tmp += Byte.toString(b);
        }
        return tmp;
    }

}
