package app.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.tiktok.feed.ListFeedRequest;
import app.tiktok.feed.ListFeedResponse;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.tiktok.type.FeedType;
import app.tiktok.type.PullType;
import app.utils.ITiktokAPI;
import app.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    
    @FXML
    private JFXButton btnClose;
    
    @Autowired
    SpringFXMLLoader spingFXMLLoader; 

    
    @Autowired
    ITiktokAPI tiktokAPI;
    
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

	/**
	 * Initializes the controller class.
	 */
	boolean flag = true;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		try {
		listFeedResponse=	tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build());
		WebEngine engin = webView.getEngine();
		engin.load(listFeedResponse.getAweme_list().get(videoIndex).getVideo().getPlay_addr().getUrl_list().get(0));
		hboxMainContent.getChildren().add(new SearchController());
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
    void close(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void minimize(ActionEvent event) {
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).setIconified(true);
    }
    
    @FXML
    void onPrev(ActionEvent event) {
    	WebEngine engin = webView.getEngine();
    	try {
    		engin.load(listFeedResponse.getAweme_list().get(--videoIndex).getVideo().getPlay_addr().getUrl_list().get(0));
		} catch (Exception e) {
			engin.load(listFeedResponse.getAweme_list().get(0).getVideo().getPlay_addr().getUrl_list().get(0));
		}
    }

    @FXML
    void onNext(ActionEvent event) throws Exception {
    	WebEngine engin = webView.getEngine();
    	try {
    		engin.load(listFeedResponse.getAweme_list().get(++videoIndex).getVideo().getPlay_addr().getUrl_list().get(0));
		} catch (Exception e) {
			listFeedResponse.getAweme_list().addAll(tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build()).getAweme_list());
			engin.load(listFeedResponse.getAweme_list().get(videoIndex).getVideo().getPlay_addr().getUrl_list().get(0));
		}

    }
    
    
    @FXML
    void onReload(ActionEvent event) {
		WebEngine engin = webView.getEngine();
		videoIndex = 0;
    	try {
    		listFeedResponse=	tiktokAPI.listFollowingFeed(ListFeedRequest.builder().count("6").is_cold_start(1).max_cursor(0).pull_type(PullType.LoadMore).type(FeedType.ForYou).build());
    		engin.load(listFeedResponse.getAweme_list().get(videoIndex).getVideo().getPlay_addr().getUrl_list().get(0));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
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

}
