package org.safaproject.safa.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5 {
	
	 private static Logger log = Logger.getLogger(MD5.class);
	
	 public static String getStringHash(String text) {
		 try{
	        MessageDigest md;
	        md = MessageDigest.getInstance("MD5");
	        byte[] md5hash = new byte[32];
	        md.update(text.getBytes("UTF-8"), 0, text.length());
	        md5hash = md.digest();
	        
	        BigInteger bigInt = new BigInteger(1, md5hash);
			String output = bigInt.toString(16);
			return output;
	    }catch (NoSuchAlgorithmException e) {
			log.error("Error al generar el hash para la url " + text, e);
		}catch (UnsupportedEncodingException e) {
			log.error("Error al generar el hash para la url " + text, e);
		}
		return null;
	 }
}
