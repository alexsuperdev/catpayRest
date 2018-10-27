package domiu.dto;

public class Nutzer {

	private String nu_id;

	public Nutzer(String nu_id) {
		this.nu_id = nu_id;

	}
	
	@Override
	public String toString() {
		return this.nu_id;
	}

}
