package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import app.executor.AsyncConfig;
import app.utils.MediaUtils;
import app.utils.TiktokAPI;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AsyncConfig.class})
public class MediaTest {
    
    @Autowired
    private MediaUtils media;

    @Test
    public void dowloadTest() throws Exception {
    	String url ="http://v16.tiktokcdn.com/e495ea0d5a7e47332d14da5aad55f76f/5c929770/video/n/v0102/9d2e11f09a3b4636b887db5c0bc16287/?rc=anE3b2c7ZHFlajMzMzgzM0ApQHRAbzU3NTY1Mzg0NDg0OjM2PDNAKXUpQGczdylAZmh1eXExZnNoaGRmMzRALS5jX2cvbzBsXy0tYC80c3M1byNvIzIyNS40LS4tLS8xLS8tLi9pOmItbyM6YC1vI3BiZnJoXitqdDojNS5e";
        System.out.println("Current Thread in test class " + Thread.currentThread().getName());
        List<CompletableFuture<String>> rs= new ArrayList<>();
      for(int i=0;i<30;i++) {
         rs.add(media.dowload(url, ""+i));
      }
      for(int i=0;i<30;i++) {
          rs.get(i).get();
       }

    }
}
