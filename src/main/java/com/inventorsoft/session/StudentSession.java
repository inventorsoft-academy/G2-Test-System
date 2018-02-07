package com.inventorsoft.session;

import com.inventorsoft.controllers.CompletedTestController;
import com.inventorsoft.controllers.StudentController;
import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.model.Group;
import com.inventorsoft.model.Student;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.TestVerifier;

import java.util.List;

public class StudentSession {

	private Student student;
	private StudentController studentController;

	public boolean start(String email){
		studentController = new StudentController();
		student = studentController.getBy(email);
		if(student == null){
			return false;
		}
		return true;
	}

	private void finish(){
		studentController.update(student);
		studentController.saveAll();
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

	public CompletedTest pass(Test test, List<String> answers){
		float mark = TestVerifier.evaluate(test.getRightAnswers(),answers);
		CompletedTest completedTest = new CompletedTest(test,answers,mark);
		CompletedTestController.save(student.getEmail(), completedTest);
		student.addCompletedTest(completedTest.getName());
		finish();
		return completedTest;
	}

	public CompletedTest findCompletedTest(String testName) {
		return CompletedTestController.getBy(student.getEmail(), testName);
	}
}

