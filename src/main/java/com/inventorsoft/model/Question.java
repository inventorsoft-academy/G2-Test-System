package com.inventorsoft.model;

import lombok.Value;

import java.util.List;

/**
 * Created by Nina on 14.01.2018.
 */
@Value
public class Question {
	private String question;
	private List<String> answerVariants;

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
