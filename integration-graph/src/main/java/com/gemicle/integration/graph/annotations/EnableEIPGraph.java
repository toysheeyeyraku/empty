package com.gemicle.integration.graph.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.integration.http.config.EnableIntegrationGraphController;

import com.gemicle.integration.graph.config.EIPGraphConfiguration;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Inherited
@EnableIntegrationGraphController(path="/integration")
@Import({EIPGraphConfiguration.class})
public @interface EnableEIPGraph {
}

