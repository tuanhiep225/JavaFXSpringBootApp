/**
 * 
 */
package app.tiktok.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class Category {
	/** A list of posts in the category */
	List<Post> aweme_list;

	/** The type of category - 0 for hashtag? */
	Integer category_type;

	/** Information about the category */
	ChallengeInfo challenge_info;

	/** A description of the category type, e.g. "Trending Hashtag" */
	String desc;
}
