package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.RootLayoutController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
//    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Laser control for PicoQuant CPDL-S-F v0.1");
        initRootLayout();
    }

    /**
     *  Initialization of the RootLayout
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));            // depending on where MainApp is, this path can be "../view/RootLayout.fxml", or "view/RootLayout.fxml", or "/view/RootLayout.fxml",
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            RootLayoutController controller = (RootLayoutController) loader.getController();        // getting access to controller
            primaryStage.setOnCloseRequest(e -> controller.workingLaserAlert(e));                  // call controller's function when press close window button

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
