package com.etrade.api;

import com.etrade.etws.oauth.sdk.client.IOAuthClient;
import com.etrade.etws.oauth.sdk.client.OAuthClientImpl;
import com.etrade.etws.oauth.sdk.common.Token;
import com.etrade.etws.sdk.client.ClientRequest;
import com.etrade.etws.sdk.client.Environment;
import com.etrade.etws.sdk.common.ETWSException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class EtradeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtradeAppApplication.class, args);
	}


	public IOAuthClient client = null;
	public ClientRequest request = null;
	public Token token = null;
	public String oauth_consumer_key = "7cb40576e5665a73f99c9ad405709021"; // Your consumer key
	public String oauth_consumer_secret = "6827d6eed8733a090e979cbed8609eae"; // Your consumer secret
	public String oauth_request_token = null; // Request token
	public String oauth_request_token_secret = null; // Request token secret

	@RequestMapping("/")
	public String hello() throws IOException, ETWSException {

		client = OAuthClientImpl.getInstance(); // Instantiate IOAUthClient
		request = new ClientRequest(); // Instantiate ClientRequest
		request.setEnv(Environment.SANDBOX); // Use sandbox environment

		request.setConsumerKey(oauth_consumer_key); //Set consumer key
		request.setConsumerSecret(oauth_consumer_secret); // Set consumer secret
		token= client.getRequestToken(request); // Get request-token object
		oauth_request_token  = token.getToken(); // Get token string
		oauth_request_token_secret = token.getSecret(); // Get token secret

		return "hi guys";
	}
}
