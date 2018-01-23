package com.inventorsoft.service;

/**
 * Created by Nina on 14.01.2018.
 */
public class TeacherService {

	private static String teachersData = "src/com/inventorsoft/loginfiles/teachers.txt";
	private String email;
	private String password;
	private AuthorisationService service = new AuthorisationService(teachersData);

	public TeacherService() {
	}

	public TeacherService(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public boolean existEmail(String email){
		return service.existEmail(email);
	}

	public boolean register(){
		return service.register(email, password);
	}

	public boolean login(){
		return service.login(email, password);
	}
}
