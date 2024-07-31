package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Goals")
public class Goals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(name = "goalId")
    private int goalId;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ClientInfo clientInfo;

    @Column(name = "goalName")
    private String goalName;


    @Column(name = "startValue")
    private int startValue;

    @Column(name = "currentValue")
    private int currentValue;

    @Column(name = "goalValue")
    private int goalValue;

    @Column(name = "goalDesc")
    private String goalDesc;

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getGoalValue() {
        return goalValue;
    }

    public void setGoalValue(int goalValue) {
        this.goalValue = goalValue;
    }

    public void setGoalDesc(String goalDesc) {
        this.goalDesc = goalDesc;
    }

    public String getGoalDesc() {
        return goalDesc;
    }
}

