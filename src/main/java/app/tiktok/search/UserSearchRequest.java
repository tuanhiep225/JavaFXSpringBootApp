/**
 * 
 */
package app.tiktok.search;

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
public class UserSearchRequest extends SearchRequest {
	  /** Required - the scope of the search - users = 1. */
	  Integer type;
	}
