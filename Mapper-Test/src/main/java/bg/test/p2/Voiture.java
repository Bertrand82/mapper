package bg.test.p2;

public class Voiture extends AbstractVehicule{

	int nbDeRoue;
	double vMax;
	String marque;
	
	public Voiture() {
	}

	public Voiture(int nbDeRoue, double vMax, String marque,String name) {
		super();
		this.nbDeRoue = nbDeRoue;
		this.vMax = vMax;
		this.name=name;
		this.marque = marque;
	}

	public int getNbDeRoue() {
		return nbDeRoue;
	}

	public void setNbDeRoue(int nbDeRoue) {
		this.nbDeRoue = nbDeRoue;
	}

	public double getvMax() {
		return vMax;
	}

	public void setvMax(double vMax) {
		this.vMax = vMax;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	@Override
	public String toString() {
		return "Voiture [nbDeRoue=" + nbDeRoue + ", vMax=" + vMax + ", marque=" + marque + ", name=" + name + "]";
	}

}
