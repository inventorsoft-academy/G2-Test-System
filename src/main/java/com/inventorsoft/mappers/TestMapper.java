package com.inventorsoft.mappers;

import com.inventorsoft.model.Question;
import com.inventorsoft.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMapper implements DataMapper<Test> {

	@Override
	public String format(Test test) {
		StringBuilder result = new StringBuilder();
		result.append("Test: " + test.getName() + "\n");
		List<Question> questions = test.getQuestions();
		List<String> answers = test.getRightAnswers();
		int j = 1;
		for (int i = 0; i <questions.size(); i++) {
			result.append("Question " + j++ + ". ");
			result.append(questions.get(i).toString());
			result.append("Right answers: " + answers.get(i));
			result.append("\n");
		}
		return result.toString();
	}

	@Override
	public Test parse(String test) {
		String[] questions = test.split("Question ");
		ArrayList<Question> questionsList = new ArrayList<>();
		ArrayList<String> answersList = new ArrayList<>();

		String lineWithName= questions[0];
		int indexN = lineWithName.indexOf(':');
		String name = lineWithName.substring(indexN+1).trim();

		for(int j=1; j<questions.length ; j++){
			String[]lines = questions[j].split("\n");
			ArrayList<String> variants = new ArrayList<>();
			for(int i=1;i<lines.length-1;i++){
				variants.add(lines[i]);
			}
			String lineWithAnswers = lines[lines.length-1];
			int index = lineWithAnswers.indexOf(':');
			String answers = lineWithAnswers.substring(index+1);
			answersList.add(answers.trim());
			Question q = new Question(lines[0],variants);
			questionsList.add(q);
		}
		return new Test(name, questionsList, answersList);
	}
}
