package bg.test.p1;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marque == null) ? 0 : marque.hashCode());
		result = prime * result + nbDeRoue;
		long temp;
		temp = Double.doubleToLongBits(vMax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Voiture other = (Voiture) obj;
		if (marque == null) {
			if (other.marque != null)
				return false;
		} else if (!marque.equals(other.marque))
			return false;
		if (nbDeRoue != other.nbDeRoue)
			return false;
		if (Double.doubleToLongBits(vMax) != Double.doubleToLongBits(other.vMax))
			return false;
		return true;
	}

}
