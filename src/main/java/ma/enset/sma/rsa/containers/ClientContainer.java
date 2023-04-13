package ma.enset.sma.rsa.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClientContainer {
    public static void main(String[] args) throws StaleProxyException, FileNotFoundException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer container = runtime.createAgentContainer(profile);

        // Read the private key from the file
        String encodedPK = new Scanner(new File("publickey.txt")).next();


        AgentController serverController = container.createNewAgent("client", "ma.enset.sma.rsa.agents.ClientAgent", new Object[]{encodedPK});
        serverController.start();
    }
}
