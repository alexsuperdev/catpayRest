package domiu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Antrag {

	@JsonProperty
	private String thema;
	@JsonProperty
	private String beschreibung;
	@JsonProperty
	private Integer wiederholung;
	@JsonProperty
	private Double betrag;
	@JsonProperty
	private String person;

	public Integer getAnzahlbezverfuegbar() {
		return anzahlbezverfuegbar;
	}

	@JsonProperty
	private Integer anzahlbezverfuegbar ;
	@JsonProperty
	private Date letztezahlung;

	@JsonProperty
	private String auftragnehmer;

	public Integer getAuftragid() {
		return auftragid;
	}

	public void setAuftragid(Integer auftragid) {
		this.auftragid = auftragid;
	}

	private Integer auftragid;

	public Antrag() {
		
	}
	
	public Antrag(String person, String auftragnehmer, String thema, String beschreibung, Double betrag,
			Integer wiederholung) {

		this.thema = thema;
		this.beschreibung = beschreibung;
		this.wiederholung = wiederholung;
		this.betrag = betrag;
		this.person = person;
		this.auftragnehmer = auftragnehmer;

	}

	public Antrag(Integer auftragid, String an_auftraggeber_id, String an_auftragnehmer_id, String an_thema,
				  String an_beschreibung, Double an_lohn, Integer an_anzahlbez, Integer an_anzahlbezverfuegbar,
				  String an_letztezahlung) {
		// TODO Auto-generated constructor stub
		this.auftragid = auftragid;
		this.thema = an_thema;
		this.beschreibung = an_beschreibung;
		this.wiederholung = an_anzahlbez;
		this.anzahlbezverfuegbar = an_anzahlbezverfuegbar;
		this.betrag = an_lohn;
		this.person = an_auftraggeber_id;
		this.auftragnehmer = an_auftragnehmer_id;

		// an_anzahlbezverfuegbar
		// an_letztezahlung
	}

	public String getAuftragnehmer() {
		return auftragnehmer;
	}

	public void setAuftragnehmer(String auftragnehmer) {
		this.auftragnehmer = auftragnehmer;
	}

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Integer getWiederholung() {
		return wiederholung;
	}

	public void setWiederholung(Integer wiederholung) {
		this.wiederholung = wiederholung;
	}

	public Double getBetrag() {
		return betrag;
	}

	public void setBetrag(Double betrag) {
		this.betrag = betrag;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {

		return this.beschreibung;
	}

}
