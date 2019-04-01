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

import app.tiktok.category.ChallengeInfo;
import app.tiktok.hashtag.ListPostsInHashtagRequest;
import app.tiktok.hashtag.ListPostsInHashtagResponse;
import app.tiktok.post.Post;
import app.utils.TiktokAPI;
import app.utils.TiktokAPIImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author jhon
 *
 */
@Controller
public class HashTagDetailController extends VBox implements Initializable{

    @FXML
    private Label lblNameHashTag;

    @FXML
    private VBox vboxTag;

    @FXML
    private JFXButton tbnBack;

    @FXML
    private Label lblLuotXem;
    
    private ChallengeInfo hashtag;
    
    private ListPostsInHashtagResponse response;
    
    private TiktokAPI tiktokAPI;
    
    @FXML
    private FlowPane resutlViewVideos;
    
    @FXML
    private ScrollPane scrollPaneResultSearch;
    
    private boolean finishedScroll = true;


	 public HashTagDetailController() {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HashTagDetailController.fxml"));
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
	 
	 public HashTagDetailController(ChallengeInfo hashtag) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HashTagDetailController.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
			this.hashtag = hashtag;
			tiktokAPI = new TiktokAPIImpl();
			try {
				fxmlLoader.load();
				this.lblNameHashTag.setText(hashtag.getCha_name());
				this.lblLuotXem.setText(hashtag.getView_count().toString());
				Rectangle clip = new Rectangle(vboxTag.getPrefWidth(), vboxTag.getPrefHeight());
				clip.setArcWidth(vboxTag.getPrefWidth());
				clip.setArcHeight(vboxTag.getPrefHeight());
				this.vboxTag.setClip(clip);
				
				loadData(hashtag.getCid());

			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
	}
	 
	 
		private void loadData(String cid) {
			Service<Void> service = new Service<Void>() {
				
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {
						@Override
						protected Void call() throws Exception {
							if(response.getHas_more() == 0)
								return null;
							 try {
								 System.out.println("Call loadData");
								 response =	tiktokAPI.listPostsInHashtag(ListPostsInHashtagRequest.builder().count("20").cursor(response.getCursor()).ch_id(cid).query_type(0).type(5).build());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("load data thanh cong");
								 if(null != response.getAweme_list()) {
									 response.getAweme_list().forEach(post->{
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		response =	ListPostsInHashtagResponse.builder().aweme_list(new ArrayList<Post>()).cursor(0).has_more(1).build();
		scrollPaneResultSearch.setOnScroll(new EventHandler<ScrollEvent>() {

			@Override
			public void handle(ScrollEvent event) {
		    	if(finishedScroll) {
		    		finishedScroll = false;
		    		try {
		    			loadData(hashtag.getCid());
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
	}
	
    @FXML
    void onBack(ActionEvent event) {
    	Scene scene = this.getScene();
		StackPane stackPane = (StackPane) scene.lookup("#stackPanel");
		stackPane.getChildren().remove(1);
		stackPane.getChildren().get(0).setVisible(true);
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
}
