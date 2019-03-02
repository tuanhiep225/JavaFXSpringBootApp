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
public class AntiSpamParams {
	  /** A 20-character anti-spam parameter */
	 String as;

	  /** A 20-character anti-spam parameter */
	  String cp;

	  /** An encoded version of the 'as' anti-spam parameter */
	  String mas;
}