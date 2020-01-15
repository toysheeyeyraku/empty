package com.gemicle.kovtun.eventing.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gemicle.kovtun.eventing.config.EventingConsumerConfiguration;

/**
 * Class description goes here.
 *
 * @version 1.0.0 20.12.2019
 * @author Kovtun Bogdan
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EventingConsumerConfiguration.class)
public @interface EnableEventingConsumer {
	/**
	 * for enabling Consumer you should declare this annotation near
	 * your @SpringBootApplication
	 */
}