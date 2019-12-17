package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "standard_drug_outcome_statistics")
public class ROR implements Serializable{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id; // IT AUTO_INCREMENT PRIMARY KEY,

    @Column(name="ror")
    private int ror;

    @Column(name="case_count")
    private int casecount;

    @Column(name = "ror_95_percent_upper_confidence_limit")
    private int upperCI;

    @Column(name = "ror_95_percent_lower_confidence_limit")
    private int lowerCI;

    /**
     * Default Constructor
     */
    public ROR() {
        super();
    }

    public int getRor() {
        return ror;
    }

    public void setRor(int ror) {
        this.ror = ror;
    }

    public int getCasecount() {
        return casecount;
    }

    public void setCasecount(int casecount) {
        this.casecount = casecount;
    }

    public int getUpperCI() {
        return upperCI;
    }

    public void setUpperCI(int upperCI) {
        this.upperCI = upperCI;
    }

    public int getLowerCI() {
        return lowerCI;
    }

    public void setLowerCI(int lowerCI) {
        this.lowerCI = lowerCI;
    }

    @Override
    public String toString() {
        return "ror{" +
                "ror=" + ror +
                ", casecount=" + casecount +
                ", upperCI=" + upperCI +
                ", lowerCI=" + lowerCI +
                '}';
    }
}

