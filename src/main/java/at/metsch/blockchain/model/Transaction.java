package at.metsch.blockchain.model;

import java.io.Serializable;

public class Transaction implements Serializable{
    String sender;

    public String getSender() {
        return this.sender;
    }


    @Override
    public String toString() {
        return "{" +
            " sender='" + getSender() + "'" +
            "}";
    }



}