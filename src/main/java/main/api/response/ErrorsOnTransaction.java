package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorsOnTransaction {
    private String emptyFields;
    private String notDigits;
    private String sameCurrency;

    public String getEmptyFields() {
        return emptyFields;
    }

    public void setEmptyFields(String emptyFields) {
        this.emptyFields = emptyFields;
    }

    public String getNotDigits() {
        return notDigits;
    }

    public void setNotDigits(String notDigits) {
        this.notDigits = notDigits;
    }

    public String getSameCurrency() {
        return sameCurrency;
    }

    public void setSameCurrency(String sameCurrency) {
        this.sameCurrency = sameCurrency;
    }
}
