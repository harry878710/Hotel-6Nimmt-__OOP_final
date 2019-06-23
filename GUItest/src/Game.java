import java.util.Scanner;

public class Game {

	public static void main(String[] argv) throws InterruptedException {

		GUItest panel = new GUItest();
		panel.setVisible(true);

		int round = 1;
		int playerNumber = 4;
		Table table = new Table();
		Player[] player = new Player[playerNumber];
		for (int i = 0; i < playerNumber; i++)
			player[i] = new Player("Player " + (i + 1));

		for (int i = 0; i < 4; i++)
			table.init(table.getCard(), i);
		table.printTable();

		for (; round <= 20; round++) {
			if (round == 1 || round == 11) {
				for (int i = 0; i < playerNumber; i++) {
					player[i] = new Player(player[i].getName(), player[i].getScore());
					player[i].setCards(table.getCard(10));
					player[i].print();
				}
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

			player[0].printMessage();
			panel.setSelectNumber();
			while (panel.getSelectNumber() == -1) {
				Thread.sleep(1000);
			}
			select[0] = player[0].selectCard(panel.getSelectNumber());
			selectOrder[0] = select[0];

			for (int i = 1; i < playerNumber; i++) {
				player[i].printMessage();
				select[i] = player[i].selectCard(-1); // select : 依「玩家順序﹞排序
				selectOrder[i] = select[i]; // selectOrder : 依「牌上數字大小」排序
			}
			System.out.println("");
			selectOrder = table.sortCard(selectOrder);

			panel.setCards(player[0].getHand());

			for (int i = 0; i < playerNumber; i++) {
				for (int j = 0; j < playerNumber; j++) {
					if (selectOrder[i].getNumber() == select[j].getNumber()) {
						playerOrder[i] = player[j];
						playerNameOrder[i] = player[j].getName();
						break;
					}
				}
			}
			
			panel.setTemp(selectOrder,playerNameOrder);
			
			for (int i = 0; i < playerNumber; i++) {
				int row = table.placeCard(playerOrder[i], selectOrder[i]);
				Thread.sleep(1000);
				panel.clearRow(row);
				panel.putCard(i);
				panel.setTable(table.getTable());
				panel.setScore(player);
			}

			System.out.println("");
			for (int i = 0; i < playerNumber; i++)
				player[i].print();

			System.out.println("");
			table.printTable();
		}

	}

}
