/**
 * 
 */
package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.tiktok.post.ListPostsRequest;
import app.tiktok.post.ListPostsResponse;
import app.tiktok.post.Post;
import app.tiktok.user.UserProfile;
import app.utils.StringUtils;
import app.utils.TiktokAPIImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class ResutlSearchController extends VBox implements Initializable{

	@FXML
	private Label lblNameP;

	@FXML
	private ImageView imgAvatar;

	@FXML
	private Label lblTiktokId;

	@FXML
	private JFXButton tbnBack;

	private TiktokAPIImpl tiktokAPI;
	
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
    
    @FXML
    private ScrollPane scrollPaneResultSearch;
    
    private boolean finishedScroll = true;

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
			this.userProfile = userProfile;
			fxmlLoader.load();
			
			scrollPaneResultSearch.setOnScroll(new EventHandler<ScrollEvent>() {

				@Override
				public void handle(ScrollEvent event) {
			    	if(finishedScroll) {
			    		finishedScroll = false;
			    		try {
							loadData(userProfile.getUid());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	}
				}
				
			});
			
			scrollPaneResultSearch.vvalueProperty().addListener(new ChangeListener<Number>() {
		          @Override
		          public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		              if (newValue.intValue() == 1) {
		            	  finishedScroll = true;
		              } else {
		            	  finishedScroll = false;
		              }
		          }
		        });

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
	Service<Void> service = new Service<Void>() {
		
		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					if(listPostResponse.getHas_more() == 0)
						return null;
					 try {
						 System.out.println("Call loadData");
						listPostResponse =	tiktokAPI.listPosts(ListPostsRequest.builder().count("10").max_cursor(listPostResponse.getMax_cursor()).user_id(userId).retry_type("1").build());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("load data thanh cong");
						 if(null != listPostResponse.getAweme_list()) {
							 listPostResponse.getAweme_list().forEach(post->{
								 renderItem(post);
							 });
						 }

					return null;
				}
				@Override
				protected void succeeded() {
						System.out.println("done");
					super.succeeded();
				}
			};
		}
	};
	service.restart();
	
	}
	
	public void renderItem(Post post) {
		Service<Void> service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() {
						ItemVideoController item = new ItemVideoController(post);
						Platform.runLater(()->{
							System.out.println("init xong item");
							resutlViewVideos.getChildren().add(item);
						});

						return null;
					}
					@Override
					protected void succeeded() {
						System.out.println("render thanh cong");
						super.succeeded();
					}
				};
			}
		};
		service.restart();
	}

	@FXML
	void onBack(ActionEvent event) {
		Scene scene = this.getScene();
		StackPane stackPane = (StackPane) scene.lookup("#stackPanel");
		stackPane.getChildren().remove(1);
		stackPane.getChildren().get(0).setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(userProfile != null) {
			this.lblNameP.setText(userProfile.getNickname());
			this.imgAvatar.setImage(new Image(userProfile.getAvatar_thumb().getUrl_list().get(0).replace(".webp", "")));
			this.lblTiktokId.setText(userProfile.getUnique_id());
			this.lblFavoritedCount.setText(StringUtils.convertNumber(userProfile.getTotal_favorited())  + " Trái tim");
			this.lblFollowingCount.setText(StringUtils.convertNumber(userProfile.getFollowing_count())+ " Following");
			this.lblFollowerCount.setText(StringUtils.convertNumber(userProfile.getFollower_count())+ " Người theo dõi");
			Rectangle clip = new Rectangle(this.imgAvatar.getFitWidth(), this.imgAvatar.getFitHeight());
			clip.setArcWidth(this.imgAvatar.getFitWidth());
			clip.setArcHeight(this.imgAvatar.getFitHeight());
			this.imgAvatar.setClip(clip);
			tiktokAPI = new TiktokAPIImpl();
			listPostResponse = ListPostsResponse.builder().aweme_list(new ArrayList<Post>()).max_cursor(0L).has_more(1).build();
			try {
				loadData(userProfile.getUid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
