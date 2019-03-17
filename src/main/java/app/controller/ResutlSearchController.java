/**
 * 
 */
package app.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.tiktok.post.ListPostsRequest;
import app.tiktok.post.ListPostsResponse;
import app.tiktok.user.UserProfile;
import app.utils.TiktokAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class ResutlSearchController extends VBox {

	@FXML
	private Label lblNameP;

	@FXML
	private ImageView imgAvatar;

	@FXML
	private Label lblTiktokId;

	@FXML
	private JFXButton tbnBack;

	private TiktokAPI tiktokAPI;
	
	private ListPostsResponse listPostResponse = null;
	
	private UserProfile userProfile;
	
    @FXML
    private Label lblFavoritedCount;
	
    @FXML
    private Label lblFollowerCount;

    @FXML
    private Label lblFollowingCount;
    
    @FXML
    private FlowPane  resutlViewVideos;

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

	public ResutlSearchController(UserProfile userProfile) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ResultSearch.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			this.lblNameP.setText(userProfile.getNickname());
			this.imgAvatar.setImage(new Image(userProfile.getAvatar_thumb().getUrl_list().get(0).replace(".webp", "")));
			this.lblTiktokId.setText(userProfile.getUnique_id());
			this.lblFavoritedCount.setText(userProfile.getTotal_favorited().toString() + " Trái tim");
			this.lblFollowingCount.setText(userProfile.getFollowing_count().toString()+ " Following");
			this.lblFollowerCount.setText(userProfile.getFollower_count().toString()+ " Người theo dõi");
			Rectangle clip = new Rectangle(this.imgAvatar.getFitWidth(), this.imgAvatar.getFitHeight());
			clip.setArcWidth(this.imgAvatar.getFitWidth());
			clip.setArcHeight(this.imgAvatar.getFitHeight());
			this.imgAvatar.setClip(clip);
			tiktokAPI = new TiktokAPI();
			loadData(userProfile.getUid());
		} catch (Exception exception) {
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
	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	private void loadData(String userId) throws Exception {
	 listPostResponse =	tiktokAPI.listPosts(ListPostsRequest.builder().count("10").user_id(userId).retry_type("1").build());
	 listPostResponse.getAweme_list().forEach(post->{
			resutlViewVideos.getChildren().add(new ItemVideoController(post));
	 });
	}

	@FXML
	void onBack(ActionEvent event) {
		Scene scene = this.getScene();
		StackPane stackPane = (StackPane) scene.lookup("#stackPanel");
		stackPane.getChildren().remove(1);
		stackPane.getChildren().get(0).setVisible(true);
	}


}
