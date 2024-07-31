package com.hungerhealthcoalition.backendhhc.model;


import com.hungerhealthcoalition.backendhhc.util.PasswordHashing;
import jakarta.persistence.*;

@Entity
@Table(name = "ClientInfo")
public class ClientInfo {

    @Id
    @Column(name = "id")
    private int clientID;

    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;

    @Column(name = "clientFirst")
    private String clientFirst;

    @Column(name = "clientLast")
    private String clientLast;

    @Column(name = "foodBox25")
    private Boolean foodBox25;

    @Column(name = "foodBox65")
    private Boolean foodBox65;

    @Column(name = "box25Date")
    private String box25Date;

    @Column(name = "box65Date")
    private String box65Date;
    @Column(name = "medication")
    private Boolean medications;

    @Column(name = "clientPicture")
    private String clientPicture;

    @Column(name = "admin")
    private boolean admin;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientFirst() {
        return clientFirst;
    }

    public void setClientFirst(String clientFirst) {
        this.clientFirst = clientFirst;
    }

    public String getClientLast() {
        return clientLast;
    }

    public void setClientLast(String clientLast) {
        this.clientLast = clientLast;
    }

    public Boolean getFoodBox25() {
        return foodBox25;
    }

    public void setFoodBox25(Boolean foodBox25) {
        this.foodBox25 = foodBox25;
    }

    public Boolean getFoodBox65() {
        return foodBox65;
    }

    public void setFoodBox65(Boolean foodBox65) {
        this.foodBox65 = foodBox65;
    }

    public String getBox25Date() {
        return box25Date;
    }

    public void setBox25Date(String box25Date) {
        this.box25Date = box25Date;
    }

    public String getBox65Date() {
        return box65Date;
    }

    public void setBox65Date(String box65Date) {
        this.box65Date = box65Date;
    }

    public Boolean getMedications() {
        return medications;
    }

    public void setMedications(Boolean medications) {
        this.medications = medications;
    }

    public String getClientPicture() {
        return clientPicture;
    }

    public void setClientPicture(String clientPicture) {
        this.clientPicture = clientPicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordHashing pw = new PasswordHashing(password);
        this.password = pw.getPassword();
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
