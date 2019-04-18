package app.controller;


import java.io.IOException;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import app.config.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Ram Alapure
 * @since 05-04-2017
 */

@Controller
public class LoginController{

	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;

    
    @Lazy
    @Autowired
    private StageManager stageManager;
        
	@FXML
    private void login(ActionEvent event) throws IOException{
        // declaration and instantiation of objects/variables
//    	System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
	//	comment the above 2 lines and uncomment below 2 lines to use Chrome
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jhon\\Downloads\\WCare Latest Version - Private Key\\chromedriver\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		
		
		// options.addExtensions(new File("C:\\Users\\jhon\\Downloads\\WCare Latest Version - Private Key\\extension\\Proxy-Switcher-Latest-Version.crx"));
		options.addArguments("user-data-dir=D:\\Profile_Chrome\\tuanhiep");
	//	options.addArguments("--start-maximized");
		
	//	options.addArguments("--proxy-server=socks4://77.121.177.139:3629"); // good
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.facebook.com/866189283553800/videos/2014974228799250");
//		driver.manage().addCookie(new Cookie("sb","z994XAQx5AeCpj18N2bxf2iV"));
//		driver.manage().addCookie(new Cookie("datr","z994XCrAL-mrwG7eEGgUr9Pp"));
//		driver.manage().addCookie(new Cookie( "locale","vi_VN"));
//		driver.manage().addCookie(new Cookie("c_user","100006572504076"));
//		driver.manage().addCookie(new Cookie("xs","38%3AJkAMkNqQrCvBRg%3A2%3A1555397904%3A13491%3A6383"));
//		driver.manage().addCookie(new Cookie("fr","0oN36fjHMjdxObIEM.AWXHdPcp0Q3XkBNXpLoNVEeULLI.BceNmg.ez.Fy3.0.0.BcuD_R.AWVFX2i1"));
//		driver.manage().addCookie(new Cookie("m_pixel_ratio","6"));
//		driver.manage().addCookie(new Cookie("wd","1920x969"));
//		driver.manage().addCookie(new Cookie("act","1555579471491%2F92"));
//		driver.manage().addCookie(new Cookie("spin","r.1000617806_b.trunk_t.1555579864_s.1_v.2_"));
//		driver.manage().addCookie(new Cookie("presence","EDvF3EtimeF1555579967EuserFA21B06572504076A2EstateFDt3F_5b_5dEutc3F1555578896182G555579967984CEchFDp_5f1B06572504076F99CC"));
		
	//	driver.get("https://facebook.com");
       
        //close Fire fox
		
     //   driver.close();
    }
	
	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}


}
