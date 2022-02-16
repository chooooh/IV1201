package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.domain.Profile;

import java.math.BigDecimal;
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
    private BigDecimal yearsOfExperience;

    //List of competences
    private List<Competence> competenceList;
    //
    private List<Profile> profiles;


    public CompetenceForm(){
       profiles = new ArrayList<>();
    }

}
