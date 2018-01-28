package com.inventorsoft.model;

import com.inventorsoft.service.FileManager;
import com.inventorsoft.service.GroupMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Group {
	private static String groupData = "src/com/inventorsoft/objects/groups.txt";
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
		ArrayList<String> lines = FileManager.readLines(groupData);
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
		Group group = gm.parse(found);
		return group;
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
