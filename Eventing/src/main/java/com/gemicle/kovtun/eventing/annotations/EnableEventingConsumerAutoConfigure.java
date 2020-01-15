package com.gemicle.kovtun.eventing.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.gemicle.kovtun.eventing.config.EventingConsumerAutoConfigure;
import com.gemicle.kovtun.eventing.config.EventingProducerConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EventingConsumerAutoConfigure.class)
public @interface EnableEventingConsumerAutoConfigure{
	/**
	 * for enabling Producer you should declare this annotation near
	 * your @SpringBootApplication
	 */
}