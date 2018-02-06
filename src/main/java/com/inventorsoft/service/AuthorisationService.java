package com.inventorsoft.service;

import java.util.ArrayList;
import java.util.List;

public class AuthorisationService {

	private String dataFile;
	private static List<String> emails;
	private static List<String> passwords;

	AuthorisationService(String dataFile) {
		this.dataFile = dataFile;
		readData();
	}

	private void readData() {
		emails = new ArrayList<>();
		passwords = new ArrayList<>();

		List<String> lines = FileManager.readLines(dataFile);
		for(String line: lines) {
			String[] tokens = line.split("[ ]+:[ ]+");
			emails.add(tokens[0].trim());
			passwords.add(tokens[1].trim());
		}

	}

	public boolean register( String email, String password){
		if(FileManager.writeTo(dataFile,"\n" + email + " : " + password )) {
			return true;
		}else {
			return false;
		}
	}

	public boolean existEmail(String email){
		return emails.contains(email);
	}

	public boolean login( String email, String password){
		readData();
		int index = emails.indexOf(email);
		if(index < 0){
			System.out.println("There is no user with such email");
			return false;
		}
		if(!password.equals(passwords.get(index))){
			System.out.println("Password is not correct");
			return false;
		}
		return true;
	}
}
