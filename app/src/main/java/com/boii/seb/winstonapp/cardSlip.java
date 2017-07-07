package com.boii.seb.winstonapp;

import java.io.Serializable;

/**
 * Created by seb on 22/06/17.
 */

public class cardSlip implements Serializable{

    private static final long serialVersionUID = 7526471155622776147L;

    private int id;
        private String type;
        private int auth;
        private double amount;

        public cardSlip(){}

        public cardSlip(String type, int auth, double amount) {
            super();
            this.type = type;
            this.auth = auth;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
        public String toString() {
            return  type + "   #" + auth
                    + "     $" + amount ;
        }
    }




