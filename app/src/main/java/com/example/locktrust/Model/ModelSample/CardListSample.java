package com.example.locktrust.Model.ModelSample;

public class CardListSample {
    public int amount, cardNo;
    public   String HolderName;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getHolderName() {
        return HolderName;
    }

    public void setHolderName(String holderName) {
        HolderName = holderName;
    }

    public CardListSample(int amount, int cardNo, String holderName) {
        this.amount = amount;
        this.cardNo = cardNo;
        HolderName = holderName;
    }
}
