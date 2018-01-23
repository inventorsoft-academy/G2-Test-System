package com.inventorsoft.service;


public class StudentService {
	private static String studentsData = "src/com/inventorsoft/loginfiles/students.txt";
	private String email;
	private String password;
	private AuthorisationService service = new AuthorisationService(studentsData);

	public StudentService() {
	}

	public StudentService(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public  boolean existEmail(String email) {
		return service.existEmail(email);
	}

	public  boolean register() {
		return service.register(email, password);
	}

	public boolean login(){
		return service.login(email, password);
	}
}
