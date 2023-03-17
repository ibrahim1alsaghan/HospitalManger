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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class winDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textName;
	private JComboBox comboBoxD;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winDoctor frame = new winDoctor();
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
	public winDoctor() {
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
					if( textName.getText().equals("") && textID.getText().equals("")&&textPhone.getText().equals("")&&textEmail.getText().equals(""))
						throw new Exception();
					String query = "select * from Doctor";
					PreparedStatement ps = con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) 
						if(rs.getString("DID").equals(textID.getText()) && rs.getString("Demail").equals(textEmail.getText()) && rs.getString("Dname").equals(textName.getText()) && rs.getString("Dphone").equals(textPhone.getText()) && rs.getString("Department_DepNum").equals((String)comboBoxD.getSelectedItem())) {
							query = "delete from Doctor where DID='" + textID.getText() + "' && Demail= '"+ textEmail.getText() +  "' && Dname= '"+ textName.getText() + "' && Dphone= '"+ textPhone.getText() + "' && Department_DepNum= '"+ (String)comboBoxD.getSelectedItem()  +"' ";
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
		
		JLabel lblNewLabel_1_1 = new JLabel("email");
		lblNewLabel_1_1.setBounds(10, 61, 130, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(150, 58, 274, 20);
		contentPane.add(textEmail);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("name");
		lblNewLabel_1_1_1.setBounds(10, 36, 130, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		 textName = new JTextField();
		textName.setBounds(150, 33, 274, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("phone");
		lblNewLabel_1_1_2.setBounds(10, 86, 130, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(150, 83, 274, 20);
		contentPane.add(textPhone);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Department Number");
		lblNewLabel_1_1_2_1.setBounds(10, 111, 130, 14);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		 comboBoxD = new JComboBox();
		try {
			Connection con = con();
			String query = "select * from department";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				comboBoxD.addItem(rs.getString("DepNum"));;
			
		}catch (Exception e) {
			
		}
		comboBoxD.setBounds(150, 107, 274, 22);
		contentPane.add(comboBoxD);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 414, 67);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1_1 = new JButton("Dispaly");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		btnNewButton_1_1_1.setBounds(223, 227, 89, 23);
		contentPane.add(btnNewButton_1_1_1);
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
		try {
			String query = "INSERT INTO Doctor values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, textID.getText());
			ps.setString(2, textEmail.getText());
			ps.setString(3, textName.getText());
			ps.setString(4, textPhone.getText());
			ps.setString(5, (String) comboBoxD.getSelectedItem());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
			

		} catch (Exception e) {
			System.out.print("Error" + e);
			JOptionPane.showMessageDialog(null, e);
		}

	}
	private void showData() {
		Connection con = con();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Email");
		model.addColumn("Name");
		model.addColumn("Phone");
		model.addColumn("Department number");
		try {
			String query = "select * from Doctor";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()) {
			model.addRow(new Object[] {
				r.getString("DID"),
				r.getString("Demail"),
				r.getString("Dname"),
				r.getString("Dphone"),
				r.getString("Department_DepNum")
			}); }
			r.close();
			st.close();
			con.close();
			table.setModel(model);
			/*table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(0).setPreferredWidth(120);
			table.getColumnModel().getColumn(0).setPreferredWidth(180);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);*/
		} catch(Exception e){}

	}
}
