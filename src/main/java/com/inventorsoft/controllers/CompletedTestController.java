package com.inventorsoft.controllers;

import com.inventorsoft.mappers.CompletedTestMapper;
import com.inventorsoft.model.CompletedTest;
import com.inventorsoft.service.FileManager;

public class CompletedTestController {
	private static final String TESTS_FOLDER = "src/main/resources/passed_tests/";


	public static CompletedTest getBy(String folderName, String testName){
		String testString = FileManager.readAll(TESTS_FOLDER + folderName + "/" + testName + ".test");
		CompletedTestMapper tm = new CompletedTestMapper();
		return tm.parse(testString);
	}

	public static boolean save(String folderName, CompletedTest test){
		CompletedTestMapper tm = new CompletedTestMapper();
		String data = tm.format(test);
		String directoryName = TESTS_FOLDER.concat(folderName);
		FileManager.writeToFileInDirectory(directoryName, test.getName(), data);
		return true;
	}
}
