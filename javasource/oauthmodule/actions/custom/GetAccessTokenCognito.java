package oauthmodule.actions.custom;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import oauthmodule.proxies.constants.Constants;
import com.google.common.collect.ImmutableMap;
import com.mendix.core.Core;
/**
 * HelperClass for retrieving a accesstoken from Cognito Oauth
 * 
 * @Author: Erwin 't Hoen
 * @version: 1.0
 * @since: 2014-10-02
 */

public class GetAccessTokenCognito extends GetAccessToken {

	private final String CLIENTID = Constants.getClientId_Cognito();
	private final String CLIENTSECRET = Constants.getClientSecret_Cognito();
	private final String CALLBACKURI =  Constants.getCallbackURI_Cognito();
	private final String OAUTHTOKENURI = Constants.getOAuthTokenURI_Cognito();
	private ImmutableMap<String, String> map = ImmutableMap.<String,String>builder()
	.put("code", code)
	.put("client_id", CLIENTID)
	.put("client_secret", CLIENTSECRET)
	.put("redirect_uri", CALLBACKURI)
	.put("grant_type", "authorization_code").build();

	public GetAccessTokenCognito(String code) {
		super(code);
	}

	protected String getResult() throws ClientProtocolException, IOException{
		Core.getLogger("OauthCallback").debug("Get access token from Cognito");
		return PostHttpRequest.post(OAUTHTOKENURI, map);
	}

}
