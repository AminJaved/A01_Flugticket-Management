package jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private ResultSet rs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {

		try {
			//Connection zur Datenbank herstellen
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdata?useSSL=true", "root", "few65f5ewfew");
			
			//Ein Statement schreiben
			Statement stm = con.createStatement();
			
			//SQL query ausführen
			rs = stm.executeQuery("SELECT * FROM airlines");
					
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1188, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel titelFlugbuchung = new JLabel("Flugbuchung");
		titelFlugbuchung.setFont(new Font("Tahoma", Font.BOLD, 30));
		titelFlugbuchung.setBounds(473, 0, 216, 37);
		panel.add(titelFlugbuchung);
		
		JComboBox ankunftCombo = new JComboBox();
		ankunftCombo.setBounds(670, 87, 173, 20);
		panel.add(ankunftCombo);
		
		JComboBox abflugCombo = new JComboBox();
		abflugCombo.setBounds(297, 87, 173, 20);
		panel.add(abflugCombo);
		
		JLabel textAbflugflughafen = new JLabel("Abflug-Flughafen");
		textAbflugflughafen.setHorizontalAlignment(SwingConstants.CENTER);
		textAbflugflughafen.setFont(new Font("Tahoma", Font.BOLD, 14));
		textAbflugflughafen.setBounds(297, 62, 173, 20);
		panel.add(textAbflugflughafen);
		
		JLabel textAnkunftflughafen = new JLabel("Ankunft-Flughafen");
		textAnkunftflughafen.setHorizontalAlignment(SwingConstants.CENTER);
		textAnkunftflughafen.setFont(new Font("Tahoma", Font.BOLD, 14));
		textAnkunftflughafen.setBounds(670, 62, 173, 20);
		panel.add(textAnkunftflughafen);
		
		JButton suchButton = new JButton("Suchen");
		
		suchButton.setBounds(480, 275, 182, 59);
		panel.add(suchButton);
		
		//ResultSet bearbeiten
		while(rs.next()) {
			abflugCombo.addItem(rs.getString("name"));
			ankunftCombo.addItem(rs.getString("name"));
		}
		
		
		suchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String abflug = (String) abflugCombo.getSelectedItem();
				String ankunft = (String)ankunftCombo.getSelectedItem();
				if (abflug.contains(ankunft)) {
					JOptionPane.showMessageDialog(null, "Bitte unterschiedliche Flughäfen auswählen!");
				} else {
					try {
						rs = stm.executeQuery("select * from flights");
						
						//rs = stm.executeQuery("select * from flights,(select airportcode as 'depcode' from airports "
						//		+ "WHERE name='"+abflug+"')dep,(select airportcode as 'arrcode' from airports WHERE name='"+ankunft+"')arr "
						//		+ "WHERE depcode = departure_airport AND arrcode = destination_airport;");
					
					while(rs.next()) {
						System.out.println("!");
					}
					
					} catch (SQLException e) {
						System.out.print(e.getMessage());
					}
				}
			}
		});
		
		String abflug = (String) abflugCombo.getSelectedItem();
		String ankunft = (String)ankunftCombo.getSelectedItem();
		
		
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
