/**
 * 
 */
package app.tiktok.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.Media;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
public class UserProfile extends CommonUserDetails {
	/** The number of videos the user has uploaded */
	Integer aweme_count;

	/** The number of videos the user has liked */
	Integer favoriting_count;

	/** The number of users who follow this user */
	Integer follower_count;

	/** The number of users this user follows */
    Integer following_count;

	/** The total number of likes the user has received */
	Integer total_favorited;
}
