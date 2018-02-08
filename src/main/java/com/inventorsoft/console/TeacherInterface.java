package com.inventorsoft.console;

import com.inventorsoft.service.CompletedTestService;
import com.inventorsoft.service.GroupService;
import com.inventorsoft.service.StudentService;
import com.inventorsoft.service.TestService;
import com.inventorsoft.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TeacherInterface {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private static String input;

	public static void generalMenu(){

		while(true){
			System.out.println("Logged as teacher. Choose command: \n"+
					"1 - Create test template \n" +
					"2 - See test template \n" +
					"3 - Create student group \n" +
					"4 - Assign test for group \n" +
					"5 - See student test results \n" +
					"6 - Return to previous menu");

			try {
				int command = Integer.parseInt(bufferedReader.readLine());
				switch (command) {
					case 1:
						createTestTemplate();
						break;
					case 2:
						seeTestTemplate();
						break;
					case 3:
						createStudentGroup();
						break;
					case 4:
						assignTestForGroup();
						break;
					case 5:
						seeStudentTestResults();
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

	private static void seeTestTemplate() throws IOException{
		TestService controller = new TestService();
		List<String> list = controller.getTestsNames();
		int i = 0;
		System.out.println("List of tests");
		for(String test : list){
			System.out.println(++i +". " + test);
		}
		System.out.println("Chose number of test you want to see");
		int testIndex;
		while(true) {
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				testIndex = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Index contains invalid symbols. Please, try again");
				continue;
			}

			if(testIndex > i){
				System.out.println("Index is bigger then list. Please, try again");
				continue;
			}
			break;
		}
		Test test = controller.getBy(list.get(--testIndex));
		System.out.println(test);
	}

	private static void seeStudentTestResults() throws IOException {
		StudentService studentService = new StudentService();
		List<Student> students = studentService.getStudents();
		System.out.println("List of students:");
		int i = 0;
		for(Student student: students){
			System.out.println(++i + ". " + student.getNameSurname());
		}
		System.out.println("Choose index of student whose test results you want to see");
		int studentIndex;
		while(true) {
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				studentIndex = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Index contains invalid symbols. Please, try again");
				continue;
			}

			if(studentIndex > i){
				System.out.println("Index is bigger then list. Please, try again");
				continue;
			}
			break;
		}
		Student chosenStudent = students.get(--studentIndex);
		List<String> tests = chosenStudent.getTests();
		if(tests.size() == 0){
			System.out.println("This student have no passed tests");
			return;
		}
		System.out.println("List of tests:");
		i = 0;
		for(String test: tests){
			System.out.println(++i + ". " + test);
		}
		System.out.println("Choose index of test you want to see");
		int testIndex;
		while(true) {
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				testIndex = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Index contains invalid symbols. Please, try again");
				continue;
			}

			if(testIndex > i){
				System.out.println("Index is bigger then list. Please, try again");
				continue;
			}
			break;
		}
		String chosenTest = tests.get(--testIndex);
		com.inventorsoft.model.CompletedTest completedTest = CompletedTestService.getBy(chosenStudent.getEmail(),chosenTest);
		System.out.println(completedTest);
	}

	private static void createStudentGroup()throws IOException {
		System.out.println("You are creating student group. Enter number 0 to exit");

		Integer groupId;
		while(true) {
			System.out.println("Enter id of group:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isDigit(input)){
				System.out.println("Please, enter valid id");
				continue;
			}

			groupId = Integer.parseInt(input);
			GroupService controller = new GroupService();
			if(controller.exists(groupId)){
				System.out.println("This group already exist");
				continue;
			}
			break;
		}

		while(true) {
			System.out.println("Enter specialization of group:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isTestName(input)){
				System.out.println("Please, enter valid words");
				continue;
			}
			break;
		}
		String specialization =  input;
		Group group = new Group(groupId, specialization, new ArrayList<>());
		if(GroupService.saveNew(group)) {
			System.out.println("You've successfully created new group");
		}

	}

	private static void assignTestForGroup() throws IOException {

		GroupService groupService = new GroupService();
		List<Group> groups = groupService.getGroups();
		System.out.println("List of groups:");
		int i = 0;
		for(Group group: groups){
			System.out.println(++i + ". " + group.getGroupId());
		}
		System.out.println("Choose index of group you want to add tests to ");
		int groupIndex;
		while(true) {
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				groupIndex = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Index contains invalid symbols. Please, try again");
				continue;
			}

			if(groupIndex > i){
				System.out.println("Index is bigger then list. Please, try again");
				continue;
			}
			break;
		}
		TestService testService = new TestService();
		List<String> tests = testService.getTestsNames();
		System.out.println("List of tests:");
		i = 0;
		for(String test: tests){
			System.out.println(++i + ". " + test);
		}
		System.out.println("Choose index of test you want to add to group");

		int testIndex;
		while(true) {
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}
			try {
				testIndex = Integer.parseInt(input);
			}catch (NumberFormatException e){
				System.out.println("Index contains invalid symbols. Please, try again");
				continue;
			}

			if(testIndex > i){
				System.out.println("Index is bigger then list. Please, try again");
				continue;
			}
			break;
		}
		Group chosenGroup = groups.get(--groupIndex);
		if(chosenGroup.addTest(tests.get(--testIndex))){
			System.out.println("You assigned test to group successfully");
		}
		else {
			System.out.println("This test is already assigned to the group");
		}
		groupService.update(chosenGroup);
		groupService.saveAll();
	}

	private static void createTestTemplate() throws IOException{
		System.out.println("Creating test template. Enter number 0 to exit");

		while(true) {
			System.out.println("Enter name of test:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isTestName(input)){
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

					if(countQ < 2){
						System.out.println("Question must have at least 2 variants");
						continue;
					}
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
				answerVariants.add((j+1) + ") " +input);
			}


			while(true) {
				System.out.println("Enter right answers of question. Format: 1,2,3");
				input = bufferedReader.readLine();

				if(Validator.isExit(input)){
					return;
				}

				if(!Validator.isAnswer(input)){
					System.out.println("Please, enter valid format: 1,2,3");
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

		if(TestService.save(test)) {
			System.out.println("You have successfully created new test. ");
		}else {
			System.out.println("Saving to file failed");
		}
	}
	public static void main(String ... args){
		TeacherInterface.generalMenu();
	}

}
