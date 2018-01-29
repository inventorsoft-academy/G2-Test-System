package com.inventorsoft.session;

import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.model.Group;
import com.inventorsoft.model.Student;
import com.inventorsoft.model.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentSession {

	private Student student;

	public void initialize(String email){
		student = Student.getBy(email);
	}

	/** get list of test names that can be passed by this group of student
	 * @return list of test names
	 */
	public List<String> getAvailableTests(){
		Group group = Group.getBy(student.getGroup());
		return group.getTests();
	}

	public String getStudentName(){
		return student.getNameSurname();
	}

	public List<String> getPassedTests(){
		return student.getTests();
	}

	/**
	 * unfinished
	 * @param test
	 */
	/*public void passTest(Test test){
		ArrayList<String> answers = test.pass();
		int mark = 0;
		ArrayList<String> rightAns = test.getRightAnswers();
		for (int i=0;i<rightAns.size();i++) {
			if(rightAns.get(i).equals(answers.get(i))){
				mark += 1;
			}
		}
		CompletedTest completedTest = new CompletedTest(answers,mark);
		//add this test to student`s tests
	}*/

}

