/**
 * 
 */
package app.utils;

import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tuanhiep225
 *
 */
public class URIHelper extends URIBuilder{
	public URIHelper setParam(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		((Map<String, String>) objectMapper.convertValue(object, new TypeReference<Map<String,String>>() {}))
		 .forEach(this::addParameter);
		return this;
	}
}
