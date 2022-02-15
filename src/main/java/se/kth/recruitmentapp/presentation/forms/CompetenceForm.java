package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;
import java.util.ArrayList;
import java.util.List;

/**
 * A form bean for holding competence information of a user
 * This form contains all properties needed for registering user competences.
 * Methods in this from can be invoked by controllers to set and get the data inserted by the user.
 */
@Data
public class CompetenceForm {
    private String selectedCompetence;
    private double yearsOfExperience;

    //List of competences
    private List<Competence> competenceList;
    private List<String> competences;

    /**
     * Method that creates a string of the specified name and years,
     * it adds the created string to a list of such strings called competences.
     *
     * @param name ,the name of the competence
     * @param years ,years of experience
     */
    public void addCompetence(String name, double years) {
        if(competences == null){
            competences = new ArrayList<>();
        }
       competences.add(name + " Years: " + years);
    }

}
