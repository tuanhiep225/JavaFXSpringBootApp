/**
 * 
 */
package app.tiktok.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class PostStatistics {
	/** The ID of the post */
	String aweme_id;

	/** The number of comments on the post */
	Integer comment_count;

	/** The number of times the post has been liked */
	Integer digg_count;

	/**
	 * The number of times the post has been viewed - doesn't appear to be public,
	 * so always 0
	 */
	Integer play_count;

	/** The number of times the post has been shared */
	Integer share_count;
}
