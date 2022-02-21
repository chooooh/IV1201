package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import se.kth.recruitmentapp.domain.Profile;
import java.math.BigDecimal;
import java.util.List;

/**
 * A form bean for holding competence information of a user
 * This form contains all properties needed for registering user competences.
 * Methods in this from can be invoked by controllers to set and get the data inserted by the user.
 */
@Data
public class CompetenceForm {
    private String selectedCompetence;

    //@NotNull
    //@NotBlank(message = "{applicant-apply.application-yoe.missing}")
    //@Pattern(regexp = "^-(?!0*\\.?0+$)\\d*\\.?\\d+$", message = "{applicant-apply.application-yoe.negative")
    //@Pattern(regexp = "^(\\d)*(\\.)?([0-9]{1})?$", message = "{applicant.apply.application.yoe.decimals")
    private BigDecimal yearsOfExperience;

    private List<String>  toBeRemovedProfileNames;
    private List<Profile>  toBeRemovedProfiles;
    //List of competences
    private List<Competence> competenceList;


    public CompetenceForm(){

    }

}
