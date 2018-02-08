package com.inventorsoft.controllers;

import com.inventorsoft.mappers.TestMapper;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestController {

	private static final String TESTS_FOLDER = "src/main/resources/tests/";

	private List<String> testsNames;

	public TestController() {
		testsNames = getAll();
	}

	private ArrayList<String> getAll() {
		File folder = new File(TESTS_FOLDER);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> tests = new ArrayList<>();
		for (File f: listOfFiles) {
			if (f.isFile()) {
				String fileName = f.getName();
				int index = fileName.indexOf(".test");
				tests.add(fileName.substring(0,index));
			}
		}
		return tests;
	}

	public List<String> getTestsNames(){
		return testsNames;
	}

	public Test getBy(String name){
		String testString = FileManager.readAll(TESTS_FOLDER + name + ".test" );
		TestMapper tm = new TestMapper();
		return tm.parse(testString);
	}

	public static boolean save(Test test){
		TestMapper tm = new TestMapper();
		String data = tm.format(test);
		FileManager.writeTo(TESTS_FOLDER + test.getName() + ".test" , data);
		return true;
	}
}
