package app;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import app.config.CustomAsyncExceptionHandler;
import app.config.SpringFXMLLoader;
import app.config.StageManager;
import app.logging.ExceptionWriter;
import app.view.FxmlView;

@SpringBootApplication
@EnableAsync
public class Main extends Application{

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    /**
     * Useful to override this method by sub-classes wishing to change the first
     * Scene to be displayed on startup. Example: Functional tests on main
     * window.
     */
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }

    
    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }
    
    @Autowired 
    SpringFXMLLoader springFXMLLoader;

    /**
     * Useful when dumping stack trace to a string for logging.
     * @return ExceptionWriter contains logging utility methods
     */
    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }
    
    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }


}
