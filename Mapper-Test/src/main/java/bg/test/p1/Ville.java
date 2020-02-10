package bg.test.p1;

import bg.test.enump.COUNTRY;

public class Ville {

	private String name;
	private COUNTRY pays;
	public COUNTRY getPays() {
		return pays;
	}

	public void setPays(COUNTRY pays) {
		this.pays = pays;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ville() {
		// TODO Auto-generated constructor stub
	}

	public Ville(String name, COUNTRY pays) {
		super();
		this.name = name;
		this.pays = pays;
	}

	@Override
	public String toString() {
		return "Ville [name=" + name + ", pays=" + pays + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pays != other.pays)
			return false;
		return true;
	}

}
