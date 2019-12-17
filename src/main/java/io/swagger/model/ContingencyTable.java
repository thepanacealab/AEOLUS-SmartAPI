package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "standard_drug_outcome_contingency_table")
public class ContingencyTable  implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id; // IT AUTO_INCREMENT PRIMARY KEY,

    @Column(name="count_a")
    private int a;

    @Column(name="count_b")
    private int b;

    @Column(name="count_c")
    private int c;

    @Column(name="count_d")
    private int d;

    /**
     * Default Constructor
     */
    public ContingencyTable() {
        super();
    }

    // Generate  Getter/Setter
    // Generate toString()

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "ContingencyTable{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
