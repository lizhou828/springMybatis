package com.liz.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zuoliqiang
 * Date: 11-12-19
 * Time: P.M2:45
 */
public class HttpUtil {

    /**
     * to do getContent from httpUrl
     * @param httpUrl of  WJ URL
     * @param charsetName response charset
     * @return String with response content
     * @throws java.io.IOException of url
     */
    public static String connect(String httpUrl, String charsetName) throws IOException {
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(httpUrl);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.connect();
            InputStream is = httpConn.getInputStream();
            StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, charsetName));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                sb.append(tmp);
            }
            return sb.toString();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

    /**
     * send get http request
     * @param url destination
     * @return response
     */
    public static String sendGetRequest(String url, String charsetName) throws IOException {
        return sendGetRequest(url, new HashMap<String, String>(), charsetName);
    }

    /**
     * send get http request
     *
     * @param url destination
     * @param params http params
     * @param charsetName response charset
     * @return response
     */
    public static String sendGetRequest(String url, Map<String, String> params, String charsetName) throws IOException {
        HttpClient httpClient = new HttpClient();
		HttpConnectionManagerParams managerParams = httpClient
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(5000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(60000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            nameValuePairList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        NameValuePair[] arr = new NameValuePair[nameValuePairList.size()];
        for (int i = 0; i<arr.length; i++) {
            arr[i] = nameValuePairList.get(i);
        }
        getMethod.setQueryString(arr);
        int statusCode;
        byte [] bytes;
        try {
            statusCode = httpClient.executeMethod(getMethod);
            bytes = getMethod.getResponseBody();
        } finally {
            getMethod.releaseConnection();
        }
        if (statusCode != HttpStatus.SC_OK) {
            throw new HttpException(String.format("response failed : %s", getMethod.getStatusLine()));
        }
        return new String(bytes, charsetName);
    }

    /**
     * send post request
     * @param url destination
     * @param params http params
     * @param charsetName response charset
     * @return response
     */
    public static String sendPostRequest(String url, Map<String, String> params, String  charsetName) throws IOException {
        HttpClient httpClient = new HttpClient();
		HttpConnectionManagerParams managerParams = httpClient
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(5000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(60000);
		PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            postMethod.addParameter(entry.getKey(), entry.getValue());
        }
        int statusCode;
        byte [] bytes;
        try {
            statusCode = httpClient.executeMethod(postMethod);
            bytes = postMethod.getResponseBody();
        } finally {
            postMethod.releaseConnection();
        }
        if (statusCode != HttpStatus.SC_OK) {
            throw new RuntimeException(String.format("response failed : %s", postMethod.getStatusLine()));
        }
        return new String(bytes, charsetName);
    }

    /* java post 数据
    *
    *   使用方法
    *   List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new NameValuePair("msg1","aaaaaaaaa"));
        nameValuePairs.add(new NameValuePair("msg2","bbbbbbbbb"));
        nameValuePairs.add(new NameValuePair("msg3","ccccccccc爱爱爱"));
        System.out.print(sendPost("192.168.0.180","/info.php",80,nameValuePairs));
    *
    * */
    public static Object sendPost(String server, String url, Integer port, List<NameValuePair> nameValuePairList){
		HttpClient client = new HttpClient();
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(5000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(60000);
        client.getHostConfiguration().setHost(server, port, "http");
        PostMethod method = new PostMethod(url);
        NameValuePair[] NameValuePairs = new NameValuePair[nameValuePairList.size()];
        if (nameValuePairList != null && nameValuePairList.size()>0) {
            for (int i = 0; i < nameValuePairList.size(); i++) {
                NameValuePairs[i] = nameValuePairList.get(i);
            }
        }
        method.setRequestBody(NameValuePairs);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		String response = null;
		try {
			client.executeMethod(method);
			response = new String(method.getResponseBodyAsString().getBytes("utf-8"));
		} catch (Throwable e) {
			throw new RuntimeException("rpc call failed!", e);
		} finally {
			if (method != null) method.releaseConnection();
		}
        return response;
    }
}



