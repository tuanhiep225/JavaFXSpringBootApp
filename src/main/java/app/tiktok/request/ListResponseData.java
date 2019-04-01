/**
 * 
 */
package app.tiktok.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResponseData extends BaseResponseData{
	  /** Whether there are more results that can be requested */
	Integer has_more;

	  /** The total number of results returned - not present in all list requests */
	  Integer total;
	}
