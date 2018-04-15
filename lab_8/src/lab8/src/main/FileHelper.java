package main;

import java.io.*;

public class FileHelper{

	public static String readHelp(){
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									new File("help.txt")
							)
					)
			);

			StringBuffer sb = new StringBuffer();
			String s;
			while((s = reader.readLine()) != null)
				sb.append(s + "\r\n");

			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error reading helper";
	}

}
