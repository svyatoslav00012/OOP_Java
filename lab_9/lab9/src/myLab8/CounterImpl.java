package myLab8;

import container.MyContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class CounterImpl implements Counter{


	private HashMap<String, Result> sentences = new HashMap<>();

	public void readData(MyContainer c){
		sentences = new HashMap<>();
		System.out.println("Enter your text:");
		Scanner in = new Scanner(System.in);
		c.add(in.nextLine());
	}

	@Override
	public void showData(MyContainer c) {
		System.out.println(c.get(0));
	}

	@Override
	public void count(MyContainer c) {
		ArrayList<String> sents = StringHelper.split(c.get(0), ".");
		for(String s : sents){
			ArrayList<String> words = StringHelper.split(s, " ");

			int mn = 1000000000;
			int mx = 0;

			for(String w : words){
				mn = Math.min(mn, w.length());
				mx = Math.max(mx, w.length());
			}

			ArrayList<String> min = new ArrayList<>();
			ArrayList<String> max = new ArrayList<>();

			for(String w : words)
				if(w.length() == mn)
					min.add(w);
				else if(w.length() == mx)
					max.add(w);

			sentences.put(s, new Result(min, max));
		}
	}

	@Override
	public void showResults() {
		Set<String> sents = sentences.keySet();
		for(String s : sents) {
			Result r = sentences.get(s);
			System.out.println(s + " / " + r.getMinLen() + " / " + r.getMaxLen());
		}
	}

	@Override
	public void end() {
		System.out.println("Bye!");
		System.exit(0);
	}

	public void debug(MyContainer c){
		System.out.println("data : " +
				(c.get(0) == null ? "Data not entered" : c.get(0)));
		System.out.println("Sentences count: " + sentences.size());
	}

}
