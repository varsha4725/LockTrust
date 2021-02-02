package com.example.locktrust.Model.ModelSample;

public class InvoiceListSample {
    private   String MailId ,Amount,date;

    public String getMailId() {
        return MailId;
    }

    public void setMailId(String mailId) {
        MailId = mailId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InvoiceListSample(String mailId, String amount, String date) {
        MailId = mailId;
        Amount = amount;
        this.date = date;
    }
}
