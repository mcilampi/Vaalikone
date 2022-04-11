import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Ehdokas;

public class Dao {
	
	static String DBpath = "//localhost:3306/vaalikone";
	static String username = "hannu";
	static String password = "kukkuu123";
	static String query = "select * from ehdokkaat";

	// create connection to database;

	public static Connection createDatabaseConnection(String DBpath, String username, String password) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql:" + DBpath,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database not connected.");
		}
		return con;
	}
    
	// read all ehdokas info from database, create ehdokas object and set it's properties according to database values
	// return a list of the created ehdokas objects
    public static ArrayList<Ehdokas> readFromDatabase(Connection con, String query) {
		ArrayList<Ehdokas> ehdokasLista = new ArrayList<Ehdokas>();
		
		try {
			System.out.println(con);
			PreparedStatement prepared = con.prepareStatement(query);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				Ehdokas ehdokas = new Ehdokas();
				ehdokas.setId(result.getInt("ehdokasID"));
				ehdokas.setEtunimi(result.getString("etunimi"));
				ehdokas.setSukunimi(result.getString("sukunimi"));
				ehdokas.setPuolue(result.getString("puolue"));
				ehdokas.setEsittely(result.getString("esittely"));
				ehdokas.setEhdokasNumero(result.getInt("ehdokasnumero"));
				ehdokas.setKayttajanimi(result.getString("kayttajanimi"));
				ehdokas.setSalasana(result.getString("salasana"));
				ehdokasLista.add(ehdokas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return ehdokasLista;
	}
    
    // returns only one ehdokas object that has it's attributes read from the database columns
    public static Ehdokas readOneEhdokasFromDatabase(Connection con, String query, int annettuEhdokas) {
    	Ehdokas ehdokas = new Ehdokas();
    	try {
			PreparedStatement prepared = con.prepareStatement(query);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				if (result.getInt("ehdokasID") == annettuEhdokas) {
					ehdokas.setId(result.getInt("ehdokasID"));
					ehdokas.setEtunimi(result.getString("etunimi"));
					ehdokas.setSukunimi(result.getString("sukunimi"));
					ehdokas.setPuolue(result.getString("puolue"));
					ehdokas.setEsittely(result.getString("esittely"));
					ehdokas.setEhdokasNumero(result.getInt("ehdokasnumero"));
					ehdokas.setKayttajanimi(result.getString("kayttajanimi"));
					ehdokas.setSalasana(result.getString("salasana"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return ehdokas;
	}
    
    // method receives an ehdokas object and updates database entry corresponding to the id attribute of the object
    // with the given attributes
	public static int updateEntry(Connection con, Ehdokas ehdokas) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("UPDATE ehdokkaat SET etunimi = ?, sukunimi = ?, puolue = ?, esittely = ?, ehdokasnumero = ?, kayttajanimi = ?, salasana = ?  WHERE id = ?");
			prepared.setString(1, ehdokas.getEtunimi());
			prepared.setString(1, ehdokas.getSukunimi());
			prepared.setString(1, ehdokas.getPuolue());
			prepared.setString(1, ehdokas.getEsittely());
			prepared.setInt(1, ehdokas.getEhdokasNumero());
			prepared.setString(1, ehdokas.getKayttajanimi());
			prepared.setString(1, ehdokas.getSalasana());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		}		
		return rowsAffected;
	}

}
