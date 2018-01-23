package com.inventorsoft.model;

public class Group {
	Integer groupId;
	String specialisation;

	public Group(Integer groupId, String specialisation) {
		this.groupId = groupId;
		this.specialisation = specialisation;
	}

	@Override
	public String toString() {
		return "Group{" +
				"groupId=" + groupId +
				", specialisation='" + specialisation + '\'' +
				'}';
	}
}
