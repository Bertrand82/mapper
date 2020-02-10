package bg.test;

import com.bg.generted.PersonnMapper;
import com.bg.generted.VilleMapper;
import com.bg.generted.VoitureMapper;

import bg.test.enump.COUNTRY;
import bg.test.p1.Personn;
import bg.test.p1.Ville;
import bg.test.p1.Voiture;
import fr.xebia.extras.selma.Selma;

public class MainMappage {

	public MainMappage() {

	}

	public static void main(String[] a) {
		PersonnMapper mapper = Selma.builder(PersonnMapper.class).build();
		Personn p1 = new Personn("AA", "ZZ", "EE");
		bg.test.p2.Personn p2 = mapper.map(p1);
		System.out.println("" + p1);
		System.out.println("" + p2);
		VilleMapper mapperVille = Selma.builder(VilleMapper.class).build();
		Ville v1 = new Ville("Montpezat", COUNTRY.FR);
		bg.test.p2.Ville v2 = mapperVille.map(v1);
		Ville v3 = mapperVille.map(v2);
		System.out.println("" + v1);
		System.out.println("" + v2);
		System.out.println("" + v3);
		System.out.println("v3.equals(v1) : " + v3.equals(v1));
		VoitureMapper voitureMapper = Selma.builder(VoitureMapper.class).build();
		Voiture voiture1 = new Voiture(4, 100.0d, "peugeot", "limousine");
		bg.test.p2.Voiture voiture2 = voitureMapper.map(voiture1);
		Voiture voiture3 = voitureMapper.map(voiture2);
		System.out.println("" + voiture1);
		System.out.println("" + voiture2);
		System.out.println("" + voiture3);
	}
}
