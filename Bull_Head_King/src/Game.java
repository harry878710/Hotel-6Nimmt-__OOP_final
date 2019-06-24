
public class Game {

	public static void main(String[] argv) throws InterruptedException {

		GUItest panel = new GUItest();
		panel.setVisible(true);

		int playerNumber = 4;
		Table table = new Table();
		Player[] player = new Player[playerNumber];
		for (int i = 0; i < playerNumber; i++)
			player[i] = new Player("Player " + (i + 1));

		// Put 4 cards on the table for each row.
		for (int i = 0; i < 4; i++)
			table.init(table.getCard(), i);
		table.printTable();

		// Play game for 20 rounds.
		for (int round = 1; round <= 20; round++) {
			if (round == 1 || round == 11) {

				// Distribute 10 cards to each player.
				for (int i = 0; i < playerNumber; i++) {
					player[i] = new Player(player[i].getName(), player[i].getScore());
					player[i].setCards(table.getCard(10));
					player[i].print();
				}

				// Initialize the GUI interface.
				panel.initCards();
				panel.setCards(player[0].getHand());
				panel.setTable(table.getTable());
				panel.setScore(player);
				System.out.println("");
			}

			System.out.println("__________ Round " + round + " __________");
			System.out.println("");

			Card[] select = new Card[playerNumber];
			Card[] selectOrder = new Card[playerNumber];
			Player[] playerOrder = new Player[playerNumber];
			String[] playerNameOrder = new String[playerNumber];

			// Ask the player to select a card.
			player[0].printMessage();
			select[0] = player[0].selectCard(panel.getSelectNumber(), table);
			selectOrder[0] = select[0];

			for (int i = 1; i < playerNumber; i++) {
				player[i].printMessage();
				select[i] = player[i].selectCard(-1, table);
				selectOrder[i] = select[i];
			}
			System.out.println("");
			selectOrder = table.sortCard(selectOrder);

			panel.setCards(player[0].getHand());

			// Sort cards by number order.
			for (int i = 0; i < playerNumber; i++) {
				for (int j = 0; j < playerNumber; j++) {
					if (selectOrder[i].getNumber() == select[j].getNumber()) {
						playerOrder[i] = player[j];
						playerNameOrder[i] = player[j].getName();
						break;
					}
				}
			}

			// Opens the cards and put them on the table respectively.
			panel.setTemporary(selectOrder, playerNameOrder);

			for (int i = 0; i < playerNumber; i++) {
				int row = table.placeCard(playerOrder[i], selectOrder[i], panel, player[0].getName());
				Thread.sleep(1200);
				panel.putCard(i);
				panel.clearRow(row,table);
				panel.setTable(table.getTable());
				panel.setScore(player);
			}

			System.out.println("");
			for (int i = 0; i < playerNumber; i++)
				player[i].print();
			System.out.println("");
			table.printTable();
		}

		panel.printWinner(player);
	}
}
