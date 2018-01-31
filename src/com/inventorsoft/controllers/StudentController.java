package com.inventorsoft.controllers;

import com.inventorsoft.mappers.StudentMapper;
import com.inventorsoft.model.Student;
import com.inventorsoft.service.FileManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentController {

	public static String STUDENT_DATA = "src/com/inventorsoft/objects/students.txt";

	/**
	 * find student data in files and return student object
	 * @param email of student we are looking for
	 * @return student object
	 */
	public static Student getBy(String email){
		ArrayList<String> lines = FileManager.readLines(STUDENT_DATA);
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

	/**
	 * save student data to file
	 * @return true if saved successfully
	 */
	public static boolean save(Student student){
		StudentMapper sm = new StudentMapper();
		String data = sm.format(student);
		FileManager.writeTo(STUDENT_DATA, data);
		return true;
	}
}
