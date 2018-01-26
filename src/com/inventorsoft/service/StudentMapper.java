package com.inventorsoft.service;

import com.inventorsoft.model.Student;

public class StudentMapper {
	private static String studentData = "src/com/inventorsoft/objects/students.txt";
	public static boolean saveStudentData(Student student) {
		return FileManager.writeTo(studentData, student.toString());
	}
}
