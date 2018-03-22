public class ArraySort {

	public static void main(String[] args) {
		int[] array = {30, 2, 10, 4, 6};
		int length = array.length;

		for(int i = 1, j = i; i < length; ++i, j = i)
			while(j > 0 && array[j] < array[j-1]){
				int t = array[j];
				array[j] = array[j-1];
				array[j-1] = t;
				j--;
			}

		for (int i = 0; i < length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}        
		
      