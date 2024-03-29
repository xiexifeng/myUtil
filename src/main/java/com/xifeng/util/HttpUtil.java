package com.xifeng.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年7月10日 下午4:25:18
 */
public class HttpUtil {
	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param httpurl 请求参数用?拼接在url后边，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return result 所代表远程资源的响应结果
	 */
	public static String doGet(String httpurl) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(httpurl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod(HttpMethod.GET.name());
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
				// 存放数据
				StringBuffer sbf = new StringBuffer();
				String temp = null;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			connection.disconnect();// 关闭远程连接
		}

		return result;
	}

	private static final String DEFAULT_ENCODE = "UTF-8";

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param httpUrl 发送请求的 URL
	 * @param param   请求参数应该是{"key":"==g43sEvsUcbcunFv3mHkIzlHO4iiUIT
	 *                R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq
	 *                JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d"}的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String doPost(String httpUrl, String param) {

		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod(HttpMethod.POST.name());
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/json");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			// connection.setRequestProperty("Authorization", "Bearer
			// da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}
	private final static OkHttpClient client =  new OkHttpClient.Builder()
	        .connectTimeout(10, TimeUnit.SECONDS)
	        .writeTimeout(120, TimeUnit.SECONDS)
	        .readTimeout(60, TimeUnit.SECONDS)
	        .build();
	
	public static String doPostJsonByOkHttp(final String url,final String content) throws Exception {
		RequestBody body = RequestBody.create(okhttp3.MediaType.parse(MediaType.APPLICATION_JSON_UTF8_VALUE), content);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			return response.body().string();
		}
	}
	
	public static String doGetByOkHttp(final String url) throws Exception {
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			return response.body().string();
		}
	}
	
	public static void main(String[] args) {
		OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder()
                .add("post_id", "1953")
                .add("form_id", "3dfaf07")
                .add("queried_id", "1953")
                .add("form_fields[CompanyName]", "海运易电商003")
                .add("form_fields[email]", "航运业")
                .add("form_fields[message]", "香港")
                .add("form_fields[field_c557d16]", "+8625252222")
                .add("form_fields[field_befa199]", "schelling.xie@marineonline.com")
                .add("form_fields[field_f331f48]", "peter")
                .add("form_fields[field_16bef8d]", "remark 20201222")
                .add("action", "elementor_pro_forms_send_form")
                .add("referrer", "https://shipping-news.marineonline.com/index.php/authorized-service-provider-aspmobile/")
                .build();

        Request request = new Request.Builder()
                .url("https://shipping-news.marineonline.com/wp-admin/admin-ajax.php")
                .post(formBody)
                .build();

        Response response;
		try {
			response = okHttpClient.newCall(request).execute();
			System.out.println(response.toString());
			System.out.println(response.body().string());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
