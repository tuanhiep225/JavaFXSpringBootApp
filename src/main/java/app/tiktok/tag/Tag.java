/**
 * 
 */
package app.tiktok.tag;

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
public class Tag {
	/** The type of user being tagged? */
	String at_user_type = null;

	/** The zero-based index in the text where the tag starts */
	Integer end = null;

	/** The zero-based index in the text where the tag ends */
	Integer start = null;

	/** The type of tag? */
	Integer type = null;

	/** The ID of the user being tagged */
	String user_id = null;
}