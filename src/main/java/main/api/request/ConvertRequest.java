package main.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConvertRequest {
    private int id;

    @JsonProperty("firstname")
    private String name;
    private String surname;

    @JsonProperty("curfrom")
    private String curFrom;

    @JsonProperty("curto")
    private String curTo;

    private String amount;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCurFrom() {
        return curFrom;
    }

    public void setCurFrom(String curFrom) {
        this.curFrom = curFrom;
    }

    public String getCurTo() {
        return curTo;
    }

    public void setCurTo(String curTo) {
        this.curTo = curTo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
