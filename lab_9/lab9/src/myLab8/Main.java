package myLab8;

import container.MyContainer;

import java.util.Scanner;

public class Main {

	public static final String HELP = FileHelper.readHelp();

    public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String last = null;
        String command;
        Counter counter = new CounterImpl();
		MyContainer container = new MyContainer();

        while(true){
			System.out.println("\ncommand : ");
            command = in.nextLine();
            if("-h".equals(command) || "-help".equals(command)){
				System.out.println(HELP);
				command.split("");
			} else if("-d".equals(command) || "-debug".equals(command)){
				System.out.println("DEBUG");
				System.out.println("last command : " + last);
				counter.debug(container);
			} else if("a".equals(command)){
				counter.readData(container);
			} else if("b".equals(command)){
				System.out.println(container.toString());
			} else if("c".equals(command)){
				counter.count(container);
			} else if("d".equals(command)){
				counter.showResults();
			} else if("e".equals(command)){
				counter.end();
			} else System.out.println("Unknown command");
			last = command;
		}
    }
}
