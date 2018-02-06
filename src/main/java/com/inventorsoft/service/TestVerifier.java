package com.inventorsoft.service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestVerifier {

	/**
	 * @param rAnswers - right answers of test
	 * @param sAnswers - student answers of test
	 * @return mark of test
	 */
	public static float evaluate(List<String> rAnswers, List<String> sAnswers){
		float mark = 0;
		for(int i = 0; i < rAnswers.size(); i++){
			mark += count(rAnswers.get(i),sAnswers.get(i));
		}
		return mark;
	}

	private static float count(String rAnswer, String sAnswer) {
		String [] list1 = rAnswer.split(",");
		String [] list2 = sAnswer.split(",");
		List<Integer> rList = Arrays.stream(list1).map(Integer::parseInt).collect(toList());
		List<Integer> sList = Arrays.stream(list2).map(Integer::parseInt).collect(toList());
		if(sList.size() > rList.size()){
			return 0;
		}
		float value = (float)1/rList.size();
		int count = 0;
		for(Integer answer: rList){
			if(sList.contains(answer)){
				count++;
			}
		}
		return value * count;
	}

	public static void main(String ...args){
		System.out.println("Mark = " + evaluate(Arrays.asList("1,2,3,4"), Arrays.asList("1,2")));
	}
}
