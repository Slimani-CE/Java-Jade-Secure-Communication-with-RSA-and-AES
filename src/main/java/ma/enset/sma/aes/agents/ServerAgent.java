package ma.enset.sma.aes.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String secret = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    String cryptedEncodedMessage = message.getContent();
                    byte[] cryptedMessage = Base64.getDecoder().decode(cryptedEncodedMessage);
                    try{
                        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKey);
                        byte[] decryptedMessage = cipher.doFinal(cryptedMessage);
                        System.out.println("Message received from: " + message.getSender().getLocalName() + " | Content: " + new String(decryptedMessage));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

}
