/**
 * 
 */
package app.tiktok.comment;

import java.util.List;

import app.tiktok.tag.Tag;
import app.tiktok.user.CommonUserDetails;
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
public class Comment {
	/** The ID of the post */
	String aweme_id;

	/** The ID of the comment */
	String cid;

	/** The timestamp in seconds when the comment was posted */
	Integer create_time;

	/** The number of times the comment has been liked */
	Integer digg_count;

	/**
	 * If this comment is replying to a comment, this array contains the original
	 * comment
	 */
	List<Comment> reply_comment;

	/**
	 * If this comment is replying to a comment, the ID of that comment - "0" if not
	 * a reply
	 */
	String reply_id;

	/** The status of the comment - 1 = published, 4 = published by you? */
	Integer status;

	/** The comment text */
	String text;

	/** Details about any tags in the comment */
	List<Tag> text_extra;

	/** Details about the author */
	CommonUserDetails user;

	/** 1 if the user likes the comment */
	Integer user_digged;
}
