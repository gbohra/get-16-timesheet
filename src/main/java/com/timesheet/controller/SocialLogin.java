package com.timesheet.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.timesheet.utill.JWTTokenUtill;
import com.timesheet.utill.TokenInfo;
/**
 * 
 * @author Avinash
 * This handle social login  
 */
// use for cross origin request
@CrossOrigin(origins = "http://192.168.100.113:3000")
@Controller
public class SocialLogin {
	
	@Autowired
	UserService userService;

	private GoogleOAuthUtil oauth;

	@SuppressWarnings("unused")
	@RequestMapping(value="/oauth/callback")
	public String AuthenticateToken(@RequestParam("code") String code) throws IOException{
		
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
						"http://localhost:8080/TimeSheet/oauth/callback").execute();

		// Specify the same redirect URI that you use with your web
		// app. If you don't have a web version of your app, you can
		// specify an empty string.


		String accessToken = tokenResponse.getAccessToken();
		String refreshToken = tokenResponse.getRefreshToken();
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
		
		System.out.println("this is id"+id);
		// now store that user in data base and get id from it 
		System.out.println("this is user's id "+id);
		TokenInfo token = new TokenInfo();
		token.setAccessToken(accessToken);
		token.setEmail(email);
		
		token.setName(name);
		
		if(id <= 0){				// user not present in database
			UserModel userModel = new UserModel();
			userModel.setEmail(email);
			String splitName[] = name.split(" ");
			if(splitName[0] != null){
				userModel.setFirstName(splitName[0]);
			}
			if(splitName[1] != null){
				userModel.setLastName(splitName[1]);
			}
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			userModel.setCreatedDate(date);
			userModel.setUpdatedDate(date);
			userModel.setRefreshToken(refreshToken);
			userService.insert(userModel);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true); 
			session.setAttribute("email",email);
			session.setAttribute("id",id);
			session.setAttribute("name",name);
			token.setId(id);
		}
		else{
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true); 
			session.setAttribute("email",email);
			session.setAttribute("id",id);
			token.setId(id);
			if(pictureUrl != null){
				session.setAttribute("picture",pictureUrl);
			}
			session.setAttribute("name",name);
		}
		
		
		String encrString = JWTTokenUtill.getEncrypted(token);
		return "redirect:" + "http://192.168.100.113:3000/?token="+encrString;
	}
	
	@RequestMapping(value="/user/login")
	public String userLogin(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		System.out.println("session email "+session.getAttribute("email"));
		if (session.getAttribute("email") == null){
			oauth = new GoogleOAuthUtil(session);
			String redirectUrl = oauth.createLoginUrl("http://localhost:8080/TimeSheet/oauth/callback");
			return "redirect:" + redirectUrl;
		}else{
			return "redirect:" + "http://localhost:8080/TimeSheet/";
		}	
	}
	
	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	@ResponseBody
	public TokenInfo validate(@RequestBody String token){
		try{
			System.out.println("Token "+token);
			// if wrong token received then this throw error/exception
			TokenInfo tokenInfo = JWTTokenUtill.getDecrypt(token);
			System.out.println("token info"+tokenInfo.getAccessToken());
			System.out.println("token email"+tokenInfo.getEmail());
			if(tokenInfo != null){
				if(tokenInfo.getEmail() == null || tokenInfo.getEmail().equals("")){
					System.out.println(" i am in nukk");
					return null;
				}else{
					
					tokenInfo.setAccessToken(token);
					System.out.println("token set "+ tokenInfo.getAccessToken());
					System.out.println("token name"+tokenInfo.getName());
					System.out.println("this is email"+tokenInfo.getEmail());
					return tokenInfo;
				}
			}else{
				System.out.println(" i am in nukk");
				return null;
			}
		}catch(Exception e){
			System.out.println(" i am in nukk");
			return null;
		}
		
		
	}
}
