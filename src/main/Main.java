package main;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import pane.RootPane;

import java.util.Optional;

/**
 * Main class for the game application.
 * This class extends the Application class from JavaFX.
 */
public class Main extends Application {

    /**
     * The start method is the main entry point for all JavaFX applications.
     * It is called after the init() method has returned, and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if any error occurs during the execution of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the title of the window
        primaryStage.setTitle("Untitled-JAVAFX-Game");

        // Override the default close request action
        primaryStage.setOnCloseRequest(event -> {
            // Consume the event to prevent the window from closing
            event.consume();
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to exit?");

            // Show the dialog and wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // If the user confirms, close the application
                primaryStage.close();
            }
        });

        // Create a new game panel
//        GamePanel gamePanel = new GamePanel();
//        gamePanel.setUpGame();

        // Create a new root pane and add the game panel to it
        Pane root = new RootPane();
//        root.getChildren().add(gamePanel);

        // Create a new scene with the root pane and set it on the stage
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);

        // Prevent the window from being resized
        primaryStage.setResizable(false);

        // Center the window on the screen
        primaryStage.centerOnScreen();

        // Show the window
//        primaryStage.show();

        // Start the game thread
//        gamePanel.startGameThread();

        Scene scene = new Scene(root,GamePanel.getInstance().getScreenWidth(),GamePanel.getInstance().getScreenHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is the entry point for all Java applications.
     *
     * @param args the command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}