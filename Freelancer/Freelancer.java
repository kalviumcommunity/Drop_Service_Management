package Freelancer;

import EcoSystem.EcoSystem;
import Job.Job;
import java.util.ArrayList;

public class Freelancer {
    public String name;
    public String email;
    public String contact_number;
    private float balance;
    private ArrayList<Job> completedJobs;
    private Job currentJob;
    public int no_of_completed_jobs = 0;
    public EcoSystem eco_System;
    public static float bonus = 0;

    public static void setBonus(float percentage){
        Freelancer.bonus = percentage;
        System.out.println("!!!Announcement!!! Freelancers are getting " + percentage + "% of bonus on payment for every job they complete.");
    }

    public Freelancer(EcoSystem ecoSystem,String name, String email,String contactNumber) {

        this.name = name;
        this.email = email;
        this.contact_number = contactNumber;
        this.balance = 0;
        this.completedJobs = new ArrayList<>();
        this.currentJob = null;
        this.eco_System = ecoSystem;

        System.out.println("Freelancer details have been recorded.");
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

    public boolean isWorking(){
        return currentJob!=null?true:false;
    }

    public void displayDetails(){
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("Contact Number: " + this.contact_number);
        System.out.println("Total Jobs Completed: " + this.no_of_completed_jobs);
    }

    public void addJob(Job Job){
        this.currentJob = Job;
    }

    public void submitWork(String path){
        currentJob.submitWork(path);
    }
    public void addBalance(float amount){
        this.balance += amount + (amount*(Freelancer.bonus/100));
        System.out.println("Got Paid: $" + (amount + (amount*(Freelancer.bonus/100))));
    }
    public void markAsCompleted(Job job){
        this.completedJobs.add(job);
        this.currentJob = null;
    }

    public void displayFreelancerDetails(){
        System.out.println("Freelancer Name: " + this.name);
        System.out.println("Freelancer Email: " + this.email);
        // System.out.println("Account Balance: $" + this.balance);
        System.out.println("Completed Jobs: " + no_of_completed_jobs);
    }
}
