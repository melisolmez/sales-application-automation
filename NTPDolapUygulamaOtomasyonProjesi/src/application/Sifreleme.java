package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sifreleme {

	 public static String hashMD5(String password) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(password.getBytes());
	            byte[] digest = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for (byte b : digest) {
	                sb.append(String.format("%02x", b & 0xff));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	 }
}
