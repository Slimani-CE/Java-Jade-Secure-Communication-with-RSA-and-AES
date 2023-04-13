package ma.enset.sma.aes.containers;

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

        String secret = "1234567812345678";

        AgentController serverController = container.createNewAgent("client", "ma.enset.sma.aes.agents.ClientAgent", new Object[]{secret});
        serverController.start();
    }
}
