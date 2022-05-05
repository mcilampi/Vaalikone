package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/** Class to instatiate Kysymys objects. 
 * 
 * Includes REST annotations because the class is also used for practicing REST.
 * 
 * @author hannu
 *
 */
@Entity
@Table
public class Kysymys {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kysymysID")
	int id;
	String kysymys;
	String tunniste;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kysymys() {
		
	}

	public String getKysymys() {
		return kysymys;
	}

	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}
	
	public String getTunniste() {
		return tunniste;
	}

	public void setTunniste(String tunniste) {
		this.tunniste = tunniste;
	}


}
