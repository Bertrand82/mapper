package bg;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author bgui
 *
 */
public class UtilJar {

	public static void main(final String[] a) throws Exception {
		getClassPath("com.squareup.javapoet");
	}

	public static File getClassPath(final String packageName) throws Exception {

		final List<Class> listClasses = new ArrayList<>();

		System.err.println("UtilJar getSubClasses " + packageName);

		final String javaClassPaths[] = System.getProperty("java.class.path").split(";");
		for (final String s : javaClassPaths) {
			if (s.endsWith(".jar")) {
				processJarFile(s.trim(), packageName);
			} else {
				// C'est dans un folder
			}
		}
		return null;
	}

	/**
	 * @param pS
	 * @param pPackageName
	 */
	private static List<Class> processJarFile(final String jarName, final String packageName) throws Exception {
		final File file = new File(jarName);
		final List<String> listClassesName = new ArrayList<>();
		final JarFile jarFile = new JarFile(file);

		final Enumeration<JarEntry> enu = jarFile.entries();
		while (enu.hasMoreElements()) {
			final JarEntry jarEntry = enu.nextElement();
			final String name = jarEntry.getName().replaceAll("/", ".");
			if (name.startsWith(packageName)) {
				if (!name.contains("$") && (name.endsWith(".class"))) {
					final String nameCanonique = name.replace(".class", "");

					listClassesName.add(nameCanonique);
				}
			}

		}

		jarFile.close();
		return toClasses(listClassesName);
	}

	/**
	 * @param pListClassesName
	 * @return
	 */
	private static List<Class> toClasses(final List<String> listClassesName) {

		final List<Class> listClasses = new ArrayList<>();
		for (final String className : listClassesName) {
			Class clazz = null;
			try {
				clazz = UtilJar.class.getClassLoader().loadClass(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listClasses.add(clazz);
			System.out.println("clazz " + clazz.getName());
		}
		return listClasses;
	}

}