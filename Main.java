import Client.Client;
import EcoSystem.EcoSystem;
import Freelancer.Freelancer;
import Manager.Manager;

public class Main {
    public static void main(String[] args) {
        EcoSystem ecoSystem = new EcoSystem();
        Client client1 = ecoSystem.addClient();
        client1.addBalance(100);
        ecoSystem.createJob(client1);
        Manager manager = ecoSystem.addManager();
        ecoSystem.chooseJobFromPool(manager);
        Freelancer freelancer = ecoSystem.addFreeLancer();
        ecoSystem.assignJobToFreelancer(manager);
        freelancer.submitWork("dawdw.mp4");
        manager.seeBalance();
        freelancer.seeBalance();
    }
}
