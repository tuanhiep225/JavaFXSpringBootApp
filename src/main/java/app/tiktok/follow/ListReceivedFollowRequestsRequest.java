/**
 * 
 */
package app.tiktok.follow;

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
public class ListReceivedFollowRequestsRequest extends ListRequestParams{
	 Integer max_time;
}
