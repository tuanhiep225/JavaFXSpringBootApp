/**
 * 
 */
package app.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author jhon
 *
 */
@Service("media")
public class MediaUtils {
	@Async
	public CompletableFuture<String> dowload(String url, String name) throws InterruptedException {
		System.out.println(name);
    	File savedFile = new File("C:/Videos/tiktok/"+ name +".mp4");

    	 
    	if (savedFile != null) {
    	 
        	try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
      			  FileOutputStream fileOutputStream = new FileOutputStream(savedFile.getPath())) {
  			    Thread.sleep(200);
      			    byte dataBuffer[] = new byte[1024];
      			    int bytesRead;
      			    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
      			        fileOutputStream.write(dataBuffer, 0, bytesRead);
      			    }
      			return  CompletableFuture.completedFuture("done");
      			} catch (IOException e) {
      				return  CompletableFuture.completedFuture("not done");
      			}
    	}
    	else {
    		return  CompletableFuture.completedFuture("not done");
    	}
    	
	}
}
