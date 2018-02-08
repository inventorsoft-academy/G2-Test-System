package com.inventorsoft.model;

public class Teacher {
	private String fullName;
	private String email;
	private String password;

	public Teacher(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"fullName='" + fullName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
