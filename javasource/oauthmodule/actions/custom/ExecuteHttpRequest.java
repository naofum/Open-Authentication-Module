package oauthmodule.actions.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
/**
 * HelperClass for execution of http requests
 * 
 * @Author: Erwin 't Hoen
 * @version: 1.0
 * @since: 2014-10-02
 */
public class ExecuteHttpRequest {
	protected String execute(HttpRequestBase request) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity);

		if (response.getStatusLine().getStatusCode() != 200) {
			httpClient.getConnectionManager().shutdown();
			throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
		}
		httpClient.getConnectionManager().shutdown();
		return body;
	}

	protected String execute(HttpRequestBase request, String token) throws ClientProtocolException, IOException {
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Authorization", "Bearer " + token));
		HttpClient httpClient = HttpClientBuilder.create()
			.setDefaultHeaders(headers).build();
		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity);

		if (response.getStatusLine().getStatusCode() != 200) {
			httpClient.getConnectionManager().shutdown();
			throw new RuntimeException("Expected 200 but got " + response.getStatusLine().getStatusCode() + ", with body " + body);
		}
		httpClient.getConnectionManager().shutdown();
		return body;
	}
}
