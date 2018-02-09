package com.inventorsoft.service;

import com.inventorsoft.mappers.TestMapper;
import com.inventorsoft.model.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestService {

	private static final String TESTS_FOLDER = "src/main/resources/tests/";
	private static final String TEST_FILE_EXTENSION = ".test";

	private List<String> testsNames;
	private FileManager fileManager;

	public TestService(FileManager fileManager) {
		this.fileManager = fileManager;
		testsNames = getAll();
	}

	private ArrayList<String> getAll() {
		ArrayList<String> tests = new ArrayList<>();
		List<String> listOfFiles = fileManager.getListOfFiles(TESTS_FOLDER);
		for (String f: listOfFiles) {
			int index = f.indexOf(TEST_FILE_EXTENSION);
			tests.add(f.substring(0,index));
		}
		return tests;
	}

	public List<String> getTestsNames(){
		return testsNames;
	}

	public Test getBy(String name){
		String testString = FileManager.readAll(TESTS_FOLDER + name + TEST_FILE_EXTENSION);
		TestMapper tm = new TestMapper();
		return tm.parse(testString);
	}

	public boolean save(Test test){
		TestMapper tm = new TestMapper();
		String data = tm.format(test);
		FileManager.writeTo(TESTS_FOLDER + test.getName() + TEST_FILE_EXTENSION, data);
		return true;
	}
}
