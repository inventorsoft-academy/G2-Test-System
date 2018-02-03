package com.inventorsoft.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static boolean rewrite(String fileName, ArrayList<String> list){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			for(String elem : list){
				pw.print(elem);
			}
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}finally {
			if (pw != null) {
				pw.close();
			}
		}
		return true;
	}

	public static <T> boolean writeListTo( String fileName, ArrayList<T> list){
		BufferedWriter out = null;
		try
		{
			out = new BufferedWriter(new FileWriter(fileName, true));
			for(T elem : list){
				out.write(elem.toString());
			}
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

	public static String readAll(String fileName){
		String content = "";
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(fileName));
			content = scanner.useDelimiter("\\Z").next();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return content;
	}

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

	public static void writeToFileInDirectory(String directoryName, String fileName, String data) {
		File directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}

		File file = new File(directoryName + "/" + fileName + ".test");
		try{
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
