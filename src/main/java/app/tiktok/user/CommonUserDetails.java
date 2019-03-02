/**
 * 
 */
package app.tiktok.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.Media;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonUserDetails {
	/** A large version of the user's avatar */
	Media avatar_larger;

	/** A medium version of the user's avatar */
	Media avatar_medium;

	/** A thumbnail version of the user's avatar */
	Media avatar_thumb;

	/** The timestamp in seconds when the user's account was created */
	Integer create_time;

	/** The badge name with a verified user (e.g. comedian, style guru) */
	String custom_verify;

	/** 1 if you follow this user */
	Integer follow_status;

	/** 1 if this user follows you */
	Integer follower_status;

	/** The user's Instagram handle */
	String ins_id;

	/** Indicates if the user has been crowned */
	Boolean is_verified;

	/** The user's profile name */
	String nickname;

	/** A 2-letter country code representing the user's region, e.g. US */
	String region;

	/** If the user is live, a string ID used to join their stream, else 0 */
	String room_id;

	/** 1 if the user's profile is set to private */
	Integer secret;

	/** The user's profile signature */
	String signature;

	/** The user's Twitter handle */
	String twitter_id;

	/** The user's ID */
	String uid;

	/** The user's musername */
	String unique_id;

	/** 1 if the user has been crowned */
	Integer verification_type;

	/** The user's YouTube channel ID */
	String youtube_channel_id;
}
