
import java.util.Random;

public class Player {

	private Card[] hand;
	private String name;
	private int score;

	public Player() {
	};

	public Player(String name) {
		this.name = name;
		score = 0;
		hand = new Card[10];
	}

	public Player(String name, int score) {
		this.name = name;
		this.score = score;
		hand = new Card[10];
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	/*
	 * Add cards "c" to player's hand and sort them.
	 */
	public void setCards(Card c[]) {
		int i = 0;
		for (; hand[i] != null; i++) {

		}
		for (int j = 0; j < c.length; j++)
			hand[i + j] = c[j];
		sortCards();
	}

	public String printMessage() {
		return (name + " please select a card : ");
	}

	/*
	 * Call player to select a card by considering the cards on the table and hand.
	 */
	public Card selectCard(int select, Table t) {
		if (select == -1) {

			int[] difference = new int[hand.length];

			for (int i = 0; i < hand.length; i++) {
				int handNumber = hand[i].getNumber();

				for (int j = 0; j < 4; j++) {
					int tableNumber = 0;
					for (int k = 0; t.getTable()[j][k] != null && k < 5; k++)
						tableNumber = t.getTable()[j][k].getNumber();
					int sub = handNumber - tableNumber;

					if (j == 0)
						difference[i] = sub;
					else if (sub > 0 && sub < Math.abs(difference[i]))
						difference[i] = sub;
					else if (sub < 0 && sub > difference[i])
						difference[i] = sub;
				}
			}

			select = 0;
			int selectPos = 10;
			int selectDiff = 104;
			boolean flag = false;
			Random rnd = new Random();

			for (int i = 0; i < hand.length; i++) {
				if (difference[i] < 0)
					continue;
				int num = hand[i].getNumber() - difference[i];
				int pos = t.findCard(new Card(num, 0))[1];

				if (pos == 0) {
					if (pos < selectPos || (pos == selectPos && difference[i] < selectDiff)) {
						select = i;
						selectPos = 0;
						selectDiff = difference[i];
						flag = true;
					}
				} else if (pos == 1 && difference[i] < rnd.nextInt(7) + 10) {
					if (pos < selectPos || (pos == selectPos && difference[i] < selectDiff)) {
						select = i;
						selectPos = 1;
						selectDiff = difference[i];
						flag = true;
					}
				} else if (pos == 2 && difference[i] < rnd.nextInt(5) + 3) {
					if (pos < selectPos || (pos == selectPos && difference[i] < selectDiff)) {
						select = i;
						selectPos = 2;
						selectDiff = difference[i];
						flag = true;
					}
				} else if (pos == 3 && difference[i] < rnd.nextInt(3) + 2) {
					if (pos < selectPos || (pos == selectPos && difference[i] < selectDiff)) {
						select = i;
						selectPos = 3;
						selectDiff = difference[i];
						flag = true;
					}
				}
			}

			if (flag == false) {
				
				selectPos = -1;
				selectDiff = 0;

				for (int i = 0; i < hand.length; i++) {
					if (difference[i] < 0)
						continue;
					int num = hand[i].getNumber() - difference[i];
					int pos = t.findCard(new Card(num, 0))[1];

					if (pos == 2 && difference[i] > rnd.nextInt(10) + 25) {
						if (pos > selectPos || (pos == selectPos && difference[i] > selectDiff)) {
							select = i;
							selectPos = 2;
							selectDiff = difference[i];
							flag = true;
						}
					} else if (pos == 3 && difference[i] > rnd.nextInt(10) + 30) {
						if (pos > selectPos || (pos == selectPos && difference[i] > selectDiff)) {
							select = i;
							selectPos = 3;
							selectDiff = difference[i];
							flag = true;
						}
					}
				}
			}

			if (flag == false) {

				int min = t.countBulls(0);
				for (int i = 1; i < 4; i++)
					if (t.countBulls(i) < min)
						min = t.countBulls(i);

				if (min < rnd.nextInt(3) + 1) {
					selectDiff = 104;
					for (int i = 0; i < hand.length; i++)
						if (difference[i] < selectDiff) {
							select = i;
							flag = true;
						}

				} else {
					selectDiff = -104;
					for (int i = 0; i < hand.length; i++)
						if (difference[i] < 0 && difference[i] > selectDiff) {
							select = i;
							flag = true;
						}
				}
			}

			if (flag == false) {
				for (int i = 0; i < hand.length; i++)
					if (difference[i] > 0 && difference[i] > selectDiff) {
						select = i;
						flag = true;
					}
			}
			
			if(flag ==false)
				select = rnd.nextInt(hand.length);

			System.out.print(name + " : ");
			for (int i = 0; i < hand.length; i++)
				System.out.print(difference[i] + " ");
			System.out.println("");
		}

		Card result = hand[select];
		System.out.print(result.getNumber() + "\n");
		remove(select);
		return result;
	}

	/*
	 * Call player to select a row.
	 */
	public int selectRow(int index) {
		System.out.print(index + "\n");
		return index;
	}

	/*
	 * Remove the card at "index".
	 */
	public void remove(int index) {
		Card[] temp = new Card[hand.length - 1];

		for (int i = 0; i < index; i++)
			temp[i] = hand[i];
		for (int i = index + 1; i < hand.length; i++)
			temp[i - 1] = hand[i];

		hand = new Card[hand.length - 1];
		for (int i = 0; i < hand.length; i++)
			hand[i] = temp[i];
	}

	/*
	 * Add BULLs on player's score.
	 */
	public void eatBull(int s) {
		score += s;
	}

	public void print() {
		System.out.print(name + " : { ");
		for (int i = 0; i < hand.length; i++)
			System.out.print(hand[i].getNumber() + " ");

		System.out.print("}   Score : " + score);
		System.out.print("\n");
	}

	/*
	 * Sort cards on the player's hand using bubble sort.
	 */
	public void sortCards() {
		for (int i = 0; i < hand.length; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getNumber() > hand[j].getNumber()) {
					Card temp = hand[i];
					hand[i] = hand[j];
					hand[j] = temp;
				}
			}
		}
	}

	public Card[] getHand() {
		return hand;
	}

}
