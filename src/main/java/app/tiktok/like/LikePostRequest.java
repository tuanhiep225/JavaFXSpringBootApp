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
public class LikePostRequest extends BaseResponseData{
	/** The id of the post to like */
	String  aweme_id;

	  /** 0 to unlike, 1 to like */
	Integer  type;
}
