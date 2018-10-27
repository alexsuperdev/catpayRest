package domiu.dto;

public class Zahlung {

	private String tokenName;
	private Antrag auftrag;
	private String rfidId;

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public Antrag getAuftrag() {
		return auftrag;
	}

	public void setAuftrag(Antrag auftrag) {
		this.auftrag = auftrag;
	}

	public String getRfid() {
		return rfidId;
	}

	public void setRfid(String rfid) {
		this.rfidId = rfid;
	}

}
