package top.atstudy.component.base.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.base.config.WechatConstants;
import top.atstudy.component.enums.EnumRequestMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;

import static top.atstudy.component.base.config.WechatConstants.DEFAULT_PARAMS_SEPARATOR;

/**
 * 公众平台通用接口工具类
 * 
 *
 */
public class HttpUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param method
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据 JSONString
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	private static JSONObject httpRequest(String requestUrl,
										  EnumRequestMethod method, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(method.getCode());

			if (EnumRequestMethod.GET == method)
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
			log.info(" ===>> https request success ... ");
			log.info(" ===>> {}", jsonObject.toJSONString());
		} catch (ConnectException ce) {
			log.error(" ===>> https request connection timed out. error: {}", ce);
		} catch (Exception e) {
			log.error(" ===>> https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 发送 Get 请求
	 * @param url 请求地址
	 * @param params 参数
	 * @return
	 */
	public static JSONObject get(String url, Map<String, Object> params){

		//1.拼装参数
		if(params != null){
			StringBuilder stringBuilder = new StringBuilder("");
			for(Map.Entry<String, Object> entry:params.entrySet()){
				stringBuilder.append(WechatConstants.DEFAULT_REQUEST_PARAMS_SEPARATOR).append(entry.getKey()).append(Constants.DEFAULT_LIMIT).append(entry.getValue());
			}

			String paramsStr = stringBuilder.toString();
			url = url + DEFAULT_PARAMS_SEPARATOR + paramsStr.substring(1,paramsStr.length());
		}
		log.info(" ===>> request: {}", url);
		return httpRequest(url, EnumRequestMethod.GET, null);
	}

	/**
	 * 发送 POST 请求
	 * @param url 请求地址
	 * @param params 参数: JSON 字符串
	 * @return
	 */
	public static JSONObject post(String url, String params){
		return httpRequest(url, EnumRequestMethod.POST, params);
	}

}
