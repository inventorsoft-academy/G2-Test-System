package com.inventorsoft.console;

import com.inventorsoft.model.Test;
import com.inventorsoft.session.StudentSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentInterface {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private static String input;
	private static StudentSession session;
	public static void generalMenu(StudentSession ss){
		session = ss;
		while(true){
			System.out.println(" Choose command: \n"+
					"1 - See available tests to pass \n" +
					"2 - See passed tests results \n" +
					"3 - Export passed tests results \n" +
					"4 - Return to previous menu");
			try {
				int command = Integer.parseInt(bufferedReader.readLine());
				switch (command) {
					case 1:
						seeAvailableTests();
						break;
					case 2:

						break;
					case 3:

						break;
					case 4:
						return;
					default:
						System.out.println("You entered wrong command number, please try again");
				}
			}catch (IOException e){
				e.printStackTrace();
			}
			catch (NumberFormatException e){
				System.out.println("Input is not a command number, please try again");
			}
		}
	}

	private static void seeAvailableTests()throws IOException {
		List<String> list = session.getAvailableTests();
		System.out.println("List of available tests to pass: ");
		int i = 0;
		for (String test: list) {
			System.out.println(++i + ". " + test);
		}
		while(true) {
			System.out.println("Enter number of test you want to pass: ");
			input = bufferedReader.readLine();

			if(!Validator.isDigit(input)){
				System.out.println("Please, enter valid number");
				continue;
			}
			int index = Integer.parseInt(input);
			if(index < 0 || index > i){
				System.out.println("Please, enter valid number");
				continue;
			}

			passTest(list.get(index-1));
			return;
		}
	}

	/**
	 * unfinished
	 * @param testName
	 */
	private static void passTest(String testName){
		Test test = Test.getBy(testName);
		System.out.println(test);
	}
}
