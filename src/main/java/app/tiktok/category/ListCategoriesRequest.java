/**
 * 
 */
package app.tiktok.category;

import app.tiktok.request.ListRequestParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tuanhiep225
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoriesRequest extends ListRequestParams{
	 Integer cursor;
}
