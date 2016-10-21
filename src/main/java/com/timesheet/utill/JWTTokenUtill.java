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
			return signedJWT.serialize();	
		}catch(Exception e){
			 e.printStackTrace();
			return null;
		}
	}
	
	public static TokenInfo getDecrypt(String encryptedText){
		TokenInfo tokenInfo = new TokenInfo();
		try{
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
		 tokenInfo.setName(signedJWT.getJWTClaimsSet().getStringClaim("access_token"));
		 tokenInfo.setName(signedJWT.getJWTClaimsSet().getStringClaim("user_id"));
		 tokenInfo.setName(signedJWT.getJWTClaimsSet().getStringClaim("email"));
		return tokenInfo;
		}
		catch(Exception e){
			return null;
		}		
	}
}