/**
 * 
 */
package app.tiktok.category;

import java.util.List;

import app.tiktok.request.ListResponseData;
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
public class ListCategoriesResponse extends ListResponseData{
	List<Category> category_list;
	Integer cursor;
}
