package com.inventorsoft.model;

public class Teacher {
	private String email;
	private String password;

	public Teacher(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
