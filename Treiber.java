package jdbc;

import java.sql.*;

public class Treiber {

	public static void main(String[] args) {
		try {
			//Connection zur Datenbank herstellen
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdata?useSSL=true", "root", "few65f5ewfew");
			
			//Ein Statement schreiben
			Statement stm = con.createStatement();
			
			//SQL query ausführen
			ResultSet rs = stm.executeQuery(null);
					
			//ResultSet bearbeiten
			while(rs.next()) {
				//Mach etwas
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}