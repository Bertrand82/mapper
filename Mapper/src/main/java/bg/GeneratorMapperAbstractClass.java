package bg;

import java.io.File;
import java.util.List;

import com.squareup.javapoet.JavaFile;

import bg.poet.FactoryMapperPoet;
import bg.poet.PoetAbstract;
import bg.poet.PoetEnumMap;

/**
 * @author c82bgui
 *
 */
public class GeneratorMapperAbstractClass {

	Class clazz1;

	Class clazz2;
	
	File dirOut; 

	private final JavaPoetWriter javaPoetWriter;

	/**
	 * @param pClazz1
	 * @param pClazz2
	 */
	public GeneratorMapperAbstractClass(final Class clazz1, final Class clazz2, File dirOutPut) {
		javaPoetWriter = new JavaPoetWriter(dirOutPut);
		this.dirOut =dirOutPut;
		try {
			this.clazz1 = clazz1;
			this.clazz2 = clazz2;

			System.out.println(clazz1);
			System.out.println(clazz2);
			final List<Class> listSubClass1 = UtilReflection.getSubClasses(clazz1);
			final List<Class> listSubClass2 = UtilReflection.getSubClasses(clazz2);
			System.out.println("subClass1   nb de classes in folder: " + listSubClass1.size()+" : "+listSubClass2.size());
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
	private void processListSubClass(final List<Class> listSubClass1, final List<Class> listSubClass2) {
		for (final Class c1 : listSubClass1) {
			final Class c2 = getClassFromList(c1.getSimpleName(), listSubClass2);
			processClass(c1, c2);
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
		for (PoetEnumMap pem : factoryPoet.getListJavaFileCustomMapper()) {
			final JavaFile javafile = pem.getJavaFile();
			javaPoetWriter.write(javafile);
			System.out.println("writen custom : " + javaPoetWriter.getDir().exists() + " " + javaPoetWriter.getDir().getName());
		}
	}

	private void processAbstractMapper() {
		System.out.println("writen custom abstract size " + factoryPoet.getListPoetAbstract().size());
		for (PoetAbstract pa : factoryPoet.getListPoetAbstract()) {
			final JavaFile javafile = pa.getJavaFile();
			javaPoetWriter.write(javafile);
			System.out.println("writen abstract custom : " + javaPoetWriter.getDir().exists() + " " + javaPoetWriter.getDir().getName()+"  "+pa.getName());
		}
	}

	/**
	 * @param pName
	 * @param pListSubClass2
	 * @return
	 */
	private Class getClassFromList(final String simpleName, final List<Class> listSubClass2) {
		for (final Class c : listSubClass2) {
			if (c.getSimpleName().equals(simpleName)) {
				return c;
			}
		}
		return null;
	}

}
