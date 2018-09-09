package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.PersonForEquals;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {
    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = Employee.class.getName();
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee");

        // TODO: please created an instance described by `theClass`
        // <--start
        Employee instance = (Employee) theClass.newInstance();
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        Method[] methods = doubleClass.getDeclaredMethods();
        List<String> publicStaticMethodsNames = new ArrayList<>();
        for (Method method : methods) {
            int modifier = method.getModifiers();
            if(Modifier.isPublic(modifier) && Modifier.isStatic(modifier)){
                publicStaticMethodsNames.add(method.getName());
            }
        }
        String[] publicStaticMethods = publicStaticMethodsNames.toArray(new String[0]);
        Arrays.sort(publicStaticMethods);
        // --end-->

        final String[] expected = {
            "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
            "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
            "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
            "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethods);
    }

    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Object employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        Object result = employee.getClass().getDeclaredMethod("getTitle").invoke(employee);
        //Object result = employee.getClass().getDeclaredMethod("getTitle", Integer.class).invoke(employee, 1);
        // --end-->

        assertEquals("Employee", result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();
        // --end-->

        assertEquals(Employee.class, itemClass);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
        Class<myMethodContainsAnnotation> myClass = myMethodContainsAnnotation.class;
        Method[] methods = myClass.getDeclaredMethods();
        List<String> containsAnnotationMethodsNames = new ArrayList<>();
        for (Method method : methods) {
            if(method.isAnnotationPresent(MyAnnotation.class)){
                containsAnnotationMethodsNames.add(method.getName());
            }
        }
        String[] methodsContainsAnnotation = containsAnnotationMethodsNames.toArray(new String[0]);
        String[] expectedMethodsNames = new String[]{"myMethod","myMethod2"};
        assertArrayEquals(expectedMethodsNames, methodsContainsAnnotation);
    }

    @Test
    void theSupperClassOfArrayOfOneClassIsNotTheAtrrayOfTheSupperClassOfThatClass() {
        Student[] students = new Student[4];
        People[] people = new People[4];
        students[0] = new Student();
        people[0] = new People();
        assertFalse(people.getClass().equals(students.getClass().getSuperclass()));
        assertTrue(people[0].getClass().equals(students[0].getClass().getSuperclass()));
    }

    @Test
    void objectArraySort() {
        PersonForEquals person1 = new PersonForEquals("Tom",(short)1987);
        PersonForEquals person2 = new PersonForEquals("Jan",(short)1990);
        PersonForEquals person3 = new PersonForEquals("Marry",(short)1989);
        PersonForEquals person4 = new PersonForEquals("Jack",(short)1991);
        PersonForEquals[] initialPerson = new PersonForEquals[]{person1,person2,person3,person4};
        Arrays.sort(initialPerson);
        PersonForEquals[] expectedPerson = new PersonForEquals[]{person4,person2,person3,person1};
        assertArrayEquals(expectedPerson, initialPerson);
    }
}

/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
