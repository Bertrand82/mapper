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
		pClasses.setProperty("fr.v1.Voiture", "usa.v1.Car");
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
