
public class Table extends Cardbank {

	private Card[][] board;

	public Table() {
		board = new Card[4][6];
	}

	// Put card "c" at row "index", column 0 on the table,
	// including the condition after a player takes BULLs.
	public void init(Card c, int index) {
		board[index][0] = c;
	}

	// Determine where card "c" should be put, or the player "p" should take the
	// BULLs.
	public void placeCard(Player p, Card c) {

		// Fetch the cards of every row and sort by card number order.
		Card[] boardOrder = new Card[4];
		int[] rowIndex = findCard(new Card(0, 0));
		for (int i = 0; i < 4; i++)
			boardOrder[i] = board[i][rowIndex[i]];
		boardOrder = sortCard(boardOrder);

		// Compare card "c" with the cards on board from the largest number.
		for (int i = 0; i < 4; i++) {
			if (c.getNumber() > boardOrder[3 - i].getNumber()) {
				int[] pos = findCard(boardOrder[3 - i]);
				// Condition that "p" should take the BULLs right away.
				if (pos[1] == 4) {
					System.out.println("*** " + p.getName() + " BULL, take row " + pos[0]);
					p.eatBull(takeCards(pos[0]));
					init(c, pos[0]);
					return;
				}
				// Condition that "c" can be put on the board.
				board[pos[0]][pos[1] + 1] = c;
				return;
			}
		}

		// Condition that "p" should select a row and take the BULLs.
		System.out.print("*** " + p.getName() + " must select a row to eat the BULL : ");
		int row = p.selectRow();
		p.eatBull(takeCards(row));
		init(c, row);
	}

	// Sort cards using bubble sort.
	public Card[] sortCard(Card[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i].getNumber() > data[j].getNumber()) {
					Card temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
		return data;
	}

	// Return {row, column} of the card "c" on the table.
	// If the number of "c" is 0, findCard() returns the column of the last card of
	// every row.
	public int[] findCard(Card c) {
		int[] result = new int[4];
		int number = c.getNumber();

		for (int i = 0; i < 4; i++) {
			int j = 0;
			for (; board[i][j] != null && j < 5; j++) {
				if (board[i][j].getNumber() == number) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
			if (number == 0)
				result[i] = j - 1;
		}
		return result;
	}

	// Returns total BULLs on the cards at row "index".
	public int takeCards(int index) {
		int result = 0;
		for (int j = 0; board[index][j] != null && j < 5; j++) {
			result += board[index][j].getBull();
			putback(board[index][j]);
			board[index][j] = null;
		}
		return result;
	}

	public void printTable() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; board[i][j] != null && j < 6; j++) {
				System.out.print(board[i][j].getNumber() + " ");
			}
			System.out.println("\n");
		}
	}
}
