/**
 * 
 */
package app.common;

import java.net.URI;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.tiktok.request.AntiSpamParams;
import app.tiktok.request.BaseRequestParams;
import app.tiktok.request.RequiredUserDefinedRequestParams;
import app.tiktok.request.StaticRequestParams;

/**
 * @author tuanhiep225
 *
 */
public class RequestParamCommon {
	public static String getStaticParam() {
		StaticRequestParams staticRequestParam = getObjectStaticParams();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		URIBuilder uri = new URIBuilder();
		((Map<String, String>) objectMapper.convertValue(staticRequestParam, new TypeReference<Map<String,String>>() {}))
		 .forEach(uri::addParameter);
		return uri.toString();
	}
	
	private static  StaticRequestParams getObjectStaticParams() {
		return StaticRequestParams.builder().os_api("25").device_type("CPH1723")
				.ssmix("a").manifest_version_code("446")
				.dpi(480).app_name("trill")
				.version_name("4.6.6").timezone_offset(25200)
				.is_my_cn(0)
				.ac("wifi")
				.update_version_code("4660")
				.channel("googleplay")
				.device_platform("android")
				.build_number("4.6.6")
				.version_code(466)
				.timezone_name("Asia/Saigon")
				.resolution("1080*1920")
				.os_version("7.1.1")
				.device_brand("OPPO")
				.mcc_mnc("45204")
				.app_language("vi").language("vi").region("VN")
				.sys_region("VN")
				.carrier_region("VN")
				.carrier_region_v2("452")
				.aid("1180")
				.pass_region(1)
				.pass_route(1).build();
	}
	
	public static String getBaseRequestParam(RequiredUserDefinedRequestParams requireParam, AntiSpamParams requestParams, Object...params) {
		BaseRequestParams baseRequest = BaseRequestParams.builder()
				.os_api("25")
				.device_type("CPH1723")
				.ssmix("a").manifest_version_code("446")
				.dpi(480).app_name("trill")
				.version_name("4.6.6").timezone_offset(25200)
				.is_my_cn(0)
				.ac("wifi")
				.update_version_code("4660")
				.channel("googleplay")
				.device_platform("android")
				.build_number("4.6.6")
				.version_code(466)
				.timezone_name("Asia/Saigon")
				.resolution("1080*1920")
				.os_version("7.1.1")
				.device_brand("OPPO")
				.mcc_mnc("45204")
				.app_language("vi").language("vi").region("VN")
				.sys_region("VN")
				.carrier_region("VN")
				.carrier_region_v2("452")
				.aid("1180")
				.pass_region(1)
				.pass_route(1).build();
		baseRequest.setAs(requestParams.getAs());
		baseRequest.setCp(requestParams.getCp());
		baseRequest.setMas(requestParams.getMas());
		baseRequest.set_rticket(String.valueOf(new Date().getTime()));
		baseRequest.setTs(String.valueOf((int)Math.floor(new Date().getTime()/1000)));
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		URIBuilder uri = new URIBuilder();
		((Map<String, String>) objectMapper.convertValue(baseRequest, new TypeReference<Map<String,String>>() {}))
		 .forEach(uri::addParameter);
		
		((Map<String, String>) objectMapper.convertValue(requireParam, new TypeReference<Map<String,String>>() {}))
		 .forEach(uri::addParameter);
		
		if(null != params) {
			for(int i=0; i<params.length;i++) {
				((Map<String, String>) objectMapper.convertValue(params[i], new TypeReference<Map<String,String>>() {}))
				 .forEach(uri::addParameter);
			}
		}
		return uri.toString();
	}
	
	public static void main(String[] agrs) {
		System.out.println(getStaticParam());
	}
}
