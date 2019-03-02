/**
 * 
 */
package app.tiktok;

import java.util.ArrayList;

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
public class PlayAddress {
	private String uri;
	private String width;
	private String height;
	private ArrayList<String> url_list;
}
