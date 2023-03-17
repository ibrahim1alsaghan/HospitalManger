package WIN;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class winRoom extends JFrame {
	
	 JPanel contentPane;
	 private JTextField textNum;
	 private JTable table;
	 private JComboBox comboBox;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winRoom frame = new winRoom();
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
	public winRoom() {
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
					if(textNum.getText().equals("")&&comboBox.getSelectedItem().equals(""))
						throw new Exception();
					String query = "select * from room";
					PreparedStatement ps = con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) 
						if(rs.getString("RoomNum").equals(textNum.getText()) && rs.getString("Department_DepNum").equals((String) comboBox.getSelectedItem())) {
							query = "delete from room where RoomNum='" + textNum.getText() + "' && Department_DepNum= '"+ (String) comboBox.getSelectedItem() + "' ";
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
			}});
		btnNewButton_1_1.setBounds(119, 227, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("Room number");
		lblNewLabel.setBounds(10, 11, 122, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartmentName = new JLabel("Department number");
		lblDepartmentName.setBounds(10, 50, 122, 14);
		contentPane.add(lblDepartmentName);
		
		textNum = new JTextField();
		textNum.setBounds(160, 8, 252, 20);
		contentPane.add(textNum);
		textNum.setColumns(10);
		
		comboBox = new JComboBox();
		try {
			Connection con = con();
			String query = "select * from department";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				comboBox.addItem(rs.getString("DepNum"));;
			
		}catch (Exception e) {
			
		}
		comboBox.setBounds(160, 46, 252, 22);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 131, 414, 85);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1_1 = new JButton("Display");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
		btnNewButton_1_1_1.setBounds(223, 227, 89, 23);
		contentPane.add(btnNewButton_1_1_1);
	}
	
	
	
	
	private void SaveToDatabase() {
		Connection con = con();
		
		try {
			String query = "INSERT INTO Room values (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, textNum.getText());
			
			ps.setString(2, (String) comboBox.getSelectedItem());
		//	ps.setString(2, textName.getText());
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
			

		} catch (Exception e) {
			System.out.print("Error" + e);
			JOptionPane.showMessageDialog(null, e);
		}

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
	private void showData() {
		Connection con = con();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Room Number");
		model.addColumn("Department Number");
		
		try {
			String query = "select * from room";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()) {
			model.addRow(new Object[] {
				r.getString("RoomNum"),
				r.getString("Department_Depnum"),
				
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
