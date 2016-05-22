package com.epam.homework_6_1.analyzers;

import com.epam.homework_6_1.caches.annotations.InjectCache;
import com.epam.homework_6_1.exceptions.AnaliseException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectCacheAnnotationAnalyzer {

    public static Set<String> analyseInjectCacheNames(Object object) {
        HashSet<String> injectCacheNames = new HashSet<>();
        Class<?> clazz;
        for (clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] declaredFields = clazz.getDeclaredFields();
            analyseFields(injectCacheNames, declaredFields);
        }
        checkSetOfNames(injectCacheNames);
        return injectCacheNames;
    }

    private static void checkSetOfNames(HashSet<String> injectCacheNames) {
        if (injectCacheNames.isEmpty()) {
            throw new AnaliseException("There is no field that has at least one InjectCache annotation");
        }
    }

    private static void analyseFields(HashSet<String> injectCacheNames, Field[] fields) {
        for (Field field : fields) {
            Optional<InjectCache> optionalAnnotation = Optional.ofNullable(field.getAnnotation(InjectCache.class));
            optionalAnnotation.ifPresent(annotation -> {
                String name = annotation.name();
                checkEmptyName(name, field);
                injectCacheNames.add(name);
            });
        }
    }

    private static void checkEmptyName(String name, Field field) {
        if (name == null || name.equals("")) {
            throw new AnaliseException("At " + field.getName() +
                    "of " + field.getClass().getName() +
                    " declared " + InjectCache.class.getName() + " is empty");
        }
    }

    public static Set<Field> analyseInjectCacheFields(Object toObject, String annotationName) {
        /*TODO
        if superclass has local/protected/public fields with the same field name that subclass has
         - don't init superclass fields
         */
        Set<Field> allFieldsSet = new HashSet<>();
        setAllFieldsInMap(toObject, allFieldsSet);
        Stream<Field> fieldStream = allFieldsSet.stream().filter(field -> {
            InjectCache annotation = field.getDeclaredAnnotation(InjectCache.class);
            return (annotation != null) && (annotation.name().equals(annotationName));
        });
        return fieldStream.collect(Collectors.toSet());

    }

    private static void setAllFieldsInMap(Object toObject, Set<Field> map) {
        Class<?> clazz = toObject.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] declaredFields = clazz.getDeclaredFields();
            Collections.addAll(map, declaredFields);
        }
    }

}
