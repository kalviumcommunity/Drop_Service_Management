package Manager;

import EcoSystem.EcoSystem;
import Job.Job;
import java.util.ArrayList;

public class Manager {
    public String name;
    public String email;
    public String contactNumber;
    private float balance;
    private float marginPercentage;
    private ArrayList<Job> pendingJobs;
    private ArrayList<Job> assignedJobs;
    private ArrayList<Job> completedJobs;
    public int no_of_completed_jobs;
    public EcoSystem eco_system;
    public static float bonus = 0;

    public static void setBonus(float percentage){
        Manager.bonus = percentage;
        System.out.println("!!!Announcement!!! Managers are getting " + percentage + "% of bonus on commision for every job they manage and complete.");
    }

    // Constructor that prompts the user for manager's info
    public Manager(EcoSystem ecoSystem,String name,String email,String contactNumber,float marginPercentage) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.marginPercentage = marginPercentage;
        this.no_of_completed_jobs = 0;
        this.pendingJobs = new ArrayList<>();
        this.assignedJobs = new ArrayList<>();
        this.completedJobs = new ArrayList<>();
        this.eco_system = ecoSystem;
        this.balance = 0;

        System.out.println("Manager details have been recorded.");
    }

    public void withdrawBalance(float amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of $" + amount + " was successful.");
            System.out.println("Remaining balance: $" + this.balance);
        } else {
            System.out.println("Insufficient balance. Available balance: $" + this.balance);
        }
    }
    public float getBalance(){
        return this.balance;
    }


    public void addBalance(float amount){
        this.balance += amount + (amount*(Manager.bonus/100));
        System.out.println("Got Paid: $" + (amount + (amount*(Manager.bonus/100))));
    }
    public float getMarginPercentage(){
        return this.marginPercentage;
    }

    public void markAsCompleted(Job job){
        this.assignedJobs.remove(job);
        this.completedJobs.add(job);
        this.no_of_completed_jobs = completedJobs.size();
    }

    public void displayManagerDetails(){
        System.out.println("Manager Name: " + this.name);
        System.out.println("Manager Email: " + this.email);
        // System.out.println("Account Balance: $" + this.balance);
        System.out.println("Completed Jobs: " + no_of_completed_jobs);
    }

    public boolean isPendingJobsEmpty(){
        return this.pendingJobs.isEmpty();
    }

    public void displayAllPendingJobs(){
        for (int i = 0; i < pendingJobs.size(); i++) {
            System.out.println("Job #" + (i + 1));
            this.pendingJobs.get(i).displayJobDetails();
            System.out.println("----------------------");
        }
    }
    public int numberOfPendingJobs(){
        return this.pendingJobs.size();
    }
    public Job chooseFromPendingJobs(int index){
        return this.pendingJobs.get(index);
    }
    public void removeFromPendingAndAddToAssignedJob(Job job){
        this.pendingJobs.remove(job);
        this.assignedJobs.add(job);
    }
    public void addToPendingJob(Job job){
        this.pendingJobs.add(job);
    }

}