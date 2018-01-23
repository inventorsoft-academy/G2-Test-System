package com.inventorsoft.service;

/**
 * Created by Nina on 14.01.2018.
 */
public class TeacherService {

	private static final String TEACHERS_DATA_FILE = "src/com/inventorsoft/loginfiles/teachers.txt";

	private static AuthorisationService service = new AuthorisationService(TEACHERS_DATA_FILE);

	public static boolean existEmail(String email){
		return service.existEmail(email);
	}

	public static boolean register(String email,String password){
		return service.register(email, password);
	}

	public static boolean login(String email,String password){
		return service.login(email, password);
	}
}
