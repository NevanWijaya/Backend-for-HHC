package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Events")
public class Events {
    @Id
    @Column(name = "eventId")
    private int eventId;

    @Column(name = "eventName")
    private String eventName;

    @Column(name = "eventDate")
    private String eventDate;

    @Column(name = "eventAddress")
    private String eventAddress;

    @Column(name = "eventDescription")
    private String eventDescription;

    @Column(name = "eventLimit")
    private int eventLimit;

    @Column(name = "eventPicture")
    private String eventPicture;

    @Column(name = "eventAdditionalPersonLimit")
    private int eventAdditionalPersonLimit;

    @Column(name = "guestAvailability ")
    private boolean guestAvailability;

    @Column(name = "eventAgeLimit")
    private int eventAgeLimit;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventLimit() {
        return eventLimit;
    }

    public void setEventLimit(int eventLimit) {
        this.eventLimit = eventLimit;
    }

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public int getEventAdditionalPersonLimit() {
        return eventAdditionalPersonLimit;
    }

    public void setEventAdditionalPersonLimit(int eventAdditionalPersonLimit) {
        this.eventAdditionalPersonLimit = eventAdditionalPersonLimit;
    }

    public int getEventAgeLimit() {
        return eventAgeLimit;
    }

    public void setEventAgeLimit(int eventAgeLimit) {
        this.eventAgeLimit = eventAgeLimit;
    }

    public boolean getGuestAvailability() {
        return guestAvailability;
    }

    public void setGuestAvailability(boolean guestAvailability) {
        this.guestAvailability = guestAvailability;
    }


}
