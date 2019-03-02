/**
 * 
 */
package app.tiktok.comment;

import app.tiktok.request.ListRequestParams;
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
@SuperBuilder
public class ListCommentsRequest extends ListRequestParams {
	Integer cursor;

	/** The ID of the post to list comments for */
	String aweme_id;

	/** ??? - default is 2 */
	Integer comment_style;

	/** ??? */
	Object digged_cid;

	/** ??? */
	Object insert_cids;
}
