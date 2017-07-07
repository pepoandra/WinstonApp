package com.boii.seb.winstonapp;

import java.io.Serializable;

/**
 * Created by seb on 27/06/17.
 */

public class cashOut implements Serializable {

    private double startingMoney, Total, Alcohol, cardTotal;


    public cashOut(double startingMoney, double total, double alcohol, double cardTotal) {
        this.startingMoney = startingMoney;
        Total = total;
        Alcohol = alcohol;
        this.cardTotal = cardTotal;
    }

    public double getStartingMoney() {
        return startingMoney;
    }

    public void setStartingMoney(double startingMoney) {
        this.startingMoney = startingMoney;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public double getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(double alcohol) {
        Alcohol = alcohol;
    }

    public double getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(double cardTotal) {
        this.cardTotal = cardTotal;
    }
}
