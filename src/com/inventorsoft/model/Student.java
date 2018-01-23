package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class Student {
	private String nameSurname;
	private String email;
	private String password;
	private Group group;
	private ArrayList<CompletedTest> tests;

	public Student(String nameSurname, String email, String password, Group group) {
		this.nameSurname = nameSurname;
		this.email = email;
		this.password = password;
		this.group = group;
		tests = new ArrayList<CompletedTest>();
	}

	@Override
	public String toString() {
		return "Student{" +
				"nameSurname='" + nameSurname + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", group='" + group + '\'' +
				", tests=" + tests +
				'}';
	}
}
