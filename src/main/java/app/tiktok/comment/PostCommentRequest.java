/**
 * 
 */
package app.tiktok.comment;

import java.util.List;

import app.tiktok.tag.Tag;
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
public class PostCommentRequest {
	/** The ID of the post to comment on */
	String aweme_id;

	/** The comment text */
	String text;

	/** The ID of the comment that is being replied to */
	String reply_id;

	/** Details about any tags in the comment */
	List<Tag> text_extra;

	/** ??? */
	Integer is_self_see;
}
