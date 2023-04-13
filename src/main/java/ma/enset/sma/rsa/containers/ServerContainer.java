package ma.enset.sma.rsa.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ServerContainer {
    public static void main(String[] args) throws StaleProxyException, FileNotFoundException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer container = runtime.createAgentContainer(profile);

        // Read the public key from the file
        String encodedPbK = new Scanner(new File("privatekey.txt")).next();

        AgentController serverController = container.createNewAgent("server", "ma.enset.sma.rsa.agents.ServerAgent", new Object[]{encodedPbK});
        serverController.start();
    }
}
