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
public class RejectFollowResponse extends BaseResponseData{
	Integer reject_status;
}
