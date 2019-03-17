/**
 * 
 */
package app.tiktok.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.user.CommonUserDetails;
import app.tiktok.user.UserProfile;
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
public class UserSearchResult {
	/** If the user's nickname contains the search term, this array contains the location of the term */
	List<SubstringPosition> position;

	  /** If the user's username (unique_id) contains the search term, this array contains the location of the term */
	List<SubstringPosition> uniqid_position;

	  /** Information about the user */
	UserProfile user_info;
}
