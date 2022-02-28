/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			WalletApplication.java
 * Description: 	Default spring application class to start the microservice.
 * Creation Date: 	27/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*********************************************************************************************
 * Name: 		WalletApplication
 * Description: Default class for running spring application.
 * Parameters: 	N/A
 * Author: 		Luke Earp
 * Created: 	27/02/2022
 * Version: 	1.0 - Created.
 * History: 
 ********************************************************************************************/
@SpringBootApplication
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

}
