package com.inventorsoft.model;

import com.inventorsoft.service.FileManager;
import com.inventorsoft.service.StudentMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nina on 14.01.2018.
 */
public class Student {
	private static String studentData = "src/com/inventorsoft/objects/students.txt";

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

	public void setGroup(Integer group) {
		this.group = group;
	}

	public void setTests(ArrayList<String> tests) {
		this.tests = tests;
	}

	/**
	 * save student data to file
	 * @return true if saved successfully
	 */
	public boolean save(){
		StudentMapper sm = new StudentMapper();
		String data = sm.format(this);
		FileManager.writeTo(studentData, data);
		return true;
	}

	/**
	 * find student data in files and return student object
	 * @param email of student we are looking for
	 * @return student object
	 */
	public static Student getBy(String email){
		ArrayList<String> lines = FileManager.readLines(studentData);
		String found = "";
		//find student by email
		for (String line : lines) {
			Pattern p = Pattern.compile(".+"+email+".+");
			Matcher m = p.matcher(line);
			if(m.matches()){
				found = line;
			}
		}
		StudentMapper sm = new StudentMapper();
		Student student = sm.parse(found);
		return student;
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
