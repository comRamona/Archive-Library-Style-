public class InstantaDosar {
	private String nr, startDos, endDos, fileDos, volDos, invDos, persDos,
			raft, subraft, pastrare;
	private String obs, ref, arh;
	private int id;

	public InstantaDosar(String nr, String startDos, String endDos,
			String fileDos, String volDos, String invDos, String persDos,
			String raft, String subraft, String pastrare, String obs,
			String ref, String arh) {
		super();
		this.nr = nr;
		this.startDos = startDos;
		this.endDos = endDos;
		this.fileDos = fileDos;
		this.volDos = volDos;
		this.invDos = invDos;
		this.persDos = persDos;
		this.raft = raft;
		this.subraft = subraft;
		this.pastrare = pastrare;
		this.obs = obs;
		this.ref = ref;
		this.arh = arh;
	}

	public int getId() {
		return id;
	}

	public String getArh() {
		return arh;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getStartDos() {
		return startDos;
	}

	public void setStartDos(String startDos) {
		this.startDos = startDos;
	}

	public String getEndDos() {
		return endDos;
	}

	public void setEndDos(String endDos) {
		this.endDos = endDos;
	}

	public String getFileDos() {
		return fileDos;
	}

	public void setFileDos(String fileDos) {
		this.fileDos = fileDos;
	}

	public String getVolDos() {
		return volDos;
	}

	public void setVolDos(String volDos) {
		this.volDos = volDos;
	}

	public String getInvDos() {
		return invDos;
	}

	public void setInvDos(String invDos) {
		this.invDos = invDos;
	}

	public String getPersDos() {
		return persDos;
	}

	public void setPersDos(String persDos) {
		this.persDos = persDos;
	}

	public String getRaft() {
		return raft;
	}

	public void setRaft(String raft) {
		this.raft = raft;
	}

	public String getSubraft() {
		return subraft;
	}

	public void setSubraft(String subraft) {
		this.subraft = subraft;
	}

	public String getPastrare() {
		return pastrare;
	}

	public void setPastrare(String pastrare) {
		this.pastrare = pastrare;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return nr + " " + persDos;
	}

	public void setArh(String arh) {
		this.arh = arh;
	}

}
