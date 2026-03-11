package com.example.doctorscheduleappointments;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private final StringProperty time;
    private final StringProperty patientName;
    private final StringProperty type;
    private final StringProperty status;
    private final StringProperty notes;

    public Appointment(String time, String patientName, String type, String status, String notes) {
        this.time = new SimpleStringProperty(time);
        this.patientName = new SimpleStringProperty(patientName);
        this.type = new SimpleStringProperty(type);
        this.status = new SimpleStringProperty(status);
        this.notes = new SimpleStringProperty(notes);
    }

    // Getters for properties (Used by JavaFX TableView)
    public StringProperty timeProperty() { return time; }
    public StringProperty patientNameProperty() { return patientName; }
    public StringProperty typeProperty() { return type; }
    public StringProperty statusProperty() { return status; }
    public StringProperty notesProperty() { return notes; }
}