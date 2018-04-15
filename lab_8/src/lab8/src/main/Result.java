package main;

import java.util.ArrayList;

public class Result {

	private ArrayList<String> minLen = new ArrayList<>();
	private ArrayList<String> maxLen = new ArrayList<>();

	public Result(){

	}

	public Result(ArrayList<String> minLen, ArrayList<String> maxLen){
		this.minLen = minLen;
		this.maxLen = maxLen;
	}

	public ArrayList<String> getMinLen() {
		return minLen;
	}

	public ArrayList<String> getMaxLen() {
		return maxLen;
	}
}
