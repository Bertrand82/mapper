
package bg;

import java.io.File;
import java.util.List;

import com.squareup.javapoet.JavaFile;

import bg.poet.FactoryMapperPoet;
import bg.poet.PoetAbstract;
import bg.poet.PoetEnumMap;

/**
 * @author bgui
 *
 */
public class GeneratorMapperAbstractClass {

	private String packageName1;

	private String packageName2;
;

	private File dirOut;

	private final JavaPoetWriter javaPoetWriter;

	/**
	 * @param pClazz1
	 * @param pClazz2
	 */
	public GeneratorMapperAbstractClass(final File dirOutPut, String packageName1 ,  String packageName2 ) {
		javaPoetWriter = new JavaPoetWriter(dirOutPut);
		dirOut = dirOutPut;
		try {
			this.packageName1 = packageName1;
			this.packageName2 = packageName2;

			System.out.println("" + packageName1);
			System.out.println("" + packageName1);
			final List<Class> listSubClass1 = UtilReflection.getSubClasses(packageName1);
			final List<Class> listSubClass2 = UtilReflection.getClassesFromPackage(packageName1, packageRoot);
			System.out.println("subClass1   nb de classes in folder: " + listSubClass1.size() + " : " + listSubClass2.size());
			processListSubClass(listSubClass1, listSubClass2);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param pListSubClass1
	 * @param pListSubClass2
	 */
	private void processListSubClass(final List<Class> listSubClass1, final List<Class> listSubClasses2) {
		for (final Class c1 : listSubClass1) {
			final Class c2 = getClassFromSimpleList(c1.getSimpleName(), listSubClasses2);
			if (c1.getSimpleName().equals("package-info")) {
			} else if (c2 == null) {
				System.err.println("No class pair for c1: " + c1.getName() + "   " + c1.getName());
			} else {
				processClass(c1, c2);
			}
		}
		processCustomMapper();
		processAbstractMapper();
	}

	/**
	 * @param pC1
	 * @param pC2
	 */
	FactoryMapperPoet factoryPoet = new FactoryMapperPoet();

	private void processClass(final Class c1, final Class c2) {
		final JavaFile javafile = factoryPoet.generateInterfaceMapper(c1, c2);
		javaPoetWriter.write(javafile);
		System.out.println("writen : dir exists :" + javaPoetWriter.getDir().exists() + " dir.name: " + javaPoetWriter.getDir().getAbsolutePath());
	}

	private void processCustomMapper() {
		for (final PoetEnumMap pem : factoryPoet.getListJavaFileCustomMapper()) {
			final JavaFile javafile = pem.getJavaFile();
			javaPoetWriter.write(javafile);
			System.out.println("writen custom : " + javaPoetWriter.getDir().exists() + " " + javaPoetWriter.getDir().getName());
		}
	}

	private void processAbstractMapper() {
		System.out.println("writen custom abstract size " + factoryPoet.getListPoetAbstract().size());
		for (final PoetAbstract pa : factoryPoet.getListPoetAbstract()) {
			final JavaFile javafile = pa.getJavaFile();
			javaPoetWriter.write(javafile);
			System.out.println("writen abstract custom : " + javaPoetWriter.getDir().exists() + " " + javaPoetWriter.getDir().getName() + "  " + pa.getName());
		}
	}

	/**
	 * @param pName
	 * @param pListSubClass2
	 * @return
	 */
	private Class getClassFromSimpleList(final String simpleName, final List<Class> listSubClass2) {
		for (final Class c : listSubClass2) {
			if (c.getSimpleName().equals(simpleName)) {
				return c;
			}
		}
		return null;

	}

}