/**
 * 
 */
package app.tiktok.login;

/**
 * @author tuanhiep225
 *
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	/** Unsure, but looks to be hard-coded to 1 */
	public Integer mix_mode;

	/** The unique username ("musername") of the user */
	public String username;

	/** The email address associated with the user account */
	String email;

	/** The mobile number associated with the user account */
	String mobile;

	/** ??? */
	String account;

	/** The password to the user account */
	String password;

	/** The captcha answer - only required if a captcha was shown */
	String captcha;
}
