package com.segelzwerg.familyfotoandroid.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {
    /**
     * Sets the field of object.
     * @param instanceClass the class of the instance.
     * @param instance the object where the field should be set.
     * @param fieldName the string name of the field
     * @param fieldValue the value the field should have
     * @throws NoSuchFieldException is thrown if the field does not exists
     * @throws IllegalAccessException if this {@code Field} object is enforcing Java language
     * access control and the underlying field is either inaccessible or final.
     */
    public static void setField(Class<?> instanceClass,
                                Object instance,
                                String fieldName,
                                String fieldValue) throws NoSuchFieldException,
            IllegalAccessException {
        Field field = instanceClass.getField(fieldName);
        field.set(instance, fieldValue);
    }
}
