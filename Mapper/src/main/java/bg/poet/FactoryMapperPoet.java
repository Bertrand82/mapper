
package bg.poet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

/**
 * @author bgui
 *
 */
public class FactoryMapperPoet {

	private final static List<String> listFieldForbiden = new ArrayList<>();
	static {
		listFieldForbiden.add("serialVersionUID");
	}

	private final List<PoetEnumMap> listJavaFileCustomMapper = new ArrayList<>();

	private final List<PoetAbstract> listPoetAbstract = new ArrayList<>();

	/**
	 * @param pC1
	 * @param pC2
	 */
	public JavaFile generateInterfaceMapper(final Class c1, final Class c2) {
		final String nameMapper = c1.getSimpleName() + "Mapper";
		final List<PoetEnumMap> list1 = processAnnotation(c1, c2);
		final List<PoetEnumMap> list2 = processAnnotation(c2, c1);
		final PoetAbstract pa = processAbstractClass(c1, c2);
		String customMapper = PoetEnumMap.getNameClasses(list1, list2);
		customMapper = pa.geNameClassAstractCustom2(customMapper);
		final AnnotationSpec.Builder annotationMapperBuilder = AnnotationSpec.builder(Mapper.class).addMember("withIgnoreFields", "{\"toto\",\"tata\"}").addMember("withEnums", "{}").addMember("withCollectionStrategy", "$T.$L", CollectionMappingStrategy.class, CollectionMappingStrategy.ALLOW_GETTER.name()).addMember("withCustom", "{" + customMapper + "}");

		final AnnotationSpec annotationMapper = annotationMapperBuilder.build();
		final TypeSpec interfaceMapper = TypeSpec.interfaceBuilder(nameMapper).addModifiers(Modifier.PUBLIC).addAnnotation(annotationMapper).addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c2).addParameter(c1, "c1").build())
				.addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c1).addParameter(c2, "c2").build()).build();

		final JavaFile javaFile = JavaFile.builder("com.bg.generated", interfaceMapper).indent("    ").build();
		return javaFile;
	}

	private PoetAbstract processAbstractClass(final Class c1, final Class c2) {
		final PoetAbstract pa = new PoetAbstract(c1, c2);
		System.out.println("processAbstractClass isAbstract " + c1.getSimpleName() + " isAbstract " + pa.isAbstract());
		if (pa.isAbstract()) {
			listPoetAbstract.add(pa);
		}
		return pa;
	}

	private List<PoetEnumMap> processAnnotation(final Class c1, final Class c2) {
		final List<PoetEnumMap> list = new ArrayList<>();
		Class clazz = null;
		Class clazzM = null;
		for (final Field field : c1.getDeclaredFields()) {
			if (isFieldProcessable(field)) {
				try {
					clazz = field.getType();
					clazzM = c2.getDeclaredField(field.getName()).getType();
					final boolean isCustomMappage = isCustomMappage(clazz, clazzM);
					if (clazz.isEnum()) {
						final PoetEnumMap pem = new PoetEnumMap(field, clazz, clazzM);
						listJavaFileCustomMapper.add(pem);
						list.add(pem);
					}
					;
				} catch (final Exception e) {
					System.err.println("Exception1 Field :" + field.getName());
					System.err.println("Exception2 class1 :" + (c1 == null ? null : (c1.getSimpleName() + "   " + c1.getName())));
					System.err.println("Exception3 class2 :" + (c2 == null ? null : (c2.getSimpleName() + "   " + c2.getName())));
					System.err.println("Exception4 clazz Field 1" + (clazz == null ? null : clazz.getSimpleName()));
					System.err.println("Exception5 clazz Field 2 " + (clazzM == null ? null : clazzM.getSimpleName()));

					// e.printStackTrace();
				}
			}
		}
		return list;
	}

	private final List<String> listFieldNonTraite = new ArrayList<>();

	/**
	 * @param pField
	 * @return
	 */
	private boolean isFieldProcessable(final Field field) {
		if (field == null) {
			return false;
		} else if (listFieldForbiden.contains(field.getName())) {
			return false;
		} else if (listFieldNonTraite.contains(field.getName())) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @param pClazz
	 * @param pClazzM
	 * @return
	 */
	private boolean isCustomMappage(final Class c1, final Class c2) {
		if (c1.isEnum() && c2.isEnum()) {
			return false;
		} else if (c1.isEnum() && !c2.isEnum()) {
			return true;
		} else if (!c1.isEnum() && c2.isEnum()) {
			return true;
		}
		return false;
	}

	public List<PoetEnumMap> getListJavaFileCustomMapper() {
		return listJavaFileCustomMapper;
	}

	public List<PoetAbstract> getListPoetAbstract() {
		return listPoetAbstract;
	}

}