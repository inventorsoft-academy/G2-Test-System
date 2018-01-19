package com.inventorsoft.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 14.01.2018.
 */
public class Teacher {

	private static String teachersData = "src/com/inventorsoft/files/teachers.txt";
	private String email;
	private String password;
	private static List<String> emails;
	private static List<String> passwords;

	public Teacher(String email, String password) {
		this.email = email;
		this.password = password;
		readData();
	}

	private static void readData() {
		emails = new ArrayList<String>();
		passwords = new ArrayList<String>();
		File file = new File(teachersData);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text;

			while ((text = reader.readLine()) != null) {
				String[] tokens = text.split("[ ]+:[ ]+");
				emails.add(tokens[0].trim());
				passwords.add(tokens[1].trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean existEmail(String email){
		readData();
		return emails.contains(email);
	}

	public static boolean register(String email, String password){
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter(teachersData, true);
			out = new BufferedWriter(fstream);
			out.write("\n" + email +" : "+password);
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
		return true;
	}


	public boolean login(){
		int index = emails.indexOf(email);
		if(index < 0){
			System.out.println("There is no user with such email");
			return false;
		}
		if(!password.equals(passwords.get(index))){
			System.out.println("Password is not correct");
			return false;
		}
		return true;
	}
}
