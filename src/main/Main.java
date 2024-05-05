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
 * Main class for the application.
 * This class extends the JavaFX Application class and overrides the start method.
 */
public class Main extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched from a web page.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the title of the window
        primaryStage.setTitle("Untitled-JAVAFX-Game");

        // Add a confirmation dialog when the user tries to close the window
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                primaryStage.close();
            }
        });

        // Create the root pane
        Pane root = new RootPane();

        // Set the window to be non-resizable
        primaryStage.setResizable(false);

        // Center the window on the screen
        primaryStage.centerOnScreen();

        // Create the scene with the root pane and the dimensions from the game panel
        Scene scene = new Scene(root,GamePanel.getInstance().getScreenWidth(),GamePanel.getInstance().getScreenHeight());

        // Set the scene on the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method for the application.
     * This method is not required for JavaFX applications when the JAR file for the
     * application is created with the JavaFX Packager tool, which embeds the JavaFX Launcher in the JAR file.
     * However, it is useful for platforms like IDEs that do not provide full support for JavaFX.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}