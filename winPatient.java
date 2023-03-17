package WIN;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;
import javax.swing.JScrollPane;  

public class winPatient extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textAge;
	private JTextField textSDate;
	private JTextField textPhone;
	private JComboBox comboBox;
	private JTextField textEDate;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winPatient frame = new winPatient();
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
	public winPatient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWin  a = new mainWin();
				a.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.frame.dispose();	
				a.frame.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		btnNewButton.setBounds(327, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase();
			}
		});
		btnNewButton_1.setBounds(15, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = con();

				try {
					if(textAge.getText().equals("")&& textName.getText().equals("") && textID.getText().equals("")&&textPhone.getText().equals("")&&textSDate.getText().equals(""))
						throw new Exception();
					String query = "select * from Patient";
					PreparedStatement ps = con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) 
						if(rs.getString("PID").equals(textID.getText()) && rs.getString("Pphone").equals(textPhone.getText()) && rs.getString("Pname").equals(textName.getText()) && rs.getString("Page").equals(textAge.getText()) && rs.getString("Room_ID").equals((String)comboBox.getSelectedItem())) {
							query = "delete from Patient where PID='" + textID.getText() + "' && Pphone= '"+ textPhone.getText() +  "' && Pname= '"+ textName.getText() + "' && Page= '"+ textAge.getText() + "' && Room_ID= '"+ (String)comboBox.getSelectedItem() + "' && date= '"+ textSDate.getText() +"' ";
							ps = con.prepareStatement(query);
							ps.execute();
							JOptionPane.showMessageDialog(null, "Deleted!");
							ps.close();
							return;
						} 
					JOptionPane.showMessageDialog(null, "Error");

					
					

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
			
			}
		});
		btnNewButton_1_1.setBounds(119, 227, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 0, 0);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 11, 130, 14);
		contentPane.add(lblNewLabel_1);
		
		textID = new JTextField();
		textID.setBounds(150, 8, 274, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("name");
		lblNewLabel_1_1.setBounds(10, 61, 130, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(150, 58, 274, 20);
		contentPane.add(textName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("phone");
		lblNewLabel_1_1_1.setBounds(10, 36, 130, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		textPhone = new JTextField();
		textPhone.setBounds(150, 33, 274, 20);
		contentPane.add(textPhone);
		textPhone.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("age");
		lblNewLabel_1_1_2.setBounds(10, 86, 130, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(150, 83, 274, 20);
		contentPane.add(textAge);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Room Number");
		lblNewLabel_1_1_2_1.setBounds(10, 111, 130, 14);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		 comboBox = new JComboBox();
		try {
			Connection con = con();
			String query = "select * from room";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				comboBox.addItem(rs.getString("RoomNum"));;
			
		}catch (Exception e) {
			
		}
		comboBox.setBounds(150, 107, 274, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Date");
		lblNewLabel_1_1_2_1_1.setBounds(10, 137, 130, 14);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		textSDate = new JTextField();
		textSDate.setColumns(10);
		textSDate.setBounds(150, 134, 274, 20);
		textSDate.setText("YYYY-MM-DD");
		contentPane.add(textSDate);
		
		JButton btnNewButton_1_1_1 = new JButton("Display");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		btnNewButton_1_1_1.setBounds(223, 227, 89, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 414, 61);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		/* textEDate = new JTextField();
		textEDate.setText("YYYY-MM-DD");
		textEDate.setColumns(10);
		textEDate.setBounds(150, 158, 274, 20);
		contentPane.add(textEDate);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("End Date");
		lblNewLabel_1_1_2_1_1_1.setBounds(10, 161, 130, 14);
		contentPane.add(lblNewLabel_1_1_2_1_1_1);*/
	}
	static Connection con() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Hospital";
			Class.forName(driver);
			return DriverManager.getConnection(url, "root", null);
		} catch (Exception e) {
			System.out.println("Erorr" + e);

		}
		return null;
	}
	private void SaveToDatabase() {
		Connection con = con();
		Date d= new Date();
		try {
			SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd");
			String s = f.format(d);
			
			String q = "select * from Patient where date='"+textSDate.getText()+"'";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(q);
			
			if(s.compareTo(textSDate.getText())>0) { 
				JOptionPane.showMessageDialog(null, "the date in the past");
				return;
			}

			String query = "INSERT INTO patient values (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, textID.getText());
			ps.setString(2, textPhone.getText());
			ps.setString(3, textName.getText());
			ps.setString(4, textAge.getText());
			ps.setString(5, (String) comboBox.getSelectedItem());
			
			
			
			ps.setString(6, textSDate.getText());
			//if(textEDate.getText().compareTo(textSDate.getText())<0 )throw new Exception();
			//ps.setString(7, textEDate.getText());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
			

		} catch (Exception e) {
			System.out.println("Error" + e);
			JOptionPane.showMessageDialog(null, e);
		}

	}
	private void showData() {
		Connection con = con();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Phone");
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Room number");
		model.addColumn("Date");
		
		try {
			String query = "select * from Patient";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()) {
			model.addRow(new Object[] {
				r.getString("PID"),
				r.getString("Pphone"),
				r.getString("Pname"),
				r.getString("Page"),
				r.getString("Room_ID"),
				r.getString("date")
			}); }
			r.close();
			st.close();
			con.close();
			table.setModel(model);
			table.setAutoResizeMode(0);
			/*table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			table.getColumnModel().getColumn(0).setPreferredWidth(180);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);*/
		} catch(Exception e){}

	}
}
