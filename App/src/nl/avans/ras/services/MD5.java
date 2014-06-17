package nl.avans.ras.services;

import java.security.MessageDigest;

public class MD5 {
	public static final String SALT = "23kl4h0dfb;l2m4podgulrm23por0dvucg";
	
	public static String hashString(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest((string).getBytes("UTF-8"));
			
			StringBuilder sb = new StringBuilder(2*hash.length);
		    for(byte b : hash){
		    	sb.append(String.format("%02x", b&0xff));
		    }
		    
		    return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
