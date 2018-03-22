package com.tasks3.fibonacci;

public class Fibonacci
{
	public long getNumber(int position){
				if(position < 1 || position > 92)
			return -1;
		if(position < 3)
			return 1;
		long p1 = 1, p2 = 1;
		for(int i = 3; i <= position; ++i) {
			long p = p1;
			p1 += p2;
			p2 = p;
		}
		return p1;
	}
}
      