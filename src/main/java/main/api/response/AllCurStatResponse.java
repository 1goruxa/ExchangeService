package main.api.response;

import javax.persistence.Column;

public class AllCurStatResponse {

    @Column(name = "name")
    private String name;
    @Column(name="amount")
    private Double amount;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
