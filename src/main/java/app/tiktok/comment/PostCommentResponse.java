/**
 * 
 */
package app.tiktok.comment;

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
public class PostCommentResponse extends BaseResponseData{
	Comment comment;
}
