package se.kth.recruitmentapp.domain;

import java.math.BigDecimal;

public class CompAndYears {
    private String competence;
    private BigDecimal yoe;

    public CompAndYears(){

    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public BigDecimal getYoe() {
        return yoe;
    }

    public void setYoe(BigDecimal yoe) {
        this.yoe = yoe;
    }
}
