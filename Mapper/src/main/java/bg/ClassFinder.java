package bg;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bgui
 *
 */
public class ClassFinder {

	private static final char PKG_SEPARATOR = '.';

	private static final char DIR_SEPARATOR = '/';

	private static final String CLASS_FILE_SUFFIX = ".class";

	private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

	public static List<Class<?>> find(final String scannedPackage) {
		final String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
		final URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
		if (scannedUrl == null) {
			throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
		}
		final File scannedDir = new File(scannedUrl.getFile());
		final List<Class<?>> classes = new ArrayList<Class<?>>();
		for (final File file : scannedDir.listFiles()) {
			classes.addAll(find(file, scannedPackage));
		}
		return classes;
	}

	private static List<Class<?>> find(final File file, final String scannedPackage) {
		final List<Class<?>> classes = new ArrayList<Class<?>>();
		final String resource = scannedPackage + PKG_SEPARATOR + file.getName();
		if (file.isDirectory()) {
			for (final File child : file.listFiles()) {
				classes.addAll(find(child, resource));
			}
		} else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
			final int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
			final String className = resource.substring(0, endIndex);
			try {
				classes.add(Class.forName(className));
			} catch (final ClassNotFoundException ignore) {
			}
		}
		return classes;
	}

}
