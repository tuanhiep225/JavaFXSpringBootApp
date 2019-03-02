/**
 * 
 */
package app.tiktok.search;

import java.util.List;

import app.tiktok.request.ListResponseData;
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
public class HashtagSearchResponse extends ListResponseData{
	/** A list of hashtags that match the search term */
	  List<HashtagSearchResult> challenge_list;

	  /** True if a challenge matches the search term */
	  Boolean is_match;

	  /** 1 if the search term is disabled */
	  Integer keyword_disabled ;
	  
	  Integer cursor;
}
