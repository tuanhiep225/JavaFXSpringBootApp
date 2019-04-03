/**
 * 
 */
package app.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import app.config.StageManager;
import app.utils.BeanUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * @author jhon
 *
 */
@Controller
public class SettingController extends VBox{
    @Lazy
    @Autowired
    private StageManager stageManager;
    
	public SettingController() {
		{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SettingController.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
			try {
				fxmlLoader.load();
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}
	}
	
    @FXML
    void onOpenFolder(ActionEvent event) {

    	stageManager = BeanUtil.getBean(StageManager.class);
    	final String FOLDER ="C:\\Videos\\tiktok\\ok";
    	File newFolder = new File(FOLDER);
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save file");
    	fileChooser.setInitialDirectory(newFolder);
        boolean created =  newFolder.mkdirs();
    	File savedFile = fileChooser.showSaveDialog(stageManager.getPrimaryStage());
        

         
        if(created)
            System.out.println("Folder was created !");
        else
            System.out.println("Unable to create folder");
    }
}
