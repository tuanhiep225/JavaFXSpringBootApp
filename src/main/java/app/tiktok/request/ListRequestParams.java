/**
 * 
 */
package app.tiktok.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author tuanhiep225
 *
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ListRequestParams {
	  /** The number of results to return */
	  String count = null;

	  /** How the request will be retried on failure - defaults to "no_retry" */
	  String retry_type = null;
	}
