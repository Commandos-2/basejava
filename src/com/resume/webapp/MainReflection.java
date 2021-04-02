package com.resume.webapp;

import com.resume.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume("Sergey Pavlov");
        Field field =resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        field.get(resume);
        System.out.println(resume);
        field.set(resume,"new UUID");
        System.out.println(resume);
        Method method=resume.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        System.out.println(method.invoke(resume));


    }
}
