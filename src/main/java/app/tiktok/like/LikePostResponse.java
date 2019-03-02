/**
 * 
 */
package app.tiktok.like;

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
public class LikePostResponse extends BaseResponseData{
	 /**
	   * 0 if liked, 1 if not liked
	   *
	   * Note: for some reason, this value is the opposite of what you would expect
	   */
	 Integer is_digg =  0 | 1;
}
