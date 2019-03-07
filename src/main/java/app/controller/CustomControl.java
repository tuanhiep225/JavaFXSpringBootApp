/**
 * 
 */
package app.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author tuanhiep225
 *
 */
public class CustomControl extends HBox {
    @FXML
    private Label tblIdTiktok;

    @FXML
    private Label lblName;

    @FXML
    private ImageView imageView;
    
    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

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
    
    
}