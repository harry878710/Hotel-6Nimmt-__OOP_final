
public class Table extends Cardbank {

	private Card[][] board;

	public Table() {
		board = new Card[4][6];
	}

	public void init(Card c, int index) {
		board[index][0] = c;
	}

	public void placeCard(Player p, Card c) {

		Card[] boardOrder = new Card[4];
		int[] rowIndex = findCard(new Card(0, 0));
		for (int i = 0; i < 4; i++)
			boardOrder[i] = board[i][rowIndex[i]];
		boardOrder = sortCard(boardOrder);

		// 從台面上最大的數字依序比較
		for (int i = 0; i < 4; i++) {
			// 如果c的數字 > 台面上的數字，則接在此排後面
			if (c.getNumber() > boardOrder[3 - i].getNumber()) {
				int[] pos = findCard(boardOrder[3 - i]);
				// 但如果此排已經有5張牌，則直接拿走所有牛頭
				if (pos[1] == 4) {
					System.out.println("*** " + p.getName() + " BULL, take row " + pos[0]);
					p.eatBull(takeCards(pos[0]));
					init(c, pos[0]);
					return;
				}
				board[pos[0]][pos[1] + 1] = c;
				return;
			}
		}
		System.out.print("*** " + p.getName() + " must select a row to eat the BULL : ");
		int row = p.selectRow();
		p.eatBull(takeCards(row));
		init(c, row);
	}

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

	public int[] findCard(Card c) { // 如果number傳0，可以找每列最後一張牌
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
