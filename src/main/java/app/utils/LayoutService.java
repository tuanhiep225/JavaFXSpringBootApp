/**
 * 
 */
package app.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import app.config.SpringFXMLLoader;
import app.view.FxmlView;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

/**
 * @author jhon
 *
 */
public class LayoutService {

    private static SpringFXMLLoader spingFXMLLoader;
    
    public static void switchLayout(Scene scene,FxmlView view) throws IOException {
    	spingFXMLLoader = BeanUtil.getBean(SpringFXMLLoader.class);
		HBox hboxMainContent = (HBox) scene.lookup("#hboxMainContent");
		hboxMainContent.getChildren().clear();
		hboxMainContent.getChildren().add(spingFXMLLoader.load(view.getFxmlFile()));
    }
    
    public static void switchLayout(Scene scene,Node node) throws IOException {
    	spingFXMLLoader = BeanUtil.getBean(SpringFXMLLoader.class);
		HBox hboxMainContent = (HBox) scene.lookup("#hboxMainContent");
		hboxMainContent.getChildren().clear();
		hboxMainContent.getChildren().add(node);
    }
    public static void switchLayout(Node nodeSource,Node node) throws IOException {
//    	Service<Void> service = new Service<Void>() {
//			
//			@Override
//			protected Task<Void> createTask() {
//				return new Task<Void>() {
//					
//					@Override
//					protected Void call(){
//				    	spingFXMLLoader = BeanUtil.getBean(SpringFXMLLoader.class);
//						HBox hboxMainContent = (HBox) nodeSource;
//						hboxMainContent.getChildren().clear();
//						Platform.runLater(()->{
//							hboxMainContent.getChildren().add(node);
//						});
//						return null;
//					}
//				};
//			}
//		};
//		service.restart();
    	
    	spingFXMLLoader = BeanUtil.getBean(SpringFXMLLoader.class);
		HBox hboxMainContent = (HBox) nodeSource;
		hboxMainContent.getChildren().clear();
		hboxMainContent.getChildren().add(node);

    }
}
