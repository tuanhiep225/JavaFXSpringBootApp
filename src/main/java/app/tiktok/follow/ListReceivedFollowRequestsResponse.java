/**
 * 
 */
package app.tiktok.follow;

import java.util.List;

import app.tiktok.request.ListResponseData;
import app.tiktok.user.CommonUserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListReceivedFollowRequestsResponse extends ListResponseData {
	/** A list of users who have requested to follow you */
	List<CommonUserDetails> request_users;
	/** The timestamp in seconds associated with the first result */
	Integer max_time;

	/**
	 * The timestamp in seconds associated with the last result - use as max_time
	 * for pagination
	 */
	Integer min_time;
}
