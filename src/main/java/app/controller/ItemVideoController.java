/**
 * 
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.tiktok.feed.ListFeedRequest;
import app.tiktok.post.Post;
import app.tiktok.type.FeedType;
import app.tiktok.type.PullType;
import app.utils.StringUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jhon
 *
 */
@Controller
@Setter
@Getter
public class ItemVideoController extends StackPane implements Initializable {

	@FXML
	private ImageView imgViewResult;

	private Post post;

	@FXML
	private JFXButton btnHeartItemVideo;

	@FXML
	private JFXButton btnCommentItemVideo;

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
		this.post = post;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ItemVideo.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {

			fxmlLoader.load();
			this.imgViewResult
					.setImage(new Image(post.getVideo().getCover().getUrl_list().get(0).replace(".webp", "")));
			this.btnCommentItemVideo.setText(StringUtils.convertNumber(post.getStatistics().getComment_count()));
			this.btnHeartItemVideo.setText(StringUtils.convertNumber(post.getStatistics().getDigg_count()));
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgViewResult.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						playVideo(getPost());
					}
				}
			}
		});

	}

	private void playVideo(Post post) {
		Scene scene = this.getScene();
		WebView webView = (WebView) scene.lookup("#webView");
		JFXButton btnHeartWebView = (JFXButton) scene.lookup("#btnHeartWebView");
		JFXButton btnCommentWebView = (JFXButton) scene.lookup("#btnCommentWebView");
		btnHeartWebView.setText(StringUtils.convertNumber(post.getStatistics().getDigg_count()));
		btnCommentWebView.setText(StringUtils.convertNumber(post.getStatistics().getComment_count()));
		WebEngine engin = webView.getEngine();
		engin.load(post.getVideo().getPlay_addr().getUrl_list().get(0));
	}

}
