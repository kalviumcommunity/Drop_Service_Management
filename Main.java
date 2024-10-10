import Client.Client;
import EcoSystem.EcoSystem;
import Freelancer.Freelancer;
import Manager.Manager;

public class Main {
    public static void main(String[] args) {
        EcoSystem ecoSystem = new EcoSystem();
        Client client1 = ecoSystem.addClient();
        client1.addBalance(100);
        Manager manager1 = ecoSystem.addManager();
        Freelancer freelancer1 = ecoSystem.addFreeLancer();
        ecoSystem.createJob(client1);
        ecoSystem.createJob(client1);
        ecoSystem.displayAllClients();
        ecoSystem.displayAvailableJobs();
        ecoSystem.displayAllManagers();
        ecoSystem.displayAllFreelancers();
    
    }
}
