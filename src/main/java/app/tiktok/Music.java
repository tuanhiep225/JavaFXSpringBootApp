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
public class Music {
	private String id;
	private String title;
	private String author;
	private PlayAddress play_url;
}
