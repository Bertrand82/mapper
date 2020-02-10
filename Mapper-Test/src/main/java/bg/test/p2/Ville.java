package bg.test.p2;

public class Ville {

	private String name;
	private String pays;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ville() {
	}

	@Override
	public String toString() {
		return "Ville [name=" + name + ", pays=" + pays + "]";
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
