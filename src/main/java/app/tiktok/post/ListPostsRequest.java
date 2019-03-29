/**
 * 
 */
package app.tiktok.post;

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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown=true)
public class ListPostsRequest extends ListRequestParams{
	String user_id ;
	Long max_cursor;
}
