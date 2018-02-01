package com.inventorsoft.controllers;

import com.inventorsoft.mappers.GroupMapper;
import com.inventorsoft.model.Group;
import com.inventorsoft.service.FileManager;

import java.util.ArrayList;

public class GroupController {
	private static String GROUP_DATA = "src/com/inventorsoft/objects/groups.txt";

	private ArrayList<Group> groups;

	private GroupMapper groupMapper;

	public GroupController() {
		groupMapper = new GroupMapper();
		this.groups = this.getAll();
	}

	public ArrayList<Group> getGroups(){
		return groups;
	}

	private ArrayList<Group> getAll() {
		ArrayList<String> lines = FileManager.readLines(GROUP_DATA);
		ArrayList<Group> list = new ArrayList<>();
		for(String line : lines){
			if(!line.equals("")){
				list.add(groupMapper.parse(line));
			}
		}
		return list;
	}

	/**
	 * find group data in files and return group object
	 * @param groupId of group we are looking for
	 * @return group object
	 */
	public Group getBy(Integer groupId){
		Group g = null;
		for(Group group: groups){
			if(group.getGroupId().equals(groupId)){
				g = group;
				break;
			}
		}
		return g;
	}

	/** Static, is called without creating controller object, because doesn't need
	 * information about all groups, just writes to end of file.
	 * Save new group data to file.
	 * @return true if saved successfully
	 */
	public static boolean saveNew(Group group){
		GroupMapper groupMapper = new GroupMapper();
		String data = groupMapper.format(group);
		FileManager.writeTo(GROUP_DATA, data);
		return true;
	}

	/**
	 * updates information about one group
	 * @param group group which will be updated
	 */
	public void update(Group group) {
		for(Group g: groups){
			if(g.getGroupId().equals(group.getGroupId())){
				g = group;
				break;
			}
		}
	}

	/**
	 * saves data about all groups, rewriting the file
	 * @return true if successful
	 */
	public boolean saveAll(){
		ArrayList<String> lines = new ArrayList<>();
		for(Group group: groups) {
			String line = groupMapper.format(group);
			lines.add(line);
		}
		FileManager.rewrite(GROUP_DATA, lines);
		return true;
	}

}
