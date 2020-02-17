package next.reflection;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;

        logger.debug(clazz.getName());

        Field[] a = clazz.getDeclaredFields();
        for (Field field : a) {
            logger.debug("필드 : " + field);
        }

        System.out.println("/////");

        Method[] b = clazz.getDeclaredMethods();
        for (Method method : b) {
            logger.debug("메소드 : " + method);
        }

        System.out.println("/////");

        Constructor<?>[] c = clazz.getConstructors();
        for (Constructor<?> constructor : c) {
            logger.debug("생성자 : " + constructor);
        }
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void constructor() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }
    }
}
