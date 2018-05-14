package lab10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	private static final SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
	private static String HELP;

	//dd.mm.yyyy
	public static Date getTime(String string) {
		return f.parse(string, new ParsePosition(0));
	}

	public static boolean checkTime(String string) {
		return getTime(string) != null;
	}

	public static String HELP() {
		if (HELP == null) {
			try {
				BufferedReader in =
						new BufferedReader(
								new InputStreamReader(
										new FileInputStream(
												new File("help.txt")
										)
								)
						);
				String s;
				StringBuffer sb = new StringBuffer();
				while((s = in.readLine()) != null)
					sb.append(s + "\n");
				HELP = sb.toString();
			} catch (Exception e){
				System.err.println("Error reading help");
			}
		}
		return HELP;
	}
}
