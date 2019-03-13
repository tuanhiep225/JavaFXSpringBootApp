/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class ResutlSearchController extends VBox{
	
    @FXML
    private Label lblNameP;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private Label lblTiktokId;
    
    public ResutlSearchController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ResultSearch.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {

            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    

	public ResutlSearchController(String lblName, Image imgAvatar, String lblTiktokId) {

		
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ResultSearch.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
    		this.lblNameP.setText(lblName);
    		this.imgAvatar.setImage(imgAvatar);
    		this.lblTiktokId.setText(lblTiktokId);
    		Rectangle clip = new Rectangle(
    				this.imgAvatar.getFitWidth(), this.imgAvatar.getFitHeight()
                );
                clip.setArcWidth(this.imgAvatar.getFitWidth());
                clip.setArcHeight(this.imgAvatar.getFitHeight());
                this.imgAvatar.setClip(clip);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}



	public Label getLblName() {
		return lblNameP;
	}

	public void setLblName(String lblName) {
		this.lblNameP = new Label(lblName);
	}

	public ImageView getImgAvatar() {
		return imgAvatar;
	}

	public void setImgAvatar(String url) {
		this.imgAvatar = new ImageView(url);
	}

	public Label getLblTiktokId() {
		return lblTiktokId;
	}

	public void setLblTiktokId(String lblTiktokId) {
		this.lblTiktokId = new Label(lblTiktokId);
	}
    
    
    
}
