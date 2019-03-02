/**
 * 
 */
package app.tiktok.music;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.Media;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class MusicTrack {
	/** The name of the musician */
	String author;

	/** A HD version of the music's cover art */
	Media cover_hd;

	/** A large version of the music's cover art */
	Media cover_large;

	/** A medium version of the music's cover art */
	Media cover_medium;

	/** A thumbnail version of the music's cover art */
	Media cover_thumb;

	/** The duration of the track */
	Integer duration;

	/** The ID of the track */
	String id;

	/** The handle of the owner of the track */
	String owner_handle;

	/** The ID of the owner of the track */
	String owner_id;

	/** The nickname of the owner of the track */
	String owner_nickname;

	/** The link to play this track */
	Media play_url;

	/** The title of this track */
	String title;

	/** The number of posts that use this track */
	Integer user_count;
}
