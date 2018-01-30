package com.inventorsoft.model;

import com.inventorsoft.service.FileManager;
import com.inventorsoft.mappers.TestMapper;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class Test {
	private static String TESTS_FOLDER = "src/com/inventorsoft/tests/";

	private String name;
	private ArrayList<Question> questions;
	private ArrayList<String> rightAnswers;

	public String getName() {
		return name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public ArrayList<String> getRightAnswers() {
		return rightAnswers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Test() {
	}

	public Test(Test test){
		this.name = test.getName();
		this.questions = test.getQuestions();
		this.rightAnswers = test.getRightAnswers();
	}

	public Test(String name, ArrayList<Question> questions, ArrayList<String> rightAnswers) {
		this.name = name;
		this.questions = questions;
		this.rightAnswers = rightAnswers;
	}

	public boolean save(){
		TestMapper tm = new TestMapper();
		String data = tm.format(this);
		FileManager.writeTo(TESTS_FOLDER + name + ".test" , data);
		return true;
	}

	public static Test getBy(String name){
		String testString = FileManager.readAll("src/com/inventorsoft/tests/" + name);
		TestMapper tm = new TestMapper();
		Test test =  tm.parse(testString);
		test.setName(name);
		return test;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Test: " + name + "\n");
		for(Question question : questions)
			str.append(question);
		return str.toString();
	}
}
