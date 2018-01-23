package com.inventorsoft.model;

import java.util.ArrayList;

/**
 * Created by Nina on 14.01.2018.
 */
public class Question {
	private String question;
	private ArrayList<String> answerVariants;
	private String rightAnswers;

	public Question(String question, ArrayList<String> answers, String rightAnswer) {
		this.question = question;
		this.answerVariants = answers;
		this.rightAnswers = rightAnswer;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append( "Question " + question + "\n");
		for(String answer:answerVariants)
				str.append(answer + "\n");
		str.append("rightAnswers= " + rightAnswers+"\n");
		return str.toString();
	}
}
