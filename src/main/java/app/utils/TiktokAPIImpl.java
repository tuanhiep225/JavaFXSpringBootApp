/**
 * 
 */
package app.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import app.common.RequestParamCommon;
import app.tiktok.category.ListCategoriesRequest;
import app.tiktok.category.ListCategoriesResponse;
import app.tiktok.comment.ListCommentsRequest;
import app.tiktok.comment.ListCommentsResponse;
import app.tiktok.feed.ListFeedRequest;
import app.tiktok.feed.ListFeedResponse;
import app.tiktok.feed.ListForYouFeedResponse;
import app.tiktok.follow.ListReceivedFollowRequestsRequest;
import app.tiktok.follow.ListReceivedFollowRequestsResponse;
import app.tiktok.follower.ListFollowersRequest;
import app.tiktok.follower.ListFollowersResponse;
import app.tiktok.follower.ListFollowingRequest;
import app.tiktok.follower.ListFollowingResponse;
import app.tiktok.hashtag.ListPostsInHashtagRequest;
import app.tiktok.hashtag.ListPostsInHashtagResponse;
import app.tiktok.login.LoginRequest;
import app.tiktok.login.LoginResponse;
import app.tiktok.post.ListPostsRequest;
import app.tiktok.post.ListPostsResponse;
import app.tiktok.qrcode.QRCodeRequest;
import app.tiktok.qrcode.QRCodeResponse;
import app.tiktok.request.AntiSpamParams;
import app.tiktok.request.RequiredUserDefinedRequestParams;
import app.tiktok.search.HashtagSearchResponse;
import app.tiktok.search.SearchRequest;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.tiktok.user.UserProfileResponse;

/**
 * @author tuanhiep225
 *
 */
@Service
public class TiktokAPIImpl implements TiktokAPI{
	private static Client client;
	private final String baseUrl = "https://api-h2.tiktokv.com/%1$s";
	private final String baseUrlNew = "https://api21-h2.tiktokv.com/%1$s";
	
	@Override

	public LoginResponse login(LoginRequest request) throws Exception {
		  client = ClientHelperUtils.createClient();
		  String path = "passport/user/login/";
		  String url = String.format(baseUrl, path);
		  Map<String, String> newParam = new HashMap<>();
		  newParam.put("mix_mode", "1");
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(), request, newParam);
		  url+= param;
		  WebTarget target = client.target(url);
		  Form form = new Form();
		  form.param("mobile", request.getMobile());
		  form.param("password", request.getPassword());
		  Response response = target.request().post(Entity.form(form));
		  return response.readEntity(LoginResponse.class);
	}

	@Override

	public LoginResponse loginWithPhone(String phone, String password) throws Exception {
		String passEncode = StringUtils.encryptWithXor(password);
		String phoneEnCode =  StringUtils.encryptWithXor(phone);
		LoginRequest loginRequest = LoginRequest.builder().mobile(phoneEnCode).password(passEncode).build();
		
		return login(loginRequest);
	}
	

	@Override

	public UserProfileResponse getUser(String userId) throws Exception {
		 client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/user/";
		  String url = String.format(baseUrl, path);
		  Map<String, String> newParam = new HashMap<>();
		  newParam.put("user_id",userId);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(), newParam);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(UserProfileResponse.class);
	}

	@Override
	@Async
	public CompletableFuture<UserSearchResponse> searchUsers(UserSearchRequest request) throws Exception {
		 client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/discover/search/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().header("x-tt-token", "01abe5e241592779dbed405dc78524c9c6e642d868a074472f1ea199fd5d666c38f70c3d3a94c0ab803d17967975cf245d16")
				  .header("user-agent", "com.ss.android.ugc.trill/466 (Linux; U; Android 7.1.1; vi_VN; CPH1723; Build/N6F26Q; Cronet/58.0.2991.0)")
				  .header("sdk-version", "1").get();
		  Thread.sleep(5000);
		return CompletableFuture.completedFuture(response.readEntity(UserSearchResponse.class));
	}

	@Override

	public ListPostsResponse listPosts(ListPostsRequest request) throws Exception {
		 client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/aweme/post/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListPostsResponse.class);
	}
	
	

	@Override

	public ListFollowersResponse listFollowers(ListFollowersRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/user/follower/list/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListFollowersResponse.class);
	}
	
	@Override

	public ListFollowingResponse listFollowing(ListFollowingRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/user/following/list/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListFollowingResponse.class);
	}


	// phải đăng nhập trước
	@Override

	public ListReceivedFollowRequestsResponse listReceivedFollowRequests(ListReceivedFollowRequestsRequest request)
			throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/user/following/request/list/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListReceivedFollowRequestsResponse.class);
	}

	@Override

	public ListCommentsResponse listComments(ListCommentsRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/comment/list/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListCommentsResponse.class);
	}

	@Override
	public ListCategoriesResponse listCategories(ListCategoriesRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/category/list/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListCategoriesResponse.class);
	}

	@Override

	public HashtagSearchResponse searchHashtags(SearchRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/challenge/search/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(HashtagSearchResponse.class);
	}

	@Override

	public ListPostsInHashtagResponse listPostsInHashtag(ListPostsInHashtagRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/challenge/aweme/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListPostsInHashtagResponse.class);
	}

	@Override
	public CompletableFuture<ListFeedResponse> listFollowingFeed(ListFeedRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/feed/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return CompletableFuture.completedFuture(response.readEntity(ListFeedResponse.class));
	}

	@Override
	public ListForYouFeedResponse listForYouFeed(ListFeedRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/feed/";
		  String url = String.format(baseUrl, path);
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(),request);
		  url+= param;
		  WebTarget target = client.target(url);
		  Response response = target.request().get();
		return response.readEntity(ListForYouFeedResponse.class);
	}

	@Override
	public QRCodeResponse getQRCode(QRCodeRequest request) throws Exception {
		client = ClientHelperUtils.createClient();
		  String path = "aweme/v1/fancy/qrcode/info/";
		  String url = String.format(baseUrl, path);
		  
		  
		  Map<String, String> newParam = new HashMap<>();
		  newParam.put("js_sdk_version","");
		  newParam.put("app_type","normal");
		  
		  String param = RequestParamCommon.getBaseRequestParam(RequiredUserDefinedRequestParams.builder().device_id("6549802077311403522").iid("6644197233862854401").openudid("278552578f3f613f").build(), AntiSpamParams.builder().build(), newParam);
		  url+= param;
		  WebTarget target = client.target(url);
		  
		  Form form = new Form();
		  form.param("schema_type", request.getSchema_type().toString());
		  form.param("object_id", request.getObject_id());

		  Response response = target.request().post(Entity.form(form));
		return response.readEntity(QRCodeResponse.class);
	}
	
	public static void main (String[] agrs) throws Exception {
		TiktokAPIImpl a = new TiktokAPIImpl();
//		 HashtagSearchResponse us = a.searchHashtags(SearchRequest.builder().count("10").cursor(0).keyword("boss.sen").build());
//		UserSearchResponse rs= a.searchUsers(UserSearchRequest.builder().type(1).count("10").cursor(0).keyword("boss.sen").build());
	//	LoginResponse b = a.loginWithPhone("+840984599264", "123456a@");
		System.out.println("");
	}


}
