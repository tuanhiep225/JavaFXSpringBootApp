package app.tiktok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Status {
	private String aweme_id;
	private String is_delete;
	private Boolean allow_share;
	private Boolean allow_comment;
}
