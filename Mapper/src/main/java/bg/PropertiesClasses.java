/**
 *
 */
package bg;

import java.util.Properties;

/**
 * @author bgui
 *
 */
public class PropertiesClasses {

	public static Properties pClasses = new Properties();
	static {
		pClasses.setProperty("fr.msa.atom.sante.prestationsennature.facturepn.objets.individusanteatom.v1.Assureur", "fr.atom.referentiel.assurance.domain.assureur.Assureur");
		pClasses.setProperty("fr.msa.atom.sante.prestationsennature.facturepn.objets.individusanteatom.v1.SoinsPlanifies", "fr.atom.sante.relationsassure.contratassurance.domain.contratassurance.SoinsPlanifies");
	}

	static Class getClassesPair(final String className) {
		try {
			final String name = pClasses.getProperty(className);
			if (name == null) {
				return null;
			}
			return PropertiesClasses.class.getClassLoader().loadClass(name);
		} catch (final ClassNotFoundException e) {
			return null;
		}

	}
}
