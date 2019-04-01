package app.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.tiktok.feed.ListFeedRequest;
import app.tiktok.feed.ListFeedResponse;
import app.tiktok.post.Post;
import app.tiktok.type.FeedType;
import app.tiktok.type.PullType;
import app.tiktok.user.UserProfile;
import app.tiktok.user.UserProfileResponse;
import app.utils.BeanUtil;
import app.utils.StringUtils;
import app.utils.TiktokAPI;
import app.view.FxmlView;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
@Controller
public class ContentAreaController implements Initializable {

	private static final String Stage = null;
	
    @Lazy
    @Autowired
    private StageManager stageManager;
    
	@FXML
	private VBox content_area;
	@FXML
	private HBox menubar;

    @FXML
    private WebView webView;
    
    
    @Autowired
    SpringFXMLLoader spingFXMLLoader; 

    
    @Autowired
    TiktokAPI tiktokAPI;
    
    ListFeedResponse listFeedResponse = null;
 
    
    int videoIndex = 0;
    
    @FXML
    private JFXButton btnPrev;

    @FXML
    private JFXButton btnNext;
    

    @FXML
    private JFXButton btnDownload;
    
    
    @FXML
    private HBox hboxMainContent;
    
    @FXML
    private StackPane stackPanel;
    
    @FXML
    private JFXButton btnHeartWebView;
    
    @FXML
    private JFXButton btnCommentWebView;

	/**
	 * Initializes the controller class.
	 */
	boolean flag = true;
	

    @FXML
    private JFXButton btnUser;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
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

	@FXML
	private void open_sidebar(ActionEvent event) throws IOException {
		BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
		if (flag == true) {
			Parent sidebar = spingFXMLLoader.load(FxmlView.SIDE_BAR.getFxmlFile());
			border_pane.setLeft(sidebar);
			flag = false;
		} else {
			border_pane.setLeft(null);
			flag = true;
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
    void onNext(ActionEvent event) throws Exception {
    	try {
    		setViewDataAndPlay(listFeedResponse.getAweme_list().get(++videoIndex));
		} catch (Exception e) {
			listFeedResponse.getAweme_list().addAll(tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build()).get().getAweme_list());
			setViewDataAndPlay(listFeedResponse.getAweme_list().get(videoIndex));
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
    void onDownload(ActionEvent event)  {
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
    
    
    public StackPane getStackPanel() {
		return stackPanel;
	}

	public void setStackPanel(StackPane stackPanel) {
		this.stackPanel = stackPanel;
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
    
    public void thongbao() {
    	System.out.println("DDaay la thong bao");
    }
    

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
						ContentAreaController controler = BeanUtil.getBean(ContentAreaController.class);
						controler.thongbao();
						StackPane stackPane = controler.getStackPanel();
						stackPane.getChildren().get(0).setVisible(false);
						Platform.runLater(()->{
							if(stackPane.getChildren().size()> 1)
								stackPane.getChildren().remove(1);
							stackPane.getChildren().add(new ResutlSearchController(userprofile.getUser()));
						});
						return null;
					}
				};
			}
		};
		service.restart();
    }
    
    
}
