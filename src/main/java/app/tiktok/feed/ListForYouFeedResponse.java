/**
 * 
 */
package app.tiktok.feed;

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
public class ListForYouFeedResponse extends ListFeedResponse{
	 /** ??? - 1 */
	  Integer home_model;

	  /** ??? - 1 */
	  Integer refresh_clear;
}
