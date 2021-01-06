package main.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity=Currency.class,optional=false)
    @JoinColumn(name="currency_from", referencedColumnName = "id")
    private Currency curFrom;

    @ManyToOne(targetEntity=Currency.class,optional=false)
    @JoinColumn(name="currency_to", referencedColumnName = "id")
    private Currency curTo;

    @ManyToOne(targetEntity=User.class,optional=false)
    @JoinColumn(name="owner", referencedColumnName = "id")
    private User owner;

    private String status;

    @Column(name = "amount_before")
    private double amountBefore;
    @Column(name = "amount_after")
    private double amountAfter;
    @Column(name = "current_course")
    private double currentCourse;

    public double getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(double amountBefore) {
        this.amountBefore = amountBefore;
    }

    public double getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(double amountAfter) {
        this.amountAfter = amountAfter;
    }

    public double getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(double currentCourse) {
        this.currentCourse = currentCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getCurFrom() {
        return curFrom;
    }

    public void setCurFrom(Currency curFrom) {
        this.curFrom = curFrom;
    }

    public Currency getCurTo() {
        return curTo;
    }

    public void setCurTo(Currency curTo) {
        this.curTo = curTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
