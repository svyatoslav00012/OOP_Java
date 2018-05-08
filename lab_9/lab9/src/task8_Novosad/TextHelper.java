package task8_Novosad;

import java.util.Scanner;

public class TextHelper implements Command {
	
	Scanner in = new Scanner(System.in);
	private String text;
	
	public String getText() {
		return text;
	}
	
	public void read() {
		  System.out.print("Write your text: ");
		  text = in.nextLine();
	}
	
	public void show() {
		System.out.println(text);
	}
	
}
