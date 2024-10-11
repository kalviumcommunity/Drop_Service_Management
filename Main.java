import Client.Client;
import EcoSystem.EcoSystem;
import Freelancer.Freelancer;
import Manager.Manager;

public class Main {
    public static void main(String[] args) {
        EcoSystem ecoSystem = new EcoSystem();
        Client client1 = ecoSystem.addClient();
        Client client2 = ecoSystem.addClient();
        client1.addBalance(100);
        client2.addBalance(100);
        Manager manager1 = ecoSystem.addManager();
        Manager manager2 = ecoSystem.addManager();
        Freelancer freelancer1 = ecoSystem.addFreeLancer();
        Freelancer freelancer2 = ecoSystem.addFreeLancer();
        
        System.out.println(freelancer1.bonus);
        System.out.println(freelancer2.bonus);
        System.out.println(manager1.bonus);
        System.out.println(manager2.bonus);
        System.out.println(client1.discount);
        System.out.println(client2.discount);
        
        
        
        ecoSystem.createJob(client1);
        ecoSystem.createJob(client2);
        ecoSystem.chooseJobFromPool(manager1);
        ecoSystem.chooseJobFromPool(manager2);
        ecoSystem.assignJobToFreelancer(manager1);
        ecoSystem.assignJobToFreelancer(manager2);
        freelancer1.submitWork("path.mp4");
        freelancer2.submitWork("path.jpg");
    }
}
