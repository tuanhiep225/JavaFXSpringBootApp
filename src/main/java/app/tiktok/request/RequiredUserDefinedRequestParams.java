/**
 * 
 */
package app.tiktok.request;

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
public class RequiredUserDefinedRequestParams {
	/** The 16-character ID of your installation, e.g. 4549764744226841084 */
	 String iid;
	/** A 16-character hexadecimal identifier associated with your device, e.g. 4b903fbb9d457937 */
	String openudid;
	/** The ID of your device that has already been registered with musical.ly */
	String device_id;
	/** An anti-fraud fingerprint of your device requested from a different API */
	String fp;
}
