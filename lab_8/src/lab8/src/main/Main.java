package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static final String HELP = FileHelper.readHelp();

    public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String last = null;
        String command;
        Counter counter = new CounterImpl();

        while(true){
			System.out.println("\ncommand : ");
            command = in.nextLine();
            if("-h".equals(command) || "-help".equals(command)){
				System.out.println(HELP);
				command.split("");
			} else if("-d".equals(command) || "-debug".equals(command)){
				System.out.println("DEBUG");
				System.out.println("last command : " + last);
				counter.debug();
			} else if("a".equals(command)){
				counter.readData();
			} else if("b".equals(command)){
				counter.showData();
			} else if("c".equals(command)){
				counter.count();
			} else if("d".equals(command)){
				counter.showResults();
			} else if("e".equals(command)){
				counter.end();
			} else System.out.println("Unknown command");
			last = command;
		}
    }
}
