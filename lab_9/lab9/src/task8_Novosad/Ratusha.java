package task8_Novosad;

import java.math.BigInteger;
import java.util.Scanner;



public class Ratusha {
	static BigInteger[][] memory = new BigInteger[4][1001];
	static BigInteger recursion(int N, int krok) {
		N=N-krok;
	if (N == 0) { return new BigInteger("1"); }
	if (memory[krok][N] != null) { return memory[krok][N]; }
		BigInteger k = new BigInteger("0");
		for (int i = 1; i <= 3; i++)
			if (i != krok && N - i >= 0)
				k = k.add(recursion(N, i));
		memory[krok][N] = k;
		return k;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(recursion(new Scanner(System.in).nextInt(), 0));
	}

}
