package com.tasks6.rle;

public class Application
{
	public static void main( String[] args )
	{
    		StringBuilder sb = new StringBuilder(args.length == 0 ? "" : args[0]);
		for(int i = 0; i < sb.length(); ++i){
			int k = 0;
			for(int j = i; j < sb.length() && sb.charAt(j) == sb.charAt(i) && k < 9; ++j, ++k);
			if(k > 1){
				sb.delete(i + 1, i + k);
				sb.insert(i + 1, k);
			}
		}
		System.out.println(sb.toString());
	}
}      
      