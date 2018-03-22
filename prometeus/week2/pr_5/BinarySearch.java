public class BinarySearch {

	public static void main(String[] args) {

		int data[] = { 3, 6, 7, 10, 34, 56, 60 };
		int numberToFind = 10;

		int l = 0;
		int r = data.length - 1;
		int m = -1;

		while(l <= r){
			m = (l + r) / 2;
			if(data[m] == numberToFind){
				System.out.println(m);
				return;
			} else
			if(data[m] < numberToFind)
				l = m + 1;
			else if (data[m] > numberToFind)
				r = m - 1;
		}
		System.out.println(-1);
        
	}
}
      