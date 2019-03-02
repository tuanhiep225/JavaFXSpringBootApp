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
public class FollowResponse extends BaseResponseData {
	Integer follow_status;

	Integer watch_status;
}
