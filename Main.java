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
        Freelancer freelancer2 = ecoSystem.addFreeLancer();
        
        
        
        Client.setDiscount(10);
        Freelancer.setBonus(10);
        Manager.setBonus(10);
        
        ecoSystem.createJob(client1);
        ecoSystem.createJob(client1);
        ecoSystem.chooseJobFromPool(manager1);
        ecoSystem.chooseJobFromPool(manager1);
        ecoSystem.assignJobToFreelancer(manager1);
        ecoSystem.assignJobToFreelancer(manager1);
        freelancer1.submitWork("path.pdf");
        freelancer2.submitWork("path.txt");
    }
}
