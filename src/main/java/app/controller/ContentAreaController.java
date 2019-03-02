package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;

import app.config.SpringFXMLLoader;
import app.view.FxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
@Controller
public class ContentAreaController implements Initializable {

	private static final String Stage = null;
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

	/**
	 * Initializes the controller class.
	 */
	boolean flag = true;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		WebEngine engin = webView.getEngine();
		engin.load("http://v16-tiktokcdn-com.akamaized.net/13feff5c8414b252858b57a1d520c201/5c746592/video/n/v0102/b819954e274f4dbe9819ea2e2452db5f/?rc=MzZ3c2p0Onk4azMzZjgzM0ApQHRAbzg1NDM1NDg0NDwzMzg1PDNAKXUpQGczdylAZmh1eXExZnNoaGRmMzRANnJkZmQxLTUzXy0tYy80c3M1byNvIzE1NTI2LS4tLTAtLi8tLi9pOmItbyM6YC1vI3BiZnJoXitqdDojNS5e");
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
    


}
