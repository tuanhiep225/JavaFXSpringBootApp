/**
 * 
 */
package app.tiktok.request;

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
public class StaticRequestParams extends RequiredUserDefinedRequestParams{
	 /** Your Android version, e.g. 23 */
	 String os_api;

	  /** Your device model, e.g. Pixel 2 */
	 String device_type;

	  /** ??? - set to "a" */
	 String ssmix;

	  /** The SS_VERSION_CODE metadata value from the AndroidManifest.xml file, e.g. 2018060103 */
	 String manifest_version_code;

	  /** Your device's pixel density, e.g. 480 */
	  Integer dpi;

	  /** The application name - hard-coded to "musical_ly" */
	  String app_name;

	  /** The SS_VERSION_NAME metadata value from the AndroidManifest.xml file, e.g. 7.2.0 */
	  String version_name;

	  /** The UTC offset in seconds of your timezone, e.g. 37800 for Australia/Lord_Howe */
	  Integer timezone_offset;

	  /** ??? - are we in China / using the Chinese version? Set to 0 */
	  Integer is_my_cn;

	  /** Network connection type, e.g. "wifi" */
	  String ac;

	  /** The UPDATE_VERSION_CODE metadata value from the AndroidManifest.xml file, e.g. 2018060103 */
	  String update_version_code;

	  /** The channel you downloaded the app through, e.g. googleplay */
	  String channel;

	  /** Your device's platform, e.g. android */
	  String device_platform;

	  /** The build number of the application, e.g. 7.2.0 */
	  String build_number;

	  /** A numeric version of the version_name metadata value, e.g. 720 */
	  Integer  version_code;

	  /** The name of your timezone as per the tz database, e.g. Australia/Sydney */
	  String timezone_name;

	  /**
	   * The region of the account you are logging into, e.g. AU.
	   *
	   * This field is only present if you are logging in from a device that hasn't had a
	   * user logged in before.
	   */
	  String account_region;

	  /** Your device's resolution, e.g. 1080*1920 */
	  String resolution;

	  /** Your device's operating system version, e.g. 8.0.0 */
	  String  os_version;

	  /** Your device's brand, e.g. Google */
	  String device_brand;

	  /** ??? - empty */
	  String mcc_mnc;

	  /** The application's two-letter language code, e.g. en */
	  String app_language;

	  /** Your i18n language, e.g. en */
	  String language;

	  /** Your region's i18n locale, e.g. US */
	  String region;

	  /** Your device's i18n locale, e.g. US */
	  String sys_region;

	  /** Your carrier's region (a two-letter country code), e.g. AU */
	  String carrier_region;

	  /** You carrer's mobile country code (MCC), e.g. 505 */
	  String carrier_region_v2;

	  /** A hard-coded i18n constant set to "1233" */
	  String aid;

	  /** ??? - set to 1 */
	  Integer pass_region;

	  /** ??? - set to 1 */
	  Integer pass_route;
}