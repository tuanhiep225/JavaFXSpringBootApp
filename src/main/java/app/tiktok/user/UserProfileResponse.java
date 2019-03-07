/**
 * 
 */
package app.tiktok.user;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.BaseResponseData;
import lombok.AllArgsConstructor;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileResponse extends BaseResponseData{
	UserProfile user;
}
