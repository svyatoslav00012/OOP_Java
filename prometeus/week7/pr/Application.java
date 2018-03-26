package com.tasks7.rpn;

import java.util.LinkedList;

public class Application{

	static final String OPS = "*/+-";
	static LinkedList<Double> list  = new LinkedList<>();

	public static double parse(String rpnString){
		String[] coms = rpnString.split(" ");
		for(String s : coms){
			if(OPS.contains(s)){
				Double d2 = list.pollLast();
				Double d1 = list.pollLast();
				if(d1 == null || d2 == null)
					throw new RPNParserException();
				if(s.equals("+")){
					if(Double.isNaN(d1 + d2) || Double.isInfinite(d1 + d2))
						throw new ArithmeticException();
					list.add(d1 + d2);
				}
				if(s.equals("-")){
					if(Double.isNaN(d1 - d2) || Double.isInfinite(d1 - d2))
						throw new ArithmeticException();
					list.add(d1 - d2);
				}
				if(s.equals("*")){
					if(Double.isNaN(d1 * d2) || Double.isInfinite(d1 * d2))
						throw new ArithmeticException();
					list.add(d1 * d2);
				}
				if(s.equals("/")){
					if(Double.isNaN(d1 / d2) || Double.isInfinite(d1 / d2))
						throw new ArithmeticException();
					list.add(d1 / d2);
				}
			} else {
				Double d = null;
				try {
					d = Double.parseDouble(s);
				} catch (NumberFormatException e) {
					throw new RPNParserException();
				}
				list.add(d);
			}
		}
		if(list.isEmpty())
			throw new RPNParserException();
		return list.pollLast();
	}
}
