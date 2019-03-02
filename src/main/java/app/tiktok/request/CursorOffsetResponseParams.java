/**
 * 
 */
package app.tiktok.request;

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
public class CursorOffsetResponseParams {
	Integer min_cursor;
	Integer max_cursor;
}
