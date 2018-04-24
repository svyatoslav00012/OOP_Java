package container;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Container c = new MyContainer();
		((MyContainer)c).demarshal(new File("obj.dat"));
		((MyContainer)c).sort();
		System.out.println(c);
	}
}
