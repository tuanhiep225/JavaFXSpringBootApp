package app.tiktok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
	private PlayAddress play_addr;
	private String share_url;
	private PlayAddress download_addr;
	private PlayAddress cover;
	private PlayAddress dynamic_cover;
}
