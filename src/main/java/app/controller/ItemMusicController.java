/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

/**
 * @author jhon
 *
 */
@Controller
public class ItemMusicController extends HBox{
	public ItemMusicController(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemMusicController.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
