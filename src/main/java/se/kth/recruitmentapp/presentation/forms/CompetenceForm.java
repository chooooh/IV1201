package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;

import java.util.HashMap;
import java.util.List;

@Data
public class CompetenceForm {
    private String selectedCompetence;
    private double yearsOfExperience;

    //List of competences
    private List<Competence> competenceList;
    //User selected competence and number of years
    private HashMap<String, String> competences;

    public CompetenceForm(){}

    public CompetenceForm(List<Competence> competenceList){
        this.competenceList = competenceList;
    }

    public CompetenceForm(HashMap<String, String> competences, List<Competence> competenceList){
        this.competenceList = competenceList;
        this.competences = competences;

    }

    public void addCompetence(String name, String years) {
        if(competences == null){
            competences = new HashMap<>();
        }
        competences.put(name, years);
    }

}
