package com.inventorsoft.model;

import com.inventorsoft.service.FileManager;
import com.inventorsoft.mappers.GroupMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Group {
	private static String groupData = "src/main/resources/objects/groups.txt";
	private Integer groupId;
	private String specialisation;
	private List<String> tests; // templates of tests that group can pass

	public Integer getGroupId() {
		return groupId;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public List<String> getTests() {
		return tests;
	}

	public boolean addTest(String test){
		if(tests.contains(test)){
			return false;
		}
		tests.add(test);
		return true;
	}

	public Group(Integer groupId, String specialisation, List<String> tests) {
		this.groupId = groupId;
		this.specialisation = specialisation;
		this.tests = tests;
	}

	public boolean save(){
		GroupMapper sm = new GroupMapper();
		String data = sm.format(this);
		FileManager.writeTo(groupData, data);
		return true;
	}


	public static Group getBy(Integer groupId){
		List<String> lines = FileManager.readLines(groupData);
		String found = "";
		//find group by id
		for (String line : lines) {
			Pattern p = Pattern.compile(".*"+groupId+".*");
			Matcher m = p.matcher(line);
			if(m.matches()){
				found = line;
			}
		}
		GroupMapper gm = new GroupMapper();
		return gm.parse(found);
	}

	@Override
	public String toString() {
		return "Group{" +
				"groupId=" + groupId +
				", specialisation='" + specialisation + '\'' +
				", tests=" + tests +
				'}';
	}
}
