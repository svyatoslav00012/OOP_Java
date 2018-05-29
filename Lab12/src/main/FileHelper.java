package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileHelper {

	public static String readFile(String filename){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			StringBuffer sb = new StringBuffer();
			String s;
			while((s = in.readLine()) != null)
				sb.append(s + " \n");
			in.close();
			return sb.toString();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static String readReg(){
		String r = readFile("regex.txt");
		return r.substring(0, r.length() - 1);
	}

	public static String readIn(){
		return readFile("input.txt");
	}
}
