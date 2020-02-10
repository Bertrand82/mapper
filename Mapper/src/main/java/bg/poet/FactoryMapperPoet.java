package bg.poet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import bg.UtilReflection;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

/**
 * @author c82bgui
 *
 */
public class FactoryMapperPoet {
	private List<PoetEnumMap> listJavaFileCustomMapper = new ArrayList<>();
	private List<PoetAbstract> listPoetAbstract = new ArrayList<>();
	/**
	 * @param pC1
	 * @param pC2
	 */
	public JavaFile generateInterfaceMapper(final Class c1, final Class c2) {
		final String nameMapper = c1.getSimpleName() + "Mapper";
		List<PoetEnumMap> list1= processAnnotation(c1, c2);
		List<PoetEnumMap> list2= processAnnotation(c2, c1);
		PoetAbstract pa =processAbstractClass(c1,c2);
		String customMapper = PoetEnumMap.getNameClasses(list1,list2);
		customMapper = pa.geNameClassAstractCustom2(customMapper);
		AnnotationSpec.Builder annotationMapperBuilder = AnnotationSpec.builder(Mapper.class).
				addMember("withIgnoreFields", "{\"toto\",\"tata\"}").
				addMember("withEnums", "{}").				
				addMember("withCollectionStrategy", "$T.$L", CollectionMappingStrategy.class, CollectionMappingStrategy.ALLOW_GETTER.name()).
			    addMember("withCustom", "{"+customMapper+"}");
			
		AnnotationSpec annotationMapper = annotationMapperBuilder.build();
		final TypeSpec interfaceMapper = TypeSpec.interfaceBuilder(nameMapper).addModifiers(Modifier.PUBLIC).addAnnotation(annotationMapper).addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c2).addParameter(c1, "c1").build())
				.addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c1).addParameter(c2, "c2").build()).build();

		final JavaFile javaFile = JavaFile.builder("com.bg.generted", interfaceMapper).indent("    ").build();
		return javaFile;
	}

	private PoetAbstract processAbstractClass(Class c1, Class c2) {
		PoetAbstract pa = new PoetAbstract(c1,c2);
		System.out.println("processAbstractClass isAbstract "+c1.getSimpleName()+" isAbstract "+pa.isAbstract());
		if (pa.isAbstract()) {
			listPoetAbstract.add(pa);
		}
		return pa;
	}

	
	private List<PoetEnumMap> processAnnotation(Class c1, Class c2) {
		List<PoetEnumMap> list = new ArrayList<>();
		for (Field field : c1.getDeclaredFields()) {
			try {
				Class clazz = field.getType();
				Class clazzM = c2.getDeclaredField(field.getName()).getType();
				if (clazz.isEnum()) {
					PoetEnumMap pem = new PoetEnumMap(field, clazz, clazzM);
					listJavaFileCustomMapper.add(pem);
					list.add(pem);
				}
				;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<PoetEnumMap> getListJavaFileCustomMapper() {
		return listJavaFileCustomMapper;
	}

	public List<PoetAbstract> getListPoetAbstract() {
		return listPoetAbstract;
	}

}