/**
 * 
 */
package app;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import app.executor.AsyncConfig;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.utils.TiktokAPI;

/**
 * @author tuanhiep225
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {AsyncConfig.class})
public class TikTokAPITest {
    @Autowired
    private TiktokAPI tiktokAPI;
    
    
    @Test
    public void searchUserTest() throws Exception {
      CompletableFuture<UserSearchResponse> usr = tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0).count("10").keyword("boss.sen").build());
      CompletableFuture<UserSearchResponse> usr1 = tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0).count("10").keyword("boss").build());
      CompletableFuture<UserSearchResponse> usr2 = tiktokAPI.searchUsers(UserSearchRequest.builder().type(1).cursor(0).count("10").keyword("bossen").build());
      
      CompletableFuture.allOf(usr,usr1,usr2).join();
      
      
      System.out.println(usr.get().getUser_list().size());
      System.out.println(usr1.get().getUser_list().size());
      System.out.println(usr2.get().getUser_list().size());
		
    }
}
