package com.inventorsoft.service;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
	public static boolean writeTo( String fileName, String text){
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(text);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace(); //log
			return false;
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

	//public static String readAll(String fileName){}

	//public static String readLine(String fileName){}

	public static ArrayList<String> readLines(String fileName){
		ArrayList<String> list = new ArrayList<>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));

			String line;
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
