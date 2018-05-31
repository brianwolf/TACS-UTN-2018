package ar.utn.tacs.util;

import java.util.Date;
import java.util.Random;

public class TokenMakerUtil {
	
	public static final Integer LENGHT_STRING_TOKEN = 32; 
	
	public static final String BASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 

	public TokenMakerUtil() {
		super();
	}

	public String makeToken() {

		Date dateConnected = new Date();
		StringBuilder hashResult = new StringBuilder();
		hashResult.append(dateConnected.getTime());
		
		Random rnd = new Random();

		while (hashResult.length() < LENGHT_STRING_TOKEN) { 
			int index = (int) (rnd.nextFloat() * BASE_CHARACTERS.length());
			hashResult.append(BASE_CHARACTERS.charAt(index));
		}

		return hashResult.toString();
	}

}
