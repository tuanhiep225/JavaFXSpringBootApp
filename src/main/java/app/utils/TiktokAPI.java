package app.utils;

import java.util.concurrent.CompletableFuture;

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
import app.tiktok.search.HashtagSearchResponse;
import app.tiktok.search.SearchRequest;
import app.tiktok.search.UserSearchRequest;
import app.tiktok.search.UserSearchResponse;
import app.tiktok.user.UserProfileResponse;



public interface TiktokAPI {

	public LoginResponse login(LoginRequest request) throws Exception;
	public LoginResponse loginWithPhone(String phone, String password) throws Exception;
	public UserProfileResponse getUser(String userId) throws Exception;
	public CompletableFuture<UserSearchResponse> searchUsers(UserSearchRequest request) throws Exception;
	public QRCodeResponse  getQRCode(QRCodeRequest request) throws Exception;
	public ListPostsResponse listPosts(ListPostsRequest request) throws Exception;
	public ListFollowersResponse listFollowers(ListFollowersRequest request) throws Exception;
	public ListFollowingResponse listFollowing(ListFollowingRequest request) throws Exception;
//	public Map<String, Object> follow(String phone, String password);
//	public Map<String, Object> unfollow(String phone, String password);
	public ListReceivedFollowRequestsResponse listReceivedFollowRequests(ListReceivedFollowRequestsRequest request) throws Exception;
//	public Map<String, Object> approveFollowRequest(String phone, String password);
//	public Map<String, Object> rejectFollowRequest(String phone, String password);
//	public Map<String, Object> likePost(String phone, String password);
//	public Map<String, Object> unlikePost(String phone, String password);
	public ListCommentsResponse listComments(ListCommentsRequest request) throws Exception;
//	public Map<String, Object> postComment(String phone, String password);
	public ListCategoriesResponse listCategories(ListCategoriesRequest request) throws Exception;
	public HashtagSearchResponse searchHashtags(SearchRequest request) throws Exception;
	public ListPostsInHashtagResponse listPostsInHashtag(ListPostsInHashtagRequest request) throws Exception;
	public CompletableFuture<ListFeedResponse> listFollowingFeed(ListFeedRequest request) throws Exception;
	public ListForYouFeedResponse listForYouFeed(ListFeedRequest request) throws Exception;
}
