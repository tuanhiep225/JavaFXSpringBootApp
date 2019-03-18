package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

@Controller
public class SidebarController implements Initializable {

    @FXML
    private VBox sidebar;
    
    @FXML
    private ImageView imgLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	Rectangle clip = new Rectangle(this.imgLogo.getFitWidth(), this.imgLogo.getFitHeight());
//		clip.setArcWidth(this.imgLogo.getFitWidth());
//		clip.setArcHeight(this.imgLogo.getFitHeight());
//		this.imgLogo.setClip(clip);
    }    
    
}
