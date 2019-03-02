/**
 * 
 */
package app.tiktok.search;

import java.util.List;

import app.tiktok.category.ChallengeInfo;
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
public class HashtagSearchResult {
	 /** Information about the hashtag */
	ChallengeInfo challenge_info;

	  /** If the hashtag contains the search term, this array contains the location of the term */
	 List<SubstringPosition> position;
}
