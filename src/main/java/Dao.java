import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import data.Ehdokas;
import data.Kysymys;
/**
 * DAO class, includes all the methods that connect to database.
 * @author hannu
 *
 */
public class Dao {
	
	static String DBpath = "//localhost:3306/vaalikone";
	static String username = "hannu";
	static String password = "kukkuu123";
	static String query = "select * from ehdokkaat";
	static String queryy = "select * from KYSYMYS";
	
	/**
	 * Close the database connection.
	 * @param con
	 */
	public static void closeDatabaseConnection(Connection con) {
		try {
			con.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.out.println("Not able to close database connection.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Read distinct tags from the database table and return them as an ArrayList.
	 * @param con
	 * @return
	 */
	public static ArrayList<String> readDistinctTags(Connection con) {
		ArrayList<String> tags = new ArrayList<String>();
		try {
			PreparedStatement prepared = con.prepareStatement("select distinct TUNNISTE , case when TUNNISTE is null then 'none' else TUNNISTE end from KYSYMYS");
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				tags.add(result.getString("TUNNISTE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tags;
	}
	
	/**
	 * Get all questions with the ceartain tag from database.
	 * @param con
	 * @param tag
	 * @return
	 */
	public static ArrayList<Kysymys> readAllQuestionsWithTagFromDatabase(Connection con, String tag) {
		ArrayList<Kysymys> kysymykset = new ArrayList<Kysymys>();
		try {
			PreparedStatement prepared = con.prepareStatement("select * from KYSYMYS where TUNNISTE like(?)");
			prepared.setString(1, tag);
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				Kysymys kysymys = new Kysymys();
				kysymys.setId(result.getInt("kysymysID"));
				kysymys.setKysymys(result.getString("kysymys"));
				kysymys.setTunniste(result.getString("TUNNISTE"));
				kysymykset.add(kysymys);
			}
		} catch (SQLException e) {
			System.out.println("Kysymysten haku ei onnistunut.");
			e.printStackTrace();
		}
		
		return kysymykset;
	}
	
	/**
	 * Read all questions from database.
	 * @param con
	 * @return
	 */
	public static ArrayList<Kysymys> readAllQuestionsFromDatabase(Connection con) {
		ArrayList<Kysymys> kysymykset = new ArrayList<Kysymys>();
		try {
			PreparedStatement prepared = con.prepareStatement("select * from KYSYMYS");
			ResultSet result = prepared.executeQuery();
			while (result.next()) {
				Kysymys kysymys = new Kysymys();
				kysymys.setId(result.getInt("kysymysID"));
				kysymys.setKysymys(result.getString("kysymys"));
				kysymys.setTunniste(result.getString("TUNNISTE"));
				kysymykset.add(kysymys);
			}
		} catch (SQLException e) {
			System.out.println("Kysymysten haku ei onnistunut.");
			e.printStackTrace();
		}
		
		return kysymykset;
	}
	
	 /**
	  * Returns only one question object that has it's attributes read from the database columns.
	  * @param con
	  * @param queryy
	  * @param annettuKysymys
	  * @return
	  */
    public static Kysymys readOneKysymysFromDatabase(Connection con, String queryy, int annettuKysymys) {
    	Kysymys kysymys = new Kysymys();
    	try {
			PreparedStatement prepared = con.prepareStatement(queryy);
			ResultSet result = prepared.executeQuery();
			while(result.next()) {
				if (result.getInt("kysymysID") == annettuKysymys) {
					kysymys.setId(result.getInt("kysymysID"));
					kysymys.setKysymys(result.getString("kysymys"));
					kysymys.setTunniste(result.getString("TUNNISTE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return kysymys;
	}
	
	/**
	 * Insert question into database
	 * @param con
	 * @param kysymys
	 * @return
	 */
	public static int createQuestion(Connection con, Kysymys kysymys) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("INSERT INTO KYSYMYS(kysymys,TUNNISTE) VALUES(?,?)");
			prepared.setString(1, kysymys.getKysymys());
			prepared.setString(2, kysymys.getTunniste());
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {
			System.out.println("EI voitu lisätä kysymystä.");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	/**
	 * Insert candidate into database
	 * @param con
	 * @param ehdokas
	 * @return
	 */
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
	
	/**
	 * Delete candidate from database
	 * @param con
	 * @param candidateId
	 * @return
	 */
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
	
	/**
	 * Delete question from database
	 * @param con
	 * @param questionId
	 * @return
	 */
			public static int deleteQuestion(Connection con, int questionId) {
				int rowsAffected = 0;
				try {
					PreparedStatement prepared = con.prepareStatement("DELETE FROM KYSYMYS WHERE kysymysID = ?");
					prepared.setInt(1, questionId);
					rowsAffected = prepared.executeUpdate();
				} catch (SQLException e) {
					System.out.println("Kysymyksen poistaminen ei onnistunut.");
					e.printStackTrace();
				}
				return rowsAffected;
			}
		
	
	/**
	 * Create connection to database
	 * @param DBpath
	 * @param username
	 * @param password
	 * @return
	 */

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
    
	/**
	 *  Read all ehdokas info from database, create ehdokas object and set it's properties according to database values
	 *  return a list of the created ehdokas objects
	 * @param con
	 * @param query
	 * @return
	 */

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
    
    /**
     * Returns only one ehdokas object that has it's attributes read from the database columns
     * @param con
     * @param query
     * @param annettuEhdokas
     * @return
     */
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
    
    /**
     * Method receives an ehdokas object and updates database entry corresponding to the id attribute of the object
     * with the given attributes
     * @param con
     * @param ehdokas
     * @return
     */
    
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

	/**
	 * Method receives an kysymys object and updates database entry corresponding to the id attribute of the object
	 * with the given attributes
	 * @param con
	 * @param kysymys
	 * @return
	 */
	public static int updateEntryQuestion(Connection con, Kysymys kysymys) {
		int rowsAffected = 0;
		try {
			PreparedStatement prepared = con.prepareStatement("UPDATE KYSYMYS SET kysymys = ?, TUNNISTE = ?  WHERE kysymysID = ?");
			prepared.setString(1, kysymys.getKysymys());
			prepared.setString(2, kysymys.getTunniste());
			prepared.setInt(3, kysymys.getId());
			
			rowsAffected = prepared.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		}		
		return rowsAffected;
	}
	
}
