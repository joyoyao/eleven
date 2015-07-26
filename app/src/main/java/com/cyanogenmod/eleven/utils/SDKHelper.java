package com.cyanogenmod.eleven.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.music.SDKEngine;
import com.baidu.music.interfaces.OAuthInterface;
import com.baidu.music.manager.OAuthManager;
import com.baidu.music.model.OAuthException;
//import com.baidu.music.oauth.OAuthInterface;
//import com.baidu.music.oauth.OAuthManager;
import com.baidu.music.onlinedata.OnlineManagerEngine;
import com.baidu.utils.LogUtil;

/**
 * Created by CHUER on 2015/1/3.
 */
public class SDKHelper {

	private static final String TAG = "SDKHelper";
	private static final String SECRET = "rIPjm8CciNH972nA9N09UxB3Ap71htpz";
	private static final String APP_KEY = "E3GkLQfYBgYkKgdIPcaGaCTv";
	private static final String SCOPE = "music_media_basic,music_musicdata_basic,music_search_basic,music_media_premium";

	private static int mRetStatus = OAuthException.ERROR_UNKNOWN;
	private static SDKEngine mEngine;
	public final static String REDIRECT_URL = "bdconnect://success";

	/**
	 * 授权
	 * 
	 * @param context
	 * @return 授权状态 ERROR_ACCESS_DENIED 16 用户或授权服务器拒绝授予数据访问权限
	 *         ERROR_CERTIFICATE_ERROR 17 HTTS证书异常 ERROR_EXPIRED_TOKEN 7
	 *         提供的Refresh Token已过期 ERROR_INVALID_CLIENT 2
	 *         "client_id"、"client_secret"参数无效 ERROR_INVALID_GRANT 3 提供的Access
	 *         Grant是无效的、过期的或已撤销的，例如，Authorization Code无效(一个授权码只能使用一次)、Refresh
	 *         Token无效、redirect_uri与获取Authorization Code时提供的不一致等
	 *         ERROR_INVALID_RESQ 1 请求缺少某个必需参数，包含一个不支持的参数或参数值，或者格式不正确
	 *         ERROR_INVALID_SCOPE 6
	 *         请求的“scope”参数是无效的、未知的、格式不正确的、或所请求的权限范围超过了数据拥有者所授予的权限范围
	 *         ERROR_REDIRECT_URI_MISMATCH 8
	 *         “redirect_uri”所在的根域与开发者注册应用时所填写的根域名不匹配 ERROR_UNAUTH_CLIENT 4
	 *         应用没有被授权，无法使用所指定的grant_type ERROR_UNKNOWN 255 其他未知错误
	 *         ERROR_UNSUPPORTED_GRANT 5 “grant_type”参数值不为百度OAuth2.0服务所支持
	 *         ERROR_UNSUPPORTED_RESPONSE 9 “response_type”参数值不为百度OAuth2.0服务所支持
	 *         SUCCESS 0 请求成功
	 */
	public static int authorize(Context context) {
		// 在线模块每次发起网络请求会以debug级别在Logcat打印出网络请求及响应的Log，对应TAG为HttpHelper
		LogUtil.setDebugMode(true);
		SDKImpl sdk = new SDKImpl();
		mEngine = SDKEngine.getInstance();
		// 使用SDK必须先调用此方法进行相关初始化
		mEngine.init(context, APP_KEY, SECRET, SCOPE, sdk);
		OAuthManager mOauthManager = OAuthManager.getInstance(context);

		if (mOauthManager.validate() < 0) {
			Log.d(TAG, "mOauthManager.validate() < 0");
			mOauthManager
					.authorize(new OAuthInterface.onAuthorizeFinishListener() {
						@Override
						public void onAuthorizeFinish(int status) {
							Log.d(TAG, "status=" + status);
							mRetStatus = status;
						}
					});
			return mRetStatus;
		} else {
			Log.d(TAG, "validate=" + mOauthManager.validate());
			return OAuthException.SUCCESS;
		}
	}

	public static void destroySDK(Context context) {
		// 清理SDK相关资源，此接口需要在应用结束时调用
		if (mEngine != null) {
			mEngine.destory();
		}
		// 应用退出前，需要调用releaseEngine来做清理工作 否则会有内存泄露风险
		OnlineManagerEngine.getInstance(context).releaseEngine();
	}
}
