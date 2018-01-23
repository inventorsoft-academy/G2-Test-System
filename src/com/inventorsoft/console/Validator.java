package com.inventorsoft.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


	static boolean isExit(String input) {
		try{
			if (Integer.parseInt(input) == 0) {
				return true;
			}
		}catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	static boolean isDigit(String input) {
		try{
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(input);
			if(m.matches()){
				return true;
			}

		}catch (NumberFormatException e){
			return false;
		}
		return false;
	}
}
