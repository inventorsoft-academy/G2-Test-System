package com.inventorsoft.console;

import com.inventorsoft.controllers.GroupController;
import com.inventorsoft.controllers.StudentController;
import com.inventorsoft.model.Student;
import com.inventorsoft.service.AuthorisationService;
import com.inventorsoft.session.StudentSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nina on 11.01.2018.
 */
public class ConsoleInterface {

	private static final String STUDENT_DATA_FILE = "src/main/resources/login_files/students.txt";
	private static final String TEACHERS_DATA_FILE = "src/main/resources/login_files/teachers.txt";
	private static AuthorisationService service;

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private static String input;

	public static void main(String[] args){
		System.out.println("Start of Testing Application");
		generalMenu();
	}

	private static void generalMenu() {
		while (true){
			System.out.println("Choose command: \n"+
					"1 - Teacher login \n" +
					"2 - Teacher registration\n" +
					"3 - Student login \n" +
					"4 - Student registration \n" +
					"5 - Exit application");
			try {
				int command = Integer.parseInt(bufferedReader.readLine());
				switch (command) {
					case 1:
						teacherLogin();
						break;
					case 2:
						teacherRegistration();
						break;
					case 3:
						studentLogin();
						break;
					case 4:
						studentRegistration();
						break;
					case 5:
						try {
							bufferedReader.close();
						}catch (IOException e){
							e.printStackTrace();
						}
						return;
					default:
						System.out.println("You entered wrong command number, please try again");
				}
			}catch (IOException e){
				e.printStackTrace(); // log
				System.exit(0);
			}
			catch (NumberFormatException e){
				//e.printStackTrace();    // log
				System.out.println("Input is not a command number, please try again");
			}
		}
	}

	private static void teacherLogin() throws IOException{

		System.out.println("Teacher login. Enter number 0 to exit login");

		System.out.println("Enter email:");
		input = bufferedReader.readLine();
		if(Validator.isExit(input)){
			return;
		}
		String email =  input;

		System.out.println("Enter password:");
		input = bufferedReader.readLine();
		if(Validator.isExit(input)){
			return;
		}
		String password = input;

		service = new AuthorisationService(TEACHERS_DATA_FILE);

		if(service.login(email, password)) {
			System.out.println("You have successfully logged.");
			TeacherInterface.generalMenu();
		}else {
			System.out.println("Login failed");
		}
	}

	private static void teacherRegistration() throws IOException{

		System.out.println("Teacher registration. Enter number 0 to exit registration");

		while(true) {
			System.out.println("Enter name, surname, and patronymic:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Please, enter your full name");
				continue;
			}

			if(!Validator.isFullName(input)){
				System.out.println("Please, enter valid name and surname");
				continue;
			}
			break;
		}
		String nameSurname =  input;

		service = new AuthorisationService(TEACHERS_DATA_FILE);

		while(true) {
			System.out.println("Enter email:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isEmail(input)){
				System.out.println("Please, enter valid email");
				continue;
			}

			if(service.existEmail(input)){
				System.out.println("Teacher with entered email already exists");
				continue;
			}
			break;
		}
		String email =  input;

		while(true) {
			System.out.println("Enter password:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Password is too short. Please, try again");
				continue;
			}
			break;
		}

		String password =  input;

		if(service.register(email, password)) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}

	private static void studentLogin()throws IOException {
		System.out.println("Student login. Enter number 0 to exit login");

		System.out.println("Enter email:");
		input = bufferedReader.readLine();
		if(Validator.isExit(input)){
			return;
		}
		String email =  input;

		System.out.println("Enter password:");
		input = bufferedReader.readLine();
		if(Validator.isExit(input)){
			return;
		}
		String password = input;

		service = new AuthorisationService(STUDENT_DATA_FILE);

		if(service.login(email,password)) {
		StudentSession ss = new StudentSession();
			if(!ss.start(email)){
				System.out.println("Student not found!");
				return;
			}
			System.out.println("Welcome, " + ss.getStudentName() + " !");
			System.out.println("You have successfully logged as student");
			StudentInterface.generalMenu(ss);
		}else {
			System.out.println("Login failed");
		}
	}

	private static void studentRegistration() throws IOException{

		System.out.println("Student registration. Enter number 0 to exit registration");

		while(true) {
			System.out.println("Enter name and surname:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Please, enter your full name");
				continue;
			}

			if(!Validator.isName(input)){
				System.out.println("Please, enter valid name and surname");
				continue;
			}
			break;
		}
		String nameSurname =  input;

		service = new AuthorisationService(STUDENT_DATA_FILE);

		while(true) {
			System.out.println("Enter email:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isEmail(input)){
				System.out.println("Please, enter valid email");
				continue;
			}

			if(service.existEmail(input)){
				System.out.println("Student with entered email already exists");
				continue;
			}
			break;
		}
		String email =  input;

		while(true) {
			System.out.println("Enter password:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Password is too short. Please, try again");
				continue;
			}
			break;
		}
		String password =  input;

		Integer group;
		while(true) {
			System.out.println("Enter group:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isDigit(input)){
				System.out.println("Group number contains invalid symbols. Please, try again");
				continue;
			}

			group = Integer.parseInt(input);
			GroupController controller = new GroupController();
			if(!controller.exists(group)){
				System.out.println("There is no such group");
				continue;
			}
			break;
		}
		Student student = new Student(nameSurname,email,password,group);

		if(StudentController.saveNew(student)){
			System.out.println("Your account data saved");
		}else{
			System.out.println("Failed to save account data");
		}

		if(service.register(email,password)) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}


}
