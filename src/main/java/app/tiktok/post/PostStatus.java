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
public class PostStatus {
	/** Whether the post allows comments */
	Boolean allow_comment;

	/** Whether the post allows sharing */
	Boolean allow_share;

	/** Whether the post has been deleted */
	Boolean is_delete;

	/** Whether the post is private */
	Boolean is_private;
}
