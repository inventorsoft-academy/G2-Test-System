package com.inventorsoft.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


	/**
	 * @return true if input contains only exit code '0'
	 */
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

	/**
	 * @return true if input contains only digits
	 */
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

	/**
	 * @param input - email address
	 * @return true if input is like "emailname@gmail.com"
	 */
	static boolean isEmail(String input){
		Pattern p = Pattern.compile("^.+@.+\\..+$");
		Matcher m = p.matcher(input);
		return  m.matches();
	}

	/**
	 * @param input - name of person
	 * @return true if name is like "Nina Novos"
	 */
	static boolean isName(String input){
		Pattern p = Pattern.compile("[A-Z][a-z]+[ ][A-Z][a-z]+");
		Matcher m = p.matcher(input);
		return  m.matches();
	}

	/**
	 * @param input - full name of person
	 * @return true if name is like "Nina Novos Alex"
	 */
	static boolean isFullName(String input) {
		Pattern p = Pattern.compile("[A-Z][a-z]+[ ][A-Z][a-z]+[ ][A-Z][a-z]+");
		Matcher m = p.matcher(input);
		return  m.matches();
	}

	/**
	 * @param input - name of test file
	 * @return true if name contains only of word character (alphanumeric & underscore)
	 * and whitespace character
	 */
	static boolean isTestName(String input){
		Pattern p = Pattern.compile("(\\w+\\s*)+[^~]");//without ~
		Matcher m = p.matcher(input);
		return  m.matches();
	}

	/**
	 * @param input - test question answer
	 * @return true if input is like "1" or "1,2,3"
	 */
	static boolean isAnswer(String input) {
		try{
			Pattern p = Pattern.compile("(\\d(,)*)+");
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
