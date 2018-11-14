package at.metsch.blockchain.block;

import java.io.Serializable;

public class Transaction implements Serializable{
    private String sender;
    private String recipient;

    @Override
    public String toString() {
        return "{" +
            " sender='" + getSender() + "'" +
            ", recipient='" + getRecipient() + "'" +
            "}";
    }

    public Transaction(String sender,String recipient){
        this.sender=sender;
        this.recipient=recipient;
    }
    public String getSender() {
        return this.sender;
    }

    public String getRecipient() {
        return this.recipient;
    }



}