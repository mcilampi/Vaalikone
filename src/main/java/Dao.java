import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Ehdokas;
import data.Kysymys;

public class Dao {
	
	static String DBpath = "//localhost:3306/vaalikone";
	static String username = "hannu";
	static String password = "kukkuu123";
	static String query = "select * from ehdokkaat";
	static String queryy = "select * from kysymykset";
	
	// close database connection
	public static void closeDatabaseConnection(Connection con) {
		try {
			con.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.out.println("Not able to close database connection.");
			e.printStackTrace();
		}
	}
	
	// get distinct tags from database table kysymykset
	public static ArrayList<String> readDistinctTags(Connection con) {
		ArrayList<String> tags = new ArrayList<String>();
		try {
			PreparedStatement prepared = con.prepareStatement("select distinct tag , case when tag is null then 'none' else tag end from kysymykset");
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				tags.add(result.getString("tag"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tags;
	}
	
	// read questions from database
	public static ArrayList<Kysymys> readAllQuestionsWithTagFromDatabase(Connection con, String tag) {
		ArrayList<Kysymys> kysymykset = new ArrayList<Kysymys>();
		try {
			PreparedStatement prepared = con.prepareStatement("select * from kysymykset where tag like(?)");
			prepared.setString(1, tag);
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				Kysymys kysymys = new Kysymys();
				kysymys.setId(result.getInt("kysymysID"));
				kysymys.setKysymys(result.getString("kysymys"));
				kysymys.setTunniste(result.getString("tag"));
				kysymykset.add(kysymys);
			}
		} catch (SQLException e) {
			System.out.println("Kysymysten haku ei onnistunut.");
			e.printStackTrace();
		}
		
		return kysymykset;
	}
	
	// read questions from database
	public static ArrayList<Kysymys> readAllQuestionsFromDatabase(Connection con) {
		ArrayList<Kysymys> kysymykset = new ArrayList<Kysymys>();
		try {
			PreparedStatement prepared = con.prepareStatement("select * from kysymykset");
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				Kysymys kysymys = new Kysymys();
				kysymys.setId(result.getInt("kysymysID"));
				kysymys.setKysymys(result.getString("kysymys"));
				kysymys.setTunniste(result.getString("tag"));
				kysymykset.add(kysymys);
			}
		} catch (SQLException e) {
			System.out.println("Kysymysten haku ei onnistunut.");
			e.printStackTrace();
		}
		
		return kysymykset;
	}
	
	 // returns only one question object that has it's attributes read from the database columns
    public static Kysymys readOneKysymysFromDatabase(Connection con, String queryy, int annettuKysymys) {
    	Kysymys kysymys = new Kysymys();
    	try {
			PreparedStatement prepared = con.prepareStatement(queryy);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				if (result.getInt("kysymysID") == annettuKysymys) {
					kysymys.setId(result.getInt("kysymysID"));
					kysymys.setKysymys(result.getString("kysymys"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return kysymys;
	}
	
	// insert question into database
	public static int createQuestion(Connection con, Kysymys kysymys) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("INSERT INTO kysymykset(kysymys,tag) VALUES(?,?)");
			prepared.setString(1, kysymys.getKysymys());
			prepared.setString(2, kysymys.getTunniste());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {
			System.out.println("EI voitu lisätä kysymystä.");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	// insert candidate into database
	public static int createCandidate(Connection con, Ehdokas ehdokas) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("INSERT INTO ehdokkaat(etunimi,sukunimi,puolue,esittely,ehdokasnumero,kayttajanimi,salasana) VALUES(?,?,?,?,?,?,?)");
			prepared.setString(1, ehdokas.getEtunimi());
			prepared.setString(2, ehdokas.getSukunimi());
			prepared.setString(3, ehdokas.getPuolue());
			prepared.setString(4, ehdokas.getEsittely());
			prepared.setInt(5, ehdokas.getEhdokasNumero());
			prepared.setString(6, ehdokas.getKayttajanimi());
			prepared.setString(7, ehdokas.getSalasana());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ei voitu lisätä ehdokasta tietokantaan.");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	// delete candidate from database
	public static int deleteCandidate(Connection con, int candidateId) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("DELETE FROM ehdokkaat WHERE ehdokasID = ?");
			prepared.setInt(1, candidateId);
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ehdokkaan poistaminen ei onnistunut.");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	// delete question from database
			public static int deleteQuestion(Connection con, int questionId) {
				int rowsAffected = 0;
				try {
					PreparedStatement prepared = con.prepareStatement("DELETE FROM kysymykset WHERE kysymysID = ?");
					prepared.setInt(1, questionId);
					rowsAffected = prepared.executeUpdate();
				} catch (SQLException e) {
					System.out.println("Kysymyksen poistaminen ei onnistunut.");
					e.printStackTrace();
				}
				return rowsAffected;
			}
		
	
	// create connection to database;

	public static Connection createDatabaseConnection(String DBpath, String username, String password) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql:" + DBpath,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Tietokantayhteyttä ei muodostettu.");
		}
		return con;
	}
    
	// read all ehdokas info from database, create ehdokas object and set it's properties according to database values
	// return a list of the created ehdokas objects
    public static ArrayList<Ehdokas> readFromDatabase(Connection con, String query) {
		ArrayList<Ehdokas> ehdokasLista = new ArrayList<Ehdokas>();
		
		try {
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
			PreparedStatement prepared = con.prepareStatement("UPDATE ehdokkaat SET etunimi = ?, sukunimi = ?, puolue = ?, esittely = ?, ehdokasnumero = ? WHERE ehdokasID = ?");
			prepared.setString(1, ehdokas.getEtunimi());
			prepared.setString(2, ehdokas.getSukunimi());
			prepared.setString(3, ehdokas.getPuolue());
			prepared.setString(4, ehdokas.getEsittely());
			prepared.setInt(5, ehdokas.getEhdokasNumero());
			prepared.setInt(6, ehdokas.getId());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		}		
		return rowsAffected;
	}

	// method receives an kysymys object and updates database entry corresponding to the id attribute of the object
    // with the given attributes
	public static int updateEntryQuestion(Connection con, Kysymys kysymys) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("UPDATE kysymykset SET kysymys = ? WHERE kysymysID = ?");
			prepared.setString(1, kysymys.getKysymys());
			prepared.setInt(2, kysymys.getId());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		}		
		return rowsAffected;
	}
	
}
