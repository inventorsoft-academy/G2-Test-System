package com.inventorsoft.service;

import com.inventorsoft.mappers.StudentMapper;
import com.inventorsoft.model.Student;
import com.inventorsoft.service.FileManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentService {

	private static final String STUDENT_DATA = "src/main/resources/objects/students.txt";

	private List<Student> students;

	private StudentMapper studentMapper;

	public StudentService(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
		this.students = getAll();
	}

	private List<Student> getAll(){
		List<String> lines = FileManager.readLines(STUDENT_DATA);
		List<Student> list = new ArrayList<>();
		for(String line : lines){
			if(!line.equals("")){
				list.add(studentMapper.parse(line));
			}
		}
		return list;
	}

	public List<Student> getStudents() {
		return students;
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
	public boolean saveNew(Student student){
		String data = studentMapper.format(student);
		FileManager.writeTo(STUDENT_DATA, data);
		return true;
	}

	/**
	 * updates information about one student
	 * @param student student which will be updated
	 */
	public void update(Student student) {
		for(Student s: students){
			if(s.getEmail().equals(student.getEmail())){
				s = student;
				break;
			}
		}
	}

	/**
	 * saves data about all students, rewriting the file
	 * @return true if successful
	 */
	public boolean saveAll(){
		ArrayList<String> lines = new ArrayList<>();
		for(Student student: students) {
			String line = studentMapper.format(student);
			lines.add(line);
		}
		FileManager.rewrite(STUDENT_DATA, lines);
		return true;
	}
}
