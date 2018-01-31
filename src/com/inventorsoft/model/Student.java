package com.inventorsoft.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 14.01.2018.
 */
public class Student {

	private int id; //create autoincrement
	private String nameSurname;
	private String email;
	private String password;
	private Integer group;
	private List<String> tests; // names of completed tests

	/**
	 * existing student
	 */
	public Student(int id, String nameSurname, String email, String password, Integer group, List<String> tests) {
		this.id = id;
		this.nameSurname = nameSurname;
		this.email = email;
		this.password = password;
		this.group = group;
		this.tests = tests;
	}

	/**
	 * new student
	 */
	public Student(String nameSurname, String email, String password, Integer group) {
		//this.id = increment();
		this.nameSurname = nameSurname;
		this.email = email;
		this.password = password;
		this.group = group;
		tests = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Integer getGroup() {
		return group;
	}

	public List<String> getTests() {
		return tests;
	}

	public void addCompletedTest(String test){
		this.tests.add(test);
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
