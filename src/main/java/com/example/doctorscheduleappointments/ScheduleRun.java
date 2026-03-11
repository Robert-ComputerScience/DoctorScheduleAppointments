package com.example.doctorscheduleappointments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleRun extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleRun.class.getResource("DoctorSchedule.fxml"));

        // Note: For full screen, the initial dimensions (320, 240) will be ignored
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Doctor Schedule");
        stage.setScene(scene);

        // --- Full Screen Options ---

        // Option A: True Full Screen (Kiosk mode, hides taskbar)
        stage.setFullScreen(true);

        // Option B: Maximized (Shows taskbar, fills available space)
        // stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}