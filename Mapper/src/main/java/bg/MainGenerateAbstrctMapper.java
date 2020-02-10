package bg;

import java.io.File;

/**
 * @author c82bgui
 *
 */
public class MainGenerateAbstrctMapper {

    String packageSourcePath = "D:\\platformsg2_R_64\\workspace\\bm1atom-tarif\\bm1atom-tarif-ws\\target\\generated-sources\\cxf";

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {

        System.err.println("start");
        final Class clazz1 = getClassByName("bg.test.p1.Personn");
        final Class clazz2 = getClassByName("bg.test.p2.Personn");
        System.err.println("Start ---------------- " + clazz1);
        System.err.println("Start ---------------- " + clazz2);
        File dirTest = new File("..\\Mapper-Test");
        File dirOutput =new File(dirTest,"src\\main\\java");
        final GeneratorMapperAbstractClass gmpa = new GeneratorMapperAbstractClass(clazz1, clazz2,dirOutput);

    }

    private static Class getClassByName(final String s) throws ClassNotFoundException {
        return MainGenerateAbstrctMapper.class.getClassLoader().loadClass(s);
    }

}

