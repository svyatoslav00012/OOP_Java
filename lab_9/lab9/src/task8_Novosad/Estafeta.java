package task8_Novosad;

import java.math.BigInteger;
import java.util.Scanner;


public class Estafeta {

	public static void main(String[] args) {
		int n, m, k, i, j, x, y;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		BigInteger[][] field = new BigInteger[n + 1][m + 1];
		boolean[][] snowb = new boolean[n + 1][m + 1];
		for (i = 0; i <= n; ++i)
			field[i][0] = new BigInteger("0");
		for (j = 0; j <= m; ++j)
			field[0][j] = new BigInteger("0");

		for (i = 0; i < k; ++i) {
			x = in.nextInt();
			y = in.nextInt();
			snowb[x][y] = true;
		}

		for (i = 1; i <= n; ++i)
			for (j = 1; j <= m; ++j)
				if (!snowb[i][j])
					if (i == 1 && j == 1)
						field[i][j] = new BigInteger("1");
					else
						field[i][j] = field[i][j - 1].add(
								field[i - 1][j].add(
										field[i - 1][j - 1]
								)
						);
				else field[i][j] = new BigInteger("0");

		System.out.println(field[n][m]);
	}

}
