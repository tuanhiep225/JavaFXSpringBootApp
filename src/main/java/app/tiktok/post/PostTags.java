/**
 * 
 */
package app.tiktok.post;

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
public class PostTags {
	/** 0 if the tag is for a user; 1 if the tag is for a hashtag */
	Integer type;

	/** The name of the hashtag */
	String hashtag_name;

	/** The ID of the tagged user */
	String user_id;
}
