package com.inventorsoft.console;

import com.inventorsoft.controllers.TestController;
import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.model.Question;
import com.inventorsoft.model.Test;
import com.inventorsoft.session.StudentSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	 * student passes the test with given name
	 * in the end they can compare their answers and correct answers
	 * and see mark (count of correctly  answered questions)
	 */
	private static void passTest(String testName)throws IOException{
		Test test = TestController.getBy(testName);
		ArrayList<String> answers = new ArrayList<>(test.getQuestions().size());

		System.out.println("Test: " + testName);
		ArrayList<Question> questions = test.getQuestions();
		for (Question question : questions){
			System.out.print(question);
			while(true){
				input = bufferedReader.readLine();
				if(!Validator.isAnswer(input)) {
					System.err.println("Your input is not correct, please, try again. (Input should be like '1' or '1,2,3')");
					continue;
				}
				break;
			}
			answers.add(input.trim());
		}

		System.out.println("Test finished.");
		CompletedTest res = session.pass(test,answers);
		ArrayList<String> rightAns = test.getRightAnswers();
		for (int i=0; i < rightAns.size(); i++) {
			System.out.println("Question " + (i + 1));
			System.out.println("right answer: " + rightAns.get(i));
			System.out.println("your answer: " + answers.get(i));
		}
		System.out.println("Your mark is: " + res.getMark() );
	}
}
