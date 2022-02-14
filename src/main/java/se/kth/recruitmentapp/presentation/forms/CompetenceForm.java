package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import se.kth.recruitmentapp.domain.Competence;

import java.util.HashMap;
import java.util.List;

@Data
public class CompetenceForm {
    private String selectedCompetence;
    private double yearsOfExperience;

    private List<Competence> competenceList;
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

    public HashMap<String, String> getCompetences(){
        return competences;
    }

    public List<Competence> getCompetenceList(){
        return competenceList;
    }

}
