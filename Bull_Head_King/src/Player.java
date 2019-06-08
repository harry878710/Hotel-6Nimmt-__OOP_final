
//import java.util.Scanner;
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

	public String getName() {
		return name;
	}

	// Add cards "c" to player's hand and sort them.
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

	// Call player to select a card.(Partially random select now for convenience.)
	public Card selectCard(int index) {
		if (index == -1) {
			Random rnd = new Random();
			index = rnd.nextInt(hand.length);
		}

		Card result = hand[index];
		System.out.print(result.getNumber() + "\n");
		remove(index);
		return result;
	}

	// Call player to select a row.(Random select now for convenience.)
	public int selectRow() {

		Random rnd = new Random();
		int index = rnd.nextInt(4);
		System.out.print(index + "\n");

		return index;
	}

	// Remove the card at "index".
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

	// Add BULLs on player's score.
	public void eatBull(int s) {
		score += s;
	}

	public void print() {
		System.out.print(name + " : { ");
		for (int i = 0; i < hand.length; i++) {
			System.out.print(hand[i].getNumber() + " ");
		}
		System.out.print("}   Score : " + score);
		System.out.print("\n");
	}

	// Sort cards on the player's hand using bubble sort.
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

}
