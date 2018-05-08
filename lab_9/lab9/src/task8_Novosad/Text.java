package task8_Novosad;

import java.util.Scanner;


public class Text {

	static FindWords find = new FindWords();
	static TextHelper helper = new TextHelper();

	public static void main(String[] args) {
		if ((args.length - 1) == 1 && ("-help".equals(args[0]) || "-h".equals(args[0]))) {
			System.out.print("Student KN-109 Ruslana Novosad\n\t"
					+ "Program will find all copies of words in your text\n\t"
					+ "a- write your text\n\t"
					+ "b- read your text\n\t"
					+ "c- calculate result\n\t"
					+ "d- display result\n\t"
					+ "e- exit or close program\n\t"
					+ "args[0]= -h help\t");

		} else if (args.length - 1 > 1) {
			return;
		}

		Scanner in = new Scanner(System.in);
		String command = "a";

		while (!command.equals("exit")) {
			command = in.nextLine();
		/*if(command == null && command.isEmpty()){
			System.out.println("a- write your text"
					         + "b- read your text"
					          +"c-kfgkfm");
		}else {*/
			if ("a".equals(command)) {
				helper.read();
			} else if ("b".equals(command)) {
				helper.show();
			} else if ("c".equals(command)) {
				find.find(helper.getText());
			} else if ("d".equals(command)) {
				find.displayResults();
			} else if ("e".equals(command) || "exit".equals(command)) {
				return;
			} else {
				System.out.println("Use one of commands");
			}
			//}//else

		}//while

	}

}
