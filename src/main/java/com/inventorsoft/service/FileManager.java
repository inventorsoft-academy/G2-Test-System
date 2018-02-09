package com.inventorsoft.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Component //TODO
public class FileManager {
	public static boolean writeTo( String fileName, String text){

		try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true)) ) {
			out.write(text);
			return true;
		}
		catch (IOException e) {
			e.printStackTrace(); //log
			return false;
		}
	}

	public static boolean rewrite(String fileName, List<String> list){

		try(PrintWriter pw = new PrintWriter(fileName)) {
			for(String elem : list){
				pw.print(elem);
			}
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static <T> boolean writeListTo( String fileName, List<T> list){

		try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {
			for(T elem : list){
				out.write(elem.toString());
			}
			return true;
		}
		catch (IOException e) {
			e.printStackTrace(); //log
			return false;
		}
	}

	public static String readAll(String fileName){

		String content = "";
		try (Scanner scanner = new Scanner(new File(fileName)) ){
			content = scanner.useDelimiter("\\Z").next();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static List<String> readLines(String fileName){
		ArrayList<String> list = new ArrayList<>();
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = in.readLine()) != null) {
				list.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void writeToFileInDirectory(String directoryName, String fileName, String data) {
		File directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}

		File file = new File(directoryName + "/" + fileName + ".test");
		try(
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw)
		){
			bw.write(data);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	public List<String> getListOfFiles(String directoryName){
		File folder = new File(directoryName);
		File[] listOfFiles = folder.listFiles();
		List<String> list = new LinkedList<>();
		if(listOfFiles == null){
			return list;
		}
		for (File f: listOfFiles) {
			if (f.isFile()) {
				String fileName = f.getName();
				list.add(fileName);
			}
		}
		return list;
	}
}
