package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConvertResponse {
    private boolean status;
    private double amount;
    private int id;
    private ErrorsOnTransaction errorsOnTransaction;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ErrorsOnTransaction getErrorsOnTransaction() {
        return errorsOnTransaction;
    }

    public void setErrorsOnTransaction(ErrorsOnTransaction errorsOnTransaction) {
        this.errorsOnTransaction = errorsOnTransaction;
    }
}
