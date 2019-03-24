/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import app.tiktok.category.ChallengeInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class HashTagSearchResultController extends HBox{
	
    @FXML
    private Label lblHashtagUserCount;

    @FXML
    private Label lblHashtagName;
    
    @FXML
    private Label lblHashTagViewCount;
	
    @FXML
    private VBox vboxTag;
	
	public HashTagSearchResultController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HashTagSearchResultController.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			Rectangle clip = new Rectangle(vboxTag.getPrefWidth(), vboxTag.getPrefHeight());
			clip.setArcWidth(vboxTag.getPrefWidth());
			clip.setArcHeight(vboxTag.getPrefHeight());
			this.vboxTag.setClip(clip);

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public HashTagSearchResultController(ChallengeInfo hashtag) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HashTagSearchResultController.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			Rectangle clip = new Rectangle(vboxTag.getPrefWidth(), vboxTag.getPrefHeight());
			clip.setArcWidth(vboxTag.getPrefWidth());
			clip.setArcHeight(vboxTag.getPrefHeight());
			this.vboxTag.setClip(clip);
			
			this.lblHashtagName.setText(hashtag.getCha_name());
			this.lblHashtagUserCount.setText(hashtag.getUser_count().toString()+ " user");
			this.lblHashTagViewCount.setText(hashtag.getView_count().toString()+" views");

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
