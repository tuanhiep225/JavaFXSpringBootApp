package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import app.Main;
import app.common.TabName;
import app.tiktok.search.HashtagSearchResponse;
import app.tiktok.search.SearchRequest;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.tiktok.user.UserProfile;
import app.utils.TiktokAPI;
import app.utils.TiktokAPIImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

@Controller
public class SearchController extends VBox implements Initializable{

	UserSearchResponse userSearchResponse = null;

	@Autowired
	TiktokAPI tiktokAPI;

	@FXML
	private JFXTextField textSearch;

	@FXML
	private VBox tab_content_nguoidung;
	
	@FXML
	private VBox tab_content_music;
	
	@FXML
	private VBox tab_content_hashtag;

	@FXML
	private JFXSpinner spiner;
	
	@FXML
	private JFXTabPane tabSearch;
	
	
	private Map<String, String> tabStatus;

	public SearchController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			tiktokAPI = new TiktokAPIImpl();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@FXML
	void onKeyPressed(KeyEvent ke) throws InterruptedException, ExecutionException, Exception {

		if (ke.getCode().equals(KeyCode.ENTER)) {
			switch (tabSearch.getSelectionModel().getSelectedIndex()) {
			case 0:
				searchUsers();
				break;
			case 1:
				searchMusics();
				break;
			case 2:
				searchHashTags();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabStatus = new HashMap<>();
		tabSearch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				switch (arg2.intValue()) {
				case 0:
					try {
						searchUsers();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 1:

					break;
				case 2:
					try {
						searchHashTags();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
				
			}
			
		});
		
	}

	
	public void searchUsers() throws InterruptedException, ExecutionException, Exception {
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {

						if(tabStatus.get(TabName.USER.name()) !=null && textSearch.getText().equals(((String)tabStatus.get(TabName.USER.name())))) {
							
						} else {
							tabStatus.put(TabName.USER.name(), textSearch.getText());
							userSearchResponse = tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0)
									.count("10").keyword(textSearch.getText()).build()).get();
							Platform.runLater(()->{
								if (!tab_content_nguoidung.getChildren().isEmpty()) {
									tab_content_nguoidung.getChildren().setAll(new ArrayList<Node>());
								}
								userSearchResponse.getUser_list().forEach(user -> {
									renderResult(user.getUser_info());
								});
							});
						}
						return null;
					}
				};
			}
		};
		service.restart();
	}
	
	public void renderResult(UserProfile user) {
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
						CustomControl custom = new CustomControl(user);
						Platform.runLater(()->{
							tab_content_nguoidung.getChildren().add(custom);
						});
						return null;
					}
				};
			}
		};
		service.restart();
	}

	public void searchHashTags() throws Exception {
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				// TODO Auto-generated method stub
				return new Task<Void>() {
					
					@Override
					protected Void call() throws Exception {
						if(tabStatus.get(TabName.HASHTAG.name()) !=null && textSearch.getText().equals(((String)tabStatus.get(TabName.HASHTAG.name())))) {
							
						} else {
							tabStatus.put(TabName.HASHTAG.name(), textSearch.getText());
							HashtagSearchResponse response =tiktokAPI.searchHashtags(SearchRequest.builder().count("10").cursor(0).keyword(textSearch.getText()).build());
							
							Platform.runLater(() -> {
								if (!tab_content_hashtag.getChildren().isEmpty()) {
									tab_content_hashtag.getChildren().setAll(new ArrayList<Node>());
								}
								response.getChallenge_list().forEach(hashtag -> {
									HashTagSearchResultController custom = new HashTagSearchResultController(hashtag.getChallenge_info());
									tab_content_hashtag.getChildren().add(custom);
								});

							});
						}
						return null;
					}
				};
			}
		};
		service.restart();
	}
	
	public void searchMusics() throws Exception {
		HashtagSearchResponse response =tiktokAPI.searchHashtags(SearchRequest.builder().count("10").keyword(textSearch.getText()).build());
		Platform.runLater(() -> {
			if (!tab_content_music.getChildren().isEmpty()) {
				tab_content_music.getChildren().setAll(new ArrayList<Node>());
			}
			response.getChallenge_list().forEach(hashtag -> {
				ItemMusicController custom = new ItemMusicController();
				tab_content_music.getChildren().add(custom);
			});
			spiner.setVisible(false);
		});
	}


}
