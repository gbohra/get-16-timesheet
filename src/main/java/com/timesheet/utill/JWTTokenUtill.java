package com.timesheet.utill;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;


public final class JWTTokenUtill {
	
	public static String getEncrypted(TokenInfo tokenInfo){
		
		try{
			byte[] sharedSecret = new byte[32];
			sharedSecret = "abcdefghijklmnopqrstuvwxyzabcdef".getBytes();
			// Create HMAC signer
			JWSSigner signer = new MACSigner(sharedSecret);
			System.out.println(signer);
			
			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
		     .claim("access_token", tokenInfo.getAccessToken())
		     .claim("user_id", tokenInfo.getId())
		     .claim("email", tokenInfo.getEmail())
		     .claim("name", tokenInfo.getName())
		     .build();

			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
		
			// Apply the HMAC protection
			signedJWT.sign(signer);

			// Serialize to compact form, produces something like
			// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
			String s = signedJWT.serialize();
			System.out.println("this is encrypt " + s);
			return s;	
		}catch(Exception e){
			 e.printStackTrace();
			return null;
		}
	}
	
	public static TokenInfo getDecrypt(String encryptedText){
		TokenInfo tokenInfo = new TokenInfo();
		try{
			System.out.println("decrpti "+ encryptedText);
		byte[] sharedSecret = new byte[32];
		sharedSecret = "abcdefghijklmnopqrstuvwxyzabcdef".getBytes();
		// Create HMAC signer
		JWSSigner signer = new MACSigner(sharedSecret);
		System.out.println(signer);
		
		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
	
		// Apply the HMAC protection
		signedJWT.sign(signer);
		
		// On the consumer side, parse the JWS and verify its HMAC
		signedJWT = SignedJWT.parse(encryptedText);		
		 tokenInfo.setName(signedJWT.getJWTClaimsSet().getStringClaim("name"));
		 
		 tokenInfo.setAccessToken(signedJWT.getJWTClaimsSet().getStringClaim("access_token"));
		 tokenInfo.setId(signedJWT.getJWTClaimsSet().getIntegerClaim("user_id"));
		 //tokenInfo.setId(Integer.parseInt(userId));
		 tokenInfo.setEmail(signedJWT.getJWTClaimsSet().getStringClaim("email"));
		 System.out.println("access token " + tokenInfo.getAccessToken());
		 System.out.println("email id " +tokenInfo.getEmail());
		return tokenInfo;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
}