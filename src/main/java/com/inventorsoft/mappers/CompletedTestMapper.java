package com.inventorsoft.mappers;

import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.model.Question;

import java.util.ArrayList;
import java.util.List;

public class CompletedTestMapper implements DataMapper<CompletedTest> {

	@Override
	public String format(CompletedTest test) {
		StringBuilder result = new StringBuilder();
		result.append("Test: " + test.getName() + "\n");
		result.append("Mark: " + test.getMark() + "\n");
		List<Question> questions = test.getQuestions();
		List<String> rightAnswers = test.getRightAnswers();
		List<String> answers = test.getAnswers();
		for (int i = 0; i <questions.size(); i++) {
			result.append("Question " );
			result.append(questions.get(i).toString());
			result.append("Right answers: " + rightAnswers.get(i));
			result.append("\n");
			result.append("Student answers: " + answers.get(i));
			result.append("\n");
		}
		return result.toString();
	}

	@Override
	public CompletedTest parse(String test) {
		String[] questions = test.split("Question ");
		ArrayList<Question> questionsList = new ArrayList<>();
		ArrayList<String> rightAnswersList = new ArrayList<>();
		ArrayList<String> answersList = new ArrayList<>();

		String[] nameAndMark = questions[0].split("\n");

		String lineWithName= nameAndMark[0];
		int indexN = lineWithName.indexOf(':');
		String name = lineWithName.substring(indexN+1).trim();

		String lineWithMark= nameAndMark[1];
		int indexM = lineWithMark.indexOf(':');
		String m = lineWithMark.substring(indexM+1).trim();
		float mark = Float.parseFloat(m);

		for(int j=1; j<questions.length ; j++){
			String[]lines = questions[j].split("\n");
			ArrayList<String> variants = new ArrayList<>();
			for(int i=1;i<lines.length-2;i++){
				variants.add(lines[i]);
			}
			Question q = new Question(lines[0],variants);
			questionsList.add(q);

			String lineWithRightAnswers = lines[lines.length-2];
			int index = lineWithRightAnswers.indexOf(':');
			String rightAnswers = lineWithRightAnswers.substring(index+1);
			rightAnswersList.add(rightAnswers.trim());

			String lineWithAnswers = lines[lines.length-1];
			index = lineWithAnswers.indexOf(':');
			String answers = lineWithAnswers.substring(index+1);
			answersList.add(answers.trim());
		}
		return new CompletedTest(name, questionsList, rightAnswersList, answersList, mark);
	}
}
