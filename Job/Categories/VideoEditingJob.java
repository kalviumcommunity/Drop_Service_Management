package Job.Categories;
import java.time.LocalDate;

import EcoSystem.EcoSystem;
import Job.*;

public class VideoEditingJob extends Job {
    
    private int rawDuration; // in minutes
    private int postEditingDuration; // in minutes
    private boolean isTransitionIncluded;
    private boolean isColorGradingIncluded;
    private boolean isVFXIncluded;
    public int level = this.calculateJobLevel();

    public VideoEditingJob(String email_id, EcoSystem ecoSystem,String title,String description,LocalDate date_of_completion,float price,int rawDuration,int postEditingDuration,boolean isTransitionIncluded, boolean isColorGradingIncluded,boolean isVFXIncluded) {


        super(email_id,ecoSystem,title,description,date_of_completion,price);
        this.category = "Video Editing";
        this.rawDuration = rawDuration;
        this.postEditingDuration = postEditingDuration;
        this.isTransitionIncluded= isTransitionIncluded;
        this.isColorGradingIncluded = isColorGradingIncluded;
        this.isVFXIncluded = isVFXIncluded;
        
        this.ALLOWED_SUBMISSION_TYPES = new String[] {"mp4", "mov", "avi"};
        this.RECOMMENDED_SKILLS = new String[] {"Video Editing", "Color Correction", "Audio Synchronization"};
    }

    @Override
    public int calculateJobLevel() {
        int jobLevel = 1;

        if (rawDuration > 60 || postEditingDuration > 30) {
            jobLevel = 2;
        }

        if (isTransitionIncluded) {
            jobLevel++;
        }

        if (isColorGradingIncluded) {
            jobLevel++;
        }

        if (isVFXIncluded) {
            jobLevel++;
        }

        return Math.min(jobLevel, 5); // Maximum level set to 5
    }
}

