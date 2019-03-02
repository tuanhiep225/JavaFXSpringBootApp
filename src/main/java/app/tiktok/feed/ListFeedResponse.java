/**
 * 
 */
package app.tiktok.feed;

import java.util.List;

import app.tiktok.post.Post;
import app.tiktok.request.ListResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListFeedResponse extends ListResponseData{
	List<Post> aweme_list;
	Integer min_cursor;
	Integer max_cursor;
}
