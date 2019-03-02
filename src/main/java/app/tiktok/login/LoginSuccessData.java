/**
 * 
 */
package app.tiktok.login;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public  class LoginSuccessData{
	/** ??? */
	String area;

	/** The URL of the user's avatar */
	String avatar_url;

	/** ??? */
	String bg_img_url;

	/** The user's birthday */
	String birthday;

	/** If the user allows people to find them by their phone number */
	Integer can_be_found_by_phone;

	/** ??? */
	List<Object> connects;

	/** ??? */
	String description;

	/** The email address associated with the account */
	String email;

	/** The number of users that follow the user */
	Integer followers_count;

	/** The number of users the user is following */
	Integer followings_count;

	/** An integer representing the gender of the user */
	Integer gender;

	/** ??? */
	String industry;

	/** Indicates if the user account is blocked */
	Integer is_blocked;

	/** ??? */
	Integer is_blocking;

	/** ??? */
	Integer is_recommend_allowed;

	/** ??? */
	Integer media_id;

	/** The mobile number of the user */
	String mobile;

	/** The name of the user - does not appear to be used */
	String name;

	/** Indicates if the user is new or not */
	Integer new_user;

	/** A Chinese character hint */
	String recommend_hint_message;

	/** The screen name of the user - does not appear to be used */
	String screen_name;

	/**
	 * The session ID used to authenticate subsequent requests in the sessionid
	 * cookie
	 */
	String session_key;

	/** ??? */
	Integer skip_edit_profile;

	/** ??? */
	String user_auth_info;

	/** The ID of the user */
	public String user_id;

	/** If the user is verified or not */
	Boolean user_verified;

	/** ??? */
	String verified_agency;

	/** ??? */
	String verified_content;

	/** The number of users that have visited the user's profile recently */
	Integer visit_count_recent;
}
