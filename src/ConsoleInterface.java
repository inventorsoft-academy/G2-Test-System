import com.inventorsoft.model.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nina on 11.01.2018.
 */
public class ConsoleInterface {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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
						//teacherLogin();
						break;
					case 2:
						teacherRegistration();
						break;
					case 3:
						//studentLogin();
						break;
					case 4:
						//studentRegistration();
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


	private static void teacherRegistration() throws IOException{
		String input;

		System.out.println("Teacher registration. Enter number 1 to exit registration");

		while(true) {
			System.out.println("Enter name and surname:");
			input = bufferedReader.readLine();

			if(isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Please, enter your full name");
				continue;
			}
			//TODO check if name is valid, without digits and other symbols
			//TODO check if user with entered name already exists
			break;
		}
		String nameSurname =  input;

		while(true) {
			System.out.println("Enter password:");
			input = bufferedReader.readLine();

			if(isExit(input)){
				return;
			}

			if(input.length() < 3){
				System.out.println("Password is too short. Please, try again");
				continue;
			}
			break;
		}

		String password =  input;

		if(Teacher.register(nameSurname, password)) {
			System.out.println("You have successfully registered. Please login with your name and password");
		}else {
			System.out.println("Registration failed");
		}
	}

	private static boolean isExit(String input) {
		try{
			if (Integer.parseInt(input) == 1) {
				return true;
			}
		}catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	private static void teacherMenu(){

		while(true){
			System.out.println("Choose command: \n"+
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

						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

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
}
