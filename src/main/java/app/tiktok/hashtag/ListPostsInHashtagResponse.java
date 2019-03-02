/**
 * 
 */
package app.tiktok.hashtag;

import java.util.List;

import app.tiktok.post.Post;
import app.tiktok.request.ListResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ListPostsInHashtagResponse extends ListResponseData{
	List<Post> aweme_list;
	Integer cursor;
}
