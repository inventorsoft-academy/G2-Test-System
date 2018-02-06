package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class CompletedTest extends Test{

	private ArrayList<String> answers;
	private float mark;

	public CompletedTest(Test test, ArrayList<String> answers, float mark) {
		super(test);
		this.answers = answers;
		this.mark = mark;
	}

	public CompletedTest(String name, ArrayList<Question> questions, ArrayList<String> rightAnswers,
	                     ArrayList<String> answers, float mark) {
		super(name, questions,rightAnswers);
		this.answers = answers;
		this.mark = mark;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public float getMark() {
		return mark;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Test: " + name + "\n");
		for(int i=0; i<questions.size(); i++){
			str.append(questions.get(i));
			str.append("Right answers: " + rightAnswers.get(i));
			str.append("\n");
			str.append("Student answers: " + answers.get(i));
			str.append("\n");
		}
		str.append("Mark: " + mark);
		return str.toString();
	}
}
