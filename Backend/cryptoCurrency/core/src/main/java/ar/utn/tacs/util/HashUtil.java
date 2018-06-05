package ar.utn.tacs.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public final static String SHA_256 = "SHA-256";

//	private MessageDigest messageDigest;

	public HashUtil() {
		super();
//		try {
//			this.messageDigest = MessageDigest.getInstance(SHA_256);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
	}

//	public byte[] getHash(String element) {
//		return this.messageDigest.digest(element.getBytes(StandardCharsets.UTF_8));
//	}
	
	public String getStringHash(String element) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(element.getBytes(StandardCharsets.UTF_8));
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
}
