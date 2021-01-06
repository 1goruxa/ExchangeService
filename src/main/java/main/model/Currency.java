package main.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double course;

    @OneToMany(mappedBy = "curFrom", fetch=FetchType.EAGER)
    private Set<Transaction> transactionFromSet;

    @OneToMany(mappedBy = "curTo", fetch=FetchType.EAGER)
    private Set<Transaction> transactionToSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }
}