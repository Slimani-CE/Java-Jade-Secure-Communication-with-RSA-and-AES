package ma.enset.sma.aes.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


public class ServerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer container = runtime.createAgentContainer(profile);

        String secret = "1234567812345678";

        AgentController serverController = container.createNewAgent("server", "ma.enset.sma.aes.agents.ServerAgent", new Object[]{secret});
        serverController.start();
    }
}
