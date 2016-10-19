package com.timesheet.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleOAuthUtil {
	private GoogleAuthorizationCodeFlow flow;
	public GoogleOAuthUtil(HttpSession session){
		 
		String CLIENT_SECRET_FILE = getClass().getClassLoader().getResource("client_secret.json").getFile();
		String client_id = null;
		String client_secret = null;
		
		try {
			GoogleClientSecrets clientSecrets =
					GoogleClientSecrets.load(
							JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
			client_id = clientSecrets.getDetails().getClientId();
			client_secret = clientSecrets.getDetails().getClientSecret();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
			
		 
		String access_token = "";
		if(session != null){
			 access_token = (String) session
					.getAttribute("ACCESS_TOKEN");
		}
		
		if(access_token != null){
			GoogleCredential credentials = new GoogleCredential().setAccessToken(access_token);
		}
		
		 flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,
				jsonFactory, client_id, client_secret, Arrays.asList(
						"https://www.googleapis.com/auth/userinfo.profile", 
						"https://www.googleapis.com/auth/userinfo.email"
						))
				.setAccessType("offline")
				.setApprovalPrompt("auto").build();
	}
	public GoogleAuthorizationCodeFlow getFlow() {
		return flow;
	}
	public void setFlow(GoogleAuthorizationCodeFlow flow) {
		this.flow = flow;
	}
	
	public String createLoginUrl(String destinationURL) {
		String loginURL = getFlow().newAuthorizationUrl()
				.setRedirectUri(destinationURL).build();
		return loginURL;
	}
	
}
