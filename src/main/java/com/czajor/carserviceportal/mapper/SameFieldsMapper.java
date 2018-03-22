package com.czajor.carserviceportal.mapper;

import java.lang.reflect.Field;


public class SameFieldsMapper {

    public static Object map(Object object, Object objectDto) throws IllegalAccessException {

        Field[] fields = object.getClass().getDeclaredFields();
        Field[] fieldsDto = objectDto.getClass().getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            for(Field fieldDto : fieldsDto) {
                fieldDto.setAccessible(true);
                if(fieldDto.get(objectDto) != null && field.getName().equals(fieldDto.getName())) {
                    field.set(object, fieldDto.get(objectDto));
                }
            }
        }

        return object;
    }
}
