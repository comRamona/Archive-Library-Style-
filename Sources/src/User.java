public class User {

	private String user, parola, status;

	/*
	 * Tipuri user: ori cauta:poate cauta modifica:poate
	 * cauta,adauga,modifica,sterge
	 */

	public User() {
		user = "";
		parola = "";
		status = "none";
	}

	public User(String user, String parola, String status) {
		this.user = user;
		this.parola = parola;
		this.status = status;
	}

	public String getParola() {
		return parola;
	}

	public String getUser() {
		return user;
	}

	public String getStatus() {
		return status;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isModify() {
		return status.equals("modifica");
	}

	public boolean isSee() {
		return status.equals("cauta");
	}

	public boolean isNone() {
		return status.equals("none");
	}

	public boolean isWpass() {
		return status.equals("wpass");
	}
}
