import java.util.Random;

public class Cardbank {

	private Card[] bank;

	/*
	 * Create the bank with 104 cards.
	 */
	public Cardbank() {
		bank = new Card[104];
		for (int i = 1; i <= 104; i++) {
			int bull;
			if (i == 55)
				bull = 7;
			else if (i % 11 == 0)
				bull = 5;
			else if (i % 10 == 0)
				bull = 3;
			else if (i % 5 == 0)
				bull = 2;
			else
				bull = 1;

			bank[i - 1] = new Card(i, bull);
		}
	}
	
	/*
	 * Remove the card of bank at "index".
	 */
	public void remove(int index) {
		Card[] temp = new Card[bank.length - 1];

		for (int i = 0; i < index; i++)
			temp[i] = bank[i];
		for (int i = index + 1; i < bank.length; i++)
			temp[i - 1] = bank[i];

		bank = new Card[bank.length - 1];
		for (int i = 0; i < bank.length; i++)
			bank[i] = temp[i];
	}

	/*
	 * Put card "c" back to the bank.
	 */
	public void putback(Card c) {
		Card[] temp = new Card[bank.length + 1];
		for (int i = 0; i < bank.length; i++)
			temp[i] = bank[i];
		temp[bank.length] = c;

		bank = new Card[bank.length + 1];
		for (int i = 0; i < bank.length; i++)
			bank[i] = temp[i];
	}

	/*
	 * Randomly return cards and delete them from bank.
	 */
	public Card[] getCard(int n) {
		Card result[] = new Card[n];
		for (int i = 0; i < n; i++) {
			Random rnd = new Random();
			int index = rnd.nextInt(bank.length);
			result[i] = bank[index];
			remove(index);
		}
		return result;
	}
	
	/*
	 * Randomly return a card and delete it from bank.
	 */
	public Card getCard() {
		Random rnd = new Random();
		int index = rnd.nextInt(bank.length);
		Card result = bank[index];
		remove(index);

		return result;
	}

}
