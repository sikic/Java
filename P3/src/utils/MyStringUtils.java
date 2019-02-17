package utils;

import java.io.*;
import java.util.Scanner;

public class MyStringUtils {

	public static final String inicio = "This file stores a saved 2048 game";
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
		result += elmnt;
		
		}
		return result;
		}
	
		public static String centre(String text, int len){
		String out = String.format(" %"+len+"s %s %"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		return out.substring((int)start, (int)end);
		
		}
		
		public static boolean isNumeric(String cadena) {

	        boolean resultado;

	        try {
	            Integer.parseInt(cadena);
	            resultado = true;
	            
	        } catch (NumberFormatException excepcion) {
	        	
	            resultado = false;
	        }

	        return resultado;
	    }
		
		public static boolean validFileName(String filename) {
		File file= new File(filename);
		if(file.exists()){
			return canWriteLocal(file);
		}
		else
		{
			try
			{
				file.createNewFile();
				file.delete();
				return true;
			}
			catch(Exception e) {
				return false;
		}
	  }
	}

		private static boolean canWriteLocal(File file) {
			try{
			new FileOutputStream(file,true).close();
			}
			catch(IOException e) {
			return false;
			}
			return true;
		}

}
