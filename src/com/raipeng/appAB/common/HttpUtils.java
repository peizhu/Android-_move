package com.raipeng.appAB.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @date: 2013-10-28 下午2:17:29
 * 
 * @email: tchen@raipeng.com
 * 
 * @version: V1.0
 * 
 * @description: 网络交互工具类
 * 
 */
public class HttpUtils {
	public static String currentSessionId;

	public static String getHttpString(String urlStr, String param) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(50000);
			urlConn.connect();

			DataOutputStream dos = new DataOutputStream(
					urlConn.getOutputStream());
			if (param != null) {
				String params = "param=" + URLEncoder.encode(param, "UTF-8");
				dos.write(params.getBytes());
				dos.flush();
				dos.close();
			}

			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			String cookieval = urlConn.getHeaderField("set-cookie");
			if (cookieval != null)
				currentSessionId = cookieval.substring(0,
						cookieval.indexOf(";"));

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", "false");
				obj.put("reason", "无法连接到服务器");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return obj.toString();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static Bitmap getHttpImageWhole(String urlStr) {
		Bitmap bmp = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
			bitmapOptions.inSampleSize = 0;
			InputStream is = urlConn.getInputStream();
			byte[] data = readStream(is);
			if (data != null) {
				bmp = BitmapFactory.decodeByteArray(data, 0, data.length,
						bitmapOptions);
			}
			is.close();
			urlConn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmp;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	public static void downLoadApp(String urlStr, String name) {/*
		try {
			File root = new File(Constants.BASE_DOWNLOAD_IMAGE_PATH);
			if (!root.exists())
				root.mkdirs();

			File f = new File(Constants.BASE_DOWNLOAD_IMAGE_PATH + name);
			if (f.exists()) {
				f.delete();
			}
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);

			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setRequestMethod("GET");
			urlConn.setUseCaches(false);
			urlConn.setConnectTimeout(5000);//（单位：毫秒）,连接超时
			urlConn.setReadTimeout(50000);//  （单位：毫秒）,读操作超时
			urlConn.connect();

			InputStream is = urlConn.getInputStream();
			byte[] buffer = new byte[1 * 1024];
			int length = 0;
			while ((length = is.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}

			out.flush();
			out.close();
			is.close();
			urlConn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/}

	public static String saveHttpString(String urlStr, String param) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setConnectTimeout(5000);// （单位：毫秒）,连接超时
			urlConn.setReadTimeout(30000);// （单位：毫秒）读操作超时
			urlConn.connect();
			// 得到输出流
			DataOutputStream dos = new DataOutputStream(
					urlConn.getOutputStream());
			if (param != null) {

				String params = "param=" + URLEncoder.encode(param, "UTF-8");
				dos.write(params.getBytes());
				dos.flush();
				dos.close();
			}

			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			String cookieval = urlConn.getHeaderField("set-cookie");
			if (cookieval != null)
				currentSessionId = cookieval.substring(0,
						cookieval.indexOf(";"));
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", "false");
				obj.put("reason", "无法连接到服务器");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return obj.toString();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String getHttpStringWithGet(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setRequestMethod("GET");
			urlConn.setUseCaches(false);
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(50000);
			urlConn.connect();

			buffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
			String cookieval = urlConn.getHeaderField("set-cookie");
			if (cookieval != null)
				currentSessionId = cookieval.substring(0,
						cookieval.indexOf(";"));

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			try {
				obj.put("success", "false");
				obj.put("reason", "无法连接到服务器");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return obj.toString();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
