/**
 * 
 */
package app.tiktok.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class BaseRequestParams extends StaticRequestParams{
	  /** The current timestamp in seconds since UNIX epoch */
	  String ts;

	  /** The current timestamp in milliseconds since UNIX epoch */
	  String _rticket;
	  
	  String as;

	  /** A 20-character anti-spam parameter */
	  String cp;

	  /** An encoded version of the 'as' anti-spam parameter */
	  String mas;
	}
