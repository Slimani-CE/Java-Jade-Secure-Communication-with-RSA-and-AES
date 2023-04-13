package ma.enset.sma.rsa;

import ma.enset.sma.CryptographyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPair keyPair = CryptographyUtils.generateRSAKeys();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String encodedPK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String encodedPbK = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        // Create files to store the keys
        try {
            FileWriter pubKeyFileWriter = new FileWriter("publickey.txt");
            pubKeyFileWriter.write(encodedPK);
            pubKeyFileWriter.close();

            FileWriter privKeyFileWriter = new FileWriter("privatekey.txt");
            privKeyFileWriter.write(encodedPbK);
            privKeyFileWriter.close();
            System.out.println("Keys generated successfully");
            System.out.println("Public key is stored in  : publickey.txt");
            System.out.println("Private key is stored in : privatekey.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
