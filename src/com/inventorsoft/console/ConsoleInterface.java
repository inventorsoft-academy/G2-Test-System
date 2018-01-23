package com.inventorsoft.console;

import com.inventorsoft.model.Student;
import com.inventorsoft.service.StudentMapper;
import com.inventorsoft.service.StudentService;
import com.inventorsoft.service.TeacherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nina on 11.01.2018.
 */
public class ConsoleInterface {

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
						System.exit(0);
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

		TeacherService teacherService = new TeacherService(email,password);

		if(teacherService.login()) {
			System.out.println("You have successfully logged.");
			TeacherSession.generalMenu();
		}else {
			System.out.println("Login failed");
		}
	}

	private static void teacherRegistration() throws IOException{

		System.out.println("Teacher registration. Enter number 0 to exit registration");

		while(true) {
			System.out.println("Enter email:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isValidEmail(input)){
				System.out.println("Please, enter valid email");
				continue;
			}

			TeacherService teacherService = new TeacherService();

			if(teacherService.existEmail(input)){
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

		TeacherService teacherService = new TeacherService(email, password);

		if(teacherService.register()) {
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

		StudentService studentService = new StudentService(email,password);

		if(studentService.login()) {
			System.out.println("You have successfully logged.");
			//Student Menu
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

			if(!Validator.isValidName(input)){
				System.out.println("Please, enter valid name and surname");
				continue;
			}
			break;
		}
		String nameSurname =  input;

		while(true) {
			System.out.println("Enter email:");
			input = bufferedReader.readLine();

			if(Validator.isExit(input)){
				return;
			}

			if(!Validator.isValidEmail(input)){
				System.out.println("Please, enter valid email");
				continue;
			}

			StudentService studentService = new StudentService();

			if(studentService.existEmail(input)){
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

			if(input.length() < 3){
				System.out.println("Group number is too short. Please, try again");
				continue;
			}
			break;
		}
		String group = input;

		Student student = new Student(nameSurname,email,password,group);

		StudentService studentService = new StudentService(email, password);
		StudentMapper studentMapper = new StudentMapper();
		studentMapper.saveStudentData(student);
		if(studentService.register()) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}


}
