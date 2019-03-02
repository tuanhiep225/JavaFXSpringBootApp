/**
 * 
 */
package app.tiktok.hashtag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown= true)
public class ListPostsInHashtagRequest extends ListRequestParams{
	String ch_id;
	Integer query_type;
	Integer type;
	Integer cursor;
}
