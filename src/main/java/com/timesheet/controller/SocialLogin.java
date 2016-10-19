package com.timesheet.controller;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.timesheet.dao.model.UserModel;
import com.timesheet.service.UserService;

@Controller
public class SocialLogin {
	
	@Autowired
	UserService userService;

	private GoogleOAuthUtil oauth;

	@RequestMapping(value="/oauth/callback")
	public void AuthenticateToken(@RequestParam("code") String code) throws IOException{
		
		System.out.println(code);
		String CLIENT_SECRET_FILE = getClass().getClassLoader().getResource("client_secret.json").getFile();
		
		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(
						JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		
		GoogleTokenResponse tokenResponse =
				new GoogleAuthorizationCodeTokenRequest(
						new NetHttpTransport(),
						JacksonFactory.getDefaultInstance(),
						"https://www.googleapis.com/oauth2/v4/token",
						clientSecrets.getDetails().getClientId(),
						clientSecrets.getDetails().getClientSecret(),
						code,
						"http://localhost:8080/TimesheetVersion1/oauth/callback").execute();

		// Specify the same redirect URI that you use with your web
		// app. If you don't have a web version of your app, you can
		// specify an empty string.


		String accessToken = tokenResponse.getAccessToken();
		System.out.println(accessToken);

		// Use access token to call API
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		System.out.println(email);
		String name = (String) payload.get("name");
		System.out.println(name);
		String pictureUrl = (String) payload.get("picture");
		System.out.println("user's email "+email);
		int id = userService.checkEmail(email);
		// now store that user in data base and get id from it 
		System.out.println("this is user's id "+id);
		if(id <= 0){				// user not present in database
			
		}
		else{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true); 
			session.setAttribute("email",email);
			session.setAttribute("id",id);
			if(pictureUrl != null){
				session.setAttribute("picture",pictureUrl);
			}
			session.setAttribute("name",name);
		}
		if(userId != null){
			System.out.println("user id is null");
			UserModel user = new UserModel();
			if(email != null){
				user.setEmail(email);
			}
			if(name != null){
				String lastName = "";
				String[] fullName = name.split(" ");
				user.setFirstName(fullName[0]);
				for(int i = 1 ; i < fullName.length ; ++i){
				//	user += fullName[i]+" ";
				}
				user.setLastName(lastName);
			}
			if(pictureUrl != null){
				//employee.setProfilePicture(pictureUrl);
			}
		}
//		return "redirect:" + redirect to dashboard;
	}
	
	@RequestMapping(value="/user/login")
	public String userLogin(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); 
		if (session == null){
			oauth = new GoogleOAuthUtil(session);
			String redirectUrl = oauth.createLoginUrl("http://localhost:8080/TimesheetVersion1/oauth/callback");
			return "redirect:" + redirectUrl;
		}else{
			return "redirect:" + "http://localhost:8080/TimesheetVersion1/";
		}
		
	}
	
	
	
}
