package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import app.Main;
import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.view.FxmlView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Benny In
 */
@Controller
public class MainController implements Initializable {

    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @Autowired
    SpringFXMLLoader spingFXMLLoader;
	
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private BorderPane border_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDrageable();
        try {
            Parent contentarea = spingFXMLLoader.load(FxmlView.CONTENT.getFxmlFile());
            border_pane.setCenter(contentarea);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void makeStageDrageable() {
        border_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        border_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	stageManager.getPrimaryStage().setX(event.getScreenX() - xOffset);
            	stageManager.getPrimaryStage().setY(event.getScreenY() - yOffset);
            	stageManager.getPrimaryStage().setOpacity(0.7f);
            }
        });
        border_pane.setOnDragDone((e) -> {
        	stageManager.getPrimaryStage().setOpacity(1.0f);
        });
        border_pane.setOnMouseReleased((e) -> {
        	stageManager.getPrimaryStage().setOpacity(1.0f);
        });

    }
}
