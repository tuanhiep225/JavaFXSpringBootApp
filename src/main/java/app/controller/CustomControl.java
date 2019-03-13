/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import app.utils.TiktokAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class CustomControl extends HBox {
    @FXML
    private Label tblIdTiktok;

    @FXML
    private Label lblName;

    @FXML
    private ImageView imageView;
    
    @FXML
    private JFXButton btnViewAllVideo;
    

    private TiktokAPI tiktokAPI;

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        tiktokAPI = new TiktokAPI();
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	public String getIdTiktok() {
		return tblIdTiktok.getText();
	}

	public void setIdTiktok(String idTiktok) {
		this.tblIdTiktok.setText(idTiktok);
	}

	public String getName() {
		return lblName.getText();
	}

	public void setName(String name) {
		this.lblName.setText(name);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(String url) {
		imageView.setImage(new Image(url));
	}
    
    @FXML
    void viewAllVideo(ActionEvent event) throws Exception {
    	
    	Scene scene = this.getScene();
    	VBox node = (VBox) scene.lookup("#contentSearch");
    	node.setVisible(false);
    	HBox node1 = (HBox) scene.lookup("#hboxMainContent");
    	node1.getChildren().add(new ResutlSearchController(lblName.getText(),imageView.getImage(),tblIdTiktok.getText()));
    	System.out.println("name: "+ lblName.getText());

    }
    
    
}