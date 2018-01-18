package com.inventorsoft.console;

import com.inventorsoft.model.Student;
import com.inventorsoft.model.Teacher;

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
						//studentLogin();
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

	private static void studentRegistration() throws IOException{

		while(true) {
			System.out.println("Enter name and surname:");
			input = bufferedReader.readLine();

			if(isExit()){
				return;
			}

			if(input.length() < 3){
				System.out.println("Please, enter your full name");
				continue;
			}
			//TODO check if name is valid, without digits and other symbols
			break;
		}
		String nameSurname =  input;
		//TODO
		String email="";
		String password="";

		if(Student.register(nameSurname, email, password)) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}

	private static void teacherLogin() throws IOException{

		System.out.println("Teacher login. Enter number 1 to exit login");


		System.out.println("Enter email:");
		input = bufferedReader.readLine();
		if(isExit()){
			return;
		}
		String email =  input;


		System.out.println("Enter password:");
		input = bufferedReader.readLine();
		if(isExit()){
			return;
		}
		String password = input;
		Teacher teacher = new Teacher(email,password);

		if(teacher.login()) {
			System.out.println("You have successfully logged.");
			TeacherLogin.teacherMenu();
		}else {
			System.out.println("Login failed");
		}
	}


	private static void teacherRegistration() throws IOException{

		System.out.println("Teacher registration. Enter number 1 to exit registration");

		while(true) {
			System.out.println("Enter email:");
			input = bufferedReader.readLine();

			if(isExit()){
				return;
			}

			Pattern p = Pattern.compile("^.+@.+\\..+$");
			Matcher m = p.matcher(input);

			if(!m.matches()){
				System.out.println("Please, enter valid email");
				continue;
			}
			//TODO check if user with entered email already exists
			break;
		}
		String email =  input;

		while(true) {
			System.out.println("Enter password:");
			input = bufferedReader.readLine();

			if(isExit()){
				return;
			}

			if(input.length() < 3){
				System.out.println("Password is too short. Please, try again");
				continue;
			}
			break;
		}

		String password =  input;

		if(Teacher.register(email, password)) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}

	private static boolean isExit() {
		try{
			if (Integer.parseInt(input) == 1) {
				return true;
			}
		}catch (NumberFormatException e){
			return false;
		}
		return false;
	}

}
