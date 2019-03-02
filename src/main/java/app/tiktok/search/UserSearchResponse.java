/**
 * 
 */
package app.tiktok.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.ListResponseData;
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
public class UserSearchResponse extends ListResponseData {
	/** A list of users that match the search term */
	List<UserSearchResult> user_list;
	/** The scope of the search - users = 1 */
	Integer type;

	Integer cursor;
}
