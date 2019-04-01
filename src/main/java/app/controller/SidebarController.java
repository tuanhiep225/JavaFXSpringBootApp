package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Controller
public class SidebarController implements Initializable {

    @FXML
    private VBox sidebar;
    
    @FXML
    private ImageView imgLogo;
    
    @Autowired
    SpringFXMLLoader spingFXMLLoader;

    private BorderPane border_pane;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	Scene scene = this.getScene();
//    	Rectangle clip = new Rectangle(this.imgLogo.getFitWidth(), this.imgLogo.getFitHeight());
//		clip.setArcWidth(this.imgLogo.getFitWidth());
//		clip.setArcHeight(this.imgLogo.getFitHeight());
//		this.imgLogo.setClip(clip);
    }
    
    
    
    @FXML
    void onSetting(MouseEvent mouseEvent) {
    	if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
			if (mouseEvent.getClickCount() == 2) {
				 // Parent contentarea = spingFXMLLoader.load(FxmlView.CONTENT.getFxmlFile());
				stageManager.switchScene(FxmlView.LOGIN);
			}
		}
    	
    }
    
}
