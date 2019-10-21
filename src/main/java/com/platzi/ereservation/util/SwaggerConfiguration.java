package com.platzi.ereservation.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase para configurar Swagger - Las anotaciones @conf.. y @enable... Son para
 * expecificar que esta clase se haran las configuraciones de esta tecnologia.
 * 
 * @author andemar-pc
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	/**
	 * - Este metodo define la configuracion, y esta al pendiente de las anotaciones de las clases
	 * RestController. 
	 * @return
	 */
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
	}
}
