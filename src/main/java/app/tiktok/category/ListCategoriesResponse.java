/**
 * 
 */
package app.tiktok.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.tiktok.request.ListResponseData;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class ListCategoriesResponse extends ListResponseData{
	List<Category> category_list;
	Integer cursor;
}
