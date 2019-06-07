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

public class GUItest extends JFrame {

	private JPanel contentPane;

	private JButton btnCard_0;
	private JButton btnCard_1;
	private int selectNumber = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUItest test = new GUItest();
		test.setVisible(true);
		//test.go();
		
	}

	/**
	 * Create the frame.
	 */
	public GUItest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCard_0 = new JButton("Card1");
		btnCard_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectNumber = 1;
			}
		});

		
		btnCard_0.setBounds(170, 700, 83, 98);
		contentPane.add(btnCard_0);

		btnCard_1 = new JButton("Card2");
		btnCard_1.setBounds(270, 700, 83, 98);
		contentPane.add(btnCard_1);
		
		JButton btnCard_2 = new JButton("Card3");
		btnCard_2.setBounds(370, 700, 83, 98);
		contentPane.add(btnCard_2);

		JButton btnCard_3 = new JButton("Card4");
		btnCard_3.setBounds(470, 700, 83, 98);
		contentPane.add(btnCard_3);

		JButton btnCard_4 = new JButton("Card5");
		btnCard_4.setBounds(570, 700, 83, 98);
		contentPane.add(btnCard_4);

		JButton btnCard_5 = new JButton("Card6");
		btnCard_5.setBounds(670, 700, 83, 98);
		contentPane.add(btnCard_5);

		JButton btnCard_6 = new JButton("Card7");
		btnCard_6.setBounds(770, 700, 83, 98);
		contentPane.add(btnCard_6);

		JButton btnCard_7 = new JButton("Card8");
		btnCard_7.setBounds(870, 700, 83, 98);
		contentPane.add(btnCard_7);

		JButton btnCard_8 = new JButton("Card9");
		btnCard_8.setBounds(970, 700, 83, 98);
		contentPane.add(btnCard_8);

		JButton btnCard_9 = new JButton("Card10");
		btnCard_9.setBounds(1070, 700, 86, 98);
		contentPane.add(btnCard_9);

		JButton btnFirst = new JButton("first");
		btnFirst.setBounds(15, 59, 83, 98);
		contentPane.add(btnFirst);

		JButton btnSecond = new JButton("second");
		btnSecond.setBounds(15, 189, 83, 98);
		contentPane.add(btnSecond);

		JButton btnThird = new JButton("third");
		btnThird.setBounds(15, 317, 83, 98);
		contentPane.add(btnThird);

		JButton btnFourth = new JButton("fourth");
		btnFourth.setBounds(15, 441, 83, 98);
		contentPane.add(btnFourth);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUItest.class.getResource("/img/white.jpg")));
		label.setBounds(135, 59, 83, 98);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUItest.class.getResource("/img/white.jpg")));
		label_1.setBounds(255, 59, 83, 98);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(GUItest.class.getResource("/img/white.jpg")));
		label_2.setBounds(375, 59, 83, 98);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(GUItest.class.getResource("/img/white.jpg")));
		label_3.setBounds(495, 59, 83, 98);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(GUItest.class.getResource("/img/Black.jpg")));
		label_4.setBounds(615, 59, 83, 98);
		contentPane.add(label_4);
		label_4.setOpaque(false);
		// label_4.setContentAreaFilled(false);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(735, 3, 83, 98);
		contentPane.add(label_5);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GUItest.class.getResource("/img/wood2.jpg")));
		lblNewLabel.setBounds(0, -15, 1419, 878);
		contentPane.add(lblNewLabel);
		
	}
	
	public void setSelectNumber(){
		selectNumber = -1;
	}
	
	public int getSelectNumber(){
		
		return selectNumber;
	}

}
