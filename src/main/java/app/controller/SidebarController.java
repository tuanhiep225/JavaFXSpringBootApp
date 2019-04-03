package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.config.SpringFXMLLoader;
import app.utils.LayoutService;
import app.view.FxmlView;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Controller
public class SidebarController implements Initializable {

    @FXML
    private VBox sidebar;
    
    @FXML
    private ImageView imgLogo;
    
    @Autowired
    private SpringFXMLLoader spingFXMLLoader;
    
    public void initialize(URL url, ResourceBundle rb) {
//    	Scene scene = this.getScene();
//    	Rectangle clip = new Rectangle(this.imgLogo.getFitWidth(), this.imgLogo.getFitHeight());
//		clip.setArcWidth(this.imgLogo.getFitWidth());
//		clip.setArcHeight(this.imgLogo.getFitHeight());
//		this.imgLogo.setClip(clip);
    }
    
    
    
    @FXML
    void onSetting(MouseEvent event) throws IOException {
    	switchScene(event, new SettingController());
    	
    }
    

    @FXML
    void onHome(MouseEvent event) throws IOException {
    	switchScene(event, ((Parent) spingFXMLLoader.load("/fxml/Home.fxml")));
    }
    
    public void switchScene(MouseEvent event, Node node) throws IOException {
			Scene scene = ((Node) event.getSource()).getScene();
				LayoutService.switchLayout(scene,node);
    }
    
}
