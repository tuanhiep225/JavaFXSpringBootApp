/**
 * 
 */
package app.tiktok.follower;

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
public class ListFollowersRequest extends ListRequestParams{
	String user_id;
	Integer max_time;
}
