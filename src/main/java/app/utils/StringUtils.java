/**
 * 
 */
package app.utils;

import javax.xml.bind.DatatypeConverter;

/**
 * @author tuanhiep225
 *
 */
public class StringUtils {
	
	
	
	public static String encryptWithXor(String input) {
		int key = 5; //Can be any chars, and any length array
		byte[] inputBytes = input.getBytes();
		byte[] outputBytes = new byte[inputBytes.length];
		for(int i = 0; i < inputBytes.length; i++) {
			byte x = (byte) (inputBytes[i] ^ key);
			outputBytes[i] = x;
		}
		return DatatypeConverter.printHexBinary(outputBytes).toLowerCase();
	}
	
	public static String decryptWithXor(String input) {
		int key = 5; //Can be any chars, and any length array
		byte[] inputBytes = DatatypeConverter.parseHexBinary(input);
		char[] outputBytes = new char[inputBytes.length];
		for(int i = 0; i < inputBytes.length; i++) {
			char x = (char) (inputBytes[i] ^ key);
			outputBytes[i] = x;
		}
		return String.valueOf(outputBytes);
	}
	
	public static String convertNumber(int number) {
		if(number>10000) {
			double rs = number/1000.0;
			return String.valueOf(Math.round(rs*10) /10.0)+ "k";
		}
		return String.valueOf(number);
	}

	 public static void main(String[] args) {
//		 	System.out.println(encryptWithXor("+840984599264"));
//	    	System.out.println(encryptWithXor("123456a@"));
		 System.out.println(convertNumber(112660));
	    }
}
