package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.utils.LayoutService;
import app.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
@Controller
public class ContentAreaController implements Initializable {
    
	@FXML
	private VBox content_area;
	@FXML
	private HBox menubar;
    
    @Autowired
    SpringFXMLLoader spingFXMLLoader; 

    @FXML
    private HBox hboxMainContent;
    
    @Lazy
    @Autowired
    private StageManager stage;
    

	/**
	 * Initializes the controller class.
	 */
	boolean flag = true;
	



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			LayoutService.switchLayout(hboxMainContent, spingFXMLLoader.load("/fxml/Home.fxml"));
		} catch (IOException e) {
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
}
