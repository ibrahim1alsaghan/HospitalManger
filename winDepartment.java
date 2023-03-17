package WIN;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
//import net.proteanit.sql.DbUtils;
public class winDepartment extends JFrame {

	 JPanel contentPane;
	private JTable table;
	private JTextField textNum;
	private JTextField textName;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winDepartment frame = new winDepartment();
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
	public winDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWin  a = new mainWin();
				a.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.frame.dispose();	
				a.frame.setVisible(true);
				
			}
		});
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
					if(textNum.getText().equals("")&& textName.getText().equals(""))
						throw new Exception();
					String query = "select * from department";
					PreparedStatement ps = con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) 
						if(rs.getString("DepNum").equals(textNum.getText()) && rs.getString("DepName").equals(textName.getText())) {
							query = "delete from department where DepNum='" + textNum.getText() + "' && DepName= '"+ textName.getText() + "' ";
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
		
		table = new JTable();
		table.setBounds(10, 216, 414, -70);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Department number");
		lblNewLabel.setBounds(10, 11, 122, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartmentName = new JLabel("Department name");
		lblDepartmentName.setBounds(10, 50, 122, 14);
		contentPane.add(lblDepartmentName);
		
		textNum = new JTextField();
		textNum.setBounds(160, 8, 252, 20);
		contentPane.add(textNum);
		textNum.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(160, 47, 252, 20);
		contentPane.add(textName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 406, 71);
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
			String query = "INSERT INTO Department values (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, textNum.getText());
			ps.setString(2, textName.getText());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Added!");
			

		} catch (Exception e) {
			System.out.print("Error" + e);
			JOptionPane.showMessageDialog(null, e);
		}

	}
	/*private void Referesh() {
		Connection con = con();
		String sql = "select * from department";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	
	private void showData() {
		Connection con = con();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Number");
		model.addColumn("Name");
		try {
			String query = "select * from department";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()) {
			model.addRow(new Object[] {
				r.getString("DepNum"),
				r.getString("DepName")
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



