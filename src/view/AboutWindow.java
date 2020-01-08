package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class AboutWindow {

    public static void display() {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinWidth(450);
        window.setMinHeight(200);

        Text textVersion = new Text("Laser control for PicoQuant CPDL-S-F v0.1");
        Text textAuthor = new Text("Author :");
        Text textAuthorName = new Text("Andrei Fedotov");
        Text textHomePage = new Text("Home page :");
        Hyperlink hyperlinkHomePage = new Hyperlink("https://github.com/andrei-fedotov");
        Button buttonOk = new Button("Ok");


        hyperlinkHomePage.setBorder(Border.EMPTY);
        hyperlinkHomePage.setPadding(new Insets(0, 0, 0, 0));
        hyperlinkHomePage.setOnAction(e -> {
            if(Desktop.isDesktopSupported())
            {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/andrei-fedotov"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });


        buttonOk.setPrefWidth(75);
        buttonOk.setOnAction(e -> window.close());

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Arranging all the nodes in the grid
        gridPane.add(textVersion, 0, 0, 2,1);
        gridPane.add(textAuthor, 0, 2);
        gridPane.add(textAuthorName, 1, 2);
        gridPane.add(textHomePage, 0, 3);
        gridPane.add(hyperlinkHomePage, 1, 3);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().add(buttonOk);
        gridPane.add(hbox,0,6,2,1);

        // Creating a scene object
        Scene scene = new Scene(gridPane, 300, 200);

        //Display window and wait for it to be closed before returning
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }

}