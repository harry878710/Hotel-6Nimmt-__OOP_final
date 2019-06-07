import java.util.Scanner;

public class Game {

	public static void main(String[] argv) throws InterruptedException {

		// Combine the game with user interface.
		GUItest panel = new GUItest();
		panel.setVisible(true);

		int round = 1;
		Table table = new Table();

		System.out.print("Enter player number : ");
		Scanner keyboard = new Scanner(System.in);
		int playerNumber = keyboard.nextInt();

		// Distribute 10 cards to each player.
		Player[] player = new Player[playerNumber];
		for (int i = 0; i < playerNumber; i++) {
			player[i] = new Player("Player " + (i + 1));
			player[i].setCards(table.getCard(10));
			player[i].print();
		}
		System.out.println("");

		// Put 4 cards on the table for each row.
		for (int i = 0; i < 4; i++)
			table.init(table.getCard(), i);
		table.printTable();

		for (; round <= 10; round++) {
			System.out.println("__________ Round " + round + " __________");
			System.out.println("");

			Card[] select = new Card[playerNumber];
			Card[] selectOrder = new Card[playerNumber];

			// Ask player to select a card.
			player[0].printMessage();
			panel.setSelectNumber();
			while (panel.getSelectNumber() == -1) {
				Thread.sleep(1000);
			}
			select[0] = player[0].selectCard(panel.getSelectNumber());
			selectOrder[0] = select[0];

			for (int i = 1; i < playerNumber; i++) {
				player[i].printMessage();
				select[i] = player[i].selectCard(-1); // select: sort by player order
				selectOrder[i] = select[i];
			}
			System.out.println("");
			selectOrder = table.sortCard(selectOrder); // selectOrder: sort by card number order

			// Opens the cards and determine whether put on the table or not.
			for (int i = 0; i < playerNumber; i++) {
				for (int j = 0; j < playerNumber; j++) {
					if (selectOrder[i].getNumber() == select[j].getNumber()) {
						table.placeCard(player[j], selectOrder[i]);
						break;
					}
				}
			}

			System.out.println("");
			for (int i = 0; i < playerNumber; i++)
				player[i].print();

			System.out.println("");
			table.printTable();

		}
	}

}
