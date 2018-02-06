package com.inventorsoft.mappers;

import com.inventorsoft.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupMapper implements DataMapper<Group> {
	@Override
	public String format(Group group) {
		StringBuilder getTests = new StringBuilder();
		List<String> tests = group.getTests();
		int i = 0;
		for (String test: tests) {
			getTests.append(test);
			if(++i != tests.size()){
				getTests.append("~");
			}
		}
		return group.getGroupId() + "; "
				+ group.getSpecialisation()+ "; "
				+ getTests + "\n";
	}

	@Override
	public Group parse(String line) {
		String[] data = line.split("; ");

		Integer id = Integer.parseInt(data[0].trim());
		String specialisation = data[1].trim();

		List<String> tests = new ArrayList<>();
		//if that group has some tests to pass
		if(data.length == 3) {
			String testsLine = data[2].trim();
			String[] testsArr = testsLine.split("~");
			for (String test : testsArr) {
				if (!test.isEmpty()) {
					tests.add(test.trim());
				}
			}
		}
		return new Group(id, specialisation, tests);
	}
}
