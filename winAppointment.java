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
import javax.swing.JScrollPane;

public class winAppointment extends JFrame {

	private JPanel contentPane;
	
	 private JTextField textFieldDate;
	 private JComboBox comboBoxDoctor;
	 private JComboBox comboBoxPatient;
	 private JTable table;
	
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					winAppointment frame = new winAppointment();
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
	public winAppointment() {
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
					
					if(comboBoxPatient.getSelectedItem().equals("")&& comboBoxDoctor.getSelectedItem().equals(""))
						throw new Exception();
					String query = "select * from Appointment";
					PreparedStatement ps = con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) 
						if(rs.getString("Patient_PID").equals((String)comboBoxPatient.getSelectedItem()) && ( rs.getString("Doctor_DID").equals((String)comboBoxDoctor.getSelectedItem())) && rs.getString("date").equals(textFieldDate.getText())) {
							query = "delete from appointment where patient_PID='" + (String)comboBoxPatient.getSelectedItem() + "' && Doctor_DID= '"+ (String)comboBoxDoctor.getSelectedItem() +"' &&date = '"+textFieldDate.getText() +"' ";
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
				/*try {
					String query = "delete from Appointment where Patient_PID='" + (String) comboBoxPatient.getSelectedItem() + "' && Doctor_DID= '"
							+ (String) comboBoxDoctor.getSelectedItem() + "' && date= ' " + textFieldDate.getText() +"' ";

					PreparedStatement ps = con.prepareStatement(query);
					ps.execute();
//					String sql = "delete from department where DepNum= ?";
//					PreparedStatement pst = con.prepareStatement(sql);
					// ps.setInt(1, Integer.parseInt(txtDepnum.getText()));
//					pst.execute();

					JOptionPane.showMessageDialog(null, "Deleted!");

					// JOptionPane.showMessageDialog(null, "Error, Dpeartment not found!");

					ps.close();
					

				} catch (Exception ex) {
					ex.printStackTrace();
				}*/
			}
		});
		btnNewButton_1_1.setBounds(119, 227, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("Patient");
		lblNewLabel.setBounds(10, 11, 122, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartmentName = new JLabel("Doctor");
		lblDepartmentName.setBounds(10, 50, 122, 14);
		contentPane.add(lblDepartmentName);
		
		 comboBoxPatient = new JComboBox();
		try {
			Connection con = con();
			String query = "select * from patient";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				comboBoxPatient.addItem(rs.getString("PID"));;
			
		}catch (Exception e) {
			
		}
		comboBoxPatient.setBounds(160, 7, 252, 22);
		contentPane.add(comboBoxPatient);
		
		comboBoxDoctor = new JComboBox();
			try {
			Connection con = con();
			String query = "select * from Doctor";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				comboBoxDoctor.addItem(rs.getString("DID"));;
			
		}catch (Exception e) {
			
		}
		comboBoxDoctor.setBounds(160, 46, 252, 22);
		contentPane.add(comboBoxDoctor);
		
		 
		
		JLabel lblDepartmentName_1 = new JLabel("Date");
		lblDepartmentName_1.setBounds(10, 81, 122, 14);
		contentPane.add(lblDepartmentName_1);
		
		textFieldDate = new JTextField();
		textFieldDate.setText("YYYY-MM-DD");
		textFieldDate.setBounds(160, 78, 252, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 147, 409, 68);
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
			String query = "INSERT INTO Appointment values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, (String) comboBoxPatient.getSelectedItem());
			ps.setString(2, (String) comboBoxDoctor.getSelectedItem());
			ps.setString(3, textFieldDate.getText());
			
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
		model.addColumn("Doctor");
		model.addColumn("Patient");
		model.addColumn("Date");
		try {
			String query = "select * from appointment";
			Statement st= con.createStatement();
			ResultSet r = st.executeQuery(query);
			while(r.next()) {
			model.addRow(new Object[] {
				r.getString("Patient_PID"),
				r.getString("Doctor_DID"),
				r.getString("date")
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
