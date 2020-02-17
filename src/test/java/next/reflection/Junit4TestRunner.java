package next.reflection;

import org.junit.Test;

import java.lang.reflect.Method;

public class Junit4TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;

        Method[] m = clazz.getMethods();

        for (Method method : m) {
            if(method.isAnnotationPresent(MyTest.class)){
                method.invoke(clazz.newInstance());
            }
        }
    }
}
