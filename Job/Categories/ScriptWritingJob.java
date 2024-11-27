package Job.Categories;

import EcoSystem.EcoSystem;
import java.time.LocalDate;

public class ScriptWritingJob extends ContentWritingJob {
    private String genre;
    
    public ScriptWritingJob(String email_id,EcoSystem ecoSystem,String title,String description,LocalDate date_of_completion,float price,int numberOfPages, String genre) {
     super(email_id, ecoSystem, title, description, date_of_completion, price,numberOfPages);
     this.genre = genre;
     this.RECOMMENDED_SKILLS = new String[] {"Story Telling", "Grammar", "Creativity"};
     this.level = calculateJobLevel();
    }
    
    @Override
    public int calculateJobLevel() {
        // Base job level calculation from parent
        int baseLevel = super.calculateJobLevel();

        // Adjust for genre if applicable
        if (this.genre != null && this.genre.equalsIgnoreCase("non fiction")) {
            return baseLevel + 1;
        } else {
            return baseLevel;
        }
    }


    
}
