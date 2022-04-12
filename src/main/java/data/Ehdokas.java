package data;
import java.util.Comparator;

public class Ehdokas {
	
	int id;
	String etunimi;
	String sukunimi;
	String puolue;
	String esittely;
	int ehdokasNumero;
	String kayttajanimi;
	String salasana;
	
	public Ehdokas() {
		
	}
	
	
	public Ehdokas(int id, String etunimi, String sukunimi, String puolue, String esittely, int ehdokasNumero,
			String kayttajanimi, String salasana) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.puolue = puolue;
		this.esittely = esittely;
		this.ehdokasNumero = ehdokasNumero;
		this.kayttajanimi = kayttajanimi;
		this.salasana = salasana;
	}
	
	public void printEhdokas() {
		
		System.out.println("Ehdokkaan tiedot: ");
		System.out.println("Nimi: " + this.etunimi + " " + this.sukunimi);
		System.out.println("Puolue: " + this.puolue);
		System.out.println("Ehdokasnumero: " + this.ehdokasNumero);
		System.out.println("Esittelyteksti: " +this.esittely);		
		
	}



	@Override
	public String toString() {
		return "Ehdokas [id=" + id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", puolue=" + puolue
				+ ", esittely=" + esittely + ", ehdokasNumero=" + ehdokasNumero + ", kayttajanimi=" + kayttajanimi
				+ ", salasana=" + salasana + "]";
	}


	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getPuolue() {
		return puolue;
	}

	public void setPuolue(String puolue) {
		this.puolue = puolue;
	}

	public String getEsittely() {
		return esittely;
	}

	public void setEsittely(String esittely) {
		this.esittely = esittely;
	}

	public int getEhdokasNumero() {
		return ehdokasNumero;
	}

	public void setEhdokasNumero(int ehdokasNumero) {
		this.ehdokasNumero = ehdokasNumero;
	}

	public String getKayttajanimi() {
		return kayttajanimi;
	}

	public void setKayttajanimi(String kayttajanimi) {
		this.kayttajanimi = kayttajanimi;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
