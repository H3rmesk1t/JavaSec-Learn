package CommonsCollections2;

import javassist.*;

import java.lang.reflect.Method;

/**
 * @Author: H3rmesk1t
 * @Data: 2021/11/26 3:09 下午
 */
public class JavassistCreateDemo {
    /**
     * 创建一个 Demo 对象
     */
    public static void main(String[] args) {
        try {
            createDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDemo() throws Exception {
        ClassPool classPool = ClassPool.getDefault();

        // 创建一个空类
        CtClass ctClass = classPool.makeClass("com.commons-collections.CommonsCollections2.javassist.Demo");
        // 增加一个字段名 way
        CtField ctField = new CtField(classPool.get("java.lang.String"), "way", ctClass);
        // 设置访问级别为 private
        ctField.setModifiers(Modifier.PRIVATE);
        // 设置初始信息
        ctClass.addField(ctField, CtField.Initializer.constant("Misc"));

        // 生成 getter、setter 方法
        ctClass.addMethod(CtNewMethod.setter("setWay", ctField));
        ctClass.addMethod(CtNewMethod.getter("getWay", ctField));

        // 设置无参构造函数
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        ctConstructor.setBody("{way = \"Misc\";}");
        ctClass.addConstructor(ctConstructor);

        // 设置有参构造函数
        CtConstructor ctConstructor1 = new CtConstructor(new CtClass[]{classPool.get("java.lang.String")}, ctClass);
        ctConstructor1.setBody("{$0.way = $1;}");
        ctClass.addConstructor(ctConstructor1);

        // 创建 printWayName 方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printWayName", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(way);}");
        ctClass.addMethod(ctMethod);

        // 编译构造内容
        // ctClass.writeFile("/Users/h3rmesk1t/Desktop/commons-collections/src/main/java/CommonsCollections2");
        Object demo = ctClass.toClass().getInterfaces();
        Method setWay = demo.getClass().getMethod("setWay", String.class);
        setWay.invoke(demo, "Web");
        Method execute = demo.getClass().getMethod("printWayName");
        execute.invoke(demo);
    }
}
