package task8_Novosad;

import java.util.ArrayList;

public class FindWords {

	private SimpleArray<String> str = new SimpleArray<>();

	private ArrayList<String> listwords = new ArrayList<>(1);
	private ArrayList<String> listcompare = new ArrayList<>(1);
	private ArrayList<String> listamounts = new ArrayList<>(1);
	private StringBuilder buffer = new StringBuilder();

	void find(String text) {
		str.add(text);
		//add string in list word
		int point = 0;
		for (int i = 0; i < str.get(0).length(); i++) {
			while (i < str.get(0).length() && str.get(0).charAt(i) != ' ') {
				buffer.append(str.get(0).charAt(i));
				point = 1;
				i++;
			}
			if (point == 1) {
				listwords.add(buffer.toString());
				buffer.replace(0, i, "");//clean buffer
				point = 0;
			}
		}

		//find the same words
		int amount_currword = 0;
		for (int i = 0; i < listwords.size(); i++) {
			amount_currword = 1;
			listcompare.add(i, listwords.get(i));//list without copies
			listamounts.add(i, String.valueOf(amount_currword));//amount of copies
			for (int j = i + 1; j < listwords.size(); j++) {
				if (listcompare.get(i).equals(listwords.get(j))) {
					amount_currword++;
					listamounts.add(i, String.valueOf(amount_currword));
					listwords.remove(j);
					if (j > 0) {
						j--;
					}//because we remove
				}
			}
		}
		System.out.println("It has been calculated");
	}//find()

	public void displayResults() {
		for (int i = 0; i < listcompare.size(); i++) {
			System.out.println(listcompare.get(i) + "  \t" + listamounts.get(i) + "\t");
		}
	}
}
