package com.inventorsoft.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorisationService {

	private String dataFile;
	private static List<String> emails;
	private static List<String> passwords;

	AuthorisationService(String dataFile) {
		this.dataFile = dataFile;
		readData(dataFile);
	}

	private static void readData(String dataFile) {
		emails = new ArrayList<String>();
		passwords = new ArrayList<String>();
		File file = new File(dataFile);
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

	public boolean register( String email, String password){
		BufferedWriter out = null;
		try
		{
			FileWriter fstream = new FileWriter(dataFile, true);
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

	public boolean existEmail(String email){
		return emails.contains(email);
	}

	public boolean login( String email, String password){
		readData(dataFile);
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
