package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class CompetenceForm {
    private String selectedCompetence;
    private double yearsOfExperience;

    //List of competences
    private List<Competence> competenceList;
    private List<String> competences;


    public void addCompetence(String name, double years) {
        if(competences == null){
            competences = new ArrayList<>();
        }
       competences.add(name + " Years: " + years);
    }

}
