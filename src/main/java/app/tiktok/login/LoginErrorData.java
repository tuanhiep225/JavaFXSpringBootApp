/**
 * 
 */
package app.tiktok.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginErrorData{
	/** If required, the captcha that must solved */
	String captcha;

	/** A message explaining why the request failed */
	String description;

	/** An error code */
	Integer error_code;
}
