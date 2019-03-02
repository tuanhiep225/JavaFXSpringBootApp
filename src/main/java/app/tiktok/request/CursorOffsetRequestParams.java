/**
 * 
 */
package app.tiktok.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursorOffsetRequestParams {
	  /**
	   * A timestamp in milliseconds - the most recent results before this time will be listed.
	   * Use max_cursor from the response data here for pagination. Use 0 for the most recent.
	   */
	  Integer max_cursor;
	}
