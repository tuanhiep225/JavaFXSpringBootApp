/**
 * 
 */
package app.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.config.StageManager;
import app.tiktok.feed.ListFeedRequest;
import app.tiktok.feed.ListFeedResponse;
import app.tiktok.post.Post;
import app.tiktok.type.FeedType;
import app.tiktok.type.PullType;
import app.tiktok.user.UserProfileResponse;
import app.utils.BeanUtil;
import app.utils.StringUtils;
import app.utils.TiktokAPI;
import app.utils.TiktokAPIImpl;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * @author jhon
 *
 */
@Controller
public class HomeController implements Initializable{
	

    @FXML
    private JFXButton btnReload;

    @FXML
    private JFXButton btnCommentWebView;

    @FXML
    private JFXButton btnDownload;

    @FXML
    private WebView webView;

    @FXML
    private JFXButton btnPrev;

    @FXML
    private JFXButton btnNext;

    @FXML
    private JFXButton btnHeartWebView;

    @FXML
    private StackPane stackPanel;

    @FXML
    private JFXButton btnUser;
    
    ListFeedResponse listFeedResponse = null;
    
    int videoIndex = 0;
    
    @Autowired
    TiktokAPI tiktokAPI;
    
    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    void viewUser(ActionEvent event) {
		Service<Void> service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {

				return new Task<Void>() {
					UserProfileResponse userprofile = null;
					
					@Override
					protected Void call() throws Exception {
						Post post = listFeedResponse.getAweme_list().get(videoIndex);
						try {
							userprofile = tiktokAPI.getUser(post.getAuthor_user_id());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						stackPanel.getChildren().get(0).setVisible(false);
						Platform.runLater(()->{
							if(stackPanel.getChildren().size()> 1)
								stackPanel.getChildren().remove(1);
							stackPanel.getChildren().add(new ResutlSearchController(userprofile.getUser()));
						});
						return null;
					}
				};
			}
		};
		service.restart();

    }

    @FXML
    void onDownload(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save file");
    	fileChooser.setInitialDirectory(new File("C:/Videos/tiktok"));
    	fileChooser.setInitialFileName(listFeedResponse.getAweme_list().get(videoIndex).getAweme_id()+".mp4");
    	File savedFile = fileChooser.showSaveDialog(stageManager.getPrimaryStage());

    	 
    	if (savedFile != null) {
    	 
        	try (BufferedInputStream in = new BufferedInputStream(new URL(listFeedResponse.getAweme_list().get(videoIndex).getVideo().getPlay_addr().getUrl_list().get(0)).openStream());
      			  FileOutputStream fileOutputStream = new FileOutputStream(savedFile.getPath())) {
      			    byte dataBuffer[] = new byte[1024];
      			    int bytesRead;
      			    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
      			        fileOutputStream.write(dataBuffer, 0, bytesRead);
      			    }
      			} catch (IOException e) {
      			    System.out.println(e.getMessage());
      			}
    	}
    	else {
    		System.out.println("not ok");
    	}
    }

    @FXML
    void onPrev(ActionEvent event) {
    	try {
    		setViewDataAndPlay(listFeedResponse.getAweme_list().get(--videoIndex));
		} catch (Exception e) {
			setViewDataAndPlay(listFeedResponse.getAweme_list().get(0));
		}
    }

    @FXML
    void onReload(ActionEvent event) {
		videoIndex = 0;
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new  Task<Void>() {
					@Override
					protected Void call() throws Exception {
						listFeedResponse=	tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build()).get();
						setViewDataAndPlay(listFeedResponse.getAweme_list().get(videoIndex));
						return null;
					}
				};
			}
		};
		service.restart();

    }

    @FXML
    void onNext(ActionEvent event) throws InterruptedException, ExecutionException, Exception {
    	try {
    		setViewDataAndPlay(listFeedResponse.getAweme_list().get(++videoIndex));
		} catch (Exception e) {
			listFeedResponse.getAweme_list().addAll(tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build()).get().getAweme_list());
			setViewDataAndPlay(listFeedResponse.getAweme_list().get(videoIndex));
		}


    }
    
	public void setViewDataAndPlay(Post post) {
		Service<Void> service = new Service<Void>() {
			
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new  Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
				    	Platform.runLater(()->{
				    		btnHeartWebView.setText(StringUtils.convertNumber(post.getStatistics().getDigg_count()));
					    	btnCommentWebView.setText(StringUtils.convertNumber(post.getStatistics().getComment_count()));
					    	WebEngine engin = webView.getEngine();
					    	engin.load(post.getVideo().getPlay_addr().getUrl_list().get(0));
				    	});
						return null;
					}
				};
			}
		};
		
		service.restart();

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		videoIndex = 0;
		tiktokAPI = new TiktokAPIImpl();
	//	stageManager = BeanUtil.getBean(StageManager.class);
		try {
			listFeedResponse=	tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).min_cursor(-1).pull_type(PullType.LoadMore).type(FeedType.ForYou).build()).get();
			setViewDataAndPlay(listFeedResponse.getAweme_list().get(videoIndex));
			SearchController searchContent = new SearchController();
			stackPanel.getChildren().add(searchContent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}


	public StackPane getStackPanel() {
		return stackPanel;
	}
	

}
