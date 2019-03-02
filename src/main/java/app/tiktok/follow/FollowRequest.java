/**
 * 
 */
package app.tiktok.follow;

import app.tiktok.request.BaseResponseData;
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
public class FollowRequest extends BaseResponseData {
	/** The id of the user to follow */
	String user_id;

	/** 0 to unfollow, 1 to follow */
	Integer type;
}
