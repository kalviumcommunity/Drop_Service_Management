package Client;

import Job.Job;

import java.util.ArrayList;

import EcoSystem.EcoSystem;

public class Client {
    public String name;
    public String email;
    private float balance;
    private ArrayList<Job> postedJobs;
    public EcoSystem eco_system;

    // Constructor to initialize client details and starting balance
    public Client(EcoSystem ecoSystem,String name, String email) {
        this.name = name;
        this.email = email;
        this.balance = 0;
        this.postedJobs = new ArrayList<Job>();
        this.eco_system = ecoSystem;
    }

    // Getter for balance
    public float getBalance() {
        return this.balance;
    }

    public void addBalance(float amount) {
        this.balance += amount;
    }

    public void addToPostedJob(Job job){
        this.postedJobs.add(job);
    }
   

    // Check if the client can afford the job
    public boolean canAffordJob(float price) {

        float unpaidJobTotal = 0.0f;

        for (Job job : postedJobs) {
            if (!job.isPaid) {
                unpaidJobTotal += job.price;
            }
        }

        if (balance >= (price + unpaidJobTotal)) {
            return true;
        } else {
            return false;
        }
    }

    // Display the client's details and posted jobs
    public void displayClientDetails() {
        System.out.println("Client Name: " + this.name);
        System.out.println("Client Email: " + this.email);
        // System.out.println("Account Balance: $" + this.balance);
        System.out.println("Posted Jobs: " + postedJobs.size());
    }

    public void deductBalance(float amount){
        this.balance -= amount;
    }
}
