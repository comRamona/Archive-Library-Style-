public class Istoric {
	String nr, pers, d1, d2, nume, prenume;

	public Istoric(String nr, String pers, String nume, String prenume,
			String d1, String d2) {
		this.nr = nr;
		this.pers = pers;
		this.nume = nume;
		this.prenume = prenume;
		this.d1 = d1;
		this.d2 = d2;
	}

	public String getNr() {
		return nr;
	}

	public String getPers() {
		return pers;
	}

	public String getD1() {
		return d1;
	}

	public String getD2() {
		return d2;
	}

	public String toString() {
		String s = nr + " " + pers + " " + nume + " " + prenume + " " + d1
				+ " " + d2 + "\n";
		return s;
	}

	public String[] matr() {
		String[] matr = { nr, pers, nume, prenume, d1, d2 };
		return matr;
	}
}
