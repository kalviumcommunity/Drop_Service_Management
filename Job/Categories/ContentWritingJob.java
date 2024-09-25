package Job.Categories;

import java.time.LocalDate;

import EcoSystem.EcoSystem;
import Job.Job;

public class ContentWritingJob extends Job {
    private int numberOfPages;
    public int level = this.calculateJobLevel();

    public ContentWritingJob(String email_id,EcoSystem ecoSystem,String title,String description,LocalDate date_of_completion,float price,int numberOfPages) {
        super(email_id,ecoSystem,title,description,date_of_completion,price);
        this.category = "Content Writing";
        this.numberOfPages = numberOfPages;


        this.ALLOWED_SUBMISSION_TYPES = new String[] {"doc", "pdf", "txt"};
        this.RECOMMENDED_SKILLS = new String[] {"SEO", "Grammar", "Creativity"};
    }

    @Override
    public int calculateJobLevel() {
        if (numberOfPages <= 5) {
            return 1; // Basic level
        } else if (numberOfPages <= 20) {
            return 2; // Intermediate level
        } else {
            return 3; // Advanced level
        }
    }

}
