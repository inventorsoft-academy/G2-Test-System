package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class Question {
	private String question;
	private ArrayList<String> answerVariants;

	public Question(String question, ArrayList<String> answers) {
		this.question = question;
		this.answerVariants = answers;
	}

	/**
	 * @return question and its variants
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append( question + "\n");
		for(String answer : answerVariants)
				str.append( answer + "\n");
		return str.toString();
	}
}
