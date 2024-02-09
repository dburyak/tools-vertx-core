package com.dburyak.vertx.eventbus;

import jakarta.inject.Singleton;

/**
 * Json message codec that does not transform message for local communications. This codec does not provide any
 * guarantees about message visibility, nor performs defensive copying. It is suitable only for immutable objects.
 *
 * @param <T> type of message to send
 */
@Singleton
public class LocalAwareJsonUnsafeMessageCodec<T> extends JsonMessageCodec<T, T> {

    @Override
    public T transform(T message) {
        return message;
    }
}
