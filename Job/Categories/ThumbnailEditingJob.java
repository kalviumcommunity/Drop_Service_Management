package Job.Categories;

import Job.Job;

import java.time.LocalDate;

import EcoSystem.EcoSystem;

public class ThumbnailEditingJob extends Job {
    private int numberOfElements;
    private boolean isVFXIncluded;
    public int level = this.calculateJobLevel();

    public ThumbnailEditingJob(String email_id,EcoSystem ecoSystem,String title,String description,LocalDate date_of_completion,float price,int noOfElements,boolean isVFXIncluded) {
        super(email_id,ecoSystem,title,description,date_of_completion,price);
        this.category = "Thumbnail Editing";
        this.numberOfElements = noOfElements;
        this.isVFXIncluded = isVFXIncluded;


        this.ALLOWED_SUBMISSION_TYPES = new String[] {"png", "jpeg", "jpg"};
        this.RECOMMENDED_SKILLS = new String[] {"Image Editing", "Design Principles", "Color Theory"};
    }

    @Override
    public int calculateJobLevel() {
        int jobLevel = 1;

        if (numberOfElements > 3) {
            jobLevel = 2;
        }

        if (numberOfElements > 6) {
            jobLevel = 3;
        }

        if (isVFXIncluded) {
            jobLevel++;
        }

        return Math.min(jobLevel, 4);
    }
}

