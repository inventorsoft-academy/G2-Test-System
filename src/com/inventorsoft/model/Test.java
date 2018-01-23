package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class Test {
	private String name;
	private ArrayList<Question> questions;

	public String getName() {
		return name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public Test() {
	}

	public Test(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Test " + name + "\n");
		for(Question question:questions)
			str.append(question);
		return str.toString();
	}
}
