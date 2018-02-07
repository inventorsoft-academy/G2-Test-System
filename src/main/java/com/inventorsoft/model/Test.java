package com.inventorsoft.model;

import java.util.List;

/**
 * Created by Nina on 14.01.2018.
 */
public class Test {

	protected String name;
	protected List<Question> questions;
	protected List<String> rightAnswers;

	public String getName() {
		return name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<String> getRightAnswers() {
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

	public Test(String name, List<Question> questions, List<String> rightAnswers) {
		this.name = name;
		this.questions = questions;
		this.rightAnswers = rightAnswers;
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
