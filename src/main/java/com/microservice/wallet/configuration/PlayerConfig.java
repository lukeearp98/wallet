/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			PlayerConfig.java
 * Description: 	File to configure the Swagger UI to allow the API to be tested with ease.
 * Creation Date: 	27/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class PlayerConfig 
{
	/*********************************************************************************************
	 * Name: 		springShopOpenAPI
	 * Description: Initialise the values shown on swagger UI.
	 * Parameters: 	N/A
	 * Returns: 	OpenAPI - The API config.
	 * Author: 		Luke Earp
	 * Created: 	27/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	@Bean
	  public OpenAPI springShopOpenAPI() { //Replaced springfox docket with this as springfox causing compilation errors with recent bug.
	      return new OpenAPI()
	              .info(new Info().title("Wallet Microservice API")
	              .description("Wallet microservice sample application")
	              .version("v1.0")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation());
	  }
}
