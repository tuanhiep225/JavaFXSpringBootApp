/**
 * 
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import app.tiktok.category.ChallengeInfo;
import app.utils.BeanUtil;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class HashTagSearchResultController extends HBox implements Initializable{
	
    @FXML
    private Label lblHashtagUserCount;

    @FXML
    private Label lblHashtagName;
    
    @FXML
    private Label lblHashTagViewCount;
	
    @FXML
    private VBox vboxTag;
    
    private ChallengeInfo hashtag;
    
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
			this.hashtag = hashtag;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						viewAllVideoInHashTag();
					}
				}
			}
		});
		
	}
	
	public void viewAllVideoInHashTag() {
		HashTagSearchResultController self = this;
		Service<Void> service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						Platform.runLater(()->{
							ContentAreaController controler = BeanUtil.getBean(ContentAreaController.class);
							controler.thongbao();

							StackPane stackPane = controler.getStackPanel();
							stackPane.getChildren().get(0).setVisible(false);
							stackPane.getChildren().add(new HashTagDetailController(hashtag));
						});
						return null;
					}
				};
			}
		};
		service.restart();
	}

}
