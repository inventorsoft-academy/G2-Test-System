package com.inventorsoft.session;

import com.inventorsoft.controllers.StudentController;
import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.model.Group;
import com.inventorsoft.model.Student;
import com.inventorsoft.model.Test;

import java.util.ArrayList;
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

	public void end(){
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

	public List<String> getPassesTests(){
		return student.getTests();
	}

	public String getStudentName(){
		return student.getNameSurname();
	}

	public List<String> getPassedTests(){
		return student.getTests();
	}

	public CompletedTest pass(Test test, ArrayList<String> answers){
		int mark = 0;
		ArrayList<String> rightAns = test.getRightAnswers();
		for (int i=0; i < rightAns.size(); i++) {
			if(rightAns.get(i).equals(answers.get(i))){
				mark += 1;
			}
		}
		CompletedTest completedTest = new CompletedTest(test,answers,mark);
		student.addCompletedTest(completedTest.getName());
		return completedTest;
	}

}

