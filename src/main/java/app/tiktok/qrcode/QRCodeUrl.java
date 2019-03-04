/**
 * 
 */
package app.tiktok.qrcode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown= true)
public class QRCodeUrl {
	/** An in-app link to the QR code */
	String uri;

	/** Contains a public link to the QR code image (first element in array) */
	List<String> url_list;
}
