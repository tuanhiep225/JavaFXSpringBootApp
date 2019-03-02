/**
 * 
 */
package app.tiktok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareInfo {
	private String  share_url;
	private String share_desc;
	private String share_title;
	
}
