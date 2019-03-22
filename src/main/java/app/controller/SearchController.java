package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import app.tiktok.search.HashtagSearchResponse;
import app.tiktok.search.SearchRequest;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
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
	private JFXSpinner spiner;
	
	@FXML
	private JFXTabPane tabSearch;

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
	void onKeyPressed(KeyEvent ke) {

		if (ke.getCode().equals(KeyCode.ENTER)) {
			spiner.setVisible(true);
			Service<Void> service = new Service<Void>() {

				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {

						@Override
						protected Void call() throws InterruptedException, ExecutionException, Exception {
							
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
							
							return null;
						}

					};
				}
			};
			service.restart();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabSearch.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				switch (arg2.intValue()) {
				case 0:
					System.out.println("Tab user");
					break;
				case 1:
					System.out.println("Tab music");
					break;
				case 2:
					System.out.println("Tab hashtag");
					break;
				default:
					break;
				}
				
			}
			
		});
		
	}

	
	public void searchUsers() throws InterruptedException, ExecutionException, Exception {
		userSearchResponse = tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0)
				.count("10").keyword(textSearch.getText()).build()).get();
		
		Platform.runLater(() -> {
			if (!tab_content_nguoidung.getChildren().isEmpty()) {
				tab_content_nguoidung.getChildren().setAll(new ArrayList<Node>());
			}
			userSearchResponse.getUser_list().forEach(user -> {
				CustomControl custom = new CustomControl(user.getUser_info());
				tab_content_nguoidung.getChildren().add(custom);

			});
			spiner.setVisible(false);
		});
	}

	public void searchHashTags() throws Exception {
		HashtagSearchResponse response =tiktokAPI.searchHashtags(SearchRequest.builder().count("10").keyword(textSearch.getText()).build());
		
		Platform.runLater(() -> {
			if (!tab_content_nguoidung.getChildren().isEmpty()) {
				tab_content_nguoidung.getChildren().setAll(new ArrayList<Node>());
			}
			userSearchResponse.getUser_list().forEach(user -> {
				CustomControl custom = new CustomControl(user.getUser_info());
				tab_content_nguoidung.getChildren().add(custom);

			});
			spiner.setVisible(false);
		});
	}
	
	public void searchMusics() {
		System.out.println("musics");
	}


}
