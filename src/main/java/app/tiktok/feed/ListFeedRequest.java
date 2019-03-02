/**
 * 
 */
package app.tiktok.feed;

import app.tiktok.request.ListRequestParams;
import app.tiktok.type.FeedType;
import app.tiktok.type.PullType;
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
public class ListFeedRequest extends ListRequestParams {
	/** The type of feed to load */
	FeedType type;

	/** Your device's current volume level on a scale of 0 to 1, e.g. 0.5 */
	Integer volume;

	/** How the feed was requested */
	PullType pull_type;

	/** ??? - empty */
	String req_from;

	/** ??? - 0 */
	Integer is_cold_start;

	/** ??? */
	String gaid;

	/** A user agent for your device */
	String ad_user_agent;
	
	  /**
	   * A timestamp in milliseconds - the most recent results before this time will be listed.
	   * Use max_cursor from the response data here for pagination. Use 0 for the most recent.
	   */
	Integer  max_cursor;
}
