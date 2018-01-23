package com.inventorsoft.service;


public class StudentService {
	private static final String STUDENT_DATA_FILE = "src/com/inventorsoft/loginfiles/students.txt";

	private static AuthorisationService service = new AuthorisationService(STUDENT_DATA_FILE);

	public static boolean existEmail(String email) {
		return service.existEmail(email);
	}

	public static boolean register(String email,String password) {
		return service.register(email, password);
	}

	public static boolean login(String email,String password){
		return service.login(email, password);
	}
}
