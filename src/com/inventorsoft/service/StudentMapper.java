package com.inventorsoft.service;

import com.inventorsoft.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentMapper {
	private static String studentData = "src/com/inventorsoft/objects/students.txt";
	public void saveStudentData(Student student) {
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter(studentData, true);
			out = new BufferedWriter(fstream);
			out.write(student.toString());
		}
		catch (IOException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		finally
		{
			try{
				if(out != null) {
					out.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
