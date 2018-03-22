package com.tasks3.carddeck;

import java.util.*;

public class Deck {

	private ArrayList<Card> cards;

	public Deck(){
		cards = new ArrayList<>(36);
		generate();
	}

	public void generate(){
		cards.clear();
		for(Suit s : Suit.values)
			for(Rank r : Rank.values)

				cards.add(new Card(r, s));
	}

	//Перемішує колоду у випадковому порядку
	public void shuffle() {
		ArrayList<Card> cardsBuf = new ArrayList<>();
		while(!cards.isEmpty()) {
			int ind = new Random().nextInt(cards.size());
			cardsBuf.add(cards.get(ind));
			cards.remove(ind);
		}
		cards = new ArrayList<>(cardsBuf);
	}
	/* * Впорядкування колоди за мастями та значеннями
	* Порядок сотрування:
	* Спочатку всі карти з мастю HEARTS, потім DIAMONDS, CLUBS, SPADES
	* для кожної масті порядок наступний: Ace,King,Queen,Jack,10,9,8,7,6
	* Наприклад
	* HEARTS Ace
	* HEARTS King
	* HEARTS Queen
	* HEARTS Jack
	* HEARTS 10
	* HEARTS 9
	* HEARTS 8
	* HEARTS 7
	* HEARTS 6
	* І так далі для DIAMONDS, CLUBS, SPADES */
	public void order() {
		Collections.sort(cards, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				Card card1 = (Card)o1;
				Card card2 = (Card)o2;
				if(Arrays.asList(Suit.values).indexOf(card1.getSuit())
						< Arrays.asList(Suit.values).indexOf(card2.getSuit()))
					return -1;
				else if(Arrays.asList(Suit.values).indexOf(card1.getSuit())
						> Arrays.asList(Suit.values).indexOf(card2.getSuit()))
					return 1;
				else if(Arrays.asList(Rank.values).indexOf(card1.getRank())
						< Arrays.asList(Rank.values).indexOf(card2.getRank()))
					return -1;
				else if(Arrays.asList(Rank.values).indexOf(card1.getRank())
						> Arrays.asList(Rank.values).indexOf(card2.getRank()))
					return 1;
				return 0;
			}
		});
	}

	//Повертає true у випадку коли в колоді ще доступні карти
	public boolean hasNext() {
		return !cards.isEmpty();
	}

	//"Виймає" одну карту з колоди, коли буде видано всі 36 карт повертає null
	//Карти виймаються з "вершини" колоди. Наприклад перший виклик видасть SPADES 6 потім
	//SPADES 7, ..., CLUBS 6, ..., CLUBS Ace і так далі до HEARTS Ace
	public Card drawOne() {
		if(cards.isEmpty())
			return null;
		Card c = cards.get(cards.size() - 1);
		cards.remove(c);
		return c;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Card c : cards)
			sb.append(c + " \n");
		return sb.toString();
	}
}
