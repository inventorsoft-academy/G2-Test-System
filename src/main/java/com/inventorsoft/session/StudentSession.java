package com.inventorsoft.session;

import com.inventorsoft.service.CompletedTestService;
import com.inventorsoft.service.StudentService;
import com.inventorsoft.model.Group;
import com.inventorsoft.model.Student;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.TestVerifier;

import java.util.List;

public class StudentSession {

	private Student student;
	private StudentService studentService;

	public boolean start(String email){
		studentService = new StudentService();
		student = studentService.getBy(email);
		return student != null;
	}

	private void finish(){
		studentService.update(student);
		studentService.saveAll();
	}

	/** get list of tests that can be passed by this group of student
	 * @return list of tests' names
	 */
	public List<String> getAvailableTests(){
		Group group = Group.getBy(student.getGroup());
		List<String> tests = group.getTests();
		//remove passed tests from list
		List<String> passedTests = getPassedTests();
		for(String test: passedTests){
			if(tests.contains(test)){
				tests.remove(test);
			}
		}
		return tests;
	}

	public String getStudentName(){
		return student.getNameSurname();
	}

	public List<String> getPassedTests(){
		return student.getTests();
	}

	public com.inventorsoft.model.CompletedTest pass(Test test, List<String> answers){
		float mark = TestVerifier.evaluate(test.getRightAnswers(),answers);
		com.inventorsoft.model.CompletedTest completedTest = new com.inventorsoft.model.CompletedTest(test,answers,mark);
		CompletedTestService.save(student.getEmail(), completedTest);
		student.addCompletedTest(completedTest.getName());
		finish();
		return completedTest;
	}

	public com.inventorsoft.model.CompletedTest findCompletedTest(String testName) {
		return CompletedTestService.getBy(student.getEmail(), testName);
	}
}

