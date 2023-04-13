package ma.enset.sma.rsa.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPk = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    String cryptedEncodedMessage = message.getContent();
                    byte[] cryptedMessage = Base64.getDecoder().decode(cryptedEncodedMessage);
                    try{
                        byte[] decodedPk = Base64.getDecoder().decode(encodedPk);
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPk));
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
