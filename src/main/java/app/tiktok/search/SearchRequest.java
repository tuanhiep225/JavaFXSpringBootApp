/**
 * 
 */
package app.tiktok.search;

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
public class SearchRequest extends ListRequestParams {
	  /** The term to search for */
	 String keyword;
	 
	 Integer cursor;
	}

