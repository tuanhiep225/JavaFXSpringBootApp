package app.tiktok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Statistics {
	private String aweme_id;
	private String comment_count;
	private String digg_count;
	private String play_count;
	private String share_count;
	
}
