package com.inventorsoft.mappers;

import com.inventorsoft.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper implements DataMapper<Student> {

	/**
	 * create line that represents student
	 * @param student object
	 * @return line
	 */
	public String format(Student student) {
		StringBuilder getTests = new StringBuilder();
		List<String> tests = student.getTests();
		int i = 0;
		for (String test: tests) {
			getTests.append(test);
			if(++i != tests.size()){
				getTests.append("~");
			}
		}
		return student.getId() + "; "
				+ student.getNameSurname()+ "; "
				+ student.getEmail() + "; "
				+ student.getPassword() + "; "
				+ student.getGroup() + "; "
				+ getTests + "\n";
	}

	/**
	 * parse line that represents student
	 * @param line with student data
	 * @return student object
	 */
	public Student parse(String line){
		String[] data = line.split(";");

		int id = Integer.parseInt(data[0].trim());
		String nameSurname = data[1].trim();
		String email = data[2].trim();
		String password = data[3].trim();
		Integer group = Integer.parseInt(data[4].trim());

		List<String> tests = new ArrayList<>();
		//if that student has some completed tests
		if (data.length == 6) {
			String testsLine = data[5].trim();
			String[] testsData = testsLine.split("~");
			for (String test  : testsData) {
				if(!test.isEmpty()){
					tests.add(test.trim()   );
				}
			}
		}
		return new Student(id, nameSurname, email, password, group, tests);
	}

}
