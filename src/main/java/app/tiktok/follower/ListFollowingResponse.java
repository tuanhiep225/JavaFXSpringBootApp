/**
 * 
 */
package app.tiktok.follower;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.ListResponseData;
import app.tiktok.user.CommonUserDetails;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class ListFollowingResponse  extends ListResponseData {
	List<CommonUserDetails> followings;
	Integer max_time;
}

