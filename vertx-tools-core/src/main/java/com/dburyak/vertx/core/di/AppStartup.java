package com.dburyak.vertx.core.di;

import jakarta.inject.Qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marker qualifier annotation for beans that need to be initialized early on the application startup, before deploying
 * any verticles, but after all the beans marked with {@link AppBootstrap} qualifier have already been initialized.
 * <p>
 * Beans are requested only once per application, so they should be singletons. Such beans are expected to either do
 * their initialization work in {@link jakarta.annotation.PostConstruct} methods. Or they can implement
 * {@link com.dburyak.vertx.core.AsyncAction} interface and do their initialization work asynchronously in the
 * {@link com.dburyak.vertx.core.AsyncAction#execute()} method. Async action is guaranteed to be executed on vertx
 * context. Ordering of async actions is not supported as of now, no any assumptions should be made about the order of
 * async actions execution.
 * <p>
 * Typical usage: eagerly create connections to DB so that some are available when the first requests arrive,
 * pre-populate caches with rarely changing data to avoid cold starts, etc.
 */
@Qualifier
@Retention(RUNTIME)
@Documented
public @interface AppStartup {
}
