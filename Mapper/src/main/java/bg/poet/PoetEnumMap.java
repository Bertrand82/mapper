package bg.poet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import bg.test.enump.COUNTRY;

public class PoetEnumMap {
	Field field;

	Class clazzEnum;

	Class clazzB;

	public PoetEnumMap(Field field, Class clazzA, Class clazzB) {
		super();
		this.field = field;
		this.clazzEnum = clazzA;
		this.clazzB = clazzB;
	}

	@Override
	public String toString() {
		return "PoetEnumMap [field=" + field.getName() + "  type: " + field.getType() + "]";
	}

	private String getClassMapperName() {
		return "MapperCustom" + clazzEnum.getSimpleName();
	}

	public JavaFile getJavaFile() {
		MethodSpec method1 = MethodSpec.methodBuilder("as" + clazzEnum.getSimpleName())
				.addParameter(clazzB, "s")
				.addModifiers(Modifier.PUBLIC)
				.returns(clazzEnum)
            	.addStatement("return $T.valueOf(\"\"+s)",clazzEnum)
				.build();
		MethodSpec.Builder method2b =MethodSpec.methodBuilder("as" + clazzB.getSimpleName())
				.addParameter(clazzEnum, "enumP")
				.addModifiers(Modifier.PUBLIC)
				.returns(clazzB);
		if(clazzB.isEnum()) {
			method2b.addStatement("return $T.valueOf(enumP.name())",clazzB);
		}else {
				method2b.addStatement("return enumP.name()");
		}		
				MethodSpec method2 =method2b.build();
		TypeSpec customClassMapper = 
				TypeSpec.classBuilder(getClassMapperName())
				.addModifiers(Modifier.PUBLIC)
				.addMethod(method1)
				.addMethod(method2)
				.build();
		final JavaFile javaFile = JavaFile.builder("com.bg.generted", customClassMapper).indent("    ").build();
		return javaFile;
	}

	private String getNameClassEnum(Class clazzEnum2) {
		System.out.println("getNameClassEnum "+clazzEnum2.isMemberClass()+" "+clazzEnum2.getName());
		/*if (clazzEnum2.isMemberClass()) {
			return clazzEnum2.getEnclosingClass().getSimpleName()+"."+clazzEnum2.getSimpleName();
			
		}*/
		return clazzEnum2.getName();
	}

	public boolean isInside(List<PoetEnumMap> list) {
		for (PoetEnumMap poe : list) {
			if (equals2(poe)) {
				return true;
			}
		}
		return false;
	}

	private boolean equals2(PoetEnumMap poe) {
		if (poe == null) {
			return false;
		}
		return (poe.clazzB.equals(clazzB)) && (poe.clazzEnum.equals(clazzEnum));

	}

	public static String getNameClasses(List<PoetEnumMap>... lists) {
		List<PoetEnumMap> list = new ArrayList<PoetEnumMap>();
		for (List<PoetEnumMap> l : lists) {
			list.addAll(l);
		}
		List<PoetEnumMap> list0 = consolideListEnum(list);
		String s = "";
		for (PoetEnumMap poe : list0) {
			if (!s.isEmpty()) {
				s +=",";
			}
			s += poe.getClassMapperName() + ".class";
		}
		return s;
	}

	private static List<PoetEnumMap> consolideListEnum(List<PoetEnumMap> listEnumMapper) {
		List<PoetEnumMap> list = new ArrayList<>();
		for (PoetEnumMap poe : listEnumMapper) {
			if (!poe.isInside(list)) {
				list.add(poe);
			}
		}
		return list;
	}

}
