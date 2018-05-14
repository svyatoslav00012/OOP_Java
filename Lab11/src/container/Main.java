package container;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();
		Scanner in = new Scanner(System.in);
		String s;
		String[] commands;
		while(true){
			System.out.print("Enter command: ");
			s = in.nextLine();
			commands = s.split(" ");
			if(commands.length == 0)
				continue;
			if(commands[0].equals("-add") && commands.length == 2){
				list.add(commands[1]);
			} else if(commands[0].equals("-rem") && commands.length == 2){
				list.remove(commands[1]);
			} else if(commands[0].equals("-clear") && commands.length == 1){
				list.clear();
			} else if(commands[0].equals("-show") && commands.length == 1){
				System.out.println(list);
			} else if(commands[0].equals("-cont") && commands.length == 2){
				System.out.println(list.contains(commands[1]));
			}
		}
	}
}
