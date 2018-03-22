package com.tasks6.rle_decoder;

public class Application
{
	public static void main( String[] args )
	{
		StringBuilder sb = new StringBuilder(args.length == 0 ? "" : args[0]);
		for (int i = 0; i < sb.length(); ++i) {
			if(		i < sb.length() - 1 && sb.charAt(i) == sb.charAt(i + 1) ||
					i < sb.length() - 1 && Character.isDigit(sb.charAt(i + 1)) &&
					(sb.charAt(i+1) - '0' < 2 || i < sb.length() - 2 && Character.isDigit(sb.charAt(i + 2)))
					|| Character.isDigit(sb.charAt(0))
					){
				System.out.println();
				return;
			}
			if (i < sb.length() - 1 && Character.isDigit(sb.charAt(i + 1))) {
				int k = sb.charAt(i + 1) - '0';
				StringBuilder sb1 = new StringBuilder();
				for (int j = 1; j < k; ++j)
					sb1.append(sb.charAt(i));
				if (k > 1) {
					sb.delete(i + 1, i + 2);
					sb.insert(i + 1, sb1);
				}
				i += k - 1;
			}
		}
		System.out.println(sb.toString());
	}
}      
      