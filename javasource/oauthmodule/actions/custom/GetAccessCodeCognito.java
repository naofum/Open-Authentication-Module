package oauthmodule.actions.custom;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import oauthmodule.proxies.constants.Constants;
import com.mendix.core.Core;
/**
 * HelperClass for retrieving a accesscode from Cognito Oauth
 * 
 * @Author: Erwin 't Hoen
 * @version: 1.0
 * @since: 2014-10-02
 */
public class GetAccessCodeCognito {
	
	private final String OAUTHURI = Constants.getOAuthURI_Cognito();
	private final String CLIENTID = Constants.getClientId_Cognito();
	private final String CALLBACKURI = Constants.getCallbackURI_Cognito();
	
	protected void getCode(String UUIDstate, HttpServletResponse servletResponse) throws IOException{
	Core.getLogger("OAuthSignin").trace("Get token from Cognito");
	StringBuilder oauthUrl = new StringBuilder()
			.append(OAUTHURI)
			.append("?client_id=").append(CLIENTID) // the client id from the api console registration
			.append("&response_type=code")
			.append("&scope=openid%20email") // scope is the api permissions we are requesting
			.append("&redirect_uri=").append(CALLBACKURI) // the servlet that cognito redirects to after authorization
			.append("&state="+UUIDstate)
			.append("&access_type=online") // here we are asking to access to user's data while they are not signed in
			.append("&approval_prompt=auto"); // this requires them to verify which account to use, if they are already signed in
	Core.getLogger("OAuthSignin").trace("Url used for cognito: \n"+oauthUrl.toString());
	servletResponse.sendRedirect(oauthUrl.toString());
	}
}
