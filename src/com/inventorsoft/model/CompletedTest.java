package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class CompletedTest extends Test{
	private ArrayList<String> answers;
	private float mark;

	public CompletedTest(ArrayList<String> answers, float mark) {
		this.answers = answers;
		this.mark = mark;
	}

	public CompletedTest(String name, ArrayList<Question> questions, ArrayList<String> rightAnswers,
	                     ArrayList<String> answers, float mark) {
		super(name, questions,rightAnswers);
		this.answers = answers;
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "CompletedTest{" +
				"answers=" + answers +
				", mark=" + mark +
				'}';
	}
}
