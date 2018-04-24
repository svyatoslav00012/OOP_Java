package myLab8;

import java.util.ArrayList;

public class StringHelper {

	public static ArrayList<String> split(String s, String regex){
		ArrayList<String> parts = new ArrayList<>();
		StringBuffer sb = new StringBuffer(s + regex);
		while(sb.length() > 0){
			if(sb.indexOf(regex) > 0)
				parts.add(sb.substring(0, sb.indexOf(regex)));
			sb.delete(0, sb.indexOf(regex) + regex.length());
		}

		return parts;
	}
}
