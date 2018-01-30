package com.inventorsoft.console;

import com.inventorsoft.model.Question;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.TestMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TeacherInterface {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private static String input;

	public static void generalMenu(){

		while(true){
			System.out.println("Logged as teacher. Choose command: \n"+
					"1 - Create test template \n" +
					"2 - Import test template from file \n" +
					"3 - Create test for group \n" +
					"4 - See student test results \n" +
					"5 - Export student test results \n" +
					"6 - Return to previous menu");

			try {
				int command = Integer.parseInt(bufferedReader.readLine());
				switch (command) {
					case 1:
						createTestTemplate();
						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					case 5:

						break;
					case 6:
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

	private static void createTestTemplate() throws IOException{
		System.out.println("Creating test template. Enter number 0 to exit");

		while(true) {
			System.out.println("Enter name of test:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isValidTestName(input)){
				System.out.println("Please, enter valid name");
				continue;
			}
			break;
		}
		String name =  input;

		int count;
		while(true) {
			System.out.println("Enter count of questions:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				count = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Number contains invalid symbols. Please, try again");
				continue;
			}

			break;
		}

		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<String> answers = new ArrayList<>();
		for(int i=0;i<count;i++){

			System.out.println("Enter text of question "+(i+1));
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			String questionText = input;

			int countQ;
			while(true) {
				System.out.println("Enter count of answer variants:");
				input = bufferedReader.readLine();

				if(Validator.isExit(input)){
					return;
				}
				try {
					countQ = Integer.parseInt(input);
				}catch (NumberFormatException e){
					System.out.println("Number contains invalid symbols. Please, try again");
					continue;
				}

				break;
			}

			ArrayList<String> answerVariants = new ArrayList<>();
			for(int j=0;j<countQ;j++) {

				System.out.println("Enter variant "+(j+1));
				input = bufferedReader.readLine();
				if (Validator.isExit(input)) {
					return;
				}
				answerVariants.add(input);
			}


			while(true) {
				System.out.println("Enter right answers of question:");
				input = bufferedReader.readLine();

				if(Validator.isExit(input)){
					return;
				}

				if(!Validator.isAnswer(input)){
					System.out.println("Please, enter valid format: 1,2,3...");
					continue;
				}
				break;
			}
			String rightAnswers =  input;
			Question newQuestion = new Question(questionText,answerVariants);
			questions.add(newQuestion);
			answers.add(rightAnswers);
		}

		Test test = new Test(name,questions,answers);

		if(test.save()) {
			System.out.println("You have successfully created new test. ");
		}else {
			System.out.println("Saving to file failed");
		}
	}
	public static void main(String ... args){
		TeacherInterface.generalMenu();
	}

}
