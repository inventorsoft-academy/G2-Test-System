package com.inventorsoft.service;

import com.inventorsoft.model.Question;
import com.inventorsoft.model.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMapper {
	/**
	 * lines that contain one question, its variants, and answers
	 */
	private String[] questions;
	/**
	 * test name(from file name)
	 */
	private String testName;

	private void readQuestionsFromFile(String testName){
		File file = new File("src/com/inventorsoft/tests/"+testName+"");
		this.testName = testName;
		String content;
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			content = scanner.useDelimiter("\\Z").next();
			 questions = content.split("Question ");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}

		}

	}

	private Test createTest() {
		ArrayList<Question> questionsList = new ArrayList<Question>();

		for(int j=1;j<questions.length;j++){
				ArrayList<String> variants = new ArrayList<String>();
				String[]lines = questions[j].split("\n");
				for(int i=1;i<lines.length-1;i++){
					variants.add(lines[i]);
				}
				String lineWithAnswers = lines[lines.length-1];
				int index = lineWithAnswers.indexOf(":");
				String answers = lineWithAnswers.substring(index+1);
				Question q = new Question(lines[0],variants,answers);
				questionsList.add(q);

		}

		return new Test(testName,questionsList);
	}

	public Test getTest(String fileName){
		readQuestionsFromFile(fileName);
		return createTest();
	}

	public boolean saveTest(Test test){
		File file = new File("src/com/inventorsoft/tests/"+test.getName()+".txt");
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter(file, true);
			out = new BufferedWriter(fstream);
			for(Question q : test.getQuestions()){
				out.write(q.toString());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try{
				if(out != null) {
					out.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return true;
	}
}
