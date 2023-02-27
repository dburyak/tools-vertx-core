package com.dburyak.vertx.eventbus.kryo;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Singleton
@Slf4j
public class LocalAwareKryoCopyingMessageCodec<T> extends KryoMessageCodecBase<T, T> {

    @SuppressWarnings("unchecked")
    @Override
    public T transform(T data) {
        // copy object via copy constructor
        var dataClass = data.getClass();
        try {
            return (T) dataClass.getDeclaredConstructor(dataClass).newInstance(data);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            log.error("failed to find public copy constructor of data object", e);
            throw new IllegalArgumentException("data object must implement public copy constructor: " + data, e);
        } catch (InvocationTargetException e) {
            log.error("failed to call copy constructor of data object", e);
            throw new RuntimeException(e);
        }
    }
}
