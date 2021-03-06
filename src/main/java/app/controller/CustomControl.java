/**
 * 
 */
package app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import app.tiktok.post.ListPostsRequest;
import app.tiktok.post.ListPostsResponse;
import app.tiktok.user.UserProfile;
import app.utils.BeanUtil;
import app.utils.MediaUtils;
import app.utils.TiktokAPIImpl;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * @author tuanhiep225
 *
 */
@Controller
public class CustomControl extends HBox{
	@FXML
	private Label tblIdTiktok;

	@FXML
	private Label lblName;

	@FXML
	private ImageView imageView;

	@FXML
	private JFXButton btnDownloadAll;

	private TiktokAPIImpl tiktokAPI;

	private MediaUtils media;

    @FXML
    private JFXSpinner spinnerDownloadAll;

	private UserProfile userProfile;
	
	

	public CustomControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/custom_control.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		tiktokAPI = new TiktokAPIImpl();
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public CustomControl(UserProfile userProfile) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/custom_control.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		tiktokAPI = new TiktokAPIImpl();
		media = (MediaUtils)BeanUtil.getBean(MediaUtils.class);
		try {
			fxmlLoader.load();
			this.setIdTiktok(userProfile.getUnique_id());
			this.setImageView(userProfile.getAvatar_thumb().getUrl_list().get(0).replace(".webp", ""));
			this.setName(userProfile.getNickname());
			this.setUserProfile(userProfile);
			this.btnDownloadAll.setText(userProfile.getAweme_count().toString() + " Video");
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						viewAllVideo();
					}
				}
			}
		});
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public void viewAllVideo() {
		CustomControl self = this;
		Service<Void> service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						Platform.runLater(()->{
							ContentAreaController controler = BeanUtil.getBean(ContentAreaController.class);
							controler.thongbao();
							Scene scene = self.getScene();
							StackPane stackPane = controler.getStackPanel();
							//StackPane stackPane = (StackPane) scene.lookup("#stackPanel");
							stackPane.getChildren().get(0).setVisible(false);
							stackPane.getChildren().add(new ResutlSearchController(userProfile));
							System.out.println("name: " + lblName.getText());
						});
						return null;
					}
				};
			}
		};
		service.restart();
	}

	@FXML
	void onDownloadAll(ActionEvent event) throws InterruptedException, ExecutionException {
		Service<Void>background = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					int a = 0;
					@Override
					protected Void call() throws Exception {
						spinnerDownloadAll.setVisible(true);
						ListPostsResponse listPostResponse =	tiktokAPI.listPosts(ListPostsRequest.builder().count(userProfile.getAweme_count().toString()).user_id(userProfile.getUid()).retry_type("1").build());
						System.out.println("Current Thread in test class " + Thread.currentThread().getName());
						List<CompletableFuture<String>> rs = new ArrayList<>();
						
						if(listPostResponse.getAweme_list() != null) {
							listPostResponse.getAweme_list().forEach(post->{
								try {
									rs.add(media.dowload(post.getVideo().getPlay_addr().getUrl_list().get(0),post.getAweme_id()));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});
							
							rs.forEach(x->{
								try {
									x.get();
									++a;
									updateMessage(String.valueOf(a));
								} catch (InterruptedException | ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});
						}
						return null;
					}
					
					
					@Override
					protected void succeeded() {
						spinnerDownloadAll.setVisible(false);
						super.succeeded();
					}
				};
			}
		};
//		lblName.textProperty().bind(background.messageProperty());
		background.restart();

	}

}