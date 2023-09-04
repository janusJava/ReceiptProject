package com.alibou.security.project.dto.mapper;

import lombok.experimental.PackagePrivate;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@CommonsLog
@PackagePrivate
public abstract class BaseMapper {

    public static <T, K> K map(T sourceObject, Class<K> destinationClassType) {
        try {
            K desc = createInstance(destinationClassType);
            BeanUtils.copyProperties(sourceObject, desc);
            return desc;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            log.error("Could not convert " + sourceObject.getClass().getSimpleName() + " to " + destinationClassType.getSimpleName());
            throw new RuntimeException(e);
        }
    }

    private static <T> T createInstance(Class<T> type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return type.getDeclaredConstructor()
                .newInstance();
    }
}
