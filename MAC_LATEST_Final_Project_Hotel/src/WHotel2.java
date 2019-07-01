import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileReader;
import java.util.Calendar;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.json.*;
import java.util.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JList;

public class WHotel2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtPleaseSetYour;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField people;
	private JTextField room;
	private String userid;
	private JTextField textField_hotel_id;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtTextfielduser;
	private JTextField textField_ho_id;
	static Hotel[] h = new Hotel[1500];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//// json to object start

		try {
			FileReader reader = new FileReader("HotelList.txt");
			JSONTokener jsrc = new JSONTokener(reader);
			JSONArray jobj = new JSONArray(jsrc);
			JSONObject j[] = new JSONObject[1500];
			for (int i = 0; i < 1500; i++) {
				h[i] = new Hotel();
				j[i] = jobj.getJSONObject(i);
				h[i].setID(j[i].getInt("HotelID"));
				h[i].setLocality(j[i].getString("Locality"));
				h[i].setHotelStar(j[i].getInt("HotelStar"));
				h[i].setStreet_Address(j[i].getString("Street-Address"));
				JSONTokener js = new JSONTokener(j[i].get("Rooms").toString());
				JSONArray tmp = new JSONArray(js);
				JSONObject room = tmp.getJSONObject(0);
				h[i].setsingle(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
				room = tmp.getJSONObject(1);
				h[i].setdoub(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
				room = tmp.getJSONObject(2);
				h[i].setquad(room.getString("RoomType"), room.getInt("RoomPrice"), room.getInt("Number"));
				// System.out.println(h[i].getHotelStar()+" "+h[i].getID()+"
				// "+h[i].getLocality());
				// System.out.println(h[i].getSingle().toString()+h[i].getDoub().toString()+h[i].getQuad().toString());
			}
			// json end
			// initial the booking from sql
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:hotel.db");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK;");
				while (rs.next()) {
					DateFormat df = new SimpleDateFormat("yyyy-M-dd");
					Date stdate = new Date();
					Date endate = new Date();
					stdate = df.parse(rs.getString("START"));
					endate = df.parse(rs.getString("END"));
					Calendar start = Calendar.getInstance(Locale.TAIWAN);
					Calendar end = Calendar.getInstance(Locale.TAIWAN);
					start.setTime(stdate);
					end.setTime(endate);
					long st = start.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
					long en = end.getTimeInMillis() - Calendar.getInstance(Locale.TAIWAN).getTimeInMillis();
					st = st / (24 * 60 * 60 * 1000);
					en = en / (24 * 60 * 60 * 1000);
					if (st == 0 && start.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN)
							.get(Calendar.DAY_OF_YEAR)) {
						st += 1;
					}
					if (en == 0 && end.get(Calendar.DAY_OF_YEAR) != Calendar.getInstance(Locale.TAIWAN)
							.get(Calendar.DAY_OF_YEAR)) {
						en += 1;
					}
					for (int i = (int) st; i < en; i++) {
						if (i >= 0) {
							h[rs.getInt("HOTELID")].getSingle()[i].setNumber(
									h[rs.getInt("HOTELID")].getSingle()[i].getNumber() - rs.getInt("SINGLE"));
							h[rs.getInt("HOTELID")].getDoub()[i]
									.setNumber(h[rs.getInt("HOTELID")].getDoub()[i].getNumber() - rs.getInt("DOUBLE"));
							h[rs.getInt("HOTELID")].getQuad()[i]
									.setNumber(h[rs.getInt("HOTELID")].getQuad()[i].getNumber() - rs.getInt("QUAD"));
						}
					}
				}
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
			// end
		} catch (IOException e) {
			System.out.println("error");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WHotel2 frame = new WHotel2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class JTextFieldHintListener implements FocusListener {
		private String hintText;
		private JTextField textField;

		public JTextFieldHintListener(JTextField jTextField, String hintText) {
			this.textField = jTextField;
			this.hintText = hintText;
			jTextField.setText(hintText); // 預設直接顯示
			jTextField.setForeground(Color.GRAY);
		}

		@Override
		public void focusGained(FocusEvent e) {
			// 獲取焦點時，清空提示內容
			String temp = textField.getText();
			if (temp.equals(hintText)) {
				textField.setText("");
				textField.setForeground(Color.BLACK);
			}

		}

		@Override
		public void focusLost(FocusEvent e) {
			// 失去焦點時，沒有輸入內容，顯示提示內容
			String temp = textField.getText();
			if (temp.equals("")) {
				textField.setForeground(Color.GRAY);
				textField.setText(hintText);
			}

		}

	}

	public void switchPanels(JPanel panel) {
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.repaint();
		contentPane.revalidate();
	}

	/**
	 * Create the frame.
	 */
	public WHotel2() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel home = new JPanel();
		
		JScrollPane hlist_pane = new JScrollPane();
		hlist_pane.setBounds(0, 0, 720, 400);
		contentPane.add(hlist_pane);
		hlist_pane.setVisible(false);
		
		JLabel lblSearchingResult = new JLabel("Searching Result: ");
		lblSearchingResult.setBackground(new Color(255, 255, 204));
		lblSearchingResult.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblSearchingResult.setHorizontalAlignment(SwingConstants.CENTER);
		hlist_pane.setColumnHeaderView(lblSearchingResult);
		
		JList list = new JList();
		hlist_pane.setViewportView(list);
		
		JButton button_2 = new JButton("");
		button_2.setBackground(new Color(255, 255, 153));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hlist_pane.setVisible(false);
				home.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(WHotel2.class.getResource("/img/箭頭.png")));
		hlist_pane.setRowHeaderView(button_2);

		JPanel b_revise = new JPanel();
		b_revise.setLayout(null);
		b_revise.setBounds(0, 0, 720, 400);
		contentPane.add(b_revise);
		
		JLabel label_5 = new JLabel("Booking Overview");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		label_5.setBounds(181, 25, 362, 46);
		b_revise.add(label_5);
		
		JLabel label_6 = new JLabel("User");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(110, 112, 104, 16);
		b_revise.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年太陽logo.png")));
		label_7.setBounds(588, 0, 132, 131);
		b_revise.add(label_7);
		
		JLabel label_8 = new JLabel("Hotel ID");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(272, 112, 104, 16);
		b_revise.add(label_8);
		
		txtTextfielduser = new JTextField();
		
		txtTextfielduser.setColumns(10);
		txtTextfielduser.setBounds(101, 131, 123, 26);
		b_revise.add(txtTextfielduser);
		
		textField_ho_id = new JTextField();
		textField_ho_id.setColumns(10);
		textField_ho_id.setBounds(268, 131, 123, 26);
		b_revise.add(textField_ho_id);
		
		
		
		JLabel label_9 = new JLabel("Quadruple");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(342, 191, 104, 16);
		b_revise.add(label_9);
		
		JLabel label_10 = new JLabel("Double");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(194, 191, 104, 16);
		b_revise.add(label_10);
		
		JLabel label_11 = new JLabel("Single");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(54, 191, 104, 16);
		b_revise.add(label_11);
		
		JComboBox comboBox_s = new JComboBox();
		comboBox_s.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox_s.setBounds(74, 208, 64, 29);
		b_revise.add(comboBox_s);
		
		JComboBox comboBox_d = new JComboBox();
		comboBox_d.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox_d.setBounds(214, 209, 64, 29);
		b_revise.add(comboBox_d);
		
		JComboBox comboBox_q = new JComboBox();
		comboBox_q.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox_q.setBounds(363, 209, 64, 29);
		b_revise.add(comboBox_q);
		
		JDateChooser dateChooser_4 = new JDateChooser();
		dateChooser_4.setBounds(110, 293, 119, 26);
		b_revise.add(dateChooser_4);
		
		JDateChooser dateChooser_5 = new JDateChooser();
		dateChooser_5.setBounds(272, 293, 119, 26);
		b_revise.add(dateChooser_5);
		
		JLabel label_12 = new JLabel("Check-in time");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(111, 265, 104, 16);
		b_revise.add(label_12);
		
		JLabel label_13 = new JLabel("Check-out time");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(278, 265, 104, 16);
		b_revise.add(label_13);
		
		JButton btnNewButton = new JButton("Cancel Reservation");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Visitor v = new Visitor();
				v.setName(userid);
				if(Booking.cancel(v, Integer.parseInt(textField_3.getText()))==true){
					JFrame frame = new JFrame("Success");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "Your reservation has been canceled. ");
					b_revise.setVisible(false);
					home.setVisible(true);
				}
				else {
					JFrame frame = new JFrame("Fail");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "System wrong. ");
					b_revise.setVisible(false);
					home.setVisible(true);
				}
			}
		});
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(479, 242, 139, 41);
		b_revise.add(btnNewButton);
		
		JButton btnRevise = new JButton("Revise");
		btnRevise.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String us = userid;
				Visitor tmp = new Visitor();
				tmp.setName(us);
				
				Calendar start = dateChooser_4.getCalendar();
				Calendar end = dateChooser_5.getCalendar();
				int s = comboBox_s.getSelectedIndex();
				int d = comboBox_d.getSelectedIndex();
				int q = comboBox_q.getSelectedIndex();
				String out = Booking.change(tmp, Integer.parseInt(textField_3.getText()),start, end, s, d, q);
				
					JFrame frame = new JFrame("Result");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, out);
					
					b_revise.setVisible(false);
					home.setVisible(true);
			}
		});
		btnRevise.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnRevise.setForeground(Color.ORANGE);
		btnRevise.setBounds(488, 149, 117, 41);
		b_revise.add(btnRevise);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(WHotel2.class.getResource("/img/箭頭.png")));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b_revise.setVisible(false);
				home.setVisible(true);
			}
		});
		button_1.setBounds(679, 359, 35, 35);
		b_revise.add(button_1);
		
		JLabel BG_o = new JLabel("");
		BG_o.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG3.jpeg")));
		BG_o.setBounds(0, 0, 720, 400);
		b_revise.add(BG_o);
		b_revise.setVisible(false);

		JPanel b_overview = new JPanel();
		b_overview.setLayout(null);
		b_overview.setBounds(0, 0, 720, 400);
		contentPane.add(b_overview);

		JLabel User = new JLabel("User");
		User.setHorizontalAlignment(SwingConstants.CENTER);
		User.setBounds(125, 167, 104, 16);
		b_overview.add(User);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年太陽logo.png")));
		label_3.setBounds(418, 117, 132, 131);
		b_overview.add(label_3);

		JLabel lblReservationNumber = new JLabel("Reservation ID");
		lblReservationNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservationNumber.setBounds(125, 253, 104, 16);
		b_overview.add(lblReservationNumber);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(238, 248, 124, 26);
		b_overview.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(237, 162, 124, 26);
		b_overview.add(textField_4);

		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b_overview.setVisible(false);
				home.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(WHotel2.class.getResource("/img/箭頭.png")));
		button.setBounds(679, 359, 35, 35);
		b_overview.add(button);

		JButton btnCheck = new JButton("Check");
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Visitor v = new Visitor();
				v.setName(userid);
				String[] tmp;
				tmp = Booking.search(v,Integer.parseInt(textField_3.getText()));
				txtTextfielduser.setText(userid);
				textField_ho_id.setText(textField_3.getText());
				if(tmp[0].length()>2) {
					JFrame frame = new JFrame("Woops");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, tmp[0]);
				}else {
				comboBox_s.setSelectedIndex(Integer.parseInt(tmp[0].toString()));
				comboBox_d.setSelectedIndex(Integer.parseInt(tmp[1].toString()));
				comboBox_q.setSelectedIndex(Integer.parseInt(tmp[2].toString()));
				JFrame frame = new JFrame("Your Reservation");
				frame.setBounds(100, 100, 72, 40);
				JOptionPane.showMessageDialog(frame, "From "+tmp[5]+" to "+tmp[6]);
				b_overview.setVisible(false);
				b_revise.setVisible(true);
				}
			}
		});
		btnCheck.setBounds(426, 260, 117, 46);
		b_overview.add(btnCheck);

		JLabel lblBookingOverview = new JLabel("Booking Overview");
		lblBookingOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingOverview.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		lblBookingOverview.setBounds(181, 25, 362, 46);
		b_overview.add(lblBookingOverview);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG3.jpeg")));
		label_4.setBounds(0, 0, 720, 400);
		b_overview.add(label_4);
		b_overview.setVisible(false);

		JPanel ho_reserve = new JPanel();
		ho_reserve.setBounds(0, 0, 720, 400);
		contentPane.add(ho_reserve);
		ho_reserve.setLayout(null);

		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(468, 109, 119, 26);
		ho_reserve.add(dateChooser_2);

		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(465, 169, 119, 26);
		ho_reserve.add(dateChooser_3);

		JLabel young_r = new JLabel("");
		young_r.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年太陽logo.png")));
		young_r.setBounds(509, 226, 132, 131);
		ho_reserve.add(young_r);

		JComboBox quadruple_box = new JComboBox();
		quadruple_box.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		quadruple_box.setBounds(196, 279, 125, 29);
		ho_reserve.add(quadruple_box);

		JComboBox double_box = new JComboBox();
		double_box.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		double_box.setBounds(197, 226, 124, 29);
		ho_reserve.add(double_box);

		JLabel label = new JLabel("Check-out time");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(349, 170, 104, 16);
		ho_reserve.add(label);

		JLabel label_1 = new JLabel("Check-in time");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(349, 114, 104, 16);
		ho_reserve.add(label_1);

		JLabel hotel_id = new JLabel("Hotel ID");
		hotel_id.setHorizontalAlignment(SwingConstants.CENTER);
		hotel_id.setBounds(89, 113, 104, 16);
		ho_reserve.add(hotel_id);

		JLabel single = new JLabel("Single");
		single.setHorizontalAlignment(SwingConstants.CENTER);
		single.setBounds(90, 171, 104, 16);
		ho_reserve.add(single);

		JLabel Double = new JLabel("Double");
		Double.setHorizontalAlignment(SwingConstants.CENTER);
		Double.setBounds(88, 231, 104, 16);
		ho_reserve.add(Double);

		JLabel quadruple = new JLabel("Quadruple");
		quadruple.setHorizontalAlignment(SwingConstants.CENTER);
		quadruple.setBounds(90, 284, 104, 16);
		ho_reserve.add(quadruple);

		textField_hotel_id = new JTextField();
		textField_hotel_id.setBounds(197, 109, 124, 26);
		ho_reserve.add(textField_hotel_id);
		textField_hotel_id.setColumns(10);

		JComboBox single_box = new JComboBox();
		single_box.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		single_box.setBounds(197, 166, 124, 29);
		ho_reserve.add(single_box);

		JLabel L_booking = new JLabel("Booking");
		L_booking.setHorizontalAlignment(SwingConstants.CENTER);
		L_booking.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		L_booking.setBounds(273, 30, 161, 46);
		ho_reserve.add(L_booking);

		JButton btnback = new JButton("");
		btnback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ho_reserve.setVisible(false);
				home.setVisible(true);
			}
		});
		btnback.setIcon(new ImageIcon(WHotel2.class.getResource("/img/箭頭.png")));
		btnback.setBounds(679, 359, 35, 35);
		ho_reserve.add(btnback);

		JButton btnReserve = new JButton("Reserve");
		btnReserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String us = userid;
				Visitor tmp = new Visitor();
				tmp.setName(us);
				
				Calendar start = dateChooser_2.getCalendar();
				Calendar end = dateChooser_3.getCalendar();
				int s = single_box.getSelectedIndex();
				int d = double_box.getSelectedIndex();
				int q = quadruple_box.getSelectedIndex();
				String out = Booking.tobook(tmp, start, end, h[Integer.parseInt(textField_hotel_id.getText())], s, d, q);
				
				if (out.length()<4) {
					JFrame frame = new JFrame("Success");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "Reservation success! Enjoy! Your Reservation ID is: "+out);
					ho_reserve.setVisible(false);
					home.setVisible(true);
				}
				else {
					JFrame frame = new JFrame("Woops");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "The reservation is not available. "+out);
				}
			}
		});
		btnReserve.setBounds(391, 270, 117, 46);
		ho_reserve.add(btnReserve);

		JLabel BG_r = new JLabel("");
		BG_r.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG3.jpeg")));
		BG_r.setBounds(0, 0, 720, 400);
		ho_reserve.add(BG_r);
		ho_reserve.setVisible(false);

		JPanel ho_search = new JPanel();
		ho_search.setLayout(null);
		ho_search.setBounds(0, 0, 720, 400);
		contentPane.add(ho_search);
		ho_search.setVisible(false);

		JLabel lblCheckoutTime = new JLabel("Check-out time");
		lblCheckoutTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckoutTime.setBounds(527, 51, 104, 16);
		ho_search.add(lblCheckoutTime);

		JLabel lblCheckinTime = new JLabel("Check-in time");
		lblCheckinTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckinTime.setBounds(385, 51, 104, 16);
		ho_search.add(lblCheckinTime);

		room = new JTextField();
		room.setColumns(10);
		room.setBounds(226, 79, 111, 51);
		ho_search.add(room);

		people = new JTextField();
		people.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jTextField = new JTextField();
				jTextField.addFocusListener(new JTextFieldHintListener(jTextField, "How many people?"));

			}
		});

		JLabel lblRoomNumber = new JLabel("Room number");
		lblRoomNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomNumber.setBounds(230, 51, 104, 16);
		ho_search.add(lblRoomNumber);
		people.setBounds(90, 79, 111, 51);
		ho_search.add(people);
		people.setColumns(10);

		JLabel lblNewLabel = new JLabel("People");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(116, 51, 61, 16);
		ho_search.add(lblNewLabel);

		JComboBox location_box = new JComboBox();
		location_box.setModel(new DefaultComboBoxModel(new String[] { "-", "台北", "台中", "高雄" }));
		location_box.setBounds(160, 186, 110, 51);
		ho_search.add(location_box);

		JLabel young = new JLabel("");
		young.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年太陽logo.png")));
		young.setBounds(295, 156, 132, 131);
		ho_search.add(young);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocation.setBounds(183, 164, 61, 16);
		ho_search.add(lblLocation);

		JLabel lblStrar = new JLabel("Ranking");
		lblStrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrar.setBounds(474, 164, 61, 16);
		ho_search.add(lblStrar);

		JComboBox ranking_box = new JComboBox();
		ranking_box.setModel(new DefaultComboBoxModel(new String[] { "-", "＊＊", "＊＊＊", "＊＊＊＊", "＊＊＊＊＊" }));
		ranking_box.setBounds(450, 186, 110, 51);
		ho_search.add(ranking_box);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(380, 91, 119, 26);
		ho_search.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(519, 91, 119, 26);
		ho_search.add(dateChooser_1);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(WHotel2.class.getResource("/img/箭頭.png")));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ho_search.setVisible(false);
				home.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(679, 359, 35, 35);
		ho_search.add(btnNewButton_1);

		JButton Search = new JButton("Search");
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Hotel[] hlist = new Hotel[0];
				Hotel[]	tmplist;
				String ranking = (String) ranking_box.getSelectedItem();
				if (!ranking.equals("-")) {
					for (int i = 0; i < 1500; i++) {
//						System.out.println(ranking);
						if (h[i].getHotelStar() == ranking.length()) {
							Hotel[] tmp = new Hotel[hlist.length + 1];
							for (int j = 0; j < hlist.length; j++) {
								tmp[j] = hlist[j];
							}
							tmp[tmp.length - 1] = h[i];
							hlist = tmp;
						}
					}
				}
				tmplist = hlist.clone();
				hlist = new Hotel[0];
				for (int i = 0; i < tmplist.length; i++) {
					//System.out.println(ranking);
					String location = (String)location_box.getSelectedItem();
					if (tmplist[i].getLocality().equals(location)) {
						Hotel[] tmp = new Hotel[hlist.length + 1];
						for (int j = 0; j < hlist.length; j++) {
							tmp[j] = hlist[j];
						}
						tmp[tmp.length - 1] = tmplist[i];
						hlist = tmp;
					}
				}
				//String slist [] = new String[h.length];
//				for(int i =0;i<h.length;i++) {
//					slist [i]= "";
//					slist [i]= slist[i].concat("Hotel ID: " +hlist[i].getID());
//					slist [i]= slist[i].concat("Hotel star: " +hlist[i].getHotelStar());
//					slist [i]= slist[i].concat("Hotel location: " +hlist[i].getLocality());
//					slist [i]= slist[i].concat("Hotel address: " +hlist[i].getStreet_Address());
//					slist [i]= slist[i].concat("Hotel price: " +hlist[i].getStreet_Address());
////					
//				}
				int p = Integer.parseInt(people.getText());
				int r = Integer.parseInt(room.getText());
				Calendar start = dateChooser.getCalendar();
				Calendar end = dateChooser_1.getCalendar();
				Search se = new Search(p,r,start,end);
				
//				hlist[0].getSingle()[0].getPrice();
				
				
				list.setListData(se.roomsearch(hlist));
				ho_search.setVisible(false);
				hlist_pane.setVisible(true);
				
//				JFrame frame = new JFrame("Wrong ID or password");
//				frame.setBounds(100, 100, 72, 40);
//				JOptionPane.showMessageDialog(frame, "Wrong ID or password.");
				
			}
		});
		Search.setBounds(299, 310, 117, 29);
		ho_search.add(Search);

		JLabel BG_s = new JLabel("");
		BG_s.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG3.jpeg")));
		BG_s.setBounds(0, 0, 720, 400);
		ho_search.add(BG_s);

//		home

		home.setBounds(0, 0, 720, 400);
		contentPane.add(home);
		home.setLayout(null);

		JButton search = new JButton("New button");
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.setVisible(false);
				ho_search.setVisible(true);
			}
		});
		search.setIcon(new ImageIcon(WHotel2.class.getResource("/img/button1.png")));
		search.setBounds(93, 226, 125, 125);
		home.add(search);

		JButton overview = new JButton("New button");
		overview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.setVisible(false);
				b_overview.setVisible(true);
			}
		});
		overview.setIcon(new ImageIcon(WHotel2.class.getResource("/img/button2.png")));
		overview.setBounds(484, 226, 125, 125);
		home.add(overview);

		JButton reserve = new JButton("New button");
		reserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				home.setVisible(false);
				ho_reserve.setVisible(true);
			}
		});
		reserve.setIcon(new ImageIcon(WHotel2.class.getResource("/img/button4.png")));
		reserve.setBounds(289, 226, 125, 125);
		home.add(reserve);

		JLabel lblNewLabel_1 = new JLabel("WELCOME TO HOTEL");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 56));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(UIManager.getColor("Button.select"));
		lblNewLabel_1.setBounds(67, 20, 597, 82);
		home.add(lblNewLabel_1);

		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年head_logo.png")));
		label_14.setBounds(136, 102, 100, 100);
		home.add(label_14);

		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon(WHotel2.class.getResource("/img/校年早餐店字樣黃紅.png")));
		label_15.setBounds(233, 102, 387, 100);
		home.add(label_15);

		JLabel BG2 = new JLabel("New label");
		BG2.setVerticalAlignment(SwingConstants.BOTTOM);
		BG2.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG.jpg")));
		BG2.setBounds(0, 0, 720, 400);
		home.add(BG2);
		home.setVisible(false);

//		input id log in panel
		JPanel input_id_2 = new JPanel();
		input_id_2.setBounds(0, 0, 720, 400);
		contentPane.add(input_id_2);
		input_id_2.setLayout(null);
		input_id_2.setVisible(false);

		JLabel hint_2 = new JLabel("Please enter your ID number: ");
		hint_2.setBounds(132, 121, 219, 46);
		input_id_2.add(hint_2);
		hint_2.setBackground(Color.BLACK);
		hint_2.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setBounds(339, 119, 203, 50);
		input_id_2.add(textField);
		textField.setColumns(10);

		JLabel lblPleaseEnterYour = new JLabel("Please enter your password: ");
		lblPleaseEnterYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterYour.setBackground(Color.BLACK);
		lblPleaseEnterYour.setBounds(132, 195, 219, 46);
		input_id_2.add(lblPleaseEnterYour);

		passwordField = new JPasswordField();
		passwordField.setBounds(339, 192, 203, 50);
		input_id_2.add(passwordField);

		JButton Enter = new JButton("Enter");
		Enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userid = textField.getText();
				char[] pz = passwordField.getPassword();
				Visitor v = new Visitor();
				if (v.login(userid, String.copyValueOf(pz)) == false) {
					JFrame frame = new JFrame("Wrong ID or password");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "Wrong ID or password.");

				} else {
					input_id_2.setVisible(false);
					home.setVisible(true);
				}
			}
		});
		Enter.setBounds(290, 260, 117, 29);
		input_id_2.add(Enter);

//		input id sign up panel
		JPanel input_id_1 = new JPanel();
		input_id_1.setBounds(0, 0, 720, 400);
		contentPane.add(input_id_1);
		input_id_1.setLayout(null);
		input_id_1.setVisible(false);

		JLabel hint = new JLabel("Please set your ID number: ");
		hint.setBounds(132, 121, 219, 46);
		input_id_1.add(hint);
		hint.setBackground(Color.BLACK);
		hint.setHorizontalAlignment(SwingConstants.CENTER);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

//				Character set_id = e.getKeyChar();
//				String set_sid = set_id.toString();
//				set_id.toString();				
			}
		});
		textField_1.setBounds(339, 119, 203, 50);
		input_id_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblPleaseSetYour = new JLabel("Please set your password: ");
		lblPleaseSetYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSetYour.setBackground(Color.BLACK);
		lblPleaseSetYour.setBounds(132, 195, 219, 46);
		input_id_1.add(lblPleaseSetYour);

		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

//				Visitor v = new Visitor();
//				Character set_pzword = e.getKeyChar();
//				String set_spzword = set_pzword.toString();
//				v.setName(set_spzword);
			}
		});
		passwordField_1.setBounds(339, 192, 203, 50);
		input_id_1.add(passwordField_1);

		JButton enter = new JButton("Enter");
		enter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userid = textField_1.getText();
				char[] pz = passwordField_1.getPassword();
				Visitor v = new Visitor();

				if (v.newVisitor(userid, String.copyValueOf(pz)) == false) {
					JFrame frame = new JFrame("Username error");
					frame.setBounds(100, 100, 72, 40);
					JOptionPane.showMessageDialog(frame, "Username has been used.");
				} else {
					input_id_1.setVisible(false);
					home.setVisible(true);
				}

			}
		});
		enter.setBounds(290, 260, 117, 29);
		input_id_1.add(enter);

		JPanel sign_in = new JPanel();
		sign_in.setBounds(0, 0, 720, 400);
		contentPane.add(sign_in);
		sign_in.setLayout(null);
		sign_in.setVisible(true);

//		sign up button
		JButton signup_button = new JButton("Sign up");
		signup_button.setBounds(280, 95, 137, 69);
		sign_in.add(signup_button);
		signup_button.setForeground(Color.BLACK);
		signup_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//					switchPanels(input_id);
				sign_in.setVisible(false);
				input_id_1.setVisible(true);

			}
		});

//	log in button
		JButton login_button = new JButton("Log in");
		login_button.setBounds(280, 216, 137, 69);
		sign_in.add(login_button);
		login_button.setForeground(Color.BLACK);
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//					switchPanels(input_id);
				sign_in.setVisible(false);
				input_id_2.setVisible(true);

			}
		});

		JLabel BG = new JLabel("New label");
		BG.setBounds(0, 0, 1985, 1080);
		sign_in.add(BG);
		BG.setIcon(new ImageIcon(WHotel2.class.getResource("/img/BG2.jpg")));

//	input_interface

	}
}
