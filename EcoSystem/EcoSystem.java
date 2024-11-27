package EcoSystem;

import Client.Client;
import Freelancer.Freelancer;
import Job.Categories.ContentWritingJob;
import Job.Categories.ScriptWritingJob;
import Job.Categories.ThumbnailEditingJob;
import Job.Categories.VideoEditingJob;
import Job.Job;
import Manager.Manager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EcoSystem {

    Scanner sc = new Scanner(System.in);

    public ArrayList<Job> job_pool = new ArrayList<Job>();
    public ArrayList<Client> client_pool = new ArrayList<Client>();
    public ArrayList<Manager> manager_pool = new ArrayList<Manager>();
    public ArrayList<Freelancer> freelancer_pool = new ArrayList<Freelancer>();

    public Client addClient() {
        System.out.print("Please Provide Client's name: ");
        String name = sc.nextLine();
        String email;
        while (true) {
            System.out.print("Enter Clients's Email: ");
            String res_email = sc.nextLine();
            if (res_email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                boolean isPresent = false;
                for (Client client : this.client_pool) {
                    if (client.email == res_email) {
                        System.out.println("Email already taken.");
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    email = res_email;
                    break;
                }
            } else {
                System.out.println("Invalid Email");
            }
        }
        Client client = new Client(this, name, email);
        this.client_pool.add(client);
        System.out.println("Client added to the Ecosystem.");
        return client;
    }

    public Manager addManager() {
        System.out.print("Enter Manager's Name: ");
        String name = sc.nextLine();

        String email;
        while (true) {
            System.out.print("Enter Manager's Email: ");
            String res_email = sc.nextLine();
            if (res_email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                boolean isPresent = false;
                for (Manager manager : this.manager_pool) {
                    if (manager.email == res_email) {
                        System.out.println("Email already taken.");
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    email = res_email;
                    break;
                }
            } else {
                System.out.println("Invalid Email");
            }
        }

        System.out.print("Enter Manager's Contact Number: ");
        String contactNumber = sc.nextLine();

        System.out.print("Enter Manager's Margin Percentage (in %): ");
        float marginPercentage = sc.nextFloat();
        Manager manager = new Manager(this, name, email, contactNumber, marginPercentage);
        this.manager_pool.add(manager);
        System.out.println(" added to the Ecosystem.");
        sc.nextLine();
        return manager;
    }

    public Freelancer addFreeLancer() {
        System.out.print("Enter Freelancer's Name: ");
        String name = sc.nextLine();
        String email;
        while (true) {
            System.out.print("Enter Freelancers's Email: ");
            String res_email = sc.nextLine();
            if (res_email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                boolean isPresent = false;
                for (Freelancer freelancer : this.freelancer_pool) {
                    if (freelancer.email == res_email) {
                        System.out.println("Email already taken.");
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    email = res_email;
                    break;
                }
            } else {
                System.out.println("Invalid Email");
            }
        }
        System.out.print("Enter Freelancer's Contact Number: ");
        String contact_number = sc.nextLine();
        Freelancer freelancer = new Freelancer(this, name, email, contact_number);
        this.freelancer_pool.add(freelancer);
        System.out.println("Freelancer added to the Ecosystem.");
        return freelancer;
    }

    public void assignJobToFreelancer(Manager manager) {
        // sc.nextLine();

        if (this.freelancer_pool.isEmpty()) {
            System.out.println("No Freelancer available in the Ecosystem at this point.");
            return;
        }
        if (manager.isPendingJobsEmpty()) {
            System.out.println("You have no Job to assign.");
            return;
        }
        Job chosenJob;

        while (true) {
            System.out.println("Pending Jobs:");
            manager.displayAllPendingJobs();
            System.out.print("Enter the number of the job you'd like to take: ");
            int jobChoice = sc.nextInt() - 1;

            if (jobChoice >= 0 && jobChoice < manager.numberOfPendingJobs()) {
                chosenJob = manager.chooseFromPendingJobs(jobChoice);
                break;
            } else {
                System.out.println("Invalid choice.");
                return;
            }
        }

        ArrayList<Freelancer> free_freelancers = new ArrayList<Freelancer>();

        for (Freelancer freelancer : this.freelancer_pool) {
            if (!freelancer.isWorking()) {
                free_freelancers.add(freelancer);
            }
        }

        // Display available Freelancers
        while (true) {
            System.out.println("Available Freelancers in the Ecosystem:");
            for (int i = 0; i < free_freelancers.size(); i++) {
                System.out.println("Freelancer #" + (i + 1));
                free_freelancers.get(i).displayDetails();
                System.out.println("----------------------");
            }

            System.out.print("Enter the number of the Freelancer you'd like to assign the work: ");
            int freelancerChoice = sc.nextInt() - 1;

            if (freelancerChoice >= 0 && freelancerChoice < free_freelancers.size()) {
                Freelancer chosenFreelancer = free_freelancers.get(freelancerChoice);
                chosenJob.assignFreelancer(chosenFreelancer.email);
                chosenFreelancer.addJob(chosenJob);
                manager.removeFromPendingAndAddToAssignedJob(chosenJob);
                System.out.println("Job assigned to Freelancer: " + chosenFreelancer.name);
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

    }

    public void chooseJobFromPool(Manager manager) {
        sc.nextLine();
        if (this.job_pool.isEmpty()) {
            System.out.println("No jobs available in the pool.");
            return;
        }
        ArrayList<Job> unassignedJobs = new ArrayList<>();

        // Iterate through the job pool to find unassigned jobs
        for (Job job : this.job_pool) {
            if (!job.isDropServicerAssigned) { // Assuming isAssigned() checks if the job is assigned
                unassignedJobs.add(job);
            }
        }

        // Display available jobs
        while (true) {
            System.out.println("Available Jobs in the Pool:");
            for (int i = 0; i < unassignedJobs.size(); i++) {
                System.out.println("Job #" + (i + 1));
                unassignedJobs.get(i).displayJobDetails();
                System.out.println("----------------------");
            }

            System.out.print("Enter the number of the job you'd like to take: ");
            int jobChoice = sc.nextInt() - 1;

            if (jobChoice >= 0 && jobChoice < unassignedJobs.size()) {
                Job chosenJob = unassignedJobs.get(jobChoice);
                manager.addToPendingJob(chosenJob);; // Add to manager's pending jobs
                chosenJob.assignDropServicer(manager.email);
                System.out.println("Job assigned to Manager: " + manager.name);
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void displayAvailableJobs() {
        if (this.job_pool.isEmpty()) {
            System.out.println("No jobs available in the pool.");
            return;
        }

        System.out.println("Available Jobs in the Pool:");
        for (int i = 0; i < this.job_pool.size(); i++) {
            System.out.println("Job #" + (i + 1));
            this.job_pool.get(i).displayJobDetails();
            System.out.println("----------------------");
        }
    }

    public void createJob(Client client) {

        System.out.println("Choose Job Category:");
        System.out.println("1. Video Editing");
        System.out.println("2. Thumbnail Editing");
        System.out.println("3. Content Writing");
        System.out.println("4. Script Writing");

        int choice = sc.nextInt();
        sc.nextLine();

        Job newJob = null;

        System.out.println("Creating a job...");
        System.out.print("Give a title to the Job: ");
        String title = sc.nextLine();
        System.out.println();

        System.out.print("Add Description for the job: ");
        String description = sc.nextLine();
        System.out.println();

        System.out.print("Provide the Date to Complete the job(yyyy-mm-dd): ");
        String dateString = sc.nextLine();
        LocalDate date_of_completion = LocalDate.parse(dateString);
        System.out.println();

        System.out.print("Set the price for the Job in USD(ex: 5.5): ");
        float price = sc.nextFloat();
        System.out.println();

        switch (choice) {
            case 1:
                System.out.println("Let's get information about the Video Editing job.");
                System.out.print("Give the duration of the raw data provided(in mins): ");
                int rawDuration = sc.nextInt();

                System.out.print("Give the expected output duration of the final video(in mins): ");
                int postEditingDuration = sc.nextInt();

                System.out.print("Is there any transition throughout the expected video(Y/N): ");
                String response = sc.next();
                boolean isTransitionIncluded = response.toLowerCase() == "y" ? true : false;

                System.out.print("Do you want color grading in the expected video(Y/N): ");
                response = sc.next();
                boolean isColorGradingIncluded = response.toLowerCase() == "y" ? true : false;

                System.out.print("Do you want VFX in the expected video(Y/N): ");
                response = sc.next();
                boolean isVFXIncluded = response.toLowerCase() == "y" ? true : false;
                newJob = new VideoEditingJob(client.email, this, title, description, date_of_completion, price,
                        rawDuration, postEditingDuration, isTransitionIncluded, isColorGradingIncluded, isVFXIncluded);
                break;

            case 2:
                System.out.println("Let's get information about the Thumbnail Editing job.");
                System.out.print("How many elements will be included in the thumbnail? ");
                int numberOfElements = sc.nextInt();

                System.out.print("Do you want VFX in the thumbnail (Y/N): ");
                response = sc.next();
                isVFXIncluded = response.toLowerCase().equals("y");
                System.out.println("Creating a Thumbnail Editing job...");

                newJob = new ThumbnailEditingJob(client.email, this, title, description, date_of_completion, price, numberOfElements, isVFXIncluded);
                break;

            case 3:
                System.out.println("Let's get information about the Content Writing job.");
                System.out.print("How many pages are required for the content? ");
                int numberOfPages = sc.nextInt();
                System.out.println("Creating a Content Writing job...");
                newJob = new ContentWritingJob(client.email, this, title, description, date_of_completion, price, numberOfPages);
                break;

            case 4:
                System.out.println("Let's get information about the Script Writing job.");
                System.out.print("How many pages are required for the content? ");
                numberOfPages = sc.nextInt();
                sc.nextLine();
                System.out.print("What is the genre of the video you want script of? ");
                String genre = sc.nextLine();
                System.out.println("Creating a Script Writing job...");
                newJob = new ScriptWritingJob(client.email, this, title, description, date_of_completion, price, numberOfPages, genre);
                break;

            default:
                System.out.println("Invalid choice. Job creation aborted.");
                return;
        }
        if (newJob != null) {
            if (client.canAffordJob(newJob.price)) {
                newJob.client_email_id = client.email;

                // Add the new job to client's posted jobs list
                client.addToPostedJob(newJob);

                // Add the new job to the EcoSystem job pool
                this.job_pool.add(newJob);

                System.out.println("Job created, posted successfully, and added to the job pool!");
            } else {
                System.out.println(
                        "Insufficient balance to create this job. You may have Jobs that are not completed yet.");
            }
        }
    }

    public void displayAllClients() {
        if (this.client_pool.isEmpty()) {
            System.out.println("No Clients are there in the Eco System.");
            return;
        }

        System.out.println("All Clients in the Eco System:");
        for (int i = 0; i < this.client_pool.size(); i++) {
            System.out.println("Client #" + (i + 1));
            this.client_pool.get(i).displayClientDetails();
            System.out.println("----------------------");
        }
    }

    public void displayAllManagers() {
        if (this.manager_pool.isEmpty()) {
            System.out.println("No Managers are there in the Eco System.");
            return;
        }

        System.out.println("All Managers in the Eco System:");
        for (int i = 0; i < this.manager_pool.size(); i++) {
            System.out.println("Manager #" + (i + 1));
            this.manager_pool.get(i).displayManagerDetails();
            System.out.println("----------------------");
        }
    }

    public void displayAllFreelancers() {
        if (this.freelancer_pool.isEmpty()) {
            System.out.println("No Freelancers are there in the Eco System.");
            return;
        }

        System.out.println("All Freelancers in the Eco System:");
        for (int i = 0; i < this.freelancer_pool.size(); i++) {
            System.out.println("Freelancer #" + (i + 1));
            this.freelancer_pool.get(i).displayFreelancerDetails();
            System.out.println("----------------------");
        }
    }

}
