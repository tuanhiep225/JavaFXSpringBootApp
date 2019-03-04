/**
 * 
 */
package app.tiktok.category;

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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChallengeInfo {
	/** The user who created the challenge, or an empty object */
	Object author;

	/** The name of the challenge */
	 String cha_name;

	/** The ID of the challenge */
	String  cid;

	/** A description of the challenge */
	 String  desc;

	/** ??? */
	 Boolean is_pgcshow;

	/** An in-app link to the challenge */
	 String  schema;

	/** The type of challenge - 0 for hashtag? */
	 Integer type;

	/** The number of users who have uploaded a video for the challenge */
	 Integer user_count;
}
