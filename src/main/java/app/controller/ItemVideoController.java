/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import app.tiktok.post.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author jhon
 *
 */
@Controller
public class ItemVideoController extends StackPane {

	@FXML
	private ImageView imgViewResult;

	public ItemVideoController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemVideo.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {

			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public ItemVideoController(Post post) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemVideo.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {

			fxmlLoader.load();
			this.imgViewResult.setImage(new Image(post.getVideo().getCover().getUrl_list().get(0).replace(".webp", "")));
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
