package com.inventorsoft.service;

import com.inventorsoft.mappers.CompletedTestMapper;

public class CompletedTestService {
	private static final String TESTS_FOLDER = "src/main/resources/passed_tests/";


	public static com.inventorsoft.model.CompletedTest getBy(String folderName, String testName){
		String testString = FileManager.readAll(TESTS_FOLDER + folderName + "/" + testName + ".test");
		CompletedTestMapper tm = new CompletedTestMapper();
		return tm.parse(testString);
	}

	public static boolean save(String folderName, com.inventorsoft.model.CompletedTest test){
		CompletedTestMapper tm = new CompletedTestMapper();
		String data = tm.format(test);
		String directoryName = TESTS_FOLDER.concat(folderName);
		FileManager.writeToFileInDirectory(directoryName, test.getName(), data);
		return true;
	}
}
