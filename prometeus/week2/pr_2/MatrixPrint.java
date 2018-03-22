public class MatrixPrint {
	public static void main(String args[]){
		for (int i = 0; i < 5; ++i) {
			for (int j = 1; j <= 5; ++j) {
				String num = (i == j - 1 || j == 5 - i) ? " * " : (i * 5 + j > 9) ? (i * 5 + j) + " " : " " + (i * 5 + j) + " ";
				System.out.print(num);
			}
			System.out.println();
		}
	}
}
      
      