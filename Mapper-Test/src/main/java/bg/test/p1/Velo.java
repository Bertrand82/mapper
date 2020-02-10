package bg.test.p1;

public class Velo extends AbstractVehicule{

	int nbRoue; 
	
	public enum TYPE_VELO {
		TRAIL,VILLE,SPORTIF
	}
	TYPE_VELO type;
	
	public Velo() {
	}

	public Velo(int nbRoue, TYPE_VELO type, String name) {
		super();
		this.nbRoue = nbRoue;
		this.type = type;
		this.name = name;
	}

	public int getNbRoue() {
		return nbRoue;
	}

	public void setNbRoue(int nbRoue) {
		this.nbRoue = nbRoue;
	}

	public TYPE_VELO getType() {
		return type;
	}

	public void setType(TYPE_VELO type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Velo [nbRoue=" + nbRoue + ", type=" + type + ", name=" + name + "]";
	}

}
