
package bg.poet;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import bg.UtilReflection;
import fr.xebia.extras.selma.Selma;

public class PoetAbstract {
	Class clazz1;
	Class clazz2;
	List<ClassCouple> listClassesCouple = new ArrayList<PoetAbstract.ClassCouple>();
	boolean isAbstract;

	PoetAbstract() {
	}

	public PoetAbstract(Class clazz1, Class clazz2) {
		super();
		this.clazz1 = clazz1;
		this.clazz2 = clazz2;
		isAbstract = java.lang.reflect.Modifier.isAbstract(clazz1.getModifiers());
		if (isAbstract) {
			List<Class> sc1 = UtilReflection.getClassesChildreen(clazz1);
			List<Class> sc2 = UtilReflection.getClassesChildreen(clazz2);
			System.out.println("childreen size " + sc1.size());
			for (Class c1 : sc1) {
				Class c2 = getClassBySimpleName(c1.getSimpleName(), sc2);
				ClassCouple cc = new ClassCouple(c1, c2);
				listClassesCouple.add(cc);
			}
		}
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	private Class getClassBySimpleName(String name, List<Class> sc2) {
		for (Class c : sc2) {
			if (c.getSimpleName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	private class ClassCouple {
		Class c1;
		Class c2;

		ClassCouple(Class c1, Class c2) {
			this.c1 = c1;
			this.c2 = c2;
		}
	}

	public String geNameClassAstractCustom2(String s) {
		if (s == null) {
			s = "";
		}

		if (isAbstract) {
			if (!s.trim().isEmpty()) {
				s += ",";
			}
			s += getNameClassCustom() + ".class";
		}
		return s;
	}

	private String getNameClassCustom() {

		return "MapCustom" + clazz1.getSimpleName();
	}

	public JavaFile getJavaFile() {

		TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(getNameClassCustom()).addModifiers(Modifier.PUBLIC);

		for (ClassCouple cc : listClassesCouple) {
			ClassName className = ClassName.get("com.bg.generted", cc.c1.getSimpleName() + "Mapper");
			FieldSpec mapper = FieldSpec.builder(className, cc.c1.getSimpleName().toLowerCase() + "Mapper").addModifiers(Modifier.PRIVATE).initializer(" $T.builder($T.class).build()", Selma.class, className).build();
			typeBuilder.addField(mapper);
		}
		MethodSpec.Builder methode1b = MethodSpec.methodBuilder("as" + clazz1.getSimpleName()).addParameter(clazz2, "o").addModifiers(Modifier.PUBLIC).returns(clazz1).addStatement("if (o == null) return null;");
		for (ClassCouple cc : listClassesCouple) {
			methode1b.addStatement("if (o instanceof " + cc.c2.getName() + ") return " + cc.c2.getSimpleName().toLowerCase() + "Mapper.map(($T)o)" + ";", cc.c2);
		}
		methode1b.addStatement("return null");
		MethodSpec methode1 = methode1b.build();
		MethodSpec.Builder methode2b = MethodSpec.methodBuilder("as" + clazz2.getSimpleName()).addParameter(clazz1, "o").addModifiers(Modifier.PUBLIC).returns(clazz2).addStatement("return null");
		MethodSpec methode2 = methode2b.build();

		typeBuilder.addMethod(methode1);
		typeBuilder.addMethod(methode2);

		TypeSpec customClassMapper = typeBuilder.build();
		final JavaFile javaFile = JavaFile.builder("com.bg.generted", customClassMapper).indent("    ").build();
		return javaFile;
	}

	public String getName() {
		return clazz1.getSimpleName();
	}

}