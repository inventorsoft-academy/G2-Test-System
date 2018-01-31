package com.inventorsoft.controllers;

import com.inventorsoft.mappers.StudentMapper;
import com.inventorsoft.model.Student;
import com.inventorsoft.service.FileManager;

import java.util.ArrayList;

public class StudentController {

	public static String STUDENT_DATA = "src/com/inventorsoft/objects/students.txt";

	ArrayList<Student> students;

	public StudentController() {
		this.students = getAll();
	}

	private static ArrayList<Student> getAll(){
		ArrayList<String> lines = FileManager.readLines(STUDENT_DATA);
		StudentMapper sm = new StudentMapper();
		ArrayList<Student> list = new ArrayList<>();
		for(String line : lines){
			if(!line.equals("")){
				list.add(sm.parse(line));
			}
		}
		return list;
	}

	/**
	 * find student data in files and return student object
	 * @param email of student we are looking for
	 * @return student object
	 */
	public Student getBy(String email){
		Student s = null;
		for(Student student: students){
			if(student.getEmail().equals(email)){
				s = student;
				break;
			}
		}
		return s;
	}

	/**
	 * save student data to file
	 * @return true if saved successfully
	 */
	public static boolean saveNew(Student student){
		StudentMapper sm = new StudentMapper();
		String data = sm.format(student);
		FileManager.writeTo(STUDENT_DATA, data);
		return true;
	}

	public boolean saveAll(){
		StudentMapper sm = new StudentMapper();
		ArrayList<String> lines = new ArrayList<>();
		for(Student student: students) {
			String line = sm.format(student);
			lines.add(line);
		}
		FileManager.rewrite(STUDENT_DATA, lines);
		return true;
	}

	public void update(Student student) {
		for(Student s: students){
			if(s.getEmail().equals(student.getEmail())){
				s = student;
				break;
			}
		}
	}
}
