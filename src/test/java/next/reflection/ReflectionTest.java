package next.reflection;

import java.lang.reflect.*;

import next.optional.User;
import next.student.Student;
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

    @Test
    public void privateFieldAccess() throws IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        Field[] fields= clazz.getDeclaredFields();
        Student s = new Student();
        for (Field field : fields) {
            field.setAccessible(true);

            if(field.getType() == String.class) {
                field.set(s, "댕냥");
            }
            if(field.getType() == int.class) {
                field.set(s, 30);
            }
        }
        logger.debug("학생 : {}", s);
    }

    @Test
    public void createUser () throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<User> clazz = User.class;

        Constructor[] c =clazz.getDeclaredConstructors();

        User user = null;
        for (Constructor constructor : c) {
            if(constructor.getParameterCount() == 2) {
                Type[] a = constructor.getParameterTypes();
                if(a[0].equals(String.class) && a[1].equals(Integer.class)) {
                    user = (User)constructor.newInstance("댕냥", 30);
                }
            }
        }
        logger.debug("유저 : {}", user);
    }
}
