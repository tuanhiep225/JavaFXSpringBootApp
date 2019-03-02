/**
 * 
 */
package app.tiktok.post;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.music.MusicTrack;
import app.tiktok.user.CommonUserDetails;
import app.tiktok.video.Video;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Post {
	/** Details about the author */
	CommonUserDetails author;

	/** The ID of the author */
	String author_user_id;

	/** The ID of the post */
	String aweme_id;

	/** The type of post - 0 for a musical.ly */
	Integer aweme_type;

	/** The timestamp in seconds when the post was created */
	Integer create_time;

	/** A description of the post */
	String desc;

	/** Details about the music used in the post */
	MusicTrack music;

	/** An age rating for the post */
	Integer rate;

	/** The 2-letter region the post was created in, e.g. US */
	String region;

	/** A link to the video on the musical.ly website that is used when sharing */
	String share_url;

	/** Statistics about the post */
	PostStatistics statistics;

	/** Status information about the post */
	PostStatus status;

	/** Tagged users and hashtags used in the description */
	List<PostTags> text_extra;

	/** 1 if the logged in user has liked this post */
	Integer user_digged;

	/** Details about the video in the post */
	Video video;
}
