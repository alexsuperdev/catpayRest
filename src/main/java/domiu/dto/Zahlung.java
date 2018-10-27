package domiu.dto;

public class Zahlung {

	private String tokenName;
	private String auftragId;
	private String rfidId;

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getAuftragId() {
		return auftragId;
	}

	public void setAuftragId(String auftragId) {
		this.auftragId = auftragId;
	}

	public String getRfid() {
		return rfidId;
	}

	public void setRfid(String rfid) {
		this.rfidId = rfid;
	}

}
