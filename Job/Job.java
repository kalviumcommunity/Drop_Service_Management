package Job;
import java.time.LocalDate;

import Client.Client;
import EcoSystem.EcoSystem;
import Freelancer.Freelancer;
import Manager.Manager;


abstract public class Job{
    public String title;
    public String description;
    public LocalDate date_of_completion;
    public float price;
    public String client_email_id;
    public String freelancer_email_id;
    public String drop_servicer_email_id;
    public boolean isDropServicerAssigned;
    public boolean isFreelancerAssigned;
    public boolean isCompleted;
    public boolean isPaid;
    public boolean isExpired;
    public String[] skills_required;
    public String category;
    public EcoSystem eco_system;

    protected String[] ALLOWED_SUBMISSION_TYPES;
    protected String[] RECOMMENDED_SKILLS;

    public Job(String email_id,EcoSystem ecoSystem,String title,String description,LocalDate date_of_completion,float price){
        this.title = title;
        this.description = description;
        this.date_of_completion = date_of_completion;
        this.price = price;
        this.isDropServicerAssigned = false;
        this.isFreelancerAssigned = false;
        this.isCompleted = false;
        this.isPaid = false;
        this.isExpired = false;
        this.freelancer_email_id = null;
        this.drop_servicer_email_id = null;
        this.eco_system = ecoSystem;
    }

    abstract public int calculateJobLevel();


    public void assignFreelancer(String freelancerEmail) {
        if (!this.isFreelancerAssigned && this.isDropServicerAssigned) {
            this.freelancer_email_id = freelancerEmail;
            this.isFreelancerAssigned = true;
            System.out.println("Freelancer assigned successfully.");
        } else {
            System.out.println("Freelancer is already assigned or DropServicer not assigned yet.");
        }
    }

    public void assignDropServicer(String dropServicerEmail) {
        if (!this.isDropServicerAssigned && !this.isFreelancerAssigned) {
            this.drop_servicer_email_id = dropServicerEmail;
            this.isDropServicerAssigned = true;
            System.out.println("Drop Servicer assigned successfully.");
        } else {
            System.out.println("Drop Servicer is already assigned.");
        }
    }

    public void markAsCompleted() {
        if (this.isFreelancerAssigned && !this.isCompleted) {
            this.isCompleted = true;
            System.out.println("Job marked as completed.");
        } else {
            System.out.println("Job cannot be completed. Either it's already completed or no freelancer is assigned.");
        }
    }

    public void markAsPaid() {
        if (!this.isPaid && this.isCompleted) {
            this.isPaid = true;
            System.out.println("Payment successful. Job is marked as paid.");
        } else {
            System.out.println("Cannot mark as paid. Ensure the job is completed.");
        }
    }

    public void checkExpiration() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(this.date_of_completion)) {
            this.isExpired = true;
            System.out.println("The job has expired.");
        } else {
            this.isExpired = false;
            System.out.println("The job is still active.");
        }
    }

    public void displayJobDetails() {
        System.out.println("Job Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Description: " + this.category);
        System.out.println("Date of Completion: " + this.date_of_completion);
        System.out.println("Price: $" + this.price);
        System.out.println("Client Email ID: " + this.client_email_id);
        System.out.println("Freelancer Email ID: " + (this.isFreelancerAssigned ? this.freelancer_email_id : "Not Assigned"));
        System.out.println("Drop Servicer Email ID: " + (this.isDropServicerAssigned ? this.drop_servicer_email_id : "Not Assigned"));
        System.out.println("Is Completed: " + this.isCompleted);
        System.out.println("Is Assigned: " + this.isDropServicerAssigned);
        System.out.println("Is Paid: " + this.isPaid);
        System.out.println("Is Expired: " + this.isExpired);
    }

    public void updateDescription(String newDescription) {
        this.description = newDescription;
        System.out.println("Job description updated successfully.");
    }


    public boolean submitWork(String filePath) {
        if(isDropServicerAssigned && isFreelancerAssigned){
            String fileExtension = getFileExtension(filePath);
            if (isExtensionValidForCategory(fileExtension)) {
                System.out.println("Work submitted successfully: " + filePath);
                markAsCompleted();
                processPayment();
                return true;
            } else {
                System.out.println("Invalid file extension. Allowed types are: " + String.join(", ", ALLOWED_SUBMISSION_TYPES));
                return false;
            }
        }else{
            System.out.println("No Freelancer is assigned yet to complete.");
            return false;
        }
        }

    // Helper method to get the file extension
    private String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // No extension found
        }
        return filePath.substring(lastIndexOfDot + 1);
    }

    // Helper method to validate the file extension based on allowed submission types
    private boolean isExtensionValidForCategory(String extension) {
        for (String allowedExtension : ALLOWED_SUBMISSION_TYPES) {
            if (allowedExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private boolean processPayment(){
        Client requiredClient = null;
        Manager requiredManager = null;
        Freelancer requiredFreelancer = null;
        for(Client client : this.eco_system.client_pool){
            if(client.email == this.client_email_id){
                requiredClient = client;
                break;
            }
        }
        for(Freelancer freelancer : this.eco_system.freelancer_pool){
            if(freelancer.email == this.freelancer_email_id){
                requiredFreelancer = freelancer;
                break;
            }
        }
        for(Manager manager : this.eco_system.manager_pool){
            if(manager.email == this.drop_servicer_email_id){
                requiredManager = manager;
                break;
            }
        }
        System.out.println(requiredClient.name);
        System.out.println(requiredManager.name);
        System.out.println(requiredFreelancer.name);

        if(requiredClient != null && requiredFreelancer != null && requiredManager != null){
            requiredClient.deductBalance(this.price);
            requiredManager.addBalance(this.price * (requiredManager.getMarginPercentage()/100));
            requiredManager.markAsCompleted(this);
            requiredFreelancer.addBalance(this.price - (this.price * (requiredManager.getMarginPercentage()/100)));
            requiredFreelancer.markAsCompleted(this);
            markAsPaid();
            return true;
        }else{
            System.out.println("Something went wrong while processing payment.");
            return false;
        }

    }
    
}

