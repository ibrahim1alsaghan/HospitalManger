package WIN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainWin {

	 JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWin window = new mainWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDepartment = new JButton("Department");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winDepartment a = new winDepartment();
				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setVisible(true);
				
				frame.dispose();
			}
		});
		btnDepartment.setBounds(134, 12, 131, 37);
		frame.getContentPane().add(btnDepartment);
		
		
		
		JButton btnRoom = new JButton("Room");
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			winRoom a =new winRoom();
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			a.setVisible(true);
			frame.dispose();
			}
		});
		btnRoom.setBounds(134, 61, 131, 37);
		frame.getContentPane().add(btnRoom);
		
		
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winDoctor a = new winDoctor();
				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setVisible(true);
				frame.dispose();
			}
		});
		btnDoctor.setBounds(134, 110, 131, 37);
		frame.getContentPane().add(btnDoctor);
		
		
		
		JButton btnPatient = new JButton("Patient");
		btnPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winPatient a= new winPatient();
				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setVisible(true);
				frame.dispose();
				
			}
		});
		btnPatient.setBounds(134, 159, 131, 37);
		frame.getContentPane().add(btnPatient);
		
		
		
		JButton btnAppointment = new JButton("Appointment");
		btnAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winAppointment a = new winAppointment();
				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				a.setVisible(true);
				frame.dispose();
			}
		});
		btnAppointment.setBounds(134, 208, 131, 37);
		frame.getContentPane().add(btnAppointment);
	
	
	}
}
