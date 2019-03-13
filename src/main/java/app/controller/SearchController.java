package app.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;

import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.utils.ITiktokAPI;
import app.utils.TiktokAPI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

@Controller
public class SearchController extends VBox{
	
    UserSearchResponse userSearchResponse=  null;
    
    @Autowired
    ITiktokAPI tiktokAPI;
    
    @FXML
    private JFXTextField textSearch;
    
    @FXML
    private VBox tab_content_nguoidung;
	
	public SearchController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            tiktokAPI = new TiktokAPI();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
	
  @FXML
  void onKeyPressed(KeyEvent ke) {
      if (ke.getCode().equals(KeyCode.ENTER))
      {
      	
  		try {
				userSearchResponse =	tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0).count("10").keyword(textSearch.getText()).build());
				if(!tab_content_nguoidung.getChildren().isEmpty()) {
					tab_content_nguoidung.getChildren().setAll(new ArrayList<Node>());
				}
      		userSearchResponse.getUser_list().forEach(user->{
      			String image = user.getUser_info().getAvatar_thumb().getUrl_list().get(0);
      			String name = user.getUser_info().getNickname();
      			String tiktokId = user.getUser_info().getUnique_id();
      			CustomControl custom = new CustomControl();
      			custom.setImageView(image);
      			custom.setName(name);
      			custom.setIdTiktok(tiktokId);
      			tab_content_nguoidung.getChildren().add(custom);
      		});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
  }
}
