package com.inventorsoft.controllers;

import com.inventorsoft.mappers.TestMapper;
import com.inventorsoft.model.Test;
import com.inventorsoft.service.FileManager;

public class TestController {
	public static String TESTS_FOLDER = "src/com/inventorsoft/tests/";

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
