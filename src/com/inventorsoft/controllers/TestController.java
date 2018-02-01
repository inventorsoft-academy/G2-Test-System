package com.inventorsoft.controllers;

import com.inventorsoft.mappers.TestMapper;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.FileManager;

import java.io.File;
import java.util.ArrayList;

public class TestController {
	private static String TESTS_FOLDER = "src/com/inventorsoft/tests/";

	private ArrayList<String> testsNames;

	public TestController() {
		testsNames = getAll();
	}

	private ArrayList<String> getAll() {
		File folder = new File(TESTS_FOLDER);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> tests = new ArrayList<>();
		for (File f: listOfFiles) {
			if (f.isFile()) {
				tests.add(f.getName());
			}
		}
		return tests;
	}

	public ArrayList<String> getTestsNames(){
		return testsNames;
	}

	public static Test getBy(String name){
		String testString = FileManager.readAll("src/com/inventorsoft/tests/" + name);
		TestMapper tm = new TestMapper();
		Test test =  tm.parse(testString);
		test.setName(name);
		return test;
	}

	public static boolean save(Test test){
		TestMapper tm = new TestMapper();
		String data = tm.format(test);
		FileManager.writeTo(TESTS_FOLDER + test.getName() + ".test" , data);
		return true;
	}
}
