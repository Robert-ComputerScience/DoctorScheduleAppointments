package com.example.doctorscheduleappointments;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    // --- Table View and Columns ---
    @FXML private TableView<Schedule> appointmentTable;
    @FXML private TableColumn<Schedule, String> colTime;
    @FXML private TableColumn<Schedule, String> colPatient;
    @FXML private TableColumn<Schedule, String> colType;
    @FXML private TableColumn<Schedule, String> colStatus;
    @FXML private TableColumn<Schedule, String> colNotes;

    // --- Input Fields ---
    @FXML private TextField txtName;
    @FXML private TextField txtContact;
    @FXML private TextArea txtReason;
    @FXML private ComboBox<String> comboTime;
    @FXML private ComboBox<String> comboDuration;
    @FXML private ComboBox<String> comboType;
    @FXML private Button btnBook;

    // --- Data List ---
    private final ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
        setupInputs();
        setupActions();
    }

    private void setupTable() {
        // Bind table columns to Appointment model properties
        colTime.setCellValueFactory(cell -> cell.getValue().timeProperty());
        colPatient.setCellValueFactory(cell -> cell.getValue().patientNameProperty());
        colType.setCellValueFactory(cell -> cell.getValue().typeProperty());
        colStatus.setCellValueFactory(cell -> cell.getValue().statusProperty());
        colNotes.setCellValueFactory(cell -> cell.getValue().notesProperty());

        // Load dummy data
        scheduleList.add(new Schedule("09:00 AM", "Sarah Smith", "Check-up", "Confirmed", "Annual Physical"));
        scheduleList.add(new Schedule("10:30 AM", "Mike Johnson", "Surgery", "Pending", "Wisdom Tooth"));

        appointmentTable.setItems(scheduleList);
    }

    private void setupInputs() {
        // Populate ComboBoxes
        comboTime.getItems().addAll("09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "02:00 PM");
        comboDuration.getItems().addAll("15 min", "30 min", "1 hr");
        comboType.getItems().addAll("Check-up", "Consultation", "Emergency", "Follow-up");
    }

    // Change this line:
    @FXML private Button btnBookSchedule; // Matches fx:id="btnBookSchedule"

    // And update the reference in setupActions:
    private void setupActions() {
        btnBookSchedule.setOnAction(event -> handleBookAppointment());
    }

    private void handleBookAppointment() {
        // 1. Validate Input
        if (txtName.getText().isEmpty() || comboTime.getValue() == null) {
            showAlert("Validation Error", "Please enter at least a Patient Name and Time.");
            return;
        }

        // 2. Create new Appointment object
        String time = comboTime.getValue();
        String name = txtName.getText();
        String type = comboType.getValue() != null ? comboType.getValue() : "General";
        String notes = txtReason.getText();

        Schedule newAppt = new Schedule(time, name, type, "Scheduled", notes);

        // 3. Add to list (Table updates automatically)
        scheduleList.add(newAppt);

        // 4. Clear form
        clearForm();
    }

    private void clearForm() {
        txtName.clear();
        txtContact.clear();
        txtReason.clear();
        comboTime.getSelectionModel().clearSelection();
        comboType.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // 1. Add the @FXML reference for the top button
    @FXML private Button btnReschedule;

    // 2. Add this method to handle the Reschedule click
    @FXML
    private void handleReschedule() {
        // Get the selected appointment from the table
        Schedule selectedAppt = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppt == null) {
            showAlert("No Selection", "Please select an appointment from the table to reschedule.");
            return;
        }

        // Populate the form fields on the right with the selected data
        txtName.setText(selectedAppt.getPatientName());
        comboTime.setValue(selectedAppt.getTime());
        comboType.setValue(selectedAppt.getType());
        txtReason.setText(selectedAppt.getNotes());

        // Optional: Remove the old entry so it can be replaced by the "Book" action,
        // or keep it and let the user manually delete the old one.
        // scheduleList.remove(selectedAppt);

        // UI Feedback: Change button text to indicate we are updating
        btnBookSchedule.setText("Update Schedule");
    }
}