import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class GUItest extends JFrame {

	private JPanel contentPane;
	private int selectNumber = -1;
	private int selectRow = -1;
	private int cardNumber = 10;

	JButton btnCard_0 = new JButton("Card1");
	JButton btnCard_1 = new JButton("Card2");
	JButton btnCard_2 = new JButton("Card3");
	JButton btnCard_3 = new JButton("Card4");
	JButton btnCard_4 = new JButton("Card5");
	JButton btnCard_5 = new JButton("Card6");
	JButton btnCard_6 = new JButton("Card7");
	JButton btnCard_7 = new JButton("Card8");
	JButton btnCard_8 = new JButton("Card9");
	JButton btnCard_9 = new JButton("Card10");

	JButton btnFirst = new JButton("first");
	JButton btnSecond = new JButton("second");
	JButton btnThird = new JButton("third");
	JButton btnFourth = new JButton("fourth");

	private final JLabel label_1 = new JLabel("");
	private final JLabel label_2 = new JLabel("");
	private final JLabel label_3 = new JLabel("");
	private final JLabel label_4 = new JLabel("");
	private final JLabel label_5 = new JLabel("");
	private final JLabel label_6 = new JLabel("");
	private final JLabel label_7 = new JLabel("");
	private final JLabel label_8 = new JLabel("");
	private final JLabel label_9 = new JLabel("");
	private final JLabel label_10 = new JLabel("");
	private final JLabel label_11 = new JLabel("");
	private final JLabel label_12 = new JLabel("");
	private final JLabel label_13 = new JLabel("");
	private final JLabel label_14 = new JLabel("");
	private final JLabel label_15 = new JLabel("");
	private final JLabel label_16 = new JLabel("");
	private final JLabel label_17 = new JLabel("");
	private final JLabel label_18 = new JLabel("");
	private final JLabel label_19 = new JLabel("");
	private final JLabel label_20 = new JLabel("");

	private JButton[] cardIndex = { btnCard_0, btnCard_1, btnCard_2, btnCard_3, btnCard_4, btnCard_5, btnCard_6,
			btnCard_7, btnCard_8, btnCard_9 };

	private JButton[] tableButtonIndex = { btnFirst, btnSecond, btnThird, btnFourth };

	private JLabel[][] tableIndex = { { label_1, label_2, label_3, label_4, label_5 },
			{ label_6, label_7, label_8, label_9, label_10 }, { label_11, label_12, label_13, label_14, label_15 },
			{ label_16, label_17, label_18, label_19, label_20 } };

	private final JLabel label_21 = new JLabel("");
	private final JLabel label_22 = new JLabel("");
	private final JLabel label_23 = new JLabel("");
	private final JLabel label_24 = new JLabel("");

	private JLabel[] tempCardIndex = { label_21, label_22, label_23, label_24 };

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	JTable table = new JTable();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUItest frame = new GUItest();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUItest() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1419, 878);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCard_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 0;
			}
		});
		btnCard_0.setBounds(150, 700, 95, 98);
		contentPane.add(btnCard_0);

		btnCard_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 1;
			}
		});
		btnCard_1.setBounds(260, 700, 95, 98);
		contentPane.add(btnCard_1);
		btnCard_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnCard_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 2;
			}
		});
		btnCard_2.setBounds(370, 700, 95, 98);
		contentPane.add(btnCard_2);

		btnCard_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 3;
			}
		});
		btnCard_3.setBounds(480, 700, 95, 98);
		contentPane.add(btnCard_3);

		btnCard_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 4;
			}
		});
		btnCard_4.setBounds(590, 700, 95, 98);
		contentPane.add(btnCard_4);

		btnCard_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 5;
			}
		});
		btnCard_5.setBounds(700, 700, 95, 98);
		contentPane.add(btnCard_5);
		btnCard_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnCard_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 6;
			}
		});
		btnCard_6.setBounds(810, 700, 95, 98);
		contentPane.add(btnCard_6);

		btnCard_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 7;
			}
		});
		btnCard_7.setBounds(920, 700, 95, 98);
		contentPane.add(btnCard_7);

		btnCard_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 8;
			}
		});
		btnCard_8.setBounds(1030, 700, 95, 98);
		contentPane.add(btnCard_8);
		btnCard_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnCard_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectNumber = 9;
			}
		});
		btnCard_9.setBounds(1140, 700, 95, 98);
		contentPane.add(btnCard_9);
		btnFirst.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				selectRow = 0;
			}
		});

		btnFirst.setBounds(15, 59, 95, 98);
		contentPane.add(btnFirst);
		btnSecond.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectRow = 1;
			}
		});

		btnSecond.setBounds(15, 189, 95, 98);
		contentPane.add(btnSecond);
		btnThird.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectRow = 2;
			}
		});

		btnThird.setBounds(15, 317, 95, 98);
		contentPane.add(btnThird);
		btnFourth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFourth.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectRow = 3;
			}
		});

		btnFourth.setBounds(15, 441, 95, 98);
		contentPane.add(btnFourth);

		label_1.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_1.setBounds(150, 59, 83, 98);
		contentPane.add(label_1);

		label_2.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_2.setBounds(270, 59, 83, 98);
		contentPane.add(label_2);

		label_3.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_3.setBounds(390, 59, 83, 98);
		contentPane.add(label_3);

		label_4.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_4.setBounds(510, 59, 83, 98);
		contentPane.add(label_4);

		label_5.setIcon(new ImageIcon(GUItest.class.getResource("/img/bull.png")));
		label_5.setBounds(630, 59, 83, 98);
		contentPane.add(label_5);

		label_6.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_6.setBounds(150, 189, 83, 98);
		contentPane.add(label_6);

		label_7.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_7.setBounds(270, 189, 83, 98);
		contentPane.add(label_7);

		label_8.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_8.setBounds(390, 189, 83, 98);
		contentPane.add(label_8);

		label_9.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_9.setBounds(510, 189, 83, 98);
		contentPane.add(label_9);

		label_10.setIcon(new ImageIcon(GUItest.class.getResource("/img/bull.png")));
		label_10.setBounds(630, 189, 83, 98);
		contentPane.add(label_10);

		label_11.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_11.setBounds(150, 317, 83, 98);
		contentPane.add(label_11);

		label_12.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_12.setBounds(270, 317, 83, 98);
		contentPane.add(label_12);

		label_13.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_13.setBounds(390, 317, 83, 98);
		contentPane.add(label_13);

		label_14.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_14.setBounds(510, 317, 83, 98);
		contentPane.add(label_14);

		label_15.setIcon(new ImageIcon(GUItest.class.getResource("/img/bull.png")));
		label_15.setBounds(630, 317, 83, 98);
		contentPane.add(label_15);

		label_16.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_16.setBounds(150, 441, 83, 98);
		contentPane.add(label_16);

		label_17.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_17.setBounds(270, 441, 83, 98);
		contentPane.add(label_17);

		label_18.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_18.setBounds(390, 441, 83, 98);
		contentPane.add(label_18);

		label_19.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_19.setBounds(510, 441, 83, 98);
		contentPane.add(label_19);

		label_20.setIcon(new ImageIcon(GUItest.class.getResource("/img/bull.png")));
		label_20.setBounds(630, 441, 83, 98);
		contentPane.add(label_20);

		label_21.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_21.setBounds(831, 369, 83, 98);
		contentPane.add(label_21);

		label_22.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_22.setBounds(954, 369, 83, 98);
		contentPane.add(label_22);

		label_23.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_23.setBounds(1073, 369, 83, 98);
		contentPane.add(label_23);

		label_24.setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		label_24.setBounds(1192, 369, 83, 98);
		contentPane.add(label_24);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField_1.setBounds(831, 325, 83, 29);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(954, 325, 83, 29);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(1073, 325, 80, 29);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(1192, 325, 83, 29);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		textField_5.setForeground(Color.BLACK);
		textField_5.setColumns(10);
		textField_5.setBounds(831, 512, 444, 36);
		contentPane.add(textField_5);

		Object[] columnNames = { "Player", "Bulls" };
		Object[][] rowData = { { "P1", "B1" }, { "P2", "B2" }, { "P3", "B3" }, { "P4", "B4" } };
		table = new JTable(rowData, columnNames);
		table.setFont(new Font("Yu Gothic UI", Font.PLAIN, 40));
		table.setBounds(970, 73, 160, 200);
		table.setRowHeight(50);
		contentPane.add(table);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GUItest.class.getResource("/img/wood3.jpg")));
		lblNewLabel.setBounds(-181, -39, 1578, 878);
		contentPane.add(lblNewLabel);
	}

	/*
	 * Wait the player to select a card and returns the card index.
	 */
	public int getSelectNumber() throws InterruptedException {
		selectNumber = -1;
		while (selectNumber == -1)
			Thread.sleep(1000);

		cardNumber--;
		return selectNumber;
	}

	/*
	 * Wait the player to select a row when he needs to take BULLs and returns the
	 * row index.
	 */
	public int getRow(String name) throws InterruptedException {
		textField_5.setVisible(true);
		textField_5.setText(name + " must select a row to eat the BULLs.");
		selectRow = -1;
		while (selectRow == -1)
			Thread.sleep(1000);

		textField_5.setVisible(false);
		textField_5.setText("");
		return selectRow;
	}

	/*
	 * Initialize the table and show player's cards at the beginning.
	 */
	public void initCards() {
		for (int i = 0; i < 10; i++)
			cardIndex[i].setVisible(true);
		for (int i = 0; i < 4; i++)
			tempCardIndex[i].setVisible(false);

		textField_1.setVisible(false);
		textField_2.setVisible(false);
		textField_3.setVisible(false);
		textField_4.setVisible(false);
		textField_5.setVisible(false);
		cardNumber = 10;
	}

	/*
	 * Show cards on player's hand at the beginning of each round.
	 */
	public void setCards(Card c[]) {
		for (int i = 0; i < 10; i++) {
			if (cardNumber > i)
				cardIndex[i].setIcon(new ImageIcon(GUItest.class.getResource("/cards/" + c[i].getNumber() + ".png")));
			else
				cardIndex[i].setVisible(false);
		}
	}

	/*
	 * Show cards on table at the beginning of each round.
	 */
	public void setTable(Card c[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; c[i][j] != null; j++) {
				if (j == 0)
					tableButtonIndex[i].setIcon(
							new ImageIcon(GUItest.class.getResource("/cards/" + c[i][j].getNumber() + ".png")));
				else
					tableIndex[i][j - 1].setIcon(
							new ImageIcon(GUItest.class.getResource("/cards/" + c[i][j].getNumber() + ".png")));
			}
		}
	}

	/*
	 * Show scores on table at the beginning of each round.
	 */
	public void setScore(Player p[]) {
		for (int i = 0; i < 4; i++)
			table.getModel().setValueAt(p[i].getScore(), i, 1);
	}

	/*
	 * Show cards that each player selects on the temporary zone.
	 */
	public void setTemporary(Card[] c, String[] s) {
		for (int i = 0; i < 4; i++) {
			tempCardIndex[i].setIcon(new ImageIcon(GUItest.class.getResource("/cards/" + c[i].getNumber() + ".png")));
			tempCardIndex[i].setVisible(true);

			if (i == 0)
				textField_1.setText(s[i]);
			else if (i == 1)
				textField_2.setText(s[i]);
			else if (i == 2)
				textField_3.setText(s[i]);
			else if (i == 3)
				textField_4.setText(s[i]);
		}
		textField_1.setVisible(true);
		textField_2.setVisible(true);
		textField_3.setVisible(true);
		textField_4.setVisible(true);
	}

	/*
	 * Set temporary card invisible after the card has been put on the board.
	 */
	public void putCard(int i) {
		tempCardIndex[i].setVisible(false);
		if (i == 0)
			textField_1.setVisible(false);
		else if (i == 1)
			textField_2.setVisible(false);
		else if (i == 2)
			textField_3.setVisible(false);
		else if (i == 3)
			textField_4.setVisible(false);
	}

	/*
	 * Clear the specific row on the table.
	 */
	public void clearRow(int row, Table t) throws InterruptedException {
		if (row < 0)
			return;

		tableIndex[row][4]
				.setIcon(new ImageIcon(GUItest.class.getResource("/cards/" + t.getTable()[row][0].getNumber() + ".png")));
		Thread.sleep(1000);

		for (int i = 0; i < 5; i++) {
			if (i == 0)
				tableButtonIndex[row].setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
			else
				tableIndex[row][i - 1].setIcon(new ImageIcon(GUItest.class.getResource("/img/boarder.png")));
		}
		tableIndex[row][4]
				.setIcon(new ImageIcon(GUItest.class.getResource("/img/bull.png")));
	}

	/*
	 * Show winner on the screen.
	 */
	public void printWinner(Player[] p) {
		int minScore = p[0].getScore();
		String winner = p[0].getName();

		for (int i = 1; i < p.length; i++) {
			if (p[i].getScore() < minScore) {
				minScore = p[i].getScore();
				winner = p[i].getName();
			}
		}

		textField_5.setVisible(true);
		System.out.println(winner + " wins!");
		textField_5.setText(winner + " wins!");
	}
}
