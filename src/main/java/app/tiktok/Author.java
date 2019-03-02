/**
 * 
 */
package app.tiktok;

/**
 * @author tuanhiep225
 *
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
	
	private String uid;
	private String nickname;
	private int following_count;
	private int follower_count;
	private int favoriting_count;
	private int total_favorited;

}
