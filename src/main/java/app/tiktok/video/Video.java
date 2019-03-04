/**
 * 
 */
package app.tiktok.video;

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
public class Video {
	/** A medium version of the video's cover image */
	Media cover ;

	/** A high-quality link to download the video */
	Media download_addr;

	/** The video's duration in milliseconds */
	Integer duration;

	/** Whether the download link has a watermark */
	Boolean has_watermark;

	/** The video's height, e.g. 960 */
	Integer height;

	/** A high-quality version of the video's cover image */
	Media origin_cover;

	/** The quality of the video, e.g. 720p */
	 String ratio;

	/** The video's width, e.g. 540 */
	Integer width;
	
	Media play_addr;
}
