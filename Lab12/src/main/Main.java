package main;

import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static final String REGEX = FileHelper.readReg();
	private static final String INPUT = FileHelper.readIn();

	public static void main( String args[] ) {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(INPUT);
		int count = 0;

		while(m.find()) {
			count++;
			System.out.println("Match number "+count);
			System.out.println("start(): "+m.start());
			System.out.println("end(): "+m.end());
		}
	}

//	public static void main(String[] args) {
//		String input = FileHelper.readFile("input.txt");
//		String regex = FileHelper.readFile("regex.txt");
//		Matcher m = Pattern.compile(regex).matcher(input);
//		while(m.find()){
//			System.out.println(m.start() + " " + m.end());
//		}
//	}
}
